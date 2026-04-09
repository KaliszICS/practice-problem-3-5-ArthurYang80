import java.util.*;

public class PracticeProblem {
    
    public static int searchMazeMoves(String[][] maze) {
        if (maze == null || maze.length == 0) {
            return -1;
        }
        
        int rows = maze.length;
        int cols = maze[0].length;
        
        int startRow = rows - 1;
        int startCol = 0;
        
        int finishRow = -1, finishCol = -1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j].equals("F")) {
                    finishRow = i;
                    finishCol = j;
                    break;
                }
            }
        }
        
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startRow, startCol, 0});
        visited[startRow][startCol] = true;
        
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int distance = current[2];
            
            if (row == finishRow && col == finishCol) {
                return distance;
            }
            
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols &&
                    !visited[newRow][newCol]) {
                    if (!maze[newRow][newCol].equals("#") && !maze[newRow][newCol].equals("W")) {
                        visited[newRow][newCol] = true;
                        queue.offer(new int[]{newRow, newCol, distance + 1});
                    }
                }
            }
        }
        
        return -1;
    }
    
    public static int noOfPaths(String[][] maze) {
        if (maze == null || maze.length == 0) {
            return 0;
        }
        
        int rows = maze.length;
        int cols = maze[0].length;
        
        int startRow = rows - 1;
        int startCol = 0;
        
        int finishRow = -1, finishCol = -1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j].equals("F")) {
                    finishRow = i;
                    finishCol = j;
                    break;
                }
            }
        }
        
        Integer[][] memo = new Integer[rows][cols];
        return countPathsMemo(maze, startRow, startCol, finishRow, finishCol, memo);
    }
    
    private static int countPathsMemo(String[][] maze, int row, int col, int finishRow, int finishCol, Integer[][] memo) {
        if (row < 0 || row >= maze.length || col < 0 || col >= maze[0].length) {
            return 0;
        }
        
        if (maze[row][col].equals("#") || maze[row][col].equals("W")) {
            return 0;
        }
        
        if (row == finishRow && col == finishCol) {
            return 1;
        }
        
        if (memo[row][col] != null) {
            return memo[row][col];
        }
        
        int paths = 0;
        paths += countPathsMemo(maze, row - 1, col, finishRow, finishCol, memo);
        paths += countPathsMemo(maze, row, col + 1, finishRow, finishCol, memo); 

        memo[row][col] = paths;
        return paths;
    }
}