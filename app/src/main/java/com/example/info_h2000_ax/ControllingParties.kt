package com.example.info_h2000_ax

import android.app.AlertDialog
import android.content.*

class ControllingParties {
    var round = 0
    var Player1Movements = 0
    var Player2Movements = 0

    fun changeRound() {
        round = round + 1
    }

    fun changePlayer1Mov(Count: Int) {
        Player1Movements = Count
    }

    fun changePlayer2Mov(Count: Int) {
        Player2Movements = Count
    }

    fun broadcastWinner():String {
        /*if (Player1Movements < Player2Movements) {
            println("Player 1 Won")
        }else if (Player1Movements > Player2Movements) {
            println("Player 2 Won")
        }else{
            println("It's a draw")
        }*/

        /*val winner = when {
            Player1Movements < Player2Movements -> "Player 1 Won"
            Player1Movements > Player2Movements -> "Player 2 Won"
            else -> "It's a draw"
        }
        Toast.makeText(context, winner, Toast.LENGTH_LONG).show()*/

        val winner = if (Player1Movements < Player2Movements) {
            "Player 1 is the winner"
        } else if (Player1Movements > Player2Movements) {
            "Player 2 is the winner"
        } else {
            "It's a draw"
        }

        return winner




    }

}