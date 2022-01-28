package edu.duke.yc407.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BattleShipBoardTest {
  @Test
  public void test_width_and_height() {
    BattleShipBoard<Character> b1 = new BattleShipBoard<Character>(10, 20);
    assertEquals(10, b1.getWidth());
    assertEquals(20, b1.getHeight());

  }

  @Test
  public void test_invalid_dimensions() {
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, 0));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(0, 20));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, -5));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(-8, 20));
  }

  @Test

  public void test_empty_board() {
    BattleShipBoard<Character> b = new BattleShipBoard<Character>(2, 3);
    Character[][] expect = new Character[2][3];
    checkWhatIsAtBoard(b, expect);
  }

  private <T> void checkWhatIsAtBoard(BattleShipBoard<T> b, T[][] expect) {
    int w = b.getWidth();
    int h = b.getHeight();
    for (int i = 0; i < w; i++) {
      for (int j = 0; j < h; j++) {
        assertEquals(b.whatIsAt(new Coordinate(i, j)), expect[i][j]);
      }
    }

  }

  @Test
  public void test_whatIsAt() {
    BattleShipBoard<Character> b = new BattleShipBoard<Character>(2, 3);
    assertNull(b.whatIsAt(new Coordinate(1, 1)));
    b.tryAddShip(new RectangleShip<Character>(new Coordinate(1, 1),'s','*'));
    assertFalse(b.tryAddShip(new RectangleShip<Character>(new Coordinate(4, 5),'s','*')));
    assertEquals('s', b.whatIsAt(new Coordinate(1, 1)));
    assertNull(b.whatIsAt(new Coordinate(0, 2)));

  }

}
