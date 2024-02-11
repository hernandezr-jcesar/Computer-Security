/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cifradohash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

/**
 *
 * @author DELL
 */

public class Cifradohash {

    public static String cifradotransposicion(String mensaje){
        System.out.println("Cifrado con transpuesta: ");
                
        //Tamaño definido para los renglones
        int valor = 3;                
        /*
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce tu mensaje: ");
        String mensaje = sc.nextLine();
        System.out.println("Tu mensaje es: " + mensaje);
        */
        
        char[] aCaract = mensaje.toCharArray();
        
        int largo = aCaract.length;
        int div = largo/valor;
        int res = largo%valor;
        
        //System.out.println("largo: "+largo+"\nColumna: "+div+"\nSobrantes: "+res);
        if(res!=0){
            div++;
        }
        
        //System.out.println("Columnas: " + div);
       
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
        //System.out.println(cifrado);
        return String.valueOf(cifrado);
        
          
    }
    
    public static void main(String [] args)
    {
       System.out.println("Favor de meter una cadena:");
       String cadena;       
       Scanner scan = new Scanner(System.in);
       cadena = scan.nextLine();
       System.out.println("Tus mensajes: "+cadena); 
       
       byte[] mensaje = cadena.getBytes();
       byte[] hash = null;
       
       try{
           MessageDigest md=MessageDigest.getInstance("MD5");
           hash = md.digest(mensaje);           
       }
       catch(NoSuchAlgorithmException e){
           e.printStackTrace();
       }
       StringBuilder cadenacifrada = new StringBuilder();
       for(byte b:hash){
           cadenacifrada.append(String.format("%02x", b));
       }
       String strHash = cadenacifrada.toString();
       System.out.println("El hash: "+strHash);
       String nuevacadena = strHash + '.' + cadena;
       System.out.println("Hash + cadena: "+nuevacadena);
       
       String cadenatranspuesta = cifradotransposicion(nuevacadena);
       
       System.out.println("Hash + cadena=> cifrado con la transpuesta : "+cadenatranspuesta);
       
       //String cadenadesifradatranspuesta = cifradotransposicion(cadenatranspuesta);
       
       //System.out.println("cadena original: "+cadenadesifradatranspuesta);
       
    }
}
