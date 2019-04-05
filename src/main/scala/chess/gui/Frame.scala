package chess.gui

import java.util.Scanner

import chess.{Chessboard, Controller}
import javax.swing.JFrame

class Frame extends JFrame {

  val controller = new Controller()
  var chessboard = controller.createInitialChessboard()

  getContentPane.add(new Panel)
  setSize(700, 700)
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  setVisible(true)

  val scanner: Scanner = new Scanner(System.in)

  while(scanner.hasNext) {
    scanner.nextLine()
    chessboard = controller.applyNextMove(chessboard)
  }
}
