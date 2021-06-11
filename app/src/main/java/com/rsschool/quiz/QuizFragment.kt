package com.rsschool.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider


class QuizFragment(private var listQuestion: List<Questions>, private var i:Int, var userAnswerList:MutableList<Int>): Fragment() {

    private var optionOne: RadioButton? = null
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





    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        optionOne = view.findViewById(R.id.option_one)
        optionTwo = view.findViewById(R.id.option_two)
        optionThree = view.findViewById(R.id.option_three)
        optionFour = view.findViewById(R.id.option_four)
        optionFive = view.findViewById(R.id.option_five)
        textQuestion = view.findViewById(R.id.question)
        nextButton = view.findViewById(R.id.next_button)
        toolbar = view.findViewById(R.id.toolbar)
        radioGroup = view.findViewById(R.id.radio_group)
        prevButton = view.findViewById(R.id.previous_button)



        optionOne?.text = listQuestion[i].oneAnswer
        optionTwo?.text = listQuestion[i].twoAnswer
        optionThree?.text = listQuestion[i].threeAnswer
        optionFour?.text = listQuestion[i].fourAnswer
        optionFive?.text = listQuestion[i].fiveAnswer
        textQuestion?.text = listQuestion[i].question
        toolbar?.title = "Question ${i+1}"

        if (userAnswerList[i]!=0){
            nextButton?.isEnabled = (true)
        }else{nextButton?.isEnabled = (false)}

        when(userAnswerList[i]){
            1->optionOne?.isChecked = true
            2->optionTwo?.isChecked = true
            3->optionThree?.isChecked = true
            4->optionFour?.isChecked = true
            5->optionFive?.isChecked = true
        }



        list = context as NewOpenQuizFragment



        optionOne?.setOnClickListener {
            nextButton?.isEnabled = (true)
            userAnswerList[i] = 1
            println(userAnswerList)
        }
        optionTwo?.setOnClickListener {
            nextButton?.isEnabled = (true)
            userAnswerList[i] = 2
            println(userAnswerList)
        }
        optionThree?.setOnClickListener {
            nextButton?.isEnabled = (true)
            userAnswerList[i] = 3
            println(userAnswerList)
        }
        optionFour?.setOnClickListener {
            nextButton?.isEnabled = (true)
            userAnswerList[i] = 4
            println(userAnswerList)
        }
        optionFive?.setOnClickListener {
            nextButton?.isEnabled = (true)
            userAnswerList[i] = 5
            println(userAnswerList)
        }

        when(i){
            0 -> prevButton?.isEnabled = false
            in 1..3 -> nextButton?.text="NEXT"
            4 -> nextButton?.text="SUBMIT"
        }


        nextButton?.setOnClickListener {

                if (i in 0..3) {
                    list?.newOpenQuizFragment(listQuestion, (i + 1), userAnswerList)
                }
                if (i == 4) {
                    list?.newOpenResult(listQuestion, (i + 1), userAnswerList)
                }

        }

        prevButton?.setOnClickListener {
            parentFragmentManager.popBackStack()

        }

    }

//    companion object {
//
//        @JvmStatic
//        fun newInstance(): QuizFragment {
//            val fragment = QuizFragment()
//            val args = Bundle()
//            fragment.arguments = args
//            return fragment
//        }
//
//    }

    interface NewOpenQuizFragment{
        fun newOpenQuizFragment(listQuestion:List<Questions>,i:Int,userAnswerList:MutableList<Int>)
        fun newOpenResult(listQuestion:List<Questions>,i:Int,userAnswerList:MutableList<Int>)
    }

}