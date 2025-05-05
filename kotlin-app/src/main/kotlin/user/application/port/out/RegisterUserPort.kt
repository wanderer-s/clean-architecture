package user.application.port.out

import User

interface RegisterUserPort {
    fun save(user: User)
}