Problem

 

Using the dictionary available on most *nixmachines (/usr/share/dict/words), print to stdout the number of anagram setsthat exist in that dictionary.  Ananagram set is a group of words that are anagrams of one another.  Singletons are not anagram sets.  For example,the following is an anagram set:

 

team, meat, mate, meta, tame

 

Your program must also print to ¡°out.txt¡± aline-delimited list of these anagram lists starting with the sets that containthe most words.  E.g.

 

> cat out.txt

team, meat, mate, meta, tame

¡­.. (sets that contain 4 words)

¡­.. (sets that contain 3 words)

dog, god

its, tis

 