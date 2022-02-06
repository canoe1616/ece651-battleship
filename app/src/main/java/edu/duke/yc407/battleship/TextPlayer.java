/*This class is for two players in the battleship game*/
package edu.duke.yc407.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
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


  /*for version_2 params*/
  public int numMove = 3;
  public int numSonar = 3;


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
        if (str == null) {
          break;
        } else {
          out.println(str);
        }
      } catch (IllegalArgumentException e) {
        out.println(e.getMessage());
      }
    }
    out.print(view.displayMyOwnBoard());
  }


  /*first we should display an empty board*/
  public void doPlacementPhase() throws IOException {
    out.print(view.displayMyOwnBoard());
    // String s = "Player A Where would you like to put your ship?";
    for (int i = 0; i < shipsToPlace.size(); ++i) {
      doOnePlacement(shipsToPlace.get(i), shipCreationFns.get(shipsToPlace.get(i)));
    }
    // out.print(s);

  }

  public Coordinate readCoordinate(String Prompt) throws IOException {
    //out.println(Prompt);
    String s = inputReader.readLine();
    Coordinate fireCoordinate = null;
    try {
      fireCoordinate = new Coordinate(s);
      return fireCoordinate;
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Coordinate is invalid, please enter the new coordinate again");
    }
  }

/*Update this function in version_2 */
  public void playOneTurn(Board<Character> enemyBoard, String enemyName) throws IOException {
    String prompt = "Player " + this.name + "'s turn";
    out.println(prompt);
    BoardTextView enemyBoardTextView = new BoardTextView(enemyBoard);
    out.println(view.displayMyBoardWithEnemyNextToIt(enemyBoardTextView, "my board", "enemy board"));
    choiceReader(enemyBoard,enemyName);

  }


  public void playOneTurn_fire(Board<Character> enemyBoard, String enemyName) throws IOException {

    String prompt = "Player " + this.name + " Where would you like to fire at?";
    Coordinate fireCoordinate;
    while (true) {
      try {
        fireCoordinate = readCoordinate(prompt);
        break;
      } catch (IllegalArgumentException e) {
        out.println(e.getMessage());
      }
    }
    Ship<Character> fireShip = enemyBoard.fireAt(fireCoordinate);
    if (fireShip == null) {
      out.println("You missed!");
    } else {
      Character ship = enemyBoard.whatIsAtForEnemy(fireCoordinate);
      String hit_message = null;
      if (ship == 's') {
        hit_message = "You hit a submarine";
      } else if (ship == 'd') {
        hit_message = "You hit a Destroyer ";
      } else if (ship == 'c') {
        hit_message = "You hit a Carrier ";
      } else if (ship == 'b') {
        hit_message = "You hit a Battleship ";
      }
      out.println(hit_message);
    }
  }



  /*Version_2_part_2_move*/


  public String Read_Choice() throws IOException {
      String prompt = "Possible actions for Player " + name + " :\n\n" +
              " F Fire at a square\n" +
              " M Move a ship to another square (" + numMove + " remaining)\n" +
              " S Sonar scan (" + numSonar + " remaining)\n\n" +
              "Player " + name + ", what would you like to do?\n";
      out.println(prompt);
      String s = null;
      while (true) {
        try {
          s = inputReader.readLine();
          if (s == null) {
            throw new IllegalArgumentException("The input is null\n");
          }
          if (!(s.equals("F") || s.equals("M") || s.equals("S"))) {
            throw new IllegalArgumentException("Please enter a valid choice\n");
          }
          if(numMove == 0){
            throw new IllegalArgumentException("M has 0 remaining, enter again please\n");
          }
          if(numSonar == 0){
            throw new IllegalArgumentException("S has 0 remaining, enter again please\n");
          }
          break;
        } catch (IllegalArgumentException e) {
          out.println(e.getMessage());
        }
      }
    return s;
  }


  public void choiceReader(Board<Character> enemyBoard, String enemyName) throws IOException {
    String choice = Read_Choice();
    if (choice.equals("F")) {
      playOneTurn_fire(enemyBoard,enemyName );
    } else if (choice.equals("M")) {
      if (!move()) {
        choiceReader(enemyBoard,enemyName);
      } else {
        out.println("Successfully move!\n");
        numMove = numMove -1;
      }
    }
  }

/* try to upperleft & tryaddship boundary & collision  -- 让tryAddShip 去检查*/
  /*hit & miss -- > set -- board */
  /*enemy(hit & miss)*/


  /* Try to find whether this player selects an invalid location or not? */
  public boolean move() throws IOException {
    String prompt = "Which ship do you want to do move?";
    out.println("Which ship do you want to do move?");
    Coordinate choose = null;
    ;
    Ship<Character> ship_move = null;

    while (true) {
      try {
        choose = readCoordinate(prompt);
        ship_move = theBoard.get_Ship(choose);
        if (ship_move == null) {
          throw new InvalidPropertiesFormatException("There is no ship here, please enter again: ");
        }
        break;
      } catch (IllegalArgumentException e) {
        out.println(e.getMessage());
      }
    }
    String ship_name = ship_move.getName();
    String move_prompt = "Where do you want to place the moved ship?";
    Placement new_ship = null;
    try {
    new_ship = readPlacement(move_prompt);
    }
    catch (IllegalArgumentException e){
      out.println(e.getMessage());
      return false;
    }
    /*Move part*/
    Ship<Character> new_ship_add = shipCreationFns.get(ship_name).apply(new_ship);
    String str = theBoard.tryAddShip(new_ship_add);
    if(str != null){
      return  false;
    }
    theBoard.remove_ship(ship_move,new_ship_add);
    return true;
  }
  }

