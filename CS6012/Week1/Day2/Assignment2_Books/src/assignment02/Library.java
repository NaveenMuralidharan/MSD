package assignment02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.*;


/**
 * Class representation of a library (a collection of library books).
 * This class provides methods for adding books, looking up books by ISBN or holder,
 * checking out and checking in books, and sorting the books in various orders.
 *
 * @param <Type> The type of the holder of the book (could be a name, phone number or email address).
 */
public class Library <Type>{
  /**
   * List to store the library books.
   */
  private ArrayList<LibraryBook<Type>> library;
  /**
   * Constructor to initialize the library as an empty list.
   */
  public Library() {
    library = new ArrayList<LibraryBook<Type>>();
  }

  /**
   * Add the specified book to the library, assume no duplicates.
   * 
   * @param isbn
   *          -- ISBN of the book to be added
   * @param author
   *          -- author of the book to be added
   * @param title
   *          -- title of the book to be added
   */
  public void add(long isbn, String author, String title) {
    library.add(new LibraryBook<Type>(isbn, author, title));
  }

  /**
   * Add the list of library books to the library, assume no duplicates.
   * 
   * @param list
   *          -- list of library books to be added
   */
  public void addAll(ArrayList<LibraryBook<Type>> list) {
    library.addAll(list);
  }

  /**
   * Add books specified by the input file. One book per line with ISBN, author,
   * and title separated by tabs.
   * 
   * If file does not exist or format is violated, do nothing.
   * 
   * @param filename The name of the file containing book details.
   */
  public void addAll(String filename) {
    ArrayList<LibraryBook<Type>> toBeAdded = new ArrayList<LibraryBook<Type>>();

    try (Scanner fileIn = new Scanner(new File(filename))) {

      int lineNum = 1;

      while (fileIn.hasNextLine()) {
        String line = fileIn.nextLine();

        try (Scanner lineIn = new Scanner(line)) {
          lineIn.useDelimiter("\\t");

          if (!lineIn.hasNextLong()) {
            throw new ParseException("ISBN", lineNum);
          }
          long isbn = lineIn.nextLong();

          if (!lineIn.hasNext()) {
            throw new ParseException("Author", lineNum);
          }
          String author = lineIn.next();

          if (!lineIn.hasNext()) {
            throw new ParseException("Title", lineNum);
          }
          String title = lineIn.next();
          toBeAdded.add(new LibraryBook<Type>(isbn, author, title));
        }
        lineNum++;
      }
    } catch (FileNotFoundException e) {
      System.err.println(e.getMessage() + " Nothing added to the library.");
      return;
    } catch (ParseException e) {
      System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
          + ". Nothing added to the library.");
      return;
    }

