#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "datatype.h"
#include "bstree.h"
#include "matrix.h"

int main(){
	char index1[256],index2[256];
	char* stringCopy1;
	char* stringCopy2;

	//tree = bstree_ini(size);
	Matrix matrix = matrix_construction();

	while (scanf("%s %s",index1,index2) == 2) {
		// Check to see if indices are already in the matrix
        	if (matrix_isin(matrix, index1, index2) == 2) {
			matrix_inc(matrix, index1, index2, 1);
		}
	
		// Create copies of the indices
		stringCopy1 = string_dup(index1);
		stringCopy2 = string_dup(index2);
	
	        // Create a key with the two indices
	        Key key = key_gen(stringCopy1, stringCopy2);
	
		// Create a data occurence
		Data occurence = data_gen(1);
	
		// Insert into the matrix and tree
		bstree_insert(matrix, key, occurence);
	}

    // Print out the data in the matrix
    matrix_list(matrix);

    // Free memory associated with the matrix
    matrix_desctruction(matrix);
}
