package kata

import kata.domain.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SudokuBoardShould {
    @Test
    fun `have rows`() {
        val numbers = listOf(1, 2, 1, 2)
        val sudokuBoard = SudokuBoard.newBoard(numbers)

        val rows: List<Row> = sudokuBoard.rows()

        assertTrue(rows.isNotEmpty())
    }

    @Test
    fun `allow to create a board from 4 elements with 2 rows`() {
        val numbers = listOf(1, 2, 1, 2)

        val sudokuBoard = SudokuBoard.newBoard(numbers)

        assertNotNull(sudokuBoard)
        assertEquals(2, sudokuBoard.rows().size)
    }

    @Test
    fun `throw an exception if the square root of the number of elements is not integer`() {
        val numbers = listOf(1, 2, 3, 1, 2)

        assertThrows(NotSquareBoardException::class.java) {
            SudokuBoard.newBoard(numbers)
        }
    }

    @Test
    fun `allow to create a board from 9 elements with 3 rows`() {
        val numbers = listOf(1, 2, 3, 1, 2, 3, 1, 2, 3)

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
        val numbers = listOf(1, 2, 1, 2)

        val rows = SudokuBoard.newBoard(numbers).rows()

        assertEquals(numbers[0], rows[0].elements[0])
        assertEquals(numbers[1], rows[0].elements[1])
        assertEquals(numbers[2], rows[1].elements[0])
        assertEquals(numbers[3], rows[1].elements[1])
    }

    @Test
    fun `not allow to create a board when an element greater than number of rows`() {
        val numbers = listOf(1, 2, 3, 4)

        assertThrows(CannotBuildABoardWithAnInvalidElementValue::class.java) {
            SudokuBoard.newBoard(numbers)
        }
    }

    @Test
    fun `not allow to create a board when an repeated element per row`() {
        val numbers = listOf(1, 1, 1, 2)

        assertThrows(CannotBuildABoardWithRepeatedElementInARow::class.java) {
            SudokuBoard.newBoard(numbers)
        }
    }
}
