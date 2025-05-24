package user.application

import User
import io.kotest.assertions.throwables.shouldThrow

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import user.adapter.`in`.web.dto.RegisterUserRequest
import user.adapter.out.persistence.UserPersistenceAdapter

class RegisterUserServiceTest: DescribeSpec({
    val userPersistenceAdapter = UserPersistenceAdapter()
    val registerUserService = RegisterUserService(userPersistenceAdapter, userPersistenceAdapter)

    val user = User("test@test.io", "test", "password")
    userPersistenceAdapter.save(user)

    describe("isRegisteredEmail - email로 가입된 내역 확인") {
        it("입력된 email로 가입된 사용자가 없으면 false") {
            val result = registerUserService.isRegisteredEmail("none@test.io")
            result shouldBe false
        }
        it("입력된 email로 가입된 사용자가 있으면 true") {
            val result = registerUserService.isRegisteredEmail("test@test.io")
            result shouldBe true
        }
    }
    describe("register - 회원 가입") {
        it("이미 가입된 email 입력 시 예외 처리") {
            shouldThrow<IllegalArgumentException> {
                val userRequest = RegisterUserRequest("test@test.io", "test", "password")
                registerUserService.register(userRequest)
            }
        }
    }
})