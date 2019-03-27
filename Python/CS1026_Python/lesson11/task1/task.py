# Replace the placeholders with code and run the Python program

# Define class Fruit
class Fruit():
    def __init__(self):
        self._colour = ""
        self._shape=""


    def canBePhone(self):
        return "fruits can't be phones..."

# Define class Banana
class Banana(Fruit):
    def __init__(self):
        super().__init__()

    def canBePhone(self):
        return "Ring ring ring ring ring ring ring banana phone\nBoop-boo-ba-doo-ba-doop"

apple=Fruit()
print(apple.canBePhone())
banana=Banana()
print(banana.canBePhone())
