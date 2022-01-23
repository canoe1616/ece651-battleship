/*
@param T:one for myData and one for onHit
@getInfo: try to confirm whether the coordinate has been hitted or not
 */

package edu.duke.yc407.battleship;

public class SimpleShipDisplayInfo<T> implements ShipDisplayInfo<T> {

  T myData;
  T onHit;

  public SimpleShipDisplayInfo(T my, T hit) {
    this.myData = my;
    this.onHit = hit;
  }

  @Override
  public T getInfo(Coordinate where, boolean hit) {
    // TODO Auto-generated method stub
    if (hit == true) {
      return onHit;
    } else {
      return myData;
    }

  }

}
