package user.application.port.`in`

import User

interface GetUserUseCase {
    fun getOneById(userId: Long): User
}