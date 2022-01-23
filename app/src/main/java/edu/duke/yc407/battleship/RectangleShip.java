/*
@param info shows whether this RectangleShip has been hitted or not
@param data shows the ship has not been hitted
@param onhit shows the ship has been hitted
@param ShipDisplayInfo is the class for data and onhit

 */

package edu.duke.yc407.battleship;

import java.util.HashSet;

public class RectangleShip<T> extends BasicShip<T> {

  static HashSet<Coordinate> makeCoords(Coordinate upperLeft, int width, int height){

    HashSet<Coordinate> hashset = new HashSet<>();
    for(int i = upperLeft.getRow(); i < upperLeft.getRow() + height; i++){
      for(int j = upperLeft.getColumn() ; j < upperLeft.getColumn()+ width; j++){
        hashset.add(new Coordinate(i,j));
      }
    }
    return hashset;
  }

  public RectangleShip(Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> info) {
    super(makeCoords(upperLeft, width, height),info);
  }
    
  public RectangleShip(Coordinate upperLeft, int width, int height, T data, T onHit) {
    this(upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit));
  }
  public RectangleShip(Coordinate upperLeft, T data, T onHit) {
    this(upperLeft, 1, 1, data, onHit);
  }
    


  
  
}
