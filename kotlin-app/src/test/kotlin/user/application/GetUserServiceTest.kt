package user.application

import User
import global.exception.NotFoundException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import user.adapter.out.persistence.UserPersistenceAdapter

class GetUserServiceTest: DescribeSpec({
    val userPersistenceAdapter = UserPersistenceAdapter()
    val getUserService = GetUserService(userPersistenceAdapter)

    val user = User("test@test.io", "test"," password")
    userPersistenceAdapter.save(user)

    describe("getOneById - id로 User 조회") {
        it("id로 user를 조회 할 수 없는 경우 예외 처리") {
            shouldThrow<NotFoundException> {
                getUserService.getOneById(10)
            }
        }

        it("id로 user 조회") {
            val foundUser = getUserService.getOneById(1)

            foundUser shouldBe user
        }
    }
})