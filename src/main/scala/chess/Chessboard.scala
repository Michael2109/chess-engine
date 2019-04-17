package chess

import scala.collection.mutable


class Chessboard(piecesInit: Array[Long]) {

  val pieces: Array[Long] = piecesInit

  var whiteTurn: Boolean = true

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


  */

  // (PieceType -> Position)
  val moveHistory: mutable.Stack[List[(Byte, Short)]] = mutable.Stack()

  def makeMove(move: Move): Unit = {
    move match {
      case StandardMove(pieceType, startPosition, endPosition) => {

        val endPieceType = pieceTypeAtPosition(endPosition)
        moveHistory.push(List(
          (pieceType, startPosition),
          (endPieceType, endPosition)
        ))

        movePiece(pieceType, startPosition, endPosition)
      }
    }
    whiteTurn = !whiteTurn
  }

  def undoMove(): Unit = {

    val lastMoves = moveHistory.pop()
    lastMoves.foreach(move => if (move._1 != Chessboard.emptyPiece) {
      (move._1, move._2)
    })

    whiteTurn = !whiteTurn
  }

  def whitePieceBoards: Array[Long] = pieces.take(6)

  def blackPieceBoards: Array[Long] = pieces.splitAt(6)._2

  def allWhitePieces: Long = whitePieceBoards.foldLeft(0L)(_ | _)

  def allBlackPieces: Long = blackPieceBoards.foldLeft(0L)(_ | _)

  def pieceTypeAtPosition(position: Short): Byte = {
    pieces.zipWithIndex.find {
      case (board, pieceType) => (board & 1L << position) != 0
    } match {
      case Some(value) => value._2.toByte
      case None => Chessboard.emptyPiece
    }
  }

  def placePiece(pieceType: Byte, position: Short): Unit = {
    pieces(pieceType) = pieces(pieceType) | 1L << position
  }

  def movePiece(pieceType: Byte, oldPosition: Short, newPosition: Short): Unit = {
    pieces(pieceType) = pieces(pieceType) & ~(1L << oldPosition)
    pieces(pieceType) = pieces(pieceType) | 1L << newPosition
  }
}

object Chessboard {

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
  val emptyPiece: Byte = 12

  def toBinary(number: Long, digits: Int = 8) = String.format("%" + digits + "s", number.toBinaryString).replace(' ', '0')

  def boardString(pieces: Long): String = {
    val binaryString = Chessboard.toBinary(pieces, 64)
    binaryString.grouped(8).map(_.reverse).mkString(sys.props("line.separator"))
  }
}
