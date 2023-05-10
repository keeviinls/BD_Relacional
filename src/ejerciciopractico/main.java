package ejerciciopractico;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    static Scanner sc = new Scanner(System.in);
   public static Connection conn = null;
    public static void main(String[] args) throws SQLException {
        Jugador j = null;
        int opcion =0;
        do{
            System.out.println("0.Salir del programa");
            System.out.println("1. Crear base de datos EjercicioPractico");
            System.out.println("2. Crear tabla jugador");
            System.out.println("3. Vaciar tabla jugador");
            System.out.println("4. Eliminar tabla");
            System.out.println("5. Insertar jugador");
            System.out.println("6. Buscar jugador por dorsal y almacemos en un objeto jugador e imprimimos ese objeto");



            System.out.println("Introduzca una opción por favor");
            opcion = sc.nextInt();

            switch (opcion){
                case 0: System.out.println("Gracias por usar el programa");break;
                case 1: crear_bd();break;
                case 2: crear_tabla_jugador();break;
                case 3: vaciar_tabla_jugador();break;
                case 4: break;
                case 5:
                    System.out.println("Introduzca su dni");
                    String dni = sc.next();
                    System.out.println("Introduzca su nombre");
                    String nombre = sc.next();
                    System.out.println("Introduce dorsal camiseta");
                    int dorsal = sc.nextInt();
                    System.out.println("Introduce salario");
                    double salario=sc.nextDouble();
                    System.out.println("Introudce Edad");
                    int edad = sc.nextInt();

                    j = new Jugador(dni,nombre,dorsal,salario,edad);
                    insertar_jugador(j);break;
                case 6:
                    //ESTO ES UN METODO QUE VA A DEVOLVER UN JUGADOR
                    System.out.println("Introduzca el dorsal del jugador a buscar en la bd");
                    dorsal = sc.nextInt();
                    j = buscar_por_dorsal(dorsal);
                    System.out.println(j.toString());break;
                case 7 :
                    Jugador [] array_estatico_jugadores = almacenar_array_estatico();  //AQUI EL ARRAY SERIA ESTATICO, ES AQUEL QUE PARA DEFINIRLO NECESITAMOS SU DIMENSION




                case 8 :


                    ArrayList<Jugador> arrayList = almacenar_array_dinamico  //AQUI EL ARRAY SERIA DINAMICO
                    break;


                case 9 : break;
                case 10: break;



            }


        }while (opcion!=0);

    }

    private static Jugador[] almacenar_array_estatico() throws SQLException{

        PreparedStatement ps = "select count(*) from jugador";

    }

    private static Jugador buscar_por_dorsal(int dorsal) throws SQLException {
asignar_bd();
        Jugador j = null;
        PreparedStatement ps = conn.prepareStatement("select * from jugador where dorsal_camiseta = ?");
        ps.setInt(1,dorsal);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            j = new Jugador(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getDouble(4),rs.getInt(5));

        }


        return j;
    }

    private static void insertar_jugador(Jugador j1) throws SQLException{
        asignar_bd();
        PreparedStatement ps = conn.prepareStatement("insert into jugador values (?,?,?,?,?)");
        ps.setString(1,j1.getDni());
        ps.setString(2,j1.getNombre());
        ps.setInt(3, j1.getDorsal_camiseta());
        ps.setDouble(4, j1.getSalario());
        ps.setInt(5, j1.getEdad());
        ps.executeUpdate();


    }

    private static void vaciar_tabla_jugador() throws SQLException {
        asignar_bd();

        String query = "truncate jugador";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
    }

    private static void crear_tabla_jugador() throws SQLException{

        asignar_bd();
        String query = "create table jugador("+
                "dni varchar(9) not null primary key,"+
                "nombre varchar (100),"+
                "dorsal_camiseta int,"+
                "salario double,"+
                "edad int)";
        Statement st = conn.createStatement();
        st.executeUpdate(query);


    }


    private static void establecer_conexion() throws SQLException {
        //conexión al sgbd

        String url = "jdbc:mysql://localhost:3306/";
        String user ="root";
        String pwd="admin";
        conn = DriverManager.getConnection(url,user,pwd);
        System.out.println("conexión establecida");
    }


    private static void crear_bd() throws SQLException{
        establecer_conexion();
        String query="create database EjercicioPractico";  //PREGUNTAR SI ESO VALE PONER EN LA PRACTICA, LO DE IF NOT EXISTS
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("BD creada correctamente");

    }

    private static void asignar_bd() throws SQLException{
        String query="use EjercicioPractico";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("BD creada correctamente");
    }
}
