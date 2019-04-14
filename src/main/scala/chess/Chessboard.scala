package chess

import scala.collection.immutable.Stack
import scala.collection.mutable

class Chessboard(piecesInit: Array[Array[Option[Piece]]], nextColourInit: Colour) {

  val pieces: Array[Array[Option[Piece]]] = piecesInit
  var nextColour: Colour = nextColourInit

  def pieceAtPosition(position: Position): Option[Piece] = {
    pieces(position.y)(position.x)
  }

  def pieceColourAtPosition(position: Position): Option[Colour] = {
    pieceAtPosition(position) match {
      case Some(piece) => Some(piece.colour)
      case None => None
    }
  }

  val moveHistory: mutable.Stack[Stack[(Position, Option[Piece])]] = mutable.Stack()

  def makeMove(move: Move): Unit = {
    move match {
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

    nextColour = Colour.changeColour(nextColour)
  }

  def undoMove(): Unit = {

    val lastMoves = moveHistory.pop()
    lastMoves.foreach(move => {
      pieces(move._1.y)(move._1.x) = move._2
    })


    nextColour = Colour.changeColour(nextColour)

  }

  def getPieces(): Array[Piece] = {
    pieces.flatten.filter(_.isDefined).map(_.get)
  }

  def getPiecesOfColour(colour: Colour): Array[Piece] = {
    getPieces().filter(_.colour.equals(colour))
  }
}
