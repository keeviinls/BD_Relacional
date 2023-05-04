import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static Connection  conn = null;
    public static void main(String[] args) throws SQLException {
        int opcion =0;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("0.Salir del programa");
            System.out.println("1. Establecer conexión con el sgbd");
            System.out.println("2. Crear la bd ut14");
            System.out.println("3. Crear la tabla paciente");
            System.out.println("4. Borrar la tabla paciente");
            System.out.println("Introduzca una opción por favor");
            opcion = sc.nextInt();
            switch (opcion){
                case 0: System.out.println("Gracias por usar el programa");break;
                case 1: establecer_conexion();break;
                case 2: crear_bd();break;
                case 3: crear_tabla();break;
                case 4: borrar_tabla();break;
            }


        }while (opcion!=0);

    }

    private static void borrar_tabla() throws SQLException{
        asignar_bd();
        String query = "drop table paciente";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("Tabla borrada correctamente");

    }

    private static void crear_tabla() throws SQLException {

        //crear la tabla

       String query = "create table paciente(" +
                "dni varchar(9) primary key," +
                "nombre varchar(30) not null," +
                "apellidos varchar(100)," +
                "n_operaciones int)";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("Tabla creado correctamente");


    }

    private static void crear_bd() throws SQLException {
        //crear la base de datos
        String query="create database ut14";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("BD creada correctamente");

        // asignar la base de datos por defautl,
        // SIRVE PARA PONER LUEGO ALGO SOBRE LA BASE DE DATOS
        // POR ESO USAMOS EL USE, PARA PONERNOS "ENCIMA" Y PODER USAR LA BASE DE DATOS QUE HEMOS CREADO
        query = "use ut14";
        st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("Asignación realizada correctamente");

    }

    private static void asignar_bd() throws SQLException{
        /**
         * HEMOS CREADO ESTE METODO PQ SIEMPRE USAMOS NORMALMENTE EL USE TABLA, ES MAS COMODO
         * CREAR UN METODO QUE LO ASIGNE DIRECTEMTENE EN LUGAR DE PONERLO EN CADA METODO
         */
        // asignar la base de datos por defautl,
        // SIRVE PARA PONER LUEGO ALGO SOBRE LA BASE DE DATOS
        // POR ESO USAMOS EL USE, PARA PONERNOS "ENCIMA" Y PODER USAR LA BASE DE DATOS QUE HEMOS CREADO
        String query = "use ut14";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("Asignación realizada correctamente");
    }

    private static void establecer_conexion() throws  SQLException{
        //conexión al sgbd

        String url = "jdbc:mysql://localhost:3306/";
        String user ="root";
        String pwd="admin";
         conn = DriverManager.getConnection(url,user,pwd);
        System.out.println("conexión establecida");
    }
}