package assignment05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class WebBrowserTest {
    private WebBrowser browser;

    @BeforeEach
    void setUp() {
        browser = new WebBrowser();
    }

    @Test
    void testVisitAndBack() throws Exception  {
        // Test visiting pages and going back
        URL page1 = new URL("https://example.com");
        URL page2 = new URL("https://example.org");
        URL page3 = new URL("https://example.net");

        browser.visit(page1);
        browser.visit(page2);
        browser.visit(page3);

        // After visiting three pages, the back page should be page2
        assertEquals("https://example.org", browser.back().toString());  // Go back to page2
        assertEquals("https://example.com", browser.back().toString());  // Go back to page1

        // If we try to go back again, it should throw an exception since there's no history left
        assertThrows(NoSuchElementException.class, () -> browser.back());
    }

    @Test
    public void testForward() throws Exception {
        // Test the forward functionality after using back
        URL page1 = new URL("https://example.com");
        URL page2 = new URL("https://example.org");
        URL page3 = new URL("https://example.net");

        browser.visit(page1);
        browser.visit(page2);
        browser.visit(page3);

        browser.back();  // Go back to page2
        browser.back();  // Go back to page1

        // Now we should be able to go forward again
        assertEquals("https://example.org", browser.forward().toString());
        assertEquals("https://example.net", browser.forward().toString());

        // If we try to go forward again, it should throw an exception
        assertThrows(NoSuchElementException.class, () -> browser.forward());
    }

    @Test
    public void testHistory() throws Exception {
        // Test the history method
        URL page1 = new URL("https://example.com");
        URL page2 = new URL("https://example.org");
        URL page3 = new URL("https://example.net");

        browser.visit(page1);
        browser.visit(page2);
        browser.visit(page3);

        SinglyLinkedList<URL> history = browser.history();

        // Check that the history contains the correct order of pages
        assertEquals("https://example.net", history.get(0).toString());  // Most recent page
        assertEquals("https://example.org", history.get(1).toString());
        assertEquals("https://example.com", history.get(2).toString());  // Least recent page
    }

    @Test
    public void testVisitAndClearForwardStack() throws Exception {
        // Test that the forward stack is cleared when a new page is visited
        URL page1 = new URL("https://example.com");
        URL page2 = new URL("https://example.org");
        URL page3 = new URL("https://example.net");

        browser.visit(page1);
        browser.visit(page2);
        browser.visit(page3);

        browser.back();  // Go back to page2
        browser.back();  // Go back to page1

        // After going back, the forward stack should contain page2 and page3
        assertEquals("https://example.org", browser.forward().toString());
        assertEquals("https://example.net", browser.forward().toString());

        // Now, visit a new page. The forward stack should be cleared.
        URL page4 = new URL("https://example.edu");
        browser.visit(page4);

        // Attempt to go forward should now throw an exception as forward stack is cleared
        assertThrows(NoSuchElementException.class, () -> browser.forward());
    }

    @Test
    public void testConstructorWithPreloadedHistory() throws Exception {
        // Test the constructor with preloaded history
        SinglyLinkedList<URL> preloadedHistory = new SinglyLinkedList<>();
        preloadedHistory.insertFirst(new URL("https://example.com"));
        preloadedHistory.insertFirst(new URL("https://example.org"));
        preloadedHistory.insertFirst(new URL("https://example.net"));

        WebBrowser preloadedBrowser = new WebBrowser(preloadedHistory);

        // The first element in preloadedHistory is the most recently visited
        assertEquals(3, preloadedBrowser.history().size());
        assertEquals("https://example.net", preloadedBrowser.history().get(0).toString());
        assertEquals("https://example.org", preloadedBrowser.history().get(1).toString());
        assertEquals("https://example.com", preloadedBrowser.history().get(2).toString());
    }





}