package edu.duke.yc407.battleship;

public abstract class PlacementRuleChecker<T>{

  private final PlacementRuleChecker<T> next;

  public PlacementRuleChecker(PlacementRuleChecker<T> next) {
    this.next = next;
 }

  protected abstract String checkMyRule(Ship<T> theShip, Board<T> theBoard);

   protected abstract String  checkCollision(Ship<T> theShip,Board<T> theBoard);
  
  public boolean checkPlacement (Ship<T> theShip, Board<T> theBoard) {
    //if we fail our own rule: stop the placement is not legal
    if (checkMyRule(theShip, theBoard)!=null) {
      return false;
    }
     if(checkCollision(theShip, theBoard) != null){
      return false;
    }
    
    //other wise, ask the rest of the chain.
    if (next != null) {
      return next.checkPlacement(theShip, theBoard); 
    }
    //if there are no more rules, then the placement is legal
    return true;
  }
}
