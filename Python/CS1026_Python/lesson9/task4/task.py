# Replace the placeholders with code and run the Python program

name = ["Stirling","Lana","Cyril","Pam","Ray","Cheryl"]
alias =["Duchess","Truckasaurus","Chet","Cookie Monster","Gilles de Rais","Cherlene"]

nicknames = {}

for i in range(0,len(name)):
    nicknames[name[i]] = alias[i]


for el in nicknames:
    print(name[name.index(el)], " : ", nicknames[el])

