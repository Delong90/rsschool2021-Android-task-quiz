package com.rsschool.quiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentResultBinding


class Result : Fragment() {
    val listQuestion = listQuestionKT
    private var list: NewOpenQuizFragment? = null
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }



    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val result = requireNotNull(arguments?.getInt(RESULT))
        val userAnswerList = requireNotNull(arguments?.getIntArray(USER_ANSWER_LIST))

        binding.result.text = "Результат: $result из 5"

        list = context as NewOpenQuizFragment

        binding.share.setOnClickListener{
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Quiz result\n\n Результат: $result из 5\n\n ${returnResult(userAnswerList)}")
            sendIntent.type = "text/plain"
            startActivity(Intent.createChooser(sendIntent, "Поделиться"))
        }

        binding.repeatQuiz.setOnClickListener{
        list?.newOpenQuizFragment(0,0,IntArray(5))
        }

        binding.exitApp.setOnClickListener{
            list?.onBackPressed()
        }

    }

    fun returnResult(userAnswerList: IntArray):String{
        var result = ""
        var i = 0
        var userAnswer = ""
        while (i<5){
//            when(listQuestion[i].trueAnswer){
//                1-> trueAnswer=listQuestion[i].oneAnswer
//                2-> trueAnswer=listQuestion[i].twoAnswer
//                3-> trueAnswer=listQuestion[i].threeAnswer
//                4-> trueAnswer=listQuestion[i].fourAnswer
//                5-> trueAnswer=listQuestion[i].fiveAnswer
//            }
            when(userAnswerList[i]){
                1-> userAnswer=listQuestion[i].oneAnswer
                2-> userAnswer=listQuestion[i].twoAnswer
                3-> userAnswer=listQuestion[i].threeAnswer
                4-> userAnswer=listQuestion[i].fourAnswer
                5-> userAnswer=listQuestion[i].fiveAnswer
            }
            result += "Вопрос №${listQuestion[i].question} \n " +
                    "Ваш ответ: $userAnswer\n\n"
//                    +"Правильный ответ: $trueAnswer\n\n"
            i++
        }

        return result
    }





    companion object {
        @JvmStatic
        fun newInstance(result: Int,userAnswerList: IntArray):Result {
            val fragment = Result()
            val args = Bundle()
            args.putInt(RESULT,result)
            args.putIntArray(USER_ANSWER_LIST, userAnswerList)
            fragment.arguments = args
            return fragment
        }
        private const val RESULT = "RESULT"
        private const val USER_ANSWER_LIST = "USER_ANSWER_LIST"

            }


    interface NewOpenQuizFragment{
        fun newOpenQuizFragment(numberQuestion:Int, result:Int, userAnswerList: IntArray?)
        fun onBackPressed()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    }



