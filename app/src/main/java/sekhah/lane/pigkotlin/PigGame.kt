package sekhah.lane.pigkotlin

import kotlin.random.Random

internal class PigGame {

    private val WINNING_SCORE = 20

    var player1Score = 0
    var player2Score = 0
    var turnPoints = 0
    var currentPlayer = 1

    fun rollDie(): Int {
        val roll = Random.nextInt(1, 7)
        if (roll != 1) {
            turnPoints += roll
        } else {
            turnPoints = 0
            changeTurn()
        }
        return roll
    }

    fun changeTurn() {
        if (currentPlayer == 1) {
            player1Score += turnPoints
            currentPlayer = 2
        } else {
            player2Score += turnPoints
            currentPlayer = 1
        }
        turnPoints = 0
    }

    fun checkForWinner(): Int {
        // returns 0 for a tie and -1 if no winner or tie
        var winner = -1
        if (player1Score >= WINNING_SCORE || player2Score >= WINNING_SCORE) {
            if (player2Score > player1Score)
                winner = 2
            else if (player1Score > player2Score && currentPlayer == 1)
                winner = 1
            else if (player1Score == player2Score)
                winner = 0
        }
        return winner
    }
}