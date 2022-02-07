
/**
   * Constructs a BattleShipBoard with the specified width
   * and height
   * @param w is the width of the newly constructed board.
   * @param h is the height of the newly constructed board.
   * @throws IllegalArgumentException if the width or height are less than or equal to zero.
   */

package edu.duke.yc407.battleship;

//import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class BattleShipBoard<T> implements Board<T> {

  private final int width;
  final ArrayList<Ship<T>> myShips;
  private final PlacementRuleChecker<T> placementChecker;
  private HashSet<Coordinate> enemyMisses;
  private HashMap <Coordinate, T> enemyHit;
  private HashSet<Coordinate> newHitList;

  final T missInfo;




  
  public int getWidth() {
    return width;
  }

  private final int height;

  public int getHeight() {
    return height;
  }

  /*
   * the default placementChecker in BattleShipBoard to use the two checkers
   * combined.
   */

  //to be asked
  public BattleShipBoard(int w, int h, T miss) {
    this(w, h, new InBoundsRuleChecker<T>(new NoCollisionRuleChecker<>(null)),miss);
  }

  public BattleShipBoard(int w, int h, PlacementRuleChecker<T> placementChecker,T miss) {
    if (w <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's width must be positive but is " + w);
    }
    if (h <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's height must be positive but is " + h);
    }
    this.width = w;
    this.height = h;
    this.myShips = new ArrayList<Ship<T>>();
    this.placementChecker = placementChecker;
    this.enemyMisses = new HashSet<>();
    this.missInfo = miss;
    this.enemyHit = new HashMap<>();
    this.newHitList = new HashSet<>();
  }

  /* tryAddShip use the placementChecker */
  public String tryAddShip(Ship<T> toAdd) {
    if (placementChecker.checkPlacement(toAdd, this) != null) {
      return placementChecker.checkPlacement(toAdd, this);
    }
    else {
      myShips.add(toAdd);
    }
      return null;
  }

  public T whatIsAtForSelf(Coordinate where) {


    return whatIsAt(where, true);
  }

  protected T whatIsAt(Coordinate where, boolean isSelf) {


    if(isSelf == false && enemyHit.containsKey(where)){
      return enemyHit.get(where);
    }
    if(isSelf == false && enemyMisses.contains(where)){
      return missInfo;
    }
    for (Ship<T> s : myShips) {
      if (s.occupiesCoordinates(where)) {
        if(isSelf ==false  && newHitList.contains(where)){
          return null;
        }
        return s.getDisplayInfoAt(where, isSelf);
      }
    }

    return null;
  }

  public T whatIsAtForEnemy(Coordinate where) {
    return whatIsAt(where, false);
  }

  public Ship<T> fireAt(Coordinate c) {
    // if (whatIsAtForSelf(c) != null){
      for (Ship<T> s : myShips) {
        if (s.occupiesCoordinates(c)) {
          enemyHit.put(c,(T)((Character)s.getName().charAt(0)));
          s.recordHitAt(c);
          return s;
        }
      }
      //}
    enemyMisses.add(c);
    return null;
  }

  public boolean checkAllSunk(){
    for(Ship<T>s : myShips){
      if(!s.isSunk()){
        return false;
      }
    }
    return true;
  }

  /* For version_2_part_2 move, once we found the where we want tp place the new ship*/
  public Ship<T> get_Ship (Coordinate where) {
    for (Ship<T> s : myShips) {
      if (s.occupiesCoordinates(where)) {
          return s;
      }
    }
    return null;
  }

/*Remove implementation*/

  /*single principle*/
  public void remove_ship(Ship<T> ship_move, Ship<T>ship_add){

    //我们挪走的这艘船是否有
    //enermy misses 是否需要改变 -- 不用(应该不用）

    //在add 这艘船之后，我们需要把她的mypieces中的，对应的坐标改成true -- Done
    for(Coordinate c : ship_add.getCoordinates()){
      for(int i = 0 ; i < ship_move.getOrder_hit().size();++i){
        int a =ship_add.getMyPieces_order().get(c);
        int b = ship_move.getOrder_hit().get(i);
        if(a == b ){
          ship_add.recordHitAt(c);
          newHitList.add(c);
        }
      }
    }
    // 保留原始enermy_Board上的信息 -- done getEnemyhit -- done
    //getEnemyHit(ship_move);
    //把原本ship.remove 的信息全部都变成false --done
      myShips.remove(ship_move);
  }

//  public void getEnemyHit(Ship<T> ship_move){
//    for (Coordinate c : ship_move.getCoordinates()){
//      if(ship_move.wasHitAt(c)){
//        //这里有问题需要修bug --done
//        enemyHit.put(c,whatIsAtForEnemy(c));
//      }
//    }
//  }

  public HashMap <String , Integer> sonarScanFind (HashSet<Coordinate> diamondList){
    HashMap <String , Integer> record = new HashMap<>();
    int numSubmarine = 0 ;
    int numDestroyer = 0 ;
    int numBattleship = 0;
    int numCarrier = 0;
    for (Ship<T> s : myShips){
      for(Coordinate c : s.getCoordinates()){
        if(diamondList.contains(c)){
          if(get_Ship(c).getName().equals("Battleships")){
            numSubmarine ++;
          }
          if(get_Ship(c).getName().equals("Carrier")){
            numCarrier ++;
          }
          if(get_Ship(c).getName().equals("Submarine")){
            numSubmarine++;
          }
          if(get_Ship(c).getName().equals("Destroyer")){
            numDestroyer++;
          }

        }
      }
    }
    record.put("Battleships",numSubmarine );
    record.put("Carrier",numCarrier );
    record.put("Submarine", numSubmarine);
    record.put("Destroyer", numDestroyer);
    return record;
  }

}
