package edu.duke.yc407.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BattleShipBoardTest {
  @Test
  public void test_width_and_height() {
    BattleShipBoard<Character> b1 = new BattleShipBoard<Character>(10, 20, 'X');
    assertEquals(10, b1.getWidth());
    assertEquals(20, b1.getHeight());

  }

  @Test
  public void test_invalid_dimensions() {
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, 0, 'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(0, 20, 'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, -5, 'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(-8, 20, 'X'));
  }

  @Test

  public void test_empty_board() {
    BattleShipBoard<Character> b = new BattleShipBoard<Character>(2, 3, 'X');
    Character[][] expect = new Character[2][3];
    checkWhatIsAtBoard(b, expect);
  }

  private <T> void checkWhatIsAtBoard(BattleShipBoard<T> b, T[][] expect) {
    int w = b.getWidth();
    int h = b.getHeight();
    for (int i = 0; i < w; i++) {
      for (int j = 0; j < h; j++) {
        assertEquals(b.whatIsAtForSelf(new Coordinate(i, j)), expect[i][j]);
      }
    }

  }

  @Test
  public void test_whatIsAt() {
    BattleShipBoard<Character> b = new BattleShipBoard<Character>(2, 3, 'X');
    assertNull(b.whatIsAtForSelf(new Coordinate(1, 1)));
    b.tryAddShip(new RectangleShip<Character>(new Coordinate(1, 1), 's', '*'));
    assertEquals("That placement is invalid: the ship goes off the bottom of the board.",b.tryAddShip(new RectangleShip<Character>(new Coordinate(4, 5), 's', '*')));
    assertEquals('s', b.whatIsAtForSelf(new Coordinate(1, 1)));
    assertNull(b.whatIsAtForSelf(new Coordinate(0, 2)));

  }

  @Test
  public void test_fireAt() {
    BattleShipBoard<Character> b = new BattleShipBoard<Character>(5, 5 ,'X');
    // b.tryAddShip(new RectangleShip<Character>(new Coordinate(1, 1),'s','*'));
    V1ShipFactory f = new V1ShipFactory();
    Placement h1_2 = new Placement(new Coordinate(1, 2), 'H');
    Ship<Character> sbr = f.makeSubmarine(h1_2);
    b.tryAddShip(sbr);
    assertSame(sbr, b.fireAt(new Coordinate(1, 2)));
    //V1ShipFactory f_1 = new V1ShipFactory();
    //Placement h2_2 = new Placement(new Coordinate(1, 2), 'H');
    //Ship<Character> sbr_2 = f_1.makeSubmarine(h2_2);
    //b.tryAddShip(sbr_2);
    b.fireAt(new Coordinate(1,3));
    assertEquals('s', b.whatIsAtForEnemy(new Coordinate(1,3)));
    assertTrue(sbr.isSunk());

    // assertNull(b.fireAt(new Coordinate(2,2)));
    //Placement h2_2 = new Placement(new Coordinate(2, 2), 'H');
    b.fireAt(new Coordinate(2,2));
    assertEquals('X',b.whatIsAtForEnemy(new Coordinate(2,2)));
    Coordinate c = new Coordinate(3,3);
    assertNull( b.whatIsAtForEnemy(c));
    
  }

  @Test
  public void test_all_Sunk_or_Not() {
    BattleShipBoard<Character> b = new BattleShipBoard<Character>(5, 5 ,'X');
    V1ShipFactory f = new V1ShipFactory();
    Placement h1_2 = new Placement(new Coordinate(1, 2), 'H');
    Ship<Character> sbr = f.makeSubmarine(h1_2);
    b.tryAddShip(sbr);
    b.fireAt(new Coordinate(1, 2));
    b.fireAt(new Coordinate(1,3));
    assertTrue(b.checkAllSunk());

    Placement h2_3 = new Placement(new Coordinate(2, 3), 'H');
    Ship<Character> sbr_2 = f.makeSubmarine(h2_3);
    b.tryAddShip(sbr_2);
    assertFalse(b.checkAllSunk());
  }
  }


