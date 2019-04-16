package chess

class Controller {

  var chessboard = new Chessboard()

  def resetChessboard(): Unit ={/*
    chessboard = createInitialChessboard()*/
  }

  def applyMove(move: Move): Unit ={
    chessboard.makeMove(move)
  }

  def applyBestMove(): Unit ={

    val t0 = System.nanoTime()/*
    chessboard.makeMove(Decisions.getBestMove(chessboard))*/
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) / 1000000000.0 + " seconds")
  }

}
