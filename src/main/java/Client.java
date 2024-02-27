import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        System.out.print("Enter size of the Board: ");
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
         sc.nextLine();
        System.out.print("Enter Player 1 Name:");
        String player1 = sc.nextLine();
       // System.out.println();
        System.out.print("Enter Player 2 Name:");
        String player2 = sc.nextLine();

        TicTacToeGame game = new TicTacToeGame(size,player1,player2);
        String winner = game.startGame();

        if( winner.equalsIgnoreCase("tie")){
            System.out.println("The game is a Tie :)");
        }else{
            System.out.println("========================================================");
            System.out.println(winner+" is the Winner, yayyyyyyy!!!!!!!!!");
            System.out.println("=========================================================");

        }

    }


}
