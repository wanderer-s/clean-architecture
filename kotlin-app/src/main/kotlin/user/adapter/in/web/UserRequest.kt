package user.adapter.`in`.web

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class RegisterUserRequest(
    @field:NotBlank
    @field:Email
    val email: String,

    @field:NotBlank
    val nickName: String,

    @field:Size(max = 6)
    val plainPassword: String
)
