#include <stdio.h>
#include <stdlib.h>
#include "bst.h"
int getNode(BStree bst,Key* key,int i); // Helper prototypes
void inOrder(BStree bst,int i);

// Input: ’size’: size of an array
// Output: a pointer of type BStree,
// 		i.e. a pointer to an allocated memory of BStree_struct type
// Effect: dynamically allocate memory of type BStree_struct
// 		allocate memory for a Node array of size+1 for member tree_nodes
// 		allocate memory for an unsigned char array of size+1 for member is_free
// 		set all entries of is_free to 1
// 		set member size to ’size’;
BStree bstree_ini(int size) {
	BStree tree = (BStree_struct*) malloc(sizeof(BStree_struct)); // allocate memory for the pointer
	
	tree->tree_nodes =(Node*) malloc((size + 1)*sizeof(Node)); // allocate memory for the nodes array
	tree->is_free = (unsigned char*) malloc((size + 1)*sizeof(unsigned char)); // allocate memory for the is_free array
        
	int i;
	for (i = 0;i < (size + 1);i++){ // set all entries of is_free to 1
		tree->is_free[i] = 1;
	}
	
	tree->size = size; // initialize member size to size
	return tree;
}

// Input: ’bst’: a binary search tree
// 		’key’: a pointer to Key
// 		’data’: an integer
// Effect: ’data’ with ’key’ is inserted into ’bst’
// 		if ’key’ is already in ’bst’, do nothing
void bstree_insert(BStree bst, Key *key, int data) {
	int input_node = getNode(bst, key, 1);
	
	if (input_node >= bst->size) {// Check for array out of bounds
		printf("ERROR: Array out of bounds, bst is too small");
		return;
	}
	if (input_node == 0) // Check to see if the key is already in the tree
		return;
	else { // Add the key & data item to the array
		Node* node = malloc(sizeof(Node));
		node->key = key;
		node->data = data;
		bst->tree_nodes[input_node] = *node;
		bst->is_free[input_node] = 0;
	}
}

// Input: ’bst’: a binary search tree
// Effect: print all the nodes in bst using in order traversal
void bstree_traversal(BStree bst) {
	inOrder(bst, 1); // Call the inOrder function to print out contents
}

// Input: ’bst’: a binary search tree
// Effect: all memory used by bst are freed
void bstree_free(BStree bst) {
	free(bst->is_free);
	free(bst->tree_nodes);
	free(bst);
}

// Input: '*node' : a node in a binary search tree
//		  '*c' : an unsigned char referencing the is_free array
//		  '*key': a key that is trying to be found
//		  ' i ' : integer referencing the current location in the tree
// Output: int position of the key if already in the array or the position the key should go in the array
int getNode(BStree bst, Key *key, int i){
	if (i >= bst->size) 
		return i; // Check for array out of bounds
	else if (bst->is_free[i] == 1) 
		return i;
	else { // Recursive portion that traverses the tree based on the order of the key
		if (key_comp(*(bst->tree_nodes[i].key), *key) == 0) 
			return 0;
		else if (key_comp(*(bst->tree_nodes[i].key), *key) > 0) 
			return getNode(bst, key, i*2);
		else if (key_comp(*(bst->tree_nodes[i].key), *key) < 0) 
			return getNode(bst, key, i*2+1);
	}
}

// Input: 'bst' : a binary search tree
// 		   '*node' : node in a binary search tree
//		   '*c' : unsigned char referencing the is_free array
//		   'i' : integer referencing the current location in the tree
// Output: Prints the contents of the tree using an in order traversal
void inOrder(BStree bst, int i){
	if (bst->is_free[i] == 1) return;
	else{ // Recursive portion to traverse the tree in order
		inOrder(bst, i*2);
		print_node(bst->tree_nodes[i]);
		inOrder(bst, i*2+1);
	}
	return;
}
