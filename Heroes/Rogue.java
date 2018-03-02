package main.Heroes;

import main.Defaults.RogueDefaults;
import main.Map.MapTile;
import main.Spells.Backstab;
import main.Spells.Paralysis;
import main.Spells.Spell;

public final class Rogue extends Hero {
    public Rogue(final MapTile startingPosition) {
        super(startingPosition);
        this.letter = RogueDefaults.LETTER;
        this.maxHP = RogueDefaults.MAX_HEALTH;
        this.levelUpHpGain = RogueDefaults.HEALTH_GAIN_LEVEL;
        this.currentHP = this.maxHP;

        this.setSpells(new Backstab(this), new Paralysis(this));
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
