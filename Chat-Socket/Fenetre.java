import javax.swing.*;
import java.awt.*;
public class Fenetre extends JFrame{

    JTextField name;
    JTextField message;
    public void setJTextField(JTextField name){
        this.name = name;
    }
    public JTextField getJTextField(){
        return this.name;
    }
    public void setJTextField2(JTextField name){
        this.message = name;
    }
    public JTextField getJTextField2(){
        return this.message;
    }
     
    public Fenetre(Client client){
        this.setSize(500,500);
        JPanel panel = new JPanel();
        JTextField name = new JTextField("");
        JTextField message = new JTextField("");
        
        name.setPreferredSize(new Dimension(400,20));
        message.setPreferredSize(new Dimension(400,20));
        
        //this.setJTextField(client.getUsername());
        this.setJTextField2(message);

        panel.add(name);
        panel.add(message);
        JButton button = new JButton("Creer");
        button.addMouseListener(new MouseM(this, button, client));
        panel.add(button);

        JButton button2 = new JButton("Envoyer");
        button2.addMouseListener(new MouseM(this, button2, client));
        panel.add(button2);

        this.setVisible(true);
        this.add(panel);
    }
//     public static void main(String[] args){
//         JFrame fen=new JFrame("Client");
//         fen.setSize(400,200);

//         JPanel pan=new JPanel();
//         fen.setContentPane(pan);
//         fen.setVisible(true);


// }
    
}
    