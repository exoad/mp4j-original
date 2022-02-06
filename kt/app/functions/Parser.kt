package app.functions

import java.lang.Runnable
import java.util.function.Consumer
import kotlin.jvm.Synchronized

/**
 * @author Jack Meng
 */
object Parser {
    @JvmStatic
    fun <T> foreach(arr: Array<T>, consumer: Consumer<T>) {
        for (t in arr) {
            consumer.accept(t)
        }
    }
}