/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp_rfc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Emmanuel Valentin
 */
public class UDP_rfc_cliente {
    public static void main(String[] args){
        try{
            DatagramSocket ds = new DatagramSocket();
            String msj = "";
            System.out.println("Cliente iniciado... \nCalculador de RFC\nEscriba sus nombres:");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            

            msj += br.readLine();
            System.out.println("Escriba su apellido Paterno:");
            msj += ","+br.readLine();
            System.out.println("Escriba su apellido Materno:");
            msj += ","+br.readLine();

            System.out.println("Introduzca fecha de Nacimiento en formato YYYY-MM-DD:");
            msj += ","+br.readLine();

            byte[] b = msj.getBytes();
            String destino = "localhost";
            int puerto = 2000;
            DatagramPacket dp = new DatagramPacket(b,b.length,InetAddress.getByName(destino),puerto);
            ds.send(dp);
            
            /* Aquí se empieza recepción*/
            ds.receive(dp);
            System.out.println("\nRFC calculado por el Servidor "+dp.getAddress()+":"+dp.getPort());
            msj = new String(dp.getData(),0,dp.getLength());
            System.out.println("Resultado:>"+ msj+"<");
            /*Se termina recepción*/
            
            ds.close();
        }catch(Exception e){
            e.printStackTrace();
        }//catch
    }//main
}
