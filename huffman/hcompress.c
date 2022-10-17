#include <stdio.h>
#include <stdlib.h>
#include "hcompress.h"
#include "linkedList.h"


//
//   TODO - Malloc/free
//

int main(int argc, char *argv[]) {

    // Check the make sure the input parameters are correct
    if (argc != 3) {
        printf("Error: The correct format is \"hcompress -e filename\" or \"hcompress -d filename.huf\"\n"); fflush(stdout);
        exit(1);
    }

    // Create the frequency table by reading the generic file
    struct tnode* leafNodes = createFreqTable("decind.txt");

    // Create the huffman tree from the frequency table
    struct tnode* treeRoot = createHuffmanTree(&leafNodes);

    // encode
    if (strcmp(argv[1], "-e") == 0) {
        // Pass the leafNodes since it will process bottom up
        encodeFile(argv[2], leafNodes);
    } else { // decode
        // Pass the tree root since it will process top down
        decodeFile(argv[2], treeRoot);
    }
    return 0;
}

struct tnode* createFreqTable(char** file) {
    int counts[128] = {0};
    int total = 0;
    char e;

    // read characters from file, put them into freq array based on ASCII value
    int eof_flag = 0;
    while(eof_flag != -1) {
        eof_flag = scanf("%d", e);
        counts[e] += 1;
        total++;
    }

    // construct ll from frequency array
    LinkedList *ll = llCreate();

    for(int i = 0; i < 128; i++) {
        struct tnode *newNode;
        newNode->c = i;
        newNode->weight = (double)counts[i] / total;
        list_add_in_order(&ll, &newNode);
    }

    return ll;
}

struct tnode* createHuffmanTree(struct tnode* leafNodes) {
    LinkedList *p = leafNodes;

    while(p->next != NULL) {
        struct tnode *newNode;
        
    }
}