/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica1seguridad;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
public class Instrucciones implements ActionListener{
    Frame f;
    MenuBar mb;
    Menu ma, mr, mh;
    MenuItem mia, mig, mis, mic, mie, mit;
    TextArea ta;
    Panel p;
    Label l;
    TextField tf;
    Button b;
    ServerSocket ss;
    Socket so;
    Process pro;
    InputStream is;
    InputStreamReader isr;
    DataInputStream dis;
    DataOutputStream dos;
    OutputStream os;
    BufferedReader br;
    BufferedWriter  bw;
    FileDialog  fd;
    String linea="";
    String s = "";
    public Instrucciones(){
        f = new Frame("Instrucciones");
        f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
        mb = new MenuBar();
        ma = new Menu("Abrir");
        mr = new Menu("Red");
        mh = new Menu("Ayuda");
        mia = new MenuItem("Abrir");mia.addActionListener(this);
        mig = new MenuItem("Guardar");mig.addActionListener(this);
        mis = new MenuItem("Salir"); mis.addActionListener(this);
        mic = new MenuItem("Cliente");mic.addActionListener(this);
        mie = new MenuItem("Servidor");mie.addActionListener(this);
        mit = new MenuItem("Acerca");mit.addActionListener(this);
        ta = new TextArea();
        p = new Panel(new GridLayout(1, 3));
        l = new Label("Instruccion:", 2);
        tf = new TextField();
        b = new Button("OK"); b.addActionListener(this);
        ma.add(mia); ma.add(mig); ma.addSeparator(); ma.add(mis);
        mr.add(mic); mr.add(mie);
        mh.add(mit);
        mb.add(ma); mb.add(mr); mb.add(mh);
        f.setMenuBar(mb);
        f.add("Center", ta);
        p.add(l); p.add(tf); p.add(b);
        f.add("South", p);
        f.setSize(800, 600);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("Salir"))
            System.exit(0);
        else if(e.getActionCommand()=="Abrir"){
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
        else if(e.getActionCommand()== "Guardar"){
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
        else if(e.getActionCommand()=="Cliente"){
            try{
			so = new Socket("localhost", 5432);
			is = so.getInputStream();
			dis= new DataInputStream(is);
			System.out.println(dis.readUTF());
			dis.close();
			so.close();
		} catch(ConnectException ce){System.out.println("Sin conexion...");}
		catch(IOException ioe){}
        }
        else if(e.getActionCommand()=="Servidor"){
            try{
			ss = new ServerSocket(5432);
			while(true){
				so = ss.accept();
				os = so.getOutputStream();
				dos= new DataOutputStream(os);
				dos.writeUTF("Hola");
				dos.close(); so.close();
			}
		}catch(IOException ioe){}
        }
        else if(e.getActionCommand()== "OK"){
            ejecuta();
        }
    }
    public void ejecuta(){
        try{
            ta.setText("");
            pro  = Runtime.getRuntime().exec(tf.getText());
            is   = pro.getInputStream();
            isr  = new InputStreamReader(is);
            br   = new BufferedReader(isr);
            linea = br.readLine();
            while(linea != null){
                ta.append(linea + "\n"); System.out.println(linea + "\n");
                linea = br.readLine();
            }
            br.close(); is.close();
        } catch(IOException ioe){}
    }
    public static void main(String [] args) {
        new Instrucciones();
    }
}