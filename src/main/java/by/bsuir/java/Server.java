package by.bsuir.java;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Server {

    private static int countClients = 0;

    public static void main(String[] args) throws IOException {
        System.out.println("Hello from a server!");

        ServerSocket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            socket = new ServerSocket(1088);

            while (true) {
                Socket client = socket.accept();
                countClients++;

                System.out.println(countClients + " client(s) connected.\n");

                inputStream = client.getInputStream();
                outputStream = client.getOutputStream();

                byte[] bytes = new byte[1024];
                inputStream.read(bytes);
                String inputStr = new String(bytes, StandardCharsets.UTF_8);

                char[] characters = inputStr.toCharArray();
                Arrays.sort(characters);
                String output = new String(characters);
                bytes = output.getBytes();
                outputStream.write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error happened!");
        } finally {
            inputStream.close();
            outputStream.close();
            socket.close();
            System.out.println("Client " + countClients + "disconnected");
        }
    }
}
