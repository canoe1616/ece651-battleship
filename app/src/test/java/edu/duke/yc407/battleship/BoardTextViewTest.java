package edu.duke.yc407.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BoardTextViewTest {


  //  private void emptyBoardHelper(int w, int h, String expectedHeader, String expected body){
  //  Board b1 = new BattleShipBoard(w, h);
  //  BoardTextView view = new BoardTextView(b1);
  //  assertEquals(expectedHeader, view.makeHeader());
  //  String expected = expectedHeader + body + expectedHeader;
  //  assertEquals(expected, view.displayMyOwnBoard());
  // }

 
  
  @Test
  public void test_display_empty_2by2() {
    Board<Character> b1 = new BattleShipBoard<Character>(2, 2,'X');
    BoardTextView view = new BoardTextView(b1);
    String expectedHeader= "  0|1\n";
    assertEquals(expectedHeader, view.makeHeader());
    String expected=
      expectedHeader+
      "A  |  A\n"+
      "B  |  B\n"+
      expectedHeader;
    assertEquals(expected, view.displayMyOwnBoard());
  }
  @Test
  public void test_invalid_board_size() {
    Board<Character> wideBoard = new BattleShipBoard<Character>(11,20,'X');
    Board<Character> tallBoard = new BattleShipBoard<Character>(10,27,'X');
    // BoardTextView view_1 = new BoardTextView(wideBoard);
    //BoardTextView view_2 = new BoardTextView(tallBoard);
    //you should write two assertThrows here
    assertThrows(IllegalArgumentException.class, () -> new BoardTextView(wideBoard));
    assertThrows(IllegalArgumentException.class, () -> new BoardTextView(tallBoard));
    
  }

  @Test
  public void test_display_empty_3by2() {
    Board<Character> b1 = new BattleShipBoard<Character>(3, 2,'X');
      BoardTextView view = new BoardTextView(b1);
      String expectedHeader= "  0|1|2\n";
      assertEquals(expectedHeader, view.makeHeader());
      String expected=
      expectedHeader+
      "A  | |  A\n"+
      "B  | |  B\n"+
      expectedHeader;
    assertEquals(expected, view.displayMyOwnBoard());
  }

@Test
  public void test_display_empty_3by5() {
  Board<Character> b1 = new BattleShipBoard<Character>(5, 3,'X');
      BoardTextView view = new BoardTextView(b1);
      String expectedHeader= "  0|1|2|3|4\n";
      assertEquals(expectedHeader, view.makeHeader());
      String expected=
      expectedHeader+
      "A  | | | |  A\n"+
      "B  | | | |  B\n"+
      "C  | | | |  C\n"+
      expectedHeader;
    assertEquals(expected, view.displayMyOwnBoard());
  }


@Test
public void test_display_ship_3by5(){
  Board<Character> b1 = new BattleShipBoard<Character>(5, 3,'X');
  b1.tryAddShip(new RectangleShip<Character>(new Coordinate(1, 2),'s','*')); 
  BoardTextView view = new BoardTextView(b1);
      String expectedHeader= "  0|1|2|3|4\n";
      assertEquals(expectedHeader, view.makeHeader());
      String expected=
      expectedHeader+
      "A  | | | |  A\n"+
      "B  | |s| |  B\n"+
      "C  | | | |  C\n"+
      expectedHeader;
    assertEquals(expected, view.displayMyOwnBoard());
   

}
  @Test
  public void test_enemy_board(){
    String myView =
      "  0|1|2|3\n" +
      "A  | | |d A\n" +
      "B *|s| |d B\n" +
      "C  | | |d C\n" +
      "  0|1|2|3\n";

    String enemyView =
            "  0|1|2|3\n" +
                    "A  | | |  A\n" +
                    "B s| | |  B\n" +
                    "C X| | |  C\n" +
                    "  0|1|2|3\n";

    BattleShipBoard<Character> b = new BattleShipBoard<Character>(4, 3 ,'X');
    // b.tryAddShip(new RectangleShip<Character>(new Coordinate(1, 1),'s','*'));
    V1ShipFactory f = new V1ShipFactory();
    Placement h1_0 = new Placement(new Coordinate(1, 0), 'H');
    Placement h0_3 = new Placement(new Coordinate(0, 3), 'v');
    Ship<Character> sbr = f.makeSubmarine(h1_0);
   // V1ShipFactory f_1 = new V1ShipFactory();
    Ship<Character> des = f.makeDestroyer(h0_3);
    b.tryAddShip(sbr);
    b.tryAddShip(des);
    Coordinate fire = new Coordinate(1,0);
    Coordinate miss = new Coordinate(2,0);
    b.fireAt(fire);
    b.fireAt(miss);
    BoardTextView view = new BoardTextView(b);
    assertEquals(myView, view.displayMyOwnBoard());
    assertEquals(enemyView, view.displayEnemyBoard());

  }



  @Test
  public void Test_displayMyBoardWithEnemyNextToIt(){
     String myView =
      "  0|1|2|3\n" +
      "A  | | |d A\n" +
      "B  | | |d B\n" +
      "C  | | |d C\n" +
      "  0|1|2|3\n";
      String enemyView =
      "  0|1|2|3\n" +
      "A  | | |  A\n" +
      "B  |s|s|  B\n" +
      "C  | | |  C\n" +
      "  0|1|2|3\n";
      BattleShipBoard<Character> b1 = new BattleShipBoard<Character>(4,3,'X');
      Board<Character> b2 = new BattleShipBoard<Character>(4,3,'X');
      V1ShipFactory f = new V1ShipFactory();
      Ship<Character> d = f.makeDestroyer(new Placement("A3V"));
      Ship<Character> d_2 = f.makeDestroyer(new Placement("B1h"));
      b1.tryAddShip(d);
      b2.tryAddShip(d_2);
      BoardTextView v1 = new BoardTextView(b1);
      BoardTextView v2 = new BoardTextView(b2);
      String expected ="\n"+
   "     my board                 enemy board\n"+
   "  0|1|2|3                    0|1|2|3\n"+
   "A  | | |d A                A  | | |  A\n"+
   "B  | | |d B                B  | | |  B\n"+
   "C  | | |d C                C  | | |  C\n"+
        "  0|1|2|3                    0|1|2|3\n";

      assertEquals(expected,v1.displayMyBoardWithEnemyNextToIt(v2, "my board", "enemy board"));



    
  }
}
