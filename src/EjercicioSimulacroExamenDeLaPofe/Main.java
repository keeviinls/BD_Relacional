package EjercicioSimulacroExamenDeLaPofe;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import ejerciciopractico.Jugador;

import java.sql.*;
import java.util.*;

public class Main {
    static Emisora[] listado_emisoras;
    static ArrayList<EmisoraOnline> arraylist_emisora = new ArrayList<>();
    List<Emisora> arraylist = new LinkedList<>();
    static Connection conn = null;

    public static void main(String[] args) throws SQLException, AccionInvalida {
        int opcion = 0;
        int dorsal = 0;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("0. Salir del programa");
            System.out.println("1. Crear base de datos SimulacroExamen");
            System.out.println("2. Crear tabla emisoraonline");
            System.out.println("3. Insertar emisora online");
            System.out.println("4. Buscar emisoras por url introducida por teclado y añadirlo a un array estático");
            System.out.println("5. Almacenar en el arraylist emisoras con beneficios superiores a 4000 euros");
            System.out.println("6. Sacar la version del SGBD");
            System.out.println("Introduzca una opción por favor");
            opcion = sc.nextInt();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","admin");

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
                case 3: //crear un objeto de la clase emisora
                    EmisoraOnline e = new EmisoraOnline(1, "los 40", 50000000, "www.los40.com");
                         /* System.out.println("Introduzca el nidentifcaodr de la emisora");
                          int num_emisora = sc.nextInt();
                          System.out.println("Introduzca el nombre de la emisora");
                          String nombre_emisora = sc.next();
                          System.out.println("Introduzca el numero de oyentes de la emisora");
                          int numer_oeyntes = sc.nextInt();
                          System.out.println("Introduzca la url de la emisora");
                         String url = sc.next();
                         EmisoraOnline e1 = new EmisoraOnline(num_emisora,nombre_emisora,num_emisora,url);*/
                    insertar_emisora(e);
                    break;
                case 4:
                    System.out.println("¿Cuál es el filtro de numero de oyentes");
                    int num_oyentes = sc.nextInt();
                    buscar_añadir_array_estatico(num_oyentes);
                    break;
                case 5:
                    añadir_arraylist_beneficios();
                    break;
                case 6:
                    String versionSGBD = sacar_versionSGBD();
                    System.out.println("la version SGBD" + versionSGBD);
                    break;
            }   }         while (opcion != 0) ;


        }



    private static void buscar_añadir_array_estatico(int num_oyentes) throws SQLException, AccionInvalida {
        asignar_bd();
        PreparedStatement ps = conn.prepareStatement("select count(*) from emisoraonline where num_oyentes<? ");
        ps.setInt(1,num_oyentes);
        ResultSet rs = ps.executeQuery();
        int dimension=0;
        while (rs.next()){
         dimension = rs.getInt(1);
        }

         listado_emisoras = new EmisoraOnline[dimension];
         ps= conn.prepareStatement("select * from emisoraonline where num_oyentes<?");
         ps.setInt(1,num_oyentes);
         rs = ps.executeQuery();
         int i =0;
         while (rs.next()) {
             EmisoraOnline e = new EmisoraOnline(rs.getInt(1),rs.getString(2),rs.getInt(5),rs.getString(6));
             listado_emisoras[i]= e;
             i++;
         }

         /*for (int j=0;j< listado_emisoras.length;j++)
         {
           System.out.println(listado_emisoras[j].toString());


         }*/


        System.out.println(Arrays.toString(listado_emisoras));


    }

    private static void añadir_arraylist_beneficios() throws SQLException, AccionInvalida {
        asignar_bd();
        //solo insertaremos las radios cuyos beneficios superen los 3000 euros

        PreparedStatement ps = conn.prepareStatement("select * from emisoraonline");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            if (rs.getDouble(4)>3000){
                EmisoraOnline e = new EmisoraOnline(rs.getInt(1),rs.getString(2),rs.getInt(5),rs.getString(6));
                arraylist_emisora.add(e);
            }
        }
        for (EmisoraOnline e: arraylist_emisora){
            System.out.println(e.toString());
        }
    for (int i=0;i<arraylist_emisora.size();i++){
        System.out.println(arraylist_emisora.get(i).toString());
    }


    }

    private static void insertar_emisora(EmisoraOnline e) throws SQLException {
        asignar_bd();

        PreparedStatement ps  = conn.prepareStatement("insert into emisoraonline values (?,?,?,?,?,?)");
        ps.setInt(1,e.getNum_emisora());
        ps.setString(2,e.getNombre_emisora());
        ps.setBoolean(3,e.isEmitiendo());
        ps.setDouble(4,e.getBeneficios());
        ps.setInt(5,e.getNum_oyentes());
        ps.setString(6,e.getUrl());
        ps.executeUpdate();
        System.out.println("el registro se insertó correctamente");
    }

    private static void crear_tabla_emisora() throws SQLException {
        asignar_bd();

        String query = "create table emisoraonline(" +
                "num_emisora int not null primary key," +
                "nombre varchar(50) not null," +
                "emitiendo boolean," +
                "beneficios double," +
                "num_oyentes int," +
                "url varchar(100)" +
                ")";
        Statement st = conn.createStatement();
        st.executeUpdate(query);


    }

    private static void crear_bd() throws SQLException {

        String query = "create database simulacroexamen";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
    }

    private static String sacar_versionSGBD() throws SQLException {

        DatabaseMetaData databaseMetaData = conn.getMetaData();
        return databaseMetaData.getDatabaseProductVersion();

         }


    private static void asignar_bd() throws SQLException{

        // asignar la base de datos por defautl
        String query = "use simulacroexamen";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("Asignación realizada correctamente");
    }
    }
