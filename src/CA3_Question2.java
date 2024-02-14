import java.util.Stack;

/**
 *  Name: Roman Manzhelii
 *  Class Group:SD2a
 */
public class CA3_Question2
{
    public static Stack<Pair> myStack = new Stack();
    // Starter function to create the 2D array and populate it with 0
    public static int[][]  floodFillStart() {
        int[][] arr = new int[10][10];
        for (int x = 0; x < 10; x++)
        {
            for (int y = 0; y < 10; y++)
            {
                arr[x][y] = 0;
            }
        }
       return arr;
    }

    // Helper function to display the image
    public static void display(int[][] arr)
    {
        for (int x = 0; x < 10; x++)
        {
            for (int y = 0; y < 10; y++)
            {
                System.out.printf("%4d", arr[x][y]);
            }
            System.out.println();
        }
    }
    public static void fill(int r, int c, int[][] arr)
    {
        int num = 1;
        myStack.push(new Pair(r,c));
        while (!myStack.empty()){
            Pair current = myStack.pop();
            int x = current.x;
            int y = current.y;

            if(arr[x][y]==0){
                arr[x][y] = num;
                num++;

            if (x > 0 && arr[x-1][y] == 0)
                myStack.push(new Pair(x-1,y));
            if (y > 0 && arr[x][y-1] == 0)
                myStack.push(new Pair(x,y-1));
            if (x < 10 - 1 && arr[x+1][y] == 0)
                myStack.push(new Pair(x+1,y));
            if (y < 10 - 1 && arr[x][y+1] == 0)
                myStack.push(new Pair(x,y+1));
        }
    }

    }

    public static void start()
    {
       int[][] arr = floodFillStart();
       fill(0,0,arr);
        display(arr);

    }
    public static void main(String[] args) {
        start();

    }

}

class Pair {
    public int x;
    public int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}