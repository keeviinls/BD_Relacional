package EjericicioSimulacroExamen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MainEmisoraTradicional {
    static Scanner sc = new Scanner(System.in);
    static Connection  conn;

    public static void main(String[] args) throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "admin");
        int opcion = 0;
        do {
            System.out.println("0.Salir del programa");
            System.out.println("1. Crear tabla emisoraTradicional");
            System.out.println("2. Crear tabla emisoraonline");


            System.out.println("Introduzca una opción por favor");
            opcion = sc.nextInt();


            switch (opcion) {
                case 0:
                    System.out.println("Gracias por usar el programa");
                    break;
                case 1:
                    crear_bd();
                    break;
                case 2:
                    crear_tabla_emisora();
                    break;
                case 3:

                    break;
            }
        }





    }

    private static void crear_bd() throws SQLException{
        String query="use simulacroexamen";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
    }

    private static void crear_tabla_emisora() throws SQLException {
        asignar_bd();
        String query = "create table emisoraonline("+
                "num_emisora int not null primary key,"+
                "nombre varchar (50) not null,"+
                "emitiendo boolean,"+
                "beneficios double,"+
                "num_oyentes int,"+
                "url varchar(100))";

        Statement st = conn.createStatement();
        st.executeUpdate(query);
    }


}
