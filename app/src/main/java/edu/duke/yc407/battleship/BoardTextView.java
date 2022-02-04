package edu.duke.yc407.battleship;

import java.util.function.Function;

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
  private final Board<Character> toDisplay;
    /**
   * Constructs a BoardView, given the board it will display.
   * @param toDisplay is the Board to display
   * @throws IllegalArgumentException if the board is larger than 10x26.  
   */
   

  
    public BoardTextView(Board<Character> toDisplay) {
    this.toDisplay = toDisplay;
    if (toDisplay.getWidth() > 10 || toDisplay.getHeight() > 26) {
      throw new IllegalArgumentException(
          "Board must be no larger than 10x26, but is " + toDisplay.getWidth() + "x" + toDisplay.getHeight());
    }
  }
   public String displayMyOwnBoard() {

     return displayAnyBoard((c)->toDisplay.whatIsAtForSelf(c));
 }

  public String displayEnemyBoard(){

      return displayAnyBoard((c)->toDisplay.whatIsAtForEnemy(c));
  }

  protected String displayAnyBoard(Function<Coordinate, Character> getSquareFn){
        StringBuilder ans = new StringBuilder();
     ans.append(makeHeader());
     /*
start to develop the board
      */
     for(int i = 0 ; i < toDisplay.getHeight(); i++){
       char tmp = (char)(i + 97);
       ans.append(Character.toUpperCase(tmp));
       ans.append(" ");
       for(int j = 0 ; j < toDisplay.getWidth();j++){
         
         if(j == toDisplay.getWidth() - 1){
             if(toDisplay.whatIsAtForSelf(new Coordinate(i, j)) != null){
                 ans.append(toDisplay.whatIsAtForSelf(new Coordinate(i, j)));
                 ans.append(" ");
             }
             else{
                 ans.append("  ");
             }
           ans.append(Character.toUpperCase(tmp));
         }
         else{
           if(toDisplay.whatIsAtForSelf(new Coordinate(i, j)) != null){
             ans.append(toDisplay.whatIsAtForSelf(new Coordinate(i, j)));
             }
           else{
           ans.append(" ");
           }
           ans.append("|");
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
  
  public String displayMyBoardWithEnemyNextToIt(BoardTextView enemyView, String myHeader, String enemyHeader) {
    String[] myLines = displayMyOwnBoard().split("\n");
    String[] enemyLines = enemyView.displayEnemyBoard().split("\n");

    StringBuilder ans = new StringBuilder();
    int header_start =5;
    int header_space = 2 * toDisplay.getWidth() + 22 - myHeader.length() - header_start;
    int string_space = 16;
    int string_start_space = 18;
    
    /*start to put all things in the stringBuilder */
    ans.append("\n");
    ans.append(helper(header_start));
    ans.append(myHeader);
    ans.append(helper(header_space));
    ans.append(enemyHeader);
    ans.append("\n");

     ans.append(myLines[0]);
     ans.append(helper(string_start_space));
     ans.append(enemyLines[0]);
     ans.append("\n");

    
     /*start to tackle the file itself*/
    for(int i=1; i<myLines.length-1;i++){
      ans.append(myLines[i]);
      ans.append(helper(string_space));
      ans.append(enemyLines[i]);
      ans.append("\n");
    }

      ans.append(myLines[myLines.length-1]);
      ans.append(helper(string_start_space));
      ans.append(enemyLines[myLines.length-1]);
      ans.append("\n");
    return ans.toString();
  }

  public String helper(int k){
    StringBuilder ans = new StringBuilder();
    for(int i = 0 ; i < k ; ++i){
      ans.append(" ");
    }
    return ans.toString();
  }
}
