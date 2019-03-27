class Movie:
    def __init__(self,t,y):
        self._title = t
        self._year = y
        self._rating = 0
        self._cast = set()
        self._genre = ""

    def getTitle(self):
        return self._title

    def getYear(self):
        return self._year

    def getCast(self):
        return self._cast

    def getRating(self):
        return self._rating

    def setGenre(self, g):
        self._genre = g

    def setRating(self, r):
        self._rating = r

    def addActor(self, a):
        if isinstance(a, str):
            self._cast.add(a)

m1 = Movie("incredibles 2", 2018)
m1.setGenre('Animation')
m1.setRating(1000)

m2 = Movie('Shrek', 2001)
m2.setGenre('Animation')
m2.setRating(4)

print(m2.getTitle())
print(m2.getYear())

class Netflix:
    _lastID = 1000
    def __index__(self,n,a):
        self._accountType = a
        self._name = n
        self._favourites = set()
        self._lastID = self._lastID + 1
        self._id = self._lastID

    def getName(self):
        return self._name

    def getID(self):
        return self._id

    def getFave(self):
        return self._favourites

    def setAccountType(self,ac):
        self._accountType = ac

    def addFavouriteMovie(self, m):
        if isinstance(m, Movie):
            self._favourites.add(m)
            print(m.getTitle(), "has been added")
        else:
            print("this is not a movie fam")

    def removeMovie(self,m):
        self._favourites.discard(m)

    def findMoviesByGenre(self,ge):
        listMovies = []
        for mo in self._favourites:
            if mo.getGenre() == ge:
                listMovies.append(mo.getTitle())
        return listMovies

    def findMovieWithHighestRating(self):
        rating = -1
        movieName = ""
        for el in self._favourites:
            if el.getRating() > rating:
                rating = el.getRating()
                movieName = el.getTitle()
        return movieName # returns just a movie title

    def findMovieWithHighestRating2(self):
        x = sorted(self._favourites, key = Movie.getRating, reverse = True)
        return x[0].getTitle()  # returns a list
