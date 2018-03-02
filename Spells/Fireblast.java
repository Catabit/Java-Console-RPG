package main.Spells;

import main.Defaults.FireblastDefaults;
import main.Heroes.Hero;
import main.Heroes.Pyromancer;
import main.Heroes.Knight;
import main.Heroes.Wizard;
import main.Heroes.Rogue;

public final class Fireblast extends Spell {

    public Fireblast(final Hero inCaster) {
        super(0, inCaster);
        this.baseDamage = FireblastDefaults.BASE_DAMAGE;
        this.damageBonusPerLevel = FireblastDefaults.DAMAGE_BONUS_PER_LEVEL;
        this.damagePerTurn = 0;
        this.damagePerTurnBonusPerLevel = 0;
    }

    @Override
    public void setTarget(final Hero inTarget) {
        this.baseDamage = FireblastDefaults.BASE_DAMAGE;
        this.damageBonusPerLevel = FireblastDefaults.DAMAGE_BONUS_PER_LEVEL;
        this.damagePerTurn = 0;
        this.damagePerTurnBonusPerLevel = 0;
        super.setTarget(inTarget);
    }

    public float getDamageMultiplier(final Hero enemy) {
        return 0;
    }

    public float getDamageMultiplier(final Pyromancer enemy) {
        return FireblastDefaults.MULTI_PYRO;
    }

    public float getDamageMultiplier(final Knight enemy) {
        return FireblastDefaults.MULTI_KNIGHT;
    }

    public float getDamageMultiplier(final Wizard enemy) {
        return FireblastDefaults.MULTI_WIZARD;
    }

    public float getDamageMultiplier(final Rogue enemy) {
        return FireblastDefaults.MULTI_ROGUE;
    }
}
