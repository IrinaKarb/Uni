#include "stdafx.h"
#include "RMQ.h"

void build(int left, int right, int* arr, node*obj) {
	obj->leftInd = left;
	obj->rightInd = right;
	if (left == right) {
		obj->item = arr[left];
		return;
	}
	int mid = (left + right) / 2;
	build(left, mid, arr, obj->left = new node);
	build(mid + 1, right, arr, obj->right = new node);
	if (obj->left->item < obj->right->item)
		obj->item = obj->right->item;
	else
		obj->item = obj->left->item;
}

int findMax(int left, int right, node* currentNode) {
	if (right < currentNode->leftInd || left > currentNode->rightInd)
		return -1 * INT_MAX;
	if (right >= currentNode->rightInd && left <= currentNode->leftInd)
		return currentNode->item;
	int leftMax, rightMax;
	leftMax = findMax(left, right, currentNode->left);
	rightMax = findMax(left, right, currentNode->right);
	if (leftMax < rightMax)
		return rightMax;
	else return leftMax;
}

void update(int left, int right, int item, node* currentNode) {
	if (right < currentNode->leftInd || left > currentNode->rightInd)
		return;
	if (currentNode->leftInd == currentNode->rightInd) {
		currentNode->item = item;
		return;
	}
	update(left, right, item, currentNode->left);
	update(left, right, item, currentNode->right);
	if (currentNode->left->item < currentNode->right->item)
		currentNode->item = currentNode->right->item;
	else
		currentNode->item = currentNode->left->item;
}