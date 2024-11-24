package assignment05;

import java.net.URL;
import java.util.NoSuchElementException;

public class WebBrowser {
    private LinkedListStack<URL> backButton;
    private LinkedListStack<URL> forwardButton;

    public WebBrowser(){
        backButton = new LinkedListStack<>();
        forwardButton = new LinkedListStack<>();
    }

    public WebBrowser(SinglyLinkedList<URL> history){
        backButton = new LinkedListStack<>();
        forwardButton = new LinkedListStack<>();
        System.out.println(history.size());
        System.out.println(history.get(0));
        for(int i = history.size()-1; i <= 0 ; i++){
            System.out.println(history.get(i));
            backButton.push(history.get(i));
        }
    }

    public void visit(URL webpage){
        this.backButton.push(webpage);
        this.forwardButton.clear();
    }

    public URL back() throws NoSuchElementException{
        if(backButton.isEmpty()){
            throw new NoSuchElementException("No previous webpage to visit");
        }
        URL recent = backButton.pop();
        forwardButton.push(recent);

        if(backButton.isEmpty()){
            throw new NoSuchElementException("No previous webpage to visit");
        }
        return backButton.peek();
    }

    public URL forward() throws NoSuchElementException{
        if(forwardButton.isEmpty()){
            throw new NoSuchElementException("No next webpage to visit");
        }
        URL recent = forwardButton.pop();
        backButton.push(recent);
        return recent;
    }

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
