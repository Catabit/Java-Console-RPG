package main.Map;

import main.Defaults.KnightDefaults;
import main.Defaults.PyromancerDefaults;
import main.Defaults.RogueDefaults;
import main.Defaults.WizardDefaults;
import main.Heroes.Hero;
import main.Heroes.Pyromancer;
import main.Heroes.Knight;
import main.Heroes.Wizard;
import main.Heroes.Rogue;

public class MapTile {

    private int x, y;
    private TileType type;

    public MapTile(final int inX, final int inY, final TileType inType) {
        this.x = inX;
        this.y = inY;
        this.type = inType;
    }

    public final int getX() {
        return x;
    }

    public final int getY() {
        return y;
    }

    public final TileType getType() {
        return type;
    }

    /**
     * Returns the damage multiplier for the default abstract class Hero.
     *
     * @param h the default hero
     * @return the damage multiplier
     */
    public float getDamageMultiplier(final Hero h) {
        return 0;
    }

    public final float getDamageMultiplier(final Pyromancer h) {
        if (type == TileType.VOLCANIC) {
            return PyromancerDefaults.VOLCANIC_MULTIPLIER;
        }
        return 1;
    }

    public final float getDamageMultiplier(final Knight h) {
        if (type == TileType.LAND) {
            return KnightDefaults.LAND_MULTIPLIER;
        }
        return 1;
    }

    public final float getDamageMultiplier(final Wizard h) {
        if (type == TileType.DESERT) {
            return WizardDefaults.DESERT_MULTIPLIER;
        }
        return 1;
    }

    public final float getDamageMultiplier(final Rogue h) {
        if (type == TileType.WOODS) {
            return RogueDefaults.WOODS_MULTIPLIER;
        }
        return 1;
    }
}


