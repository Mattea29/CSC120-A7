/* This is a stub for the Cafe class */
/**
 * The Cafe class which extends the Building class, and contains methods for selling coffee and restocking
 */
public class Cafe extends Building{

    private int nCoffeeOunces;
    private int nSugarPackets;
    private int nCreams;
    private int nCups;
    private int maxCoffee;
    private int maxSugar;
    private int maxCream;
    private int maxCups;
    
    /**
    * The constructor which creates a new Cafe object given the following parameters
    * @param name the name of the Cafe
    * @param address the address of the Cafe
    * @param nFloors the number of floors of the Cafe
    * @param startingCoffee the starting amount of coffee in ounces
    * @param startingSugar the starting number of sugar packets
    * @param startingCreams the starting number of creamers
    * @param startingCups the starting number of cups
    */
    public Cafe(String name, String address, int nFloors, int startingCoffee, int startingSugar, int startingCreams, int startingCups) {
        super(name, address, nFloors);
        System.out.println("You have built a cafe: ☕");
        this.nCoffeeOunces = startingCoffee;
        this.nSugarPackets = startingSugar;
        this.nCreams = startingCreams;
        this.nCups = startingCups;
    }

    /**
     * Overloaded constructor which creates a new Cafe object with maximum stock values!
     * @param name the name of the Cafe
     * @param address the address of the Cafe
     * @param nFloors the number of floors in the Cafe
     * @param startingCoffee the starting amount of coffee in ounces
     * @param startingSugar the starting number of sugar packets
     * @param startingCreams the starting number of creamers
     * @param startingCups the starting number of cups
     * @param maxCoffee the maximum amount of coffee in ounces that the cafe can store
     * @param maxSugar the maximum number of sugar packets that can be stored
     * @param maxCream the maximum number of creamers that can be stored
     * @param maxCups the maximum number of cups that can be stored
     */
    public Cafe(String name, String address, int nFloors, int startingCoffee, int startingSugar, int startingCreams, int startingCups, int maxCoffee, int maxSugar, int maxCream, int maxCups) {
        super(name, address, nFloors);
        System.out.println("You have built a cafe: ☕");
        this.nCoffeeOunces = startingCoffee;
        this.nSugarPackets = startingSugar;
        this.nCreams = startingCreams;
        this.nCups = startingCups;
        this.maxCoffee = maxCoffee;
        this.maxSugar = maxSugar;
        this.maxCream = maxCream;
        this.maxCups = maxCups;
    }

    /**
     * Overriden showOptions method which includes the specific methods available in the Cafe class
     */
    @Override
    public void showOptions() {
      super.showOptions();
      System.out.println("Cafe specific ooptions at " + this.name + ": \n + sellCoffee() \n + restock()");
    }

    /**
     * Overridden goToFloor method which prohibits user from going to a floor other than the 1st
     */
    @Override
    public void goToFloor(int floorNum) {
        if (floorNum ==1) {
            super.goToFloor(floorNum);
        } else {
            System.out.println("Sorry, only the first floor of " + this.name + " is available to the public.");
        }
    }

    /**
     * the method to sell coffee with the user specified values for coffee, sugar, and creams.
     * In order to properly use restock so that the stock is not just set to 0 every time, the method
     * calculates the difference between the size of the requested attribute and the current stock, then restocks 
     * twice the difference before selling the coffee if a shortage exists. For example, if the 
     * remaining stock of coffee is 10 oz and we only have 2 sugar packets but we are supposed to sell a 12 oz coffee with 3 sugar
     * packets, the restock method is called such that we restock 4 oz of coffee and 2 sugar packets so that we 
     * are not left back with 0 stock
     * @param size the amount of ounces of coffee to be sold
     * @param nSugarPackets the number of sugar packets for the coffee
     * @param nCreams the number of creams for the coffee
     */
    public void sellCoffee(int size, int nSugarPackets, int nCreams) {
        int coffeeShortage = Math.max(size - this.nCoffeeOunces, 0);
        int sugarShortage = Math.max(nSugarPackets - this.nSugarPackets, 0);
        int creamShortage = Math.max(nCreams - this.nCreams, 0);
    
        if (coffeeShortage > 0 || sugarShortage > 0 || creamShortage > 0) {
            restock(2 * coffeeShortage, 2 * sugarShortage, 2 * creamShortage, 1); // Restock 1 cup
        }
    
        if (this.nCoffeeOunces >= size && this.nSugarPackets >= nSugarPackets && this.nCreams >= nCreams && this.nCups > 0) {
            this.nCoffeeOunces -= size;
            this.nSugarPackets -= nSugarPackets;
            this.nCreams -= nCreams;
            this.nCups--;
        }
    } // modified to just add twice the difference between what the user wants and the actual stock if there is a shortage so that it doesn't hit 0

