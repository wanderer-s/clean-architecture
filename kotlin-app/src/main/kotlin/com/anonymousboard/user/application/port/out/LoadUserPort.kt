package com.anonymousboard.user.application.port.out

import User

interface LoadUserPort {
    fun findOneById(id: Long): User?
    fun findOneByEmail(email: String): User?
}