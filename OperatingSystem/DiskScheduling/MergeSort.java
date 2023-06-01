
public class MergeSort 
{
    public void merging(int []arr,int low,int mid,int high)
    {
        int i=low;
        int j=mid+1;
        int k=low;
        int []b=new int[high+1];
        while(i<=mid && j<=high)
        {
            if(arr[i]<=arr[j])
            b[k++]=arr[i++];
            else
            b[k++]=arr[j++];
        }
        while(i<=mid)
        {
            b[k++]=arr[i++];
        }
        while(j<=high)
        {
            b[k++]=arr[j++];
        }
        for(int p=low;p<=high;p++)
        arr[p]=b[p];
    }
    public void algorithm(int []arr,int low,int high)
    {
        if(low<high)
        {
            int mid=(low+high)/2;
            algorithm(arr,low,mid);
            algorithm(arr,mid+1,high);
            merging(arr, low, mid, high);
        }
    }
}
