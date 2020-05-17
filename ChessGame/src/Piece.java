import java.util.List;

/**
 * This is an abstract class that models the piece and its abilities
 */
public abstract class Piece {
    public abstract String getLongName();

    /**
     * This method performs often required checks to see whether a piece can move from the initial position
     * to the desired one for a player, taking into account all the existing pieces in the board.
     * First all the allowed moves for the piece are added to a list, then this list is filtered based on the position
     * of the other pieces and lastly the desired position is checked to see if it's on the list.
     * @param rowInitial The row in which the piece is in momentarily
     * @param colInitial The column in which the piece is in momentarily
     * @param rowFinal The desired destination row of the piece
     * @param colFinal The desired destination column of the piece
     * @param player The player who wants to make a move
     * @param piecesInfo The list that contains the state of the board
     * @return true or false, whether the move can be made
     */
    public abstract boolean checkCanMoveTo(int rowInitial, int colInitial, int rowFinal, int colFinal, Player player,
                                           List<Board.PieceInformation> piecesInfo);

    boolean positionInList(int row, int col, List<int[]> allPiecesInfo) {
        for (int[] pieceInfo : allPiecesInfo) {
            if (pieceInfo[0] == row && pieceInfo[1] == col) {
                return true;
            }
        }
        return false;
    }

    boolean positionIsOurs(int row, int col, Player player, List<Board.PieceInformation> allPiecesInfo) {
        for (Board.PieceInformation pieceInfo : allPiecesInfo) {
            if (pieceInfo.row == row && pieceInfo.column == col && pieceInfo.player == player) {
                return true;
            }
        }
        return false;
    }

    boolean positionIsTaken(int row, int col, List<Board.PieceInformation> allPiecesInfo) {
        for (Board.PieceInformation pieceInfo : allPiecesInfo) {
            if (pieceInfo.row == row && pieceInfo.column == col) {
                return true;
            }
        }
        return false;
    }
}
