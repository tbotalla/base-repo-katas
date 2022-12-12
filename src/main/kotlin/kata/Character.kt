package kata

class Character {
    var health: Health = Health.getMaxHealth()
    val level: Int = 1

    fun isAlive(): Boolean = health.isAlive()

    fun damage(characterToDamage: Character, damage: Int) {
        characterToDamage.health = Health(characterToDamage.health.value - damage)
    }

    fun heal(characterToHeal: Character, healAmount: Int) {
        if (characterToHeal.isAlive())
            characterToHeal.health = Health(characterToHeal.health.value + healAmount)
    }

    companion object {
        const val DEAD_HEALTH: Int = 0
        const val MAX_HEALTH: Int = 1000
    }
}

class Health(var value: Int) {

    init {
        if (value < DEAD_HEALTH) value = DEAD_HEALTH
        if (value > MAX_HEALTH) value = MAX_HEALTH
    }

    fun isAlive(): Boolean = value > Character.DEAD_HEALTH

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Health

        return value == other.value
    }



    companion object {
        @JvmStatic
        fun getMaxHealth() = Health(MAX_HEALTH)

        const val DEAD_HEALTH: Int = 0
        const val MAX_HEALTH: Int = 1000
    }
}