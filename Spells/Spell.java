package main.Spells;

import main.Heroes.Hero;
import main.Heroes.Pyromancer;
import main.Heroes.Knight;
import main.Heroes.Wizard;
import main.Heroes.Rogue;

public abstract class Spell {
    protected int baseDamage = 0;
    protected int damageBonusPerLevel = 0;
    protected int numTurnsLeft = 0;
    protected int damagePerTurn = 0;
    protected int damagePerTurnBonusPerLevel = 0;
    private float damageMultiplier = 1;
    protected Hero caster, target;
    protected float enemyClassModifier = 1;
    protected float terrainDamageModifier = 1;
    protected int damageBeforeClassModifiers = 0;

    public Spell(final int turns, final Hero inCaster) {
        this.numTurnsLeft = turns;
        this.caster = inCaster;
        this.target = null;
    }

    public final int getDamageBeforeClassModifiers() {
        return damageBeforeClassModifiers;
    }

    /**
     * Sets the enemy target before combat and calculates the damage to deal.
     *
     * @param inTarget enemy hero
     */
    public void setTarget(final Hero inTarget) {
        this.target = inTarget;
        this.terrainDamageModifier = caster.getTerrainDamageMultiplier();
        this.enemyClassModifier = getEnemyClassModifier();

        damageBeforeClassModifiers = this.baseDamage + damageBonusPerLevel * caster.getLevel();
        damageBeforeClassModifiers = Math.round(terrainDamageModifier * damageBeforeClassModifiers);

        this.damageMultiplier = terrainDamageModifier * enemyClassModifier;
        computeDirectDamage();
        computeDamageOverTime();
    }

    /**
     * Double dispatch used to get the class multiplier for the enemy hero.
     *
     * @return the multiplier
     */
    private float getEnemyClassModifier() {
        return target.accept(this);
    }

    public abstract float getDamageMultiplier(Hero enemy);

    public abstract float getDamageMultiplier(Pyromancer enemy);

    public abstract float getDamageMultiplier(Knight enemy);

    public abstract float getDamageMultiplier(Wizard enemy);

    public abstract float getDamageMultiplier(Rogue enemy);

    /**
     * Used to calculate the damage the spell deals directly.
     * Can be extended to add extra functionality and special effects.
     */
    protected void computeDirectDamage() {
        this.baseDamage = this.baseDamage + damageBonusPerLevel * caster.getLevel();
        //Apply the multiplier to the base damage
        this.baseDamage = Math.round(this.baseDamage * damageMultiplier);
    }

    /**
     * Used to calculate the damage the spell deals overtime.
     * Can be extended to add extra functionality and special effects.
     */
    protected void computeDamageOverTime() {
        this.damagePerTurn = this.damagePerTurn + damagePerTurnBonusPerLevel * caster.getLevel();
        //Apply the multiplier to the dot damage
        this.damagePerTurn = Math.round(this.damagePerTurn * damageMultiplier);
    }

    /**
     * Applies the base damage to the enemy target.
     */
    public void applyDirectDamage() {
        target.takeDamage(this.baseDamage);
    }

    /**
     * Sets a new instance of DamageOverTime on the enemy hero.
     */
    public void setDamageOverTime() {
        target.setCurrentDot(new DamageOverTime(numTurnsLeft, damagePerTurn, target));
    }


}
