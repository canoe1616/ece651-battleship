/*This class is for two players in the battleship game*/
package edu.duke.yc407.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Function;

public class TextPlayer {
  final Board<Character> theBoard;
  final BufferedReader inputReader;
  final BoardTextView view;
  final PrintStream out;
  final AbstractShipFactory<Character> shipFactory;
  String name;
  final ArrayList<String> shipsToPlace;
  final HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns;

  public TextPlayer(String name, Board<Character> theBoard, BufferedReader inputSource, PrintStream out,
      AbstractShipFactory<Character> shipFactory) {
    this.theBoard = theBoard;
    this.view = new BoardTextView(theBoard);
    this.inputReader = inputSource;
    this.out = out;
    this.shipFactory = shipFactory;
    this.name = name;
    //this.shipsToPlace = shipsToPlace;
    //this.shipCreationFns = shipCreationFns;
    this.shipsToPlace = new ArrayList<String>();
    this.shipCreationFns = new HashMap<String, Function<Placement, Ship<Character>>>();
    setupShipCreationMap();
    setupShipCreationList();
  }

  /* put the String -> lambda mappings into shipCreationFns */
  protected void setupShipCreationMap() {

    shipCreationFns.put("Submarine", (p) -> shipFactory.makeSubmarine(p));
    shipCreationFns.put("Destroyer", (p) -> shipFactory.makeDestroyer(p));
    shipCreationFns.put("Battleships", (p) -> shipFactory.makeBattleship(p));
    shipCreationFns.put("Carriers", (p) -> shipFactory.makeCarrier(p));
  }

  protected void setupShipCreationList() {
    shipsToPlace.addAll(Collections.nCopies(2, "Submarine"));
    shipsToPlace.addAll(Collections.nCopies(3, "Destroyer"));
    shipsToPlace.addAll(Collections.nCopies(3, "Battleships"));
    shipsToPlace.addAll(Collections.nCopies(2, "Carriers"));
  }

  public Placement readPlacement(String prompt) throws IOException {
    out.println(prompt);
    String s = inputReader.readLine();
    return new Placement(s);
  }

  public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException {

    while (true) {
      try {
        Placement p = readPlacement("Player " + name + " where do you want to place a " + shipName + "?");
        Ship<Character> s = createFn.apply(p);
        String str = theBoard.tryAddShip(s);
        if(str == null) {
          break;
        }
        else{
          out.println(str);
        }
      }catch (IllegalArgumentException e) {
        out.println(e.getMessage());
      }
    }
    out.print(view.displayMyOwnBoard());
    }


  /*first we should display an empty board*/
  public void doPlacementPhase() throws IOException {
    out.print(view.displayMyOwnBoard());
    // String s = "Player A Where would you like to put your ship?";
    for(int i = 0 ; i < shipsToPlace.size(); ++i){
      doOnePlacement(shipsToPlace.get(i), shipCreationFns.get(shipsToPlace.get(i)));
    }
    // out.print(s);

  }

//  public Coordinate readCoordinate(String Prompt) throws IOException {
//      String s = inputReader.readLine();
//      if (s == null) {
//        throw new IOException("The input of coordinate to fire is empty or invalid!\n");
//      }
//      try {
//        Coordinate fireCoordinate = new Coordinate(s);
//        // only within bound can end loop
//        indicator = fireCoordinate.boundCheck(theBoard.getWidth(), theBoard.getHeight());
//        if (fireCoordinate.boundCheck(theBoard.getWidth(), theBoard.getHeight()) == false) {
//          out.println("The coordinate is out of bound");
//        }
//      } catch (IllegalArgumentException illegalArg) {
//        out.print(illegalArg.getMessage());
//        continue; // continue to the next loop
//      }
//    } while (indicator == false);
//    return fireCoordinate;
//  }


//  public void playOneTurn(Board<Character> enemyBoard, String enemyName) throws IOException {
//
//    String prompt = "Player" + this.name + "Where would you like to fire at?";
//
//
//
//    BoardTextView enemyBoardTextView = new BoardTextView(enemyBoard);
//    out.println(view.displayMyBoardWithEnemyNextToIt(enemyBoardTextView, "Your ocean", enemyHeadline));
//
//    Coordinate fireCoordinate = readCoordinate();
//    Character loc = enemyBoard.whatIsAtForSelf(fireCoordinate);
//    // To check fire result
//    Ship<Character> fireShip = enemyBoard.fireAt(fireCoordinate);
//    if (fireShip == null) {
//      out.println("You missed!");
//    } else {
//      if (loc == '*') {
//        out.println("This coordinate is hit already!\n");
//      } else {
//        String shipName = shipMap.get(loc);
//        out.println("You hit a " + shipName + "!");
//      }
//    }
//  }



}
