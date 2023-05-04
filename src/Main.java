import java.sql.*;
import java.util.Scanner;

public class Main {
    public static Connection  conn = null;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        int opcion =0;
        do{
            System.out.println("0.Salir del programa");
            System.out.println("1. Establecer conexión con el sgbd");
            System.out.println("2. Crear la bd ut14");
            System.out.println("3. Crear la tabla paciente");
            System.out.println("4. Borrar la tabla paciente");
            System.out.println("5. Asignar base de datos");
            System.out.println("6. Insertar registro en la tabla paciente");
            System.out.println("7. Vaciar tabla");
            System.out.println("8. Eliminar por dni introducido por teclado");
            System.out.println("9. Actualizar el numero de operaciones de un paciente cuyo DNI es introducido por teclado");
            System.out.println("10. Consultar datos ");
            System.out.println("Introduzca una opción por favor");
            opcion = sc.nextInt();

            switch (opcion){
                case 0: System.out.println("Gracias por usar el programa");break;
                case 1: establecer_conexion();break;
                case 2: crear_bd();break;
                case 3: crear_tabla();break;
                case 4: borrar_tabla();break;
                case 5: asignar_bd();break;
                case 6: insetar_registro();break;
                case 7 : vaciar_tabla();break;
                case 8 : borrar_registro();break;
                case 9 : actualizar_registro();break;
                case 10: consultar_registro();break;
            }


        }while (opcion!=0);

    }

    private static void consultar_registro() throws SQLException{
        asignar_bd();
        PreparedStatement ps = conn.prepareStatement("select * from paciente where dni=?");
        System.out.println("Introduzca el dni del paciente a actualizar");
        String dni = sc.next();
        ps.setString(1, dni);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            System.out.println("El paciente con DNI: "+rs.getString(1)+ " y NOMBRE: "+rs.getString(2)
             + " y APELLIDOS "+rs.getString(3) + " tiene un total de "+ rs.getString(4)+ " OPERACIONES");
        }

    }

    private static void actualizar_registro() throws SQLException{
        asignar_bd();
        PreparedStatement ps = conn.prepareStatement("update paciente set n_operaciones= ? where dni=?");
        System.out.println("Introducido el dni del paciente a actualizar");
        String dni = sc.next();
        System.out.println("Introduzca el nuevo numero de operaciones");
        int n_operaciones = sc.nextInt();
        ps.setInt(1, n_operaciones);
        ps.setString(2,dni);
        ps.executeUpdate();
        System.out.println("Paciente actualizado correctamente");
    }


    private static void borrar_registro() throws SQLException{
        asignar_bd();

        PreparedStatement ps = conn.prepareStatement("delete from paciente where dni=?");
        System.out.println("Introduzca el dni del paciente que quiere borrar");
        String dni = sc.next();
        ps.setString(1,dni);
        ps.executeUpdate();
        System.out.println("Registro eliminado correctamente");
    }

    private static void vaciar_tabla() throws SQLException {
        asignar_bd();
        String query = "truncate paciente;";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("La tabla ha sido vaciada correctamente");
    }

    private static void insetar_registro() throws SQLException {
        asignar_bd();
        PreparedStatement ps = conn.prepareStatement("insert into paciente values (?,?,?,?)");
        System.out.println("Introduzca el dni");
        String dni = sc.next();

        System.out.println("Introduzca el nombre");
        String nombre = sc.next();


        System.out.println("Introduzca el apellido");
        String apellido = sc.next();


        System.out.println("Introduzca el numero de operaciones");
         int n_operaciones = sc.nextInt();

         ps.setString(1,dni);
         ps.setString(2,nombre);
        ps.setString(3,apellido);
        ps.setInt(4,n_operaciones);
        ps.executeUpdate();
        System.out.println("Registro insertado correctamente");

    }

    private static void borrar_tabla() throws SQLException{
        asignar_bd();
        String query = "drop table paciente";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("Tabla borrada correctamente");

    }

    private static void crear_tabla() throws SQLException {
        asignar_bd();

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