package main.Heroes;

import main.Defaults.HeroDefaults;
import main.Map.MapTile;
import main.Spells.DamageOverTime;
import main.Spells.Spell;

public abstract class Hero {
    protected char letter = '-';
    protected int currentHP;
    private int thisRoundHP;
    protected int maxHP;
    protected int levelUpHpGain;
    private int level;
    private int xp;
    protected MapTile position;
    private DamageOverTime currentDot;
    private Spell spell1, spell2;
    private boolean bCanMove;
    protected float terrainDamageMultiplier = 1;
    private boolean isDead = false;

    public Hero(final MapTile startingPosition) {
        this.position = startingPosition;
        this.currentHP = 0;
        this.levelUpHpGain = 0;
        this.level = 0;
        this.xp = 0;
        this.removeDot();
        this.bCanMove = true;
    }

    /**
     * Returns the letter assigned to this hero type.
     *
     * @return a single uppercase char
     */
    public final char getLetter() {
        return letter;
    }

    public final int getXp() {
        return xp;
    }

    public final MapTile getPosition() {
        return position;
    }

    public final int getCurrentHP() {
        return currentHP;
    }

    public final int getMaxHP() {
        return maxHP;
    }

    public final int getLevel() {
        return level;
    }

    /**
     * Used to set the spells the hero should use during combat.
     *
     * @param inSpell1 the first spell, if it has a DOT effect, it is ignored.
     * @param inSpell2 the second spell which can have overtime effects.
     */
    protected final void setSpells(final Spell inSpell1, final Spell inSpell2) {
        this.spell1 = inSpell1;
        this.spell2 = inSpell2;
    }

    /**
     * Prepares the current hero to fight the target, calls for the spells to compute their damage.
     *
     * @param enemy enemy hero for the current round.
     */
    public final void preCombat(final Hero enemy) {
        thisRoundHP = currentHP;
        this.spell1.setTarget(enemy);
        this.spell2.setTarget(enemy);
    }

    /**
     * Calls for the spells to apply their damage to the enemy hero via hero.takeDamage(int).
     */
    public final void doDamage() {
        this.spell1.applyDirectDamage();
        this.spell2.applyDirectDamage();
        this.spell2.setDamageOverTime();
    }

    /**
     * Checks at the end of the round if the current hero is dead, and awards the enemy some xp.
     *
     * @param enemy enemy hero for the current round.
     */
    public final void checkXp(final Hero enemy) {
        if (this.currentHP <= 0 && !isDead) {
            int enemyXp = HeroDefaults.BASE_XP_WIN
                    - (enemy.getLevel() - level) * HeroDefaults.XP_WIN_MULTI;
            enemy.gainXP(Math.max(0, enemyXp));
            isDead = true;
        }
    }

    public final boolean isDead() {
        isDead = currentHP <= 0;
        return isDead;
    }

    /**
     * Called after the combat so the actual HP is updated to the temporary round HP.
     */
    public final void updateHP() {
        currentHP = thisRoundHP;
    }

    /**
     * Called at the start of the round to apply the overtime effects.
     */
    public final void beginTurn() {
        thisRoundHP = currentHP;
        if (currentDot != null) {
            currentDot.applyDamageOverTime();
        }
    }

    /**
     * Returns the total damage without the class modifiers it should do to the enemy this round.
     *
     * @param enemy enemy hero for the current round.
     * @return asgas.
     */
    public final int getDamageDealtThisRound(final Hero enemy) {
        preCombat(enemy);
        return spell1.getDamageBeforeClassModifiers() + spell2.getDamageBeforeClassModifiers();
    }

    /**
     * Double dispatch used for getting the racial multiplier.
     *
     * @param s calling spell
     * @return the multiplier
     */
    public float accept(final Spell s) {
        return s.getDamageMultiplier(this);
    }

    /**
     * Adds up the xp gained after combat and redoes the level computations.
     *
     * @param xpToGain the xp it should get.
     */
    private void gainXP(final int xpToGain) {
        if (!isDead()) {
            this.xp += xpToGain;
            if (xp >= HeroDefaults.BASE_XP) {
                int newLevel = 1 + (xp - HeroDefaults.BASE_XP)
                        / HeroDefaults.LEVEL_XP_MULTIPLIER - level;
                if (newLevel >= level) {
                    this.maxHP += (newLevel - level) * levelUpHpGain;
                    this.level += newLevel;
                    this.currentHP = this.maxHP;
                }
            }
        }
    }


    /**
     * Double dispatch used to get the terrain multiplier.
     *
     * @return the multiplier
     */
    public float getTerrainDamageMultiplier() {
        this.terrainDamageMultiplier = this.position.getDamageMultiplier(this);
        return terrainDamageMultiplier;
    }


    public final void setCrippled() {
        this.bCanMove = false;
    }

    public final void removeCrippled() {
        this.bCanMove = true;
    }

    /**
     * Damages the hero.
     *
     * @param damageToTake the damage the hero will take
     */
    public final void takeDamage(final int damageToTake) {
        this.thisRoundHP -= damageToTake;
    }

    /**
     * Special takeDamage function used for the overtime effects. Adds some extra death checks.
     *
     * @param damageToTake the damage the hero will take
     */
    public final void takeOvertimeDamage(final int damageToTake) {
        this.thisRoundHP -= damageToTake;
        if (this.currentHP <= 0) {
            isDead = true;
        }
    }

    /**
     * Used to set a new Overtime effect on the current hero.
     *
     * @param newDot the new Overtime effect which will overwrite the previous one.
     */
    public final void setCurrentDot(final DamageOverTime newDot) {
        if (this.currentDot == null) {
            this.currentDot = newDot;
        } else {
            this.currentDot.beRemoved();
            this.currentDot = newDot;
        }
    }

    public final void removeDot() {
        this.currentDot = null;
    }

    /**
     * Updates the hero position to the new one.
     *
     * @param newPosition the new position the hero should take.
     */
    public final void move(final MapTile newPosition) {
        if (bCanMove && !isDead()) {
            position = newPosition;
        }
    }
}


