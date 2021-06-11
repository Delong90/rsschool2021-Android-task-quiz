package com.rsschool.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(), QuizFragment.NewOpenQuizFragment {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listQuestion = listOf(
            Questions("Первый вопрос","Первый вопрос-Первый вариант",
                "Первый вопрос-Второй вариант","Первый вопрос-Третий вариант",
                "Первый вопрос-Четвёртый вариант","Первый вопрос-Пятый вариант",
                1),

            Questions("Второй вопрос","Второй вопрос-Первый вариант",
                "Второй вопрос-Второй вариант","Второй вопрос-Третий вариант",
                "Второй вопрос-Четвёртый вариант","Второй вопрос-Пятый вариант",
                2),

            Questions("Третий вопрос","Третий вопрос-Первый вариант",
                "Третий вопрос-Второй вариант","Третий вопрос-Третий вариант",
                "Третий вопрос-Четвёртый вариант","Третий вопрос-Пятый вариант",
                3),

            Questions("Четвёртый вопрос","Четвёртый вопрос-Первый вариант",
                "Четвёртый вопрос-Второй вариант","Четвёртый вопрос-Третий вариант",
                "Четвёртый вопрос-Четвёртый вариант","Четвёртый вопрос-Пятый вариант",
                4),

            Questions("Пятый вопрос","Пятый вопрос-Первый вариант",
                "Пятый вопрос-Второй вариант","Пятый вопрос-Третий вариант",
                "Пятый вопрос-Четвёртый вариант","Пятый вопрос-Пятый вариант",
                5)
        )
        var i=0
        var userAnswerList:MutableList<Int> = List<Int>(listQuestion.size){0} as MutableList<Int>

        openQuizFragment(listQuestion,i,userAnswerList)

    }

    private fun openQuizFragment(listQuestion:List<Questions>, i:Int,userAnswerList:MutableList<Int>){
        var quizFragment:Fragment = QuizFragment(listQuestion, i,userAnswerList)
        var transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.addToBackStack("").replace(R.id.container, quizFragment).commit()

    }

    private fun openResult(listQuestion:List<Questions>, i:Int,userAnswerList:MutableList<Int>){
        var quizFragment:Fragment = Result(listQuestion, i,userAnswerList)
        var transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.addToBackStack("").replace(R.id.container, quizFragment).commit()

    }

    override fun newOpenQuizFragment(listQuestion:List<Questions>,i:Int,userAnswerList:MutableList<Int>){
        openQuizFragment(listQuestion,i,userAnswerList)
    }
    override fun newOpenResult(listQuestion:List<Questions>,i:Int,userAnswerList:MutableList<Int>){
        openResult(listQuestion,i,userAnswerList)
    }


}