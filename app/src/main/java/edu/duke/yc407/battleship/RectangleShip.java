/*
@param info shows whether this RectangleShip has been hitted or not
@param data shows the ship has not been hitted
@param onhit shows the ship has been hitted
@param ShipDisplayInfo is the class for data and onhit

 */

package edu.duke.yc407.battleship;

import com.google.common.cache.AbstractCache;
import org.checkerframework.checker.units.qual.C;

import java.util.HashMap;
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


//to be confirmed with 郭郭
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
    if(name.equals("Carrier")){
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


  /* To make the mypices_order*/
  static HashMap<Coordinate, Integer> make_Order(Coordinate upperLeft, Character orientation, String name){
    HashMap<Coordinate, Integer> tmp = new HashMap<>();
    int row = upperLeft.getRow();
    int column = upperLeft.getColumn();
    if(name.equals("Battleship")) {
      if (orientation == 'U' || orientation == 'u') {
        tmp.put(new Coordinate(row,column+1),1);
        tmp.put(new Coordinate(row+1,column),2);
        tmp.put(new Coordinate(row+1,column+1),3);
        tmp.put(new Coordinate(row+1,column+2),4);
      }
      if (orientation == 'R' || orientation == 'r') {
        tmp.put(new Coordinate(row+1,column+1),1);
        tmp.put(new Coordinate(row,column),2);
        tmp.put(new Coordinate(row+1,column),3);
        tmp.put(new Coordinate(row+2,column),4);
      }
      if (orientation == 'D' || orientation == 'd'){
        tmp.put(new Coordinate(row+1,column+1),1);
        tmp.put(new Coordinate(row,column+2),2);
        tmp.put(new Coordinate(row,column+1),3);
        tmp.put(new Coordinate(row,column),4);
      }
      if (orientation == 'L' || orientation == 'l') {
        tmp.put(new Coordinate(row+1,column),1);
        tmp.put(new Coordinate(row,column+1),4);
        tmp.put(new Coordinate(row+1,column+1),3);
        tmp.put(new Coordinate(row+2,column+1),2);
      }

    }
    if(name.equals("Carrier")) {
      if (orientation == 'U' || orientation == 'u') {
        tmp.put(new Coordinate(row,column),1);
        tmp.put(new Coordinate(row+1,column),2);
        tmp.put(new Coordinate(row+2,column),3);
        tmp.put(new Coordinate(row+3,column),4);
        tmp.put(new Coordinate(row+2,column+1),5);
        tmp.put(new Coordinate(row+3,column+1),6);
        tmp.put(new Coordinate(row+4,column+1),7);
      }
      if (orientation == 'r' || orientation == 'R') {
        tmp.put(new Coordinate(row,column+4),1);
        tmp.put(new Coordinate(row,column+3),2);
        tmp.put(new Coordinate(row,column+2),3);
        tmp.put(new Coordinate(row,column+1),4);
        tmp.put(new Coordinate(row+1,column+2),5);
        tmp.put(new Coordinate(row+1,column+1),6);
        tmp.put(new Coordinate(row+1,column),7);
      }
      if (orientation == 'd' || orientation == 'D') {
        tmp.put(new Coordinate(row+4,column+1),1);
        tmp.put(new Coordinate(row+3,column+1),2);
        tmp.put(new Coordinate(row+2,column+1),3);
        tmp.put(new Coordinate(row+1,column+1),4);
        tmp.put(new Coordinate(row+2,column),5);
        tmp.put(new Coordinate(row+1,column),6);
        tmp.put(new Coordinate(row,column),7);
      }
      if (orientation == 'l' || orientation == 'L') {
        tmp.put(new Coordinate(row+1,column),1);
        tmp.put(new Coordinate(row+1,column+1),2);
        tmp.put(new Coordinate(row+1,column+2),3);
        tmp.put(new Coordinate(row+1,column+3),4);
        tmp.put(new Coordinate(row,column+2),5);
        tmp.put(new Coordinate(row,column+3),6);
        tmp.put(new Coordinate(row,column+4),7);
      }

    }
    if(name.equals("Submarine")) {
      if (orientation == 'v' || orientation == 'V'){
        tmp.put(new Coordinate(row,column),1);
        tmp.put(new Coordinate(row+1,column),2);
      }
      if (orientation == 'h' || orientation == 'H'){
        tmp.put(new Coordinate(row,column),1);
        tmp.put(new Coordinate(row,column+1),2);
      }
    }
    if(name.equals("Destroyer")) {
      if (orientation == 'v' || orientation == 'V'){
        tmp.put(new Coordinate(row,column),1);
        tmp.put(new Coordinate(row+1,column),2);
        tmp.put(new Coordinate(row+2,column),3);
      }
      if (orientation == 'h' || orientation == 'H'){
        tmp.put(new Coordinate(row,column),1);
        tmp.put(new Coordinate(row,column+1),2);
        tmp.put(new Coordinate(row,column+2),3);
      }
    }
    return tmp;

  }


  public RectangleShip(String name,Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> info,ShipDisplayInfo<T> info_enermy ,Character orientation) {
    super(makeCoords(upperLeft, width, height),info,info_enermy,make_Order(upperLeft,orientation,name));
    this.name = name;
  }
    
  public RectangleShip(String name, Coordinate upperLeft, int width, int height, T data, T onHit, Character orientation) {
    this(name,upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit), new SimpleShipDisplayInfo<T>(null, data),orientation);
  }
  public RectangleShip(Coordinate upperLeft, T data, T onHit) {
    this("testship",upperLeft, 1, 1, new SimpleShipDisplayInfo<T>(data, onHit), new SimpleShipDisplayInfo<T>(null,data),'v');
  }

  public RectangleShip(Coordinate upperLeft, T data, T onHit, Character orientation, String name){
    super(makeCoords_V2(upperLeft, orientation,name), new SimpleShipDisplayInfo<T>(data, onHit), new SimpleShipDisplayInfo<T>(null, data),make_Order(upperLeft,orientation,name));
    this.name = name;
  }



//  public RectangleShip(Coordinate upperLeft, Character Orientation, String name,T data, T onHit ){
//    this.name = name;
//    super(makeCoords_Battleship(upperLeft,Orientation), new SimpleShipDisplayInfo<T>(data, onHit), new SimpleShipDisplayInfo<T>(null, data));
//  }
  
}
