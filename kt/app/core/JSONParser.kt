package app.core

/**
 * @author Jack Meng
 */
object JSONParser {
    /**
     * @param element
     * @param JSON
     * @return String
     */
    @JvmStatic
    fun parseElement(element: String?, JSON: String): String? {
        val elements = JSON.split(",".toRegex()).toTypedArray()
        for (e in elements) {
            if (e.contains(element!!)) {
                val elementValue = e.split(":".toRegex()).toTypedArray()
                if (elementValue.size == 2) {
                    return elementValue[1].replace("\"", "").replace("}", "")
                }
            }
        }
        return null
    }
}