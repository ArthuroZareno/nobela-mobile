package com.example.nobelabookreader.data.model.modeling

import com.example.nobelabookreader.dataclass.EmailCheckRequest
import com.example.nobelabookreader.dataclass.LoginRequest
import com.example.nobelabookreader.dataclass.LoginResponse
import com.example.nobelabookreader.dataclass.RegistrationRequest
import com.example.nobelabookreader.dataclass.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface API {

    @Headers("Accept: application/json") // Specifies the Accept header
    @POST("/register") // Adjust this endpoint to match your API.
    suspend fun register(@Body registrationRequest: RegistrationRequest): Response<UserResponse>

    @Headers("Accept: application/json")
    @POST("/login")
    suspend fun login(@Body credentials: LoginRequest): Response<LoginResponse>

    @Headers("Accept: application/json")
    @POST("/register")
    suspend fun checkEmail(@Query("email") emailCheckRequest: EmailCheckRequest): Response<UserResponse>
}