   /**
    * Overloaded method of selling coffee which calls the overloaded restock method once any of the items hits 0 or is less than the requested item
    * @param size the amount of ounces of coffee to sell
    * @param nSugarPackets the number of sugar packets for the coffee
    * @param nCreams the number of creams for the coffee
    * @param nCups the number of cups to be used (maybe people want to double cup -- who am I to judge?)
    */
    public void sellCoffee(int size, int nSugarPackets, int nCreams, int nCups) {
        if (this.nCoffeeOunces >= size && this.nSugarPackets >= nSugarPackets && this.nCreams >= nCreams && this.nCups > 0) {
            this.nCoffeeOunces -= size;
            this.nSugarPackets -= nSugarPackets;
            this.nCreams -= nCreams;
            this.nCups -= nCups;
            System.out.println("Here is a coffee with " + size + " ounces of coffee, " + nSugarPackets + " packets of sugar, " + nCreams + " creams, and " + nCups + " cups. Enjoy!");
        } else {
            System.out.println("Give us a second.");
            restock();
            System.out.println("Ok, try ordering again!");
        }
    }

    /**
     * the method to restock the cafe supplies if called in the sellCoffee method
     * @param nCoffeeOunces the number of ounces of coffee to restock 
     * @param nSugarPackets the number of sugar packets to be restocked
     * @param nCreams the number of creams to be restocked
     * @param nCups the number of cups to be restocked
     */
    private void restock(int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups){
        this.nCoffeeOunces += nCoffeeOunces;
        this.nSugarPackets += nSugarPackets;
        this.nCreams += nCreams;
        this.nCups += nCups;
        System.out.println("Restocked: " + nCups +  " cups, " + nCoffeeOunces + " oz. coffee, " + nSugarPackets + " sugar packets, " + nCreams + " creamers.");
    }

    /**
     * Overloaded method that will restock the cafe supplies to the maximum amount
     */
    public void restock() {
        this.nCoffeeOunces = maxCoffee;
        this.nSugarPackets = maxSugar;
        this.nCreams = maxCream;
        this.nCups = maxCups;
        System.out.println("All items restocked to maximum capacity");
    }
    
    //testing!!
    public static void main(String[] args) {
        Cafe myCafe = new Cafe("Kitty Cat Cafe", "29 Juniper St.", 2, 10, 2, 10, 25);
        System.out.println(myCafe);
        myCafe.showOptions();
        myCafe.enter();
        myCafe.goToFloor(1);
        myCafe.goToFloor(2);
        Cafe testCafe = new Cafe("Woodstar Cafe", "123 Main Street", 3, 20, 15, 4, 10, 50, 50, 50, 100);
        testCafe.enter();
        testCafe.goToFloor(3);
        testCafe.sellCoffee(10, 3, 2, 2);
        testCafe.sellCoffee(12, 3, 0, 1);
        // System.out.println("Initial inventory: ");
        // System.out.println("---------");
        // System.out.println(myCafe.nCoffeeOunces + " ounces of coffee");
        // System.out.println(myCafe.nSugarPackets + " packets of sugar");
        // System.out.println(myCafe.nCreams + " creamers");
        // System.out.println(myCafe.nCups + " cups");

        // myCafe.sellCoffee(12, 3, 3);

        // System.out.println("Updated inventory: ");
        // System.out.println("---------");
        // System.out.println(myCafe.nCoffeeOunces + " ounces of coffee");
        // System.out.println(myCafe.nSugarPackets + " packets of sugar");
        // System.out.println(myCafe.nCreams + " creamers");
        // System.out.println(myCafe.nCups + " cups");


    }
    
}
