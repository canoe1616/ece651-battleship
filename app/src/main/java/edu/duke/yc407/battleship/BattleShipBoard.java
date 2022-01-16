
/**
   * Constructs a BattleShipBoard with the specified width
   * and height
   * @param w is the width of the newly constructed board.
   * @param h is the height of the newly constructed board.
   * @throws IllegalArgumentException if the width or height are less than or equal to zero.
   */


package edu.duke.yc407.battleship;

public class BattleShipBoard implements Board{

  private int width;
    
  public int getWidth(){
    return width;
  }
  private int height;
  public int getHeight(){
    return height;
  }
  public BattleShipBoard(int w, int h){
    if (w <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's width must be positive but is " + w);
    }
    if (h <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's height must be positive but is " + h);
    }
    this.width = w;
    this.height = h;
  }

  

}


  

  

