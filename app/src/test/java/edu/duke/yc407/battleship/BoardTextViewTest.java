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
      "B  | | |s|  B\n"+
      "C  | | | |  C\n"+
      expectedHeader;
    assertEquals(expected, view.displayMyOwnBoard());
   

}
  
}
