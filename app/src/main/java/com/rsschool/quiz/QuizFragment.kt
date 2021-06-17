package com.rsschool.quiz


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuizBinding



class QuizFragment: Fragment() {


    val listQuestion = listQuestionKT
    private var list: NewOpenQuizFragment? = null


    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        var themeResId:Int = resources.getIdentifier("ThemeQuiz", "style", "com.rsschool.quiz.theme")
//         context?.theme?.applyStyle(resources.getIdentifier("ThemeQuiz","style","com.rsschool.quiz"),true)
//        context?.theme?.applyStyle(resources.getIdentifier("ThemeQuizSecond","style","app.src.main.res.values.themes"),true)
        val numberQuestion = requireNotNull(arguments?.getInt(NUMBER_QUESTION))
        setTheme(numberQuestion,inflater)
        setStatusBarColor(numberQuestion)
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

// Получение входных данных

        val numberQuestion = requireNotNull(arguments?.getInt(NUMBER_QUESTION))
        var result = requireNotNull(arguments?.getInt(RESULT))
        val userAnswerList = requireNotNull(arguments?.getIntArray(USER_ANSWER_LIST))


// Заполнение фрагмента(номер вопроса, вопрос, варианты ответа)
        binding.optionOne.text = listQuestion[numberQuestion].oneAnswer
        binding.optionTwo.text = listQuestion[numberQuestion].twoAnswer
        binding.optionThree.text = listQuestion[numberQuestion].threeAnswer
        binding.optionFour.text = listQuestion[numberQuestion].fourAnswer
        binding.optionFive.text = listQuestion[numberQuestion].fiveAnswer
        binding.question.text = listQuestion[numberQuestion].question
        binding.toolbar.title = "Question ${numberQuestion + 1}"


// состояние кнопки next в зависимости от того выбран ли какой то овет
        if (userAnswerList[numberQuestion] != 0) {
            binding.nextButton.isEnabled = (true)
        } else {
            binding.nextButton.isEnabled = (false)
        }
// состояние радио кнопок в зависимости от того выбран ли какой то овет



        when (userAnswerList[numberQuestion]) {
            1 -> binding.optionOne.isChecked = true
            2 -> binding.optionTwo.isChecked = true
            3 -> binding.optionThree.isChecked = true
            4 -> binding.optionFour.isChecked = true
            5 -> binding.optionFive.isChecked = true
        }


        list = context as NewOpenQuizFragment

        binding.optionOne.setOnClickListener {
            binding.nextButton.isEnabled = (true)
            userAnswerList[numberQuestion] = 1
        }

        binding.optionTwo.setOnClickListener {
            binding.nextButton.isEnabled = (true)
            userAnswerList[numberQuestion] = 2
        }

        binding.optionThree.setOnClickListener {
            binding.nextButton.isEnabled = (true)
            userAnswerList[numberQuestion] = 3
        }

        binding.optionFour.setOnClickListener {
            binding.nextButton.isEnabled = (true)
            userAnswerList[numberQuestion] = 4
        }

        binding.optionFive.setOnClickListener {
            binding.nextButton.isEnabled = (true)
            userAnswerList[numberQuestion] = 5
        }


// состояние кнопок NEXT PREV в зависимости от номера вопроса
        when (numberQuestion) {
            0 -> {
                binding.previousButton.isEnabled = false
                binding.toolbar.navigationIcon = null
            }
            in 1..3 -> binding.nextButton.text = "NEXT"
            4 -> binding.nextButton.text = "SUBMIT"
        }

        binding.previousButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.nextButton.setOnClickListener {

            if (numberQuestion in 0..3) {
                list?.newOpenQuizFragment((numberQuestion + 1), result, userAnswerList)
            }
            if (numberQuestion == 4) {
                var k = 0
                for (j in listQuestion) {
                    if (j.trueAnswer == userAnswerList[k]) {
                        result++
                        k++
                    } else k++
                }
                list?.newOpenResult(result, userAnswerList)
            }

        }


    }

    companion object {

        @JvmStatic
        fun newInstance(numberQuestion: Int, result: Int, userAnswerList: IntArray): QuizFragment {
            val fragment = QuizFragment()
            val args = Bundle()
            args.putInt(NUMBER_QUESTION, numberQuestion)
            args.putInt(RESULT, result)
            args.putIntArray(USER_ANSWER_LIST, userAnswerList)
            fragment.arguments = args
            return fragment
        }

        private const val NUMBER_QUESTION = "NUMBER_QUESTION"
        private const val RESULT = "RESULT"
        private const val USER_ANSWER_LIST = "USER_ANSWER_LIST"

    }

    interface NewOpenQuizFragment {
        fun newOpenQuizFragment(numberQuestion: Int, result: Int, userAnswerList: IntArray?)
        fun newOpenResult(result: Int, userAnswerList: IntArray)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setStatusBarColor(numberQuestion: Int) {
        val window = activity?.window
        when (numberQuestion) {
            0 -> window?.statusBarColor =
                ResourcesCompat.getColor(resources, R.color.deep_orange_100_dark, null)
            1 -> window?.statusBarColor =
                ResourcesCompat.getColor(resources, R.color.yellow_100_dark, null)
            2 -> window?.statusBarColor =
                ResourcesCompat.getColor(resources, R.color.cyan_100_dark, null)
            3 -> window?.statusBarColor =
                ResourcesCompat.getColor(resources, R.color.deep_purple_100_dark, null)
            else -> window?.statusBarColor =
                ResourcesCompat.getColor(resources, R.color.light_green_100_dark, null)
        }
    }

    private fun setTheme(numberQuestion: Int, inflater: LayoutInflater,) {
        when (numberQuestion) {
            0 -> inflater.context.setTheme(R.style.ThemeQuizFirst)
            1 -> inflater.context.setTheme(R.style.ThemeQuizSecond)
            2 -> inflater.context.setTheme(R.style.ThemeQuizThree)
            3 -> inflater.context.setTheme(R.style.ThemeQuizFour)
            else -> inflater.context.setTheme(R.style.ThemeQuizFive)
        }
    }
}