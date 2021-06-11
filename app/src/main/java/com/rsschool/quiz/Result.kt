package com.rsschool.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView



class Result : Fragment() {
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


    private var textResult: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val result = arguments?.getInt(RESULT)
        var userAnswerList = arguments?.getIntArray(USER_ANSWER_LIST)

        textResult = view.findViewById(R.id.result)

        textResult?.text = userAnswerList?.let { returnResult(it) }
    }

    fun returnResult(userAnswerList: IntArray):String{
        var result = ""
        var i = 0
        var j = 0
        var trueAnswer = ""
        var userAnswer = ""
        while (i<5){
            when(listQuestion[i].trueAnswer){
                1-> trueAnswer=listQuestion[i].oneAnswer
                2-> trueAnswer=listQuestion[i].twoAnswer
                3-> trueAnswer=listQuestion[i].threeAnswer
                4-> trueAnswer=listQuestion[i].fourAnswer
                5-> trueAnswer=listQuestion[i].fiveAnswer
            }
            when(userAnswerList[i]){
                1-> userAnswer=listQuestion[i].oneAnswer
                2-> userAnswer=listQuestion[i].twoAnswer
                3-> userAnswer=listQuestion[i].threeAnswer
                4-> userAnswer=listQuestion[i].fourAnswer
                5-> userAnswer=listQuestion[i].fiveAnswer
            }
            result += " ${listQuestion[i].question} \n " +
                    "Ваш ответ: $userAnswer\n" +
                    "Правильный ответ: $trueAnswer\n\n"
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
    }



