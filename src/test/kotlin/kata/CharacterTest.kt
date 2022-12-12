package kata

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CharacterTest {

    @Test
    fun `a Character must be created with 1000 health, level 1 and alive`() {
        val aCharacter = Character()

        assertEquals(Health.getMaxHealth(), aCharacter.health)
        assertEquals(1, aCharacter.level)
        assertTrue(aCharacter.isAlive())
    }

    @Test
    fun `a Character can damage another one`() {
        val aCharacter = Character()
        val anotherCharacter = Character()
        val damage = 50
        val expectedHealth = Health(aCharacter.health.value - damage)

        aCharacter.damage(anotherCharacter, damage)

        assertEquals(expectedHealth, anotherCharacter.health)
    }

    @Test
    fun `a Character can damage another one twice`() {
        val aCharacter = Character()
        val anotherCharacter = Character()
        val firstDamage = 50
        val secondDamage = 100
        val expectedHealth = Health(aCharacter.health.value - firstDamage - secondDamage)

        aCharacter.damage(anotherCharacter, firstDamage)
        aCharacter.damage(anotherCharacter, secondDamage)

        assertEquals(expectedHealth, anotherCharacter.health)
    }

    @Test
    fun `a Character can be healed when is alive`() {
        val aCharacter = Character()
        val damagedCharacter = Character()
        val healAmount = 50
        val damage = 100
        val expectedHealth = Health(damagedCharacter.health.value - damage + healAmount)

        aCharacter.damage(damagedCharacter, damage)
        aCharacter.heal(damagedCharacter, healAmount)

        assertTrue(damagedCharacter.isAlive())
        assertEquals(expectedHealth, damagedCharacter.health)
    }

    @Test
    fun `a Character cannot be healed when is dead`() {
        val aCharacter = Character()
        val damagedCharacter = Character()
        val healAmount = 50
        val damage = 1050
        val expectedHealth = Health(Health.DEAD_HEALTH)

        aCharacter.damage(damagedCharacter, damage)
        aCharacter.heal(damagedCharacter, healAmount)

        assertFalse(damagedCharacter.isAlive())
        assertEquals(expectedHealth, damagedCharacter.health)
    }

    @Test
    fun `a Character cannot heal another one when at full health`() {
        val aCharacter = Character()
        val healedCharacter = Character()
        val healAmount = 50
        val expectedHealth = Health(Health.MAX_HEALTH)

        aCharacter.heal(healedCharacter, healAmount)

        assertEquals(expectedHealth, healedCharacter.health)
    }
}