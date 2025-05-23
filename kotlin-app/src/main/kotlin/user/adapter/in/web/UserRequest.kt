package user.adapter.`in`.web

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class RegisterUserRequest(
    @field:NotBlank
    @field:Email
    val email: String,

    @field:NotBlank
    val nickname: String,

    @field:NotBlank
    @field:Size(min = 6)
    val plainPassword: String
)

data class UpdateUserNicknameRequest(
    @field:NotBlank
    val nickname: String,
)

data class UpdateUserPasswordRequest(
    @field:NotBlank
    val previousPassword: String,

    @field:NotBlank
    val newPassword: String
)