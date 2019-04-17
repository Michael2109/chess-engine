package chess.gui

import java.awt.event.{MouseEvent, MouseListener}
import java.awt.image.BufferedImage
import java.awt.{Color, Graphics}

import chess._
import javax.imageio.ImageIO
import javax.swing.JPanel

import scala.collection.mutable.ListBuffer

class Panel(controller: Controller) extends JPanel with MouseListener {

  val selectedMoves: ListBuffer[Move] = ListBuffer()

  val squareWidth = 80
  val squareHeight = 80


  addMouseListener(this)

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)

    // Draw the squares
/*
    // Draw the pieces
    controller.chessboard.pieces.zipWithIndex.foreach {
      case (pieces, yIndex) => pieces.zipWithIndex.foreach {
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
    */
/*
    // Draw available move
    selectedMoves.foreach {
      case StandardMove(pieceType, startPosition, endPosition) => {
        val startPositionX = startPosition.x * squareWidth
        val startPositionY = startPosition.y * squareHeight

        val endPositionX = endPosition.x * squareWidth
        val endPositionY = endPosition.y * squareHeight
        g.setColor(Color.YELLOW.darker())
        g.fillRect(startPositionX, startPositionY, squareWidth, squareHeight)
        g.setColor(Color.YELLOW)
        g.fillRect(endPositionX, endPositionY, squareWidth, squareHeight)
      }
    }*/

    g.setColor(Color.WHITE)
/*
    // Draw the pieces
    controller.chessboard.pieces.zipWithIndex.foreach {
      case (pieces, yIndex) => pieces.zipWithIndex.foreach {
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
    }*/
  }

  private def isAI(colour: Colour): Boolean = {
    colour match {
      case White => false
      case Black => true
    }
  }
/*

  def getChessPieceImage(piece: Piece): BufferedImage = {
    piece match {
      case Piece(pieceType, colour) =>
        val pathString = s"chesspieces/${colourToString(colour)}${pieceTypeToString(pieceType)}.png"
        ImageIO.read(getClass().getClassLoader().getResource(pathString))
    }
  }

  def colourToString(colour: Colour): String = {
    colour match {
      case White => "w"
      case Black => "b"
    }
  }

  def pieceTypeToString(pieceType: PieceType): String = {
    pieceType match {
      case _: Pawn => "P"
      case _: Rook => "R"
      case _: Bishop => "B"
      case _: Queen => "Q"
      case _: King => "K"
      case _: Knight => "N"
    }
  }
*/

  override def mouseClicked(e: MouseEvent): Unit = ()

  override def mousePressed(e: MouseEvent): Unit = {

    val selectedPosition = Position(e.getX / squareWidth, e.getY / squareHeight)
/*
    if (e.getButton == 1) {
      selectedMoves.clear()
      controller.chessboard.pieceColourAtPosition(selectedPosition) match {
        case Some(colour) => if (colour.equals(controller.chessboard.nextColour)) {
          selectedMoves ++= Rules.possibleMoves(controller.chessboard, selectedPosition)
          repaint()
        }
        case None =>
      }
    } else if (e.getButton == 3) {
      val appliedMove = selectedMoves.find {
        case StandardMove(_, endPosition) => endPosition.equals(selectedPosition)
      }
      appliedMove match {
        case Some(move) => {
          controller.applyMove(move)
          selectedMoves.clear()
        }
        case None =>
      }
      repaint()

      if (isAI(controller.chessboard.nextColour)) {
        controller.applyBestMove()
        repaint()
      }
    }*/
  }

  override def mouseReleased(e: MouseEvent): Unit = ()

  override def mouseEntered(e: MouseEvent): Unit = ()

  override def mouseExited(e: MouseEvent): Unit = ()
}
