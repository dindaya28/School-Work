#include <stdlib.h>
#include "datatype.h"
#include "bstree.h"
#include "matrix.h"

/*  Matrix construction using bst_ini() */
Matrix matrix_construction(void){
    Matrix matrix = bstree_ini();
    return matrix;
}

/*  Input: Matrix, index1, index2
    Output: unsigned char value of 1 or 0
    Effect: If location (index1, index2) is defined in Matrix m, then return 1. Otherwise, return 0.*/
unsigned char matrix_isin(Matrix m, Index index1, Index index2){
    // Create a new key with index1 and index2
    Key key = key_gen(index1, index2);

    // Search the tree to see if a key with given indexes is in the tree
    Data data = bstree_search(*m,key);

    // Check to see if data contains anything
    if (data == NULL) return 0;
    else return 1;
}
/*  Input: matrix, index1, and index2
    Output: data value stored in the node associated with the index values
    Effect: If location (index1, index2) is defined in Matrix m, then return a pointer to the associated
    value. Otherwise, return NULL.*/
Value *matrix_get(Matrix m, Index index1, Index index2){
    // Create a new key with index1 and index2
    Key key = key_gen(index1, index2);

    // Search the tree to see if a key with given indexes is in the tree
    Data data = bstree_search(*m,key);

    // Check to see if data contains anything
    if (data == NULL) return NULL;
    else return data;
}
/*  Input: matrix, index1, index2, data value
    Effect: Assign value to Matrix m at location (index1, index2). If that location already has value,
    then overwrite.*/
void matrix_set(Matrix m, Index index1, Index index2, Value value){
    // Create a new key with index1 and index2
    Key key = key_gen(index1,index2);

    // Locate the node containing the two indexes
    BStree_node node = *getNode(*m,key);

    // Set the data value for the node to given value
    Data data = &value;
    *node.data = *data;
}
/*  Input: matrix, index1, index2, data value
    Effect: If location (index1, index2) is defined in Matrix m, then increase the associated value by
    value. Otherwise report error.*/
void matrix_inc(Matrix m, Index index1, Index index2, Value value){
    // Create a new key with index1 and index2
    Key key = key_gen(index1,index2);

    // Locate the node containing the two indexes
    BStree_node node = *getNode(*m,key);

    // If found node is not in the tree then report error
    if (node == NULL){
        printf("Error: Node not found in matrix.");
    }
    // Increment the data value for the node to given value
    else{
        Data data = &value;
        *node.data += *data;
    }
}

/*  Input: root of matrix
    Effect: Print indices and values in the Matrix m (with bstree traversal())*/
void matrix_list(Matrix m){
    // Call bstree_traversal
    bstree_traversal(*m);
}

/*  Input: root of matrix
    Effect: Free allocated space (with bstree free())*/
void matrix_destruction(Matrix m){
    // Call bstree_free
    bstree_free(*m);
}

/*  Input: matrix root, target key
    Output: Node containing the key */
BStree_node* getNode(BStree_node *root, Key key){
    // Traverse the tree recursively, comparing nodes
    int compare = key_comp(root->key,key);

    // If compare < 0 traverse right subtree
    if (compare < 0) return getNode(root->right);

    // If compare > 0 traverse left subtree
    else if (compare > 0) return getNode(root->left);

    // If compare == 0 node found
    else return root;
    
    // Node not in tree, return null
    return NULL;
}
