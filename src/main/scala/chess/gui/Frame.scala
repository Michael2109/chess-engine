package chess.gui

import java.util.Scanner

import chess.{Chessboard, Controller}
import javax.swing.JFrame

class Frame extends JFrame {

  val controller = new Controller()

  add(new Panel(controller))
  setSize(657, 680)
  setLocationRelativeTo(null)
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  setVisible(false)

  System.exit(0)
}
