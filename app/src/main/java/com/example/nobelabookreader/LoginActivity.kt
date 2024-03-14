package com.example.nobelabookreader

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.nobelabookreader.dataclass.LoginRequest
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText
    private lateinit var loginBtn: Button
    private lateinit var noAccountTv: TextView

    private lateinit var email: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEt = findViewById(R.id.emailEt)
        passwordEt= findViewById(R.id.passwordEt)
        loginBtn= findViewById(R.id.loginBtn)
        noAccountTv = findViewById(R.id.noAccountTv)

        // Set click listener for 'Forgot Password' TextView

        // Set click listener for 'Create Account' TextView
        noAccountTv.setOnClickListener {
            // Redirect to register activity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Set click listener for Login Button
        loginBtn.setOnClickListener {
            val enteredEmail = emailEt.text.toString().trim()
            val enteredPassword = passwordEt.text.toString().trim()

            // Check for empty fields
            if (enteredEmail.isEmpty() || enteredPassword.isEmpty()) {
                Toast.makeText(this, "Email or Password is empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                // Assign values to email and password properties
                email = enteredEmail
                password = enteredPassword
                // Perform login operation
                loginUser()
            }
        }
    }

    private fun loginUser() {
        val API = RetrofitInstance.getAPI(this@LoginActivity)
        val loginRequest = LoginRequest(email, password)

        lifecycleScope.launch {
            try {
                val response = API.login(loginRequest)
                if (response.isSuccessful && response.body() != null) {
                    val userResponse = response.body()!!
                    // Handle successful login here, e.g., navigate to another activity
                    Log.d("LoginActivity", "Login success: ${userResponse.user}")
                    runOnUiThread {
                        Toast.makeText(this@LoginActivity, "Successfully logged in", Toast.LENGTH_SHORT).show()
                    }
                    // Navigate to MainActivity
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    // Optional: Add flags to clear task and start new if needed
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish() // Optional: if you don't want users to return to the login screen when pressing back

                } else {
                    // Handle login failure, e.g., show error message
                    Log.e("LoginActivity", "Login failed: ${response.errorBody()?.string()}")
                    runOnUiThread {
                        Toast.makeText(this@LoginActivity, "ERROR", Toast.LENGTH_SHORT).show()
                    }

                }
            } catch (e: Exception) {
                Log.e("LoginActivity", "Error fetching user", e)
            }

        }
    }
}
