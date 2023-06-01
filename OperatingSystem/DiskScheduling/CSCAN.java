/* Complixcity is nlogn ---> MergeSort
 * Complixcity is logn ---> BinearySearch
 * Complixcity is n ---> CSCAN
 * total complixcity is nlogn
 */

import java.util.*;

public class CSCAN
{
    private int[] request;
    private int head;
    private int n;// no of request
    private int disc_size;
    public Scanner sc = new Scanner(System.in);
    CSCAN(int head, int n,int disc_size) {
        this.head = head;
        this.n = n;
        this.disc_size=disc_size;
        request = new int[n + 3];
    }

    public void inputRequest() {
        System.out.println("Enter the requests:- ");
        for (int i = 0; i < n; i++) {
            request[i] = sc.nextInt();
        }
        request[n] = head;
        request[n+1]=0;
        request[n+1]=disc_size-1;
    }
    void algorithm() {
        MergeSort m = new MergeSort();
        m.algorithm(request, 0, n);
        BinarySearch b = new BinarySearch();
        int k = b.algorithm(request, head, 0, n);
        // int temp=k;
        int[] arr = new int[n + 3];
        boolean[] visited = new boolean[n + 3];
        int q = 0;
        while (true) {
            arr[q++] = Math.abs(request[k] - request[(k + 1) % (n + 3)]);
            visited[k] = true;
            k = (k + 1) % (n + 3);
            System.out.println("k=" + k);
            if (visited[k] == true)
                break;
        }
        printArray(arr);
    }
    void printArray(int[] arr) {
        int sum = 0;
        for (int i = 0; i < n+2; i++) {
            System.out.println(arr[i]);
            sum += arr[i];
        }
        System.out.println();
        System.out.println(sum);
    }
    public static void main(String[] args) {
        int head;
        int n;
        int disc_size;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the no of requests: ");
        n = sc.nextInt();
        System.out.print("Enter the head: ");
        head = sc.nextInt();
        System.out.print("Enter the disc size: ");
        disc_size=sc.nextInt();
        CSCAN s = new CSCAN(head, n,disc_size);
        s.inputRequest();
        s.algorithm();
    }
}
