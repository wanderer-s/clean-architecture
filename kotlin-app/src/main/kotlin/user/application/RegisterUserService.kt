package user.application

import User
import user.adapter.`in`.web.RegisterUserRequest
import user.application.port.`in`.RegisterUserUseCase
import user.application.port.out.LoadUserPort
import com.password4j.Password
import user.application.port.out.SaveUserPort

class RegisterUserService(
    private val loadUserPort: LoadUserPort,
    private val saveUserPort: SaveUserPort,
    ): RegisterUserUseCase {
    override fun isRegisteredEmail(email: String): Boolean {
        val user = loadUserPort.findOneByEmail(email)
        return user != null
    }

    override fun register(param: RegisterUserRequest) {
        require(!isRegisteredEmail(param.email)) {
            "이미 가입된 메일주소 입니다"
        }
        val hashedPassword = Password.hash(param.plainPassword).withArgon2().result

        val user = User(param.email, param.nickname, hashedPassword)
        saveUserPort.save(user)
    }
}