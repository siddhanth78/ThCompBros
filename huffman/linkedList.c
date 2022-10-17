#include <stdio.h>
#include <stdlib.h> // malloc
#include "linkedList.h"
#include "hcompress.h"

LinkedList *llCreate()
{
    return NULL;
}

int llIsEmpty(LinkedList *ll)
{
    return (ll == NULL);
}

void llDisplay(LinkedList *ll)
{

    LinkedList *p = ll;
    printf("[");

    while (p != NULL)
    {
        printf("value: %d, weight: %f", p->value->c, p->value->weight);
        p = p->next;
    }
    printf("]\n");
}

void llAdd(LinkedList **ll, struct tnode* newValue)
{
    // Create the new node
    LinkedList *newNode = (LinkedList *)malloc(1 * sizeof(LinkedList));
    newNode->value = newValue;
    newNode->next = NULL;

    // Find the end of the list
    LinkedList *p = *ll;
    if (p == NULL)
    {                  // Add first element 
        *ll = newNode; // This is why we need ll to be a **
    }
    else
    {
        while (p->next != NULL)
        {
            p = p->next;
        }

        // Attach it to the end
        p->next = newNode;
    }
}

void list_add_in_order(LinkedList** ll, struct tnode* newValue) {
    // Create the new node
    LinkedList *newNode = (LinkedList *)malloc(1 * sizeof(LinkedList));
    newNode->value = newValue;
    newNode->next = NULL;

    // Find the end of the list
    LinkedList *p = *ll;
    LinkedList *pTrail;
    if (p == NULL)
    {                  // Add first element 
        *ll = newNode; // This is why we need ll to be a **
    }
    else
    {
        while (newValue->weight > p->value->weight && p->next != NULL)   
        {
            pTrail = p;
            p = p->next;
        }

        if(p->next != NULL) { // Add node in middle
            pTrail->next = newNode;
            newNode->next = p;
        } else { // Add node at end
            pTrail->next = newNode;
        } 
    }
}

void llFree(LinkedList *ll)
{
    LinkedList *p = ll;
    while (p != NULL)
    {
        LinkedList *oldP = p;
        p = p->next;
        free(oldP);
    }
}