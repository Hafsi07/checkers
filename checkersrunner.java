import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import java.awt.GridLayout;
public class checkersrunner{
    public static void main(String[] args) {
        JFrame f=new JFrame();
        JLayeredPane p=new JLayeredPane();
        p.add(new peice(0,0,75,75));
        p.add(new peice(75,0,75,75));
        p.add(new peice(150,0,75,75));
        p.add(new cell(0,0,75,75));
        p.add(new cell(150,0,75,75));
        p.add(new cell(75,0,75,75));
        f.add(p);
        f.setLayout(new GridLayout());
        f.setVisible(true);
        f.setDefaultCloseOperation(3);
        f.setSize(200,200);
    }
}