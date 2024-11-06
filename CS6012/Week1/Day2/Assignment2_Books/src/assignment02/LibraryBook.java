package assignment02;
import java.util.GregorianCalendar;

public class LibraryBook<Type> extends Book{

    private Type holder = null;
    private GregorianCalendar dueDate = null;

    public LibraryBook(long isbn, String author, String title){
        super(isbn, author, title);
    }

    public Type getHolder(){
        return this.holder;
    }

    public GregorianCalendar getDueDate(){
        return this.dueDate;
    }

    //Methods for checking a book in and out.
    public void checkoutBook(Type holder, GregorianCalendar dueDate){
        this.holder = holder;
        this.dueDate = dueDate;
    }

    public void checkIn(){
        this.holder = null;
        this.dueDate = null;
    }

}
