package user.adapter.out.persistence

import User
import user.application.port.out.LoadUserPort
import user.application.port.out.RegisterUserPort

class UserPersistenceAdapter: LoadUserPort, RegisterUserPort {
    private val users = mutableMapOf<Long, User>()
    private var nextId = 1L

    override fun findOneById(id: Long): User? {
        return users[id]
    }

    override fun findOneByEmail(email: String): User? {
        return users.values.find { it.email == email }
    }

    override fun save(user: User) {
        if (user.id == 0L) {
            user.updateId(nextId++)
        }
        users[user.id] = user
    }
}
