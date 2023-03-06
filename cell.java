import java.awt.*;
import javax.swing.*;

public class cell extends JLabel{ 
    cell(int x,int y,int w, int h){ 
        Color couleur;
        if (x%2==0 && y%2!=0 || x%2!=0 && y%2==0){couleur=new Color(200,0,0);}else{couleur=new Color(10,10,10);}
        this.setBounds(x,y,w,h);
        this.setOpaque(true);
        this.setBackground(couleur);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }
}