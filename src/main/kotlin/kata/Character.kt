package kata

class Character {
    var health: Int = MAX_HEALTH
    val level: Int = 1

    fun isAlive(): Boolean = health > DEAD_HEALTH

    fun damage(characterToDamage: Character, damage: Int) {
        characterToDamage.health -= damage
        if (characterToDamage.health < DEAD_HEALTH) characterToDamage.health = DEAD_HEALTH
    }

    fun heal(characterToHeal: Character, healAmount: Int) {
        if (characterToHeal.isAlive())
            characterToHeal.health += healAmount
        if (characterToHeal.health > MAX_HEALTH) characterToHeal.health = MAX_HEALTH
    }

    companion object {
        const val DEAD_HEALTH: Int = 0
        const val MAX_HEALTH: Int = 1000
    }
}

class Health(var value: Int) {
    init {
        if (value < 0) throw HealthCannotBeNegative("The Health cannot be negative")
        if (value > MAX_HEALTH) value = MAX_HEALTH
    }

    companion object {
        const val MAX_HEALTH: Int = 1000
    }
}

class HealthCannotBeNegative(message: String) : RuntimeException(message)