
import java.time.LocalDateTime

class User(
    val email: String,
    nickname: String,
    password: String,
    id: Long = 0L,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {
    var id: Long = id
        private set

    var nickname = nickname
        private set

    var password = password
        private set

    fun updateId(id: Long) {
        if(this.id == 0L) {
            this.id = id
        }
    }

    fun updatePassword(hashedPassword: String) {
        this.password = hashedPassword
        this.updatedAt = LocalDateTime.now()
    }

    fun updateNickname(newNickname: String) {
        require(nickname != newNickname) {
            "전과 같은 닉네임입니다"
        }
        nickname = newNickname
        this.updatedAt = LocalDateTime.now()

    }
}