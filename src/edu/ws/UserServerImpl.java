/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ws;

//Service Implementation Bean

import edu.logic.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;

@MTOM
@WebService(endpointInterface = "edu.ws.UserServer")
public class UserServerImpl implements UserServer {

    @Override
    public Hashtable<String, Object> getUserData(int uid, String pass) {
        
        Hashtable<String, Object> user = new Hashtable();
        DBConnector connector = new DBConnector();
        Connection con = null;
                
        try {
            con = connector.getConnection();
        

            con.setAutoCommit(false);

            PreparedStatement pstmt;
            pstmt = con.prepareStatement("SELECT * FROM user " +
                        "WHERE idUser = ? ");
            pstmt.setFloat(1, uid);
            ResultSet result = pstmt.executeQuery();
            
            String passField = "password";
            List<String> stringFields = Arrays.asList("userName", "codigo", "name", "lastName");  
            List<String> intFields = Arrays.asList("idUser");  

            result.next();
            if(result.getString(passField).compareTo(pass) == 0){
                for(String field : intFields){
                    user.put(field, result.getInt(field));
                }
                for(String field : stringFields){
                    user.put(field, result.getString(field));
                }
            }

            
            con.commit();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (con != null) 
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        return user;
    }

    @Override
    public String[] getUserDataByUserName(String userName, String pass) {
       
        String[] user = new String[]{};
        
        DBConnector connector = new DBConnector();
        Connection con = null;
                
        try {
            con = connector.getConnection();
        

            con.setAutoCommit(false);

            PreparedStatement pstmt;
            pstmt = con.prepareStatement("SELECT idUser, userName, codigo, user.name, lastName, profile.name AS profile_name, password "
                    + "FROM user LEFT JOIN profile ON user.idProfile = profile.idProfile " +
                        "WHERE userName = ? ");
            pstmt.setString(1, userName);
            ResultSet result = pstmt.executeQuery();
            
            String passField = "password";
            List<String> stringFields = Arrays.asList("userName", "codigo", "name", "lastName", "profile_name");  
            List<String> intFields = Arrays.asList("idUser");  

            Vector<String> userdata = new Vector();
            
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
            
            con.commit();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (con != null) { 
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return user;
    }

    
}
