package chess

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class ChessboardTest extends FunSpec with Matchers {

  describe("Move piece from start to end position") {
    it("Move white pawn from a -> b") {
      val pieces = LayoutGenerator.emptyChessboard
      pieces(Chessboard.whitePawn) = 1L << 8

      val chessboard = new Chessboard(pieces)

      val startPosition: Short = 8
      val endPosition: Short = 24

      val move = StandardMove(Chessboard.whitePawn, startPosition, endPosition)

      chessboard.makeMove(move)

      chessboard.pieces(Chessboard.whitePawn) >> 1 & 1 shouldBe 0
      chessboard.pieces(Chessboard.whitePawn) >> endPosition & 1 shouldBe 1

      (1L << 0) shouldBe 1
    }
    it("Move white knight from a -> b") {
      val pieces = LayoutGenerator.emptyChessboard
      pieces(Chessboard.whitePawn) = 1L << 8

      val chessboard = new Chessboard(pieces)

      val startPosition: Short = 19
      val endPosition: Short = 60

      val move = StandardMove(Chessboard.whiteKnight, startPosition, endPosition)

      chessboard.makeMove(move)

      chessboard.pieces(Chessboard.whiteKnight) >> 1 & 1 shouldBe 0
      chessboard.pieces(Chessboard.whiteKnight) >> endPosition & 1 shouldBe 1

      (1L << 0) shouldBe 1
    }

    it("Move white bishop from a -> b") {
      val pieces = LayoutGenerator.emptyChessboard
      pieces(Chessboard.whiteBishop) = 1L << 8

      val chessboard = new Chessboard(pieces)

      val startPosition: Short = 19
      val endPosition: Short = 60

      val move = StandardMove(Chessboard.whiteBishop, startPosition, endPosition)

      chessboard.makeMove(move)

      chessboard.pieces(Chessboard.whiteBishop) >> 1 & 1 shouldBe 0
      chessboard.pieces(Chessboard.whiteBishop) >> endPosition & 1 shouldBe 1

      (1L << 0) shouldBe 1
    }

    it("Move white queen from a -> b") {
      val pieces = LayoutGenerator.emptyChessboard
      pieces(Chessboard.whiteQueen) = 1L << 8

      val chessboard = new Chessboard(pieces)

      val startPosition: Short = 19
      val endPosition: Short = 60

      val move = StandardMove(Chessboard.whiteQueen, startPosition, endPosition)

      chessboard.makeMove(move)

      chessboard.pieces(Chessboard.whiteQueen) >> 1 & 1 shouldBe 0
      chessboard.pieces(Chessboard.whiteQueen) >> endPosition & 1 shouldBe 1

      (1L << 0) shouldBe 1
    }

    it("Move white king from a -> b") {
      val pieces = LayoutGenerator.emptyChessboard
      pieces(Chessboard.whiteKing) = 1L << 8

      val chessboard = new Chessboard(pieces)

      val startPosition: Short = 19
      val endPosition: Short = 60

      val move = StandardMove(Chessboard.whiteKing, startPosition, endPosition)

      chessboard.makeMove(move)

      chessboard.pieces(Chessboard.whiteKing) >> 1 & 1 shouldBe 0
      chessboard.pieces(Chessboard.whiteKing) >> endPosition & 1 shouldBe 1

      (1L << 0) shouldBe 1
    }


    it("Move black pawn from a -> b") {
      val pieces = LayoutGenerator.emptyChessboard
      pieces(Chessboard.blackPawn) = 1L << 8

      val chessboard = new Chessboard(pieces)

      val startPosition: Short = 8
      val endPosition: Short = 24

      val move = StandardMove(Chessboard.blackPawn, startPosition, endPosition)

      chessboard.makeMove(move)

      chessboard.pieces(Chessboard.blackPawn) >> 1 & 1 shouldBe 0
      chessboard.pieces(Chessboard.blackPawn) >> endPosition & 1 shouldBe 1

      (1L << 0) shouldBe 1
    }
    it("Move black knight from a -> b") {
      val pieces = LayoutGenerator.emptyChessboard
      pieces(Chessboard.blackPawn) = 1L << 8

      val chessboard = new Chessboard(pieces)

      val startPosition: Short = 19
      val endPosition: Short = 60

      val move = StandardMove(Chessboard.blackKnight, startPosition, endPosition)

      chessboard.makeMove(move)

      chessboard.pieces(Chessboard.blackKnight) >> 1 & 1 shouldBe 0
      chessboard.pieces(Chessboard.blackKnight) >> endPosition & 1 shouldBe 1

      (1L << 0) shouldBe 1
    }

    it("Move black bishop from a -> b") {
      val pieces = LayoutGenerator.emptyChessboard
      pieces(Chessboard.blackBishop) = 1L << 8

      val chessboard = new Chessboard(pieces)

      val startPosition: Short = 19
      val endPosition: Short = 60

      val move = StandardMove(Chessboard.blackBishop, startPosition, endPosition)

      chessboard.makeMove(move)

      chessboard.pieces(Chessboard.blackBishop) >> 1 & 1 shouldBe 0
      chessboard.pieces(Chessboard.blackBishop) >> endPosition & 1 shouldBe 1

      (1L << 0) shouldBe 1
    }

    it("Move black queen from a -> b") {
      val pieces = LayoutGenerator.emptyChessboard
      pieces(Chessboard.blackQueen) = 1L << 8

      val chessboard = new Chessboard(pieces)

      val startPosition: Short = 19
      val endPosition: Short = 60

      val move = StandardMove(Chessboard.blackQueen, startPosition, endPosition)

      chessboard.makeMove(move)

      chessboard.pieces(Chessboard.blackQueen) >> 1 & 1 shouldBe 0
      chessboard.pieces(Chessboard.blackQueen) >> endPosition & 1 shouldBe 1

      (1L << 0) shouldBe 1
    }

    it("Move black king from a -> b") {
      val pieces = LayoutGenerator.emptyChessboard
      pieces(Chessboard.blackKing) = 1L << 8

      val chessboard = new Chessboard(pieces)

      val startPosition: Short = 19
      val endPosition: Short = 60

      val move = StandardMove(Chessboard.blackKing, startPosition, endPosition)

      chessboard.makeMove(move)

      chessboard.pieces(Chessboard.blackKing) >> 1 & 1 shouldBe 0
      chessboard.pieces(Chessboard.blackKing) >> endPosition & 1 shouldBe 1

      (1L << 0) shouldBe 1
    }
  }
}
