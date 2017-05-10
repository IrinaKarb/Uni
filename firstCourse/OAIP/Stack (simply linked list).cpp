#include "stdafx.h"
#include <iostream>

using namespace std;

struct Node {
	int x;
	Node *adr;
} *Head, *p, *tmp, *s, *sort;

Node Create(Node*& Head);
void Sort(Node* Head);
void Show(Node* Head);
void Task(Node* Head);

//void Delete(Node* Head) {
//	Node *h;
//	while (Head != NULL) {
//		h = Head;
//		Head = Head->adr;
//		delete h;
//	}
//	Head = NULL;
//}

int main() {
	setlocale(0, "");
	Head = new Node;
	while (1) {
		int choice;
		cout << "\nВыберите действие:\n1 - Создание, 2 - Просмотр, 3 - Удаление каждого нечетного элемента, 4 - Удаление стека, 0 - Выход" << endl;
		cin >> choice;
		switch (choice) {
		case 1: {
			Head = NULL;
			int n, x;
			cout << "Введите количество элементов: "; cin >> n;
			for (int i = 0; i < n; i++) {
				Create(Head);
			}
			break;
		}
		case 2: {
			sort = Head;
			Sort(sort);
			cout << "Стек: ";
			Show(Head);
			break;
		}
		case 3: {
			tmp = Head;
			s = Head;
			
			Task(Head);

			Head = s;
			break;
		}
		/*case 4:
			Delete(Head);
			break;*/
		case 0:
			exit(1);
		}
	}
	system("pause");
	return 0;
}

Node Create(Node*& Head) {
	int x;
	p = new Node;
	x = rand() % 25;
	p->x = x;
	p->adr = Head;
	Head = p;
	return *Head;
}

void Sort(Node* Head) {
	Node *t = NULL, *t1;
	int r;
	do {
		for (t1 = Head; t1->adr != t; t1 = t1->adr)
			if (t1->x > t1->adr->x) {
				r = t1->x;
				t1->x = t1->adr->x;
				t1->adr->x = r;
			}
		t = t1;
	} while (Head->adr != t);
}

void Show(Node* Head) {
	Node *p = Head;
	while (p->adr != NULL) {
		cout << p->x << " ";
		p = p->adr;
	}
	if (p->adr == NULL) {
		cout << p->x << endl;
	}
}

void Task(Node* Head) {
	while (s->x % 2 != 0) {
		s = s->adr;
		if (s->x % 2 == 0)
			break;
	}

	while (Head->adr != NULL) {
		if (Head->x % 2 == 0) {
			tmp = Head;
			Head = Head->adr;
		}
		else {
			if (Head->adr->x % 2 != 0) {
				Head = Head->adr;
				continue;
			}
			tmp->adr = Head->adr;
			Head = Head->adr;
			tmp = tmp->adr;
		}
	}
	if (Head->adr == NULL) {
		if (Head->x % 2 != 0) {
			Head = tmp;
			Head->adr = NULL;
		}
	}
}
