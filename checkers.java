import java.awt.*; 
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.awt.GridLayout;

public class checkers extends JFrame{ 
    // Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
    static int width=600; 
    static int height=width;
    static int cell_side=(height)/8;
    static int counters=height/cell_side;

    public static peice[] white=new peice[12];
    public static peice[] black=new peice[12];
    JLayeredPane p =new JLayeredPane(); 
    
    checkers(){ 
        p.setOpaque(true);
        p.setSize(width,height);
        p.setBackground(Color.BLACK);
        p.setBounds(0,0,width,height);
        p.setVisible(true);
        
        
        for (int i=0;i<counters;i++){
            for (int j=0;j<counters;j++){               
                cell x=new cell(i*cell_side,j*cell_side,cell_side,cell_side);
                p.add(x,Integer.valueOf(0));
            }
        }



        int k=0;
        int m=0;
        for (int i=0;i<counters;i++){
            for (int j=0;j<counters;j++){
                if ((i%2==0 && j%2!=0 ||i%2!=0 && j%2==0) && i<3){
                    white[k]=new peice(j*cell_side,i*cell_side,cell_side,cell_side);
                    p.add(white[k],Integer.valueOf(1));
                    System.out.println("white n"+k+" "+ j*cell_side+" "+i*cell_side);
                    k++;
                }
                if ((i%2==0 && j%2!=0 || i%2!=0 && j%2==0) && i>4){
                    black[m]=new peice(j*cell_side,i*cell_side,cell_side,cell_side);
                    p.add(black[m],Integer.valueOf(1));                    
                    // black[m].move(525,100);
                    m++;
                }
            }
        }

        //test area
        System.out.println(height+"   "+cell_side);
        white[9].move(0,0);
        black[1].move(375,150);
        black[2].move(375,300);
        black[11].move(0,0);
        white[10].move(0,0);
        System.out.println(white[6].x+" "+white[6].y);
        System.out.println(black[6].x+" "+black[6].y);
        System.out.println(Arrays.toString(peice.middle(white[6].x,white[6].y,null))+"hh");
        // System.out.println(Arrays.toString(peice.possibleblack(black[6].x,black[6].y,null))+"hh");
        //end test area

        add(p);
        setLayout(new GridLayout());
        setResizable(true);
        setTitle("Checkers");
        setDefaultCloseOperation(3);
        setVisible(true);
        setSize(width+16,height+39);
    }
    public static void main(String[] args) {
        new checkers();
    }
}