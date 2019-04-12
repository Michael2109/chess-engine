package chess

object Decisions {

  def getBestMove(chessboard: Chessboard): Move = {


    // Apply the moves and see which filter down to the best moves
    Rules.allPossibleMovesForColour(chessboard, chessboard.nextColour).maxBy(move => {
      val score = minimax(3, Chessboard.makeMove(chessboard, move), Int.MinValue, Int.MaxValue)
      Chessboard.undoMove(chessboard)
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
    chessboard.pieces.flatten.map {
      case Some(piece) => piece.colour match {
        case White => pieceCost(piece.pieceType)
        case Black => -pieceCost(piece.pieceType)
      }
      case None => 0
    }.sum
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
              val value = minimax(depth - 1, Chessboard.makeMove(chessboard, move), min, max)
              Chessboard.undoMove(chessboard)
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
              val value = minimax(depth - 1, Chessboard.makeMove(chessboard, move), min, max)
              Chessboard.undoMove(chessboard)
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
    val whitePieces = Chessboard.getPiecesOfColour(chessboard, White)
    val blackPieces = Chessboard.getPiecesOfColour(chessboard, Black)

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
  }
}
