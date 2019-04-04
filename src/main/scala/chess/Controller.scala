package chess

class Controller {

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
      List(),
      List(),
      List(),
      List(),
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
