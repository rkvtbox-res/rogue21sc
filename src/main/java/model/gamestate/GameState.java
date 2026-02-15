package model.gamestate;

public class GameState {
    private PlayerState playerState;
    private MapState mapState;
    private BackpackState backpackState;
    private MonstersState monstersState;
    private TreasureState treasureState;
    private Potions potions;
    private Weapons weapons;
    private final long seed;

    public GameState() {
        initClass();
        this.seed = System.currentTimeMillis();

    }

    private void initClass() {
        this.playerState = new PlayerState();
        this.mapState = new MapState();
        this.monstersState = new MonstersState();
        this.treasureState = new TreasureState();
        this.potions = new Potions();
        this.weapons = new Weapons();
        this.backpackState = new BackpackState();
    }


    public PlayerState getPlayerState() {
        return playerState;
    }

    public MapState getMapState() {
        return mapState;
    }

    public BackpackState getBackpackState() {
        return backpackState;
    }

    public MonstersState getMonstersState() {
        return monstersState;
    }

    public TreasureState getTreasureState() {
        return treasureState;
    }

    public Potions getPotions() {
        return potions;
    }

    public Weapons getWeapons () {
        return weapons;
    }

    public long getSeed() {
        return seed;
    }
}
