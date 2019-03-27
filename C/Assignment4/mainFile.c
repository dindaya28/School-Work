#include <stdio.h>
#include "bst.h"

int main(){
	char key[256],skip;
	int id, data, size;

	BStree tree;

	//printf("Indicate the size of the tree:	");
	//scanf("%d", &size);

	//tree = bstree_ini(size);
	tree = bstree_ini(1000);

	while (scanf("%s%d%d",key,&id,&data) == 3) {
		bstree_insert(tree, key_construct(key,id),data);
	}

	bstree_traversal(tree);

	bstree_free(tree);
}
