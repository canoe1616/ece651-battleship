package edu.duke.yc407.battleship;

import java.util.HashMap;
import java.util.HashSet;

public interface Board<T> {
  public int getWidth();

  public int getHeight();

  public String tryAddShip(Ship<T> toAdd);
  public T whatIsAtForSelf(Coordinate where);
  //  public T whatIsAt(Coordinate where, boolean isSelf);
  // private <T> void checkWhatIsAtBoard(Board<T> b, T[][] expected){};
  public Ship<T> fireAt(Coordinate c);
  public T whatIsAtForEnemy(Coordinate where);
  public boolean checkAllSunk();
  public Ship<T> get_Ship (Coordinate where);
  public void remove_ship(Ship<T> ship_move, Ship<T>ship_add);
  public HashMap<String , Integer> sonarScanFind (HashSet<Coordinate> diamondList);
}
