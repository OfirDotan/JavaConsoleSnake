import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class Main {
    static boolean isGameOver;
    //Right - 1, Up - 2, Left - 3, Down - 4
    public static int direction = 4;

    public static void main(String[] args) throws InterruptedException, IOException {
        isGameOver = false;
        String [][] arr = new String[16][16];
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr.length; j++){
                arr[i][j] = " ";
            }
        }
        LinkedList<SnakePart> snake = new LinkedList<>();
        //Building the snake
        snake.add(new SnakePart(8, 4));
        snake.add(new SnakePart(8, 3));
        snake.add(new SnakePart(8, 2));

        Thread thread = new Thread(new UserInput());
        thread.start();
        generateFood(arr, snake);
        while (!isGameOver){

            moveSnake(snake, direction, arr);
            if(isGameOver){
                break;
            }
            drawSnake(arr, snake);
            print(arr);
            System.out.println("\n\n");
            Thread.sleep(700);
       }
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\tGAME OVER!\n\n\n\n\n\n\n\n\n");
    }
    public static void generateFood(String [][] arr, LinkedList<SnakePart> snake){
        Random rand = new Random();
        int x = rand.nextInt(0, 16);
        int y = rand.nextInt(0, 16);
        while(whatToPrint(snake, x, y) != "Z"){
            x = rand.nextInt(0, 16);
            y = rand.nextInt(0, 16);
        }
        System.out.println(x + " " + y);
        arr[y][x] = "%";
    }
    public static void print(String [][] arr){
        System.out.println("----------------------------------");
        for(int row = 0; row < arr.length; row++){
            System.out.print("|");
            for (int col = 0; col < arr.length; col++){
                System.out.print(arr[row][col] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("----------------------------------");
    }
    public static void  moveSnake(LinkedList<SnakePart> snake, int direction, String [][] arr){
        int newX = snake.getFirst().getX();
        int newY = snake.getFirst().getY();
        SnakePart newHead = snake.get(snake.size() - 1);
        SnakePart lastCopy = new SnakePart(newHead);
        arr[lastCopy.getY()][lastCopy.getX()] = " ";
        snake.removeLast();
        newHead.setX(newX);
        newHead.setY(newY);
        switch(direction){
            case 1:
                newX = newHead.getX() + 1;
                newHead.setX(newX);
                break;
            case 2:
                newY = newHead.getY() - 1;
                newHead.setY(newY);
                break;
            case 3:
                newX = newHead.getX() - 1;
                newHead.setX(newX);
                break;
            case 4:
                newY = newHead.getY() + 1;
                newHead.setY(newY);
                break;
        }

        if(newX >= 16 || newX < 0 || newY >= 16 || newY < 0) {
            isGameOver = true;
            return;
        }
        if(arr[newY][newX] == "O") {
            isGameOver = true;
            return;
        }
        if(arr[newY][newX] == "%"){
            snake.add(lastCopy);
            generateFood(arr, snake);
        }
        snake.addFirst(newHead);
    }
    public static void drawSnake(String [][] arr, LinkedList<SnakePart> snake){
        int count = 0;
        for(int row = 0; row < arr.length; row++){
            for (int col = 0; col < arr.length; col++){
                String whatToPrint = whatToPrint(snake, row, col);
                if(!whatToPrint.equals("Z")){
                    arr[row][col] = whatToPrint;
                }
            }
        }
    }
    public static String whatToPrint(LinkedList<SnakePart> snake,int row, int col){
        int count = 0;
        while (count < snake.size()){
            SnakePart part = snake.get(count);
            if(part.getX() == col && part.getY() == row){
                if(count == 0){
                    return  "X";
                }
                else{
                    return "O";
                }
            }
            count++;
        }
        return "Z";
    }
}