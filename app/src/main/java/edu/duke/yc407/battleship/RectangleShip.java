/*
@param info shows whether this RectangleShip has been hitted or not
@param data shows the ship has not been hitted
@param onhit shows the ship has been hitted
@param ShipDisplayInfo is the class for data and onhit

 */

package edu.duke.yc407.battleship;

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
    


  
  
}
