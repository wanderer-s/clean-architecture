package user.application

import User
import com.password4j.Password
import global.exception.ForbiddenException
import global.exception.NotFoundException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import user.adapter.`in`.web.dto.UpdateUserPasswordRequest
import user.adapter.out.persistence.UserPersistenceAdapter

class UpdateUserServiceTest: DescribeSpec({
    val userPersistenceAdapter = UserPersistenceAdapter()
    val updateUserService = UpdateUserService(userPersistenceAdapter, userPersistenceAdapter)

    val hashed = Password.hash("password").withArgon2().result
    val user = User("test@test.io", "test", hashed)
    userPersistenceAdapter.save(user)

    describe("updateNickname - nickname 수정") {
        it("nickname을 update 하려는 사용자를 찾을 수 없는 경우 예외 처리") {
            shouldThrow<NotFoundException> {
                updateUserService.updateNickname(2, "test")
            }
        }
        it("nickname이 이 전과 같은 경우 예외 처리") {
            shouldThrow<IllegalArgumentException> {
                updateUserService.updateNickname(1, "test")
            }
        }
        it("update nickname 과정중 예외처리 없이 완료가 되면 입력된 값으로 변경됨") {
            user.nickname shouldBe "test"
            updateUserService.updateNickname(1, "newNickname")
            user.nickname shouldBe "newNickname"
        }
    }

    describe("updatePassword - 비밀번호 수정") {
        it("password를 update 하려는 사용자를 찾을 수 없는 경우 예외 처리") {
            shouldThrow<NotFoundException> {
                val param = UpdateUserPasswordRequest("password", "newPassword")
                updateUserService.updatePassword(2, param)
            }
        }

        it("이전 password를 확인하기 위해 입력된 값이 이전 password 와 일치하지 않을 때 예외 처리") {
            shouldThrow<ForbiddenException> {
                val param = UpdateUserPasswordRequest("wrongPassword", "newPassword")
                updateUserService.updatePassword(1, param)
            }
        }

        it("바꾸려는 password 값이 이전 password와 같은 경우 예외 처리") {
            shouldThrow<IllegalArgumentException> {
                val param = UpdateUserPasswordRequest("password", "password")
                updateUserService.updatePassword(1, param)
            }
        }

        it("update password 과정중 예외처리 없이 완료가 되면 password 변경") {
            Password.check("password", user.password).withArgon2() shouldBe true

            val param = UpdateUserPasswordRequest("password", "newPassword")
            updateUserService.updatePassword(1, param)

            Password.check("newPassword", user.password).withArgon2() shouldBe true
        }
    }
})