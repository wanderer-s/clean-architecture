package com.anonymousboard.user.application

import User
import com.anonymousboard.global.exception.NotFoundException
import com.anonymousboard.user.application.port.`in`.GetUserUseCase
import com.anonymousboard.user.application.port.out.LoadUserPort

class GetUserService(
    private val loadUserPort: LoadUserPort
): GetUserUseCase {
    override fun getOneById(userId: Long): User {
        val user = loadUserPort.findOneById(userId) ?: throw NotFoundException("사용자 정보를 찾을 수 없습니다")
        return user
    }
}