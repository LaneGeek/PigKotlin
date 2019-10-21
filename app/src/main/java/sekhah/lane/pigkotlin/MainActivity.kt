package sekhah.lane.pigkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var pigGame = PigGame()
    private var die = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rollDieButton.setOnClickListener {
            die = pigGame.rollDie()
            if (die == 1) {
                rollDieButton.isEnabled = false
                turnButton.text = "End Turn"
                pigGame.changeTurn()
            }
            updateScreen()
        }

        turnButton.setOnClickListener {
            if (turnButton.text == "Start Turn") {
                turnButton.text = "End Turn"
                rollDieButton.isEnabled = true
                updateScreen()
            } else {
                turnButton.text = "Start Turn"
                rollDieButton.isEnabled = false
                pigGame.changeTurn()
                die = 0
                updateScreen()
            }
        }

        newGameButton.setOnClickListener {
            pigGame = PigGame()
            die = 0
            rollDieButton.isEnabled = false
            turnButton.text = "Start Turn"
            turnButton.isEnabled = true
            updateScreen()
        }
    }

    private fun updateScreen() {
        if (pigGame.currentPlayer == 1)
            nextTurnTextView.text = """${player1NameEditText.text}'s Turn"""
        else
            nextTurnTextView.text = """${player2NameEditText.text}'s Turn"""
        player1ScoreTextView.text = pigGame.player1Score.toString()
        player2ScoreTextView.text = pigGame.player2Score.toString()
        turnPointsTextView.text = pigGame.turnPoints.toString()

        if (pigGame.checkForWinner() != -1) {
            when (pigGame.checkForWinner()) {
                0 -> nextTurnTextView.text = "It is a tie!"
                1 -> nextTurnTextView.text = """${player1NameEditText.text} Wins!"""
                2 -> nextTurnTextView.text = """${player2NameEditText.text} Wins!"""
            }
            rollDieButton.isEnabled = false
            turnButton.isEnabled = false
        }

        when (die) {
            1 -> dieImageView.setImageResource(R.drawable.die1)
            2 -> dieImageView.setImageResource(R.drawable.die2)
            3 -> dieImageView.setImageResource(R.drawable.die3)
            4 -> dieImageView.setImageResource(R.drawable.die4)
            5 -> dieImageView.setImageResource(R.drawable.die5)
            6 -> dieImageView.setImageResource(R.drawable.die6)
            else -> dieImageView.setImageResource(R.drawable.pig)
        }
    }
}
