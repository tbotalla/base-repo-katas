package kata.domain

import kotlin.math.sqrt


class SudokuBoard private constructor(numbers: List<Int>) {

    private var rows: MutableList<Row> = mutableListOf()
    private var n = 0

    init {
        validateNumbersNotEmpty(numbers)
        val squareRootOfNumbers = sqrt(numbers.size.toDouble())
        n = squareRootOfNumbers.toInt()
        validateBoardSquare(squareRootOfNumbers)
        validateNumbers(numbers)
        addRows(numbers)
    }

    private fun validateNumbers(numbers: List<Int>) {
        if (numbers.firstOrNull { it > n } != null)
            throw CannotBuildABoardWithAnInvalidElementValue()
    }

    private fun validateNumbersNotEmpty(numbers: List<Int>) {
        if (numbers.isEmpty()) {
            throw CannotBuildABoardWithNoElementsException()
        }
    }

    private fun addRows(numbers: List<Int>) {
        val rowsElements = numbers.chunked(n)
        for (rowElements in rowsElements) {
            rows.add(Row(rowElements))
        }
    }

    private fun validateBoardSquare(squareRootOfNumbersSize: Double) {
        if ((squareRootOfNumbersSize % 1) != 0.toDouble()) {
            throw NotSquareBoardException()
        }
    }

    fun rows(): List<Row> {
        return rows
    }

    companion object {
        fun newBoard(numbers: List<Int>): SudokuBoard {
            return SudokuBoard(numbers)
        }
    }

}