package chess.gui

import java.awt.{Color, Graphics}
import java.awt.event.{MouseEvent, MouseListener}
import java.awt.geom.Rectangle2D
import java.awt.image.BufferedImage
import java.nio.file.Paths
import java.util.Scanner

import chess._
import javax.imageio.ImageIO
import javax.swing.JPanel

class Panel(controller: Controller) extends JPanel with MouseListener {

  var selectedMoves: List[Move] = List()

  val squareWidth = 80
  val squareHeight = 80

  addMouseListener(this)

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)

    // Draw the squares

    // Draw the pieces
    controller.chessboard.pieces.zipWithIndex.foreach {
      case(pieces, yIndex) => pieces.zipWithIndex.foreach {
        case (piece, xIndex) => {

          val xPos = xIndex * squareWidth
          val yPos = yIndex * squareHeight

          val colour = {
            if ((xIndex % 2 == 0 && yIndex % 2 == 0) || (xIndex % 2 == 1 && yIndex % 2 == 1)) {
              Color.GRAY
            } else {
              Color.WHITE
            }
          }
          g.setColor(colour)
          g.fillRect(xPos, yPos, squareWidth, squareHeight)
        }
      }
    }

    // Draw available moves
    selectedMoves.foreach {
      case StandardMove(startPosition, endPosition) => {
        val startPositionX = startPosition.x * squareWidth
        val startPositionY = startPosition.y * squareHeight

        val endPositionX = endPosition.x * squareWidth
        val endPositionY = endPosition.y * squareHeight
        g.setColor(Color.YELLOW)
        g.fillRect(startPositionX, startPositionY, squareWidth, squareHeight)
        g.fillRect(endPositionX, endPositionY, squareWidth, squareHeight)
      }
    }

    g.setColor(Color.WHITE)

    // Draw the pieces
    controller.chessboard.pieces.zipWithIndex.foreach {
      case(pieces, yIndex) => pieces.zipWithIndex.foreach {
        case (piece, xIndex) => {

          val xPos = xIndex * squareWidth
          val yPos = yIndex * squareHeight

          piece match {
            case Some(value) =>
              val image = getChessPieceImage(value)
              g.drawImage(image, xPos, yPos, this)
            case None =>
          }
        }
      }
    }
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

  override def mouseClicked(e: MouseEvent): Unit = ()

  override def mousePressed(e: MouseEvent): Unit = {

    val selectedPosition = Position(e.getX / squareWidth, e.getY / squareHeight)
    selectedMoves = Rules.possibleMoves(controller.chessboard, selectedPosition)
    repaint()
  }

  override def mouseReleased(e: MouseEvent): Unit = ()

  override def mouseEntered(e: MouseEvent): Unit = ()

  override def mouseExited(e: MouseEvent): Unit = ()
}
