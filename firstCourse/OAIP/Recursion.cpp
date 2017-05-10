#include "stdafx.h"
#include <iostream>

using namespace std;

double fun_R(int n) 
{
	if (n == 1) 
		return	0.5;
	return 1 / (1 + (fun_R(n - 1)));
}

double fun(int n) 
{
	double znam = 1;
	for (int i = 0; i < n; i++) {
		znam = 1 + 1 / znam;
	}
	return 1 / znam;
}

void main()
{
	setlocale(0, "");
	int n, kod;
	cout << "Введите число ступеней: ";
	cin >> n;
	while (true) {
		cout << "1 - Рекурсивная функция, 2 - Обычная функция, 3 - Выход" << endl;
		cin >> kod;
		switch (kod) {
		case 1:
			cout << fun_R(n) << endl;
			break;
		case 2:
			cout << fun(n) << endl;
			break;
		case 3:
			exit(1);
		}
	}
	system("pause");
}