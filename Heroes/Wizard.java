package main.Heroes;

import main.Defaults.WizardDefaults;
import main.Map.MapTile;
import main.Spells.Deflect;
import main.Spells.Drain;
import main.Spells.Spell;

public final class Wizard extends Hero {
    public Wizard(final MapTile startingPosition) {
        super(startingPosition);
        this.letter = WizardDefaults.LETTER;
        this.maxHP = WizardDefaults.MAX_HEALTH;
        this.levelUpHpGain = WizardDefaults.HEALTH_GAIN_LEVEL;
        this.currentHP = this.maxHP;

        this.setSpells(new Drain(this), new Deflect(this));
    }

    @Override
    public float accept(final Spell s) {
        return s.getDamageMultiplier(this);
    }

    @Override
    public float getTerrainDamageMultiplier() {
        this.terrainDamageMultiplier = this.position.getDamageMultiplier(this);
        return terrainDamageMultiplier;
    }
}
