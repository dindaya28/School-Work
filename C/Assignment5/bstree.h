#include "datatype.h"

typedef struct BStree_node {Key key; Data data; struct BStree_node *left, *right; } BStree_node; typedef BStree_node** BStree;
BStree bstree_ini(void);
void bstree_insert(BStree bst, Key key, Data data);
BStree_node *new_node(Key key, Data data);
Data bstree_search(BStree bst, Key key);
void bstree_traversal(BStree bst);
void bstree_free(BStree bst);
Data getData(BStree_node *node,Key key);
BStree_node* insert(BStree_node *root,Key key,Data data);
void inOrder(BStree_node *root);
void free_nodes(BStree_node *root);
