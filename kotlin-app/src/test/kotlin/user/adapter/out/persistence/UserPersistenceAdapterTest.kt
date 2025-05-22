package user.adapter.out.persistence

import User
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class UserPersistenceAdapterTest: DescribeSpec({
    val userPersistenceAdapter = UserPersistenceAdapter()

    describe("save and find user") {
        it("save 하면 user 가 저장된다") {
            val user = User("test@test.io", "test", "password")
            userPersistenceAdapter.save(user)
        }

        describe("findOneById") {
            it("없는 id로 사용자 정보를 조회하면 null을 반환한다") {
                val user = userPersistenceAdapter.findOneById(5L)
                user shouldBe null
            }

            it("id로 사용자 정보를 조회할 수 있다") {
                val user = userPersistenceAdapter.findOneById(1L)
                user?.email shouldBe "test@test.io"
                user?.nickname shouldBe "test"
            }
        }

        describe("findOneByEmail") {
            it("email로 사용자 정보를 조회할 수 있다") {
                val user = userPersistenceAdapter.findOneByEmail("test@test.io")
                user?.email shouldBe "test@test.io"
                user?.nickname shouldBe "test"
            }

            it("없는 email로 사용자 정보를 조회하면 null을 반환한다") {
                val user = userPersistenceAdapter.findOneByEmail("non-exist-mail@test.io")
                user shouldBe null
            }
        }
    }
})
