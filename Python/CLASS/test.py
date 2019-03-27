# Lists
listOfKeywords = []
pacificScore = []
mountainScore = []
centralScore = []
easternScore = []

# Function for calculating happiness score within each tweet
def happinessCalculator():
    wordValue = 0
    for el in range(5, len(line)):  # Need to make this a function to repeat within the other timezones
        if el in listOfKeywords:
            wordValue = wordValue + int(listOfKeywords[listOfKeywords.index(el)][1])
            #print(el) # REMOVE AFTER TESTING
        #else: DON'T NEED THIS SHIT
            #pass
    return wordValue

def clean():
    for el in range(int(line[5]), len(line)):
        line[el] = line[el].strip(".,/*@#!$%^&()_-:;\n")
    return line

try:
    # Prompt user for file input
    keywords = open(input("Enter the file name containing keywords: "), "r")
    line = keywords.readline()
    while line != "":
        line = line.strip("\n")
        line = line.split(",")
        listOfKeywords.append(line)
        line = keywords.readline()
    keywords.close()
    #print(listOfKeywords)
except IOError:
    print("Error: file not found.")

try:
    # prompt user for tweets file
    tweets = open(input("Enter the file name containing tweets: "), "r")
    tweetLine = tweets.readline()
    while tweetLine != "":
        print(tweetLine)
        tweetLine = tweetLine.lower()
        tweetLine = tweetLine.replace(",","").strip("[").lower().split()
        tweetLine[1] = tweetLine[1].rstrip("]")
        latitude = float(tweetLine[0])
        longitude = float(tweetLine[1])
        easternTweets = 0
        centralTweets = 0
        mountainTweets = 0
        pacificTweets = 0
        if 24.660845 <= latitude <= 49.189787:
            if -87.518395 <= longitude <= -67.444574:
                #clean()
                easternTweets = easternTweets + 1
                happinessCalculator()
                easternScore.append(happinessCalculator())
            elif -101.998892 <= longitude < -87.518395:
                #clean()
                centralTweets = centralTweets + 1
                happinessCalculator()
                centralScore.append(happinessCalculator())
            elif -115.236428 <= longitude < -101.998892:
                #clean()
                mountainTweets = mountainTweets + 1
                happinessCalculator()
                mountainScore.append(happinessCalculator())
            elif -125.242264 <= longitude < -115.236428:
                #clean()
                pacificTweets = pacificTweets + 1
                happinessCalculator()
                pacificScore.append(happinessCalculator())
        else:
            line = tweets.readline()

    tweets.close()
except IOError:
    print("Error: file not found.")

pacificScore = sum(pacificScore)/pacificTweets
mountainScore = sum(mountainScore)/mountainTweets
centralScore = sum(centralScore)/centralTweets
easternScore = sum(easternScore)/easternTweets

print("The happiness score for the Pacific timezone is ", pacificScore, ", from", pacificTweets, "number of tweets!")
print("The happiness score for the Mountain timezone is ", mountainScore, ", from", mountainTweets, "number of tweets!")
print("The happiness score for the Central timezone is ", centralScore, ", from", centralTweets, "number of tweets!")
print("The happiness score for the Eastern timezone is ", easternScore, ", from", easternTweets, "number of tweets!")
