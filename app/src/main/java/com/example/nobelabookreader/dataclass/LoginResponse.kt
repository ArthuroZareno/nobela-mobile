package com.example.nobelabookreader.dataclass

import android.media.session.MediaSession

data class LoginResponse(val user: User, val token: MediaSession.Token)

data class LoginRequest(val email: String, val password: String)

data class RegistrationRequest(
    val email: String,
    val name: String,
    val password: String,
    val password_confirmation: String,

)

data class UserResponse(val User: User,  val token: MediaSession.Token)

data class EmailCheckRequest(
    val email: String
)