package edu.duke.yc407.battleship;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TextPlayerTest {
  @Test
  public void test_read_placement() throws IOException {
    StringReader sr = new StringReader("B2V\nC8H\na4v\n");
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(bytes, true);
    Board<Character> b1 = new BattleShipBoard<Character>(10, 20,'X');
    // ArrayList<String> shipsToPlace = new ArrayList<>();
    // HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns = new
    // HashMap<>();
    TextPlayer player = new TextPlayer("A", b1, new BufferedReader(sr), ps, new V1ShipFactory());
    String prompt = "Please enter a location for a ship:";
    Placement[] expected = new Placement[3];
    expected[0] = new Placement(new Coordinate(1, 2), 'V');
    expected[1] = new Placement(new Coordinate(2, 8), 'H');
    expected[2] = new Placement(new Coordinate(0, 4), 'V');
    for (int i = 0; i < expected.length; i++) {
      Placement p = player.readPlacement(prompt);
      assertEquals(p, expected[i]);
      assertEquals(prompt + "\n", bytes.toString());
      bytes.reset();
    }

  }

  // helper fucntion
  private TextPlayer createTextPlayer(int w, int h, String inputData, OutputStream bytes) {
    BufferedReader input = new BufferedReader(new StringReader(inputData));
    PrintStream output = new PrintStream(bytes, true);
    Board<Character> board = new BattleShipBoard<Character>(w, h,'X');
    V1ShipFactory shipFactory = new V1ShipFactory();
    // ArrayList<String> shipsToPlace = new ArrayList<>();
    // HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns = new
    // HashMap<>();
    return new TextPlayer("A", board, input, output, shipFactory);
  }

  @Test
  void test_do_placement() throws IOException {

    String prompt = "Player A where do you want to place a Destroyer?";
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();

    TextPlayer player = createTextPlayer(5, 5, "B2V\nA1V\nA3V\n", bytes);
    V1ShipFactory shipFactory = new V1ShipFactory();
    // player.doOnePlacement("Destroyer",player.shipCreationFns.get("Destroyer"));
    player.doOnePlacement("Destroyer", (p) -> shipFactory.makeDestroyer(p));
    String expectedHeader = "  0|1|2|3|4\n";
    String expected = prompt + "\n" + expectedHeader + "A  | | | |  A\n" + "B  | |d| |  B\n" + "C  | |d| |  C\n"
        + "D  | |d| |  D\n" + "E  | | | |  E\n" + expectedHeader;
    assertEquals(expected, bytes.toString());
    bytes.reset();
    player.doOnePlacement("Destroyer", (p) -> shipFactory.makeDestroyer(p));
  }

  /*A helper function*/
  //ByteArrayOutputStream bytes_test = new ByteArrayOutputStream();
  //Board<Character> board_test = new BattleShipBoard<Character>(10, 10);
  //PrintStream output = new PrintStream(bytes_test, true);
  //public String helper(int w, int h, String Placement, String name) throws IOException{
    //ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    //TextPlayer player = createTextPlayer(w, h, Placement, bytes_test);
  //  V1ShipFactory shipFactory = new V1ShipFactory();
  //  BufferedReader input = new BufferedReader(new StringReader(Placement));
    //PrintStream output = new PrintStream(bytes_test, true);
  //  TextPlayer player = new TextPlayer("A", board_test, input, output, shipFactory);
    
    //V1ShipFactory shipFactory = new V1ShipFactory();
  // if(name =="Submarine"){
  //   player.doOnePlacement("Submarine", (p) -> shipFactory.makeSubmarine(p));
  // }
  // if(name =="Destroyer"){
  //   player.doOnePlacement("Destroyer", (p) -> shipFactory.makeDestroyer(p));
  // }
  // if(name == "Carriers"){
  //   player.doOnePlacement("Carriers", (p) -> shipFactory.makeCarrier(p));
  // }
  // if(name == "Battleships"){
  //  player.doOnePlacement("Battleships", (p) -> shipFactory.makeBattleship(p));
  // }
  //   return bytes_test.toString();
  // }


@Test
void test_read_coordinate() throws IOException{
  //Test IOException
  ByteArrayOutputStream bytes = new ByteArrayOutputStream();
  TextPlayer playerIOE = createTextPlayer(4, 4, "123\n", bytes);
  String prompt = "Player " +playerIOE.name + " Where would you like to fire at?";
  assertThrows(IllegalArgumentException.class,()->playerIOE.readCoordinate(prompt));
  bytes.reset();


// 待跟郭郭讨论
//  TextPlayer playerBound_2 = createTextPlayer(4, 22, "Z3\ng3\n", bytes);
//  String prompt_4 = "Player " +playerBound_2.name + " Where would you like to fire at?";
//  playerBound_2.readCoordinate(prompt_4);
//  String expected = "Player A Where would you like to fire at?\n"+
//          "Out of board boundary\n"+
//          "Player A Where would you like to fire at?\n";
//  assertEquals(expected, bytes.toString());
//  bytes.reset();
//

  //Test for correct cases
  TextPlayer playercorrect= createTextPlayer(4, 22, "A2\ng3\n", bytes);
  String prompt_3 = "Player " +playercorrect.name + " Where would you like to fire at?";
  assertEquals(0, playercorrect.readCoordinate(prompt_3).getRow());
}

//  @Test
//  void test_play_one_turn() throws IOException{
//     Board<Character> b = new BattleShipBoard(4,3,'X');
//     V1ShipFactory f = new V1ShipFactory();
//     Ship<Character> s = f.makeDestroyer(new Placement("B1h"));
//     b.tryAddShip(s);
//     ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//     TextPlayer player = createTextPlayer(4, 3, "B2\nc2\nb1\n", bytes);
//     player.playOneTurn(b, "B");
//     player.playOneTurn(b, "B");
//     bytes.reset();
//     player.playOneTurn(b, "B");
//     String expected = "Player A's turn:\n\n"+
//         "     Your ocean               Player B's ocean\n"+
//          "  0|1|2|3                    0|1|2|3\n"+
//   "A  | | |  A                A  | | |  A\n"+
//   "B  | | |  B                B  | |d|  B\n"+
//   "C  | | |  C                C  | |X|  C\n"+
//        "  0|1|2|3                    0|1|2|3\n"+
//       "\nPlease enter the fire location:\n"+
//         "You hit a destroyer!\n";
//
//     assertEquals(expected, bytes.toString());
//  }






  @Test
  void test_doPlacementPhase() throws IOException{  
    String prompt = "Player A where do you want to place a Destroyer";
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 10, "A2V\nA3V\nB4v\nB5v\nB6v\nC2v\nC3v\nC9V\nA7v\nA8v\n", bytes);
    player.doPlacementPhase();

    ByteArrayOutputStream bytes_1 = new ByteArrayOutputStream();
TextPlayer player_1 = createTextPlayer(10, 10, "A1V\n", bytes_1);
V1ShipFactory shipFactory = new V1ShipFactory();
player_1.doOnePlacement("Submarine", (p) -> shipFactory.makeSubmarine(p));

//ByteArrayOutputStream bytes_2 = new ByteArrayOutputStream();
//TextPlayer player_2 = createTextPlayer(10, 10, "A2V\n", bytes_2);
//V1ShipFactory shipFactory = new V1ShipFactory();
//player_2.doOnePlacement("Submarine", (p) -> shipFactory.makeSubmarine(p));


//    ByteArrayOutputStream bytes_3 = new ByteArrayOutputStream();
//  TextPlayer player_3 = createTextPlayer(10, 10, "B3V\n", bytes_3);
//  V1ShipFactory shipFactory = new V1ShipFactory();
//  player_3.doOnePlacement("Destroyer", (p) -> shipFactory.makeDestroyer(p));
    
    // String expectedHeader = "  0|1|2|3|4\n";
    //String expected = expectedHeader+ "A  | | | |  A\n"+ "B  | | | |  B\n"+ "C  | | | |  C\n"+ "D  | | | |  D\n"+"E  | | | |  E\n"+ expectedHeader +prompt + "\n" + expectedHeader + "A  | | | |  A\n" + "B  | | |d|  B\n" + "C  | | |d|  C\n" + "D  | | |d|  D\n" + "E  | | | |  E\n" + expectedHeader;
    String expected = "  0|1|2|3|4|5|6|7|8|9\n"+
      "A  | | | | | | | | |  A\n"+
      "B  | | | | | | | | |  B\n"+
      "C  | | | | | | | | |  C\n"+
      "D  | | | | | | | | |  D\n"+
      "E  | | | | | | | | |  E\n"+
      "F  | | | | | | | | |  F\n"+
      "G  | | | | | | | | |  G\n"+
      "H  | | | | | | | | |  H\n"+
      "I  | | | | | | | | |  I\n"+
      "J  | | | | | | | | |  J\n"+
      "  0|1|2|3|4|5|6|7|8|9\n"+
      //      helper(10, 10, "A1V\n","Submarine")+
      //helper(10, 10, "A2V\n","Submarine")+
      //helper(10, 10, "B3V\n","Destroyer")+
      //helper(10,10,"B4V\n","Destroyer")+
      //helper(10,10,"B5V\n","Destroyer")+
      //helper(10,10,"C1V\n","Battleships")+
      //helper(10,10,"C2V\n","Battleships")+
      //helper(10,10,"C8V\n","Battleships")+
      //helper(10,10,"A6V\n","Carriers")+
      //helper(10,10,"A7V\n","Carriers");
      "Player A where do you want to place a Submarine?\n"+
      "  0|1|2|3|4|5|6|7|8|9\n"+
      "A  | |s| | | | | | |  A\n"+
      "B  | |s| | | | | | |  B\n"+
      "C  | | | | | | | | |  C\n"+
      "D  | | | | | | | | |  D\n"+
      "E  | | | | | | | | |  E\n"+
      "F  | | | | | | | | |  F\n"+
      "G  | | | | | | | | |  G\n"+
      "H  | | | | | | | | |  H\n"+
      "I  | | | | | | | | |  I\n"+
      "J  | | | | | | | | |  J\n"+
      "  0|1|2|3|4|5|6|7|8|9\n"+
      "Player A where do you want to place a Submarine?\n"+
      "  0|1|2|3|4|5|6|7|8|9\n"+
      "A  | |s|s| | | | | |  A\n"+
      "B  | |s|s| | | | | |  B\n"+
      "C  | | | | | | | | |  C\n"+
      "D  | | | | | | | | |  D\n"+
      "E  | | | | | | | | |  E\n"+
      "F  | | | | | | | | |  F\n"+
      "G  | | | | | | | | |  G\n"+
      "H  | | | | | | | | |  H\n"+
      "I  | | | | | | | | |  I\n"+
      "J  | | | | | | | | |  J\n"+
      "  0|1|2|3|4|5|6|7|8|9\n"+
      "Player A where do you want to place a Destroyer?\n"+
      "  0|1|2|3|4|5|6|7|8|9\n"+
      "A  | |s|s| | | | | |  A\n"+
      "B  | |s|s|d| | | | |  B\n"+
      "C  | | | |d| | | | |  C\n"+
      "D  | | | |d| | | | |  D\n"+
      "E  | | | | | | | | |  E\n"+                                                                                     "F  | | | | | | | | |  F\n"+                                                                           
      "G  | | | | | | | | |  G\n"+                                                                               
      "H  | | | | | | | | |  H\n"+                                                                               
     "I  | | | | | | | | |  I\n"+                                                                                     "J  | | | | | | | | |  J\n"+                                                                                     "  0|1|2|3|4|5|6|7|8|9\n"+                                                                                        "Player A where do you want to place a Destroyer?\n"+ 
      "  0|1|2|3|4|5|6|7|8|9\n"+
      "A  | |s|s| | | | | |  A\n"+
      "B  | |s|s|d|d| | | |  B\n"+
      "C  | | | |d|d| | | |  C\n"+
      "D  | | | |d|d| | | |  D\n"+
      "E  | | | | | | | | |  E\n"+
      "F  | | | | | | | | |  F\n"+
      "G  | | | | | | | | |  G\n"+
      "H  | | | | | | | | |  H\n"+
      "I  | | | | | | | | |  I\n"+
      "J  | | | | | | | | |  J\n"+
      "  0|1|2|3|4|5|6|7|8|9\n"+
      "Player A where do you want to place a Destroyer?\n"+
      "  0|1|2|3|4|5|6|7|8|9\n"+
      "A  | |s|s| | | | | |  A\n"+
      "B  | |s|s|d|d|d| | |  B\n"+
      "C  | | | |d|d|d| | |  C\n"+
      "D  | | | |d|d|d| | |  D\n"+
      "E  | | | | | | | | |  E\n"+
      "F  | | | | | | | | |  F\n"+
     "G  | | | | | | | | |  G\n"+
      "H  | | | | | | | | |  H\n"+
      "I  | | | | | | | | |  I\n"+
      "J  | | | | | | | | |  J\n"+
      "  0|1|2|3|4|5|6|7|8|9\n"+
      "Player A where do you want to place a Battleships?\n"+
      "  0|1|2|3|4|5|6|7|8|9\n"+
      "A  | |s|s| | | | | |  A\n"+
      "B  | |s|s|d|d|d| | |  B\n"+
      "C  | |b| |d|d|d| | |  C\n"+
      "D  | |b| |d|d|d| | |  D\n"+
      "E  | |b| | | | | | |  E\n"+
      "F  | |b| | | | | | |  F\n"+
      "G  | | | | | | | | |  G\n"+
      "H  | | | | | | | | |  H\n"+
      "I  | | | | | | | | |  I\n"+
      "J  | | | | | | | | |  J\n"+
      "  0|1|2|3|4|5|6|7|8|9\n"+
      "Player A where do you want to place a Battleships?\n"+
      "  0|1|2|3|4|5|6|7|8|9\n"+
      "A  | |s|s| | | | | |  A\n"+
      "B  | |s|s|d|d|d| | |  B\n"+
      "C  | |b|b|d|d|d| | |  C\n"+
      "D  | |b|b|d|d|d| | |  D\n"+
      "E  | |b|b| | | | | |  E\n"+
      "F  | |b|b| | | | | |  F\n"+
      "G  | | | | | | | | |  G\n"+
      "H  | | | | | | | | |  H\n"+
      "I  | | | | | | | | |  I\n"+
      "J  | | | | | | | | |  J\n"+
      "  0|1|2|3|4|5|6|7|8|9\n"+
      "Player A where do you want to place a Battleships?\n"+
      "  0|1|2|3|4|5|6|7|8|9\n"+
      "A  | |s|s| | | | | |  A\n"+
      "B  | |s|s|d|d|d| | |  B\n"+
      "C  | |b|b|d|d|d| | |b C\n"+
      "D  | |b|b|d|d|d| | |b D\n"+
      "E  | |b|b| | | | | |b E\n"+
      "F  | |b|b| | | | | |b F\n"+
      "G  | | | | | | | | |  G\n"+
      "H  | | | | | | | | |  H\n"+
      "I  | | | | | | | | |  I\n"+
      "J  | | | | | | | | |  J\n"+
      "  0|1|2|3|4|5|6|7|8|9\n"+
      "Player A where do you want to place a Carriers?\n"+
      "  0|1|2|3|4|5|6|7|8|9\n"+
      "A  | |s|s| | | |c| |  A\n"+
      "B  | |s|s|d|d|d|c| |  B\n"+
      "C  | |b|b|d|d|d|c| |b C\n"+
      "D  | |b|b|d|d|d|c| |b D\n"+
      "E  | |b|b| | | |c| |b E\n"+
      "F  | |b|b| | | |c| |b F\n"+
      "G  | | | | | | | | |  G\n"+
      "H  | | | | | | | | |  H\n"+
      "I  | | | | | | | | |  I\n"+
      "J  | | | | | | | | |  J\n"+
      "  0|1|2|3|4|5|6|7|8|9\n"+
      "Player A where do you want to place a Carriers?\n"+
      "  0|1|2|3|4|5|6|7|8|9\n"+
      "A  | |s|s| | | |c|c|  A\n"+
      "B  | |s|s|d|d|d|c|c|  B\n"+
      "C  | |b|b|d|d|d|c|c|b C\n"+
      "D  | |b|b|d|d|d|c|c|b D\n"+
      "E  | |b|b| | | |c|c|b E\n"+
      "F  | |b|b| | | |c|c|b F\n"+
      "G  | | | | | | | | |  G\n"+
      "H  | | | | | | | | |  H\n"+
      "I  | | | | | | | | |  I\n"+
      "J  | | | | | | | | |  J\n"+
      "  0|1|2|3|4|5|6|7|8|9\n";
    assertEquals(expected, bytes.toString());

  }

}
