package user.application.port.out

import User

interface SaveUserPort {
    fun save(user: User)
}