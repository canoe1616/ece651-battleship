/*
<T> is so that we can track different
information for different Views.  For our text views,
it will be a Character.  
 */
package edu.duke.yc407.battleship;

public interface ShipDisplayInfo<T> {
  public T getInfo(Coordinate where, boolean hit);
}
