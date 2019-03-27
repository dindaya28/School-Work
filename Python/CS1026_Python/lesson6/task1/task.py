# Replace the placeholders and complete the Python program.

def moreThanTwo (words):
    count=0
    for el in words:
        #print(i)
        if len(el) > 2:
            count+=1
    return count

# Create a list of words
words = ["hello","good","nice","as","at","baseball","absorb","sword","a","tall","so","bored","silver",
       "hi","pool","we","I","am","seven","Do","you","want","ants","because","that's","how","you","get"]

# Call the function and print the number of words with more than two letters.
print(moreThanTwo(words))
