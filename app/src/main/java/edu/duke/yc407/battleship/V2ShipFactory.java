package edu.duke.yc407.battleship;
/* the first part of the version 2*/

public class V2ShipFactory extends V1ShipFactory{


    public boolean Orientation_checker(Placement where){
        Character orientation = where.getOrientation();
        if(!(orientation == 'u' ||
                orientation == 'U' || orientation == 'R' || orientation == 'r' || orientation == 'd'||orientation == 'D'||
                orientation == 'l' || orientation == 'L')){
            throw new IllegalArgumentException("The Orientation is not valid");
        }
        return true;
    }

    @Override
    public Ship<Character> makeBattleship(Placement where) {
        // TODO Auto-generated method stub
        Orientation_checker(where);
        return new RectangleShip<>(where.getCoordinate(), 'b', '*', where.getOrientation(), "Battleship");
    }

    @Override
    public Ship<Character> makeCarrier(Placement where) {
        // TODO Auto-generated method stub
        Orientation_checker(where);
        return new RectangleShip<>(where.getCoordinate(), 'c', '*', where.getOrientation(), "Carrier");
    }
}
