package model.generator;

public enum Cell {
    WALL,
    FLOOR,
    DOOR,
    CORRIDOR,
    STAIR_NEXT_LEVEL,
    EMPTY;

public boolean isWalkable() {
    return this == FLOOR || this == CORRIDOR || this == DOOR || this == STAIR_NEXT_LEVEL;
}}