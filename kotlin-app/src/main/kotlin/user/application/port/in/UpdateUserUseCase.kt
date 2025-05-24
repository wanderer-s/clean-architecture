package user.application.port.`in`

import user.adapter.`in`.web.dto.UpdateUserPasswordRequest

interface UpdateUserUseCase {
    fun updateNickname(id: Long, newNickname: String)
    fun updatePassword(id: Long, param: UpdateUserPasswordRequest)
}