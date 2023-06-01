
/**
 * SSTF algorithm complixicity---> n^2
*/

import java.util.*;

public class SSTF {
    private int[] request;
    private int head;
    private int n;// no of request
    public Scanner sc = new Scanner(System.in);

    SSTF(int head, int n) {
        this.head = head;
        this.n = n;
        request = new int[n];
    }

    public void inputRequest() {
        System.out.println("Enter the requests:- ");
        for (int i = 0; i < n; i++) {
            request[i] = sc.nextInt();
        }
    }

    public int findMin(int[] arr, int p, boolean[] visited) {
        int index = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(p - arr[i]);
            if (min > diff && visited[i] == false) {
                min = diff;
                index = i;
            }
        }
        System.out.println("Selected: " + arr[index]);
        return index;
    }

    public void algorithm() {
        boolean[] visited = new boolean[n];
        int q = 0;
        int[] arr = new int[n];
        int p = findMin(request, head, visited);
        arr[q++] = Math.abs(request[p] - head);
        visited[p] = true;
        for (int i = 0; i < n - 1; i++) {
            int temp = request[p];
            p = findMin(request, temp, visited);
            visited[p] = true;
            System.out.println("p=" + p);
            arr[q++] = Math.abs(request[p] - temp);
        }
        printArray(arr);
    }

    void printArray(int[] arr) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            System.out.println(arr[i]);
            sum += arr[i];
        }
        System.out.println();
        System.out.println(sum);
    }

    public static void main(String[] args) {
        int head;
        int n;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the no of requests: ");
        n = sc.nextInt();
        System.out.print("Enter the head: ");
        head = sc.nextInt();
        SSTF s = new SSTF(head, n);
        s.inputRequest();
        s.algorithm();
    }
}