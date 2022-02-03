package edu.duke.yc407.battleship;

public interface Board<T> {
  public int getWidth();

  public int getHeight();

  public boolean tryAddShip(Ship<T> toAdd);
  public T whatIsAt(Coordinate where);
  // private <T> void checkWhatIsAtBoard(Board<T> b, T[][] expected){};
  public Ship<T> fireAt(Coordinate c);
}
