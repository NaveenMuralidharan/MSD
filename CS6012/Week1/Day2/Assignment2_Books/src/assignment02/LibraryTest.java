package assignment02;

import assignment02.Book;
import assignment02.Library;
import assignment02.LibraryBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    public void testEmpty() {
        Library<String> lib = new Library<>();
        assertNull(lib.lookup(978037429279L));

        ArrayList<LibraryBook<String>> booksCheckedOut = lib.lookup("Jane Doe");
        assertEquals(booksCheckedOut.size(), 0);

        assertFalse(lib.checkout(978037429279L, "Jane Doe", 1, 1, 2008));
        assertFalse(lib.checkin(978037429279L));
        assertFalse(lib.checkin("Jane Doe"));
    }

    @Test
    public void testNonEmpty() {

        Library<String> lib = new Library<>();
        // test a small library
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        assertNull(lib.lookup(9780330351690L)); //not checked out
        var res = lib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008);
        assertTrue(res);
        var booksCheckedOut = lib.lookup("Jane Doe");
        assertEquals(booksCheckedOut.size(), 1);
        assertEquals(booksCheckedOut.get(0),new Book(9780330351690L, "Jon Krakauer", "Into the Wild"));
        assertEquals(booksCheckedOut.get(0).getHolder(), "Jane Doe");
        assertEquals(booksCheckedOut.get(0).getDueDate(),new GregorianCalendar(2008, 1, 1));
        res = lib.checkin(9780330351690L);
        assertTrue(res);
        res = lib.checkin("Jane Doe");
        assertFalse(res);
    }

    @Test
    public void testLargeLibrary(){
        // test a medium library
        Library<String> lib = new Library<>();
        lib.addAll("Mushroom_Publishing.txt");

        //negative test
        assertNull(lib.lookup(9781843190004L));
        ArrayList<LibraryBook<String>> booksCheckedOut = lib.lookup("Jane Doe");
        assertEquals(booksCheckedOut.size(), 0);
        assertFalse(lib.checkout(978037429279L, "Jane Doe", 1, 1, 2008));
        assertFalse(lib.checkin(978037429279L));
        assertFalse(lib.checkin("Jane Doe"));

        //positive test
        var res = lib.checkout(9781843190004L, "Jane Doe", 1, 1, 2008);
        assertTrue(res);
        ArrayList<LibraryBook<String>> booksCheckedOut2 = lib.lookup("Jane Doe");
        assertEquals(booksCheckedOut2.size(), 1);
        assertEquals(booksCheckedOut2.get(0),new Book(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound"));
        assertEquals(booksCheckedOut2.get(0).getHolder(), "Jane Doe");
        assertEquals(booksCheckedOut2.get(0).getDueDate(),new GregorianCalendar(2008, 1, 1));
        res = lib.checkin(9781843190004L);
        assertTrue(res);
        assertNull(lib.lookup(9781843190004L));

    }

    @Test
    public void testLookupForNonExistentBook() {
        Library<String> lib = new Library<>();
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");

        // Lookup a book that doesn't exist
        assertNull(lib.lookup(1234567890123L));  // ISBN not in the library
    }

    @Test
    public void testLookupAfterCheckout() {
        Library<String> lib = new Library<>();
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");

        lib.checkout(9780374292799L, "Jane Doe", 1, 1, 2025);

        // Verify that the holder of the book is correctly returned
        assertEquals(lib.lookup(9780374292799L), "Jane Doe");
    }

    @Test
    public void testCheckoutAlreadyCheckedOut() {
        Library<String> lib = new Library<>();
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");

        // First checkout
        lib.checkout(9780374292799L, "Jane Doe", 1, 1, 2025);

        // Try checking out again, which should fail
        assertFalse(lib.checkout(9780374292799L, "John Smith", 1, 1, 2025));
    }

    @Test
    public void testCheckinBookNotCheckedOut() {
        Library<String> lib = new Library<>();
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");

        // Attempt to check in a book that hasn't been checked out
        assertFalse(lib.checkin(9780374292799L));
    }

    @Test
    public void testCheckinNonExistentBook() {
        Library<String> lib = new Library<>();

        // Try checking in a book that's not in the library
        assertFalse(lib.checkin(1234567890123L));
    }

    @Test
    public void stringLibraryTest() {
        // test a library that uses names (String) to id patrons
        Library<String> lib = new Library<>();
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        String patron1 = "Jane Doe";

        assertTrue(lib.checkout(9780330351690L, patron1, 1, 1, 2008));
        assertTrue(lib.checkout(9780374292799L, patron1, 1, 1, 2008));

        var booksCheckedOut1 = lib.lookup(patron1);
        assertEquals(booksCheckedOut1.size(), 2);
        assertTrue(booksCheckedOut1.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
        assertTrue(booksCheckedOut1.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
        assertEquals(booksCheckedOut1.get(0).getHolder(), patron1);
        assertEquals(booksCheckedOut1.get(0).getDueDate(), new GregorianCalendar(2008, 1, 1));
        assertEquals(booksCheckedOut1.get(1).getHolder(),patron1);
        assertEquals(booksCheckedOut1.get(1).getDueDate(),new GregorianCalendar(2008, 1, 1));

        assertTrue(lib.checkin(patron1));

    }

    @Test
    public void phoneNumberTest(){
        // test a library that uses phone numbers (PhoneNumber) to id patrons
        var lib = new Library<PhoneNumber>();
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        PhoneNumber patron2 = new PhoneNumber("801.555.1234");

        assertTrue(lib.checkout(9780330351690L, patron2, 1, 1, 2008));
        assertTrue(lib.checkout(9780374292799L, patron2, 1, 1, 2008));

        ArrayList<LibraryBook<PhoneNumber>> booksCheckedOut2 = lib.lookup(patron2);

        assertEquals(booksCheckedOut2.size(), 2);
        assertTrue(booksCheckedOut2.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
        assertTrue(booksCheckedOut2.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
        assertEquals(booksCheckedOut2.get(0).getHolder(),patron2);
        assertEquals(booksCheckedOut2.get(0).getDueDate(),new GregorianCalendar(2008, 1, 1));
        assertEquals(booksCheckedOut2.get(1).getHolder(), patron2);
        assertEquals(booksCheckedOut2.get(1).getDueDate(), new GregorianCalendar(2008, 1, 1));

        assertTrue(lib.checkin(patron2));

    }

    @Test
    public void isbnSortTest(){
        var lib = new Library<String>();
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");

        ArrayList<LibraryBook<String>> sortedLibraryBooks= lib.getInventoryList();
        assertEquals(sortedLibraryBooks.getFirst().getIsbn(), 9780330351690L);
        assertEquals(sortedLibraryBooks.get(2).getIsbn(), 9780446580342L);

    }

    @Test
    public void authorSortTest(){
        var lib = new Library<String>();
        lib.add(9780446580342L, "Moyra Caldecott", "The Eye of Callanish");
        lib.add(9780374292799L, "Moyra Caldecott", "Crystal Legends");
        lib.add(9780330351690L, "Abel Adams", "Into the Wild");

        ArrayList<LibraryBook<String>> sortedLibraryBooks= lib.getOrderedByAuthor();
        assertEquals(sortedLibraryBooks.getFirst().getAuthor(), "Abel Adams");
        assertEquals(sortedLibraryBooks.get(2).getTitle(), "The Eye of Callanish");

    }

    @Test
    public void overdueTest(){
        var lib = new Library<String>();
        lib.add(9780446580342L, "Moyra Caldecott", "The Eye of Callanish");
        lib.add(9780374292799L, "Moyra Caldecott", "Crystal Legends");
        lib.add(9780330351690L, "Abel Adams", "Into the Wild");
        lib.add(9780330334590L, "Michael Adams", "From the Wild");

        lib.checkout(9780446580342L, "Jane Doe", 11, 6, 2024);
        lib.checkout(9780374292799L, "Jane Doe", 10, 5, 2024);
        lib.checkout(9780330351690L, "Jane Doe", 10, 3, 2024);

        ArrayList<LibraryBook<String>> overdueBooks = lib.getOverdueList(11,6,2024);
        assertEquals(overdueBooks.getFirst().getIsbn(), 9780330351690L);
        assertNotEquals(overdueBooks.size(), 3);
        assertEquals(overdueBooks.get(1).getIsbn(), 9780374292799L);

    }


}