package com.rsschool.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuizBinding

class QuizFragment: Fragment() {
    private val listQuestion = listOf(
        Questions("1. Длительная блокировка Main-потока может привести к:","Системной ошибке ANR (Application Not Responding)",
            "IllegalArgumentException","Системной ошибке ANR (Application Not Responding)",
            "Сворачиваю приложения в фон","Закрытие приложения",
            1),

        Questions("2. По-умолчанию Android-приложение запускается:","В отдельном процессе (main process), внутри которого при необходимости мы можем создавать собственные процессы (background)",
            "В отдельном процессе, внутри которого создается главный поток (main thread)","В общем процессе для всех приложений (app process), внутри которого создается главный поток (main thread)",
            "В отдельном потоке (main thread). Отдельных процессов в Android нет.","На background потоке",
            2),

        Questions("3. Отметьте верные утверждения о Fragments:","У одного Activity может быть только один Fragment",
            "Использование Fragments обязательно объявлять в файле манифеста","За добавление, удаление, замену, работу со стеком Fragments, отвечает специальный класс FragmentManager",
            "Начиная с Android 3.0 использование Fragments обязательно. Нельзя отображать UI непосредственно на Activity","Fragment обязательно прописывать в Manifests",
            3),

        Questions("4. Отметьте верные утверждения об Intent?","Объект Intent необходим для запуска Fragment",
            "Activity вызывается только через механизм явного (explicit) Intent","Activity можно вызвать через метод startActivity(...), не используя объект Intent",
            "Объект Intent необходим для запуска Activity","Объект Intent",
            4),

        Questions("5. Необходимо передать имя (String) и возраст (Int) во Fragment при его создании. Какой способ стоит использовать в данной ситуации?","Записать данные в файл, затем считать данные из файла после создания Fragment",
            "Передать данные через конструктор","Создать открытый метод в Activity, который возвращает эти данные, и вызвать метод после создания Fragment",
            "Элементы не передаются во фрагменты","Передать данные через arguments (Bundle)",
            5)
    )


    private var list: NewOpenQuizFragment? = null


    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

// Получение входных данных
        var numberQuestion = arguments?.getInt(NUMBER_QUESTION)
        var result = arguments?.getInt(RESULT)
        var userAnswerList = arguments?.getIntArray(USER_ANSWER_LIST)


// Заполнение фрагмента(номер вопроса, вопрос, варианты ответа)
        binding.optionOne.text = listQuestion[numberQuestion!!].oneAnswer
        binding.optionTwo.text = listQuestion[numberQuestion].twoAnswer
        binding.optionThree.text = listQuestion[numberQuestion].threeAnswer
        binding.optionFour.text = listQuestion[numberQuestion].fourAnswer
        binding.optionFive.text = listQuestion[numberQuestion].fiveAnswer
        binding.question.text = listQuestion[numberQuestion].question
        binding.toolbar.title = "Question ${numberQuestion+1}"

        if (userAnswerList!![numberQuestion]!=0){
            binding.nextButton.isEnabled = (true)
        }else{binding.nextButton.isEnabled = (false)}

        when(userAnswerList[numberQuestion]){
            1->binding.optionOne.isChecked = true
            2->binding.optionTwo.isChecked = true
            3->binding.optionThree.isChecked = true
            4->binding.optionFour.isChecked = true
            5->binding.optionFive.isChecked = true
        }



        list = context as NewOpenQuizFragment



        binding.optionOne.setOnClickListener {
            binding.nextButton.isEnabled = (true)
            userAnswerList[numberQuestion] = 1
            println(userAnswerList)
        }

        binding.optionTwo.setOnClickListener {
            binding.nextButton.isEnabled = (true)
            userAnswerList[numberQuestion] = 2
            println(userAnswerList)
        }

        binding.optionThree.setOnClickListener {
            binding.nextButton.isEnabled = (true)
            userAnswerList[numberQuestion] = 3
            println(userAnswerList)
        }

        binding.optionFour.setOnClickListener {
            binding.nextButton.isEnabled = (true)
            userAnswerList[numberQuestion] = 4
            println(userAnswerList)
        }

        binding.optionFive.setOnClickListener {
            binding.nextButton.isEnabled = (true)
            userAnswerList[numberQuestion] = 5
            println(userAnswerList)
        }

        when(numberQuestion){
            0 -> binding.previousButton.isEnabled = false
            in 1..3 -> binding.nextButton.text="NEXT"
            4 -> binding.nextButton.text="SUBMIT"
        }

        binding.previousButton.setOnClickListener {
            parentFragmentManager.backStackEntryCount
            parentFragmentManager.popBackStack()
        }

        binding.nextButton.setOnClickListener {

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
                        list?.newOpenResult(result,userAnswerList)
                    }
                }

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
        fun newOpenResult(result:Int,userAnswerList: IntArray)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}