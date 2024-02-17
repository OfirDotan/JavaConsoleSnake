import java.util.Scanner;
public class UserInput implements Runnable {
    @Override
    public void run() {
        while (!Main.isGameOver){
            Scanner reader = new Scanner(System.in);
            String userInput = reader.next();
            if(userInput.toUpperCase().charAt(0) == 'W'){
                Main.direction = 2;
            }
            else if(userInput.toUpperCase().charAt(0) == 'A'){
                Main.direction = 3;
            }
            else if(userInput.toUpperCase().charAt(0) == 'S'){
                Main.direction = 4;
            }
            else if(userInput.toUpperCase().charAt(0) == 'D'){
                Main.direction = 1;
            }
        }

    }
}
