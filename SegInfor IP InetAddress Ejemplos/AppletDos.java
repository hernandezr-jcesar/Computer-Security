import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
public class UnApplet extends Applet implements ActionListener{
    int xi, yi, xf, yf;
    Graphics g;
    Panel   p;
    Label   l;
    TextField t;
    Button  b;
    public void init(){
        p = new Panel();
        l = new Label("Nombre:");
        t = new TextField(10);
        b = new Button("Ok"); b.addActionListener(this);
        p.add(l); p.add(t); p.add(b);
        add(p);
    }
    public void paint(Graphics g){
        g = getGraphics();
        g.fillOval(xi, yi, 20, 20);
        xi = xf;
        yi = yf;        
    }
    public void actionPerformed(ActionEvent ae){
        t.setText("hola");
    }
}
