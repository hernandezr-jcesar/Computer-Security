/*
	El funcionamiento de este programa es el siguiente:
	->Cifrar caracteres de un archivo de texto, haciendo 
	el uso de un XOR con el valor binario de cada caracter
	->Primero se tiene que tener un caracter llave el cual sera usado 
	para hacer el XOR con los demas, este caracter para mi sera:
	SIMBOLO		CODIGOASCII		BINARIO
	  #				35			00100011
*/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char *itoa(int value, char *result, int base) {
    // check that the base if valid
    if (base < 2 || base > 36) {
        *result = '\0';
        return result;
    }

    char *ptr = result, *ptr1 = result, tmp_char;
    int tmp_value;

    do {
        tmp_value = value;
        value /= base;
        *ptr++ = "zyxwvutsrqponmlkjihgfedcba9876543210123456789abcdefghijklmnopqrstuvwxyz"[35 +
                                                                                           (tmp_value - value * base)];
    } while (value);

    // Apply negative sign
    if (tmp_value < 0) *ptr++ = '-';
    *ptr-- = '\0';
    while (ptr1 < ptr) {
        tmp_char = *ptr;
        *ptr-- = *ptr1;
        *ptr1++ = tmp_char;
    }
    return result;
}

void concatenarCharACadena(char c, char *cadena){
	char cadenaTemporal[2];
	cadenaTemporal[0] = c;
	cadenaTemporal[1] = '\0';
	strcat(cadena, cadenaTemporal);
}

char* caracterabinario(char caracter){
	int decimal = 0;
	char cadena[9];	

	decimal = (long) caracter;

	itoa(decimal, cadena, 2);

	int diferencia = 8 - (int) strlen(cadena);
		
	char binario[8] = "";
	for(int i = 0; i < diferencia; i++){
		concatenarCharACadena('0',binario);
	}		
	char* buffer = malloc(8);		
	strcat(strcpy(buffer, binario),cadena);				

	return buffer;
	//printf("%s ",buffer);
}
void textoabinario(char* texto){
	int i = 0;
	while(texto[i]!='\0'){
		char* binario = caracterabinario(texto[i]);
		printf("%s ", binario);
		free(binario);
		i++;
	}
}

char* xorentrebinarios(char* cad1, char* cad2){
	char temp[8]="";
	int i;
	for(i = 0; i<=7; i++){
		if(cad1[i] == cad2[i]){			
			concatenarCharACadena('0', temp);
		}else{			
			concatenarCharACadena('1', temp);
		}		
	}	
	//printf("%s", temp);
	char* nueva = malloc(8);	
	strcat(nueva,temp);				
	return nueva;
}


int main(int argc, char const *argv[])
{
	printf("Ejemplo funcionamiento:\n");
	int decimal = 0;	
	char caracterclave = '#';
	decimal = (long) caracterclave;
	char* clavebinaria = caracterabinario(caracterclave);
	printf("Clave '#' en binario: %s\n",clavebinaria);

	int decimal2 = 0;	
	char caracter = '=';
	decimal2 = (long) caracter;
	char* cadena = caracterabinario(caracter);
	printf("El caracter '=' de ejemplo: %s\n",cadena);

	char* newcad = xorentrebinarios(clavebinaria,cadena);
	printf("XOR entre estos dos: [%s]\n",newcad);
	char* newcad2 = xorentrebinarios(clavebinaria,newcad);
	
	printf("XOR entre la salida anterior y la clave: {%s}\n",newcad2);	
	printf("Ejemplificando que se llega al caracter de ejemplo otra vez.\n\n");

	printf("Trabajando con el archivo...\n");


	FILE *archivo = fopen("xor.txt", "r");
	
	if(!archivo) {
     printf("No fue posible abrir el archivo\n");
     return -1;
	}

	char *contents = NULL;
    size_t len = 0;
    while (getline(&contents, &len, archivo) != -1){
        printf("%s\n", contents);        
        textoabinario(contents);
        printf("\n");
    }	

    fclose(archivo);
    free(contents);
	//char *texto = "Mi nombre es Julio Cesar Hernandez Reyes";
	//printf("En texto es: %s\n", texto);	

	//textoabinario(texto);
	
	printf("\n");

	
	return 0;
}