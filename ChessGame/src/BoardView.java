import javax.swing.*;
import java.awt.*;

/**
 * This class is the View of this application
 */
public class BoardView extends JPanel {
    private int squareSize;
    private Board board;
    private JFrame boardFrame;

    /**
     * The constructor for the BoardView class
     */
    public BoardView() {
        squareSize = 120;
        board = null;
        int titleOffset = 35;
        int boardWidth = squareSize * 8;
        int boardHeight = boardWidth + titleOffset;
        boardFrame = new JFrame();

        boardFrame.getContentPane().add(this);
        boardFrame.setTitle("Chess Game");
        boardFrame.setSize(boardWidth, boardHeight);
        boardFrame.setResizable(false);
        boardFrame.setVisible(true);
        boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * This method draws the empty board, its pieces' names and the player's name on each piece
     * @param g The pen that is used to draw with
     */
    public void paintComponent(Graphics g) {
        drawEmptyBoard(g);
        drawBoardPieces(g);
    }

    /**
     * This method is used to draw an empty board and a unique number for every field in the board
     * @param g The pen that is used to draw with
     */
    private void drawEmptyBoard(Graphics g) {
        g.setFont(new Font("SansSerif", Font.BOLD, 15));

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if ((row + col) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(col * squareSize, row * squareSize, squareSize, squareSize);
                g.setColor(Color.GRAY);
                g.drawString("" + row + col, col * squareSize + 5,
                        row * squareSize + 15);
            }
        }
    }

    /**
     * This method is used to draw the names of the players and their pieces' names
     * @param g The pen that is used to draw with
     */
    private void drawBoardPieces(Graphics g) {
        if (this.board == null) {
            return;
        }

        for (Board.PieceInformation pieceInfo : board.getPiecesInfo()) {
            Player player = pieceInfo.player;
            g.setColor(Color.RED);
            g.setFont(new Font("SansSerif", Font.BOLD, 20));
            g.drawString(pieceInfo.piece.getLongName(), pieceInfo.column * squareSize + 20,
                    pieceInfo.row * squareSize + 60);
            g.setColor(Color.ORANGE);
            g.setFont(new Font("SansSerif", Font.BOLD, 15));
            g.drawString(player.getName(), pieceInfo.column * squareSize + 20,
                    pieceInfo.row * squareSize + 80);
        }
    }

    /**
     * This method reads the player's name as input
     * @param whichPlayer Indicates which player we're asking
     * @return A string that will hold the name of the player
     */
    public String readPlayerName(String whichPlayer) {
        String input = JOptionPane.showInputDialog(
                this, "Type name for player " + whichPlayer);
        boardFrame.repaint();
        return input;
    }

    /**
     * This method reads the player's desired next move as input and splits that input into individual numbers,
     * with each number then becoming an element of an array
     * @param player The player whose move is being read
     * @param errorMessage A message that will appear if the input is not correct
     * @return An array that will hold 4 elements that specify the initial row, intial column, final row, final column
     * taken from the player's input
     */
    public int[] readMove(Player player, String errorMessage) {
        try {
            String input = JOptionPane.showInputDialog(errorMessage + "\nWrite move for Player "
                    + player.getName() + "\n(Ex. '11 21' moves the piece from 11 to 21)").trim();

            boardFrame.repaint();

            int[] numbers = new int[4];
            numbers[0] = Integer.parseInt(input.substring(0, 1));
            numbers[1] = Integer.parseInt(input.substring(1, 2));
            numbers[2] = Integer.parseInt(input.substring(3, 4));
            numbers[3] = Integer.parseInt(input.substring(4, 5));

            return numbers;
        } catch (NumberFormatException ex) {
            return readMove(player, errorMessage);
        }
    }
}