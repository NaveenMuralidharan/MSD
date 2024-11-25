package assignment05;

import java.net.URL;
import java.util.NoSuchElementException;
/**
 * The WebBrowser class simulates a basic web browser's back and forward navigation functionality.
 * It maintains two stacks: one for the back button history and another for the forward button history.
 * The back stack stores the URLs of previously visited webpages, while the forward stack stores
 * the URLs of webpages that can be revisited after going back.
 *
 * The class supports operations like visiting a webpage, going back, going forward, and retrieving
 * the history of visited pages.
 */
public class WebBrowser {
    // Stack for keeping track of the back history (visited pages).
    private LinkedListStack<URL> backButton;
    // Stack for keeping track of the forward history (pages that can be revisited).
    private LinkedListStack<URL> forwardButton;

    /**
     * Constructs a WebBrowser with empty history, where both back and forward button histories are empty.
     */
    public WebBrowser(){
        backButton = new LinkedListStack<>();
        forwardButton = new LinkedListStack<>();
    }
    /**
     * Constructs a WebBrowser with an initial browsing history, where the most recent page is at the top of the back stack.
     * The forward history stack is initially empty.
     *
     * @param history A list of URLs representing the browsing history. The first URL in the list is the current page.
     */
    public WebBrowser(SinglyLinkedList<URL> history) throws NoSuchElementException{

        backButton = new LinkedListStack<>();
        forwardButton = new LinkedListStack<>();
//        if(history.isEmpty()){
//            throw new NoSuchElementException("history cannot be empty");
//        }
        for(int i = history.size()-1; i >= 0 ; i--){

            backButton.push(history.get(i));
        }
    }
    /**
     * Simulates visiting a new webpage. The current webpage is added to the back history stack,
     * and the forward history stack is cleared.
     *
     * @param webpage The URL of the webpage to visit.
     */
    public void visit(URL webpage){
        this.backButton.push(webpage);
        this.forwardButton.clear();
    }
    /**
     * Simulates going back to the previous webpage. If there is a previous page, it is returned,
     * and the current page is moved to the forward history stack.
     * If no previous page exists, a NoSuchElementException is thrown.
     *
     * @return The URL of the previous webpage.
     * @throws NoSuchElementException If there is no previous page to visit.
     */

    public URL back() throws NoSuchElementException{
        if(backButton.isEmpty()){
            throw new NoSuchElementException("No previous webpage to visit");
        }
        URL recent = backButton.pop();
        forwardButton.push(recent);

//        if(backButton.isEmpty()){
//            throw new NoSuchElementException("No previous webpage to visit");
//        }
        return backButton.peek();
    }
    /**
     * Simulates going forward to the next webpage. If there is a next page, it is returned,
     * and the current page is moved to the back history stack.
     * If no next page exists, a NoSuchElementException is thrown.
     *
     * @return The URL of the next webpage.
     * @throws NoSuchElementException If there is no next page to visit.
     */

    public URL forward() throws NoSuchElementException{
        if(forwardButton.isEmpty()){
            throw new NoSuchElementException("No next webpage to visit");
        }
        URL recent = forwardButton.pop();
        backButton.push(recent);
        return recent;
    }
    /**
     * Retrieves the browsing history as a list of URLs, ordered from most recently visited to least visited.
     * The history includes the current webpage and excludes the forward stack (pages that can be revisited).
     *
     * @return A SinglyLinkedList containing the URLs of visited webpages in order from most recent to least recent.
     */
    public SinglyLinkedList<URL> history() {
        SinglyLinkedList<URL> historyList = new SinglyLinkedList<>();

        // Create a temporary stack to reverse the order of backButton for history
        LinkedListStack<URL> tempStack = new LinkedListStack<>();

        // Pop all elements from backStack to tempStack to reverse the order
        while (!backButton.isEmpty()) {
            tempStack.push(backButton.pop());
        }

        // Push them back to backButton to restore it, and add to the historyList
        while (!tempStack.isEmpty()) {
            URL webpage = tempStack.pop();
            historyList.insertFirst(webpage);
            backButton.push(webpage);  // Restore the back stack
        }

        return historyList;
    }



}
