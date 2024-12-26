package com.interview.prep.mediums.grind75.week2;

import java.util.HashMap;
import java.util.Map;

/**
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
 *
 * Implement the Trie class:
 *     Trie() Initializes the trie object.
 *     void insert(String word) Inserts the string word into the trie.
 *     boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 *     boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 *
 * Example 1:
 * Input
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output
 * [null, null, true, false, true, null, true]
 *
 * Explanation
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // return True
 * trie.search("app");     // return False
 * trie.startsWith("app"); // return True
 * trie.insert("app");
 * trie.search("app");     // return True
 */
public class ImplementTrie {

    public static class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfWord;

        public TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
        }
    }

    TrieNode root;
    public ImplementTrie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the Trie.
     * Time complexity: O(n), where n is the length of the word.
     * Space complexity: O(n), for the nodes created during insertion.
     */
    public void insert(String word) {
        TrieNode currentNode = root;
        for (int i = 0; i < word.length(); i++) {
            char currentLetter = word.charAt(i);
            if (!currentNode.children.containsKey(currentLetter)) {
                currentNode.children.put(currentLetter, new TrieNode());
            }
            currentNode = currentNode.children.get(currentLetter);
        }
        currentNode.endOfWord = true;
    }

    /**
     * Checks if the word exists in the Trie.
     * Time complexity: O(n), where n is the length of the word.
     * Space complexity: O(1)
     */
    public boolean search(String word) {
        TrieNode currentNode = root;
        for (int i = 0; i < word.length(); i++) {
            char currentLetter = word.charAt(i);
            if (!currentNode.children.containsKey(currentLetter)) {
                return false;
            }
            currentNode = currentNode.children.get(currentLetter);
        }
        return currentNode.endOfWord;
    }

    /**
     * Checks if there is any word in the Trie that starts with the given prefix.
     * Time complexity: O(n), where n is the length of the prefix.
     * Space complexity: O(1)
     */
    public boolean startsWith(String prefix) {
        TrieNode currentNode = root;
        for (int i = 0; i < prefix.length(); i++) {
            char currentLetter = prefix.charAt(i);
            if (!currentNode.children.containsKey(currentLetter)) {
                return false;
            }
            currentNode = currentNode.children.get(currentLetter);
        }
        return true;
    }
}
