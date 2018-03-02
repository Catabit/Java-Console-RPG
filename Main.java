package main;


import fileio.implementations.FileReader;
import fileio.implementations.FileWriter;
import main.Heroes.Hero;
import main.Heroes.Pyromancer;
import main.Heroes.Knight;
import main.Heroes.Wizard;
import main.Heroes.Rogue;
import main.Map.Map;
import main.Map.MapTile;
import main.Map.TileType;


public abstract class Main {

    public static void main(final String[] args) {
        int lines, cols;
        try {
            int numHeroes;
            Hero[] heroes;
            int numRounds;
            FileReader fr = new FileReader(args[0]);
            lines = fr.nextInt();
            cols = fr.nextInt();
            Map map = new Map(lines, cols);

            for (int i = 0; i < lines; i++) {
                String s = fr.nextWord();
                for (int j = 0; j < cols; j++) {
                    map.getTiles()[i][j] = new MapTile(i, j, chrToTileType(s.charAt(j)));
                }
            }
            numHeroes = fr.nextInt();
            heroes = new Hero[numHeroes];
            for (int i = 0; i < numHeroes; i++) {
                String s = fr.nextWord();
                int x = fr.nextInt(), y = fr.nextInt();
                switch (s.charAt(0)) {
                    case 'P':
                        heroes[i] = new Pyromancer(map.getTiles()[x][y]);
                        break;
                    case 'K':
                        heroes[i] = new Knight(map.getTiles()[x][y]);
                        break;
                    case 'W':
                        heroes[i] = new Wizard(map.getTiles()[x][y]);
                        break;
                    case 'R':
                        heroes[i] = new Rogue(map.getTiles()[x][y]);
                        break;
                    default:
                        heroes[i] = null;
                        break;
                }
            }
            numRounds = fr.nextInt();
            for (int i = 0; i < numRounds; i++) {

                processMovement(heroes, map, fr.nextWord());
                beginTurn(heroes);

                for (int k = 0; k < numHeroes; k++) {
                    for (int j = k + 1; j < numHeroes; j++) {
                        if (heroes[k].getPosition() == heroes[j].getPosition()) { //references
                            if (!heroes[k].isDead() && !heroes[j].isDead()) {

                                heroes[k].preCombat(heroes[j]);
                                heroes[j].preCombat(heroes[k]);

                                heroes[k].doDamage();
                                heroes[j].doDamage();

                                heroes[j].updateHP();
                                heroes[k].updateHP();

                                heroes[j].checkXp(heroes[k]);
                                heroes[k].checkXp(heroes[j]);

                            }
                        }
                    }
                }


            }
            FileWriter fw = new FileWriter(args[1]);
            for (Hero h : heroes) {
                String out = h.getLetter() + " ";
                if (h.isDead()) {
                    out += "dead";
                } else {
                    out += h.getLevel() + " " + h.getXp() + " " + h.getCurrentHP() + " ";
                    out += h.getPosition().getX() + " " + h.getPosition().getY();
                }
                fw.writeWord(out);
                fw.writeNewLine();

            }
            fw.close();
            fr.close();


        } catch (java.io.IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private static void beginTurn(final Hero[] heroes) {
        for (Hero h : heroes) {
            h.beginTurn();
            h.updateHP();
        }
    }

    private static TileType chrToTileType(final char c) {
        switch (c) {
            case 'L':
                return TileType.LAND;
            case 'W':
                return TileType.WOODS;
            case 'D':
                return TileType.DESERT;
            case 'V':
                return TileType.VOLCANIC;
            default:
                return null;
        }
    }

    private static void processMovement(final Hero[] heroes, final Map map, final String info) {
        for (int i = 0; i < heroes.length; i++) {
            if (!heroes[i].isDead()) {
                switch (info.charAt(i)) {
                    case 'U':
                        heroes[i].move(map.getTileOffset(heroes[i].getPosition(), -1, 0));
                        break;
                    case 'D':
                        heroes[i].move(map.getTileOffset(heroes[i].getPosition(), 1, 0));
                        break;
                    case 'R':
                        heroes[i].move(map.getTileOffset(heroes[i].getPosition(), 0, 1));
                        break;
                    case 'L':
                        heroes[i].move(map.getTileOffset(heroes[i].getPosition(), 0, -1));
                        break;
                    default:
                        heroes[i].move(map.getTileOffset(heroes[i].getPosition(), 0, 0));
                        break;
                }
            }
        }
    }

}
