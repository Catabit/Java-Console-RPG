package main.Spells;

import main.Defaults.ExecuteDefaults;
import main.Heroes.Hero;
import main.Heroes.Pyromancer;
import main.Heroes.Knight;
import main.Heroes.Wizard;
import main.Heroes.Rogue;


public final class Execute extends Spell {

    public Execute(final Hero inCaster) {
        super(0, inCaster);
        this.baseDamage = ExecuteDefaults.BASE_DAMAGE;
        this.damageBonusPerLevel = ExecuteDefaults.DAMAGE_BONUS_PER_LEVEL;
        this.damagePerTurn = 0;
        this.damagePerTurnBonusPerLevel = 0;
    }

    @Override
    public void setTarget(final Hero inTarget) {
        this.baseDamage = ExecuteDefaults.BASE_DAMAGE;
        this.damageBonusPerLevel = ExecuteDefaults.DAMAGE_BONUS_PER_LEVEL;
        this.damagePerTurn = 0;
        this.damagePerTurnBonusPerLevel = 0;
        super.setTarget(inTarget);
    }

    @Override
    protected void computeDirectDamage() {
        super.computeDirectDamage();
        float hplimit = ExecuteDefaults.HP_LIMIT;
        hplimit += ExecuteDefaults.HP_LIMIT_BONUS_PER_LEVEL * caster.getLevel();
        hplimit = Math.min(hplimit, ExecuteDefaults.HP_LIMIT_MAX);

        if (target.getCurrentHP() <= target.getMaxHP() * hplimit) {
            baseDamage = target.getCurrentHP();
            this.damageBeforeClassModifiers = Math.round(baseDamage * terrainDamageModifier);
        }
    }

    public float getDamageMultiplier(final Hero enemy) {
        return 0;
    }

    public float getDamageMultiplier(final Pyromancer enemy) { //TODO add all classes
        return ExecuteDefaults.MULTI_PYRO;
    }

    public float getDamageMultiplier(final Knight enemy) {
        return ExecuteDefaults.MULTI_KNIGHT;
    }

    public float getDamageMultiplier(final Wizard enemy) {
        return ExecuteDefaults.MULTI_WIZARD;
    }

    public float getDamageMultiplier(final Rogue enemy) {
        return ExecuteDefaults.MULTI_ROGUE;
    }
}
