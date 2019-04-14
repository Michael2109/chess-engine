package chess

class Controller {

  var chessboard = createInitialChessboard()

  def resetChessboard(): Unit ={
    chessboard = createInitialChessboard()
  }

  def applyMove(move: Move): Unit ={
    chessboard.makeMove(move)
  }

  def applyBestMove(): Unit ={

    val t0 = System.nanoTime()
    chessboard.makeMove(Decisions.getBestMove(chessboard))
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) / 1000000000.0 + " seconds")
  }

  def createInitialChessboard(): Chessboard = {
    val pieces: Array[Array[Option[Piece]]] = Array(
      Array(
        Option.apply(Piece(Rook(), Black)),
        Option.apply(Piece(Knight(), Black)),
        Option.apply(Piece(Bishop(), Black)),
        Option.apply(Piece(Queen(), Black)),
        Option.apply(Piece(King(), Black)),
        Option.apply(Piece(Bishop(), Black)),
        Option.apply(Piece(Knight(), Black)),
        Option.apply(Piece(Rook(), Black))
      ),
      Array(
        Option.apply(Piece(Pawn(true), Black)),
        Option.apply(Piece(Pawn(true), Black)),
        Option.apply(Piece(Pawn(true), Black)),
        Option.apply(Piece(Pawn(true), Black)),
        Option.apply(Piece(Pawn(true), Black)),
        Option.apply(Piece(Pawn(true), Black)),
        Option.apply(Piece(Pawn(true), Black)),
        Option.apply(Piece(Pawn(true), Black))
      ),
      Array(None, None, None, None, None, None, None, None),
      Array(None, None, None, None, None, None, None, None),
      Array(None, None, None, None, None, None, None, None),
      Array(None, None, None, None, None, None, None, None),
      Array(
        Option.apply(Piece(Pawn(true), White)),
        Option.apply(Piece(Pawn(true), White)),
        Option.apply(Piece(Pawn(true), White)),
        Option.apply(Piece(Pawn(true), White)),
        Option.apply(Piece(Pawn(true), White)),
        Option.apply(Piece(Pawn(true), White)),
        Option.apply(Piece(Pawn(true), White)),
        Option.apply(Piece(Pawn(true), White))
      ),
      Array(
        Option.apply(Piece(Rook(), White)),
        Option.apply(Piece(Knight(), White)),
        Option.apply(Piece(Bishop(), White)),
        Option.apply(Piece(Queen(), White)),
        Option.apply(Piece(King(), White)),
        Option.apply(Piece(Bishop(), White)),
        Option.apply(Piece(Knight(), White)),
        Option.apply(Piece(Rook(), White))
      )
    )
    new Chessboard(pieces, White)
  }

}
