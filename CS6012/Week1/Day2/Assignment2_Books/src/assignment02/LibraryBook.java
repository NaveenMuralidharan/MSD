package assignment02;
import java.util.GregorianCalendar;

/**
 * Represents a library book that extends from {@link Book} and keeps track of
 * the holder of the book and its due date.
 *
 * @param <Type> The type of the holder (holder name, holder phone number, or holder email).
 */
public class LibraryBook<Type> extends Book{

    private Type holder = null;
    private GregorianCalendar dueDate = null;
    /**
     * Constructs a new {@code LibraryBook} with the specified ISBN, author, and title.
     *
     * @param isbn The ISBN of the book.
     * @param author The author of the book.
     * @param title The title of the book.
     */
    public LibraryBook(long isbn, String author, String title){
        super(isbn, author, title);
    }
    /**
     * Returns the holder of this book.
     *
     * @return The holder of the book, or {@code null} if the book is not checked out.
     */
    public Type getHolder(){
        return this.holder;
    }
    /**
     * Returns the due date of this book.
     *
     * @return The due date of the book, or {@code null} if the book is not checked out.
     */
    public GregorianCalendar getDueDate(){
        return this.dueDate;
    }

    /**
     * Checks out the book to a specified holder and assigns a due date.
     * This method updates the holder and due date of the book.
     *
     * @param holder The holder of the book (e.g., a person, student).
     * @param dueDate The due date for returning the book.
     */
    public void checkoutBook(Type holder, GregorianCalendar dueDate){
        this.holder = holder;
        this.dueDate = dueDate;
    }
    /**
     * Checks in the book, clearing the holder and due date.
     * This method resets the book's status to available.
     */
    public void checkIn(){
        this.holder = null;
        this.dueDate = null;
    }

}
