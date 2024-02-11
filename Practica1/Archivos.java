/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica1seguridad;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class Archivos implements ActionListener{
    Frame       f;
    MenuBar     mb;
    Menu        ma;
    MenuItem    mia, mig, mis;
    FileDialog  fd;
    BufferedReader  br;
    BufferedWriter  bw;
    TextArea    ta;
    String      s = "";
    public Archivos(){
        f   = new Frame("Archivos");
        mb  = new MenuBar();
        ma  = new Menu("Archivo");
        mia = new MenuItem("Abrir"); mia.addActionListener(this);
        mig = new MenuItem("Guardar"); mig.addActionListener(this);
        mis = new MenuItem("Salir"); mis.addActionListener(this);
        ta  = new TextArea();
        f.add("Center", ta);
        ma.add(mia); ma.add(mig); ma.addSeparator(); ma.add(mis);
        mb.add(ma);
        f.setMenuBar(mb);
        f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
        f.setSize(800, 600);
        f.setLocationRelativeTo(f);
        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand()=="Salir")
            System.exit(0);
        else if(ae.getActionCommand()=="Abrir"){
            ta.setText("");
            fd = new FileDialog(f, "Abrir", FileDialog.LOAD);
            fd.setVisible(true);
            String arch = fd.getFile();
            String ruta = fd.getDirectory();
            try{
                br = new BufferedReader(new FileReader(ruta + arch));
                while(null != (s=br.readLine())){
                    ta.append(s + "\n");
                }
            }catch(IOException ioe){}
        }
        else if(ae.getActionCommand()== "Guardar"){
            fd = new FileDialog(f, "Guardar", FileDialog.SAVE);
            fd.setVisible(true);
            String arch = fd.getFile();
            String ruta = fd.getDirectory();
            try{
                bw = new BufferedWriter(new FileWriter(ruta + arch));
                bw.write(ta.getText());
                bw.close();
            }catch(IOException ioe){}
        }
    }
    public static void main(String [] args){
        new Archivos();
    }
}