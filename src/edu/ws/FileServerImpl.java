package edu.ws;

import edu.logic.DocumentHandle;
import edu.logic.Setting;
import edu.logic.PropertiesTool;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;
/**
 *
 * @author lmparra
 * 
 * <code>FileServerImpl</code> class implements <code>FileServer</code> class
 * to create a web service that allow upload and download files.
 */

//Service Implementation Bean
@MTOM
@WebService(endpointInterface = "edu.ws.FileServer", serviceName="FSImpl")
public class FileServerImpl implements FileServer{
    
    private PropertiesTool prop;
    private String fileName = Setting.BASE_PATH + Setting.PROPERTIES_FILE;
    private InputStream in;
    private DocumentHandle document;
    
    public FileServerImpl() throws IOException {
        prop = new PropertiesTool(fileName);
    }

    @Override
    public byte[] downloadFile(String name) {        
        document = new DocumentHandle(prop.getProperty("ws.dl_folder"), name);
        try {
            return document.readDocument();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String uploadFile(byte[] data, String name) {        
        document = new DocumentHandle(name, prop.getProperty("ws.dl_folder"));
        
        return document.writeDocument(data);
    }

}
