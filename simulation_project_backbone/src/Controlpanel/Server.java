package Controlpanel;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Server{

    //private HashMap<String,ClientHandler> clintList = new HashMap<>();
    private ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private ArrayList<ClientHandler> parrentList = new ArrayList<>();
    int index =0;
    public Server(int port){
        new Connection(port).start();
    }

    private class Connection extends Thread{
        private final int port;
        public Connection(int port) {
            this.port = port;
        }
        @Override
        public void run() {
            Socket socket;
            try(ServerSocket serverSocket = new ServerSocket(port)){
                while(!Thread.interrupted()){
                    socket = serverSocket.accept();
                    new ClientHandler(socket).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private class ClientHandler extends Thread {
        private ObjectInputStream ois;
        private ObjectOutputStream oos;

        public ClientHandler(Socket s) {
            try {
                ois = new ObjectInputStream(s.getInputStream());
                oos = new ObjectOutputStream(s.getOutputStream());
                clientHandlers.add(this);

                if(clientHandlers.size() <= 5)
                {
                   parrentList.add(this);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    System.out.println(ois.readObject());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        public void write(String message) {
            try {
                oos.writeObject(message);
                oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
