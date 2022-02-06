package app.global.cli

import java.lang.Exception

/**
 * @author Jack Meng
 */
class CliException : Exception {
    constructor(message: String?) : super(message) {}
    constructor(message: String?, cause: Throwable?) : super(message, cause) {}
    constructor(cause: Throwable?) : super(cause) {}
}