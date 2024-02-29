import Model.*;
import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Scanner;

public class TicTacToeGame {

    private ArrayDeque<Player> deque;
    Board board;
    HashMap<Integer, Pair> boxNumber = new HashMap<>();

    public TicTacToeGame(int size,String player1,String player2) {
        initialiseGame(size,player1,player2);
    }

    public void initialiseGame(int size,String player1,String player2){
        board= new Board(size);
        deque = new ArrayDeque<>();
        mapBoxtoPair(size);

        Player p1 = new Player(player1,new PieceO());
        deque.addLast(p1);
        Player p2 = new Player(player2,new PieceX());
        deque.addLast(p2);

    }


    public String startGame(){
        boolean noWinner = true;
        int countofPlayer1turn =0;
        int countofPlayer2turn =0;
//        System.out.println("*********************************************************");
//        System.out.println("                     Board position:                     ");
//        board.printDummyBoard();
//        System.out.println("*********************************************************");

        while (noWinner){

            Player playerTurn = deque.removeFirst();

            board.printDummyBoard();
            board.printBoard();

            int countOfFreeSpaces = board.countOfFreeSpaces();

            if(countOfFreeSpaces ==0){
                noWinner=false;
                continue;
            }

            System.out.print("Player:" + playerTurn.getName() + " select box number to enter: ");
            Scanner inputScanner = new Scanner(System.in);
            int i = inputScanner.nextInt();

            if(i> board.getSize()* board.getSize()){
                System.out.println("incorrect place please give correct place");
                deque.addFirst(playerTurn);
                continue;
            }

            int inputRow = (int)boxNumber.get(i).getKey();
            int inputColumn = (int)boxNumber.get(i).getValue();

            boolean enteredSuccessfully = board.addPiece(inputRow, inputColumn, playerTurn.getPlayingPiece());

            if(!enteredSuccessfully){
                System.out.println("========================================================");

                System.out.println("Incorrect place, please give correct place");
                System.out.println("========================================================");

                deque.addFirst(playerTurn);
                continue;
            }
            deque.addLast(playerTurn);

            if(playerTurn.getName().equalsIgnoreCase("Player1")) {
                countofPlayer1turn++;
            }else {
                countofPlayer2turn++;
            }

            if(countofPlayer1turn>= board.getSize()|| countofPlayer2turn>= board.getSize()){

                boolean winner = isWinner( inputRow,inputColumn,playerTurn.getPlayingPiece().pieceType);
                if(winner){
                    System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                    board.printBoard();
                    System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

                    return playerTurn.getName();

                }

            }



        }
        return "Tie";
    }

    public  boolean isWinner(int row, int coulmn, PieceType pieceType){


        boolean rowCheck = true;
        boolean coulmnCheck= true;
        boolean diagonalCheck= true;
        boolean antiDiagonalCheck= true;

        for (int i=0;i<board.getSize();i++){

            if( board.getBoard()[row][i]==null || board.getBoard()[row][i].pieceType!=pieceType ){
                rowCheck=  false;
            }
        }

        for (int i=0;i<board.getSize();i++){

            if( board.getBoard()[i][coulmn]==null || board.getBoard()[i][coulmn].pieceType!=pieceType ){
                coulmnCheck=  false;
            }
        }

        for (int i=0, j=0;i<board.getSize();i++,j++){

            if( board.getBoard()[i][j]==null || board.getBoard()[i][j].pieceType!=pieceType ){
                diagonalCheck=  false;
            }
        }

        for (int i=0, j=board.getSize()-1;i<board.getSize();i++,j--){

            if( board.getBoard()[i][j]==null || board.getBoard()[i][j].pieceType!=pieceType ){
                antiDiagonalCheck=  false;
            }
        }

        return  rowCheck|| coulmnCheck||diagonalCheck||antiDiagonalCheck;
    }


    public  void mapBoxtoPair(int size){

        int x=1;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){

                boxNumber.put(x, new Pair(i,j));
                x++;
            }
        }

    }
}
