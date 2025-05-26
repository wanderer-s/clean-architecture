package com.anonymousboard.user.application.port.`in`

import User

interface GetUserUseCase {
    fun getOneById(userId: Long): User
}