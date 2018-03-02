package main.Heroes;

import main.Defaults.KnightDefaults;
import main.Map.MapTile;
import main.Spells.Execute;
import main.Spells.Slam;
import main.Spells.Spell;

public final class Knight extends Hero {

    public Knight(final MapTile startingPosition) {
        super(startingPosition);
        this.letter = KnightDefaults.LETTER;
        this.maxHP = KnightDefaults.MAX_HEALTH;
        this.levelUpHpGain = KnightDefaults.HEALTH_GAIN_LEVEL;
        this.currentHP = this.maxHP;

        this.setSpells(new Execute(this), new Slam(this));

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
