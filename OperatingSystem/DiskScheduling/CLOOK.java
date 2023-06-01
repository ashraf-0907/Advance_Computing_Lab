
/* Complixcity is nlogn ---> MergeSort
 * Complixcity is logn ---> BinearySearch
 * Complixcity is n ---> CLOOK
 * total complixcity is nlogn
 */
import java.util.*;

public class CLOOK {
    private int[] request;
    private int head;
    private int n;// no of request
    public Scanner sc = new Scanner(System.in);

    CLOOK(int head, int n) {
        this.head = head;
        this.n = n;
        request = new int[n + 1];
    }

    public void inputRequest() {
        System.out.println("Enter the requests:- ");
        for (int i = 0; i < n; i++) {
            request[i] = sc.nextInt();
        }
        request[n] = head;
    }

    void algorithm() {
        MergeSort m = new MergeSort();
        m.algorithm(request, 0, n);
        BinarySearch b = new BinarySearch();
        int k = b.algorithm(request, head, 0, n);
        // int temp=k;
        int[] arr = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        int q = 0;
        while (true) {
            arr[q++] = Math.abs(request[k] - request[(k + 1) % (n + 1)]);
            visited[k] = true;
            k = (k + 1) % (n + 1);
            System.out.println("k=" + k);
            if (visited[k] == true)
                break;
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
        CLOOK s = new CLOOK(head, n);
        s.inputRequest();
        s.algorithm();
    }
}
