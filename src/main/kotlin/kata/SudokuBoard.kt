package kata

import java.lang.Math.sqrt

class SudokuBoard private constructor(numbers: List<Int>) {

    private var rows: MutableList<Row> = mutableListOf()
    private var columns : MutableList<Column> = mutableListOf()
    private var n = 0

    init {
        validateNumbersNotEmpty(numbers)
        val squareRootOfNumbers = sqrt(numbers.size.toDouble())
        validateBoardSquare(squareRootOfNumbers)
        n = squareRootOfNumbers.toInt()

        addRows(numbers)
        addColumns(numbers, n)
    }

    private fun addColumns(numbers: List<Int>, n: Int) {
        for (i in 0 until n step 1) {
            val columnElements = mutableListOf<Int>()
            for (j in 0 until n*n step n) {
                columnElements.add(numbers[i+j])
            }
            columns.add(Column(columnElements))
        }
    }

    private fun validateNumbersNotEmpty(numbers: List<Int>) {
        if (numbers.isEmpty()) {
            throw CannotBuildABoardWithNoElementsException()
        }
    }

    private fun addRows(numbers: List<Int>) {
        val rowsElements = numbers.chunked(n)
        for (row in rowsElements) {
            rows.add(Row(row))
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

    fun columns(): List<Column> {
        return columns
    }

    companion object {
        fun newBoard(numbers: List<Int>): SudokuBoard {
            return SudokuBoard(numbers)
        }
    }

}