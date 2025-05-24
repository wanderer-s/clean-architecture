package user.application

import User
import global.exception.NotFoundException
import user.application.port.`in`.GetUserUseCase
import user.application.port.out.LoadUserPort

class GetUserService(
    private val loadUserPort: LoadUserPort
): GetUserUseCase {
    override fun getOneById(userId: Long): User {
        val user = loadUserPort.findOneById(userId) ?: throw NotFoundException("사용자 정보를 찾을 수 없습니다")
        return user
    }
}