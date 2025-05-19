package user.application.port.`in`

interface RegisterUserUseCase {
    fun isRegisteredEmail(): Boolean
    fun register()
}