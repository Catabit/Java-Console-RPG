package main.Spells;

import main.Defaults.BackstabDefaults;
import main.Heroes.Hero;
import main.Heroes.Pyromancer;
import main.Heroes.Knight;
import main.Heroes.Wizard;
import main.Heroes.Rogue;
import main.Map.TileType;

public final class Backstab extends Spell {

    private int count = BackstabDefaults.RESET_COUNT;

    public Backstab(final Hero inCaster) {
        super(0, inCaster);
        this.baseDamage = BackstabDefaults.BASE_DAMAGE;
        this.damageBonusPerLevel = BackstabDefaults.DAMAGE_BONUS_PER_LEVEL;
        this.damagePerTurn = 0;
        this.damagePerTurnBonusPerLevel = 0;
    }

    @Override
    public void setTarget(final Hero inTarget) {
        this.baseDamage = BackstabDefaults.BASE_DAMAGE;
        this.damageBonusPerLevel = BackstabDefaults.DAMAGE_BONUS_PER_LEVEL;
        this.damagePerTurn = 0;
        this.damagePerTurnBonusPerLevel = 0;
        super.setTarget(inTarget);
    }


    /**
     * Backstab specific critical hit counter handling.
     */
    @Override
    protected void computeDirectDamage() {
        super.computeDirectDamage();
        if (count == BackstabDefaults.RESET_COUNT) {
            if (caster.getPosition().getType() == TileType.WOODS) {
                baseDamage = Math.round(baseDamage * BackstabDefaults.CRITICAL_MULTI);
                damageBeforeClassModifiers = Math.round(damageBeforeClassModifiers
                        * BackstabDefaults.CRITICAL_MULTI);

            }
        }
    }

    @Override
    public void applyDirectDamage() {
        count++;
        if (count == BackstabDefaults.RESET_COUNT) {
            count = 0;
        }
        super.applyDirectDamage();
    }

    public float getDamageMultiplier(final Hero enemy) {
        return 0;
    }

    public float getDamageMultiplier(final Pyromancer enemy) { //TODO add all classes
        return BackstabDefaults.MULTI_PYRO;
    }

    public float getDamageMultiplier(final Knight enemy) {
        return BackstabDefaults.MULTI_KNIGHT;
    }

    public float getDamageMultiplier(final Wizard enemy) {
        return BackstabDefaults.MULTI_WIZARD;
    }

    public float getDamageMultiplier(final Rogue enemy) {
        return BackstabDefaults.MULTI_ROGUE;
    }
}

