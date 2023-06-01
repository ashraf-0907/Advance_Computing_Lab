import java.util.*;
import java.lang.*;
import java.awt.*;
import javax.swing.*;

/*
        <applet code="ConvexHull" height="400" width="400" />
         */
public class ConvexHull extends JPanel
{
    private Point [] coordinates;
    private int n;
    Point [] ans ;
    static Scanner sc = new Scanner(System.in);
    int ans_size=0;

    int x[];
    int y[];

    int x1[];
    int y1[];

    ConvexHull(int n)
    {
        this.n=n;
        coordinates = new Point[n];
        ans = new Point[n+1];
        x1=new int[n];
        y1=new int[n];
    }

    void input()
    {
        for(int i=0;i<n;i++)
        {
            int x;
            int y;

            System.out.print("Enter the x co-ordinate ");
            x=sc.nextInt();
            System.out.print("Enter the y co-ordinate ");
            y=sc.nextInt();
            x1[i]=x*30;
            y1[i]=y*30;
            coordinates[i]=new Point(x,y);
        }
    }

//    void print1()
//    {
//        System.out.println("Co-ordinates forming the convex hull are:-");
//        for(int i=0;i<n;i++)
//        {
//            CrossProduct c=new CrossProduct();
//            System.out.println("x = "+coordinates[i].x+" y = "+coordinates[i].y);
//        }
//    }
    void jarvis_algorithm()
    {
        // find the left most point

        int left_most=0;
        for(int i=0;i<n;i++)
        {
            if(coordinates[left_most].x>coordinates[i].x)
                left_most=i;
        }

        // declare two variable from where we start and goes cyclic

        int start=left_most;
        int pointer;
        int j=0;

        do {

            ans[j++]=new Point(coordinates[start].x,coordinates[start].y);

            pointer =(1+start)%n;

            for(int i=0;i<n;i++)
            {
                CrossProduct c=new CrossProduct();
                if(c.checker(coordinates[start],coordinates[i],coordinates[pointer])==1)
                {
                    pointer=i;
                }
            }
            start=pointer;
        }while (start != left_most);
    }
    void print()
    {
        System.out.println("Co-ordinates forming the convex hull are:-");
        for(int i=0;i<n;i++)
        {
            if(ans[i]==null)
                break;
            System.out.println("x = "+ans[i].x+" y = "+ans[i].y);
            ans_size++;
        }
    }

    public void vertices()
    {
        x=new int[ans_size];
        y=new int[ans_size];

        for(int i=0;i<ans_size;i++)
        {
            x[i]=ans[i].x*30;
            y[i]=ans[i].y*30;
        }
//        for(int i=0;i<ans_size;i++)
//            System.out.println("x= "+x[i]+"y= "+y[i]);

    }

    public void polygon()
    {
        JFrame frame=new JFrame("ConvexHull");
        frame.setSize(500,600);
        frame.setLocation(100,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Panel(x,y,ans_size,n,x1,y1));
        frame.setVisible(true);
    }

    public static void main(String [] args)
    {
        int n;
        System.out.print("Enter the no of coordinates ");
        n=sc.nextInt();
        if(n<3)
        {
            System.out.print("Jarvis algorithm needs atleast 3 vertices");
            return;
        }

        ConvexHull j = new ConvexHull(n);
        j.input();
        j.jarvis_algorithm();
        j.print();
        j.vertices();
        j.polygon();
    }
}
