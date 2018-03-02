package main.Spells;

import main.Heroes.Hero;

public final class DamageOverTime {

    private Hero target;
    private int damagePerTurn = 0;
    private int numTurnsLeft = 0;

    public DamageOverTime(final int duration, final int inDamagePerTurn, final Hero inTarget) {
        this.target = inTarget;
        this.numTurnsLeft = duration;
        this.damagePerTurn = inDamagePerTurn;

    }

    /**
     * Applies the damage over time part of the parent spell and decrements the TurnsLeft counter.
     * In case the counter gets to 0, the effect is removed from the target.
     */
    public void applyDamageOverTime() {
        if (this.numTurnsLeft > 0) {
            this.numTurnsLeft--;
            target.takeOvertimeDamage(damagePerTurn);
        }
        if (this.numTurnsLeft <= 0) {
            this.beRemoved();
        }
    }

    /**
     * Removes all the overtime effects from the enemy target.
     */
    public void beRemoved() {
        target.removeCrippled();
        target.removeDot();
    }
}
