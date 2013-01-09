/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ws;

import edu.logic.Archivo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;
/**
 *
 * @author lmparra
 */

//Service Implementation Bean
@MTOM
@WebService(endpointInterface = "edu.ws.FileServer")
public class FileServerImpl implements FileServer{
    
    private Properties prop;
    private String fileName = "ws.properties";
    private InputStream in;
    
    public FileServerImpl() {
        prop = new Properties();
        try {
            in = new FileInputStream(fileName);           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            prop.load(in);
        } catch (IOException ex) {
            Logger.getLogger(FileServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public byte[] downloadFile(String name) {        
        File image = new File(prop.getProperty("ws.dl_folder") + name);
        
        return null;
    }

    @Override
    public String uploadFile(byte[] data) {        
        Archivo archivo = new Archivo(prop.getProperty("ws.dl_file"), prop.getProperty("ws.dl_folder"));
        
        return archivo.escribeArchivo(data);
    }

}
