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


/*
*
 b                               b
bbb    gets hit and becomes     *bb
*
* tryADdship myships
*
* up: b - >1  (1 -> *)
*
* b
* bb
* b
*
*
* Down: bbb      --
*        b
*
*
* d & s --> 1,2,3 --done
* ddd
*
*
*
*
*
* To do:
* (1) 造船的方式 -done
* （2）造船的过程中硬编码 -done
* （3）enemyhit
* (4) remove 的函数改一下
*
*
*
* */