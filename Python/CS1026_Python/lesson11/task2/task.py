# Replace the placeholders with code and run the Python program

# Define class Fruit
class Fruit():
    def __init__(self,clr="",shp="",tst=""):
        self._colour = clr
        self._shape = shp
        self._taste = tst

    def setColour(self,value):
        self._colour = value

    def setShape(self,value):
        self._shape = value

    def setTaste(self,value):
        self._taste = value

    def descriptor(self):
        return self._colour+","+self._shape+","+self._taste

# Define class Banana
class Banana(Fruit):
    def __init__(self,clr,shp,tst):
        super().__init__(clr,shp,tst)

# Define class Apple
class Apple(Fruit):
    def __init__(self,clr,shp,tst):
        super().__init__(clr, shp, tst)
        self._type=""

    def setType(self,value):
        self._type = value

    def descriptor(self):
        if self._type == "":
            return super().descriptor()
        else:
            return super().descriptor()+","+self._type

# Create some objects and test the methods
afruit=Fruit("green","round","sweet")
print("A fruit: ",afruit.descriptor())
bn = Banana("yellow","long","soft and sweet")
print("A banana: ",bn.descriptor())

app1 = Apple("red","round","sweet and crunchy")
print("An apple: ",app1.descriptor())
app1.setType("Gala")
print("A particular apple: ",app1.descriptor())
