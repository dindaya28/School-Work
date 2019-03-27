#lists that will contain the volume results from the functions
listCube = []
listPyramid = []
listEllipsoid = []

#functions for volume calculations
#Function containing the volume calculation of a cube as well as user input prompts for the dimensions
def cube():
    sideLength = int(input("Enter the length of one of the sides: "))
    volumeCube = sideLength*sideLength*sideLength
    volumeCube = round(volumeCube,1)
    listCube.append(volumeCube)
    print("The volume of a cube with side length ", round(sideLength, 1), " is ", round(volumeCube, 1), ".")
    return

#Function containing the volume calculation of a pyramid as well as user input prompts for the dimensions
def pyramid():
    baseLength = int(input("Enter the length of the base: "))
    height = int(input("Enter the height of the pyramid: "))
    volumePyramid = (1/3)*(baseLength*baseLength)*height
    volumePyramid = round(volumePyramid,1)
    listPyramid.append(volumePyramid)
    print("The volume of a pyramid with a base of ", baseLength, "and height of ", height, " is ", round(volumePyramid, 1), ".")
    return

#Function containing the volume calculation of an ellipsoid as well as user input prompts for the dimensions
def ellipsoid():
    radiusOne = int(input("Enter the first radius: "))
    radiusTwo = int(input("Enter the second radius: "))
    radiusThree = int(input("Enter the third radius: "))
    from math import pi
    volumeEllipsoid = (4/3)*pi*radiusOne*radiusTwo*radiusThree
    volumeEllipsoid = round(volumeEllipsoid,1)
    listEllipsoid.append(volumeEllipsoid)
    print("The volume of an ellipsoid with radii ", radiusOne, ",", radiusTwo, " and ", radiusThree, " is ", round(volumeEllipsoid, 1), ".")
    return

#input to start program
shape = input("What shape are you interested in: cube, pyramid, or ellipsoid? ")

#while loop that ensures the user is continually asked what shapes they are entering
while shape.lower() != "quit":
    if shape.lower() == "cube":
        cube()
        shape = input("Please enter another shape: ")
    elif shape.lower() == "pyramid":
        pyramid()
        shape = input("Please enter another shape: ")
    elif shape.lower() == "ellipsoid":
        ellipsoid()
        shape = input("Please enter another shape: ")
    elif shape.lower() != "cube" and shape.lower() != "pyramid" and shape.lower() != "ellipsoid":
        shape = input("Invalid input, please enter a correct shape: ")

#Exit code
#First if statement prints when there has been no input given and user quits the program
#Else statement prints the calculated volumes in ascending order

if len(listCube) == 0 and len(listPyramid) == 0 and len(listEllipsoid) == 0:
    print("You have come to the end of the session.")
    print("You did not perform any calculations")
else:
    print("")
    print("You have come to the end of the session.")
    print("The volumes calculated for each shape are shown below: ")
    if len(listCube) == 0:
        listCube = "No computations for a Cube entered."
        print("Cube: ", listCube)
    else:
        listCube.sort()
        for i in range(len(listCube)):
            if i == len(listCube)-1:
                print(listCube[i])
            elif i == 0:
                print("Cube: ", listCube[0], end=", ")
            else:
                print(listCube[i], end=", ")
    if len(listPyramid) == 0:
        listPyramid = "No computations for a Pyramid entered."
        print("Pyramid: ", listPyramid)
    else:
        listPyramid.sort()
        for i in range(len(listPyramid)):
            if i == len(listPyramid)-1:
                print(listPyramid[i])
            elif i == 0:
                print("Pyramid: ", listPyramid[0], end=", ")
            else:
                print(listPyramid[i], end=", ")
    if len(listEllipsoid) == 0:
        listEllipsoid = "No computations for an Ellipsoid entered."
        print("Ellipsoid: ", listEllipsoid)
    else:
        listEllipsoid.sort()
        for i in range(len(listEllipsoid)):
            if i == len(listEllipsoid)-1:
                print(listEllipsoid[i])
            elif i == 0:
                print("Ellipsoid: ", listEllipsoid[0], end=", ")
            else:
                print(listEllipsoid[i], end=", ")
