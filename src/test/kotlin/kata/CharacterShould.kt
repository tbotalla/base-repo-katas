package kata

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CharacterShould {

    @BeforeEach
    fun setup(){
        target = CharacterRPG()
    }

    @Test
    fun `be created with 1000 hp`() {
        val character = CharacterRPG()

        assertEquals(INITIAL_HP, character.health)
    }

    @Test
    fun `be created with level 1`() {
        val character = CharacterRPG()

        assertEquals(INITIAL_LEVEL, character.level)
    }

    @Test
    fun `be alive when is created`() {
        val character = CharacterRPG()

        val isAlive = character.isAlive

        assertTrue(isAlive)
    }

    @Test
    fun `subtract health to other CharacterRPG when deal damage`() {
        val damageValue = INITIAL_HP - 10
        val attacker = CharacterRPG()

        attacker.damage(target, damageValue)

        val expectedHealth = INITIAL_HP - damageValue
        assertEquals(expectedHealth, target.health)
    }

    @Test
    fun `kill another character when damage exceeds his health`() {
        val damageValue = INITIAL_HP + 10
        val attacker = CharacterRPG()

        attacker.damage(target, damageValue)

        assertFalse(target.isAlive)
        assertEquals(ZERO_HP, target.health)
    }

    @Test
    fun `heal another character`() {
        val healer = CharacterRPG()
        val damageValue = 20
        healer.damage(target, damageValue)

        val healValue = 10
        healer.heal(target, healValue)

        val expectedHealth = INITIAL_HP - damageValue + healValue
        assertEquals(expectedHealth, target.health)
    }

    @Test
    fun `not heal when another character is dead`() {
        val healer = CharacterRPG()
        healer.damage(target, INITIAL_HP + 10)

        val healValue = 10
        healer.heal(target, healValue)

        assertFalse(target.isAlive)
        assertEquals(ZERO_HP, target.health)
    }

    @Test
    fun `not heal over initial hp`() {
        val healer = CharacterRPG()

        val healValue = 10
        healer.heal(target, healValue)

        assertEquals(INITIAL_HP, target.health)
    }

    companion object {
        const val ZERO_HP = 0
        const val INITIAL_HP = 1000
        const val INITIAL_LEVEL = 1
        lateinit var target: CharacterRPG
    }
}

class CharacterRPG {

    val isAlive: Boolean
        get() = health > ZERO_HP
    val level: Int = INITIAL_LEVEL
    var health: Int = INITIAL_HP
        private set

    fun damage(target: CharacterRPG, damage: Int) {
        target.health = maxOf(ZERO_HP, target.health - damage)
    }

    fun heal(target: CharacterRPG, heal: Int) {
        if(target.isAlive)
            target.health = minOf(INITIAL_HP, target.health + heal)
    }

    companion object {
        const val ZERO_HP = 0
        const val INITIAL_HP = 1000
        const val INITIAL_LEVEL = 1
    }
}
