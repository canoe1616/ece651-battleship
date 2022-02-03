package edu.duke.yc407.battleship;

import java.util.HashMap;
import java.util.HashSet;

public abstract class BasicShip<T> implements Ship<T> {

  protected HashMap<Coordinate, Boolean> myPieces;
  protected ShipDisplayInfo<T> myDisplayInfo;
  protected ShipDisplayInfo<T> enemyDisplayInfo;
  
  public BasicShip(Iterable<Coordinate> where, ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> enemyDisplayInfo) {
    this.myDisplayInfo = myDisplayInfo;
    this.enemyDisplayInfo = enemyDisplayInfo;
    myPieces = new HashMap<Coordinate, Boolean>();
    for (Coordinate c : where) {
      myPieces.put(c, false);
    }
  }

  // public HashMap<Coordinate, Boolean> get_myPieces(){
  // return myPieces;
  // }
  @Override
  public Iterable<Coordinate> getCoordinates() {
    return myPieces.keySet();

  }

  @Override
  public boolean occupiesCoordinates(Coordinate where) {
    // TODO Auto-generated method stub
    // return where.equals(myLocation);
    return myPieces.containsKey(where);
  }

  protected void checkCoordinateInThisShip(Coordinate c) {
    if (occupiesCoordinates(c) == false) {
      throw new IllegalArgumentException("This coordinate is not in the ship.");
    }
  }

  @Override
  public boolean isSunk() {
    // TODO Auto-generated method stub
    for (Coordinate c : myPieces.keySet()) {
      checkCoordinateInThisShip(c);
      if (myPieces.get(c) == false) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void recordHitAt(Coordinate where) {
    // TODO Auto-generated method stub
    checkCoordinateInThisShip(where);
    for (Coordinate c : myPieces.keySet()) {
      if (c.equals(where)) {
        myPieces.replace(c, true);
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
  public T getDisplayInfoAt(Coordinate where,boolean myShip) {
    // TODO Auto-generated method stub
    checkCoordinateInThisShip(where);
    if(myShip == true){
    return myDisplayInfo.getInfo(where, wasHitAt(where));
  }
    else{
      return enemyDisplayInfo.getInfo(where, wasHitAt(where));
    }
  }
}
