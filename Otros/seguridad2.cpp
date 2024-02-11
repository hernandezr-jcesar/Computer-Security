#include <stdio.h>

int main(){
	int i=0,j=0,k=0,n,num[4], numa=0, numd,aux,auxb[4],res;
printf("Ingrese numero de 4 digitos\n");
scanf("%d", &n);
for(k=0;k<=7;k++){
for(i=0; n!=0; i++ )
{
    num[i] = n % 10;
    n /= 10;
}
	for(j=0;j<4;j++){
	for(i=0;i<4;i++){
		if(num[i]<num[i+1]){
			aux=num[i+1];
			num[i+1]=num[i];
			num[i]=aux;
		}
}
}
numa=0+num[3]+(num[2]*10)+(num[1]*100)+(num[0]*1000);
printf("Numero ascendente: %d\n",numa);
for(j=0;j<4;j++){
	for(i=0;i<4;i++){
		if(num[i]>num[j]){
			aux=num[i];
			num[i]=num[j];
			num[j]=aux;
		}
}
}
numd=0+num[3]+(num[2]*10)+(num[1]*100)+(num[0]*1000);
printf("Numero descendente: %d\n",numd);
res=numa-numd;
printf("Resultado: %d\n",res);
n=res;
}
}
