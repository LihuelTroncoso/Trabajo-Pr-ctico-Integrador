package models;

import classes.Paciente;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegistrarDatos {

    public static boolean registro(Paciente p, String obraSocial, String estudio, String doctor) {
        Connection conn = null;
        Statement stm = null;
        ResultSet rs;
        int resultUpdate = 0;
        try{
            conn = ConectarBD.abrir();
            stm = conn.createStatement();
            String n = p.getNombre();
            String a = p.getApellido();
            int d = p.getDni();
            String direccion = p.getDireccion();
            String sql = "INSERT INTO paciente(nombre, apellido, dni, direccion) VALUES('"+
                    n+"', '"+a+"' , '"+d+"' , '"+direccion+"'); \n ";
            resultUpdate = stm.executeUpdate(sql);
            if(resultUpdate != 0){
                ConectarBD.cerrar();
                return true;
            }else{
                ConectarBD.cerrar();
                return false;
            }
        }catch(Exception e){
            System.out.println("Error en la base de datos");
            e.printStackTrace();
            return false;
        }
    }
    
}
