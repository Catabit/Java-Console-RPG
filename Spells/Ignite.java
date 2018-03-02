package main.Spells;

import main.Defaults.IgniteDefaults;
import main.Heroes.Hero;
import main.Heroes.Pyromancer;
import main.Heroes.Knight;
import main.Heroes.Wizard;
import main.Heroes.Rogue;

public final class Ignite extends Spell {
    public Ignite(final Hero inCaster) {
        super(IgniteDefaults.DURATION, inCaster);
        this.baseDamage = IgniteDefaults.BASE_DAMAGE;
        this.damageBonusPerLevel = IgniteDefaults.DAMAGE_BONUS_PER_LEVEL;
        this.damagePerTurn = IgniteDefaults.BASE_DAMAGE_PER_TURN;
        this.damagePerTurnBonusPerLevel = IgniteDefaults.DAMAGE_PER_TURN_BONUS_PER_LEVEL;
    }

    @Override
    public void setTarget(final Hero inTarget) {
        this.baseDamage = IgniteDefaults.BASE_DAMAGE;
        this.damageBonusPerLevel = IgniteDefaults.DAMAGE_BONUS_PER_LEVEL;
        this.damagePerTurn = IgniteDefaults.BASE_DAMAGE_PER_TURN;
        this.damagePerTurnBonusPerLevel = IgniteDefaults.DAMAGE_PER_TURN_BONUS_PER_LEVEL;
        super.setTarget(inTarget);
    }

    public float getDamageMultiplier(final Hero enemy) {
        return 0;
    }

    public float getDamageMultiplier(final Pyromancer enemy) { //TODO add all classes
        return IgniteDefaults.MULTI_PYRO;
    }

    public float getDamageMultiplier(final Knight enemy) {
        return IgniteDefaults.MULTI_KNIGHT;
    }

    public float getDamageMultiplier(final Wizard enemy) {
        return IgniteDefaults.MULTI_WIZARD;
    }

    public float getDamageMultiplier(final Rogue enemy) {
        return IgniteDefaults.MULTI_ROGUE;
    }
}
