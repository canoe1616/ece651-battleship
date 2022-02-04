/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.duke.yc407.battleship;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;

public class App {

  // final Board<Character> theBoard;
  // final BoardTextView view;
  // final BufferedReader inputReader;
  // final PrintStream out;
  // final AbstractShipFactory<Character> shipFactory;

  TextPlayer player1;
  TextPlayer player2;

  public App(TextPlayer A, TextPlayer B) {
    this.player1 = A;
    this.player2 = B;
  }

  // public App(Board<Character> theBoard, Reader inputSource, PrintStream out) {
  // this.theBoard = theBoard;
  // this.view = new BoardTextView(theBoard);
  // this.inputReader = new BufferedReader(inputSource);
  // this.out = out;
  // this.shipFactory = new V1ShipFactory();
  // }

  // public Placement readPlacement(String prompt) throws IOException {
  // out.println(prompt);
  // String s = inputReader.readLine();
  // return new Placement(s);
  // }

  // public void doOnePlacement() throws IOException {

  // String s = "Where would you like to put your ship?";
  // Ship<Character> ship = new
  // RectangleShip<Character>(readPlacement(s).getCoordinate(), 's', '*');
  // Ship<Character> ship = shipFactory.makeDestroyer(readPlacement(s));
  // theBoard.tryAddShip(ship);
  // out.print(view.displayMyOwnBoard());
  // }

  public static void main(String[] args) throws IOException {

    Board<Character> b1 = new BattleShipBoard<Character>(10, 20, 'X');
    Board<Character> b2 = new BattleShipBoard<Character>(10, 20,'X');
    // InputStream in = System.in;
    // InputStreamReader isr = new InputStreamReader(in);
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    V1ShipFactory factory = new V1ShipFactory();
    TextPlayer a = new TextPlayer("A", b1, input, System.out, factory);
    TextPlayer b = new TextPlayer("B", b2, input, System.out, factory);
    App app = new App(a, b);
    app.doPlacementPhase();
    app.doAttackingPhase();
  }

  public void doPlacementPhase() throws IOException {
    player1.doPlacementPhase();
    player2.doPlacementPhase();
  }

  public void doAttackingPhase() throws IOException {
    do {
      player1.playOneTurn(player2.theBoard, player2.name);
      if (player2.theBoard.checkAllSunk() == true) {
        System.out.println("Congratulations! Player " + player1.name + " you win!");
        break;
      }
      player2.playOneTurn(player1.theBoard, player1.name);
      if (player1.theBoard.checkAllSunk() == true) {
        System.out.println("Congratulations! Player " + player2.name + " you win!");
        break;
      }
    } while (true);
  }


}
