package edu.logic;

import edu.ws.FileServerImpl;
import javax.xml.ws.Endpoint;

//Endpoint publisher
public class FilePublisher{

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9999/ws/file", new FileServerImpl());

        System.out.println("Server is published!");
    }

}
