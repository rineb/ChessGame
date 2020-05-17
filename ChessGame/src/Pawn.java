import java.util.ArrayList;
import java.util.List;

/**
 * This class models the Pawn piece
 */
public class Pawn extends Piece {
    private final String longName = "Pawn";
    private boolean isFirstMove;

    public Pawn() {
        this.isFirstMove = true;
    }

    @Override
    public String getLongName() {
        return longName;
    }

    @Override
    public boolean checkCanMoveTo(int rowInitial, int colInitial, int rowFinal, int colFinal, Player player,
                                  List<Board.PieceInformation> piecesInfo) {
        List<int[]> allAllowedPositions = new ArrayList<>();
        allAllowedPositions.add(new int[] {rowInitial - 1 * player.getNumber(), colInitial});
        if (isFirstMove) {
            allAllowedPositions.add(new int[] {rowInitial - 2 * player.getNumber(), colInitial});
        }

        if (positionIsTaken(rowInitial - 1 * player.getNumber(), colInitial - 1, piecesInfo)) {
            allAllowedPositions.add(new int[] {rowInitial - 1 * player.getNumber(), colInitial - 1});
        }

        if (positionIsTaken(rowInitial - 1 * player.getNumber(), colInitial + 1, piecesInfo)) {
            allAllowedPositions.add(new int[] {rowInitial - 1 * player.getNumber(), colInitial + 1});
        }

        List<int[]> freeAllowedPositions = new ArrayList<>();
        for (int[] allowedPosition : allAllowedPositions) {
            if (!positionIsOurs(allowedPosition[0], allowedPosition[1], player, piecesInfo)) {
                freeAllowedPositions.add(allowedPosition);
            }
        }


        boolean canMove = positionInList(rowFinal, colFinal, freeAllowedPositions);
        if(canMove) {
            isFirstMove = false; //Because will happen
        }

        return canMove;
    }
}
