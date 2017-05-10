#include "stdafx.h"
#include <iostream>
#include <fstream>

void check(int first1, int second1, int first2, int second2);
void doTask(char *name1, char *name2);

using namespace std;

int **graph1, **graph2, **graph_sum, **pr, size1, size2;

struct gr {
	char firstNode;
	char secondNode;
};

int main()
{
	setlocale(0, "");
	cout << "Тест 1: ";
	doTask("graph1_T1.txt","graph2_T1.txt");
	cout << "Тест 2: ";
	doTask("graph1_T2.txt", "graph2_T2.txt");
	cout << "Тест 3: ";
	doTask("graph1_T3.txt", "graph2_T3.txt");
	cout << "Тест 4: ";
	doTask("graph1_T4.txt", "graph2_T4.txt");
	cout << "Тест 5: ";
	doTask("graph1_T5.txt", "graph2_T5.txt");
	system("pause");
	return 0;
}

void check(int first1, int second1, int first2, int second2) {
	int position = 0;
	if (second1 == second2) {
		for (int i = 1; i < size1; i++)
			if (graph1[i][0] == first1)
			{
				position = i;
				break;
			}
		for (int j = 1; j < size1; j++) {
			if (graph1[position][j] == 1)
				if (graph1[0][j] == first2)
					cout << "<" << first1 << "," << second1 << ">" << " - " << "<" << first2 << "," << second2 << ">" << endl;
		}
	}

	position = 0;
	if (first1 == first2) {
		for (int i = 1; i < size2; i++)
			if (graph2[i][0] == second1)
			{
				position = i;
				break;
			}
		for (int j = 1; j < size2; j++) {
			if (graph2[position][j] == 1)
				if (graph2[0][j] == second2)
					cout << "<" << first1 << "," << second1 << ">" << " - " << "<" << first2 << "," << second2 << ">" << endl;
		}
	}
}

void doTask(char *name1, char *name2) {
	
	ifstream f1(name1);
	f1 >> size1;
	graph1 = new int*[size1];
	for (int i = 0; i < size1; i++)
		graph1[i] = new int[size1];
	for (int i = 0; i < size1; i++) {
		for (int j = 0; j < size1; j++) {
			f1 >> graph1[i][j];
		}
	}

	ifstream f2(name2);
	f2 >> size2;
	graph2 = new int*[size2];
	for (int i = 0; i < size2; i++)
		graph2[i] = new int[size2];
	for (int i = 0; i < size2; i++) {
		for (int j = 0; j < size2; j++) {
			f2 >> graph2[i][j];
		}
	}

	pr = new int*[size1 * size2];
	for (int i = 0; i < size1 * size2; i++)
		pr[i] = new int[2];
	int m = 0;
	for (int i = 1; i < size2; i++) {
		for (int j = 1; j < size1; j++) {
			pr[m][0] = graph1[0][j];
			pr[m][1] = graph2[0][i];
			m++;
		}
	}

	cout << "Декартова сумма: " << endl;
	for (int i = 0; i < (size1 - 1) * (size2 - 1); i++) {
		for (int j = i + 1; j < (size1 - 1) * (size2 - 1); j++) {
			check(pr[i][0], pr[i][1], pr[j][0], pr[j][1]);
		}
	}
	cout << endl;

	f1.close();
	f2.close();
}