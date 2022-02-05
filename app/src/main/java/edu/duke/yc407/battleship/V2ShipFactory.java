package edu.duke.yc407.battleship;
/* the first part of the version 2*/

public class V2ShipFactory extends V1ShipFactory{

    @Override
    public Ship<Character> makeBattleship(Placement where) {
        // TODO Auto-generated method stub
        return new RectangleShip<>(where.getCoordinate(), 'b', '*', where.getOrientation(), "Battleship");
    }

    @Override
    public Ship<Character> makeCarrier(Placement where) {
        // TODO Auto-generated method stub
        return new RectangleShip<>(where.getCoordinate(), 'c', '*', where.getOrientation(), "Carrier");
    }
}
