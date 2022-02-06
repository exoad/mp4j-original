package app.functions

import java.lang.Runnable
import java.util.ArrayList
import kotlin.jvm.Synchronized

/**
 * @author Jack Meng
 */
class Worker {
    private var worker: Thread? = null
    private val args = ArrayList<Runnable>()
    @Synchronized
    fun threadedWorker(r: Runnable?) {
        worker = Thread(r)
        worker!!.start()
    }

    @Synchronized
    fun killWorker() {
        worker!!.interrupt()
    }

    @Synchronized
    fun groupedWorker(vararg r: Runnable) {
        for (r1 in r) {
            worker = Thread(r1)
            worker!!.start()
            args.add(r1)
        }
    }

    @Synchronized
    fun killSpecWorker(index: Int) {
        (args[index] as Thread).interrupt()
    }
}