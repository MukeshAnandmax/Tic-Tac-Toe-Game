public class Client {
    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame();
        String winner = game.startGame();

        if( winner.equalsIgnoreCase("tie")){
            System.out.println("The game is a Tie :)");
        }else{
            System.out.println(winner+" is the Winner :)");
        }

    }


}
