package com.example.nobelabookreader.view

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import com.example.nobelabookreader.R
import com.example.nobelabookreader.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(), View.OnClickListener, View.OnFocusChangeListener, View.OnKeyListener{

    private lateinit var mBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)
        mBinding.nameEt.onFocusChangeListener = this
        mBinding.emailEt.onFocusChangeListener = this
        mBinding.passwordEt.onFocusChangeListener = this
        mBinding.cPasswordEt.onFocusChangeListener = this
    }

    private fun validateFullName(): Boolean{
        var errorMessage: String? = null
        val  value: String = mBinding.nameEt.text.toString()
        if(value.isEmpty()){
            errorMessage = "Full name is required"
        }

        if(errorMessage != null){
            mBinding.nameTil.apply {
                isErrorEnabled = true
                errorMessage == errorMessage
            }
        }

        return errorMessage == null
    }

    private fun  validateEmail(): Boolean{
        var errorMessage: String? = null
        val value = mBinding.emailEt.text.toString()
        if(value.isEmpty()){
            errorMessage = "Email is Required"
        }else if(Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            errorMessage = "Email address is invalid"
        }

        if(errorMessage != null){
            mBinding.emailTil.apply {
                isErrorEnabled = true
                error  == errorMessage
            }
        }

        return errorMessage == null
    }

    private fun validatePassword(): Boolean{
        var errorMessage: String? = null
        val value = mBinding.passwordEt.text.toString()
        if(value.isEmpty()){
            errorMessage = "Password is Required"
        }else if(value.length < 6){
            errorMessage = "Password must be 6 characters long"
        }

        if(errorMessage != null){
            mBinding.passwordTil.apply {
                isErrorEnabled = true
                error  == errorMessage
            }
        }

        return errorMessage == null
    }

    private fun validateConfirmPassword(): Boolean{
        var errorMessage: String? = null
        val value = mBinding.cPasswordEt.text.toString()
        if(value.isEmpty()){
            errorMessage = "Confirm Password is Required"
        }else if(value.length < 6){
            errorMessage = "Confirm Password muut be 6 characters long"
        }

        if(errorMessage != null){
            mBinding.cPasswordTil.apply {
                isErrorEnabled = true
                error  == errorMessage
            }
        }

        return errorMessage == null
    }

    private fun validatePasswordandConfirmPassword(): Boolean{
        var errorMessage: String?= null
        val password = mBinding.passwordEt.text.toString()
        val confirmPassword = mBinding.cPasswordEt.text.toString()
        if(password != confirmPassword){
            errorMessage = "Confirm password doesnt match with password"
        }

        if(errorMessage != null){
            mBinding.cPasswordTil.apply {
                isErrorEnabled = true
                error  == errorMessage
            }
        }

        return errorMessage == null
    }


    override fun onClick(view: View?) {

    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if(view != null){
            when(view.id){
                R.id.nameEt -> {
                    if(hasFocus){
                        if(mBinding.nameTil.isErrorEnabled){
                            mBinding.nameTil.isErrorEnabled = false
                        }
                    }
                    else {
                        validateFullName()
                    }
                }
                R.id.emailEt -> {
                    if(hasFocus){
                        if(mBinding.emailTil.isErrorEnabled){
                            mBinding.emailTil.isErrorEnabled = false
                        }
                    }
                    else {
                        if(validateEmail()){
                            //do validation for uniqueness
                        }
                    }
                }
                R.id.passwordEt -> {
                    if(hasFocus){
                        if(mBinding.passwordTil.isErrorEnabled){
                            mBinding.passwordTil.isErrorEnabled = false
                        }
                    }
                    else {
                        if(validatePassword() && mBinding.cPasswordEt.text!!.isNotEmpty() && validateConfirmPassword()
                            && validatePasswordandConfirmPassword()){
                            if (mBinding.cPasswordTil.isErrorEnabled){
                                mBinding.cPasswordTil.isErrorEnabled = false
                            }
                            mBinding.cPasswordTil.apply {
                                setStartIconDrawable(R.drawable.ic_check_green)
                                setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                            }
                        }
                    }
                }
                R.id.cPasswordEt -> {
                    if(hasFocus){
                        if(mBinding.cPasswordTil.isCounterEnabled){
                            mBinding.cPasswordTil.isErrorEnabled = false
                        }
                    }
                    else {
                        if(validateConfirmPassword() && validatePassword() && validatePasswordandConfirmPassword()){
                            if (mBinding.passwordTil.isErrorEnabled){
                                mBinding.passwordTil.isErrorEnabled = false
                            }
                            mBinding.cPasswordTil.apply {
                                setStartIconDrawable(R.drawable.ic_check_green)
                                setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onKey(view: View?, event: Int, keyEvent: KeyEvent): Boolean {
        return false
    }
}