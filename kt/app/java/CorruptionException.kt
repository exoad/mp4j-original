package app.java

import app.java.CorruptionException

class CorruptionException : Throwable {
    constructor(message: String) : super(message) {
        message.javaClass
    }

    constructor(message: String?, cause: Throwable) : super(message, cause) {
        cause.javaClass
    }

    override fun printStackTrace() {
        println("CorruptionException: " + CorruptionException("").message)
    }
}