    library.addAll(toBeAdded);
  }

  /**
   * Returns the holder of the library book with the specified ISBN.
   * 
   * If no book with the specified ISBN is in the library, returns null.
   * 
   * @param isbn
   *          -- ISBN of the book to be looked up
   *  * @return The holder of the book, or @code null if not found or checked in.
   */
  public Type lookup(long isbn) {

    boolean foundBook = false;
    LibraryBook<Type> lookupBook= null;
    for(LibraryBook<Type> book : library){
      if(book.getIsbn() == isbn){
        foundBook = true;
        lookupBook = book;
      }
    }
    //return null if book not found or if its checked in
    if(!foundBook){
      return null;
    } else if(lookupBook.getHolder() == null){
      return null;
    } else {
      return lookupBook.getHolder();
    }

  }

  /**
   * Returns the list of library books checked out to the specified holder.
   * 
   * If the specified holder has no books checked out, returns an empty list.
   * 
   * @param holder -- holder whose checked out books are returned
   * @return A list of library books checked out to the holder.
   */
  public ArrayList<LibraryBook<Type>> lookup(Type holder) {
    ArrayList<LibraryBook<Type>> holdersBooks = new ArrayList<>();

    for(LibraryBook<Type> book : library){
      if(book.getHolder() != null && book.getHolder().equals(holder)){
        holdersBooks.add(book);
      }
    }

    return holdersBooks;
  }

  /**
   * Sets the holder and due date of the library book with the specified ISBN.
   * 
   * If no book with the specified ISBN is in the library, returns false.
   * 
   * If the book with the specified ISBN is already checked out, returns false.
   * 
   * Otherwise, returns true.
   * 
   * @param isbn
   *          -- ISBN of the library book to be checked out
   * @param holder
   *          -- new holder of the library book
   * @param month
   *          -- month of the new due date of the library book
   * @param day
   *          -- day of the new due date of the library book
   * @param year
   *          -- year of the new due date of the library book
   * @return {@code true} if the book is successfully checked out; {@code false} otherwise.
   */
  public boolean checkout(long isbn, Type holder, int month, int day, int year) {

    for(LibraryBook<Type> book : library){
      if(book.getIsbn() == isbn && book.getHolder() == null){
        book.checkoutBook(holder, new GregorianCalendar(year, month, day));
        return true;
      }
    }
    return false;
  }

  /**
   * Unsets the holder and due date of the library book.
   * 
   * If no book with the specified ISBN is in the library, returns false.
   * 
   * If the book with the specified ISBN is already checked in, returns false.
   * 
   * Otherwise, returns true.
   * 
   * @param isbn-- ISBN of the library book to be checked in
   * @return {@code true} if the book is successfully checked in; {@code false} otherwise.
   */
  public boolean checkin(long isbn) {

    for(LibraryBook<Type> book : library){
      if(book.getHolder() != null && book.getIsbn() == isbn){
        book.checkIn();
        return true;
      }
    }

    return false;
  }

  /**
   * Unsets the holder and due date for all library books checked out be the
   * specified holder.
   * 
   * If no books with the specified holder are in the library, returns false;
   * 
   * Otherwise, returns true.
   * 
   * @param holder-- holder of the library books to be checked in
   * @return {@code true} if books are successfully checked in; {@code false} otherwise.
   */
  public boolean checkin(Type holder) {
    boolean foundHolder = false;

    for(LibraryBook<Type> book : library){
      if(book.getHolder() != null && book.getHolder().equals(holder)){
        foundHolder = true;
        book.checkIn();
      }
    }

    return foundHolder;
  }

  /**
   * Returns the list of library books, sorted by ISBN (smallest ISBN first).
   * @return A sorted list of library books by ISBN.
   */
  public ArrayList<LibraryBook<Type>> getInventoryList() {
    ArrayList<LibraryBook<Type>> libraryCopy = new
            ArrayList<LibraryBook<Type>>();
    libraryCopy.addAll(library);
    OrderByIsbn comparator = new OrderByIsbn();
    Library.sort(libraryCopy, comparator);

    return libraryCopy;
  }

  /**
   * Returns the list of library books, sorted by author
   * @return A sorted list of library books by author.
   */
  public ArrayList<LibraryBook<Type>> getOrderedByAuthor() {
    ArrayList<LibraryBook<Type>> libraryCopy = new
            ArrayList<LibraryBook<Type>>();
    libraryCopy.addAll(library);
    OrderByAuthor comparator = new OrderByAuthor();
    Library.sort(libraryCopy, comparator);

    return libraryCopy;
  }

  /**
   * Returns the list of library books whose due date is older than the input
   * date. The list is sorted by date (oldest first).
   *If no library books are overdue, returns an empty list.
   * @param month The current month for comparison.
   * @param day   The current day for comparison.
   * @param year  The current year for comparison.
   * @return A sorted list of overdue library books.
   */
  public ArrayList<LibraryBook<Type>> getOverdueList(int month, int day, int year) {
    GregorianCalendar deadline = new GregorianCalendar(year, month, day);
    ArrayList<LibraryBook<Type>> overdueBooks = new
            ArrayList<LibraryBook<Type>>();
    OrderByDueDate comparator = new OrderByDueDate();

    for(LibraryBook<Type> book : library){
      if(book.getDueDate() != null && book.getDueDate().compareTo(deadline) < 0){
        overdueBooks.add(book);
      }
    }

    Library.sort(overdueBooks, comparator);
    return overdueBooks;
  }

  /**
   * Performs a SELECTION SORT on the input ArrayList.
   * 1. Find the smallest item in the list.
   * 2. Swap the smallest item with the first item in the list.
   * 3. Now let the list be the remaining unsorted portion
   * (second item to Nth item) and repeat steps 1, 2, and 3.
   */
  private static <ListType> void sort(ArrayList<ListType> list,
                                      Comparator<ListType> c) {
    for (int i = 0; i < list.size() - 1; i++) {
      int j, minIndex;
      for (j = i + 1, minIndex = i; j < list.size(); j++)
        if (c.compare(list.get(j), list.get(minIndex)) < 0)
          minIndex = j;
      ListType temp = list.get(i);
      list.set(i, list.get(minIndex));
      list.set(minIndex, temp);
    }
  }


  /**
   * Comparator that defines an ordering among library books using the
   ISBN.
   */
  protected class OrderByIsbn implements
          Comparator<LibraryBook<Type>> {
    /**
     * Returns a negative value if lhs is smaller than rhs. Returns a positive
     * value if lhs is larger than rhs. Returns 0 if lhs 	and rhs are equal.
     */
    public int compare(LibraryBook<Type> lhs,
                       LibraryBook<Type> rhs) {
      return (int) (lhs.getIsbn() - rhs.getIsbn());
    }
  }

  /**
   * Comparator that defines an ordering among library books using the
   author, and book title as a tie-breaker.
   */
  protected class OrderByAuthor implements
          Comparator<LibraryBook<Type>> {
    /**
     * Returns a negative value if lhs author name smaller than rhs. Returns a positive
     * value if lhs author name larger than rhs. Compares titles if author names are same.
     */
    public int compare(LibraryBook<Type> lhs,
                       LibraryBook<Type> rhs) {
      int comparison = lhs.getAuthor().compareTo(rhs.getAuthor());
      if(comparison == 0){
        //compare titles
        return lhs.getTitle().compareTo(rhs.getTitle());
      }
      return comparison;
    }
  }

  /**
   * Comparator that defines an ordering among library books using the
   due date.
   */
  protected class OrderByDueDate implements
          Comparator<LibraryBook<Type>> {

    /**
     * Returns a negative value if lhs due date is smaller than rhs. Returns a positive
     * value if lhs due date is larger than rhs. Returns 0 if due dates are same.
     */
    public int compare(LibraryBook<Type> lhs,
                       LibraryBook<Type> rhs) {

      return lhs.getDueDate().compareTo(rhs.getDueDate());

    }

  }

}
