package edu.duke.yc407.battleship;
/* the first part of the version 2*/

import org.checkerframework.checker.builder.qual.ReturnsReceiver;

public class V2ShipFactory extends V1ShipFactory{

    public boolean Orientation_checker(Placement where){
        char orientation = where.getOrientation();
        if(!(orientation == 'u' ||
                orientation == 'U' || orientation == 'R' || orientation == 'r' || orientation == 'd'||orientation == 'D'||
                orientation == 'l' || orientation == 'L')){
            throw new IllegalArgumentException("The Orientation is not valid");
        }
        return true;
    }

    protected Ship<Character> createShip(Placement where, int w, int h, char letter, String name) {

        Coordinate upperLeft = where.getCoordinate();
        Character orien = where.getOrientation();
        RectangleShip<Character> tmp =  new RectangleShip<>(name, upperLeft, w, h, letter, '*');;

        if(orien == 'h' || orien =='H'){
            tmp = new RectangleShip<>(name, upperLeft, h, w, letter, '*');
        }
        return tmp;

    };

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
