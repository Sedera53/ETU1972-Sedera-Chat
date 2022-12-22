
import java.util.ArrayList;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
public class ClientHandler implements Runnable{

    public static ArrayList<ClientHandler> ClientHandlers=new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;

    public ClientHandler(Socket socket){
        try{
            this.socket=socket;
            this.bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername =bufferedReader.readLine();
            ClientHandlers.add(this);
            broadcastMessage("SERVER:"+ clientUsername +" has entered the chat !");
        }
        catch(IOException e){
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }

    public void run(){
        String messageFromClient;
        while(socket.isConnected()){
            try{
                messageFromClient= bufferedReader.readLine();
                broadcastMessage(messageFromClient);
            }
            catch(IOException e){
                closeEverything(socket,bufferedReader,bufferedWriter);
                break;
            }
        }
    }

    public  void broadcastMessage(String messageToSent){
        for(ClientHandler clientHandler : ClientHandlers){
            try{
                if(!clientHandler.clientUsername.equals(clientUsername)){
                    clientHandler.bufferedWriter.write(messageToSent);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }

            }
           catch(IOException e){
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
        }
    }

    public void removeclientHandler(){
        ClientHandlers.remove(this);
        broadcastMessage("SERVER: " + clientUsername + " has left the chat !");
    }

    public void closeEverything( Socket socket,BufferedReader bufferedReader,BufferedWriter bufferedWriter){
        removeclientHandler();
        try{
            if(bufferedReader != null){
                bufferedReader.close();
            }
            if(bufferedWriter != null){
                bufferedWriter.close();
            }
            if(socket != null){
                socket.close();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
       
    }

}