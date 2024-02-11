//[0][0] = h
//[0][1] = o
//[0][2] = l
//hola mundo = hauoo n lmd;

//[00][01][02][03]
//[10][11][12][13]
//[20][21][22][23]

//[h][a][u][o]
//[o][][n][]
//[l][m][d][]

package com.computersecurity.transposicion;

/**
 *
 * @author cesar
 */
import java.util.Scanner;


public class Transposicion {

    public static void main(String[] args) {
        //Tamaño definido para los renglones
        int valor = 3;                
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce tu mensaje: ");
        String mensaje = sc.nextLine();
        System.out.println("Tu mensaje es: " + mensaje);
        
        char[] aCaract = mensaje.toCharArray();
        
        int largo = aCaract.length;
        int div = largo/valor;
        int res = largo%valor;
        
        System.out.println("largo: "+largo+"\nColumna: "+div+"\nSobrantes: "+res);
        if(res!=0){
            div++;
        }
        
        System.out.println("Columnas: " + div);
       
        char[][] arrtrans;
        arrtrans = new char[valor][div];
                
        int conta = 0;
        
        for(int i=0;i<div;i++){
           for(int j=0;j<valor;j++){
               if(conta<largo) {
                   arrtrans[j][i] = aCaract[conta];
                   conta++;
               }
           }
        }
       
        char[] cifrado;
        cifrado = new char[largo];
        conta = 0;
        for(int i=0;i<valor;i++){
           for(int j=0;j<div;j++){         
             System.out.print(arrtrans[i][j]);
                if(conta<largo){
                cifrado[conta] = arrtrans[i][j];
                conta++;
             }             
           }
           System.out.println("");
        }
        System.out.println(cifrado);
        
        
        
        
            
        
    }
}
