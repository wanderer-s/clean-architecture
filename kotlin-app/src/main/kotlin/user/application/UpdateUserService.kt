package user.application

import User
import com.password4j.Password
import global.exception.ForbiddenException
import global.exception.NotFoundException
import user.adapter.`in`.web.UpdateUserPasswordRequest
import user.application.port.`in`.UpdateUserUseCase
import user.application.port.out.LoadUserPort
import user.application.port.out.SaveUserPort

class UpdateUserService(
    private val loadUserPort: LoadUserPort,
    private val saveUserPort: SaveUserPort,
): UpdateUserUseCase {
    private fun getUserById(id: Long): User {
        val user = this.loadUserPort.findOneById(id)
        require(user != null) {
            throw NotFoundException("사용자 정보를 찾을 수 없습니다")
        }
        return user
    }

    override fun updateNickname(id: Long, newNickname: String) {
        val user = this.getUserById(id)
        user.updateNickname(newNickname)

        this.saveUserPort.save(user)
    }

    override fun updatePassword(id: Long, param: UpdateUserPasswordRequest) {
        val user = this.getUserById(id)

        if(!Password.check(param.previousPassword, user.password).withArgon2()) {
            throw ForbiddenException()
        }

        if (Password.check(param.newPassword, user.password).withArgon2()) {
            throw IllegalArgumentException("이전 비밀번호를 사용할 수 없습니다")
        }

        val hashedPassword = Password.hash(param.newPassword).withArgon2().result
        user.updatePassword(hashedPassword)

        this.saveUserPort.save(user)
    }
}