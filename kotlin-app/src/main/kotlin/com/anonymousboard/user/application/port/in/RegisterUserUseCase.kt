package com.anonymousboard.user.application.port.`in`

import com.anonymousboard.user.adapter.`in`.web.dto.RegisterUserRequest

interface RegisterUserUseCase {
    fun isRegisteredEmail(email: String): Boolean
    fun register(param: RegisterUserRequest)
}