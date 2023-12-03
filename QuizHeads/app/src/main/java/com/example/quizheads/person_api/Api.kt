package com.example.quizheads.person_api

class Api {
    private val persons = mutableMapOf(
        "1" to Person("1", "Anna Andersen"),
        "2" to Person("2", "Bo Bendtsen"),
        "3" to Person("3", "Carl Callesen"),
        "4" to Person("4", "Dorthe Danielsen"),
        "5" to Person("5", "Emil Eriksen"),
        "6" to Person("6", "Frederik Fredriksen"),
        "7" to Person("7", "Gert Gundersen"),
        "8" to Person("8", "Helle Hansen"),
        "9" to Person("9", "Ib Ibsen"),
        "10" to Person("10", "Jan Jackobsen"),
        "11" to Person("11", "Kalle Kallesen"),
        "12" to Person("12", "Leo Leosen"),
        "13" to Person("13", "Mille Mynte"),
        "14" to Person("14", "Nanna Nilsen"),
        "15" to Person("15", "Ole Olsen"),
        "16" to Person("16", "Per Pedersen"),
        "17" to Person("17", "Rasmus Rasmussen"),
        "18" to Person("18", "Signe Sørensen"),
        "19" to Person("19", "Tine Thygesen"),
        "20" to Person("20", "Ulla Ulriksen"),
        "21" to Person("21", "Vera Vannelse"),
        "22" to Person("22", "William Werner"),
        "23" to Person("23", "Yrsa Yrmer"),
        "24" to Person("24", "Zelda Zebra"),

    )

    private val quiz = mutableMapOf(
        "1" to Quiz("1", "Matematik, addition"),
        "2" to Quiz("2",  "Historie"),
        "3" to Quiz("3",  "Sport"),
    )

    private val friends = mutableMapOf(
        "1" to Friends("1", "Sound Volume", "Sound level bar"),
        "2" to Friends("2",  "Notifikations", "Notification On/OFF"),
        "3" to Friends("3",  "Sound", "Bip"),
    )

    fun getPersons() : List<Person>{
        return persons.values.toList()
    }

    fun getPerson(id:String) : Person? {
        return persons[id]
    }

    fun getQuiz() : List<Quiz>{
        return quiz.values.toList()
    }

    fun getQuiz(id:String) : Quiz? {
        return quiz[id]
    }

}

data class Person(val id:String, val name:String) {}

data class Quiz(val id:String, val name:String) {}

data class Friends(val id:String, val name:String, val function:String) {}