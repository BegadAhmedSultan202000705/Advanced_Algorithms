package submisson;

import java.util.*;

public class Spell_checker {
    private Set<String> lexicon;
    
    public Spell_checker(Set<String> lexicon) {
        this.lexicon = lexicon;
    }
    
    public List<String> check(String s) {
        List<String> suggestions = new ArrayList<>();
        if (lexicon.contains(s)) {
            suggestions.add(s);
            return suggestions;
        }
        for (String word : lexicon) {
            if (isSwap(s, word) || isInsert(s, word) || isDelete(s, word) || isReplace(s, word)) {
                suggestions.add(word);
            }
        }
        return suggestions;
    }
    
    // Check if two words differ by swapping adjacent characters
    private boolean isSwap(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        for (int i = 0; i < s1.length() - 1; i++) {
            if (s1.charAt(i) == s2.charAt(i + 1) && s1.charAt(i + 1) == s2.charAt(i)) {
                return true;
            }
        }
        return false;
    }
    
    // Check if one word can be obtained from another by inserting a single character
    private boolean isInsert(String s1, String s2) {
        if (s1.length() != s2.length() - 1) {
            return false;
        }
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i) && s1.charAt(i) != s2.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }
    
    // Check if one word can be obtained from another by deleting a single character
    private boolean isDelete(String s1, String s2) {
        return isInsert(s2, s1);
    }
    
    // Check if one word can be obtained from another by replacing a single character
    private boolean isReplace(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
            }
            if (count > 1) {
                return false;
            }
        }
        return count == 1;
    }


    

    public class Main {
        public static void main(String[] args) {
            // Create a set of words for the lexicon
            Set<String> lexicon = new HashSet<>();
            lexicon.add("hello");
            lexicon.add("world");
            lexicon.add("help");
            lexicon.add("worldwide");
            
            // Create a SpellChecker object with the lexicon
            Spell_checker spellChecker = new Spell_checker(lexicon);
            
            // Test the spell checker with some misspelled words
            String[] words = {"hlelo", "wrold", "hepl", "wroldwide"};
            for (String word : words) {
                List<String> suggestions = spellChecker.check(word);
                if (suggestions.isEmpty()) {
                    System.out.println(word + " is spelled correctly");
                } else {
                    System.out.println("Did you mean one of these words for " + word + "?");
                    System.out.println(suggestions);
                }
            }
        }
    }



































}