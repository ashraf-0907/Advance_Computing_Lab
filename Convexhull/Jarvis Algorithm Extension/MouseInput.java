import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;


public class MouseInput extends JFrame implements MouseListener
{

    int x;
    int y;

    Vector<Point> ans = new Vector<Point>();
    Vector<Point> coordinates= new Vector<Point>();

    ArrayList<Integer> xCoordinate = new ArrayList<>();
    ArrayList<Integer> yCoordinate = new ArrayList<>();

    MouseInput()
    {
        setSize(600,400);
        setTitle("Jarvis Algorithm extension");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMouseListener(this);
//        getContentPane();
//        JFrame frame=new JFrame("ConvexHull");
//        frame.setSize(500,600);
//        frame.setLocation(100,100);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        //frame.setContentPane(new Panel(x,y,ans_size,n,x1,y1));
//        frame.setVisible(true);
//        frame.addMouseListener(this);
    }
    @Override
    public void mouseClicked(MouseEvent e)
    {
        x=e.getX();
        y=e.getY();
        xCoordinate.add(x);
        yCoordinate.add(y);
        Point p=new Point(x,y);
        coordinates.add(p);
        Graphics g = getGraphics();
        g.setColor(Color.red);
        g.fillOval(x-3,y-3,6,6);
        g.setColor(Color.black);
        g.drawString("(" + x + "," + y + ")", x, y);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {
//        Algorithms a=new Algorithms();
//        ans=a.jarvis_algo(coordinates,coordinates.size());
        jarvis_algo(coordinates,coordinates.size());
        repaint();
    }

    public void jarvis_algo (Vector<Point> coordinates , int n)
    {
        Graphics g=getGraphics();
        for(int i=0;i<coordinates.size();i++)
        {
            g.setColor(Color.red);
            g.fillOval(coordinates.get(i).x-3,coordinates.get(i).y-3,6,6);
            g.setColor(Color.black);
            g.drawString("(" + x + "," + y + ")", x, y);
        }
        if(coordinates.size()<3)
        {
            System.out.println("Needed at least 3 points to draw the polygon");
        }
        int left_most=0;
        for(int i=0;i<n;i++)
        {
            if(coordinates.get(left_most).x>coordinates.get(i).x)
                left_most=i;
        }

        // declare two variable from where we start and goes cyclic
        g.setColor(Color.black);
        int start=left_most;
        int pointer;
        int j=0;
        int k=start;
        do {
            Point p=new Point(coordinates.get(start).x,coordinates.get(start).y);

            ans.add(p);
            g.setColor(Color.black);
            g.drawLine(coordinates.get(k).x,coordinates.get(k).y,coordinates.get(start).x,coordinates.get(start).y);

           // g.drawString("Selected Coordinate ( " + x + "," + y + ")", coordinates.get(start).x,coordinates.get(start).y);

//            MouseInput m=new MouseInput();
            pointer =(1+start)%n;

            for(int i=0;i<n;i++)
            {

                CrossProduct c=new CrossProduct();
                if(c.checker(coordinates.get(start),coordinates.get(i),coordinates.get(pointer))==1)
                {
                    pointer=i;
                }
                g.setColor(Color.black);
                g.drawLine(coordinates.get(start).x,coordinates.get(start).y,coordinates.get(pointer).x,coordinates.get(pointer).y);
                try {
                    // Pause the current thread for 1 second (1000 milliseconds)
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // Handle the exception
                    e.printStackTrace();
                }

                g.setColor(getBackground());
                g.drawLine(coordinates.get(start).x,coordinates.get(start).y,coordinates.get(pointer).x,coordinates.get(pointer).y);
            }
            k=start;
            start=pointer;
        }while (start != left_most);

        System.out.println("Co-ordinates forming the convex hull are:-");
        for(int i=0;i<ans.size();i++) {

            System.out.println("x = " + ans.get(i).x + " y = " + ans.get(i).y);
        }
    }

    void print()
    {
        System.out.println("Co-ordinates forming the convex hull are:-");
        for(int i=0;i<ans.size();i++) {

            System.out.println("x = " + ans.get(i).x + " y = " + ans.get(i).y);
        }
    }



//    public void paint(Graphics g) {
//        super.paint(g);
//
//        g.setColor(Color.red);
//        for (int i = 0; i < coordinates.size(); i++) {
//            int x = coordinates.get(i).x;
//            int y = coordinates.get(i).y;
//            g.fillOval(x - 3, y - 3, 6, 6);
//        }
//        g.setColor(Color.black);
//        int[] xPoints = new int[ans.size()];
//        int[] yPoints = new int[ans.size()];
//
//        for (int i = 0; i < ans.size(); i++) {
//            xPoints[i] = ans.get(i).x;
//            yPoints[i] = ans.get(i).y;
//        }
//
//        for (int i = 0; i < ans.size() - 1; i++) {
//            int x1 = xPoints[i];
//            int y1 = yPoints[i];
//            int x2 = xPoints[i + 1];
//            int y2 = yPoints[i + 1];
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            g.drawLine(x1, y1, x2, y2);
//        }

        // Draw the final line to close the polygon
//        if (ans.size() > 0) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            int x1 = xPoints[ans.size() - 1];
//            int y1 = yPoints[ans.size() - 1];
//            int x2 = xPoints[0];
//            int y2 = yPoints[0];
//            g.drawLine(x1, y1, x2, y2);
//        }
//    }


    public static void main(String [] args) {
        MouseInput m = new MouseInput();
//        m.print();
    }
}
