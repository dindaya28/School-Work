#include <stdlib.h>
#include <stdio.h>
#include "datatype.h"
#include "bstree.h"

/* Input: void
   Output: BStree initialized with malloc */
BStree bstree_ini(void){
    BStree bst;
    bst = (BStree) malloc(sizeof(BStree_node*));
    *bst = NULL;
    return bst;
}

/* Input: BStree bst, Key key, Data data
   Effect: Insert data with key into bst. If key is in bst, then do nothing. */
void bstree_insert(BStree bst, Key key, Data data){
    // Create a new node containing the key and data
    BStree_node *root;

    // Call helper function insert to insert the node into the tree
    // Will set the root to the new tree containing the inserted node
    *root = insert(root,key,data);
    *bst = root;
}

/*  Input: key of type Key
          data of type Data
    Output: node of type BStree_Node containing references to key and data
           left and right being null */
BStree_node *new_node(Key key, Data data){
    // malloc a new node to the size of BStree_node
    BStree_node *node = (BStree_node*) malloc(sizeof(BStree_node));

    // create a pointer to key
    node->key = key;

    // create a pointer to data
    node->data = data;

    // create a null pointer for left and right of node
    node->left = NULL;
    node->right = NULL;

    return node;
}

/*  Input: BStree bst(root of tree), Key key
    Output: If key is in bst, return key’s associated data. If key is not in bst, return NULL. */
Data bstree_search(BStree bst, Key key){
    /* Call helper function to recursively traverse the tree for the node containing
    the data item */
    return getData(*bst, key);
}

/*  Input: bst(root of tree)
    Output: Print the contents of the tree using an In Order traversal*/
void bstree_traversal(BStree bst){
    // Call helper function to print the contents of the tree
    BStree_node *root = *bst;
    inOrder(root);
}

/*  Input: bst(root of tree)
    Effect: releases the memory allocated to the tree */
void bstree_free(BStree bst){
    // Call the helper function to release the memory stored in the nodes
    BStree_node *root = *bst;
    free_nodes(root);

    // Release memory allocated to BStree struct
    free(bst);
}

/*  Helper function to find a node containing the specified key
    Input: node and key
    Output: returns the data stored in the node with Key key or NULL if not in the tree */
Data getData(BStree_node *node, Key key){
    // If the node is empty then return NULL, indicating the node containing the key is not in the tree
    if (node == NULL){
        return NULL;
    }
    else {
    // Compare keys to find the location of the node
    int compare = key_comp(node->key,key);

    // If compare < 0 traverse right subtree
    if (compare < 0){
        return getData(node->right, key);
    }
    // If compare > 0 traverse the left subtree
    else if (compare > 0) {
        return getData(node->left, key);
    }
    // If compare == 0 return the data item stored in node
    else{
        return node->data;
    }
    }
}

/*  Helper method to insert a node into the appropriate location in the binary search tree 
    Input: bst(root of tree), key and data
    Output: Returns the root of the tree, with new node either inserted or ignored if already in the tree*/
BStree_node* insert(BStree_node *root, Key key, Data data){
    // If the tree is empty
    if (root == NULL){
        return new_node(key,data);
    }
    else{
    // Compare keys to find the correct location to insert
    int compare = key_comp(root->key,key);

    // If compare < 0 traverse right subtree
    if (compare < 0){
        root->right = insert(root->right,key,data);
    }
    // If compare > 0 traverse the left subtree
    else if (compare > 0){
        root->left = insert(root->left,key,data);
    }
    }
    // if compare == 0 then key is already in the tree
    return root;
}

/*  Helper method to print out the contents of the tree using In Order traversal
    Input: */
void inOrder(BStree_node *root){
    // If the node is empty
    if (root == NULL) return;

    // Traverse the left subtree
    inOrder(root->left);

    // Print the key
    key_print(root->key);

    // Print the associated data
    data_print(root->data);
    
    // Traverse the right subtree
    inOrder(root->right);
}

/*  Helper method to recursively release the memory stored in each of the nodes of the binary search tree
    Input: root of the tree
    Effect: release memory of each of the nodes of the tree*/
void free_nodes(BStree_node *root){
    if (root == NULL) return;
    free_nodes(root->left);
    free_nodes(root->right);
    // Release the memory allocated to the key and data and finally the node itself
    key_free(root->key);
    data_free(root->data);
    free(root);
}
