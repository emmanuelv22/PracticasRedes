/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp_eco;

import java.net.DatagramPacket;
import java.net.DatagramSocket;



/**
 *
 * @author neyer
 */
public class UDP_eco_servidor {
      public static void main(String[] args){
        try{
            int puerto=2000,tamBuffer=2000;
            String msj="";
            DatagramSocket ds = new DatagramSocket(puerto);
            System.out.println("Servidor iniciado en el puerto:"+puerto+" , esperando paquetes...\n");
            while(true){
                DatagramPacket dp = new DatagramPacket(new byte[tamBuffer],tamBuffer);
                ds.receive(dp);
                System.out.println("Datagrama recibido desde Cliente "+dp.getAddress()+":"+dp.getPort());
                msj = new String(dp.getData(),0,dp.getLength());
                System.out.println("Con mensaje:"+ msj);
                
                
                //Retorno al cliente
                ds.send(dp);
                System.out.println("Eco enviado...");
                
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
