package user

import User
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class UserTest: DescribeSpec({
    describe("updatePassword") {
        val user = User("test@test.io", "test", "password")

        it("비밀번호 변경 성공") {
            user.password shouldBe "password"
            user.updatePassword("newPassword")
            user.password shouldBe "newPassword"
        }
    }

    describe("updateNickname") {
        val user = User( "test@test.io", "test", "password")

        it("입력된 값이 현재 닉네임과 같으면 Exception 발생") {
            shouldThrow<IllegalArgumentException> {
                user.updateNickname("test")
            }
        }

        it("닉네임 변경 성공") {
            user.updateNickname("newNickname")
            user.nickname shouldNotBe "test"
            user.nickname shouldBe "newNickname"
        }
    }

})