package examenOrdinariaPrimerTrimestre;

import java.util.Arrays;
import java.util.Scanner;

public class main {
    static Scanner sc = new Scanner( System.in);

    public static void main(String[] args) {
        int opcion = 0;
        int dimension = 0;
        String [] array = null;
        do {
            System.out.println("0. Salir");
            System.out.println("1.Crear array de elementos");
            System.out.println("2.Rellenar array de elementos");
            System.out.println("3.Imprimir array por pantalla");
            System.out.println("4.Ver número de consonantes");
            System.out.println("5.Ver número de vocales");
            System.out.println("-------------------------------");
            System.out.println("Introduzca una opción...");
            opcion=sc.nextInt();
            switch (opcion){
                case 0:
                    System.out.println("FUERA DE AQUI.");
                    break;
                case 1:
                    System.out.println("Introduce el tamaño del array: ");
                    dimension=sc.nextInt();
                    array=crear_array(array,dimension);
                    break;
                case 2:
                    array=rellenar_array(array);
                    break;
                case 3:
                    String resultado=imprimir_array(array);
                    System.out.println(resultado);
                    break;
                case 4:
                    System.out.println("¿Qué posición quieres consultar? De 0 hasta "+(dimension-1));
                    int posicion=sc.nextInt();
                    int consonantes=ver_num_consonantes(array,posicion);
                    System.out.println("La palabra "+array[posicion]+" tiene "+consonantes+" consonantes.");
                    break;
                case 5:
                    System.out.println("¿Qué posición quieres consultar? De 0 hasta "+(dimension-1));
                    posicion=sc.nextInt();
                    int vocales=ver_num_vocales(array,posicion);
                    System.out.println("La palabra "+array[posicion]+" tiene "+vocales+" vocales.");
                    break;
            }
        }while(opcion!=0);
    }

    private static int ver_num_vocales(String[] array, int posicion) {
        String palabra_rescatada=array[posicion];
        int contador=0;
        for (int i=0;i<palabra_rescatada.length();i++){
            if (palabra_rescatada.charAt(i)=='a' || palabra_rescatada.charAt(i)=='e' || palabra_rescatada.charAt(i)=='i'
                    || palabra_rescatada.charAt(i)=='o' || palabra_rescatada.charAt(i)=='u'){
                contador++;
            }
        }
        return contador;
    }

    private static int ver_num_consonantes(String[] array,int posicion) {
        String palabra_rescatada=array[posicion];
        int contador=0;
        for (int i=0;i<palabra_rescatada.length();i++){
            if (palabra_rescatada.charAt(i)!='a' && palabra_rescatada.charAt(i)!='e' && palabra_rescatada.charAt(i)!='i'
                    && palabra_rescatada.charAt(i)!='o' && palabra_rescatada.charAt(i)!='u'){
                contador++;
            }
        }
        return contador;
    }

    private static String imprimir_array(String[] array) {
        return Arrays.toString(array);
    }

    private static String[] rellenar_array(String[] array) {
        for (int i=0;i< array.length;i++){
            System.out.println("Introduzca una palabra en la posición "+i+":");
            array[i]=sc.next();
        }
        return array;
    }

    private static String[] crear_array(String[] array, int dimension) {
        array=new String[dimension];
        return array;
    }
}
