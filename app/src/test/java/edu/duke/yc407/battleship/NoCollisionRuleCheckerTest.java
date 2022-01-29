package edu.duke.yc407.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NoCollisionRuleCheckerTest {
  @Test
  public void test_checkCollision() {
    NoCollisionRuleChecker<Character> t = new NoCollisionRuleChecker<>(null);
    Coordinate upperLeft_2 = new Coordinate(1, 2);
    Coordinate upperLeft = new Coordinate(1, 1);
    RectangleShip<Character> tmp = new RectangleShip<>("Lucy", upperLeft, 1, 3, 's', '*');
    RectangleShip<Character> tmp_2 = new RectangleShip<>("Lucy", upperLeft_2, 1, 3, 's', '*');
    Board<Character> b1 = new BattleShipBoard<Character>(10, 20, t);
    Board<Character> b2 = new BattleShipBoard<Character>(10, 20, t);
    Board<Character> b3 = new BattleShipBoard<Character>(2, 2, t);
    AbstractShipFactory<Character> factory = new V1ShipFactory();
    Ship<Character> s = factory.makeSubmarine(new Placement("A1H"));
    Ship<Character> s_2 = factory.makeSubmarine(new Placement("A3H"));
    b1.tryAddShip(s);
    b2.tryAddShip(s_2);
    assertTrue(t.checkMyRule(tmp, b1));  
    assertFalse(t.checkCollision(tmp, b1));   
    assertTrue(t.checkCollision(tmp, b2));
    assertFalse(t.checkPlacement(tmp, b1));
    assertFalse(t.checkMyRule(tmp_2, b3));
    Board<Character> b4 = new BattleShipBoard<Character>(1, 3, t);
     assertFalse(t.checkMyRule(tmp_2, b4));
  }

}