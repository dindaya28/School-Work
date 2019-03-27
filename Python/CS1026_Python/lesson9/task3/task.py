# Replace the placeholders with code and run the Python program

sentence ="I had such a horrible day. It was awful, so bad, sigh. It could not have been worse but actually though "\
          +"such a terrible horrible awful bad day."

makeItHappy ={"horrible":"amazing","bad":"good","awful":"awesome","worse":"better","terrible":"great"}

sentence = sentence.split()
for word in sentence:
    if "," in word:
        comma = word.replace(",","")
        if comma in makeItHappy:
            sentence[sentence.index(word)] = makeItHappy[comma] + ","
    if word in makeItHappy:
        sentence[sentence.index(word)] = makeItHappy[word]

newString=""

for word in sentence:
    newString = newString + word + " "

print(newString)

