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
    fun `heal a character`() {
        val attacker = CharacterRPG()
        val damageValue = 20
        attacker.damage(target, damageValue)

        val healValue = 10
        target.heal(healValue)

        val expectedHealth = INITIAL_HP - damageValue + healValue
        assertEquals(expectedHealth, target.health)
    }

    @Test
    fun `not heal when another character is dead`() {
        val healer = CharacterRPG()
        healer.damage(target, INITIAL_HP + 10)

        val healValue = 10
        healer.heal(healValue)

        assertFalse(target.isAlive)
        assertEquals(ZERO_HP, target.health)
    }

    @Test
    fun `not heal over initial hp`() {
        val healer = CharacterRPG()

        val healValue = 10
        healer.heal(healValue)

        assertEquals(INITIAL_HP, target.health)
    }

    @Test
    fun `a Character should not damage itself`() {
        val aCharacter = CharacterRPG()
        val damage = 10

        aCharacter.damage(aCharacter, damage)

        assertEquals(aCharacter.health, CharacterRPG.INITIAL_HP)
    }

    @Test
    fun `a Character should only heal itself`() {
        val aCharacter = CharacterRPG()
        val attacker = CharacterRPG()
        val damage = 50
        attacker.damage(aCharacter, damage)
        val healAmount = 10

        aCharacter.heal(healAmount)

        assertEquals((CharacterRPG.INITIAL_HP - damage + healAmount), aCharacter.health)
    }

    @Test
    fun `a Character cannot heal another Character`() {
        val aCharacter = CharacterRPG()
        val attacker = CharacterRPG()
        val healerCharacter = CharacterRPG()
        val damage = 50
        attacker.damage(aCharacter, damage)
        val healAmount = 10

        healerCharacter.heal(healAmount)

        assertEquals((CharacterRPG.INITIAL_HP - damage), aCharacter.health)
    }

    @Test
    fun `should deal half damage if target is 5 or more levels`() {
        val damageValue = 10
        val attacker = CharacterRPG()
        target.level = attacker.level + 5

        attacker.damage(target, damageValue)

        assertTrue(target.isAlive)
        assertEquals((CharacterRPG.INITIAL_HP - (damageValue / 2)), target.health)
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
    var level: Int = INITIAL_LEVEL
    var health: Int = INITIAL_HP
        private set

    fun damage(target: CharacterRPG, damage: Int) {
        if(this == target) return
        var finalDamage = damage
        if(target.level >= (this.level + 5)){
            finalDamage = damage / 2
        }
        target.health = maxOf(ZERO_HP, target.health - finalDamage)
    }

    fun heal(heal: Int) {
        if (this.isAlive)
            this.health = minOf(INITIAL_HP, this.health + heal)
    }

    companion object {
        const val ZERO_HP = 0
        const val INITIAL_HP = 1000
        const val INITIAL_LEVEL = 1
    }
}
