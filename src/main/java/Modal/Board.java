package Modal;

public class Board {
    private int size;
    private Piece[][] board;

    public Board(int size) {

        this.size = size;
        board = new Piece[size][size];
    }

    public int getSize() {
        return size;
    }

    public Piece[][] getBoard() {

        return board;
    }

    public void setBoard(Piece[][] board) {
        this.board = board;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean addPiece(int row, int coulmn, Piece piece){
        if(board[row][coulmn]!=null){
            return false;
        }
        board[row][coulmn] = piece;
        return true;
    }
    public  int countOfFreeSpaces(){
        int count=0;
        for(Piece[] p:board){
            for(Piece p1 :p){
                if(p1==null){
                    count++;
                }
            }
        }
        return count;
    }

    public void printBoard() {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].pieceType.name() + "   ");
                } else {
                    System.out.print("    ");

                }
                if(j+1<size){
                    System.out.print(" | ");
                }

            }
            System.out.println();

        }
    }



}
