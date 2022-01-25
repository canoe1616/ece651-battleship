/*
This class should check the rule that theShip does not collide with anything else
on theBoard (that all the squares it needs are empty).
  */
package edu.duke.yc407.battleship;


public class NoCollisionRuleChecker<T>  extends PlacementRuleChecker<T> {
//public class NoCollisionRuleChecker<T>{
  //  new NoCollisionRuleChecker<>(new InBoundsRuleChecker<T>(null))


  @Override 
  protected boolean checkCollision(Ship<T> theShip, Board<T> theBoard){

    for(Coordinate c : theShip.getCoordinates()){
      if(theBoard.whatIsAt(c)!= null){
        return false;
      }
    }
    return true;
  }

  @Override
  protected boolean checkMyRule(Ship<T> theShip, Board<T> theBoard) {
    // TODO Auto-generated method stub
    for(Coordinate c : theShip.getCoordinates()){
      if(c.getRow() < 0 ||c.getRow() > theBoard.getHeight()){
        return false;
      }
      else if(c.getColumn() <0 ||c.getColumn() > theBoard.getWidth()){
        return false;
      }
    }
    return true;
  }



  public NoCollisionRuleChecker(PlacementRuleChecker<T> next){
    super(next);
  }

}