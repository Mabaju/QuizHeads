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

    var quizzes: MutableList<Quiz> = mutableListOf()

    init {
        val q1 = Quiz("1", "Matematik, addition")
        q1.questions.add(
            QuestionAnswers(
                "Hvad er 4+51?",
                1,
                mutableListOf<String>("55", "65", "47", "56")
            )
        )
        q1.questions.add(
            QuestionAnswers(
                "Hvad er 5+62+753?",
                3,
                mutableListOf<String>("865", "768", "820", "925")
            )
        )
        q1.questions.add(
            QuestionAnswers(
                "Hvad er 45+96+3544?",
                2,
                mutableListOf<String>("3686", "3685", "3712", "3678")
            )
        )
        q1.questions.add(
            QuestionAnswers(
                "Hvad er 54442+9885?",
                1,
                mutableListOf<String>("64327", "67852", "64952", "64395")
            )
        )
        q1.questions.add(
            QuestionAnswers(
                "Hvad er 885+665?",
                4,
                mutableListOf<String>("1510", "1385", "1560", "1550")
            )
        )
        q1.questions.add(
            QuestionAnswers(
                "Hvad er 6+75+8?",
                2,
                mutableListOf<String>("87", "89", "76", "92")
            )
        )
        q1.questions.add(
            QuestionAnswers(
                "Hvad er 5+78+2?",
                4,
                mutableListOf<String>("91", "86", "76", "85")
            )
        )
        q1.questions.add(
            QuestionAnswers(
                "Hvad er 452+954?",
                3,
                mutableListOf<String>("1411", "1424", "1406", "1400")
            )
        )
        q1.questions.add(
            QuestionAnswers(
                "Hvad er 457+86?",
                1,
                mutableListOf<String>("543", "540", "550", "546")
            )
        )
        q1.questions.add(
            QuestionAnswers(
                "Hvad er 45+785?",
                2,
                mutableListOf<String>("824", "830", "831", "840")
            )
        )
        quizzes.add(q1)

        val q2 = Quiz("2", "Matematik, subtraction")
        q2.questions.add(
            QuestionAnswers(
                "Hvad er 15-7?",
                1,
                mutableListOf<String>("8", "10", "6", "5")
            )
        )
        q2.questions.add(
            QuestionAnswers(
                "Hvad er 45-23?",
                2,
                mutableListOf<String>("20", "22", "18", "15")
            )
        )
        q2.questions.add(
            QuestionAnswers(
                "Løs ligningen 40-x=15",
                1,
                mutableListOf<String>("25", "30", "35", "40")
            )
        )
        q2.questions.add(
            QuestionAnswers(
                "Hvad er 50-14?",
                3,
                mutableListOf<String>("36", "40", "34", "26")
            )
        )
        q2.questions.add(
            QuestionAnswers(
                "Hvad er 17-9?",
                1,
                mutableListOf<String>("7", "8", "6", "9")
            )
        )
        q2.questions.add(
            QuestionAnswers(
                "Løs ligningen x-18=25",
                1,
                mutableListOf<String>("43", "37", "30", "22")
            )
        )
        q2.questions.add(
            QuestionAnswers(
                "Hvad er 72-39?",
                4,
                mutableListOf<String>("29", "31", "35", "33")
            )
        )
        q2.questions.add(
            QuestionAnswers(
                "Hvad er 29-14?",
                3,
                mutableListOf<String>("12", "13", "17", "15")
            )
        )
        q2.questions.add(
            QuestionAnswers(
                "Hvad er 63-48?",
                2,
                mutableListOf<String>("14", "15", "13", "12")
            )
        )
        q2.questions.add(
            QuestionAnswers(
                "Løs ligningen 95-x=72",
                4,
                mutableListOf<String>("15", "25", "18", "23")
            )
        )
        quizzes.add(q2)

        val q3 = Quiz("3", "Matematik, gange")
        q3.questions.add(
            QuestionAnswers(
                "Hvad er 7*5?",
                2,
                mutableListOf<String>("30", "35", "40", "42")
            )
        )
        q3.questions.add(
            QuestionAnswers(
                "Hvad er 9*6?",
                3,
                mutableListOf<String>("48", "52", "54", "56")
            )
        )
        q3.questions.add(
            QuestionAnswers(
                "Løs ligningen 4*x=24",
                2,
                mutableListOf<String>("5", "6", "7", "8")
            )
        )
        q3.questions.add(
            QuestionAnswers(
                "Hvad er 12*3?",
                2,
                mutableListOf<String>("32", "36", "40", "44")
            )
        )
        q3.questions.add(
            QuestionAnswers(
                "Hvad er 8*5?",
                4,
                mutableListOf<String>("35", "50", "45", "40")
            )
        )
        q3.questions.add(
            QuestionAnswers(
                "Løs ligningen x*9=63",
                1,
                mutableListOf<String>("7", "8", "5", "6")
            )
        )
        q3.questions.add(
            QuestionAnswers(
                "Hvad er 15*2?",
                3,
                mutableListOf<String>("25", "35", "30", "40")
            )
        )
        q3.questions.add(
            QuestionAnswers(
                "Hvad er 7*8?",
                3,
                mutableListOf<String>("48", "52", "56", "60")
            )
        )
        q3.questions.add(
            QuestionAnswers(
                "Hvad er 9*4?",
                2,
                mutableListOf<String>("32", "36", "40", "45")
            )
        )
        q3.questions.add(
            QuestionAnswers(
                "Løs ligningen 7*x=49",
                1,
                mutableListOf<String>("7", "6", "8", "5")
            )
        )
        quizzes.add(q3)

        val q4 = Quiz("4", "Generel viden")
        q4.questions.add(
            QuestionAnswers(
                "Hvad er hovedstaden i Australien?",
                3,
                mutableListOf<String>("Sydney", "MelBourne", "Canberra", "Brisbane")
            )
        )
        q4.questions.add(
            QuestionAnswers(
                "Hvilken planet er nærmest Solen?",
                2,
                mutableListOf<String>("Mars", "Venus", "Jupiter", "Saturn")
            )
        )
        q4.questions.add(
            QuestionAnswers(
                "Hvem malede Mona Lisa?",
                3,
                mutableListOf<String>(
                    "Pablo Picasso",
                    "Vincent van Gogh",
                    "Leonardo da Vinci",
                    "Michelangelo"
                )
            )
        )
        q4.questions.add(
            QuestionAnswers(
                "Hvor mange kontinenter er der på jorden?",
                1,
                mutableListOf<String>("7", "6", "5", "4")
            )
        )
        q4.questions.add(
            QuestionAnswers(
                "Hvad er hovedbestanddelen af Jordens atmosfære?",
                2,
                mutableListOf<String>("Oxygen", "Nitrogen", "Carbon Dioxide", "Hydrogen")
            )
        )
        quizzes.add(q4)

        val q5 = Quiz("5", "Popkultur")
        q5.questions.add(
            QuestionAnswers(
                "Hvem spiller hovedrollen som Harry Potter i filmserien?",
                1,
                mutableListOf<String>(
                    "Daniel Radcliffe",
                    "Rupert Grint",
                    "Emma Watson",
                    "Tom Felton"
                )
            )
        )
        q5.questions.add(
            QuestionAnswers(
                "Hvad er titlen på Adeles debutalbum?",
                4,
                mutableListOf<String>("29", "21", "25", "19")
            )
        )
        q5.questions.add(
            QuestionAnswers(
                "Hvilken tv-serie handler om en gruppe venner, der bor i New York?",
                2,
                mutableListOf<String>(
                    "How I Met Your Mother",
                    "Friends",
                    "The Big Bang Theory",
                    "Seinfeld"
                )
            )
        )
        q5.questions.add(
            QuestionAnswers(
                "Hvem vandt årets Oscar for bedste mandlige skuespiller i 2020?",
                3,
                mutableListOf<String>(
                    "Joaquin Phoenix",
                    "Leonardo DiCaprio",
                    "Brad Pitt",
                    "Tom Hanks"
                )
            )
        )
        q5.questions.add(
            QuestionAnswers(
                "Hvilken film vandt bedste billede ved Oscar-uddelingen i 2019?",
                2,
                mutableListOf<String>("La La Land", "The Shape of Water", "Moonlight", "1917")
            )
        )
        quizzes.add(q5)

        val q6 = Quiz("6", "Sport")
        q6.questions.add(
            QuestionAnswers(
                "Hvilken sportsgren spilles på et ovalt græsareal og involverer en runde læderbold?",
                2,
                mutableListOf<String>("BasketBall", "Rugby", "Fodbold", "Golf")
            )
        )
        q6.questions.add(
            QuestionAnswers(
                "Hvem er regerende verdensmester i Formel 1 i 2022?",
                4,
                mutableListOf<String>(
                    "Lewis Hamilton",
                    "Sebastian Vettel",
                    "Charles Leclerc",
                    "Max Verstappen"
                )
            )
        )
        q6.questions.add(
            QuestionAnswers(
                "Hvilket land er kendt for at være fødestedet for sumobrydning?",
                3,
                mutableListOf<String>("Kina", "Rusland", "Japan", "Indien")
            )
        )
        q6.questions.add(
            QuestionAnswers(
                "Hvem er den mest vindende Olympiske atlet i historien med 23 guldmedaljer?",
                1,
                mutableListOf<String>("Michael Phelps", "Usain Bolt", "Simone Biles", "Carl Lewis")
            )
        )
        q6.questions.add(
            QuestionAnswers(
                "Hvilken boldsport spilles på en bane med en \"deuce\" og \"love\"?",
                2,
                mutableListOf<String>("Golf", "Tennis", "Badminton", "Volleyball")
            )
        )
        quizzes.add(q6)

        val q7 = Quiz("7", "Videnskab og natur")
        q7.questions.add(
            QuestionAnswers(
                "Hvad er det kemiske symbol for vand?",
                4,
                mutableListOf<String>("H", "O", "HO", "H2O")
            )
        )
        q7.questions.add(
            QuestionAnswers(
                "Hvor mange planeter er der i vores solsystem?",
                2,
                mutableListOf<String>("7", "8", "9", "10")
            )
        )
        q7.questions.add(
            QuestionAnswers(
                "Hvilket dyr er verdens største pattedyr?",
                2,
                mutableListOf<String>("Elefant", "Blåhval", "Giraf", "Hvalhaj")
            )
        )
        q7.questions.add(
            QuestionAnswers(
                "Hvad er det primære formål med en teleskop?",
                2,
                mutableListOf<String>(
                    "Mikroskopisk observation",
                    "Astronomisk observation",
                    "Fotografi",
                    "Undervandsobservation"
                )
            )
        )
        q7.questions.add(
            QuestionAnswers(
                "Hvad er hovedkomponenten i Jordens kerne?",
                1,
                mutableListOf<String>("Jern", "Nikkel", "Kobber", "Aluminium")
            )
        )
        quizzes.add(q7)

        val q8 = Quiz("8", "Teknologi")
        q8.questions.add(
            QuestionAnswers(
                "Hvad står forkortelsen \"URL\" for?",
                1,
                mutableListOf<String>(
                    "Universal Resource Locator",
                    "Uniform Reference Link",
                    "United Resource Locator",
                    "Universal Reference Link"
                )
            )
        )
        q8.questions.add(
            QuestionAnswers(
                "Hvilket firma udviklede den første kommercielt tilgængelige mikrocomputer?",
                4,
                mutableListOf<String>("IBM", "Microsoft", "Apple", "Altair")
            )
        )
        q8.questions.add(
            QuestionAnswers(
                "Hvad står \"HTML\" for i webudvikling?",
                3,
                mutableListOf<String>(
                    "Hyperlink and Text Markup Language",
                    "Hyper Transfer Markup Language",
                    "Hyper Text Markup Language",
                    "High-Level Text Markup Language"
                )
            )
        )
        q8.questions.add(
            QuestionAnswers(
                "Hvilken type hukommelse bruges primært som computerens korttids-hukommelse?",
                1,
                mutableListOf<String>(
                    "RAM (Random Access Memory)",
                    "ROM (Read-Only Memory)",
                    "Flash-hukommelse",
                    "Harddisk"
                )
            )
        )
        q8.questions.add(
            QuestionAnswers(
                "Hvem grundlagde virksomheden Tesla Motors?",
                2,
                mutableListOf<String>("Jeff Bezos", "Elon Musk", "Mark Zuckerberg", "Bill Gates")
            )
        )
        quizzes.add(q8)

        val q9 = Quiz("9", "Litteratur")
        q9.questions.add(
            QuestionAnswers(
                "Hvem skrev tragedien \"Romeo og Julie\"?",
                1,
                mutableListOf<String>(
                    "William Shakespeare",
                    "Jane Austen",
                    "Charles Dickens",
                    "Leo Tolstoy"
                )
            )
        )
        q9.questions.add(
            QuestionAnswers(
                "Hvad er titlen på J.K. Rowlings første bog i Harry Potter-serien?",
                2,
                mutableListOf<String>(
                    "Harry Potter and the Prisoner of Azkaban",
                    "Harry Potter and the Philosopher's Stone",
                    "Harry Potter and the Goblet of Fire",
                    "Harry Potter and the Order of the Phoenix"
                )
            )
        )
        q9.questions.add(
            QuestionAnswers(
                "Hvem skrev \"To Kill a Mockingbird\"?",
                1,
                mutableListOf<String>(
                    "Harper Lee",
                    "J.D. Salinger",
                    "F. Scott Fitzgerald",
                    "Ernest Hemingway"
                )
            )
        )
        q9.questions.add(
            QuestionAnswers(
                "Hvem er forfatteren til \"1984\"?",
                1,
                mutableListOf<String>(
                    "George Orwell",
                    "Aldous Huxley",
                    "Ray Bradbury",
                    "Margaret Atwood"
                )
            )
        )
        q9.questions.add(
            QuestionAnswers(
                "Hvilken litterær genre er \"Catcher in the Rye\" bedst kendt for?",
                4,
                mutableListOf<String>("Krimi", "Science fiction", "Dystopi", "Coming-of-age")
            )
        )
        quizzes.add(q9)

        val q10 = Quiz("10", "Musik")
        q10.questions.add(
            QuestionAnswers(
                "Hvem er kendt som \"The King of Pop\"?",
                2,
                mutableListOf<String>("Prince", "Elvis Presley", "Michael Jackson", "Madonna")
            )
        )
        q10.questions.add(
            QuestionAnswers(
                "Hvilket musikinstrument spiller Kenny G primært?",
                2,
                mutableListOf<String>("Guitar", "Saxofon", "Klaver", "Trompet")
            )
        )
        q10.questions.add(
            QuestionAnswers(
                "Hvem er forsangeren i rockbandet Queen?",
                1,
                mutableListOf<String>(
                    "Freddie Mercury",
                    "David Bowie",
                    "Mick Jagger",
                    "Roger Taylor"
                )
            )
        )
        q10.questions.add(
            QuestionAnswers(
                "Hvad er det bedst sælgende album nogensinde?",
                1,
                mutableListOf<String>(
                    "Thriller af Michael Jackson",
                    "Dark Side of the Moon af Pink Floyd",
                    "Back in Black af AC/DC",
                    "The Beatles af The Beatles"
                )
            )
        )
        q10.questions.add(
            QuestionAnswers(
                "Hvilken musikgenre er kendt for sit improviserede og spontane præg?",
                2,
                mutableListOf<String>("Klassisk", "Jazz", "Country", "Elektronisk")
            )
        )
        quizzes.add(q10)

        val q11 = Quiz("11", "Film og TV")
        q11.questions.add(
            QuestionAnswers(
                "Hvem instruerede filmen \"Inception\" fra 2010?",
                2,
                mutableListOf<String>(
                    "Quentin Tarantino",
                    "Christopher Nolan",
                    "Steven Spielberg",
                    "Martin Scorsese"
                )
            )
        )
        q11.questions.add(
            QuestionAnswers(
                "Hvad er titlen på den første \"Star Wars\" -film, der blev udgivet i 1977?",
                3,
                mutableListOf<String>(
                    "The Empire Strikes Back",
                    "Return of the Jedi",
                    "A New Hope",
                    "The Phantom Menace"
                )
            )
        )
        q11.questions.add(
            QuestionAnswers(
                "Hvad er hovedkarakterens navn i TV-serien \"Breaking Bad\"?",
                1,
                mutableListOf<String>(
                    "Walter White",
                    "Jesse Pinkman",
                    "Saul Goodman",
                    "Hank Schrader"
                )
            )
        )
        q11.questions.add(
            QuestionAnswers(
                "Hvilken skuespiller spiller hovedrollen som Iron Man i Marvel Cinematic Universe?",
                3,
                mutableListOf<String>(
                    "Chris Evans",
                    "Chris Hemsworth",
                    "Robert Downey Jr.",
                    "Mark Ruffalo"
                )
            )
        )
        q11.questions.add(
            QuestionAnswers(
                "Hvilken film vandt bedste animerede film ved Oscar-uddelingen i 2021?",
                1,
                mutableListOf<String>("Soul", "Onward", "Wolfwalkers", "Over the Moon")
            )
        )
        quizzes.add(q11)
    }

    fun getPersons(): List<Person> {
        return persons.values.toList()
    }

    fun getPerson(id: String): Person? {
        return persons[id]
    }

    fun getQuiz(): List<Quiz> {
        return quizzes
    }

    fun getQuizById(id: String): Quiz? {
        for (quiz in quizzes) {
            if (id == quiz.id) {
                return quiz
            }
        }
        return null
    }
}

data class Person(val id: String, val name: String) {}

data class Friends(val id: String, val name: String, val function: String) {}