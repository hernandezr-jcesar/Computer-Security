#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int ascendente(const void *a, const void*b){
	//Castear a enteros
	int aInt = *(int *)a;
	int bInt = *(int *)b;
	// Al restarlos, se debe obtener un numero mayor, menor o igual a 0
	// Con esto ordenamos de manera ascendente
	return aInt - bInt;
}
int descendente(const void *a, const void*b){
	//Castear a enteros
	int aInt = *(int *)a;
	int bInt = *(int *)b;
	// Al restarlos, se debe obtener un numero mayor, menor o igual a 0
	// Con esto ordenamos de manera ascendente
	return bInt - aInt;
}
void concatenarCharACadena(char c, char *cadena){
	char cadenaTemporal[2];
	cadenaTemporal[0] = c;
	cadenaTemporal[1] = '\0';
	strcat(cadena, cadenaTemporal);
}
char enteroACarater(int numero){
	return numero + '0';
}

void cifradokaprekar(int arr[],int tamanioElemento, int longitud){	
	
	printf("___Original___\n");
	for(int a=0;a<longitud;a++){
		printf("Elemento[%d] = %d\n",a, arr[a]);	
	}
	//######################################################################################

	qsort(arr, longitud, tamanioElemento, descendente);
	printf("\n");
	printf("___Descendente___\n");
	for(int a=0;a<longitud;a++){
		printf("Elemento[%d] = %d\n",a, arr[a]);	
	}	
	
	char cadenadescendente[4] = "";	
	
	for(int i=0;i<4;i++){
		char temp1 = enteroACarater(arr[i]);	
		concatenarCharACadena(temp1,cadenadescendente);
	}		
	//Convertir cadena a entero, strtol regresa un long que luego se convierte en int
	int numero1 = (int) strtol(cadenadescendente, NULL, 10);
	
	printf("Numero: %d\n", numero1);	
	
	//######################################################################################
	qsort(arr, longitud, tamanioElemento, ascendente);
	printf("\n");
	printf("___Ascendente___\n");
	for(int a=0;a<longitud;a++){
		printf("Elemento[%d] = %d\n",a, arr[a]);	
	}
	
	char cadenaascendente[4] = "";	
	
	for(int i=0;i<4;i++){
		char temp2 = enteroACarater(arr[i]);	
		concatenarCharACadena(temp2,cadenaascendente);
	}		
	//Convertir cadena a entero, strtol regresa un long que luego se convierte en int
	int numero2 = (int) strtol(cadenaascendente, NULL, 10);
	
	int numero3 = numero1-numero2;
	printf("\n___RESTA___\n");	
	printf(" %d\n", numero1);	
	printf("-%d\n", numero2);
	printf(" ----\n");
	printf(" %d\n", numero3);	
	
}
int main()
{
	int arr[4] = {3,4,1,2};
	int tamanioElemento = sizeof arr[0];
	int longitud = sizeof arr/ tamanioElemento;

	cifradokaprekar(arr, tamanioElemento,longitud);
	/*
	int tamanioElemento = sizeof arr[0];
	int longitud = sizeof arr/ tamanioElemento;

	printf("___Original___\n");
	for(int a=0;a<longitud;a++){
		printf("Elemento[%d] = %d\n",a, arr[a]);	
	}
	//######################################################################################

	qsort(arr, longitud, tamanioElemento, descendente);
	printf("\n");
	printf("___Descendente___\n");
	for(int a=0;a<longitud;a++){
		printf("Elemento[%d] = %d\n",a, arr[a]);	
	}	
	
	char cadenadescendente[4] = "";	
	
	for(int i=0;i<4;i++){
		char temp1 = enteroACarater(arr[i]);	
		concatenarCharACadena(temp1,cadenadescendente);
	}		
	//Convertir cadena a entero, strtol regresa un long que luego se convierte en int
	int numero1 = (int) strtol(cadenadescendente, NULL, 10);
	
	printf("Numero: %d\n", numero1);	
	
	//######################################################################################
	qsort(arr, longitud, tamanioElemento, ascendente);
	printf("\n");
	printf("___Ascendente___\n");
	for(int a=0;a<longitud;a++){
		printf("Elemento[%d] = %d\n",a, arr[a]);	
	}
	
	char cadenaascendente[4] = "";	
	
	for(int i=0;i<4;i++){
		char temp2 = enteroACarater(arr[i]);	
		concatenarCharACadena(temp2,cadenaascendente);
	}		
	//Convertir cadena a entero, strtol regresa un long que luego se convierte en int
	int numero2 = (int) strtol(cadenaascendente, NULL, 10);
	
	printf("Numero: %d\n", numero2);	
	

	//######################################################################################	

	printf("Numero resultante de la resta: %d\n", (numero1 - numero2));

	*/
	return 0;
}
