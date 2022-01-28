package edu.duke.yc407.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

public class TextPlayerTest {
  @Test
  public void test_read_placement() throws IOException {
    StringReader sr = new StringReader("B2V\nC8H\na4v\n");
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(bytes, true);
    Board<Character> b1 = new BattleShipBoard<Character>(10, 20);
    //    ArrayList<String> shipsToPlace = new ArrayList<>();
    //HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns = new HashMap<>();
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
    Board<Character> board = new BattleShipBoard<Character>(w, h);
    V1ShipFactory shipFactory = new V1ShipFactory();
    //    ArrayList<String> shipsToPlace = new ArrayList<>();
    //HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns = new HashMap<>();
    return new TextPlayer("A", board, input, output, shipFactory);
  }

  @Test
  void test_do_placement() throws IOException {

    String prompt = "Player A Where would you like to put your ship?";
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();

    TextPlayer player = createTextPlayer(5, 5, "B2V\n", bytes);
    player.doOnePlacement("Destroyers",player.shipCreationFns.get("Destroyers"));
    String expectedHeader = "  0|1|2|3|4\n";
    String expected = prompt + "\n" + expectedHeader + "A  | | | |  A\n" + "B  | | |d|  B\n" + "C  | | |d|  C\n"
        + "D  | | |d|  D\n" + "E  | | | |  E\n" + expectedHeader;
    assertEquals(expected, bytes.toString());

  }

  @Test
  void test_doPlacementPhase() throws IOException{

    String prompt = "Player A Where would you like to put your ship?";
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(5, 5, "B2V\n", bytes);
    player.doPlacementPhase();
    String expectedHeader = "  0|1|2|3|4\n";
    String expected = expectedHeader+ "A  | | | |  A\n"+ "B  | | | |  B\n"+ "C  | | | |  C\n"+ "D  | | | |  D\n"+"E  | | | |  E\n"+ expectedHeader +prompt + "\n" + expectedHeader + "A  | | | |  A\n" + "B  | | |d|  B\n" + "C  | | |d|  C\n" + "D  | | |d|  D\n" + "E  | | | |  E\n" + expectedHeader;
    assertEquals(expected, bytes.toString());

  }

}
