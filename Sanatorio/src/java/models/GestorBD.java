package models;

import classes.DOCTOR;
import classes.ESTUDIO;
import classes.Paciente;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GestorBD {
    Connection conn = null;
    Statement stm = null;
    ResultSet rs;
    int resultUpdate = 0;
    
    
    //Registrar a un paciente
    public boolean registro(Paciente p) {
        try{
            conn = ConectarBD.abrir();
            stm = conn.createStatement();
            String n = p.getNombre();
            String a = p.getApellido();
            int d = p.getDni();
            String direccion = p.getDireccion();
            String sql = "INSERT INTO paciente(nombre, apellido, dni, direccion) VALUES('"+
                    n+"', '"+a+"' , '"+d+"' , '"+direccion+"');";
            resultUpdate = stm.executeUpdate(sql);
            if(resultUpdate != 0){
                ConectarBD.cerrar();
                return true;
            }else{
                ConectarBD.cerrar();
                return false;
            }
        }catch(SQLException e){
            System.out.println("Error en la base de datos");
            e.printStackTrace();
            return false;
        }
    }

    //Buscar a un paciente
    public Paciente buscarPaciente(int dni) {
        int resultUpdate = 0;
        try{
            conn = ConectarBD.abrir();
            stm = conn.createStatement();
            String sql = "SELECT * FROM Paciente where dni = "+dni;
            rs = stm.executeQuery(sql);
            if(!rs.next()){
                System.out.println("No se encontro el registro");
                ConectarBD.cerrar();
            }else{
                System.out.println("Registro encontrado");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String direccion = rs.getString("direccion");
                Paciente p = new Paciente(nombre, apellido, dni, direccion);
                ConectarBD.cerrar();
                return p;
            }
        }catch(SQLException e){
            System.out.println("Error en la base de datos");
            e.printStackTrace();
            return null;
        }
        return null;
    }
    
    //GENERAR EL TICKET A LA OBRA SOCIAL
    public void generarTicket(String obraSocial, int dni){
        int idEstudio = obtenerIdEstudio(dni, obraSocial);
        try{
            conn = ConectarBD.abrir();
            stm = conn.createStatement();
            String sql = "SELECT * from estudio where id_Estudio="+idEstudio;
            rs = stm.executeQuery(sql);
            if (!rs.next()) {
                System.out.println("Error al encontrar el registro del estudio");
            }else{
                System.out.println("----------------------");
                System.out.println("DNI del paciente: "+dni);
                System.out.println("----------------------");
                System.out.println("Descripcion del estudio: ");
                System.out.println(rs.getString("descripcion"));
                System.out.println(rs.getDouble("precio"));
                System.out.println("----------------------");
            }
        } catch(SQLException e){
            System.out.println("Error en la Base de datos");
        }
    }
    
    //Buscar el id de un estudio
    public int obtenerIdEstudio(int dni, String obraSocial){
        int idPaciente = obtenerIdPaciente(dni);
        int idObraSocial = obtenerIdObraSocial(obraSocial);
        try{
            conn = ConectarBD.abrir();
            stm = conn.createStatement();
            String sql = "SELECT Estudio_id_Estudio from paciente_has_estudio where obrasocial_id_obrasocial = "+idObraSocial+" and Paciente_id_Paciente = "+idPaciente;
            rs = stm.executeQuery(sql);
            if (!rs.next()) {
                System.out.println("Error al encontrar el id del estudio");
            }else{
                return rs.getInt("Estudio_id_Estudio");
            }
        } catch(SQLException e){
            System.out.println("Error en la Base de datos");
        }
        return 0;
    }
    
    //ArrayList de los ids de pacientes atendidos por un doctor en especifico
    public ArrayList obtenerVariosPacientes(String nombreDoctor){
        try{
            ArrayList idPacientes = new ArrayList();
            int id_doctor = obtenerIdDoctor(nombreDoctor);
            conn = ConectarBD.abrir();
            stm = conn.createStatement();            
            rs = stm.executeQuery("SELECT Paciente_id_Paciente FROM paciente_has_estudio where doctor_id_Doctor="+id_doctor);
            while(rs.next()){
                idPacientes.add(rs.getInt("Paciente_id_Paciente"));
            }
            return idPacientes;
        }catch (Exception e){
            System.out.println("Error en la base de datos");
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList listaDePacientes(String nombreDoctor){
        ArrayList idPacientes = obtenerVariosPacientes(nombreDoctor);
        ArrayList<Paciente> pacientes = new ArrayList();
        Paciente p = new Paciente();
        try{
            conn = ConectarBD.abrir();
            stm = conn.createStatement();
            for (Object a: idPacientes) {
                    rs = stm.executeQuery("SELECT * FROM paciente where id_Paciente="+a);
                    if (rs.next()) {
                        p.setNombre(rs.getString("nombre"));
                        p.setApellido(rs.getString("apellido"));
                        p.setDni(rs.getInt("dni"));
                    }else{
                        System.out.println("No se encontro el registro");
                    }
                    pacientes.add(p);
            }
            return pacientes;
        }catch (Exception e){
            System.out.println("Error en la base de datos");
            e.printStackTrace();
            return null;
        }
    }

    //Buscar el id de un doctor
    private int obtenerIdDoctor(String nombreDoctor) {
        try{
            conn = ConectarBD.abrir();
            stm = conn.createStatement();
            rs = stm.executeQuery("SELECT id_Doctor from doctor where nombre ='"+nombreDoctor+"'");
            if (!rs.next()) {
                System.out.println("El registro no ha sido encontrado");
            }else{
                System.out.println("Registro encontrado");
                return rs.getInt("id_Doctor");
            }
        }catch(SQLException e){
            System.out.println("Error en la base de datos");
        }
        return 0;
    }

    //Buscar el id de un paciente
    private int obtenerIdPaciente(int dni) {
        try{
            conn = ConectarBD.abrir();
            stm = conn.createStatement();
            String sql = "SELECT id_Paciente from paciente where dni="+dni;
            rs = stm.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("id_Paciente");
            }else{
                System.out.println("No se encontro el id del paciente");
            }
        }catch(SQLException e){
            System.out.println("Error en la base de datos");
            e.printStackTrace();
        }
        return 0;
    }
    
    //Registrar al paciente en el historial de los estudios hechos
    public void registrarEnPacienteHasEstudio(int dni, String doctor, String estudio, String obrasocial, int anio, int mes, int dia) {
        try{
            conn = ConectarBD.abrir();
            stm = conn.createStatement();
            String sql = "call estudios('"+obrasocial+"', '"+doctor+"', "+dni+", '"+estudio+"', '"+anio+"', '"+mes+"', '"+dia+"')";
            stm.executeQuery(sql);
            
        }catch(SQLException e){
            System.out.println("Error en la base de datos");
            e.printStackTrace();
        }
    }
    
    //Buscar el id de una obra social en especifico
    private int obtenerIdObraSocial(String obraSocial) {
        try{
            conn = ConectarBD.abrir();
            stm = conn.createStatement();
            String sql = "SELECT id_obraSocial from obrasocial where nombre= '"+obraSocial+"'";
            rs = stm.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("id_obraSocial");
            }else{
                System.out.println("No se encontro el id de la obra social");
            }
        }catch(SQLException e){
            System.out.println("Error en la base de datos");
            e.printStackTrace();
        }
        return 0;
    }
    
    public double buscarFacturado(String obraSocial) {
        try{
            conn = ConectarBD.abrir();
            stm = conn.createStatement();
            rs = stm.executeQuery("SELECT facturar from obraSocial where nombre='"+obraSocial+"'");
            if (rs.next()) {
                return rs.getDouble("facturar");
            }
        }catch(SQLException e){
            System.out.println("Error en la base de datos");
        }
        return 0;
    }

    public void actualizarFactura(double facturar, String obraSocial) {
        try{
            conn = ConectarBD.abrir();
            stm = conn.createStatement();
            stm.executeUpdate("UPDATE obrasocial set facturar=facturar+"+facturar+" where nombre='"+obraSocial+"'");
        }catch(SQLException e){
            System.out.println("Error en la base de datos");
        }
    }

    public DOCTOR obtenerDoctorConMasEstudios() {
        System.out.println("a");
        ArrayList idDoctores = obtenerIdDoctores();
        System.out.println("a");
        DOCTOR d = new DOCTOR("a", "a", 0, 0);
        System.out.println("a");
        d.setCantEstudios(0);
        try{
            System.out.println("a");
            conn = ConectarBD.abrir();
            stm = conn.createStatement();
            System.out.println("a");
            for (Object a: idDoctores) {
                System.out.println("a");
                rs = stm.executeQuery("select count(doctor_id_Doctor) as doctores from paciente_has_estudio where doctor_id_Doctor="+idDoctores.get((int) a));
                if (rs.getInt("doctores")>d.getCantEstudios()) {
                    d.setCantEstudios(rs.getInt("doctores"));
                    d.setId((int)a);
                }
            }
            return d;
        }catch(SQLException e){
            System.out.println("Error en la base de datos");
        }
        return null;
    }

    private ArrayList obtenerIdDoctores() {
        ArrayList idDoctores = new ArrayList();
        try{
            conn = ConectarBD.abrir();
            stm = conn.createStatement();
            rs = stm.executeQuery("select id_Doctor from doctor");
            do{
                idDoctores.add(rs.getInt("id_Doctor"));
            }while(rs.next());
            return idDoctores;
        }catch(SQLException e){
            System.out.println("Error en la base de datos");
        }
        return null;
    }

    public DOCTOR obtenerDatosDoctor(DOCTOR d) {
        try{
            conn = ConectarBD.abrir();
            stm = conn.createStatement();
            rs = stm.executeQuery("select * from doctor where id="+d.getId());
            if (rs.next()) {
                d.setApellido(rs.getString("nombre"));
            }else{
                System.out.println("Error al encontrar el nombre del doctor");
            }
            return d;
        }catch(SQLException e){
            System.out.println("Error en la base de datos");
        }
        return null;
    }
        
}
