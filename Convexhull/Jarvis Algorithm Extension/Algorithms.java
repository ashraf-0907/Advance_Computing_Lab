import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class Algorithms
{
    Vector<Point> ans = new Vector<Point>();
    public Vector<Point> jarvis_algo (Vector<Point> coordinates , int n)
    {
        if(coordinates.size()<3)
        {
            System.out.println("Needed at least 3 points to draw the polygon");
            return ans;
        }
        int left_most=0;
        for(int i=0;i<n;i++)
        {
            if(coordinates.get(left_most).x>coordinates.get(i).x)
                left_most=i;
        }

        // declare two variable from where we start and goes cyclic

        int start=left_most;
        int pointer;
        int j=0;

        do {

            Point p=new Point(coordinates.get(start).x,coordinates.get(start).y);

            ans.add(p);
//            MouseInput m=new MouseInput();
            pointer =(1+start)%n;

            for(int i=0;i<n;i++)
            {
                CrossProduct c=new CrossProduct();
                if(c.checker(coordinates.get(start),coordinates.get(i),coordinates.get(pointer))==1)
                {
                    pointer=i;
                }
            }
            start=pointer;
        }while (start != left_most);

        System.out.println("Co-ordinates forming the convex hull are:-");
        for(int i=0;i<ans.size();i++) {

            System.out.println("x = " + ans.get(i).x + " y = " + ans.get(i).y);
        }
        return ans;
    }
}
