#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "datatype.h"

char* string_dup(char *str){
    // Initialize string variable to the size of *str
    int length = strlen(str);
    char* copy = (char) malloc(sizeof(char)*(length+1));
    // Copy the contents of the input char into the string array
    int i;
	for (i=0;i<=length;i++){
		*(copy+i) = *(str+i);
	}
    return copy;
}

// Input: '*skey1': a string ending with '\0'
//        '*skey2': a string ending with '\0'
// Output: a pointer of type pointer to Key, pointing to an allocated memory containing a Key
// Effect: generate a key with dynamic memory allocation
Key key_gen(char *skey1, char *skey2){
    Key key = (Key_struct*) malloc(sizeof(Key_struct));
    key->skey1 = skey1;
    key->skey2 = skey2;
    return key;
}

// Input: 'key1' and 'key2' are 2 keys
// Output: if return value < 0, then key1 < key2
//         if return value = 0, then key1 = key2
//         if return value > 0, then key1 > key2
// Function takes use of strcmp() function
int key_comp(Key key1, Key key2){
    if (strcmp(key1->skey1, key2->skey1) < 0)
        return -1;
    else if (strcmp(key1->skey1, key2->skey1) > 0)
        return 1;
    // Compare the skey2 if both skey1 are identical
    else if (strcmp(key1->skey1, key2->skey1) == 0){
        if (strcmp(key1->skey2, key2->skey2) < 0)
            return -1;
        else if (strcmp(key1->skey2, key2->skey2) > 0)
            return 1;
        else if (strcmp(key1->skey2, key2->skey2) == 0)
            return 0;
    }
}

// Input: ’key’: a pointer to Key
// Effect: ( key->skey1 key->skey2 ) are printed
void key_print(Key key){
    printf("%-20s %-20s",key->skey1,key->skey2);
}

// Input: 'key' a pointer to Key
// Effect: memory allocated for key is freed
void key_free(Key key){
    free(key->skey1);
    free(key->skey2);
    free(key);
}

// Input: 'idata' int
// Output: a pointer of type pointer to Data, pointing to an allocated memory containing a Data
// Effect: generate data with dynamic memory allocation
Data data_gen(int idata){
    Data data = (Data) malloc(sizeof(idata));
    *data = idata;
    return data;
}

// Input: 'data' Data type
//        'idata' int
// Effect: assign data with idata
void data_set(Data data, int idata){
    *data = idata;
}

// Input: 'data' Data type
// Effect: prints contents of data
void data_print(Data data){
    printf("%4d\n", *data);
}

// Input: 'data' Data type
// Effect: Free memory allocated for data
void data_free(Data data){
    free(data);
}
