#include "hcompress.h"

typedef struct node {
  struct tnode* value;
  struct node* next;
} LinkedList;

LinkedList* llCreate();
int llIsEmpty(LinkedList* ll);
void llDisplay(LinkedList* ll);
void llAdd(LinkedList** ll, struct tnode* newValue);
void llFree(LinkedList* ll);

void list_add_in_order(LinkedList** ll, struct tnode* newValue);