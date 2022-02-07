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
//    shipsToPlace.addAll(Collections.nCopies(2, "Submarine"));
//    shipsToPlace.addAll(Collections.nCopies(3, "Destroyer"));
    shipsToPlace.addAll(Collections.nCopies(1, "Battleships"));
//    shipsToPlace.addAll(Collections.nCopies(2, "Carriers"));
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
    choiceReader(enemyBoard, enemyName);

  }


  public void playOneTurn_fire(Board<Character> enemyBoard, String enemyName) throws IOException {

    String prompt = "Player " + this.name + " Where would you like to fire at?";
    out.println(prompt);
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

      char ship = enemyBoard.whatIsAtForEnemy(fireCoordinate) == null ? ' ' : enemyBoard.whatIsAtForEnemy(fireCoordinate) ;
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
        if (s.equals("M") && numMove == 0) {
          throw new IllegalArgumentException("M has 0 remaining, enter again please\n");
        }
        if (s.equals("S") && numSonar == 0) {
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
      playOneTurn_fire(enemyBoard, enemyName);
    } else if (choice.equals("M")) {
      if (!move()) {
        choiceReader(enemyBoard, enemyName);
      } else {
        out.println("Successfully move!\n");
        numMove = numMove - 1;
      }
    }
    /*When it comes to the sonar scan*/
    else {
        sonarScan();
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
          throw new IllegalArgumentException("There is no ship here, please enter again: ");
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
    } catch (IllegalArgumentException e) {
      out.println(e.getMessage());
      return false;
    }
    /*Move part*/
    System.out.println(ship_name);
    Ship<Character> new_ship_add = shipCreationFns.get(ship_name).apply(new_ship);
    String str = theBoard.tryAddShip(new_ship_add);
    if (str != null) {
      return false;
    }
    theBoard.remove_ship(ship_move, new_ship_add);
    return true;
  }


  /*For part_2 version_2 sonar scan*/
  public HashSet<Coordinate> getDiamond(int row, int column) {
    int startR = row - 3;
    int startC = column - 3;

    HashSet<Coordinate> diamondList = new HashSet<>();
    for (int i = startR; i < startR + 3; ++i) {
      int startNum = 2 * (i - startR) + 1;
      int startJ = startC + 3 - (i - startR);

      while (startNum > 0) {
        if (isInRange(i, startJ)) {
          diamondList.add(new Coordinate(i, startJ));
        }
        startJ++;
        startNum--;
      }
    }

    for (int i = row + 3; i >= row + 1; i--) {
      int startNum = 2 * (3 - (i - row)) + 1;
      int startJ = startC + i - row;
      while (startNum > 0) {
        if (isInRange(i, startJ)) {
          diamondList.add(new Coordinate(i, startJ));
        }
        startJ++;
        startNum--;
      }
    }
    return diamondList;
  }

  public boolean isInRange(int r, int c) {
    if (r < 0 || c < 0 || r >= theBoard.getHeight() || c >= theBoard.getWidth()) {
      return false;
    }
    return true;
  }

  public void sonarScan() throws IOException {
    String prompt = "Please enter the center for the sonar scan";
    Coordinate choose = readCoordinate(prompt);
    while (true) {
      try {
        if (!isInRange(choose.getRow(), choose.getColumn())) {
          throw new IllegalArgumentException("The center coordinate is out of boundary!");
        }
        break;
      } catch (IllegalArgumentException e) {
        out.println(e.getMessage());
      }
    }
    HashMap<String , Integer> ans = theBoard.sonarScanFind(getDiamond(choose.getRow(), choose.getColumn()));
    for(int i = 0 ; i < ans.size() ;++i){
      out.println("Submarines occupy " + ans.get("Submarine") + " squares");
      out.println("Destroyer occupy " + ans.get("Destroyer") + " squares");
      out.println("Battleships occupy " + ans.get("Battleships") + " squares");
      out.println("Carrier occupy " + ans.get("Carrier") + " squares");

    }

  }

}