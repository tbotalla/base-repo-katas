package kata

class Row(val elements: List<Int>) {
    fun isValid():  Boolean {
        return elements.size == elements.distinct().size
    }
}