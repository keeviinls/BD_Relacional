package examenOrdinariaPrimerTrimestre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio2 {
    static Scanner sc = new Scanner(System.in);
   static double [][] notas = null;
    static Connection conn = null;
    public static void main(String[] args) {
        int opcion = 0;
        int dimension = 0;
        do {
            System.out.println("0. Salir");
            System.out.println("1.Crear array de notas");
            System.out.println("2.Rellenar array de notas");
            System.out.println("3.Nota media por asignatura 1.Prog 2. bases 3. entornos" );
            System.out.println("4.Nota media de las asignaturas");
            System.out.println("-------------------------------");
            System.out.println("Introduzca una opción...");
            opcion=sc.nextInt();
            switch (opcion){
                case 0:
                    System.out.println("FUERA DE AQUI.");
                    break;
                case 1:
                    crear_array();
                    break;
                case 2:
                    rellenar_array(notas);
                    break;

                case 3:
                    inserta_array_enbd();
                    break;
                case 4:
                    media_total(notas);
                    break;
                case 5:
                   break;
            }
        }while(opcion!=0);
    }

    private static void inserta_array_enbd() throws SQLException {
        establish_connection();
        create_db();
        create_table();


    }

    private static void establish_connection() throws SQLException {

        conn = DriverManager.getConnection("jdbc:mysql://localhost:3006", "root", "admin");


    }

    private static void create_table() throws SQLException{

        String query = "create table asignatura("+
                "id int not null primary key,"+
                "nombre varchar(50),"+
                "nota_examen double," +
                "nota_practica double)";

        Statement st = conn.createStatement();
        st.executeUpdate(query);
    }

    private static void create_db() throws SQLException {
        String query = "create database notas";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
    }

    private static void media_total(double[][] notas) {
    }

    private static double calcular_nota_media(double[][] notas) {

        int opcion=0;
        double resultado=0;
        System.out.println("¿De qué asignatura quieres saber la nota media?");
        System.out.println("1.Programación");
        System.out.println("2.Base de datos");
        System.out.println("3.Entornos Desarrollo");
        opcion=sc.nextInt();
        if (opcion==1){
            resultado=(notas[0][0]+notas[0][1])/2;
            System.out.println("La nota media de programación es "+resultado);
        } else if (opcion==2) {
            resultado=(notas[1][0]+notas[1][1])/2;
            System.out.println("La nota media de base de datos es "+resultado);

        } else if (opcion==3) {
            resultado=(notas[2][0]+notas[2][1])/2;
            System.out.println("La nota media de entornos de desarrollo es "+resultado);

        }
        return resultado;
        }



    private static void rellenar_array(double [][] notas) {

        for (int i=0;i<notas.length;i++){
            for (int j = 0;j< notas[i].length;j++){

                if (i == 0){

                    switch (j){
                        case 0:
                            System.out.println("Dime la nota del EXAMEN de Programacion");
                            notas[i][j] = sc.nextDouble();break;

                        case 1 :
                            System.out.println("Dime la nota de la PRACTICA de Programacion");
                            notas[i][j] = sc.nextDouble();break;
                    }

                }

                else if (i == 1){

                    switch (j){
                        case 0:
                            System.out.println("Dime la nota del EXAMEN de BASE DE DATOS");
                            notas[i][j] = sc.nextDouble();break;

                        case 1 :
                            System.out.println("Dime la nota de la PRACTICA de BASE DE DATOS");
                            notas[i][j] = sc.nextDouble();break;
                    }

                }

                else if (i == 2){

                    switch (j){
                        case 0:
                            System.out.println("Dime la nota del EXAMEN de ENTORNOS");
                            notas[i][j] = sc.nextDouble();break;

                        case 1 :
                            System.out.println("Dime la nota de la PRACTICA de ENTORNOS");
                            notas[i][j] = sc.nextDouble();break;
                    }

                }
            }
        }

    }

    private static void crear_array() {
        notas = new double[3][2];
    }


}
