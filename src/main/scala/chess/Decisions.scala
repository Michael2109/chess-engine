package chess

object Decisions {

  def getBestMove(chessboard: Chessboard): Move = {


    // Apply the moves and see which filter down to the best moves
    Rules.allPossibleMoves(chessboard).maxBy(move =>
      minimax(4, Chessboard.makeMove(chessboard, move), Int.MinValue, Int.MaxValue)
    )
  }

  def pieceCost(pieceType: PieceType): Int = {
    pieceType match {
      case Rook => 50
      case Knight => 30
      case Bishop => 30
      case Queen => 90
      case King => 900
      case Pawn => 10
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

  def minimax(depth: Int, chessboard: Chessboard, alpha: Int, beta: Int): Int = {
    if (depth == 0) {
      chessboard.nextColour match {
        case White => evaluateChessboard(chessboard)
        case Black => -evaluateChessboard(chessboard)
      }
    } else {
      val possibleNextMoves: Stream[Move] = Rules.allPossibleMoves(chessboard).toStream
      var continue = true
      chessboard.nextColour match {
        case White => {

          possibleNextMoves.map(move => {
            if (continue) {
              val value = minimax(depth - 1, Chessboard.makeMove(chessboard, move), alpha, beta)
              val newAlpha = math.max(alpha, value)
              if (newAlpha >= beta) {
                continue = false
              }
              value
            } else {
              0
            }
          }).max
        }
        case Black =>
          possibleNextMoves.map(move => {
            if (continue) {
              val value = minimax(depth - 1, Chessboard.makeMove(chessboard, move), alpha, beta)
              val newBeta = math.min(beta, value)
              if (alpha >= newBeta) {
                continue = false
              }
              value
            } else {
              0
            }
          }).min
      }
    }
  }

  /**
    *
    * *
    * function alphabeta(node, depth, α, β, maximizingPlayer) is
    * if depth = 0 or node is a terminal node then
    * return the heuristic value of node
    * if maximizingPlayer then
    * value := −∞
    * for each child of node do
    * value := max(value, alphabeta(child, depth − 1, α, β, FALSE))
    * α := max(α, value)
    * if α ≥ β then
    * break (* β cut-off *)
    * return value
    * else
    * value := +∞
    * for each child of node do
    * value := min(value, alphabeta(child, depth − 1, α, β, TRUE))
    * β := min(β, value)
    * if α ≥ β then
    * break (* α cut-off *)
    * return value
    *
    * @param chessboard
    * @return
    */

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
