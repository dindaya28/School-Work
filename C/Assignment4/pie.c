#include <stdio.h>
#include <math.h>
 
int main (void);

int main() {
	
	long double precision = 0.0;
	long double pie_part = 0.0;
	long double pie_total = 0.0;
	int n = 1; // n equivalent from infinte series

	printf("What precision of pie would you like to calculate to?\n");
	scanf("%Lf", &precision);
	
	do{
		if (n % 2 == 1){ // case for odd n's
			pie_part = (long double)((long double) (4)/(long double)(2*n - 1));
			n++;
			pie_total += pie_part;
			// printf("%Lf", pie_total);
		}
		else{ // case for even n's
			pie_part = (long double)((long double) (4)/(long double)(2*n - 1));
			n++;
			pie_total -= pie_part;
			// printf("Lf", pie_total);
		}
	}while (pie_part > precision); // ensure that pie doesn't go past the restriction

	printf("The approximate value of pie is: %.10Lf\n",pie_total);
}

