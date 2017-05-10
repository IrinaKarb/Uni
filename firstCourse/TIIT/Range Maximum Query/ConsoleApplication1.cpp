#include "stdafx.h"
#include <iostream>
#include <fstream>
#include"RMQ.h"
using namespace std;

int main()
{
	setlocale(0, "");
	int n;
	ifstream fin("d://work//lab.txt");
	fin >> n;
	int* arr = new int[n];
	for (int i = 0; i < n; i++)
		fin >> arr[i];
	node* obj = new node;
	build(0, n - 1, arr, obj);
	int left, right, action, changeNumber;
	for (int i = 0; i < n; i++)
		cout << arr[i] << ends;
	cout << endl;
	while (true) {
		cout << "1 - найти максимум на отрезке; 2 - изменение чисел на отрезке массива на какое-либо значение; 3 - выход."; 
		cout << endl;
		cin >> action;
		switch (action) {
		case 1:
			cout << "Введите левую и правую границу: "; cin >> left >> right;
			if (left > right)
				break;
			cout << "Максимум равен: " << findMax(left, right, obj) << endl;
			break;
		case 2: 
			cout << "Введите число: "; cin >> changeNumber;
			cout << "Введите левую и правую границу: "; cin >> left >> right;
			update(left, right, changeNumber, obj);
			break;
		case 3:
			exit(1);
		}
	}
	system("pause");
	return 0;
}