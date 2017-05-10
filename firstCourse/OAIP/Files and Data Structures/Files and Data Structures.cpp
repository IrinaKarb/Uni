#include "stdafx.h"
#include <iostream>
#include <math.h>
#include <fstream>
#include <string>

using namespace std;

struct marks {
	int maths, physics;
};

struct stud {
	char name[90];
	int group;
	double average;
	marks m;
};

int main()
{
	setlocale(0, "");
	int i = 0, kol = 0, g, kod, count = 0;
	double b;
	stud student[30];
	while (true) {
		puts("\nСоздание - 1\nПросмотр - 2\nКоррекция - 3\nРешение индивидуального задания - 4\nВыход - 5\n");
		scanf_s("%d", &kod);
		switch (kod) {
			{case 1:
				ofstream fout("d://work//text.txt");
				ofstream Result("d://work//rez.txt");
				fout.close();
				Result.close();
				break; }
			{case 2:
				ifstream fin("d://work//text.txt");
				cout << fin.rdbuf();
				fin.close();
				break; }
			{case 3:
				ofstream fout("d://work//text.txt", ios::app);
				cout << "Введите количество новых анкетных данных: ";
				cin >> kol;
				cout << endl;
				for (int j = 0; j < kol; j++)
				{
					cout << "Введите имя студента, номер группы, оценки по математике и физике: " << endl;
					cin >> student[j].name >> student[j].group >> student[j].m.maths >> student[j].m.physics;
					cout << endl;
					fout << student[j].name << " " << student[j].group << " " << student[j].m.maths << " " << student[j].m.physics << "\n";
				}
				fout.close();
				break; }
			{case 4:
				ifstream fin("d://work//text.txt");
				while (!fin.eof())
				{
					fin >> student[i].name >> student[i].group >> student[i].m.maths >> student[i].m.physics;
					if (fin.eof()) break;
					i++;
					count++;
				}
				fin.close();
				for (i = 0; i < count; i++) {
					student[i].average = (int(student[i].m.maths) + int(student[i].m.physics)) / 2;
				}
				cout << "Введите средний балл: ";
				cin >> b;
				cout << "Введите номер группы: ";
				cin >> g;
				ofstream Result("d://work//rez.txt");
				for (i = 0; i < count; i++) {
					if (b <= student[i].average && student[i].group == g) {
						cout << student[i].name << ends << student[i].group << ends << student[i].m.maths << ends << student[i].m.physics << endl;
						Result << student[i].name << " " << student[i].group << " " << student[i].m.maths << " " << student[i].m.physics << "\n";
					}
				}
				Result.close();
				break; }
		case 5:
			exit(1);
		}
	}
		system("pause");
		return 0;
}