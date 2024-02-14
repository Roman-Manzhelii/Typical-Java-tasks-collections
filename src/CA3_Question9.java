import java.util.Stack;
/**
 *  Name: Roman Manzhelii
 *  Class Group: SD2a
 */
enum DIRECTION {NORTH, SOUTH, EAST, WEST}

class Point {
    int x, y;
    DIRECTION dir;

    Point(int x, int y, DIRECTION dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}

public class CA3_Question9 {
    private final int[][] maze;
    private final boolean[][] visited;
    private final Stack<Point> path;
    private final int exitX;
    private final int exitY;

    public CA3_Question9(int[][] maze, int exitX, int exitY) {
        this.maze = maze;
        this.exitX = exitX;
        this.exitY = exitY;
        this.visited = new boolean[maze.length][maze[0].length];
        this.path = new Stack<>();
    }

    public boolean solve(int x, int y, DIRECTION dir) {
        if (x < 0 || x >= maze.length || y < 0 || y >= maze[0].length || maze[x][y] == 1 || visited[x][y]) {
            return false;
        }

        path.push(new Point(x, y, dir));
        visited[x][y] = true;

        if (x == exitX && y == exitY) {
            return true;
        }

        if (solve(x + 1, y, DIRECTION.SOUTH) ||
                solve(x - 1, y, DIRECTION.NORTH) ||
                solve(x, y + 1, DIRECTION.EAST) ||
                solve(x, y - 1, DIRECTION.WEST)) {
            return true;
        }

        path.pop();
        return false;
    }

    public void printPath() {
        for (Point p : path) {
            System.out.println("(" + p.x + ", " + p.y + ") " + p.dir);
        }
    }

    public static void main(String[] args) {
        int[][] maze = {
                {0, 1, 0, 0},
                {0, 0, 0, 1},
                {1, 1, 0, 0},
                {0, 0, 0, 0}
        };
        int exitX = 3, exitY = 3; // Exit coordinates

        CA3_Question9 solver = new CA3_Question9(maze, exitX, exitY);
        if (solver.solve(0, 0, DIRECTION.EAST)) {
            System.out.println("Path to exit:");
            solver.printPath();
        } else {
            System.out.println("No path found.");
        }
    }
}
