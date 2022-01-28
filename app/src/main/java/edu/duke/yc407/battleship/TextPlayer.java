/*This class is for two players in the battleship game*/
package edu.duke.yc407.battleship;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;

public class TextPlayer {
  final Board<Character> theBoard;
  final BoardTextView view;
  final BufferedReader inputReader;
  final PrintStream out;
  final AbstractShipFactory<Character> shipFactory;
  String name;

  public TextPlayer(String name, Board<Character> theBoard, BufferedReader inputSource, PrintStream out,
      AbstractShipFactory<Character> shipFactory) {
    this.theBoard = theBoard;
    this.view = new BoardTextView(theBoard);
    this.inputReader = inputSource;
    this.out = out;
    this.shipFactory = shipFactory;
    this.name = name;
  }

  public Placement readPlacement(String prompt) throws IOException {
    out.println(prompt);
    String s = inputReader.readLine();
    return new Placement(s);
  }

  public void doOnePlacement() throws IOException {

    String s = "Player A Where would you like to put your ship?";
    Ship<Character> ship = shipFactory.makeDestroyer(readPlacement(s));
    theBoard.tryAddShip(ship);
    out.print(view.displayMyOwnBoard());
  }

  public void doPlacementPhase() throws IOException {
    out.print(view.displayMyOwnBoard());
    String s = "Player A Where would you like to put your ship?";
    //out.print(s);
    doOnePlacement();

  }

}
