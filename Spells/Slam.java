package main.Spells;

import main.Defaults.SlamDefaults;
import main.Heroes.Hero;
import main.Heroes.Pyromancer;
import main.Heroes.Knight;
import main.Heroes.Wizard;
import main.Heroes.Rogue;

public final class Slam extends Spell {
    public Slam(final Hero inCaster) {
        super(SlamDefaults.DURATION, inCaster);
        this.baseDamage = SlamDefaults.BASE_DAMAGE;
        this.damageBonusPerLevel = SlamDefaults.DAMAGE_BONUS_PER_LEVEL;
        this.damagePerTurn = SlamDefaults.BASE_DAMAGE_PER_TURN;
        this.damagePerTurnBonusPerLevel = SlamDefaults.DAMAGE_PER_TURN_BONUS_PER_LEVEL;
    }

    public void setTarget(final Hero inTarget) {
        this.baseDamage = SlamDefaults.BASE_DAMAGE;
        this.damageBonusPerLevel = SlamDefaults.DAMAGE_BONUS_PER_LEVEL;
        this.damagePerTurn = SlamDefaults.BASE_DAMAGE_PER_TURN;
        this.damagePerTurnBonusPerLevel = SlamDefaults.DAMAGE_PER_TURN_BONUS_PER_LEVEL;
        super.setTarget(inTarget);
    }

    @Override
    public void setDamageOverTime() {
        super.setDamageOverTime();
        target.setCrippled();
    }

    public float getDamageMultiplier(final Hero enemy) {
        return 0;
    }

    public float getDamageMultiplier(final Pyromancer enemy) { //TODO add all classes
        return SlamDefaults.MULTI_PYRO;
    }

    public float getDamageMultiplier(final Knight enemy) {
        return SlamDefaults.MULTI_KNIGHT;
    }

    public float getDamageMultiplier(final Wizard enemy) {
        return SlamDefaults.MULTI_WIZARD;
    }

    public float getDamageMultiplier(final Rogue enemy) {
        return SlamDefaults.MULTI_ROGUE;
    }
}
