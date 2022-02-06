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
    V2ShipFactory f = new V2ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1, 2), 'U');
    Ship<Character> dst = f.makeBattleship(v1_2);
    checkShip(dst, "Battleships", 'b', new Coordinate(1, 3), new Coordinate(2, 2), new Coordinate(2, 4),new Coordinate(2, 3));
    assertEquals(1,dst.getMyPieces_order().get(new Coordinate(1, 3)));
    assertEquals(2,dst.getMyPieces_order().get(new Coordinate(2, 2)));
    assertEquals(3,dst.getMyPieces_order().get(new Coordinate(2, 3)));
    assertEquals(4,dst.getMyPieces_order().get(new Coordinate(2, 4)));
  }


  @Test
  public void test_V2ShipFactory_Battleship_R() {
    V2ShipFactory f = new V2ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1, 2), 'r');
    Ship<Character> dst = f.makeBattleship(v1_2);
    checkShip(dst, "Battleships", 'b', new Coordinate(2, 3), new Coordinate(2, 2), new Coordinate(3, 2),new Coordinate(1, 2));
    assertEquals(1,dst.getMyPieces_order().get(new Coordinate(1, 3)));
    assertEquals(2,dst.getMyPieces_order().get(new Coordinate(2, 2)));
    assertEquals(3,dst.getMyPieces_order().get(new Coordinate(2, 3)));
    assertEquals(4,dst.getMyPieces_order().get(new Coordinate(2, 4)));

  }

  @Test
  public void test_V2ShipFactory_Battleship_D() {
    V2ShipFactory f = new V2ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1, 2), 'd');
    Ship<Character> dst = f.makeBattleship(v1_2);
    checkShip(dst, "Battleships", 'b', new Coordinate(1, 3), new Coordinate(1, 4), new Coordinate(2, 3),new Coordinate(1, 2));
    assertEquals(1,dst.getMyPieces_order().get(new Coordinate(2, 3)));
    assertEquals(2,dst.getMyPieces_order().get(new Coordinate(1, 4)));
    assertEquals(3,dst.getMyPieces_order().get(new Coordinate(1, 3)));
    assertEquals(4,dst.getMyPieces_order().get(new Coordinate(1, 2)));

  }

  @Test
  public void test_V2ShipFactory_Battleship_L() {
    V2ShipFactory f = new V2ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1, 2), 'L');
    Ship<Character> dst = f.makeBattleship(v1_2);
    checkShip(dst, "Battleships", 'b', new Coordinate(1, 3), new Coordinate(2, 2), new Coordinate(2, 3),new Coordinate(3, 3));
    assertEquals(1,dst.getMyPieces_order().get(new Coordinate(2, 2)));
    assertEquals(2,dst.getMyPieces_order().get(new Coordinate(3, 3)));
    assertEquals(3,dst.getMyPieces_order().get(new Coordinate(2, 3)));
    assertEquals(4,dst.getMyPieces_order().get(new Coordinate(1, 3)));
  }
  @Test
  public void test_V2ShipFactory_Carrier_U() {
    V2ShipFactory f = new V2ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1, 2), 'U');
    Ship<Character> dst = f.makeCarrier(v1_2);
    checkShip(dst, "Carrier", 'c', new Coordinate(1, 2), new Coordinate(2, 2), new Coordinate(3, 2),new Coordinate(4, 2),
            new Coordinate(3,3), new Coordinate(4,3),new Coordinate(5,3));
    assertEquals(1,dst.getMyPieces_order().get(new Coordinate(1, 2)));
    assertEquals(2,dst.getMyPieces_order().get(new Coordinate(2, 2)));
    assertEquals(3,dst.getMyPieces_order().get(new Coordinate(3, 2)));
    assertEquals(4,dst.getMyPieces_order().get(new Coordinate(4, 2)));
    assertEquals(5,dst.getMyPieces_order().get(new Coordinate(3, 3)));
    assertEquals(6,dst.getMyPieces_order().get(new Coordinate(4, 3)));
    assertEquals(7,dst.getMyPieces_order().get(new Coordinate(5, 3)));
  }

  @Test
  public void test_V2ShipFactory_Carrier_R() {
    V2ShipFactory f = new V2ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1, 2), 'r');
    Ship<Character> dst = f.makeCarrier(v1_2);
    checkShip(dst, "Carrier", 'c', new Coordinate(1, 3), new Coordinate(1, 4), new Coordinate(1, 5),new Coordinate(1, 6),
            new Coordinate(2,3), new Coordinate(2,2),new Coordinate(2,4));
    assertEquals(1,dst.getMyPieces_order().get(new Coordinate(1, 6)));
    assertEquals(2,dst.getMyPieces_order().get(new Coordinate(1, 5)));
    assertEquals(3,dst.getMyPieces_order().get(new Coordinate(1, 4)));
    assertEquals(4,dst.getMyPieces_order().get(new Coordinate(1, 3)));
    assertEquals(5,dst.getMyPieces_order().get(new Coordinate(2, 4)));
    assertEquals(6,dst.getMyPieces_order().get(new Coordinate(2, 3)));
    assertEquals(7,dst.getMyPieces_order().get(new Coordinate(2, 2)));

  }

  @Test
  public void test_V2ShipFactory_Carrier_d() {
    V2ShipFactory f = new V2ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1, 2), 'd');
    Ship<Character> dst = f.makeCarrier(v1_2);
    checkShip(dst, "Carrier", 'c', new Coordinate(1, 2), new Coordinate(2, 2), new Coordinate(3, 2),new Coordinate(2, 3),
            new Coordinate(3,3), new Coordinate(4,3),new Coordinate(5,3));
    assertEquals(1,dst.getMyPieces_order().get(new Coordinate(5, 3)));
    assertEquals(2,dst.getMyPieces_order().get(new Coordinate(4, 3)));
    assertEquals(3,dst.getMyPieces_order().get(new Coordinate(3, 3)));
    assertEquals(4,dst.getMyPieces_order().get(new Coordinate(2, 3)));
    assertEquals(5,dst.getMyPieces_order().get(new Coordinate(3, 2)));
    assertEquals(6,dst.getMyPieces_order().get(new Coordinate(2, 2)));
    assertEquals(7,dst.getMyPieces_order().get(new Coordinate(1, 2)));





  }

  @Test
  public void test_V2ShipFactory_Carrier_L() {
    V2ShipFactory f = new V2ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1, 2), 'l');
    Ship<Character> dst = f.makeCarrier(v1_2);
    checkShip(dst, "Carrier", 'c', new Coordinate(1, 4), new Coordinate(1, 5), new Coordinate(1, 6),new Coordinate(2, 2),
            new Coordinate(2,3), new Coordinate(2,4),new Coordinate(2,5));
    assertEquals(1,dst.getMyPieces_order().get(new Coordinate(2, 2)));
    assertEquals(2,dst.getMyPieces_order().get(new Coordinate(2, 3)));
    assertEquals(3,dst.getMyPieces_order().get(new Coordinate(2, 4)));
    assertEquals(4,dst.getMyPieces_order().get(new Coordinate(2, 5)));
    assertEquals(5,dst.getMyPieces_order().get(new Coordinate(1, 4)));
    assertEquals(6,dst.getMyPieces_order().get(new Coordinate(1, 5)));
    assertEquals(7,dst.getMyPieces_order().get(new Coordinate(1, 6)));





  }

  /*test with wrong cases*
   */
  @Test
  public void test_wrong (){
    V2ShipFactory f = new V2ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1, 2), 'v');
    //Ship<Character> dst = f.makeCarrier(v1_2);
    assertThrows(IllegalArgumentException.class,()->f.makeCarrier(v1_2));
  }

  @Test
  public void test_wrong_Battleship(){
    V2ShipFactory f = new V2ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1, 2), 'v');
    //Ship<Character> dst = f.makeCarrier(v1_2);
    assertThrows(IllegalArgumentException.class,()->f.makeBattleship(v1_2));
  }

}
