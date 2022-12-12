package kata

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class HealthTest {

    @Test
    fun `a Health cannot be negative`() {
        val aHealth = Health(Health.DEAD_HEALTH - 10)

        assertEquals(Health.DEAD_HEALTH, aHealth.value)
    }

    @Test
    fun `a Health cannot exceed max health`() {
        val aHealth = Health(Health.MAX_HEALTH + 10)

        assertEquals(Health.MAX_HEALTH, aHealth.value)
    }

    @Test
    fun `should create health with max health`() {
        val expectedHealth = Health(Health.MAX_HEALTH)
        val maxHealth = Health.getMaxHealth()

        assertEquals(expectedHealth, maxHealth)
    }

    @Test
    fun `should be alive if health is above 0`() {
        val aHealth = Health(1)
        assertTrue(aHealth.isAlive())
    }

    @Test
    fun `should be dead if health is 0`() {
        val aHealth = Health(0)
        assertFalse(aHealth.isAlive())
    }
}