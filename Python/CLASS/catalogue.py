# Class that uses objects from Country Class to create a collection of countries
class CountryCatalogue:
    # Constructor for the class CountryCatalogue with instance variables
    def __init__(self, continentFile, countryDataFile):
        self._countryCat = {}
        self._cDictionary = {}

        # Reading the file containing continents and slotting them into their own dictionary
        continent = open(continentFile, "r")
        line = continent.readline()
        line = continent.readline()
        while line != "":
            line = line.strip("\n").split(",")
            self._cDictionary[line[0]]=line[1]
            line = continent.readline()
        continent.close()

        # Reading the country information and creating Country objects to be stored in a dictionary
        from country import Country
        country = open(countryDataFile, "r")
        line = country.readline()
        line = country.readline()
        while line != "":
            line = line.replace(",","").strip("\n").split("|")
            self._countryCat[line[0]] = Country(line[0],line[1],line[2],self._cDictionary[line[0]])
            line = country.readline()
        country.close()

    # Function finds a country within ._countryCat dictionary
    def findCountry(self,c):
        if c in self._countryCat:
            return self._countryCat[c]
        else:
            return "None"

    # Function sets a new population if the country already exists in ._countryCat
    def setPopulationOfCountry(self, c, p):
        if c in self._countryCat:
           country = self._countryCat[c]
           country.setPopulation(p)
           return True
        else:
           return False

    # Function sets a new area if the country already exists in ._countryCat
    def setAreaOfCountry(self, c, a):
        if c in self._countryCat:
            country = self._countryCat[c]
            country.setArea(a)
            return True
        else:
            return False

    # Function adds a new country object to ._countryCat if it doesn't already exist
    def addCountry(self, c, p, a, ct):
        from country import Country
        if c not in self._countryCat:
            self._countryCat[c] = Country(c,p,a,ct)
            self._cDictionary[c] = ct
            return True
        else:
            return False

    # Function removes a country from ._countryCat if it exists
    def deleteCountry(self,c):
        if c in self._countryCat:
            self._countryCat.pop(c)
            self._cDictionary.pop(c)
        return ""

    # Functions prints all contents of ._countryCat
    def printCountryCatalogue(self):
        for el in self._countryCat:
            country = self._countryCat[el]
            self._countryCat[el] = country.__str__()
        return print(self._countryCat)

    # Function returns all country objects that are found in continent parameter
    def getCountriesByContinent(self,ct):
        listOfCountries = []
        for el in self._countryCat:
            country = self._countryCat[el]
            if country.getContinent() == ct:
                listOfCountries.append(country.__str__())
        return listOfCountries

    # Functions returns all countries and their population, either only within a continent or all countries
    def getCountriesByPopulation(self,c=""):
        countryAndPopulation = []
        listOfPopulations = []
        if c == "":
            for el in self._countryCat:
                country = self._countryCat[el]
                countryAndPopulation.append(country.getName())
                countryAndPopulation.append(country.getPopulation())
                listOfPopulations.append(countryAndPopulation)
                countryAndPopulation = []
            listOfPopulations.sort(key=lambda tup: tup[1], reverse=True)
            return listOfPopulations
        elif c != "":
            for el in self._countryCat:
                country = self._countryCat[el]
                if country.getContinent() == c:
                    countryAndPopulation.append(country.getName())
                    countryAndPopulation.append(country.getPopulation())
                    listOfPopulations.append(countryAndPopulation)
                    countryAndPopulation = []
                listOfPopulations.sort(key=lambda tup: tup[1], reverse=True)
            return listOfPopulations
        else:
            return listOfPopulations

    # Function returns countries and their areas, either only within a continent or all countries
    def getCountriesByArea(self,c=""):
        countryAndArea = []
        listOfAreas = []
        if c == "":
            for el in self._countryCat:
                country = self._countryCat[el]
                countryAndArea.append(country.getName())
                countryAndArea.append(country.getArea())
                listOfAreas.append(countryAndArea)
                countryAndArea = []
            listOfAreas.sort(key=lambda tup: tup[1], reverse=True)
            return listOfAreas
        elif c != "":
            for el in self._countryCat:
                country = self._countryCat[el]
                if country.getContinent() == c:
                    countryAndArea.append(country.getName())
                    countryAndArea.append(country.getArea())
                    listOfAreas.append(countryAndArea)
                    countryAndArea = []
            listOfAreas.sort(key=lambda tup: tup[1], reverse=True)
            return listOfAreas
        else:
            return listOfAreas

    # Function returns the continent that has the highest total population and the total population
    def findMostPopulousContinent(self):
        dictionary = {}
        continentAndPop = ["",0]
        for el in self._countryCat:
            country = self._countryCat[el]
            if country.getContinent() in dictionary:
                dictionary[country.getContinent()] = int(dictionary[country.getContinent()])+ int(country.getPopulation())
            elif country.getContinent() not in dictionary:
                dictionary[country.getContinent()] = country.getPopulation()
        for el in self._cDictionary:
            continent = self._cDictionary[el]
            if int(dictionary[continent]) > int(continentAndPop[1]):
                continentAndPop = [continent,dictionary[continent]]
        return continentAndPop

    # Function returns a list of countries that fit within the boundaries of specific population densities
    def filterCountriesByPopDensity(self,low=0,high=0):
        countryAndPopD = []
        listOfCountriesByPopD = []
        for el in self._countryCat:
            country = self._countryCat[el]
            if country.getPopDensity() >= low and country.getPopDensity() <= high:
                countryAndPopD.append(country.getName())
                countryAndPopD.append(country.getPopDensity())
                listOfCountriesByPopD.append(countryAndPopD)
                countryAndPopD = []
        listOfCountriesByPopD.sort(key=lambda tup: tup[1],reverse=True)
        return listOfCountriesByPopD

    # Function writes items found in ._countryCat to a new file and returns number of lines written
    def saveCountryCatalogue(self, saveFile):
        newFile = open(saveFile, "w")
        listOfCountries = []
        for el in self._countryCat:
            country = self._countryCat[el]
            listOfCountries.append(country.getSaveFormat()+str(country.getPopDensity())+"\n")
        listOfCountries.sort()
        count = 0
        for i in range(0, len(listOfCountries)):
            newFile.write(listOfCountries[i])
            count = count+1
        print(count)
        newFile.close()
        if count == 0:
            return -1
        else:
            return count
