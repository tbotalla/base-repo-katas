package kata

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

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