package user.application.port.`in`

import user.adapter.`in`.web.dto.RegisterUserRequest

interface RegisterUserUseCase {
    fun isRegisteredEmail(email: String): Boolean
    fun register(param: RegisterUserRequest)
}