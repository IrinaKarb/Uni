#pragma once
#include <iostream>;
#include "stdafx.h"

struct node {
	int item;
	int leftInd;
	int rightInd;
	node* left;
	node* right;
};

void build(int left, int right, int* arr, node*currentNode); 
int findMax(int left, int right, node* currentNode);
void update(int left, int right, int item, node* currentNode);;