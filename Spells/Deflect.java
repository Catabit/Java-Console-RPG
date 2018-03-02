package main.Spells;

import main.Defaults.DeflectDefaults;
import main.Heroes.Hero;
import main.Heroes.Pyromancer;
import main.Heroes.Knight;
import main.Heroes.Wizard;
import main.Heroes.Rogue;

public final class Deflect extends Spell {
    public Deflect(final Hero inCaster) {
        super(0, inCaster);
        this.baseDamage = DeflectDefaults.BASE_DAMAGE;
        this.damageBonusPerLevel = DeflectDefaults.DAMAGE_BONUS_PER_LEVEL;
        this.damagePerTurn = 0;
        this.damagePerTurnBonusPerLevel = 0;
    }

    @Override
    public void setTarget(final Hero inTarget) {
        this.baseDamage = DeflectDefaults.BASE_DAMAGE;
        this.damageBonusPerLevel = DeflectDefaults.DAMAGE_BONUS_PER_LEVEL;
        this.damagePerTurn = 0;
        this.damagePerTurnBonusPerLevel = 0;
        super.setTarget(inTarget);
    }

    /**
     * Extends the base computeDirectDamage so that it always forces the enemy to finish their
     * damage calculations first, and then it computes the final damage based on that.
     */
    @Override
    protected void computeDirectDamage() {
        super.computeDirectDamage();
        if (enemyClassModifier == 0) {
            damageBeforeClassModifiers = 0;
            baseDamage = 0;
        } else {
            int targetDamage = target.getDamageDealtThisRound(caster);
            float finalPercentage = DeflectDefaults.BASE_PERCENTAGE;
            finalPercentage += DeflectDefaults.PERCENTAGE_BONUS_PER_LEVEL * caster.getLevel();
            finalPercentage = Math.min(finalPercentage, DeflectDefaults.MAX_PERCENTAGE);

            finalPercentage *= terrainDamageModifier;
            damageBeforeClassModifiers = Math.round(finalPercentage * targetDamage);
            finalPercentage *= enemyClassModifier;
            baseDamage = Math.round(finalPercentage * targetDamage);
        }
    }

    /**
     * Removes the overtime effect of the spell so it does not interfere with other DOT already set.
     */
    @Override
    public void setDamageOverTime() {
    }

    public float getDamageMultiplier(final Hero enemy) {
        return 0;
    }

    public float getDamageMultiplier(final Pyromancer enemy) {
        return DeflectDefaults.MULTI_PYRO;
    }

    public float getDamageMultiplier(final Knight enemy) {
        return DeflectDefaults.MULTI_KNIGHT;
    }

    public float getDamageMultiplier(final Wizard enemy) {
        return DeflectDefaults.MULTI_WIZARD;
    }

    public float getDamageMultiplier(final Rogue enemy) {
        return DeflectDefaults.MULTI_ROGUE;
    }
}
