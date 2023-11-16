Use this file to record your reflection on this assignment.

- Which methods did you decide to `overload`, and why?
For the House class:
    I overloaded the House constructor to include an integer representing the maximum number of people who can live in the House at once. I made it a private int so that other people cannot change it once the house is constructed. I then made an overloaded moveIn() method which takes in the name of the person trying to move in and their grade year -- both strings -- and then checks the current number of residents compared to the maximum number. Based on that result, the person will be added to the house or denied. I made the overload method take in the grade in order to allow the call signature to be different. I chose to do these two methods/constructors because I realized that in the original version of the House class, I never set a limit on how many people could move in, so theoretically the house could have an infinite amount of residents, which would be a logistical nightmare! So, I wanted to try a new method that would build a house with a set limit while still keeping the impossible house alive (because why ruin the magic??). For the move in method, I also wanted to address the issue of seemingly infinite rooms. Instead, this method approaches the situation logically to ensure the houses are not overcrowded (like some currently are). 

For the Library Class:
    I overloaded the checkOut and returnBook methods. I chose these two because I wanted to make an overloaded method that could rely on another overloaded method, and these two made the most logical sense. I had to do a lot of googling of LocalDate. In these new methods, a user is still able to check the book out but now a due date is assigned. I wanted to give the user an option to say when they would return the book by, but capping it at 21 days just in case. I had to create a different hashtable that contained the title and the associated due date and then I stored these new values so that they could be used later by the returnBook method. The return book method takes in either the current date or a specified date and then checks to see if this date is before the due date associated with the title. Either way, as long as it is part of the collection, it will accept the book, but it might send a threatening message...

For the Cafe Class:
    

- What worked, what didn't, what advice would you give someone taking this course in the future?
