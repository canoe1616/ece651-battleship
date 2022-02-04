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
    Board<Character> b1 = new BattleShipBoard<Character>(10, 20, t, 'X');
    Board<Character> b2 = new BattleShipBoard<Character>(10, 20, t, 'X');
    Board<Character> b3 = new BattleShipBoard<Character>(2, 2, t, 'X');
    AbstractShipFactory<Character> factory = new V1ShipFactory();
    Ship<Character> s = factory.makeSubmarine(new Placement("A1v"));
    Ship<Character> s_2 = factory.makeSubmarine(new Placement("A3H"));
    b1.tryAddShip(s);
    b2.tryAddShip(s_2);
    assertNull(t.checkMyRule(tmp, b1));  
    assertEquals("That placement is invalid: the ship overlaps another ship.",t.checkCollision(tmp, b1));   
    assertNull(t.checkCollision(tmp, b2));
    assertEquals("That placement is invalid: the ship overlaps another ship.",t.checkPlacement(tmp, b1));
    assertEquals("That placement is invalid: the ship goes off the bottom of the board.",t.checkMyRule(tmp_2, b3));
    Board<Character> b4 = new BattleShipBoard<Character>(1, 3, t,'X');
    assertEquals("That placement is invalid: the ship goes off the right of the board.",t.checkMyRule(tmp_2, b4));

    RectangleShip<Character> tmp_3 = new RectangleShip<>("Lucy", new Coordinate(-5, 2), 1, 1, 's', '*');
    assertEquals("That placement is invalid: the ship goes off the top of the board.",t.checkMyRule(tmp_3, b2));

    RectangleShip<Character> tmp_4 = new RectangleShip<>("Lucy", new Coordinate(1, -6), 1, 1, 's', '*');
    assertEquals("That placement is invalid: the ship goes off the left of the board.",t.checkMyRule(tmp_4, b2));


  }

}
