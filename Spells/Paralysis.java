package main.Spells;

import main.Defaults.ParalysisDefaults;
import main.Heroes.Hero;
import main.Heroes.Pyromancer;
import main.Heroes.Knight;
import main.Heroes.Wizard;
import main.Heroes.Rogue;
import main.Map.TileType;

public final class Paralysis extends Spell {
    public Paralysis(final Hero inCaster) {
        super(ParalysisDefaults.DURATION, inCaster);
        this.baseDamage = ParalysisDefaults.BASE_DAMAGE;
        this.damageBonusPerLevel = ParalysisDefaults.DAMAGE_BONUS_PER_LEVEL;
        this.damagePerTurn = ParalysisDefaults.BASE_DAMAGE_PER_TURN;
        this.damagePerTurnBonusPerLevel = ParalysisDefaults.DAMAGE_PER_TURN_BONUS_PER_LEVEL;
    }

    /**
     * Checks before the combat happens if it will be on a WOODS tile, so the duration should be
     * extended.
     * @param inTarget enemy hero
     */
    @Override
    public void setTarget(final Hero inTarget) {
        this.baseDamage = ParalysisDefaults.BASE_DAMAGE;
        this.damageBonusPerLevel = ParalysisDefaults.DAMAGE_BONUS_PER_LEVEL;
        this.damagePerTurn = ParalysisDefaults.BASE_DAMAGE_PER_TURN;
        this.damagePerTurnBonusPerLevel = ParalysisDefaults.DAMAGE_PER_TURN_BONUS_PER_LEVEL;
        super.setTarget(inTarget);

        if (target.getPosition().getType() == TileType.WOODS) {
            this.numTurnsLeft = ParalysisDefaults.WOODS_DURATION;
        } else {
            this.numTurnsLeft = ParalysisDefaults.DURATION;
        }
    }

    @Override
    public void setDamageOverTime() {
        super.setDamageOverTime();
        target.setCrippled();
    }

    public float getDamageMultiplier(final Hero enemy) {
        return 0;
    }

    public float getDamageMultiplier(final Pyromancer enemy) {
        return ParalysisDefaults.MULTI_PYRO;
    }

    public float getDamageMultiplier(final Knight enemy) {
        return ParalysisDefaults.MULTI_KNIGHT;
    }

    public float getDamageMultiplier(final Wizard enemy) {
        return ParalysisDefaults.MULTI_WIZARD;
    }

    public float getDamageMultiplier(final Rogue enemy) {
        return ParalysisDefaults.MULTI_ROGUE;
    }
}
