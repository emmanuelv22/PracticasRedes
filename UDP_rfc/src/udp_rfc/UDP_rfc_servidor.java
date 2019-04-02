/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp_rfc;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author Emmanuel Valentin
 */
public class UDP_rfc_servidor {
    
    public static String RFC(String datos){
        String data[] = datos.split(",");
        String rfc = "";
        //System.out.println("Data:"+data);
        String fechaNac[] = data[3].split("-");
        rfc = String.valueOf(data[1].charAt(0)) + String.valueOf(data[1].charAt(1));
        rfc += String.valueOf(data[2].charAt(0));
        rfc += String.valueOf(data[0].charAt(0));
        rfc += String.valueOf(fechaNac[0].charAt(2)) + String.valueOf(fechaNac[0].charAt(3));
        rfc += fechaNac[1];
        rfc += fechaNac[2];
        return rfc.toUpperCase();
    }

    public static void main(String[] args){
        try{
            int puerto=2000,tamBuffer=2000;
            String msj ="";
            DatagramSocket ds = new DatagramSocket(puerto);
            System.out.println("Servidor iniciado en el puerto:"+puerto+", esperando paquetes...\n");
            while(true){
                DatagramPacket dp = new DatagramPacket(new byte[tamBuffer],tamBuffer);
                ds.receive(dp);
                System.out.println("Datagrama recibido desde Cliente "+dp.getAddress()+":"+dp.getPort());
                msj=new String(dp.getData(),0,dp.getLength());
                System.out.println("Con mensaje:"+ msj);

                msj = RFC(msj);
                
                System.out.println("El RFC Generado: "+msj);

                System.out.println("Mensaje a enviar: "+msj);
                byte[] b = msj.getBytes();
                dp.setData(b); //se rescribe en el paquete el nuevo mensaje
                ds.send(dp);
                System.out.println("RFC enviado...");
     

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
