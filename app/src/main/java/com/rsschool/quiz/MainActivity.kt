package com.rsschool.quiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.rsschool.quiz.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity(), QuizFragment.NewOpenQuizFragment,Result.NewOpenQuizFragment {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setTheme(R.style.ThemeQuizSecond)
        openQuizFragment(0,0,IntArray(5))

    }

    private fun openQuizFragment(numberQuestion:Int,result:Int, userAnswerList: IntArray){
        var quizFragment:Fragment = QuizFragment.newInstance(numberQuestion,result,userAnswerList)
        var transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

        transaction.addToBackStack("$numberQuestion").replace(R.id.container, quizFragment).commit()


    }


    private fun openResult(result:Int,userAnswerList: IntArray){
        var quizFragment:Fragment = Result.newInstance(result,userAnswerList)
        var transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        transaction.replace(R.id.container, quizFragment).commit()

    }

    override fun newOpenQuizFragment(numberQuestion:Int, result:Int, userAnswerList: IntArray?){
        if (userAnswerList != null) {
            openQuizFragment(numberQuestion,result, userAnswerList)
        }
    }
    override fun newOpenResult(result:Int,userAnswerList: IntArray){
        openResult(result,userAnswerList)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1){
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)}
        super.onBackPressed()
    }
}