package com.example.nobelabookreader

import android.content.Intent

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nobelabookreader.dataclass.EmailCheckRequest
import com.example.nobelabookreader.dataclass.RegistrationRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var emailEt: EditText
    private lateinit var nameEt: EditText
    private lateinit var passwordEt: EditText
    private lateinit var cPasswordEt: EditText
    private lateinit var registerBtn: Button

    private lateinit var email: String
    private lateinit var name: String
    private lateinit var password: String
    private lateinit var confirmPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        emailEt = findViewById(R.id.emailEt)
        nameEt = findViewById(R.id.nameEt)
        passwordEt = findViewById(R.id.passwordEt)
        cPasswordEt = findViewById(R.id.cPasswordEt)
        registerBtn = findViewById(R.id.registerBtn)

        registerBtn.setOnClickListener {
            email = emailEt.text.toString()
            name = nameEt.text.toString()
            password = passwordEt.text.toString()
            confirmPassword = cPasswordEt.text.toString()

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                Toast.makeText(this, "Email is Required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (name.isEmpty()) {
                Toast.makeText(this, "Name is Required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check email availability before registration
            checkEmailAvailability(EmailCheckRequest(email))
        }
    }

    private fun checkEmailAvailability(emailCheckRequest: EmailCheckRequest) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val API = RetrofitInstance.PostAPI(this@RegisterActivity)
                val response = API.checkEmail(emailCheckRequest)

                if (response.isSuccessful) {
                    // Email is already in use
                    Toast.makeText(this@RegisterActivity, "Email is already in use", Toast.LENGTH_SHORT).show()
                } else {
                    // Proceed with registration
                    val registrationRequest = RegistrationRequest(email, name, password, confirmPassword)
                    registerUser(registrationRequest)
                }
            } catch (e: Exception) {
                // Handle network errors or other exceptions
                Toast.makeText(this@RegisterActivity, "Failed to check email availability: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUser(registrationRequest: RegistrationRequest) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val API = RetrofitInstance.PostAPI(this@RegisterActivity)
                val response = API.register(registrationRequest)

                if (response.isSuccessful) {
                    // Handle successful registration
                    Toast.makeText(this@RegisterActivity, "Registration successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Handle unsuccessful registration
                    val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                    Toast.makeText(this@RegisterActivity, "Registration failed: $errorMessage", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                // Handle other exceptions
                Toast.makeText(this@RegisterActivity, "Registration failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
