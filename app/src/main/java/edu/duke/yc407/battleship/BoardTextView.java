package edu.duke.yc407.battleship;

/**
 * This class handles textual display of
 * a Board (i.e., converting it to a string to show
 * to the user).
 * It supports two ways to display the Board:
 * one for the player's own board, and one for the 
 * enemy's board.
 */

public class BoardTextView {
  /**
   * The Board to display
   */
  private final Board toDisplay;
    /**
   * Constructs a BoardView, given the board it will display.
   * @param toDisplay is the Board to display
   * @throws IllegalArgumentException if the board is larger than 10x26.  
   */
   

  
    public BoardTextView(Board toDisplay) {
    this.toDisplay = toDisplay;
    if (toDisplay.getWidth() > 10 || toDisplay.getHeight() > 26) {
      throw new IllegalArgumentException(
          "Board must be no larger than 10x26, but is " + toDisplay.getWidth() + "x" + toDisplay.getHeight());
    }
  }
   public String displayMyOwnBoard() {
     StringBuilder ans = new StringBuilder();
     ans.append(makeHeader());
     /*
start to develop the board
      */
     // StringBuilder ans = new StringBuilder();
     for(int i = 0 ; i < toDisplay.getHeight(); i++){
       char tmp = (char)(i + 97);
       ans.append(Character.toUpperCase(tmp));
       ans.append("  ");
       for(int j = 0 ; j <toDisplay.getWidth();j++){
         
         if(j == toDisplay.getWidth()-1){
           ans.append(" ");
           ans.append(Character.toUpperCase(tmp));
         }
         else{
           ans.append("|");
           ans.append(" ");
         }
       }
       ans.append("\n");
     }
     ans.append(makeHeader());
     return ans.toString();
     
 }

  /*
    Construct the the first row of the board
   */
  String makeHeader() {
    StringBuilder ans = new StringBuilder("  "); // README shows two spaces at
    String sep=""; //start with nothing to separate, then switch to | to separate
    for (int i = 0; i < toDisplay.getWidth(); i++) {
      ans.append(sep);
      ans.append(i);
      sep = "|";
    }
    ans.append("\n");
    return ans.toString();
  }
}
