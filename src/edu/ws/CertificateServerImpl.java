/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ws;

//Service Implementation Bean

import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;

@MTOM
@WebService(endpointInterface = "edu.ws.CertificateServer")
public class CertificateServerImpl implements CertificateServer {

    @Override
    public byte[] downloadCertificate(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String uploadCertificate(byte[] data) {
        throw new UnsupportedOperationException("Not supported yet.");
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
