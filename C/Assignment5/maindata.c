#include <stdio.h>
#include "datatype.c"

int main(){
    Key key = key_gen("aaa", "bbb");
    Key key2 = key_gen("aba", "bbb");
    Key key3 = key_gen("aaa", "aba");
    Key key4 = key_gen("aaa", "bbb");

    int comp = key_comp(key,key2);
    int comp2 = key_comp(key,key3);
    int comp3 = key_comp(key,key4);

    printf("%d,%d,$d",comp,comp2,comp3);
}
