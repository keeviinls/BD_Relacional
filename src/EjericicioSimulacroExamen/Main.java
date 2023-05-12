package EjericicioSimulacroExamen;

import ejerciciopractico.Jugador;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Emisora [] listado_emisoras;
    static ArrayList<Emisora> arraylist_emisoras = new ArrayList<>();
    static Connection conn = null;

    public static void main(String[] args) throws SQLException {

        int opcion =0;

        do{
            System.out.println("0.Salir del programa");
            System.out.println("1. Crear base de datos SimulacroExamen");
            System.out.println("2. Crear tabla emisoraonline");
            System.out.println("3. Insertar emisora Online");
            System.out.println("4. Buscar emisora por  URL introducida por teclado y añadirlo a un ARRAY ESTATICO");
            System.out.println("5. Almacenar en el arraylist emisoras con beneficios superiores a 4000 euros ");
            System.out.println("6. Sacar la version del SGBD");

            System.out.println("Introduzca una opción por favor");
            opcion = sc.nextInt();

            switch (opcion){
                case 0: System.out.println("Gracias por usar el programa");break;
                case 1: crear_bd();break;
                case 2: crear_tabla_emisora();break;
                case 3:
                    //crear un objeto de la calse emisora
                    insertar_emisora(3);break;
                case 4: buscar_añadir_array_estatico(url);break;
                case 5: añadir_arraylist_beneficios();break;
                case 6: String versionSGBD = sacar_versionSGBD();
                    System.out.println("La version SGBD "+versionSGBD);
                    break;

            }while (opcion!=0);

    }

}

    private static void crear_tabla_emisora() throws SQLException {
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

    private static void crear_bd() throws SQLException{
        String query = "create database simulacroexamen";
        Statement st = conn.createStatement();
        st.executeUpdate(query);

    }

    private static String sacar_versionSGBD() throws SQLException{

        DatabaseMetaData databaseMetaData = conn.getMetaData();
        return databaseMetaData.getDatabaseProductVersion();


    }
    }
