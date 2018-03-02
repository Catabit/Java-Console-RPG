package main.Heroes;

import main.Defaults.PyromancerDefaults;
import main.Map.MapTile;
import main.Spells.Fireblast;
import main.Spells.Ignite;
import main.Spells.Spell;

public final class Pyromancer extends Hero {

    public Pyromancer(final MapTile startingPosition) {
        super(startingPosition);
        letter = PyromancerDefaults.LETTER;
        this.maxHP = PyromancerDefaults.MAX_HEALTH;
        this.levelUpHpGain = PyromancerDefaults.HEALTH_GAIN_LEVEL;
        this.currentHP = this.maxHP;

        this.setSpells(new Fireblast(this), new Ignite(this));

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
