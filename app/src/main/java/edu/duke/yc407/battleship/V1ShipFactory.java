package edu.duke.yc407.battleship;

public class V1ShipFactory implements AbstractShipFactory<Character> {

  protected Ship<Character> createShip(Placement where, int w, int h, char letter, String name) {

    Coordinate upperLeft = where.getCoordinate();
    Character orien = where.getOrientation();
    RectangleShip<Character> tmp =  new RectangleShip<>(name, upperLeft, w, h, letter, '*');;
   
    if(orien == 'h' || orien =='H'){
      tmp = new RectangleShip<>(name, upperLeft, w, h, letter, '*');
    }
    return tmp;

  };

  @Override
  public Ship<Character> makeSubmarine(Placement where) {
    // TODO Auto-generated method stub
    return createShip(where, 1, 2, 's', "Submarine");
  }

  @Override
  public Ship<Character> makeBattleship(Placement where) {
    // TODO Auto-generated method stub
    return createShip(where, 1, 4, 'b', "Battleship");
  }

  @Override
  public Ship<Character> makeCarrier(Placement where) {
    // TODO Auto-generated method stub
    return createShip(where, 1, 6, 'c', "Carrier");
  }

  @Override
  public Ship<Character> makeDestroyer(Placement where) {
    // TODO Auto-generated method stub
    return createShip(where, 1, 3, 'd', "Destroyer");
  }

}
