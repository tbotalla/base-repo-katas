package kata

import kata.domain.CannotBuildABoardWithAnInvalidElementValue
import kata.domain.SudokuBoard
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class ElementValueShould {

    @Test
    fun `have a value`() {
        val expectedValue = 3

        val elementValue = ElementValue(3)

        assertEquals(expectedValue, elementValue.value)
    }

    @Test
    fun `have a zero value`() {
        val elementValue = ElementValue.newZeroValue()

        assertEquals(ElementValue.ZERO_VALUE, elementValue.value)
    }

    @Test
    fun `not allow to create a element value with value `() {
        assertThrows(::class.java) {
            ElementValue(-1)
        }
    }
}
