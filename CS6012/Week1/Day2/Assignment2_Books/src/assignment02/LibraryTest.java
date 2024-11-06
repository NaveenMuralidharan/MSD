package assignment02;

import assignment02.Book;
import assignment02.Library;
import assignment02.LibraryBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    public void testEmpty() {
        Library<Type> lib = new Library();
        assertNull(lib.lookup(978037429279L));

        ArrayList<LibraryBook<Type>> booksCheckedOut = lib.lookup("Jane Doe".getClass());
        assertEquals(booksCheckedOut.size(), 0);

        assertFalse(lib.checkout(978037429279L, "Jane Doe".getClass(), 1, 1, 2008));
        assertFalse(lib.checkin(978037429279L));
        assertFalse(lib.checkin("Jane Doe"));
    }

    @Test
    public void testNonEmpty() {

        var lib = new Library<Type>();
        // test a small library
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        assertNull(lib.lookup(9780330351690L)); //not checked out
        var res = lib.checkout(9780330351690L, "Jane Doe".getClass(), 1, 1, 2008);
        assertTrue(res);
        var booksCheckedOut = lib.lookup("Jane Doe".getClass());
        assertEquals(booksCheckedOut.size(), 1);
//        assertEquals(booksCheckedOut.get(0),new Book(9780330351690L, "Jon Krakauer", "Into the Wild"));
//        assertEquals(booksCheckedOut.get(0).getHolder(), "Jane Doe".getClass());
//        assertEquals(booksCheckedOut.get(0).getDueDate(),new GregorianCalendar(2008, 1, 1));
//        res = lib.checkin(9780330351690L);
//        assertTrue(res);
//        res = lib.checkin("Jane Doe");
//        assertFalse(res);
    }

    @Test
    public void testLargeLibrary(){
        // test a medium library
        var lib = new Library();
        lib.addAll("Mushroom_Publishing.txt");

        // FILL IN MORE TESTS HERE!
        //negative test
        assertNull(lib.lookup(9781843190004L));
        ArrayList<LibraryBook> booksCheckedOut = lib.lookup("Jane Doe");
        assertEquals(booksCheckedOut.size(), 0);
        assertFalse(lib.checkout(978037429279L, "Jane Doe", 1, 1, 2008));
        assertFalse(lib.checkin(978037429279L));
        assertFalse(lib.checkin("Jane Doe"));

        //positive test
        var res = lib.checkout(9781843190004L, "Jane Doe", 1, 1, 2008);
        assertTrue(res);
        ArrayList<LibraryBook> booksCheckedOut2 = lib.lookup("Jane Doe");
        assertEquals(booksCheckedOut2.size(), 1);
        assertEquals(booksCheckedOut2.get(0),new Book(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound"));
        assertEquals(booksCheckedOut2.get(0).getHolder(), "Jane Doe");
        assertEquals(booksCheckedOut2.get(0).getDueDate(),new GregorianCalendar(2008, 1, 1));
        res = lib.checkin(9781843190004L);
        assertTrue(res);
        assertNull(lib.lookup(9781843190004L));

    }

}