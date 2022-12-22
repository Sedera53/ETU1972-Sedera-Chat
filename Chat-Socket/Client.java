import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.*;   
import java.util.Scanner;
public class Client{

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    public void setUser(String a){ this.username = a; }
    public String getUser(){return this.username;}
    public Client(Socket socket,String username){
        try{
            this.socket=socket;
            this.bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username=username;
        }
        catch(IOException e){
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
        
    }
    public void sendMessage(String message){
        try{
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            bufferedWriter.write(username + ":" + message);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            // Scanner scanner = new Scanner(System.in);
           // while(socket.isConnected()){
            //     String messageToSent= scanner.nextLine();
            //     bufferedWriter.write(username + " :" + messageToSent);
            //     bufferedWriter.newLine();
            //     bufferedWriter.flush();
            //}
        }
        catch(IOException e){
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }
    public void listenForMessage(){

        new Thread(new Runnable(){

            public void run(){
                String msgFromGroupChat;

                while(socket.isConnected()){
                    try{
                        msgFromGroupChat=bufferedReader.readLine();
                        System.out.println(msgFromGroupChat);
                    }
                    catch(IOException e){
                        closeEverything(socket,bufferedReader,bufferedWriter);
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket,BufferedReader BufferedReader,BufferedWriter bufferedWriter){
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
    public static void main(String[] args) throws Exception {
        Socket socket=new Socket("localhost",1234);
    Scanner scanner=new Scanner(System.in);
       System.out.println("Entrer your username: ");
       String username= scanner.nextLine();
        Client client = new Client(socket,username);
        client.listenForMessage();
        Fenetre fen = new Fenetre(client);
        

       
        // Socket socket=new Socket("localhost",1234);
        // Client client =new Client(socket,username);
        // client.listenForMessage();
        // client.sendMessage();

    }
}