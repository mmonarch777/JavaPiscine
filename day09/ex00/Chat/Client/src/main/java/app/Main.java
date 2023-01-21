package app;

import client.Client;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Client client = new Client(Integer.parseInt(args[0].split("=")[1]));
        client.connection();
    }
}
