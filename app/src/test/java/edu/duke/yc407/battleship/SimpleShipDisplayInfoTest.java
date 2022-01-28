package edu.duke.yc407.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SimpleShipDisplayInfoTest {
  @Test
  public void test_with_simple_ship_display() {
    SimpleShipDisplayInfo<Character> info = new SimpleShipDisplayInfo<>('a','c');
    assertEquals('c', info.getInfo(new Coordinate(1,2),true));
    assertEquals('a', info.getInfo(new Coordinate(1,2),false));
  }

}
