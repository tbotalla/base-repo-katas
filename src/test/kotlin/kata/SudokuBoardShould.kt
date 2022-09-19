package kata

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.RuntimeException
import kotlin.math.sqrt

class SudokuBoardShould {
    @Test
    fun `have rows`() {
        val numbers = listOf(1, 2, 3, 4)
        val sudokuBoard = SudokuBoard.newBoard(numbers)

        val rows: List<Row> = sudokuBoard.rows()

        assertTrue(rows.isNotEmpty())
    }

    @Test
    fun `allow to create a board from 4 elements with 2 rows`() {
        val numbers = listOf(1, 2, 3, 4)

        val sudokuBoard = SudokuBoard.newBoard(numbers)

        assertNotNull(sudokuBoard)
        assertEquals(2, sudokuBoard.rows().size)
    }

    @Test
    fun `throw an exception if the square root of the number of elements is not integer`() {
        val numbers = listOf(1, 2, 3, 4, 5)

        assertThrows(NotSquareBoardException::class.java) {
            SudokuBoard.newBoard(numbers)
        }
    }

    @Test
    fun `allow to create a board from 9 elements with 3 rows`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

        val sudokuBoard = SudokuBoard.newBoard(numbers)

        assertNotNull(sudokuBoard)
        assertEquals(3, sudokuBoard.rows().size)
    }

    @Test
    fun `throw an exception if a board is constructed from an empty list`() {
        val emptyList = listOf<Int>()

        assertThrows(CannotBuildABoardWithNoElementsException::class.java) {
            SudokuBoard.newBoard(emptyList)
        }
    }

    @Test
    fun `have the first two elements in the first row, and the next two elements in the second row when a board with 4 elements is created`() {
        val numbers = listOf(1, 2, 3, 4)

        val rows = SudokuBoard.newBoard(numbers).rows()

        assertEquals(numbers[0], rows[0].elements[0])
        assertEquals(numbers[1], rows[0].elements[1])
        assertEquals(numbers[2], rows[1].elements[0])
        assertEquals(numbers[3], rows[1].elements[1])
    }
}

class NotSquareBoardException : RuntimeException() {

}


class SudokuBoard private constructor(numbers: List<Int>) {

    private var rows: MutableList<Row> = mutableListOf()
    private var n = 0

    init {
        validateNumbersNotEmpty(numbers)
        val squareRootOfNumbers = sqrt(numbers.size.toDouble())
        validateBoardSquare(squareRootOfNumbers)
        n = squareRootOfNumbers.toInt()

        addRows(numbers)
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

    companion object {
        fun newBoard(numbers: List<Int>): SudokuBoard {
            return SudokuBoard(numbers)
        }
    }

}

class CannotBuildABoardWithNoElementsException : Throwable() {

}

class Row(val elements: List<Int>) {

}
