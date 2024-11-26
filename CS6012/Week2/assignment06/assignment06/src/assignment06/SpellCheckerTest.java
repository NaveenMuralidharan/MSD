package assignment06;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class SpellCheckerTest {
    //Test that the constructor correctly builds the dictionary from a list of words.
    @Test
    public void testConstructorWithList() {
        List<String> words = Arrays.asList("apple", "banana", "cherry");
        SpellChecker spellChecker = new SpellChecker(words);
        BinarySearchTree<String> dictionary = spellChecker.getDictionary();
        assertTrue(dictionary.contains("apple"));
        assertTrue(dictionary.contains("banana"));
        assertTrue(dictionary.contains("cherry"));
    }

    //Test that the constructor correctly builds the dictionary from a file.
    @Test
    public void testConstructorWithFile() {
        File dictionaryFile = new File("src/dictionary.txt"); // Assume file contains apple, banana, cherry
        SpellChecker spellChecker = new SpellChecker(dictionaryFile);
        BinarySearchTree<String> dictionary = spellChecker.getDictionary();

        assertTrue(dictionary.contains("juniper"));
        assertTrue(dictionary.contains("magician"));
        assertTrue(dictionary.contains("caffeine"));
    }

    //Test adding a word to the dictionary.
    @Test
    public void testAddWordToDictionary() {
        SpellChecker spellChecker = new SpellChecker();
        spellChecker.addToDictionary("apple");
        BinarySearchTree<String> dictionary = spellChecker.getDictionary();

        assertTrue(dictionary.contains("apple"));
    }

    //Test removing a word from the dictionary.
    @Test
    public void testRemoveWordFromDictionary() {
        SpellChecker spellChecker = new SpellChecker();
        spellChecker.addToDictionary("apple");
        spellChecker.removeFromDictionary("apple");
        BinarySearchTree<String> dictionary = spellChecker.getDictionary();

        assertFalse(dictionary.contains("apple"));
    }

    //Test the spell-checking functionality with a document file.
    @Test
    public void testSpellCheckWithDocument() {
        File dictionaryFile = new File("src/dictionary.txt"); // Contains apple, banana, cherry
        SpellChecker spellChecker = new SpellChecker(dictionaryFile);
        File documentFile = new File("src/document.txt"); // Contains apple, graple, cherry

        List<String> misspelledWords = spellChecker.spellCheck(documentFile);
        assertTrue(misspelledWords.contains("graple"));
        assertFalse(misspelledWords.contains("apple"));
        assertFalse(misspelledWords.contains("cherry"));
    }

    //Test that an empty document file returns an empty list of misspelled words.
    @Test
    public void testSpellCheckWithEmptyDocument() {
        File dictionaryFile = new File("src/dictionary.txt");
        SpellChecker spellChecker = new SpellChecker(dictionaryFile);
        File emptyDocumentFile = new File("src/empty_document.txt");

        List<String> misspelledWords = spellChecker.spellCheck(emptyDocumentFile);
        assertTrue(misspelledWords.isEmpty());
    }

    //Test that special characters in the document are ignored during spell-checking.
    @Test
    public void testSpellCheckWithSpecialCharacters() {
        File dictionaryFile = new File("src/dictionary.txt");
        SpellChecker spellChecker = new SpellChecker(dictionaryFile);
        File documentFile = new File("src/special_characters_document.txt"); // Contains apple, banana!, &grape?

        List<String> misspelledWords = spellChecker.spellCheck(documentFile);
        assertFalse(misspelledWords.contains("stupid"));
        assertTrue(misspelledWords.contains("graple"));
    }

    //Test handling of a non-existent dictionary or document file.
    @Test
    public void testReadFromNonExistentFile() {
        File nonExistentFile = new File("src/non_existent_file.txt");

        SpellChecker spellChecker = new SpellChecker(nonExistentFile); // Should handle file not found
        BinarySearchTree<String> dictionary = spellChecker.getDictionary();

        assertTrue(dictionary.isEmpty());
    }

}