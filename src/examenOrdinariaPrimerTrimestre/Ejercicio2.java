package examenOrdinariaPrimerTrimestre;

import java.util.Scanner;

public class Ejercicio2 {
    static Scanner sc = new Scanner(System.in);
   static double [][] notas = null;

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
                case 1:crear_array();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    break;
                case 5:
                   break;
            }
        }while(opcion!=0);
    }

    private static void crear_array() {
        notas = new double[3][2];
    }


}
