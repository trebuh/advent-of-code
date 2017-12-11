#!/usr/bin/env python

import csv
import itertools

if __name__ == '__main__':
    result = 0
    second_result = 0
    with open("input.csv") as f:
        file = csv.reader(f)
        for row in file:
            int_row = [int(x) for x in row]
            result += (max(int_row) - min(int_row))
            for pair in itertools.permutations(int_row, 2):
                if pair[0] % pair[1] == 0:
                    second_result += pair[0]/pair[1]
    print(result)
    print(second_result)
