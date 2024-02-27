import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        System.out.print("Enter size of the Board: ");
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        TicTacToeGame game = new TicTacToeGame(size);
        String winner = game.startGame();

        if( winner.equalsIgnoreCase("tie")){
            System.out.println("The game is a Tie :)");
        }else{
            System.out.println(winner+" is the Winner :)");
        }

    }


}
