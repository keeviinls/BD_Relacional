package ejerciciopractico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class main {
    static Scanner sc = new Scanner(System.in);
    static Connection conn = null;
    public static void main(String[] args) throws SQLException {
        int opcion =0;
        do{
            System.out.println("0.Salir del programa");
            System.out.println("1. Crear base de datos EjercicioPractico");
            System.out.println("2. Crear la bd ut14");
            System.out.println("3. Crear tabla jugador");


            System.out.println("Introduzca una opción por favor");
            opcion = sc.nextInt();

            switch (opcion){
                case 0: System.out.println("Gracias por usar el programa");break;
                case 1: crear_bd();break;
                case 2: break;
                case 3: break;
                case 4: break;
                case 5: break;
                case 6: break;
                case 7 : break;
                case 8 : break;
                case 9 : break;
                case 10: break;



            }


        }while (opcion!=0);

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

        String query="create database EjercicioPractico";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("BD creada correctamente");

    }
}
