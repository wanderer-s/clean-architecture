import com.password4j.Password
import java.time.LocalDateTime


class User(
    val email: String,
    var nickName: String,
    plainPassword: String,
    val id: Long = 0L,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {
    private var password = Password
        .hash(plainPassword)
        .withArgon2()
        .result
    set(value) {
        field = Password
            .hash(value)
            .withArgon2()
            .result
    }

    fun updatePassword(currPassword: String, newPassword: String) {
        if (!Password.check(currPassword, password).withArgon2()) {
            throw IllegalArgumentException("비밀번호가 일치하지 않습니다")
        }

        if(currPassword == newPassword) {
            throw IllegalArgumentException("전과 같은 비밀번호입니다")
        }

        password = newPassword
        updatedAt = LocalDateTime.now()
    }

    fun updateNickname(newNickname: String) {
        if(nickName == newNickname) {
            throw IllegalArgumentException("전과 같은 닉네임입니다")
        }

        this.nickName = newNickname
        updatedAt = LocalDateTime.now()
    }
}