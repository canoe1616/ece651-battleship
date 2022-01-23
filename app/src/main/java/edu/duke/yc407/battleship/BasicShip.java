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

  @Override
  public boolean isSunk() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void recordHitAt(Coordinate where) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean wasHitAt(Coordinate where) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public T getDisplayInfoAt(Coordinate where) {
    // TODO Auto-generated method stub
    return myDisplayInfo.getInfo(where, false);
  }

}
