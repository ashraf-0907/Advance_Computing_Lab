import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    int x[];
    int y[];
    int size;
    int size2;
    int x1[];
    int y1[];
    Panel(int x[],int y[],int size,int size2,int x1[],int y1[])
    {
        this.x=new int[size];
        this.y=new int[size];
        this.x1=new int[size2];
        this.y1=new int [size2];
        for(int i =0 ;i<size;i++)
        {
            this.x[i]=x[i];
            this.y[i]=y[i];
        }
        this.x1=x1;
        this.y1=y1;
        this.size=size;
        this.size2=size2;
    }
    public void paintComponent(Graphics g)
    {
        Graphics2D g2= (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
        for(int i=0;i<size2;i++)
            g2.drawLine(x1[i],y1[i],x1[i],y1[i]);
        g.drawPolygon(x,y,size);
    }
}
