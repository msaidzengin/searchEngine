# searchEngine

This is the BIL 212 Data Structures Homework 3. It was completed on 22 January 2019.

It is a desktop search engine for files in a local folder. `SearchEngine` reads each file in the folder you give, keeps ASCII letters, lowercases them, and counts the words with a custom hash table that uses separate chaining. It writes that inverted index to a binary file named `invertedIndex`. `DesktopSearch` loads the index and, for each word, prints up to five file names with the highest counts, then the search time.

The assignment text is in `bil212summer2018hw3.pdf`. The sample documents are in `food_txt`.

## Run

From the project directory:

```bash
javac -d out src/*.java
java -cp out SearchEngine
```

When asked for a folder, enter a path such as `food_txt`. Then, from the same directory:

```bash
java -cp out DesktopSearch
```

Type a word at the prompt. The prompt repeats until the word is `0`.
