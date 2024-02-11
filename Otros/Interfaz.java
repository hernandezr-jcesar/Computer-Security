package com.seguridad.estructurainterfaz;

import java.io.*;
import java.awt.*;
import javax.swing.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;

import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


/**
 *
 * @author cesass = new ServerSocket(PUERTO);//Se crea el socket para el servidor en puerto 1234
            cs = new Socket(); //Socket para el clienter
 */
public class Interfaz extends javax.swing.JFrame {           
    private final int PUERTO = 12345; //Puerto para la conexión
    private final String HOST = "localhost"; //Host para la conexión
    protected String mensajeServidor; //Mensajes entrantes (recibidos) en el servidor
    protected String mensajeCliente; //Mensajes entrantes (recibidos) en el cliente
    protected ServerSocket ss; //Socket del servidor
    protected Socket cs; //Socket del cliente
    protected DataOutputStream salidaServidor, salidaCliente; //Flujo de datos de salida
    protected DataInputStream entradaCliente;
        
    private final byte[] BUFFER = new byte[1024];
    /**javax.swing.JFrame
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
    }    
    /**
     * @param archivo Archivo a comprimir
     *      El archivo se comprime con el mismo nombre del archivo origen seguido de la extension *.zip
     * @return boolean 
     *      TRUE tuvo exito
     *      FALSE no se pudo comprimir
     */
    
    public boolean comprimir(File archivo) {   
        // create byte buffer        
        try{
            FileOutputStream fout = new FileOutputStream(archivo.getAbsolutePath() + ".zip");
            CheckedOutputStream checksum = new CheckedOutputStream(fout, new CRC32());
            GZIPOutputStream  out = new GZIPOutputStream (checksum);
            
        //try (GZIPOutputStream  out = new GZIPOutputStream (new FileOutputStream(archivo.getAbsolutePath() + ".zip"));
                
            FileInputStream in = new FileInputStream(archivo);
            int len;
            while ((len = in.read(BUFFER)) != -1) {
                out.write(BUFFER, 0, len);
            }            
            out.close();
            in.close();
            Avisos.append("\nArchivo comprimido correctamente...");
            Avisos.append("\nCRC32 Checksum is : " + checksum.getChecksum().getValue());            
        } catch (IOException ex) {            
            Avisos.append("\n"+ex.getMessage());            
            return false;
        }
        return true;
    }

