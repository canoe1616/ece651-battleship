package edu.duke.yc407.battleship;

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
}
