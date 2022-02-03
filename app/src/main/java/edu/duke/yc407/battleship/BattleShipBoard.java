
/**
   * Constructs a BattleShipBoard with the specified width
   * and height
   * @param w is the width of the newly constructed board.
   * @param h is the height of the newly constructed board.
   * @throws IllegalArgumentException if the width or height are less than or equal to zero.
   */

package edu.duke.yc407.battleship;

//import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;

public class BattleShipBoard<T> implements Board<T> {

  private final int width;
  final ArrayList<Ship<T>> myShips;
  private final PlacementRuleChecker<T> placementChecker;
  private HashSet<Coordinate> enemyMisses;
  final T missInfo;
  
  public int getWidth() {
    return width;
  }

  private final int height;

  public int getHeight() {
    return height;
  }

  /*
   * the default placementChecker in BattleShipBoard to use the two checkers
   * combined.
   */

  //to be asked
  public BattleShipBoard(int w, int h, T miss) {
    this(w, h, new InBoundsRuleChecker<T>(new NoCollisionRuleChecker<>(null)),miss);
  }

  public BattleShipBoard(int w, int h, PlacementRuleChecker<T> placementChecker,T miss) {
    if (w <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's width must be positive but is " + w);
    }
    if (h <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's height must be positive but is " + h);
    }
    this.width = w;
    this.height = h;
    this.myShips = new ArrayList<Ship<T>>();
    this.placementChecker = placementChecker;
    this.enemyMisses = new HashSet<>();
    this.missInfo = miss;
  }

  /* tryAddShip use the placementChecker */
  public boolean tryAddShip(Ship<T> toAdd) {
    Boolean error_not = placementChecker.checkPlacement(toAdd, this);
    if (error_not == false) {
      return false;
    } else {
      myShips.add(toAdd);
      return true;
    }
  }

  public T whatIsAtForSelf(Coordinate where) {

    return whatIsAt(where, true);
  }

  protected T whatIsAt(Coordinate where, boolean isSelf) {
    for (Ship<T> s : myShips) {
      if (s.occupiesCoordinates(where)) {
        return s.getDisplayInfoAt(where, isSelf);
      }
    }
    return null;
  }

  public T whatIsAtForEnemy(Coordinate where) {
    return whatIsAt(where, false);
  }

  public Ship<T> fireAt(Coordinate c) {
    if (whatIsAtForSelf(c) != null) {
      for (Ship<T> s : myShips) {
        if (s.occupiesCoordinates(c)) {
          s.recordHitAt(c);
          return s;
        }
      }
    }
    enemyMisses.add(c);
    return null;
  }

}
