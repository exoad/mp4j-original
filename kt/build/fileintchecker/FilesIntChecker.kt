package build.fileintchecker

import kotlin.Throws
import java.io.IOException
import kotlin.jvm.JvmStatic
import java.lang.StringBuilder
import build.fileintchecker.global.Exclude
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.*

object FilesIntChecker {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val sc = Scanner(File("src/build/fileintchecker/.loader"))
        val loadedPath = sc.nextLine()
        sc.close()
        val mainPath = File(loadedPath)
        val files = mainPath.listFiles()
        val filesSeq = StringBuilder()
        for (f in files) {
            if (f.path.contains("deprecated") || f.isDirectory || filesSeq.toString().contains(f.name)) {
                continue
            }
            filesSeq.append("""
    ${f.name}
    
    """.trimIndent())
            println(filesSeq.toString())
        }
        for (secondPaths in Exclude.ndPaths) {
            val secondPath = File(loadedPath + secondPaths)
            println(secondPath.name)
            if (secondPath.isDirectory) {
                val secondFiles = secondPath.listFiles()
                for (f in secondFiles) {
                    if (f.isDirectory || filesSeq.toString().contains(f.name)) {
                        continue
                    }
                    filesSeq.append("""
    $secondPaths${f.name}
    
    """.trimIndent())
                    println(secondPaths + filesSeq.toString())
                }
            }
        }
        filesSeq.deleteCharAt(filesSeq.length - 1)
        val f = File("$loadedPath/files.txt")
        f.createNewFile()
        BufferedWriter(FileWriter(f)).use { bw ->
            bw.write(filesSeq.toString())
            bw.flush()
            bw.close()
        }
    }
}