package com.rsschool.quiz

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.rsschool.quiz.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), QuizFragment.NewOpenQuizFragment {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var numberQuestion = 0
        var result = 0
        var userAnswerList = IntArray(5)
        openQuizFragment(numberQuestion,result,userAnswerList)
    }

    private fun openQuizFragment(numberQuestion:Int,result:Int, userAnswerList: IntArray){
        var quizFragment:Fragment = QuizFragment.newInstance(numberQuestion,result,userAnswerList)
        var transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.addToBackStack("").replace(R.id.container, quizFragment).commit()

    }

    private fun openResult(result:Int){
        var quizFragment:Fragment = Result(result)
        var transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.addToBackStack("").replace(R.id.container, quizFragment).commit()

    }

    override fun newOpenQuizFragment(numberQuestion:Int, result:Int, userAnswerList: IntArray?){
        if (userAnswerList != null) {
            openQuizFragment(numberQuestion,result, userAnswerList)
        }
    }
    override fun newOpenResult(result:Int){
        openResult(result)
    }


}