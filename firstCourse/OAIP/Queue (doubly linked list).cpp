#include "stdafx.h"
#include <iostream>

using namespace std;

struct Queue {
	int x;
	Queue *next, *prev;
} *Head, *End, *p, *tmp, *s;

void Create(Queue*& Head, Queue*& End);

void Create(Queue*& Head, Queue*& End) {
	int n, x;
	Head->next = NULL;
	Head->prev = NULL;
	cout << "Введите количество элементов: ";
	cin >> n;
	x = rand() % 25;
	Queue *p = new Queue;
	p->x = x;
	p->next = p->prev = NULL;
	Head = End = p;
	for (int i = 1; i < n; i++) {
		p = new Queue;
		x = rand() % 25;
		p->x = x;
		p->next = NULL;
		p->prev = End;
		End->next = p;
		End = p;
	}
}

int main()
{
	setlocale(0, "");
	int action, n, x;
	while (1) {
		cout << "\nВыберите действие:\n1 - Создание\n2 - Добавление\n3 - Просмотр\n4 - Удаление каждого нечётного элемента\n0 - Выход\n";
		cin >> action;
		switch (action) {
		case 1: {
			Head = new Queue;
			End = new Queue;
			Create(Head, End);
			break;
		}
		case 2:
			cout << "1 - Добавление в начало\n2 - Добавление в конец\n";
			cin >> action;
			switch (action) {
			case 1:
				cout << "Введите количество элементов: ";
				int x, n;
				cin >> n;
				for (int i = 0; i < n; i++) {
					p = new Queue;
					x = rand() % 25;
					p->x = x;
					p->prev = NULL;
					p->next = Head;
					Head->prev = p;
					Head = p;
				}
				break;
			case 2:
				cout << "Введите количество элементов: ";
				cin >> n;
				for (int i = 0; i < n; i++) {
					p = new Queue;
					x = rand() % 25;
					p->x = x;
					p->next = NULL;
					p->prev = End;
					End->next = p;
					End = p;
				}
				break;
			}
			break;
		case 3:
			cout << "1 - Просмотр с начала\n2 - Просмотр с конца\n";
			cin >> action;
			switch (action) {
			case 1: {
				cout << "\nСписок: ";
				Queue *tH = new Queue;
				tH = Head;
				while (tH->next != NULL) {
					cout << tH->x << " ";
					tH = tH->next;
				}
				if (tH->next == NULL) {
					cout << tH->x;
				}
				break;
			}
			case 2: {
				cout << "\nСписок: ";
				Queue *tE = new Queue;
				tE = End;
				while (tE->prev != NULL) {
					cout << tE->x << " ";
					tE = tE->prev;
				}
				if (tE->prev == NULL) {
					cout << tE->x;
				}
				break;
			}
			}
			cout << endl;
			break;
		case 4:
			tmp = Head;
			s = Head;

			while (s->x % 2 != 0) {
				s = s->next;
				if (s->x % 2 == 0)
					break;
			}	

			while (Head->next != NULL) {
				if (Head->x % 2 == 0) {

					tmp = Head;
					Head = Head->next;
				}
				else {
					if (Head->next->x % 2 != 0) {
						Head = Head->next;
						continue;
					}
					tmp->next = Head->next;
					Head = Head->next;
					Head->prev = tmp;
					tmp = tmp->next;
				}
			}
			if (Head->next == NULL) {
				if (Head->x % 2 != 0) {
					Head = Head->prev;
					Head->next = NULL;
				}
			}
			End = Head;
			Head = s;
			Head->prev = NULL;
			
			break;
		case 0:
			exit(1);
		}
	}
	system("pause");
    return 0;
}
