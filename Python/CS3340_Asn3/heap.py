from collections import defaultdict

import sys

class heap():

    def __init__(self):
        self.array = []
        self.pos = []
        self.size = 0

    def heap(keys,n):
        self.array = keys
        self.size = n

    def in_heap(id):
        