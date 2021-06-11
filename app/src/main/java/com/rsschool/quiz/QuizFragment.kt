package com.rsschool.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuizBinding

class QuizFragment(): Fragment() {
    private val listQuestion = listOf(
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


    private var optionTwo: RadioButton? = null
    private var optionThree: RadioButton? = null
    private var optionFour: RadioButton? = null
    private var optionFive: RadioButton? = null
    private var toolbar:androidx.appcompat.widget.Toolbar? = null
    private var textQuestion:TextView? = null
    private var nextButton:Button? = null
    private var list: NewOpenQuizFragment? = null
    private var radioGroup:RadioGroup? = null
    private var prevButton:Button? = null




    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var numberQuestion = arguments?.getInt(NUMBER_QUESTION)
        var result = arguments?.getInt(RESULT)
        var userAnswerList = arguments?.getIntArray(USER_ANSWER_LIST)



        optionTwo = view.findViewById(R.id.option_two)
        optionThree = view.findViewById(R.id.option_three)
        optionFour = view.findViewById(R.id.option_four)
        optionFive = view.findViewById(R.id.option_five)
        textQuestion = view.findViewById(R.id.question)
        nextButton = view.findViewById(R.id.next_button)
        toolbar = view.findViewById(R.id.toolbar)
        radioGroup = view.findViewById(R.id.radio_group)
        prevButton = view.findViewById(R.id.previous_button)



        binding.optionOne.text = listQuestion[numberQuestion!!].oneAnswer
        optionTwo?.text = listQuestion[numberQuestion].twoAnswer
        optionThree?.text = listQuestion[numberQuestion].threeAnswer
        optionFour?.text = listQuestion[numberQuestion].fourAnswer
        optionFive?.text = listQuestion[numberQuestion].fiveAnswer
        textQuestion?.text = listQuestion[numberQuestion].question
        toolbar?.title = "Question ${numberQuestion+1}"

        if (userAnswerList!![numberQuestion]!=0){
            nextButton?.isEnabled = (true)
        }else{nextButton?.isEnabled = (false)}

        when(userAnswerList[numberQuestion]){
            1->binding.optionOne.isChecked = true
            2->optionTwo?.isChecked = true
            3->optionThree?.isChecked = true
            4->optionFour?.isChecked = true
            5->optionFive?.isChecked = true
        }



        list = context as NewOpenQuizFragment



        binding.optionOne.setOnClickListener {
            nextButton?.isEnabled = (true)
            userAnswerList[numberQuestion] = 1
            println(userAnswerList)
        }
        optionTwo?.setOnClickListener {
            nextButton?.isEnabled = (true)
            userAnswerList[numberQuestion] = 2
            println(userAnswerList)
        }
        optionThree?.setOnClickListener {
            nextButton?.isEnabled = (true)
            userAnswerList[numberQuestion] = 3
            println(userAnswerList)
        }
        optionFour?.setOnClickListener {
            nextButton?.isEnabled = (true)
            userAnswerList[numberQuestion] = 4
            println(userAnswerList)
        }
        optionFive?.setOnClickListener {
            nextButton?.isEnabled = (true)
            userAnswerList[numberQuestion] = 5
            println(userAnswerList)
        }

        when(numberQuestion){
            0 -> prevButton?.isEnabled = false
            in 1..3 -> nextButton?.text="NEXT"
            4 -> nextButton?.text="SUBMIT"
        }


        nextButton?.setOnClickListener {

                if (numberQuestion in 0..3) {
                    if (result != null) {
                        list?.newOpenQuizFragment((numberQuestion + 1), result, userAnswerList)
                    }
                }
                if (numberQuestion == 4) {
                    if (result != null) {
                        for(j in listQuestion){
                            if (j.trueAnswer == userAnswerList[numberQuestion-4]){
                                numberQuestion++
                                result++
                            }
                        }
                        list?.newOpenResult(result)
                    }
                }

        }

        prevButton?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

    }

    companion object {

        @JvmStatic
        fun newInstance(numberQuestion: Int, result:Int, userAnswerList: IntArray): QuizFragment {
            val fragment = QuizFragment()
            val args = Bundle()
            args.putInt(NUMBER_QUESTION, numberQuestion)
            args.putInt(RESULT,result)
            args.putIntArray(USER_ANSWER_LIST, userAnswerList)
            fragment.arguments = args
            return fragment
        }
        private const val NUMBER_QUESTION = "NUMBER_QUESTION"
        private const val RESULT = "RESULT"
        private const val USER_ANSWER_LIST = "USER_ANSWER_LIST"

    }

    interface NewOpenQuizFragment{
        fun newOpenQuizFragment(numberQuestion:Int, result:Int, userAnswerList: IntArray?)
        fun newOpenResult(result:Int)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}