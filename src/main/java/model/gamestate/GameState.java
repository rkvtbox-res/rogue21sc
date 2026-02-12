package model.gamestate;

public class GameState {
    private PlayerState playerState;
    private MapState mapState;
    private BackpackState backpackState;
    private MonstersState monstersState;
    private TreasureState treasureState;
    private Potions potions;
    private Weapons weapons;

    public GameState() {
        init();
    }

    private void init() {
        playerState = new PlayerState();
        mapState = new MapState();
        monstersState = new MonstersState();
        treasureState = new TreasureState();
        potions = new Potions();
        weapons = new Weapons();
        backpackState = new BackpackState();
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
}
