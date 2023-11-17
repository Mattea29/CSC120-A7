import java.util.ArrayList;

public class CampusMap {

    ArrayList<Building> buildings;

    /* Default constructor, initializes empty ArrayList */
    public CampusMap() {
        buildings = new ArrayList<Building>();
    }

    /**
     * Adds a Building to the map
     * @param b the Building to add
     */
    public void addBuilding(Building b) {
        System.out.println("Adding building...");
        buildings.add(b);
        System.out.println("-->Successfully added " + b.getName() + " to the map.");
    }

    /**
     * Removes a Building from the map
     * @param b the Building to remove
     * @return the removed Building
     */
    public Building removeBuilding(Building b) {
        System.out.println("Removing building...");
        buildings.remove(b);
        System.out.println("-->Successfully removed " + b.getName() + " to the map.");
        return b;
    }

    public String toString() {
        String mapString = "DIRECTORY of BUILDINGS";

        for (int i = 0; i < this.buildings.size(); i ++) {
            mapString += "\n  " + (i+1) + ". "+ this.buildings.get(i).getName() + " (" + this.buildings.get(i).getAddress() + ")";
        }
        return mapString;
    }

    public static void main(String[] args) {
        CampusMap myMap = new CampusMap();
        myMap.addBuilding(new Building("Ford Hall", "100 Green Street Northampton, MA 01063", 4));
        myMap.addBuilding(new Building("Bass Hall", "4 Tyler Court Northampton, MA 01063", 4));
        System.out.println(myMap);
        myMap.addBuilding(new House("Comstock House", "1 Mandelle Road", 4, true, true));
        myMap.addBuilding(new Library("Nielson Library", "123 Book Road", 4, true));
        myMap.addBuilding(new Cafe("Campus Center Cafe", "100 Elm Street", 1, 25, 50, 35, 75));
        myMap.addBuilding(new Building("Seelye Hall", "123 Green St", 4));
        myMap.addBuilding(new Building("Botanical Garden", "16 College Lane", 1));
        myMap.addBuilding(new House("Wilson House", "16 Kensington Ave", 4, false, false));
        myMap.addBuilding(new Library("Hillyer Art Library", "20 Elm Street", 3, true));
        myMap.addBuilding(new Cafe("Compass Cafe", "123 Book Road", 1, 30, 50, 25, 50, 100, 100, 75, 200));
        myMap.addBuilding(new Building("Campus Center", "100 Elm Street", 3));
        myMap.addBuilding(new House("Morrow House", "2 Mandelle Road", 4, false, false));

        System.out.println(myMap);
    }
    
}
