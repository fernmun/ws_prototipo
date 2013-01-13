package edu.logic;

import edu.ws.CertificateServerImpl;
import javax.xml.ws.Endpoint;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lmparra
 */
public class CertificatePublisher {
    
    public static void main(String[] args) {
        
        Endpoint.publish("http://localhost:9998/ws/certificate", new CertificateServerImpl());

        System.out.println("Server is published!");
    }
    
}