/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ws;

//Service Implementation Bean

import edu.logic.DocumentHandle;
import edu.logic.PropertiesTool;
import edu.logic.Setting;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;
/**
 *
 * @author lmparra
 * 
 * <code>CertificateServerImpl</code> class implements <code>CertificateServer</code> class
 * to create a web service that allow upload and download digital certificates.
 */

@MTOM
@WebService(endpointInterface = "edu.ws.CertificateServer")
public class CertificateServerImpl implements CertificateServer {
    
    private PropertiesTool prop;
    private String fileName = Setting.BASE_PATH + Setting.PROPERTIES_FILE;
    private InputStream in;
    private DocumentHandle document;

    @Override
    public byte[] downloadCertificate(String name) {
        document = new DocumentHandle(prop.getProperty("ws.dl_folder"), name);
        try {
            return document.readDocument();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String uploadCertificate(byte[] data, String name) {
        document = new DocumentHandle(name, prop.getProperty("ws.dl_folder"));
        
        return document.writeDocument(data);
    }

    @Override
    public String getDN(String uid) {
        if ("1".compareTo(uid) == 0) {
            return "CN=dnova, L=Bogotá, ST=Cundinamarca ,C=CO, O=Test";
        }
        else {
            return null;
        }
    }
    
}
