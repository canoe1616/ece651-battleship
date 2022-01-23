package edu.duke.yc407.battleship;

import java.util.HashMap;
import java.util.HashSet;

public abstract class BasicShip<T> implements Ship<T> {

  protected HashMap<Coordinate, Boolean> myPieces;
  protected ShipDisplayInfo<T> myDisplayInfo;

  public BasicShip(Iterable<Coordinate> where, ShipDisplayInfo<T> myDisplayInfo) {
    this.myDisplayInfo = myDisplayInfo;
    myPieces = new HashMap<Coordinate, Boolean>();
    for (Coordinate c : where) {
      myPieces.put(c, false);
    }
  }

  @Override
  public boolean occupiesCoordinates(Coordinate where) {
    // TODO Auto-generated method stub
    // return where.equals(myLocation);
    return myPieces.containsKey(where);
  }

  protected void checkCoordinateInThisShip(Coordinate c){
    if(occupiesCoordinates(c) ==false){
      throw new IllegalArgumentException("This coordinate is not in the ship.");
    }
  }
  
  @Override
  public boolean isSunk() {
    // TODO Auto-generated method stub
     for(Coordinate c: myPieces.keySet()){
       checkCoordinateInThisShip(c);
       if(myPieces.get(c) == false){
         return false;
       }
     }
    return true;
  }

  @Override
  public void recordHitAt(Coordinate where) {
    // TODO Auto-generated method stub
    checkCoordinateInThisShip(where);
    for(Coordinate c: myPieces.keySet()){
      if(c.equals(where)){
        myPieces.replace(c,true);
      }
    }
  }

  @Override
  public boolean wasHitAt(Coordinate where) {
    // TODO Auto-generated method stub
    checkCoordinateInThisShip(where);
    return myPieces.get(where);
  }

  @Override
  public T getDisplayInfoAt(Coordinate where) {
    // TODO Auto-generated method stub
    checkCoordinateInThisShip(where);
    return myDisplayInfo.getInfo(where, wasHitAt(where));
  }

}
