package kata


class Column(private val elements: List<Int>) {
    fun isValid(): Boolean {
        return elements.size == elements.distinct().size
    }

}
