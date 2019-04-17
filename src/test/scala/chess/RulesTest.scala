package chess

import org.junit.runner.RunWith
import org.scalatest.{FunSpec, Matchers}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class RulesTest extends FunSpec with Matchers {

  describe("Get all possible moves") {
    it("Move white pawn from a -> b") {
      val pieces = LayoutGenerator.emptyChessboard
      pieces(Chessboard.whitePawn) = 1L << 8

      val chessboard = new Chessboard(pieces)

      val endPosition: Short = 24

      println(Chessboard.boardString(pieces(Chessboard.whitePawn)))

      println(Rules.possibleMoves(chessboard).map(move => {
        move match {
          case standardMove: StandardMove => Chessboard.boardString(standardMove.endPosition)
        }
      }).mkString("\n\n"))

      (1L << 0) shouldBe 1
    }
  }
}
