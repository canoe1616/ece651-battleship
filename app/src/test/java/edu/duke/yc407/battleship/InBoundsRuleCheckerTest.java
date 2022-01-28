package edu.duke.yc407.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class InBoundsRuleCheckerTest {
  @Test
  public void test_checkMyRule() {
    InBoundsRuleChecker<Character> t = new InBoundsRuleChecker<>(null);
    Coordinate upperLeft = new Coordinate(1, 2);
    RectangleShip<Character> tmp = new RectangleShip<>("Lucy", upperLeft, 1, 3, 's', '*');
    Board<Character> b1 = new BattleShipBoard<Character>(10, 20, t);
    Board<Character> b2 = new BattleShipBoard<Character>(2, 2, t);
    Board<Character> b3 = new BattleShipBoard<Character>(1, 3, t);
    assertTrue(t.checkMyRule(tmp, b1));
    assertFalse(t.checkMyRule(tmp, b2));
    assertFalse(t.checkMyRule(tmp, b3));
    assertTrue(t.checkPlacement(tmp, b1));
    assertFalse(t.checkPlacement(tmp, b2));
    
    Coordinate upperLeft_2 = new Coordinate(1, 1);
    RectangleShip<Character> tmp_2 = new RectangleShip<>("Lucy", upperLeft_2, 1, 3, 's', '*');
    Board<Character> b_Collision = new BattleShipBoard<Character> (10, 20, t);
    AbstractShipFactory<Character> factory = new V1ShipFactory();
    Ship<Character> s = factory.makeSubmarine(new Placement("A1H"));
    b_Collision.tryAddShip(s);
    assertFalse(t.checkCollision(tmp_2, b_Collision));
  }
}