    /**
     * @param archivo Archivo a comprimir de la forma archivo.extension.zip
     * @return boolean 
     *      TRUE tuvo exito
     *      FALSE no se pudo descomprimir
     */
    public boolean descomprimir(File archivo) {
        //extrae la extension *.zip
        String salida = archivo.getAbsolutePath().replaceFirst("[.][^.]+$", "");
        
        try{
            //final int BUFFER = 2048;
            BufferedOutputStream bos = null;
            FileInputStream fis = new FileInputStream(archivo);
            CheckedInputStream chis = new CheckedInputStream(fis,  new CRC32());
            GZIPInputStream in = new GZIPInputStream(new BufferedInputStream(chis));        
            FileOutputStream out = new FileOutputStream(salida);           
            /*                    
            ZipEntry ze;
            while((ze = zis.getNextEntry()) != null) {
                System.out.println("Extracción: " + ze);
                int n;
                byte dato[] = new byte[BUFFER];
                FileOutputStream fos = new FileOutputStream(ze.getName());
                bos = new BufferedOutputStream(fos, BUFFER);
                while ((n = zis.read(dato, 0, BUFFER)) != -1) bos.write(dato, 0, n);
                bos.flush();
                bos.close();
            }
            zis.close();
            */
            
            int len;
            while ((len = in.read(BUFFER)) != -1) {
                out.write(BUFFER, 0, len);
            }
            out.close();            
            Avisos.append("\nArchivo descomprimido correctamente...");
            Avisos.append("\nCRC: " + chis.getChecksum().getValue());            
        } catch (IOException ex) {      
            Avisos.append("\n"+ex.getMessage());            
            return false;
        }
        return true;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jMenuItem1 = new javax.swing.JMenuItem();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        Avisos = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        Red = new javax.swing.JMenu();
        RadioButtonServidor = new javax.swing.JRadioButtonMenuItem();
        RadioButtonCliente = new javax.swing.JRadioButtonMenuItem();
        Archivo = new javax.swing.JMenu();
        AbrirArchivo = new javax.swing.JMenuItem();
        GuardarArchivo = new javax.swing.JMenuItem();
        Cifrado = new javax.swing.JMenu();
        Cesar = new javax.swing.JMenuItem();
        Hash = new javax.swing.JMenuItem();
        Descifrado = new javax.swing.JMenu();
        CesarD = new javax.swing.JMenuItem();
        Compresion = new javax.swing.JMenu();
        ComprimirArchivo = new javax.swing.JMenuItem();
        DescomprimirArchivo = new javax.swing.JMenuItem();
        ComprimirTexto = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setForeground(new java.awt.Color(153, 153, 153));
        jTextField1.setText("Ej.Abrir");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Instruccion");

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        Avisos.setColumns(20);
        Avisos.setRows(5);
        jScrollPane3.setViewportView(Avisos);

        Red.setText("Red");

        buttonGroup1.add(RadioButtonServidor);
        RadioButtonServidor.setSelected(true);
        RadioButtonServidor.setText("Servidor");
        RadioButtonServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButtonServidorActionPerformed(evt);
            }
        });
        Red.add(RadioButtonServidor);

        buttonGroup1.add(RadioButtonCliente);
        RadioButtonCliente.setText("Cliente");
        RadioButtonCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButtonClienteActionPerformed(evt);
            }
        });
        Red.add(RadioButtonCliente);

        jMenuBar1.add(Red);

        Archivo.setText("Archivo");

        AbrirArchivo.setText("Abrir");
        AbrirArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirArchivoActionPerformed(evt);
            }
        });
        Archivo.add(AbrirArchivo);

        GuardarArchivo.setText("Guardar");
        GuardarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarArchivoActionPerformed(evt);
            }
        });
        Archivo.add(GuardarArchivo);

        jMenuBar1.add(Archivo);

        Cifrado.setText("Cifrado");

        Cesar.setText("Cesar");
        Cesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CesarActionPerformed(evt);
            }
        });
        Cifrado.add(Cesar);

        Hash.setText("Hash");
        Hash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HashActionPerformed(evt);
            }
        });
        Cifrado.add(Hash);

        jMenuBar1.add(Cifrado);

        Descifrado.setText("Descifrado");

        CesarD.setText("Cesar");
        CesarD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CesarDActionPerformed(evt);
            }
        });
        Descifrado.add(CesarD);

        jMenuBar1.add(Descifrado);

        Compresion.setText("Compresion");

        ComprimirArchivo.setText("ComprimirArchivo");
        ComprimirArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComprimirArchivoActionPerformed(evt);
            }
        });
        Compresion.add(ComprimirArchivo);

        DescomprimirArchivo.setText("DescomprimirArchivo");
        DescomprimirArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DescomprimirArchivoActionPerformed(evt);
            }
        });
        Compresion.add(DescomprimirArchivo);

        ComprimirTexto.setText("ComprimirTexto");
        ComprimirTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComprimirTextoActionPerformed(evt);
            }
        });
        Compresion.add(ComprimirTexto);

        jMenuBar1.add(Compresion);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void AbrirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbrirArchivoActionPerformed
        String  s = "";
        jTextArea1.setText("");
        Avisos.setText("Abriendo un Archivo...");

        //FileChooser.setFileSelectionMode(FileChooser.FILES_AND_DIRECTORIES);        
        FileDialog fd = new FileDialog(this, "Abrir", FileDialog.LOAD);
        fd.setVisible(true);
        String arch = fd.getFile();
        String ruta = fd.getDirectory();
        
        try{
            BufferedReader br = new BufferedReader(new FileReader(ruta + arch));
            while(null != (s=br.readLine())){
                jTextArea1.append(s + "\n");
            }
        }catch(IOException ioe){}
                               
    }//GEN-LAST:event_AbrirArchivoActionPerformed

    private void GuardarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarArchivoActionPerformed
        Avisos.setText("Guardando Archivo...");
        try{
            String s=jTextArea1.getText();
            if(s.length()>0)
            {
                FileDialog fd= new FileDialog(this,"Save File As",FileDialog.SAVE);
                fd.setFile("temp.txt");
                fd.setDirectory("c:\\windows\\temp");
                fd.setVisible(true);
                String path=fd.getDirectory()+fd.getFile();

                FileOutputStream fos=new FileOutputStream(path);
                //System.out.println(s);
                byte[] b=s.getBytes();
                fos.write(b);
                fos.close();
            }
        }catch(IOException ioe){}

    }//GEN-LAST:event_GuardarArchivoActionPerformed

    private void RadioButtonClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButtonClienteActionPerformed
        Avisos.setText("Modo Cliente Activo...");
        try {
            
            cs = new Socket(HOST, PUERTO); //Socket para el cliente en localhost en puerto 1234             
                        
            jTextArea1.append("Iniciando cliente\n");
                        
            entradaCliente = new DataInputStream(cs.getInputStream());
            String mensaje = entradaCliente.readUTF();
            jTextArea1.append(mensaje + "\n");                
            
            
            //Flujo de datos hacia el servidor
            salidaServidor = new DataOutputStream(cs.getOutputStream());                                                
            
            //Se enviarán dos mensajes
            for (int i = 0; i < 2; i++)
            {
                //Se escribe en el servidor usando su flujo de datos
                salidaServidor.writeUTF("Este es el mensaje número " + (i+1) + "\n");
            }            
                        
            
            cs.close();//Fin de la conexión
        }
        catch (Exception e)
        {            
            Avisos.append("\n"+e.getMessage());
        }
    }//GEN-LAST:event_RadioButtonClienteActionPerformed

    private void RadioButtonServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButtonServidorActionPerformed
        Avisos.setText("Modo Servidor Activo...");
        try
        {                           
            ss = new ServerSocket(PUERTO);//Se crea el socket para el servidor en puerto 1234
            cs = new Socket(); //Socket para el cliente
                        
            jTextArea1.append("Iniciando servidor\n");
            jTextArea1.append("Esperando...\n"); //Esperando conexión            

            cs = ss.accept(); //Accept comienza el socket y espera una conexión desde un cliente
            
            jTextArea1.append("Cliente en línea\n");

            //Se obtiene el flujo de salida del cliente para enviarle mensajes
            salidaCliente = new DataOutputStream(cs.getOutputStream());            

            //Se le envía un mensaje al cliente usando su flujo de salida
            salidaCliente.writeUTF("Petición recibida y aceptada");

            //Se obtiene el flujo entrante desde el cliente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cs.getInputStream()));

            while((mensajeServidor = entrada.readLine()) != null) //Mientras haya mensajes desde el cliente
            {
                //Se muestra por pantalla el mensaje recibido                
                jTextArea1.append(mensajeServidor + "\n");
            }
            
            
            jTextArea1.append("Fin de la conexion\n");            

            ss.close();//Se finaliza la conexión con el cliente
        }
        catch (Exception e)
        {
            Avisos.append("\n"+e.getMessage());            
        }
        
    }//GEN-LAST:event_RadioButtonServidorActionPerformed

    private void DescomprimirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DescomprimirArchivoActionPerformed
        jTextArea1.setText("");
        Avisos.setText("Abriendo un Archivo...");

        FileDialog fd = new FileDialog(this, "Abrir", FileDialog.LOAD);
        fd.setVisible(true);
        String arch = fd.getFile();
        String ruta = fd.getDirectory();

        try{
            descomprimir(new File(ruta + arch));
        }catch(Exception e){}

    }//GEN-LAST:event_DescomprimirArchivoActionPerformed

    private void ComprimirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComprimirArchivoActionPerformed
        jTextArea1.setText("");
        Avisos.setText("Abriendo un Archivo...");

        FileDialog fd = new FileDialog(this, "Abrir", FileDialog.LOAD);
        fd.setVisible(true);
        String arch = fd.getFile();
        String ruta = fd.getDirectory();

        try{
            comprimir(new File(ruta + arch));
        }catch(Exception e){}
    }//GEN-LAST:event_ComprimirArchivoActionPerformed

    private void CesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CesarActionPerformed
        Avisos.setText("\nFavor de ingresar una cadena o un texto:");
        String texto;
        int codigo = 3;
        texto = jTextArea1.getText();
        
        StringBuilder cifrado = new StringBuilder();
        codigo = codigo % 26;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) >= 'a' && texto.charAt(i) <= 'z') {
                if ((texto.charAt(i) + codigo) > 'z') {
                    cifrado.append((char) (texto.charAt(i) + codigo - 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) + codigo));
                }
            } else if (texto.charAt(i) >= 'A' && texto.charAt(i) <= 'Z') {
                if ((texto.charAt(i) + codigo) > 'Z') {
                    cifrado.append((char) (texto.charAt(i) + codigo - 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) + codigo));
                }
            }
        }
        jTextArea1.setText(cifrado.toString());        
    }//GEN-LAST:event_CesarActionPerformed

    private void CesarDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CesarDActionPerformed
        Avisos.setText("\nFavor de ingresar una cadena o un texto:");
        StringBuilder cifrado = new StringBuilder();
        String texto;
        int codigo = 3;
        texto = jTextArea1.getText();
        codigo = codigo % 26;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) >= 'a' && texto.charAt(i) <= 'z') {
                if ((texto.charAt(i) - codigo) < 'a') {
                    cifrado.append((char) (texto.charAt(i) - codigo + 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) - codigo));
                }
            } else if (texto.charAt(i) >= 'A' && texto.charAt(i) <= 'Z') {
                if ((texto.charAt(i) - codigo) < 'A') {
                    cifrado.append((char) (texto.charAt(i) - codigo + 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) - codigo));
                }
            }
        }
        jTextArea1.setText(cifrado.toString());      
    }//GEN-LAST:event_CesarDActionPerformed

    private void HashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HashActionPerformed
       Avisos.setText("\nFavor de meter mensaje:");               
       String cadena;       
       
       if(jTextArea1.getText().length()==0){
            Avisos.setText("\nFavor de meter mensaje:");               
       }else{
            cadena = jTextArea1.getText();
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
                 Avisos.append("\nEl hash: "+strHash);       
                 String nuevacadena = strHash + cadena;
                 Avisos.append("\nHash + cadena + length: "+nuevacadena+cadena.length());  
                 jTextArea1.setText(nuevacadena+cadena.length());  
                 
            }
           
       
    }//GEN-LAST:event_HashActionPerformed

    private void ComprimirTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComprimirTextoActionPerformed
       Avisos.setText("\nFavor de meter texto:");                      
       
       if(jTextArea1.getText().length()==0){
            Avisos.setText("\nFavor de meter texto:");               
       }else{
           String texto = jTextArea1.getText();
           ByteArrayOutputStream bos = new ByteArrayOutputStream(texto.length());
        try{
            GZIPOutputStream gzip = new GZIPOutputStream(bos);
            gzip.write(texto.getBytes());
            gzip.close();
            byte [] b = bos.toByteArray();
            bos.close();
            String s = new String(b, "UTF-8");
            
            jTextArea1.setText("\nTexto comprimido: " + s);
            jTextArea1.append("\nCompresión: " + s.length());
        } catch(Exception e){ e.printStackTrace(); }
       }        
        
    }//GEN-LAST:event_ComprimirTextoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);                                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AbrirArchivo;
    private javax.swing.JMenu Archivo;
    private javax.swing.JTextArea Avisos;
    private javax.swing.JMenuItem Cesar;
    private javax.swing.JMenuItem CesarD;
    private javax.swing.JMenu Cifrado;
    private javax.swing.JMenu Compresion;
    private javax.swing.JMenuItem ComprimirArchivo;
    private javax.swing.JMenuItem ComprimirTexto;
    private javax.swing.JMenu Descifrado;
    private javax.swing.JMenuItem DescomprimirArchivo;
    private javax.swing.JMenuItem GuardarArchivo;
    private javax.swing.JMenuItem Hash;
    private javax.swing.JRadioButtonMenuItem RadioButtonCliente;
    private javax.swing.JRadioButtonMenuItem RadioButtonServidor;
    private javax.swing.JMenu Red;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
