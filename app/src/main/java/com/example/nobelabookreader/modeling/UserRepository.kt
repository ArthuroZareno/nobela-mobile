package com.example.nobelabookreader.data.model.modeling

import com.example.nobelabookreader.dataclass.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun registerUser(userData: User): Flow<Result<User>>

}