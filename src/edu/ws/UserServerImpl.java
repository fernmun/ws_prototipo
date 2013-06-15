package edu.ws;


import edu.logic.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;

@MTOM
@WebService(endpointInterface = "edu.ws.UserServer")
public class UserServerImpl implements UserServer {
    
    private HashMap<String, Object> userObject;
    private String[] user;
    private DBConnector connector;
    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet result;
    private String passField;
    private ArrayDeque userdata;
    private List<String> stringFields, intFields;

    @Override
    public HashMap<String, Object> getUserDataByUid(int uid, String pass) {
        
        userObject = new HashMap();
        connector = new DBConnector();
        connection = null;
                
        try {
            connection = connector.getConnection();
            connection.setAutoCommit(false);

            pstmt = connection.prepareStatement("SELECT * FROM user " +
                        "WHERE idUser = ? ");
            pstmt.setFloat(1, uid);
            result = pstmt.executeQuery();
            
            passField = "password";
            stringFields = Arrays.asList("userName", "codigo", "name", "lastName");  
            intFields = Arrays.asList("idUser");  

            result.next();
            if(result.getString(passField).compareTo(pass) == 0){
                for(String field : intFields){
                    userObject.put(field, result.getInt(field));
                }
                for(String field : stringFields){
                    userObject.put(field, result.getString(field));
                }
            }

            
            connection.commit();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (connection != null) { 
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return userObject;
    }

    @Override
    public String[] getUserDataByUserName(String userName, String pass) {
       
        user = new String[]{};
        
        connector = new DBConnector();
        connection = null;
                
        try {
            connection = connector.getConnection();        

            connection.setAutoCommit(false);
       
            pstmt = connection.prepareStatement("SELECT idUser, userName, codigo, lastName, profile.name AS profile_name, password "
                    + "FROM user LEFT JOIN profile ON user.idProfile = profile.idProfile " +
                        "WHERE userName = ? ");
            pstmt.setString(1, userName);
            result = pstmt.executeQuery();
            
            passField = "password";
            stringFields = Arrays.asList("userName", "codigo", "name", "lastName", "profile_name");  
            intFields = Arrays.asList("idUser");  

            userdata = new ArrayDeque();
            
            if(result.next()){
                if(result.getString(passField).compareTo(pass) == 0){
                    for(String field : intFields){
                        userdata.add(field+"::"+result.getInt(field));
                    }
                    for(String field : stringFields){
                        userdata.add(field+"::"+result.getString(field));
                    }
                }
            }
            
            user = (String[])userdata.toArray(new String[]{});
            
            connection.commit();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (connection != null) { 
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return user;
    }    
}
