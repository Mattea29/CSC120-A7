/* This is a stub for the Library class */
/**
 * The Library class is a subclass of the Building class which creates a new Library object and performs several 
 * functions such as adding to the collection, removing from the collection, checking out a book, returning it, checking
 * inventory and availability, and printing the entire inventory
 */
import java.util.Hashtable;
import java.time.LocalDate; // for assigning due dates!!

public class Library extends Building {

  // the empty hashtable which will store the residents information but cannot be accessed otherwise for privacy 
  private Hashtable<String, Boolean> collection;
  private Hashtable<String, LocalDate> collectionWithDates;
  private boolean hasElevator;

  /**
   * creates new Library with Building attributes plus other necessary attributes
   * @param name the name of the Library
   * @param address the address of the library
   * @param nFloors the number of floors of the library 
   */
    public Library(String name, String address, int nFloors, boolean hasElevator) {
      super(name, address, nFloors);
      this.collection = new Hashtable<String, Boolean>();
      System.out.println("You have built a library: 📖");
      this.hasElevator = hasElevator;
      this.collectionWithDates = new Hashtable<String, LocalDate>();
    }
  
  /**
   * Overriden showOptions method which includes the specific methods available in the Cafe class
   */
    @Override
    public void showOptions() {
      super.showOptions();
      System.out.println("Library specific ooptions at " + this.name + ": \n + addTitle() \n + removeTitle( \n + checkOut() \n + returnBook() \n + containsTitle() \n + isAvailable() \n + printCollection()");
    }

  /**
   * Overriden goToFloor method which checks for the existence of an elevator and then either invokes the behavior of the parent class goToFloor method or
   * sends appropriate message
   */
    @Override
    public void goToFloor(int floorNum) {
      if (this.hasElevator) {
        super.goToFloor(floorNum);
      } else {
        System.out.println("Sorry, there is no elevator.");
      }
    }
    
    /**
     * method that allows user to add a book to the collection
     * @param title the title (name + author) to be added to the collection
     */
    public void addTitle(String title) {
      collection.put(title, true); // sets to true because it is now part of the collection
    }

    /**
     * method that allows user to remove a certain book from the collection
     * @param title the title to be removed from the collection
     * @return the book removed if it exists, otherwise gives a message and returns null
     */
    public String removeTitle(String title) {
      if (containsTitle(title)) {
        collection.remove(title, false);
        return title;
      }   else {
        System.out.println(title + " is not in the collection and cannot be removed.");
        return null;
      }  
    }

    /**
     * method that allows user to checkout a certain book ir it is available and changes the availability status to false
     * @param title the title to be checked out
     */
    public void checkOut(String title) {
      if (isAvailable(title)) {
        collection.put(title, false);
      } else {
        System.out.println("Sorry, that book is not available.");
      }
    }

    /**
     * Overloaded method that allows users to checkout a certain book if it is available and changes the availablity status to false, plus assigns a due date that the book must be returned by
     * @param title the title to be checked out
     * @param daysDue an int representing the number of days remaining until the book is due; limited to 21, so if the user puts more than 21 messages, it will be automatically limited to 21
     */
    public void checkOut(String title, int daysDue) {
      daysDue = Math.min(daysDue, 21);
      LocalDate dueDate = LocalDate.now().plusDays(daysDue);

      if (isAvailable(title)) {
        collection.put(title, false);
        System.out.println("Checked out: " + title + ". Due date: " + dueDate);
        collectionWithDates.put(title, dueDate); //storing due date to be used later in returnBook method!
      } else {
        System.out.println("Sorry, that book is not available.");
      }
    }
    /**
     * method for user to return a book if it exists in the collection, and if so, change available status to true
     * @param title the title to be returned
     */
    public void returnBook(String title) {
      if (containsTitle(title)) {
        collection.put(title, true);
      } else {
        System.out.println(title + " is not in the collection and cannot be returned.");
      }
    }

    /**
     * Overloaded method that allows the user to return a book on a specified date and checks whether or not the due date was met, then chooses the 
     * appropriate message to send
     * @param title the title of the book being returned
     * @param returnDate the date that the book is being returned on -- user should either specify today as LocalDate.now() or give a specific date by assigning a LocalDate object in the future
     */
   public void returnBook(String title, LocalDate returnDate) {
    if (containsTitle(title)) {
      collection.put(title, true);

      if (collectionWithDates.containsKey(title)) {
        LocalDate dueDate = collectionWithDates.get(title);
        if (returnDate.isBefore(dueDate)) {
          System.out.println("Thank you! " + title + " returned on time.");
        } else {
          System.out.println(title + " returned late. Sleep with one eye open.");
        }
        collectionWithDates.remove(title); //removing because it is no longer associated with a due date
      } else {
        System.out.println(title + " returned. Due date not found.");
      }
    } else {
      System.out.println(title + " is not in the collection and cannot be returned.");
    }
   }

    /**
     * method that checks whether the collection contains the title
     * @param title the title to be searched for in the collection
     * @return true if the collection contains the title, false otherwise
     */
    public boolean containsTitle(String title){
      return collection.containsKey(title);
    }

    /**
     * method that checks if the title is available by first checking if 
     * it exists in the collection
     * @param title the title to be checked for existence and then availability
     * @return the boolean value associated with the title key if it exists, otherwise false
     */
    public boolean isAvailable(String title) {
      if (containsTitle(title)) {
        return collection.get(title);
      } else {
      return false;
      }
    }
    
    /**
     * the method to print the collection, including the title and availability
     */
    public void printCollection(){
      System.out.println("Current Library Selection: ");
      for (String title : collection.keySet()) {
        boolean available = collection.get(title);
        System.out.println(title + (available ? " (Available)" : " (Checked Out)"));
      }
    }

//Testing!!
    public static void main(String[] args) {
      Library myLib = new Library("Neilson", "123 Smith St.", 5, true);
      System.out.println(myLib);
      myLib.showOptions();
      myLib.enter();
      myLib.goToFloor(2);
      myLib.goToFloor(1);
      myLib.exit();
      myLib.addTitle("Hello by Me");
      myLib.addTitle("Goodbye by You");
      myLib.checkOut("Hello by Me");
      myLib.checkOut("Goodbye by You", 14);
      myLib.addTitle("This is a book by Anonymous");
      myLib.checkOut("This is a book by Anonymous", 30);
      myLib.returnBook("Hello by Me");
      myLib.returnBook("Goodbye by You", LocalDate.now());
      LocalDate currentDate = LocalDate.now();
      LocalDate overDue = currentDate.plusWeeks(4);
      myLib.returnBook("This is a book by Anonymous", overDue);
      // myLib.addTitle("Really Cool Book by Mattea Whitlow");
      // myLib.addTitle("A Not Very Good Book by Wattea Mhitlow");
      // myLib.addTitle("An OK Book by Mattlow Whittea");
      // myLib.removeTitle("A Not Very Good Book by Wattea Mhitlow");
      // myLib.checkOut("Really Cool Book by Mattea Whitlow");
      // myLib.printCollection();
      // myLib.returnBook("Really Cool Book by Mattea Whitlow");
      // myLib.printCollection();
      // myLib.removeTitle("A Book??? by MW");
      // myLib.returnBook("Hello by Adele");
      // myLib.checkOut("Butter by BTS");

    }
  
  }