package com.anonymousboard.com.anonymousboard.user.adapter.`in`.web.dto

import User

data class UserResponse(
    val id: Long,
    val email: String,
    val nickname: String
) {
    companion object {
        fun of(user: User): UserResponse {
            with(user) {
                return UserResponse(
                    id,
                    email,
                    nickname
                )
            }
        }
    }
}