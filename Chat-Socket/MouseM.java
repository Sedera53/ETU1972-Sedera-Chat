
import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.text.BadLocationException;

public class MouseM implements MouseListener{
    JButton button;
    Client client;
    public void setButton(JButton b){
        this.button = b;
    }
    public JButton getButton(){
        return this.button;
    }
    Fenetre fenetre;
    public void setFenetre(Fenetre f){
        this.fenetre = f;
    }
    public Fenetre getFenetre(){
        return this.fenetre;
    }
  
    public MouseM(Fenetre fenetre, JButton button, Client client){
        this.setFenetre(fenetre);
        this.setButton(button);
        this.client = client;
    }
    public void	mouseClicked(MouseEvent e){
        // if(this.getButton().getText().equals("Creer")){
        //     try{
        //         String name = this.getFenetre().getJTextField().getText();
        //         String message = this.getFenetre().getJTextField2().getText();
        //         Socket socket=new Socket("localhost",1234);
        //         client = new Client(socket,name);
        //         // client.listenForMessage();
        //         // client.sendMessage(message);
        //     }
        //     catch(Exception ex){
            
        //     }
        // }
         if(this.getButton().getText().equals("Envoyer")){
             System.out.println("cl"+client.getUser());
             
            try{
            //    e) String name = this.getFenetre().getJTextField().getText();
                String message = this.getFenetre().getJTextField2().getText();
            //     Socket socket=new Socket("localhost",1234);
            //     Client client =new Client(socket,nam;
               System.out.println(message);
               
                client.listenForMessage();
                client.sendMessage(message);
            }
            catch(Exception ex){
            
            }
        }
    }
    public void	mouseEntered(MouseEvent e){

    }
    public void	mouseExited(MouseEvent e){

    }
    public void	mousePressed(MouseEvent e){

    }
    public void	mouseReleased(MouseEvent e){
        
    }
}