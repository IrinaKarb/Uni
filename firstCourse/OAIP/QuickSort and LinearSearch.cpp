#include "stdafx.h"
#include <iostream>
#include <math.h>
#include <fstream>
#include <string>

using namespace std;

struct apparatura {
	char name[20];
	char marka[20], date[20], inf[20];
};

void quick_sort(apparatura app[], apparatura a[], int start, int end);
void search(apparatura app[], int count, string f_key);

int main()
{
	setlocale(0, "");
	int count = 0, i = 0, kod = 0, kol = 0;
	string f_key;
	apparatura* app = new apparatura[30];
	apparatura* a = new apparatura[30];
	while (true) {
		puts("\nСоздание - 1\nПросмотр - 2\nДобавление - 3\nСортировка и поиск - 4\nВыход - 5\n");
		cin >> kod;
		cout << endl;
		switch (kod) {
			{case 1:
				ofstream fout("d://work//kvitanciya.txt");
				fout.close();
				break; }
			{case 2:
				ifstream fin("d://work//kvitanciya.txt");
				cout << fin.rdbuf();
				fin.close();
				break; }
			{case 3:
				ofstream fout("d://work//kvitanciya.txt", ios::app);
				cout << "Введите количество новых данных: ";
				cin >> kol;
				cout << endl;
				for (int i = 0; i < kol; i++)
				{
					cout << "Введите название группы изделий, марку изделия, дату приёмки и состояние готовности заказа: " << endl;
					cin >> app[i].name >> app[i].marka >> app[i].date >> app[i].inf;
					cout << endl;
					fout << app[i].name << " " << app[i].marka << app[i].date << app[i].inf << "\n";
				}
				fout.close();
				break; }
			{case 4:
				ifstream fin("d://work//kvitanciya.txt");
				while (!fin.eof())
				{	
					fin >> app[i].name >> app[i].marka >> app[i].date >> app[i].inf;
					cout << app[i].name << ends << app[i].marka << ends << app[i].date << ends << app[i].inf << endl;
					if (fin.eof()) break;
					i++;
					count++;
				}
				fin.close();
				cout << endl;
				cout << "Введите дату: ";
				cin >> f_key;

				cout << endl;

				search(app, count, f_key);

				ifstream itemp("d://work//temp.txt");
				int j = 0, num = 0;
				while (!itemp.eof())
				{
					itemp >> app[j].name >> app[j].marka >> app[j].date >> app[j].inf;
					if (itemp.eof()) break;
					j++;
					num++;
				}
				itemp.close();
				quick_sort(app, a, 0, num - 1);
				for (i = 0; i < num; i++) {
				cout << app[i].name << ends << app[i].marka << ends << app[i].date << ends << app[i].inf << endl;
				}
				break; }
		case 5:
			exit(1);
		}
	}
	delete[] app;
	delete[] a;
	system("pause");
	return 0;
}

void quick_sort(apparatura app[], apparatura a[], int start, int end) {
	int i = start;
	int j = end;
	string pivot = app[(start + end) / 2].name;
	if (i <= j) {
		while (app[i].name < pivot)
			i++;
		while (pivot < app[j].name)
			j--;
	}
	if (i <= j) {
		a[i] = app[i];
		app[i] = app[j];
		app[j] = a[i];
		i++;
		j--;
	}
	if (start < j)	
		quick_sort(app, a, start, j);
	if (i < end)
		quick_sort(app, a, i, end);
}

void search(apparatura app[], int count, string f_key)
{
	int i_key = 0;
	ofstream otemp("d://work//temp.txt");
	for (int i = 0; i < count; i++)
		if (string(app[i].date) == f_key) {
			i_key = i;
			otemp << app[i_key].name << " " << app[i_key].marka << " " << app[i_key].date << " " << app[i_key].inf << "\n";
		}
	otemp.close();
}