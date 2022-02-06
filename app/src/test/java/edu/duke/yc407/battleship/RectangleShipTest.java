package edu.duke.yc407.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class RectangleShipTest {
  @Test
  public void test_with_makeCoords() {
    HashSet<Coordinate> expected = new HashSet<>();
    expected.add(new Coordinate(1, 2));
    expected.add(new Coordinate(2, 2));
    expected.add(new Coordinate(3, 2));

    Coordinate upperLeft = new Coordinate(1, 2);
    HashSet<Coordinate> ans = RectangleShip.makeCoords(upperLeft, 1, 3);
    assertEquals(ans, expected);

  }

  @Test
  public void test_with_new_constructor() {

    Coordinate upperLeft = new Coordinate(1, 2);
    RectangleShip<Character> tmp = new RectangleShip<>("Lucy", upperLeft, 1, 3, 's', '*','v');
    assertTrue(tmp.occupiesCoordinates(new Coordinate(2, 2)));
  }

  @Test
  public void test_with_recordHitAt() {
    Coordinate upperLeft = new Coordinate(1, 2);
    RectangleShip<Character> tmp = new RectangleShip<>("Lucy", upperLeft, 1, 3, 's', '*','v');
    assertThrows(IllegalArgumentException.class, () -> tmp.checkCoordinateInThisShip(new Coordinate(4, 5)));
    assertThrows(IllegalArgumentException.class, () -> tmp.recordHitAt(new Coordinate(4, 5)));
    tmp.recordHitAt(new Coordinate(2, 2));
    assertTrue(tmp.wasHitAt(new Coordinate(2, 2)));
    assertFalse(tmp.isSunk());
  }

  @Test
  public void test_is_Sunk() {
    Coordinate upperLeft = new Coordinate(1, 2);
    RectangleShip<Character> tmp = new RectangleShip<>("Lucy", upperLeft, 1, 3, 's', '*','v');
    tmp.recordHitAt(new Coordinate(1, 2));
    tmp.recordHitAt(new Coordinate(2, 2));
    tmp.recordHitAt(new Coordinate(3, 2));
    assertTrue(tmp.isSunk());
  }

  @Test
  public void test_get_name() {
    Coordinate upperLeft = new Coordinate(1, 2);
    RectangleShip<Character> tmp = new RectangleShip<>("Lucy", upperLeft, 1, 3, 's', '*','v');
    assertEquals("Lucy", tmp.getName());
  }

  @Test
  public void test_getCoordinates(){
    Coordinate upperLeft = new Coordinate(1, 2);
    RectangleShip<Character> tmp = new RectangleShip<>("Lucy", upperLeft, 1, 3, 's', '*','v');
    HashSet<Coordinate> expected = new HashSet<>();
    expected.add(new Coordinate(1, 2));
    expected.add(new Coordinate(2, 2));
    expected.add(new Coordinate(3, 2));
    assertEquals(tmp.getCoordinates(), expected);
  }

  @Test
  public void test_getDisplay(){
    Coordinate upperLeft = new Coordinate(1, 2);
    ShipDisplayInfo<Character> my =  new SimpleShipDisplayInfo<>('a','b');
    ShipDisplayInfo<Character> enermy =  new SimpleShipDisplayInfo<>('c','d');
    RectangleShip<Character> tmp = new RectangleShip<>("Lucy", upperLeft, 1, 3, my, enermy,'v');
    assertEquals('a',tmp.getDisplayInfoAt(new Coordinate(2,2), true));
    assertEquals('c',tmp.getDisplayInfoAt(new Coordinate(2,2), false));
  }
}
