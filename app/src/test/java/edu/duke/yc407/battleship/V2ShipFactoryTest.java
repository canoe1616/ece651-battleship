package edu.duke.yc407.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class V2ShipFactoryTest {
  private void checkShip(Ship<Character> testShip, String expectedName, char expectedLetter,
                         Coordinate... expectedLocs) {
    assertEquals(testShip.getName(), expectedName);
    assertEquals(Character.toLowerCase(testShip.getName().charAt(0)), expectedLetter);
    for (Coordinate c : expectedLocs) {
      assertTrue(testShip.occupiesCoordinates(c));
    }
  }
  @Test
  public void test_V2ShipFactory_Battleship_U() {
    V1ShipFactory f = new V2ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1, 2), 'U');
    Ship<Character> dst = f.makeBattleship(v1_2);
    checkShip(dst, "Battleship", 'b', new Coordinate(1, 3), new Coordinate(2, 2), new Coordinate(2, 4),new Coordinate(2, 3));
  }


  @Test
  public void test_V2ShipFactory_Battleship_R() {
    V1ShipFactory f = new V2ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1, 2), 'r');
    Ship<Character> dst = f.makeBattleship(v1_2);
    checkShip(dst, "Battleship", 'b', new Coordinate(2, 3), new Coordinate(2, 2), new Coordinate(3, 2),new Coordinate(1, 2));
  }

  @Test
  public void test_V2ShipFactory_Battleship_D() {
    V1ShipFactory f = new V2ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1, 2), 'd');
    Ship<Character> dst = f.makeBattleship(v1_2);
    checkShip(dst, "Battleship", 'b', new Coordinate(1, 3), new Coordinate(1, 4), new Coordinate(2, 3),new Coordinate(1, 2));
  }

  @Test
  public void test_V2ShipFactory_Battleship_L() {
    V1ShipFactory f = new V2ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1, 2), 'L');
    Ship<Character> dst = f.makeBattleship(v1_2);
    checkShip(dst, "Battleship", 'b', new Coordinate(1, 3), new Coordinate(2, 2), new Coordinate(2, 3),new Coordinate(3, 3));
  }
  @Test
  public void test_V2ShipFactory_Carrier_U() {
    V1ShipFactory f = new V2ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1, 2), 'U');
    Ship<Character> dst = f.makeCarrier(v1_2);
    checkShip(dst, "Carrier", 'c', new Coordinate(1, 2), new Coordinate(2, 2), new Coordinate(3, 2),new Coordinate(4, 2),
            new Coordinate(3,3), new Coordinate(4,3),new Coordinate(5,3));
  }

  @Test
  public void test_V2ShipFactory_Carrier_R() {
    V1ShipFactory f = new V2ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1, 2), 'r');
    Ship<Character> dst = f.makeCarrier(v1_2);
    checkShip(dst, "Carrier", 'c', new Coordinate(1, 3), new Coordinate(1, 4), new Coordinate(1, 5),new Coordinate(1, 6),
            new Coordinate(2,3), new Coordinate(2,2),new Coordinate(2,4));
  }

  @Test
  public void test_V2ShipFactory_Carrier_d() {
    V1ShipFactory f = new V2ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1, 2), 'd');
    Ship<Character> dst = f.makeCarrier(v1_2);
    checkShip(dst, "Carrier", 'c', new Coordinate(1, 2), new Coordinate(2, 2), new Coordinate(3, 2),new Coordinate(2, 3),
            new Coordinate(3,3), new Coordinate(4,3),new Coordinate(5,3));
  }

  @Test
  public void test_V2ShipFactory_Carrier_L() {
    V1ShipFactory f = new V2ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1, 2), 'l');
    Ship<Character> dst = f.makeCarrier(v1_2);
    checkShip(dst, "Carrier", 'c', new Coordinate(1, 4), new Coordinate(1, 5), new Coordinate(1, 6),new Coordinate(2, 2),
            new Coordinate(2,3), new Coordinate(2,4),new Coordinate(2,5));
  }

}
