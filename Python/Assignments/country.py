# Country class creates country objects that contain specific attributes of population, area, and continent
class Country:
    def __init__(self, n="", p=0, a=0.0, c=""):
        self._nameOfCountry = n
        self._population = int(p)
        self._countryArea = float(a)
        self._continent = c
        self._popDensity = 0

    # Returns the name of the country object
    def getName(self):
        return self._nameOfCountry

    # Returns the population of the country object
    def getPopulation(self):
        return self._population

    # Returns the area of the country object
    def getArea(self):
        return self._countryArea

    # Returns the continent of the country object
    def getContinent(self):
        return self._continent

    # Calculates and returns the population density of the country object
    def getPopDensity(self):
        self._popDensity = float(self._population)/float(self._countryArea)
        return self._popDensity

    # Updates the existing population to a new number entered by the user
    def setPopulation(self, newPop=0):
        self._population = int(newPop)

    # Updates the existing area to a new number entered by the user
    def setArea(self, newArea=0.0):
        self._countryArea = float(newArea)

    # Updates the existing continent to a new value entered by the user
    def setContinent(self, c):
        self._continent = c

    # Assists with writing country objects to the new save file within Class CountryCatalogue
    def getSaveFormat(self):
        return self._nameOfCountry+"|"+self._continent+"|"+str(self._population)+"|"+("%.2f"%round(self._countryArea,2))+"|"

    # Returns a string containing information relevant to the country object that is created
    def __repr__(self):
        return self._nameOfCountry+" (pop: "+str(self._population)+", size:"+("%.2f"%round(self._countryArea,2))+")"+" in "+self._continent
