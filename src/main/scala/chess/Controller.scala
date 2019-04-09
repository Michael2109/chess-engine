package chess

class Controller {

  var chessboard = createInitialChessboard()

  def resetChessboard(): Unit ={
    chessboard = createInitialChessboard()
  }

  def applyMove(move: Move): Unit ={
    chessboard = Chessboard.makeMove(chessboard, move)
  }

  def applyBestMove(): Unit ={
    chessboard = Chessboard.makeMove(chessboard, Decisions.getBestMove(chessboard))
  }

  def createInitialChessboard(): Chessboard = {
    val pieces: List[List[Option[Piece]]] = List(
      List(
        Option.apply(Piece(Rook, Black)),
        Option.apply(Piece(Knight, Black)),
        Option.apply(Piece(Bishop, Black)),
        Option.apply(Piece(Queen, Black)),
        Option.apply(Piece(King, Black)),
        Option.apply(Piece(Bishop, Black)),
        Option.apply(Piece(Knight, Black)),
        Option.apply(Piece(Rook, Black))
      ),
      List(
        Option.apply(Piece(Pawn, Black)),
        Option.apply(Piece(Pawn, Black)),
        Option.apply(Piece(Pawn, Black)),
        Option.apply(Piece(Pawn, Black)),
        Option.apply(Piece(Pawn, Black)),
        Option.apply(Piece(Pawn, Black)),
        Option.apply(Piece(Pawn, Black)),
        Option.apply(Piece(Pawn, Black))
      ),
      List(None, None, None, None, None, None, None, None),
      List(None, None, None, None, None, None, None, None),
      List(None, None, None, None, None, None, None, None),
      List(None, None, None, None, None, None, None, None),
      List(
        Option.apply(Piece(Pawn, White)),
        Option.apply(Piece(Pawn, White)),
        Option.apply(Piece(Pawn, White)),
        Option.apply(Piece(Pawn, White)),
        Option.apply(Piece(Pawn, White)),
        Option.apply(Piece(Pawn, White)),
        Option.apply(Piece(Pawn, White)),
        Option.apply(Piece(Pawn, White))
      ),
      List(
        Option.apply(Piece(Rook, White)),
        Option.apply(Piece(Knight, White)),
        Option.apply(Piece(Bishop, White)),
        Option.apply(Piece(Queen, White)),
        Option.apply(Piece(King, White)),
        Option.apply(Piece(Bishop, White)),
        Option.apply(Piece(Knight, White)),
        Option.apply(Piece(Rook, White))
      )
    )
    new Chessboard(pieces, White)
  }

}
