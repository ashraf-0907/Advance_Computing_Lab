public class CrossProduct
{
    public int checker(Point a,Point b,Point c)
    {
        int abx= a.x-b.x;
        int acx= a.x-c.x;
        int aby= a.y-b.y;
        int acy= a.y-c.y;

        int calculate= aby*acx-acy*abx;

        if(calculate == 0)
            return 0;
        else
            return calculate>0 ? 1:2;
    }
}
