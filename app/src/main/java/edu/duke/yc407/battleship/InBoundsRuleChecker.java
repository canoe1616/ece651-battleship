package edu.duke.yc407.battleship;



public class InBoundsRuleChecker<T> extends PlacementRuleChecker<T> {

  @Override
  protected boolean checkMyRule(Ship<T> theShip, Board<T> theBoard) {
    // TODO Auto-generated method stub
    for(Coordinate c : theShip.getCoordinates()){
      if(c.getRow() > theBoard.getHeight()){
        return false;
      }
      else if(c.getColumn() > theBoard.getWidth()){
        return false;
      }
    }
    return true;
  }
  public InBoundsRuleChecker(PlacementRuleChecker<T> next) {
    super(next);
  }

}









