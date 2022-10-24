package kata

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

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

    @Test
    fun `not have repeated numbers in a column`() {
        val numbers = getListOfNumbers()

        val columns = SudokuBoard.newBoard(numbers).columns()

        for(column in columns) {
            assertTrue(column.isValid())
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

    private fun getListOfNumbers() = listOf(1, 2, 3, 4, 2, 3, 4 ,1, 3, 4, 1, 2, 4, 1, 2, 3)
    private fun getInvalidListOfNumbers() = listOf(1, 2, 3, 4, 2, 3, 4 ,1, 3, 4, 1, 2, 4, 1, 2, 3, 1)

}

