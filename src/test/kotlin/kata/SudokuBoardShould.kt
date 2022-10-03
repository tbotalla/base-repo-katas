package kata

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.RuntimeException
import kotlin.math.sqrt

class SudokuBoardShould {
    @Test
    fun `have rows`() {
        val numbers = getListOfNumbers()
        val sudokuBoard = SudokuBoard.newBoard(numbers)

        val rows: List<Row> = sudokuBoard.rows()

        assertTrue(rows.isNotEmpty())
    }

    @Test
    fun `allow to create a board from 16 elements with 4 rows`() {
        val numbers = getListOfNumbers()

        val sudokuBoard = SudokuBoard.newBoard(numbers)

        assertNotNull(sudokuBoard)
        assertEquals(4, sudokuBoard.rows().size)
    }

    @Test
    fun `throw an exception if the square root of the number of elements is not integer`() {
        val numbers = getInvalidListOfNumbers()

        assertThrows(NotSquareBoardException::class.java) {
            SudokuBoard.newBoard(numbers)
        }
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
        val numbers = getListOfNumbers()

        val rows = SudokuBoard.newBoard(numbers).rows()

        assertRows(numbers, rows)

    }

    @Test
    fun `not have repeated numbers in a row`() {
        val numbers = getListOfNumbers()

        val rows = SudokuBoard.newBoard(numbers).rows()

        for(row in rows) {
            assertTrue(row.isValid())
        }
    }

    private fun assertRows(numbers: List<Int>, rows: List<Row>) {
        assertEquals(numbers[0], rows[0].elements[0])
        assertEquals(numbers[1], rows[0].elements[1])
        assertEquals(numbers[2], rows[0].elements[2])
        assertEquals(numbers[3], rows[0].elements[3])

        assertEquals(numbers[4], rows[1].elements[0])
        assertEquals(numbers[5], rows[1].elements[1])
        assertEquals(numbers[6], rows[1].elements[2])
        assertEquals(numbers[7], rows[1].elements[3])

        assertEquals(numbers[8], rows[2].elements[0])
        assertEquals(numbers[9], rows[2].elements[1])
        assertEquals(numbers[10], rows[2].elements[2])
        assertEquals(numbers[11], rows[2].elements[3])

        assertEquals(numbers[12], rows[3].elements[0])
        assertEquals(numbers[13], rows[3].elements[1])
        assertEquals(numbers[14], rows[3].elements[2])
        assertEquals(numbers[15], rows[3].elements[3])
    }

    private fun getListOfNumbers() = listOf(1, 2, 3, 4, 2, 3,4 ,1, 3, 4, 1, 2, 4, 1, 2, 3)
    private fun getInvalidListOfNumbers() = listOf(1, 2, 3, 4, 2, 3, 4 ,1, 3, 4, 1, 2, 4, 1, 2, 3, 1)

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
    fun isValid():  Boolean {
        return elements.size == elements.distinct().size
    }
}

class RowShould {
    @Test
    fun `return true if there are not repeated numbers`() {
        val row = Row(listOf(1,2,3))
        assertTrue(row.isValid())
    }

    @Test
    fun `return false if there are repeated numbers`() {
        val row = Row(listOf(1,2,2))
        assertFalse(row.isValid())
    }
}