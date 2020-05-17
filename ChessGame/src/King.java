import java.util.ArrayList;
import java.util.List;

/**
 * This class models the King piece
 */
public class King extends Piece {
    private final String longName = "King";

    @Override
    public String getLongName() {
        return longName;
    }

    @Override
    public boolean checkCanMoveTo(int rowInitial, int colInitial, int rowFinal, int colFinal, Player player,
                                  List<Board.PieceInformation> piecesInfo) {

        int left = colInitial - 1;
        int right = colInitial + 1;
        int top = rowInitial - 1;
        int bottom = rowInitial + 1;

        if (left < 0) {
            left = 0;
        }
        if (right >= 8) {
            right = 7;
        }
        if (top < 0) {
            top = 0;
        }
        if (bottom >= 8) {
            bottom = 7;
        }

        List<int[]> allAllowedPositions = new ArrayList<>();

        for (int row = top; row <= bottom; row++) {
            for (int col = left; col <= right; col++) {
                allAllowedPositions.add(new int[] {row, col});
            }
        }

        List<int[]> freeAllowedPositions = new ArrayList<>();
        for (int[] allowedPosition : allAllowedPositions) {
            if (!positionIsOurs(allowedPosition[0], allowedPosition[1], player, piecesInfo)) {
                freeAllowedPositions.add(allowedPosition);
            }
        }

        boolean canMove = positionInList(rowFinal, colFinal, freeAllowedPositions);

        return canMove;
    }
}
