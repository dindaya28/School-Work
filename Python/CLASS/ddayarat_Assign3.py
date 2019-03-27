# Lists
listOfKeywords = []
pacificScore = []
mountainScore = []
centralScore = []
easternScore = []

# Function for calculating happiness score within each tweet
def happinessCalculator(line):
    wordValue = 0
    numberOfKeywords = 0
    for el in line[5:]:
        for i in listOfKeywords[0:]:
            if el in i:
                numberOfKeywords = numberOfKeywords + 1
                wordValue = wordValue + int(listOfKeywords[listOfKeywords.index(i)][1])
    try:
        sentimentValue = wordValue/numberOfKeywords
    except ZeroDivisionError:
        sentimentValue = 0
    return sentimentValue

try:
    # Prompt user for file input
    keywords = open(input("Enter the file name containing keywords: "), "r", encoding= "utf-8")
    line = keywords.readline()
    while line != "":
        line = line.strip("\n")
        line = line.split(",")
        listOfKeywords.append(line)
        line = keywords.readline()
    keywords.close()
# Exits the program if the input file is not found
except IOError:
    print("Error: file not found.")
    exit()

try:
    # prompt user for tweets file
    tweets = open(input("Enter the file name containing tweets: "), "r", encoding="utf-8")
    easternTweets = 0
    centralTweets = 0
    mountainTweets = 0
    pacificTweets = 0
    for tweetLine in tweets:
        tweetLine = tweetLine.lower()
        tweetLine = tweetLine.replace(",","").strip("[").split()
        # Removing the punctuation from the tweets to ensure all keywords are found
        for el in tweetLine:
            tweetLine[tweetLine.index(el)] = tweetLine[tweetLine.index(el)].strip("!@#$%^&*()_]")
        # Assigning the latitude and longitude variables
        latitude = float(tweetLine[0])
        longitude = float(tweetLine[1])
        # Determination of which region the tweet is located in based on coordinates
        if latitude >= 24.660845 and latitude <= 49.189787:
            if longitude >= -87.518395 and longitude <= -67.444574: # If statement for Eastern Region
                easternValue = happinessCalculator(line=tweetLine)
                if easternValue == 0:
                    easternTweets = easternTweets
                else:
                    easternTweets = easternTweets + 1
                    easternScore.append(easternValue)
            elif longitude >= -101.998892 and longitude < -87.518395:  # If statement for Central Region
                centralValue = happinessCalculator(line=tweetLine)
                if centralValue == 0:
                    centralTweets = centralTweets
                else:
                    centralTweets = centralTweets + 1
                    centralScore.append(centralValue)
            elif longitude >= -115.236428 and longitude < -101.998892:  # If statement for Mountain Region
                mountainValue = happinessCalculator(line=tweetLine)
                if mountainValue == 0:
                    mountainTweets = mountainTweets
                else:
                    mountainTweets = mountainTweets + 1
                    mountainScore.append(mountainValue)
            elif longitude >= -125.242264 and longitude < -115.236428:  # If statement for Pacific Region
                pacificValue = happinessCalculator(line=tweetLine)
                if pacificValue == 0:
                    pacificTweets = pacificTweets
                else:
                    pacificTweets = pacificTweets + 1
                    pacificScore.append(pacificValue)
    tweets.close()

    # Final happiness scores
    try:
        pacificScore = sum(pacificScore)/pacificTweets
    except ZeroDivisionError:
        pacificTweets = 0
        pacificScore = 0
    try:
        mountainScore = sum(mountainScore)/mountainTweets
    except ZeroDivisionError:
        mountainTweets = 0
        mountainScore = 0
    try:
        centralScore = sum(centralScore)/centralTweets
    except ZeroDivisionError:
        centralTweets = 0
        centralScore = 0
    try:
        easternScore = sum(easternScore)/easternTweets
    except ZeroDivisionError:
        easternTweets = 0
        easternScore = 0

    # Final print statements containing the happiness scores and number of tweets found in each timezone.
    print("The happiness score for the Pacific timezone is ", pacificScore, ", from", pacificTweets, "number of tweets!")
    print("The happiness score for the Mountain timezone is ", mountainScore, ", from", mountainTweets, "number of tweets!")
    print("The happiness score for the Central timezone is ", centralScore, ", from", centralTweets, "number of tweets!")
    print("The happiness score for the Eastern timezone is ", easternScore, ", from", easternTweets, "number of tweets!")

    # Graphics associated with the resulting scores from the program
    from happy_histogram import drawSimpleHistogram
    drawSimpleHistogram(eval=easternScore,cval=centralScore,mval=mountainScore,pval=pacificScore)

# Exits the program if the file input is not found
except IOError:
    print("Error: file not found.")
    exit()
