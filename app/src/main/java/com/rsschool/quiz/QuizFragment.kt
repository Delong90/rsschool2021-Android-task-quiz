package com.rsschool.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class QuizFragment(private var listQuestion: List<Questions>, private var i:Int): Fragment() {

    private var optionOne: RadioButton? = null
    private var optionTwo: RadioButton? = null
    private var optionThree: RadioButton? = null
    private var optionFour: RadioButton? = null
    private var optionFive: RadioButton? = null
    private var toolbar:androidx.appcompat.widget.Toolbar? = null
    private var textQuestion:TextView? = null
    private var nextButton:Button? = null
    private var list: NewOpenQuizFragment? = null






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




        optionOne?.text = listQuestion[i].oneAnswer
        optionTwo?.text = listQuestion[i].twoAnswer
        optionThree?.text = listQuestion[i].threeAnswer
        optionFour?.text = listQuestion[i].fourAnswer
        optionFive?.text = listQuestion[i].fiveAnswer
        textQuestion?.text = listQuestion[i].question
        toolbar?.title = "Question ${i+1}"




        list = context as NewOpenQuizFragment

        optionOne?.setOnClickListener{
            nextButton?.isEnabled = (true)
        }
        optionTwo?.setOnClickListener{
            nextButton?.isEnabled = (true)
        }
        optionThree?.setOnClickListener{
            nextButton?.isEnabled = (true)
        }
        optionFour?.setOnClickListener{
            nextButton?.isEnabled = (true)
        }
        optionFive?.setOnClickListener{
            nextButton?.isEnabled = (true)
        }


        nextButton?.setOnClickListener {

            i = (i+1) % listQuestion.size
            list?.newOpenQuizFragment(listQuestion,i)
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
        fun newOpenQuizFragment(listQuestion:List<Questions>,i:Int)
    }
}