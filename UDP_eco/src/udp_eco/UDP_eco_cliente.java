/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp_eco;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;



/**
 *
 * @author Emmanuel Valentin
 */
public class UDP_eco_cliente {
     public static void main(String[] args){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String msj = "";
            DatagramSocket ds = new DatagramSocket();
            System.out.println("Cliente corriendo. Escriba su mensaje:");
            msj=br.readLine();
            byte[] b = msj.getBytes();
            String destino = "localhost";
            int puerto = 2000;
            DatagramPacket dp = new DatagramPacket(b,b.length,InetAddress.getByName(destino),puerto);
            ds.send(dp);
            
            ds.receive(dp);// Recepcion
            System.out.println("\nDatagrama recibido del Servidor "+dp.getAddress()+":"+dp.getPort());
            msj = new String(dp.getData(),0,dp.getLength());
            System.out.println("Eco del mensaje:"+ msj+"");
            /*Se termina recepción*/
            
            ds.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
