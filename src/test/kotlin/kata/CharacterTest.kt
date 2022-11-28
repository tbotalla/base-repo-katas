package kata

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CharacterTest {

    @Test
    fun `a Character must be created with 1000 health, level 1 and alive`() {
        val aCharacter = Character()

        assertEquals(Character.MAX_HEALTH, aCharacter.health)
        assertEquals(1, aCharacter.level)
        assertTrue(aCharacter.isAlive())
    }

    @Test
    fun `a Character can damage another one`() {
        val aCharacter = Character()
        val anotherCharacter = Character()
        val damage = 50

        aCharacter.damage(anotherCharacter, damage)

        assertEquals(Character.MAX_HEALTH - damage, anotherCharacter.health)
    }

    @Test
    fun `a Character can damage another one twice`() {
        val aCharacter = Character()
        val anotherCharacter = Character()
        val firstDamage = 50
        val secondDamage = 100

        aCharacter.damage(anotherCharacter, firstDamage)
        aCharacter.damage(anotherCharacter, secondDamage)

        assertEquals(Character.MAX_HEALTH - firstDamage - secondDamage, anotherCharacter.health)
    }

    @Test
    fun `a Character is dead with 0 health when damage exceeds the current health`() {
        val aCharacter = Character()
        val anotherCharacter = Character()
        val damage = 1050

        aCharacter.damage(anotherCharacter, damage)

        assertEquals(0, anotherCharacter.health)
        assertFalse(anotherCharacter.isAlive())
    }

    @Test
    fun `a Character can be healed when is alive`() {
        val aCharacter = Character()
        val damagedCharacter = Character()
        val healAmount = 50
        val damage = 100

        aCharacter.damage(damagedCharacter, damage)
        aCharacter.heal(damagedCharacter, healAmount)

        assertTrue(damagedCharacter.isAlive())
        assertEquals(Character.MAX_HEALTH - damage + healAmount, damagedCharacter.health)
    }

    @Test
    fun `a Character cannot be healed when is dead`() {
        val aCharacter = Character()
        val damagedCharacter = Character()
        val healAmount = 50
        val damage = 1050

        aCharacter.damage(damagedCharacter, damage)
        aCharacter.heal(damagedCharacter, healAmount)

        assertFalse(damagedCharacter.isAlive())
        assertEquals(Character.DEAD_HEALTH, damagedCharacter.health)
    }

    @Test
    fun `a Character cannot heal another one when at full health`() {
        val aCharacter = Character()
        val healedCharacter = Character()
        val healAmount = 50

        aCharacter.heal(healedCharacter, healAmount)

        assertEquals(Character.MAX_HEALTH, healedCharacter.health)
    }
}