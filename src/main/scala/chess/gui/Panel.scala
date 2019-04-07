package chess.gui

import java.awt.Graphics
import java.awt.image.BufferedImage
import java.nio.file.Paths
import java.util.Scanner

import chess._
import javax.imageio.ImageIO
import javax.swing.JPanel

class Panel(controller: Controller) extends JPanel{



  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)

    // Draw the squares

    // Draw the pieces
    controller.chessboard.pieces
    g.drawImage(image, 0, 0, this)
  }

  controller.applyNextMove()

  def getChessPieceImage(piece: Piece): BufferedImage ={
    piece match {
      case Piece (pieceType, colour) =>
        val pathString = s"chesspieces/${colourToString(colour)}${pieceTypeToString(pieceType)}.png"
        ImageIO.read(getClass().getClassLoader().getResource(pathString))
    }
  }

  def colourToString(colour: Colour): String ={
    colour match {
      case White => "w"
      case Black => "b"
    }
  }

  def pieceTypeToString(pieceType: PieceType): String ={
    pieceType match {
      case Pawn => "P"
      case Rook => "R"
      case Bishop => "B"
      case Queen => "Q"
      case King => "K"
      case Knight => "N"
    }
  }

}
