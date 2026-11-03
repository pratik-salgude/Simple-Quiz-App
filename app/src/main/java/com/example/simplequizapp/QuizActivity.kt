package com.example.simplequizapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private lateinit var tvQuestionNumber: TextView
    private lateinit var tvScore: TextView
    private lateinit var tvQuestion: TextView
    private lateinit var tvFeedback: TextView

    private lateinit var radioGroup: RadioGroup

    private lateinit var option1: RadioButton
    private lateinit var option2: RadioButton
    private lateinit var option3: RadioButton
    private lateinit var option4: RadioButton

    private lateinit var btnNext: Button

    private var currentQuestionIndex = 0

    private var score = 0

    private var answerChecked = false


    private val questions = listOf(

        Question(
            "Which language is officially recommended for modern Android development?",
            "Python",
            "Kotlin",
            "PHP",
            "C",
            2
        ),

        Question(
            "What does CPU stand for?",
            "Central Processing Unit",
            "Computer Personal Unit",
            "Central Program Utility",
            "Control Processing User",
            1
        ),

        Question(
            "Which company developed Android?",
            "Microsoft",
            "Apple",
            "Google",
            "IBM",
            3
        ),

        Question(
            "What is the capital of India?",
            "Mumbai",
            "New Delhi",
            "Kolkata",
            "Chennai",
            2
        ),

        Question(
            "Which keyword is used to create a variable that cannot be reassigned in Kotlin?",
            "var",
            "val",
            "int",
            "let",
            2
        ),

        Question(
            "Which file is commonly used to design Android layouts using Views?",
            "XML",
            "Python",
            "CSV",
            "TXT",
            1
        ),

        Question(
            "Which Android component represents a screen?",
            "Database",
            "Activity",
            "Variable",
            "Compiler",
            2
        ),

        Question(
            "What does RAM stand for?",
            "Random Access Memory",
            "Read Access Machine",
            "Rapid Android Memory",
            "Random Android Machine",
            1
        ),

        Question(
            "Which symbol is commonly used for single-line comments in Kotlin?",
            "#",
            "//",
            "!!",
            "@@",
            2
        ),

        Question(
            "Which method is called when an Android Activity is initially created?",
            "onDelete()",
            "onCreate()",
            "onClose()",
            "onFinish()",
            2
        )
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_quiz)


        tvQuestionNumber = findViewById(R.id.tvQuestionNumber)

        tvScore = findViewById(R.id.tvScore)

        tvQuestion = findViewById(R.id.tvQuestion)

        tvFeedback = findViewById(R.id.tvFeedback)


        radioGroup = findViewById(R.id.radioGroup)


        option1 = findViewById(R.id.radioOption1)

        option2 = findViewById(R.id.radioOption2)

        option3 = findViewById(R.id.radioOption3)

        option4 = findViewById(R.id.radioOption4)


        btnNext = findViewById(R.id.btnNext)


        showQuestion()


        btnNext.setOnClickListener {

            if (!answerChecked) {

                checkAnswer()

            } else {

                moveToNextQuestion()
            }
        }
    }


    private fun showQuestion() {

        val question = questions[currentQuestionIndex]

        tvQuestionNumber.text =
            "Question ${currentQuestionIndex + 1} of ${questions.size}"

        tvScore.text =
            "Score: $score"

        tvQuestion.text =
            question.question


        option1.text = question.option1

        option2.text = question.option2

        option3.text = question.option3

        option4.text = question.option4


        radioGroup.clearCheck()

        tvFeedback.text = ""

        answerChecked = false

        btnNext.text = "SUBMIT"

        enableOptions(true)
    }


    private fun checkAnswer() {

        val selectedId = radioGroup.checkedRadioButtonId


        if (selectedId == -1) {

            Toast.makeText(
                this,
                "Please select an answer",
                Toast.LENGTH_SHORT
            ).show()

            return
        }


        val selectedAnswer = when (selectedId) {

            R.id.radioOption1 -> 1

            R.id.radioOption2 -> 2

            R.id.radioOption3 -> 3

            R.id.radioOption4 -> 4

            else -> 0
        }


        val correctAnswer =
            questions[currentQuestionIndex].correctAnswer


        if (selectedAnswer == correctAnswer) {

            score++

            tvFeedback.text = "Correct Answer!"

            tvFeedback.setTextColor(
                Color.rgb(0, 150, 0)
            )

        } else {

            val correctText =
                getCorrectAnswerText(correctAnswer)

            tvFeedback.text =
                "Wrong! Correct answer: $correctText"

            tvFeedback.setTextColor(
                Color.RED
            )
        }


        tvScore.text = "Score: $score"


        answerChecked = true

        enableOptions(false)


        if (currentQuestionIndex == questions.size - 1) {

            btnNext.text = "VIEW RESULT"

        } else {

            btnNext.text = "NEXT QUESTION"
        }
    }


    private fun moveToNextQuestion() {

        currentQuestionIndex++


        if (currentQuestionIndex < questions.size) {

            showQuestion()

        } else {

            val intent =
                Intent(this, ResultActivity::class.java)

            intent.putExtra("SCORE", score)

            intent.putExtra(
                "TOTAL_QUESTIONS",
                questions.size
            )

            startActivity(intent)

            finish()
        }
    }


    private fun getCorrectAnswerText(
        correctAnswer: Int
    ): String {

        val question =
            questions[currentQuestionIndex]


        return when (correctAnswer) {

            1 -> question.option1

            2 -> question.option2

            3 -> question.option3

            4 -> question.option4

            else -> ""
        }
    }


    private fun enableOptions(enabled: Boolean) {

        option1.isEnabled = enabled

        option2.isEnabled = enabled

        option3.isEnabled = enabled

        option4.isEnabled = enabled
    }
}