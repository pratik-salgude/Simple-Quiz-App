package com.example.simplequizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_result)


        val tvFinalScore =
            findViewById<TextView>(R.id.tvFinalScore)

        val tvPercentage =
            findViewById<TextView>(R.id.tvPercentage)

        val tvResultMessage =
            findViewById<TextView>(R.id.tvResultMessage)

        val btnPlayAgain =
            findViewById<Button>(R.id.btnPlayAgain)

        val btnExit =
            findViewById<Button>(R.id.btnExit)


        val score =
            intent.getIntExtra("SCORE", 0)

        val totalQuestions =
            intent.getIntExtra("TOTAL_QUESTIONS", 10)


        val percentage =
            (score * 100) / totalQuestions


        tvFinalScore.text =
            "Your Score: $score / $totalQuestions"

        tvPercentage.text =
            "Percentage: $percentage%"


        tvResultMessage.text = when {

            percentage >= 80 ->
                "Excellent!"

            percentage >= 60 ->
                "Good Job!"

            percentage >= 40 ->
                "Nice Try!"

            else ->
                "Keep Practicing!"
        }


        btnPlayAgain.setOnClickListener {

            val intent =
                Intent(this, QuizActivity::class.java)

            startActivity(intent)

            finish()
        }


        btnExit.setOnClickListener {

            val intent =
                Intent(this, MainActivity::class.java)

            intent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TOP

            startActivity(intent)

            finish()
        }
    }
}