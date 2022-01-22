/**
   * Constructs a Placement with the specified Coordinate and the orientation.
   * @param coord.
   * @param orientation is V / H.
   * @throws IllegalArgumentException if the length of the string is invalid(e.g "A") or the there are extra char (e.g"A1vg").
   * @throws IllegalArgumentException if the characters in the string are invalid(e.g "/@").
   * @throws IllegalArgumentException if the third characters in the string are invalid(e.g "g/a(chars are not V/H"). 
   */



package edu.duke.yc407.battleship;

public class Placement {
  private final Coordinate coord;
  private final Character Orientation;

  public Coordinate getCoordinate(){
    return coord;
    
  }

  public Character getOrientation(){
    return Orientation;
  }
  
  public Placement(Coordinate c1, Character o1) {
    this.coord = c1;
    /*
      try to keep all the orientation in the upperCase way;
     */
    this.Orientation = Character.toUpperCase(o1);
  }

   public Placement(String descr){
     if (descr.length() != 3) {
      throw new IllegalArgumentException("The string length is not correct");
    }

     this.coord = new Coordinate(descr.substring(0,2));
     int i = 2;
     if(descr.charAt(i) == 'v' || descr.charAt(i) == 'h' || descr.charAt(i) == 'V' || descr.charAt(i) == 'H'){
       this.Orientation = Character.toUpperCase(descr.charAt(i));
     }
     else{
       throw new IllegalArgumentException("The Orientation is not valid");
     }
  }

  @Override
  public boolean equals(Object o) {
    if (o.getClass().equals(getClass())) {
      Placement c = (Placement) o ;
      return coord.getRow() == c.coord.getRow() && coord.getColumn() == c.coord.getColumn() && Orientation == c.Orientation;
    }
    return false;
  }


   @Override
  public int hashCode() {
    return toString().hashCode();
  }

  @Override
  public String toString() {
    return "("+Orientation+ ")";
  }

}
