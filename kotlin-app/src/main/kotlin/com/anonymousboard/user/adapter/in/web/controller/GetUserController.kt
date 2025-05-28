package com.anonymousboard.com.anonymousboard.user.adapter.`in`.web.controller

import com.anonymousboard.com.anonymousboard.user.adapter.`in`.web.dto.UserResponse
import com.anonymousboard.user.application.GetUserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class GetUserController(
    private val getUserService: GetUserService
) {

    @GetMapping("{userId}")
    fun getUser(@PathVariable("userId") userId: Long): UserResponse {
        if(userId.toInt() == 0) {
            return UserResponse(0, "test@test.io", "test")
        }
        val user = this.getUserService.getOneById(userId)
        return UserResponse.of(user)

    }
}