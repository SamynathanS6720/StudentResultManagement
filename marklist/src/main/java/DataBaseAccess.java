
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseAccess{
    private static DataBaseAccess dbAccess = null ;
    private Connection connection = null ; 
    private Statement stmt = null ;
    private final String url = "jdbc:postgresql://localhost:5432/CollegeManagement";
    private final String user = "postgres" ;
    private String password = "INC_2038" ;

    private DataBaseAccess() {
        try{
            Class.forName("org.postgresql.Driver");
        }catch( ClassNotFoundException ex  ){
            System.out.println( " Driver Class not found : " + ex.getMessage() );
        }
    }

    public static DataBaseAccess getAccessobject(){
        if( dbAccess == null ){
            dbAccess = new DataBaseAccess();
            return dbAccess ;
        }
        else{
            return dbAccess ;
        }
    }

    public boolean openConnection( ){
        if( this.connection == null){
            try{
                this.connection = DriverManager
                .getConnection( this.url , this.user ,  this.password );
                // this.connection.setAutoCommit(true);
                return false ;
            }catch( Exception e ){
                System.err.println( e.getClass().getName()+": "+ e.getMessage() );
                // System.out.println( "Cannection failed Retry");
                return false ;
            }
        }
        else{
            return true ;
        }
    }

    public boolean closeConnection() {
            System.gc();
            return true ;
    }

    public Statement getConnectedStatement(){
        try{
            return this.connection.createStatement();    
        }catch( SQLException ex ){
            ex.printStackTrace();
            return null ;
        }
    }

    public PreparedStatement getConnectedPreparedStatement( String query ){
        try{
            return this.connection.prepareStatement( query ) ;
        }catch( SQLException ex ){
            ex.printStackTrace();
            return null ;
        }
    }

    public boolean createSchema( String schemaName ){
        try {
            String adminSchema = "CREATE SCHEMA IF NOT EXISTS "+ schemaName + " ;" ;
            this.stmt = this.connection.createStatement();
            this.stmt.executeUpdate( adminSchema ); 
            return true ;
        }catch( SQLException ex ){
            return false ;
        }
    }

    public boolean setPath( String searchPath ){
        Statement stmt = null ;
        try {
            String adminSchema = "SET SEARCH_PATH TO "+ searchPath + " ;" ;
            stmt = this.connection.createStatement();
            stmt.executeUpdate( adminSchema ); 
            return true ;
        }catch( SQLException ex ){
            return false ;
        }
    }


    @Override
    protected void finalize() throws Throwable {
        System.out.println( "Connection " );  
        try{
            if( this.stmt == null ){
                this.stmt.close();
                this.stmt = null ;
            }
        }catch( SQLException ex){}
        try{
            if( this.connection != null ){
                this.connection.close();
                this.connection = null ;
            }
        }
        catch( SQLException connectionException ){
            // connectionException.printStackTrace();
        }

    }
}