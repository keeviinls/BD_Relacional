import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static Connection  conn = null;
    public static Paciente p=null;
    public static ArrayList<Paciente> listado_pacientes = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        int opcion =0;
        Paciente p=null;
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
            System.out.println("11. Insertar en BASE DE DATOS apartir de una CLASE");
            System.out.println("12. Almacenar los registros de la BASE DE DATOS en un arraylist");
            System.out.println("13. Ordenar arraylist por el numero de opearciones de manera descendente");
            System.out.println("14. Ordenar el arraylist por numero de operaciones descendente y despues por apellido ascendente");
            System.out.println("15. SHOW DATABASES");
            System.out.println("16. SHOW DATABASES WITH METADATEOBJETC");    /*si en el examen pide usar un METADATE, HAY QUE USAR ESTA OPCION, LA DE OBJECT.*/


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

                case 11: p = construir_objeto();
                    insertar_objeto(p);break;

                case 12: insertar_array();break;
                case 13: ordenar_noperaciones_desc();break;
                case 14: ordenar_noperaciones_desc_apellido_asc();break;
                case 15: mostrar_bd();break;
                case 16: mostrar_bd2();break;


            }


        }while (opcion!=0);

    }

    private static void mostrar_bd2() throws SQLException {
        DatabaseMetaData databaseMetaData = conn.getMetaData();
        ResultSet resultados = databaseMetaData.getCatalogs();
        while (resultados.next()){
            System.out.println(resultados.getString(1));
        }
    }

    private static void mostrar_bd() throws SQLException {
        asignar_bd();
        String query="Show DATABASES";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            System.out.println(rs.getString(1));
        }

    }

    private static void ordenar_noperaciones_desc_apellido_asc() throws SQLException{
         asignar_bd();
         listado_pacientes.sort(Comparator.comparing(Paciente::getN_operaciones).reversed().thenComparing(Paciente::getApellido));
         for (int i=0;i<listado_pacientes.size();i++){
             System.out.println(listado_pacientes.get(i).toString());
         }



    }

    private static void ordenar_noperaciones_desc() throws SQLException {
        asignar_bd();
        listado_pacientes.sort(Comparator.comparing(Paciente::getN_operaciones).reversed());
        Iterator<Paciente> itr2 = listado_pacientes.iterator();
        while (itr2.hasNext()){
            System.out.println(itr2.next().toString());

        }
    }

    private static void insertar_array() throws SQLException {
        asignar_bd();

        PreparedStatement ps = conn.prepareStatement("select * from paciente");
        ResultSet rs = ps.executeQuery();

        Paciente p=null;

        while (rs.next()){

            p=new Paciente(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4) );
            listado_pacientes.add(p);

        }

       // System.out.println(listado_pacientes.toString());

        //lo llamo "e" pq "p" ya esta en uso
        for (Paciente e : listado_pacientes){
            System.out.println(e.toString());
        }





    }

    private static void insertar_objeto(Paciente p) throws SQLException {
        asignar_bd();
        PreparedStatement ps = conn.prepareStatement("insert into paciente values (?,?,?,?)");
        ps.setString(1,p.getDni());
        ps.setString(2,p.getNombre());
        ps.setString(3, p.getApellido());
        ps.setInt(4, p.getN_operaciones());
        ps.executeUpdate();

        System.out.println("Registro objeto insertado correctamente");

    }

    private static Paciente construir_objeto() {
        //generar un objeto de la clase paciente a partir de datos introducidos por teclado.
        System.out.println("Introduzca el dni");
        String dni = sc.next();

        System.out.println("Introduzca el nombre");
        String nombre = sc.next();

        System.out.println("Introduzca el apellido");
        String apellido = sc.next();

        System.out.println("Introduzca el numero de operaciones");
        int n_operaciones = sc.nextInt();

        Paciente p = new Paciente(dni,nombre,apellido,n_operaciones);

        return p;
    }

    private static void consultar_registro() throws SQLException{
        asignar_bd();
        PreparedStatement ps = conn.prepareStatement("select * from paciente");
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