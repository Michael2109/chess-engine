package chess

object Decisions {
/*
  val MoveDepth: Int = 3

  def getBestMove(chessboard: Chessboard): Move = {


    // Apply the move and see which filter down to the best move
    Rules.allPossibleMovesForColour(chessboard, chessboard.nextColour).maxBy(move => {
      chessboard.makeMove(move)
      val score = minimax(MoveDepth, chessboard, Int.MinValue, Int.MaxValue)
      chessboard.undoMove()
      score
    })
  }

  def pieceCost(pieceType: PieceType): Int = {
    pieceType match {
      case _: Rook => 50
      case _: Knight => 30
      case _: Bishop => 30
      case _: Queen => 90
      case _: King => 900
      case _: Pawn => 10
    }
  }

  def evaluateChessboard(chessboard: Chessboard): Int = {
    chessboard.getPieces().map(piece => piece.colour match {
        case White => pieceCost(piece.pieceType)
        case Black => -pieceCost(piece.pieceType)
    }).sum
  }

  def minimax(depth: Int, chessboard: Chessboard, min: Int, max: Int): Int = {
    if (depth == 0) {
      chessboard.nextColour match {
        case White => evaluateChessboard(chessboard)
        case Black => -evaluateChessboard(chessboard)
      }
    } else {

      val possibleNextMoves: Array[Move] = Rules.allPossibleMoves(chessboard)
      var continue = true
      chessboard.nextColour match {
        case White =>
          var v = min
          possibleNextMoves.map(move => {
            if (continue) {
              chessboard.makeMove(move)
              val value = minimax(depth - 1, chessboard, min, max)
              chessboard.undoMove()
              if (value > v) {
                v = value
              }
              if (v > max) {
                continue = false
              }
              value
            } else {
              Int.MinValue
            }
          }).max
        case Black =>
          var v = max
          possibleNextMoves.map(move => {
            if (continue) {
              chessboard.makeMove(move)
              val value = minimax(depth - 1, chessboard, min, max)
              chessboard.undoMove()
              if (value < v) {
                v = value
              }
              if (v < min) {
                continue = false
              }
              value
            } else {
              Int.MaxValue
            }
          }).min
      }
    }
  }

  def checkmate(chessboard: Chessboard): Option[Colour] = {
    val whitePieces = chessboard.getPiecesOfColour(White)
    val blackPieces = chessboard.getPiecesOfColour(Black)

    if (!whitePieces.exists(_.pieceType == King)) {
      Option.apply(Black)
    } else if (!blackPieces.exists(_.pieceType == King)) {
      Option.apply(White)
    } else {
      None
    }
  }

  def stalemate(chessboard: Chessboard): Boolean = {
    false
  }*/
}
