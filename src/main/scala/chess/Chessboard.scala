package chess

import scala.collection.immutable.Stack
import scala.collection.mutable


class Chessboard() {

  var whiteTurn: Boolean = true

  val whitePawn: Byte = 0
  val whiteRook: Byte = 1
  val whiteKnight: Byte = 2
  val whiteBishop: Byte = 3
  val whiteQueen: Byte = 4
  val whiteKing: Byte = 5
  val blackPawn: Byte = 6
  val blackRook: Byte = 7
  val blackKnight: Byte = 8
  val blackBishop: Byte = 9
  val blackQueen: Byte = 10
  val blackKing: Byte = 11

  val pieces: Array[Long] = Array(
    // White Pawn
    pieceAtPosition(8) | pieceAtPosition(9) | pieceAtPosition(10) | pieceAtPosition(11) | pieceAtPosition(12) | pieceAtPosition(13) | pieceAtPosition(14) | pieceAtPosition(15),
    // White Rook
    pieceAtPosition(0) | pieceAtPosition(7),
    // White Knight
    pieceAtPosition(0),
    // White Bishop
    pieceAtPosition(0),
    // White Queen
    pieceAtPosition(0),
    // White King
    pieceAtPosition(0),

    // Black Pawn
    pieceAtPosition(48) | pieceAtPosition(49) | pieceAtPosition(50) | pieceAtPosition(51) | pieceAtPosition(52) | pieceAtPosition(53) | pieceAtPosition(54) | pieceAtPosition(55),
    // Black Rook
    pieceAtPosition(56) | pieceAtPosition(63),
    // Black Knight
    pieceAtPosition(0),
    // Black Bishop
    pieceAtPosition(0),
    // Black Queen
    pieceAtPosition(0),
    // Black King
    pieceAtPosition(0)
  )

/*
  def pieceAtPosition(position: Position): Option[Piece] = {
    pieces(position.y)(position.x)
  }*/
/*
  def pieceColourAtPosition(position: Position): Option[Colour] = {
    pieceAtPosition(position) match {
      case Some(piece) => Some(piece.colour)
      case None => None
    }
  }*/
/*

  val moveHistory: mutable.Stack[Stack[(Position, Option[Piece])]] = mutable.Stack()
*/

  def makeMove(move: Move): Unit = {
/*    move match {
      case StandardMove(startPosition, endPosition) =>

        val pieceToMove = pieceAtPosition(startPosition)

        val updatedPieceToMove = pieceToMove match {
          case Some(piece) => piece match {
            case piece: Piece => piece.pieceType match {
              case _: Pawn => Option.apply(Piece(Pawn(false), piece.colour))
              case _ => Some(piece)
            }
          }
          case None => throw new Exception("No piece found: " + startPosition)
        }

        moveHistory.push(Stack((startPosition, pieceToMove), (endPosition, pieceAtPosition(endPosition))))

        pieces(endPosition.y)(endPosition.x) = updatedPieceToMove
        pieces(startPosition.y)(startPosition.x) = None
    }

    nextColour = Colour.changeColour(nextColour)*/
  }

  def undoMove(): Unit = {
/*
    val lastMoves = moveHistory.pop()
    lastMoves.foreach(move => {
      pieces(move._1.y)(move._1.x) = move._2
    })


    nextColour = Colour.changeColour(nextColour)*/

    whiteTurn = !whiteTurn
  }

  def whitePieces: Long = pieces.take(6).foldLeft(0L)(_ | _)

  def blackPiece: Long = pieces.takeRight(6).foldLeft(0L)(_ | _)

  def pieceAtPosition(position: Short): Long ={
    1 << position
  }

  def movePiece(pieceType: Byte, oldPosition: Short, newPosition: Short): Unit ={
    pieces(pieceType) = pieces(pieceType) & ~pieceAtPosition(oldPosition)
    pieces(pieceType) = pieces(pieceType) | pieceAtPosition(newPosition)
  }

  println(boardString(whitePieces))

  def boardString(pieces: Long): String ={
    val binaryString = pieces.toBinaryString
    val paddingRequired = 64 - binaryString.length
    val binaryStringPadded = List.fill(paddingRequired)("0") ++ binaryString
    binaryStringPadded.grouped(8).mkString(sys.props("line.separator"))
  }

}
