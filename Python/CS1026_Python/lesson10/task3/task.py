# Replace the placeholders with code and run the Python program

class Coffee:
    def __add__(self, other):
        if isinstance(other, Cream):
            return "Yum"

class Cream:
    def __init__(self):
        self._percent=10

coffee = Coffee()
cream = Cream()

# Test the "add" method
print(coffee+cream)
