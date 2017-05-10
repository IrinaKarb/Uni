#include "stdafx.h"
#define M_PI 3.14159265358979323846
#include <iostream>;
#include <cmath>;

using namespace std;

double Carv(double x0, double x1, double e);
double Function(double x);

int main()
{
	setlocale(0, "");
	double a = -1, b = 3, h = 0.01, x, xstr, y, e;
	cout << "Введите Е" << endl;
	cin >> e;
	for (x = a; x < b; x += h)
	{
		if (Function(x)*Function(x + h) < 0)	// при выполнении этого условия на отрезке [x, x + h] существует корень уравнения f(x)
		{
			xstr = Carv(x, x + h, e);
			y = Function(xstr);
			cout << "x = " << xstr << ends << "y = " << y << endl;
		}
	}
	system("pause");
	return 0;
}

double Carv(double x0, double x1, double e)
{
	double x, temp;
	while (fabs(x1 - x0) > e)				// при выполнении этого условия значение х1 принимается в
	{										// качестве приближенного значения корня
		temp = x1;
		x1 = x1 - (x1 - x0)*Function(x1) / (Function(x1) - Function(x0));
		x0 = temp;
	}
	return x1;
}

double Function(double x)
{
	double y;
	y = pow(x, 2) - 10 * pow(sin(x), 2) + 2;
	return y;
}