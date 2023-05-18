package EjercicioSimulacroExamenDeLaPofe;

import java.sql.*;
import java.util.Scanner;

public class MainEmisoraTradicional {
    static Scanner sc = new Scanner(System.in);
    static Connection conn;
    static EmisoraTradicional[] array_estatico;

    public static void main(String[] args) throws SQLException, AccionInvalida {
        int opcion=0;
        conn=  DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","admin");
        do {
            System.out.println("0. Salir del programa");
            System.out.println("1. Crear tabla emisoratradicional");
            System.out.println("2. Insertar array estático en bbdd");
            System.out.println("Introduzca una opción por favor");
            opcion = sc.nextInt();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","admin");

            switch (opcion) {
                case 0:
                    System.out.println("Gracias por usar el programa");
                    break;
                case 1: crear_tabla_emisora();
                    break;
                case 2: insertar_array_estatico();
                   break;

            }
        }         while (opcion != 0) ;


    }

    private static void insertar_array_estatico() throws AccionInvalida, SQLException {
        asignar_bd();
        System.out.println("Introduzca el numero de emisoras que quiere introducir");
        int dimension = sc.nextInt();
        //se dimensiona el array
        array_estatico = new EmisoraTradicional[dimension];
        //rellenar array estatico
        for (int i=0;i< array_estatico.length;i++){
            System.out.println("Procedemos a la inserción en el array de la posicion"+i);

             System.out.println("introduzcame el identificador de la emisora");
             int num_emisora = sc.nextInt();


            System.out.println("introduzcame el nombre de la emisora");
            String nombre_emisora = sc.next();


            System.out.println("introduzcame el número de oyentes de la emisora");
            int num_oyentes = sc.nextInt();



            System.out.println("introduzcame la frecuencia de la emisora : AM FM ");
            String frecuencia = sc.next();

            System.out.println(" introduzcame el numero de la frecuencia");
            double num_frecuencia = sc.nextDouble();

            EmisoraTradicional e = new EmisoraTradicional(num_emisora,nombre_emisora,num_oyentes,frecuencia,num_frecuencia);
            array_estatico[i]=e;

        }

        //imprimir array estatico para validar que hemos insertado

        //recorrerlo para insertar cada registro en bbdd
        for (int i=0;i< array_estatico.length;i++) {
            PreparedStatement ps = conn.prepareStatement("insert into emisoratradicional values (?,?,?,?,?,?,?) ");
            ps.setInt(1,array_estatico[i].getNum_emisora());
            ps.setString(2,array_estatico[i].getNombre_emisora());
            ps.setBoolean(3,array_estatico[i].isEmitiendo());
            ps.setDouble(4,array_estatico[i].getBeneficios());
            ps.setInt(5,array_estatico[i].getNum_oyentes());
            ps.setString(6,array_estatico[i].getFrecuencia().toString());
            ps.setDouble(7,array_estatico[i].getNum_frecuencia());
            ps.executeUpdate();
        }






    }

    private static void crear_tabla_emisora() throws SQLException {
        asignar_bd();

        String query = "create table emisoratradicional(" +
                "num_emisora int not null primary key," +
                "nombre varchar(50) not null," +
                "emitiendo boolean," +
                "beneficios double," +
                "num_oyentes int," +
                "frecuencia varchar(2)," +
                "num_frecuencia double" +
                ")";
        Statement st = conn.createStatement();
        st.executeUpdate(query);

    }
    private static void asignar_bd() throws SQLException{

        // asignar la base de datos por defautl
        String query = "use simulacroexamen";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("Asignación realizada correctamente");
    }

}

