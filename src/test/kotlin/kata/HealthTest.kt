package kata

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class HealthTest {

    @Test
    fun `a Health cannot be negative`() {
        assertThrows(HealthCannotBeNegative::class.java, { Health(-100) }, "")
    }

    @Test
    fun `a Health cannot exceed max health`() {
        val aHealth = Health(Health.MAX_HEALTH + 10)

        assertEquals(Health.MAX_HEALTH, aHealth.value)
    }
}