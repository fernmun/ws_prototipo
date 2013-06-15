/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ws;

/**
 *
 * @author lmparra
 */

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

//Service Endpoint Interface
/**
 *
 * @author lmparra
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface FileServer{

    //download a image from server
    /**
     *
     * @param name File name
     * @return
     */
    @WebMethod byte[] downloadFile(String name);

    //update image to server
    /**
     *
     * @param data File bytes map to transmit
     * @param String File name
     * @return
     */
    @WebMethod String uploadFile(byte[] data, String name);

}
