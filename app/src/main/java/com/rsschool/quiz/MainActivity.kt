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
                "Первый вопрос-Первый вариант"),
            Questions("Второй вопрос","Второй вопрос-Первый вариант",
                "Второй вопрос-Второй вариант","Второй вопрос-Третий вариант",
                "Второй вопрос-Четвёртый вариант","Второй вопрос-Пятый вариант",
                "Второй вопрос-Второй вариант"),
            Questions("Третий вопрос","Третий вопрос-Первый вариант",
                "Третий вопрос-Второй вариант","Третий вопрос-Третий вариант",
                "Третий вопрос-Четвёртый вариант","Третий вопрос-Пятый вариант",
                "Третий вопрос-Третий вариант"),
            Questions("Четвёртый вопрос","Четвёртый вопрос-Первый вариант",
                "Четвёртый вопрос-Второй вариант","Четвёртый вопрос-Третий вариант",
                "Четвёртый вопрос-Четвёртый вариант","Четвёртый вопрос-Пятый вариант",
                "Четвёртый вопрос-Четвёртый вариант"),
            Questions("Пятый вопрос","Пятый вопрос-Первый вариант",
                "Пятый вопрос-Второй вариант","Пятый вопрос-Третий вариант",
                "Пятый вопрос-Четвёртый вариант","Пятый вопрос-Пятый вариант",
                "Пятый вопрос-Пятый вариант")
        )
        var i=0

        openQuizFragment(listQuestion,i)

    }

    private fun openQuizFragment(listQuestion:List<Questions>, i:Int){
        var quizFragment:Fragment = QuizFragment(listQuestion, i)
        var transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, quizFragment).commit()

    }


    override fun newOpenQuizFragment(listQuestion:List<Questions>,i:Int){
        openQuizFragment(listQuestion,i)
    }



}