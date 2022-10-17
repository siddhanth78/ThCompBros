struct tnode {
    double weight;
    int c;
    struct tnode* left;
    struct tnode* right;  
    struct tnode* parent;
};

struct tnode* createFreqTable(char** file);

struct tnode* createHuffmanTree(struct tnode* leafNodes);