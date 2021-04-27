package googlePlayGame.city;

public class City {
    private  static String name;
    private  static boolean traversed;
    private  static int kilometersRequired;

    public City(String name, boolean traversed, int kilometersRequired){
        this.name=name;
        this.traversed=traversed;
        this.kilometersRequired=kilometersRequired;
    }

    public String toString(){
        return String.format(
                "%-10s  |  %-30s  |  %d" ,
                name ,
                (traversed ? "I have been there.":"I have never been there."),
                kilometersRequired)
                ;

    }
    public  String getName(){
        return name;
    }
    public  boolean getTraversed(){
        return traversed;
    }
    public  int getKilometersRequired(){
        return kilometersRequired;
    }


}

