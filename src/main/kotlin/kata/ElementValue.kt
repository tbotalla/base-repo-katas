package kata

class ElementValue(val value: Int) {

    companion object {
        const val ZERO_VALUE: Int = 0

        fun newZeroValue() = ElementValue(ZERO_VALUE)
    }
}
