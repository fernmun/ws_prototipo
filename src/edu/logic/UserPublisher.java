package edu.logic;

import edu.ws.UserServerImpl;
import javax.xml.ws.Endpoint;

//Endpoint publisher
public class UserPublisher{

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9999/ws/user", new UserServerImpl());

        System.out.println("Server is published!");
    }

}
