public class BinarySearch 
{
    public int algorithm(int []arr,int key,int low,int high)
    {
        int mid=(low+high)/2;
        if(arr[mid]==key)
        return mid;
        else if(arr[mid]>key)
        return algorithm(arr,key,low,mid-1);
        else 
        return algorithm(arr,key,mid+1,high);
    }
}
