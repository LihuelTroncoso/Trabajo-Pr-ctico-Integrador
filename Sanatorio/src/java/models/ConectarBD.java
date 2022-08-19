/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author E.T 36
 */
public class ConectarBD {
    public static Connection con;
    private static String bd = "mydb";
    public static String usuario = "root";
    public static String contraseña = "";
    public static String url = "jdbc:mysql://localhost:3306/mydb?useSSL=false";
    
    public static Connection abrir(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, usuario, contraseña);
        } catch(Exception e) {
            System.out.println("Error en la conexxion con la base de datos...");
            e.printStackTrace();
        }
        return con;
    }
    
    public static void cerrar(){
        try{
            if(con!=null){
                con.close();
            } 
        }catch(Exception e){
            System.out.println("No se pudo finalizar la conexion");
            e.printStackTrace();
        }
    }
}
