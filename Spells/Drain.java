package main.Spells;

import main.Defaults.DrainDefaults;
import main.Heroes.Hero;
import main.Heroes.Pyromancer;
import main.Heroes.Knight;
import main.Heroes.Wizard;
import main.Heroes.Rogue;

public final class Drain extends Spell {
    public Drain(final Hero inCaster) {
        super(0, inCaster);
        this.baseDamage = DrainDefaults.BASE_DAMAGE;
        this.damageBonusPerLevel = DrainDefaults.DAMAGE_BONUS_PER_LEVEL;
        this.damagePerTurn = 0;
        this.damagePerTurnBonusPerLevel = 0;
    }

    @Override
    public void setTarget(final Hero inTarget) {
        this.baseDamage = DrainDefaults.BASE_DAMAGE;
        this.damageBonusPerLevel = DrainDefaults.DAMAGE_BONUS_PER_LEVEL;
        this.damagePerTurn = 0;
        this.damagePerTurnBonusPerLevel = 0;
        super.setTarget(inTarget);
    }

    @Override
    protected void computeDirectDamage() {
        super.computeDirectDamage();
        int minHP = Math.round(DrainDefaults.MIN_HEALTH * target.getMaxHP());
        int accountedHP = Math.min(minHP, target.getCurrentHP());


        float finalPercentage = DrainDefaults.BASE_PERCENTAGE;
        finalPercentage += DrainDefaults.PERCENTAGE_BONUS_PER_LEVEL * caster.getLevel();
        finalPercentage = finalPercentage * terrainDamageModifier;

        damageBeforeClassModifiers = Math.round(finalPercentage * accountedHP);
        finalPercentage *= enemyClassModifier;
        baseDamage = Math.round(finalPercentage * accountedHP);
    }

    public float getDamageMultiplier(final Hero enemy) {
        return 0;
    }

    public float getDamageMultiplier(final Pyromancer enemy) { //TODO add all classes
        return DrainDefaults.MULTI_PYRO;
    }

    public float getDamageMultiplier(final Knight enemy) {
        return DrainDefaults.MULTI_KNIGHT;
    }

    public float getDamageMultiplier(final Wizard enemy) {
        return DrainDefaults.MULTI_WIZARD;
    }

    public float getDamageMultiplier(final Rogue enemy) {
        return DrainDefaults.MULTI_ROGUE;
    }
}
