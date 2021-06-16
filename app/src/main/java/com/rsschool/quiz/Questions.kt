package com.rsschool.quiz

data class Questions (val question:String,
                      val oneAnswer:String,
                      val twoAnswer:String,
                      val threeAnswer:String,
                      val fourAnswer:String,
                      val fiveAnswer:String,
                      val trueAnswer:Int)
 val listQuestionKT = listOf(
    Questions("1. Длительная блокировка Main-потока может привести к:","Системной ошибке ANR (Application Not Responding)",
        "IllegalArgumentException","Ни к чему не приводит. Приложение \"убьет\" задачу, которая блокирует поток, и продолжит выполнение",
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