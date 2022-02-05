/*
@param info shows whether this RectangleShip has been hitted or not
@param data shows the ship has not been hitted
@param onhit shows the ship has been hitted
@param ShipDisplayInfo is the class for data and onhit

 */

package edu.duke.yc407.battleship;

import org.checkerframework.checker.units.qual.C;

import java.util.HashSet;

public class RectangleShip<T> extends BasicShip<T> {


  final String name;
  public String getName(){
    return name;
  }
  
  static HashSet<Coordinate> makeCoords(Coordinate upperLeft, int width, int height){

    HashSet<Coordinate> hashset = new HashSet<>();
    for(int i = upperLeft.getRow(); i < upperLeft.getRow() + height; i++){
      for(int j = upperLeft.getColumn() ; j < upperLeft.getColumn()+ width; j++){
        hashset.add(new Coordinate(i,j));
      }
    }
    return hashset;
  }


  static HashSet<Coordinate> makeCoords_V2(Coordinate upperLeft, Character orientation, String name){
    HashSet<Coordinate> s = new HashSet<>();
    int row = upperLeft.getRow();
    int column = upperLeft.getColumn();
    if(name.equals("Battleship")) {
      if (orientation == 'U' || orientation == 'u') {
        s.addAll(makeCoords(new Coordinate(row, column + 1), 1, 1));
        s.addAll(makeCoords(new Coordinate(row + 1, column ), 3, 1));
      }
      if (orientation == 'R' || orientation == 'r') {
        s.addAll(makeCoords(new Coordinate(row, column), 1, 3));
        s.addAll(makeCoords(new Coordinate(row + 1, column + 1), 1, 1));
      }
      if (orientation == 'D' || orientation == 'd') {
        s.addAll(makeCoords(new Coordinate(row, column), 3, 1));
        s.addAll(makeCoords(new Coordinate(row + 1, column + 1), 1, 1));
      }
      if (orientation == 'L' || orientation == 'l') {
        s.addAll(makeCoords(new Coordinate(row + 1, column), 1, 1));
        s.addAll(makeCoords(new Coordinate(row, column + 1), 1, 3));
      }

    }
    else if(name.equals("Carrier")){
      if(orientation == 'U' || orientation == 'u'){
        s.addAll(makeCoords(new Coordinate(row,column),1,4));
        s.addAll(makeCoords(new Coordinate(row+2,column+1),1,3));
      }

      if(orientation == 'R' || orientation == 'r'){
        s.addAll(makeCoords(new Coordinate(row,column+1),4,1));
        s.addAll(makeCoords(new Coordinate(row+1,column),3,1));
      }
      if(orientation == 'D' || orientation == 'd'){
        s.addAll(makeCoords(new Coordinate(row,column),1,3));
        s.addAll(makeCoords(new Coordinate(row+1,column+1),1,4));
      }
      if(orientation == 'L' || orientation == 'l'){
        s.addAll(makeCoords(new Coordinate(row,column+2),3,1));
        s.addAll(makeCoords(new Coordinate(row+1,column),4,1));
      }
    }
    return s;

  }

/* 星的地方才是upperleft*/
//  static HashSet<Coordinate> makeCoords_Carrier(Coordinate upperLeft, Character orientation){
//    HashSet<Coordinate> s = new HashSet<>();
//    int row = upperLeft.getRow();
//    int column = upperLeft.getColumn();
//    if(orientation == 'U'){
//      s.addAll(makeCoords(new Coordinate(row,column),1,4));
//      s.addAll(makeCoords(new Coordinate(row+2,column+1),1,3));
//    }
//
//    if(orientation == 'R'){
//      s.addAll(makeCoords(new Coordinate(row,column+1),4,1));
//      s.addAll(makeCoords(new Coordinate(row+1,column),3,1));
//    }
//    if(orientation == 'D'){
//      s.addAll(makeCoords(new Coordinate(row,column),1,3));
//      s.addAll(makeCoords(new Coordinate(row+1,column+1),1,4));
//    }
//    if(orientation == 'L'){
//      s.addAll(makeCoords(new Coordinate(row,column+2),3,1));
//      s.addAll(makeCoords(new Coordinate(row+1,column),4,1));
//    }
//    return s;
//  }


  public RectangleShip(String name,Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> info,ShipDisplayInfo<T> info_enermy) {
    super(makeCoords(upperLeft, width, height),info,info_enermy);
    this.name = name;
  }
    
  public RectangleShip(String name, Coordinate upperLeft, int width, int height, T data, T onHit) {
    this(name,upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit), new SimpleShipDisplayInfo<T>(null, data));
  }
  public RectangleShip(Coordinate upperLeft, T data, T onHit) {
    this("testship",upperLeft, 1, 1, new SimpleShipDisplayInfo<T>(data, onHit), new SimpleShipDisplayInfo<T>(null,data));
  }

  public RectangleShip(Coordinate upperLeft, T data, T onHit, Character orientation, String name){
    super(makeCoords_V2(upperLeft, orientation,name), new SimpleShipDisplayInfo<T>(data, onHit), new SimpleShipDisplayInfo<T>(null, data));
    this.name = name;
  }



//  public RectangleShip(Coordinate upperLeft, Character Orientation, String name,T data, T onHit ){
//    this.name = name;
//    super(makeCoords_Battleship(upperLeft,Orientation), new SimpleShipDisplayInfo<T>(data, onHit), new SimpleShipDisplayInfo<T>(null, data));
//  }
  
}
