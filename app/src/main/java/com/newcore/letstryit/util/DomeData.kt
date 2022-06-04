package com.newcore.letstryit.util

import com.google.gson.Gson

private class schoolsList : ArrayList<Map<String, String>>()

object DomeData {

    val schoolsName = Gson().fromJson(schoolsNameJson, schoolsList::class.java).map {
        it["institution"]
    }.filterNotNull()

    val parentsNames = names

}


private const val schoolsNameJson = """
        [
  {
    "institution": "Alabama A & M University"
  },
  {
    "institution": "University of Alabama at Birmingham"
  },
  {
    "institution": "Amridge University"
  },
  {
    "institution": "University of Alabama in Huntsville"
  },
  {
    "institution": "Alabama State University"
  },
  {
    "institution": "University of Alabama System Office"
  },
  {
    "institution": "The University of Alabama"
  },
  {
    "institution": "Central Alabama Community College"
  },
  {
    "institution": "Athens State University"
  },
  {
    "institution": "Auburn University at Montgomery"
  },
  {
    "institution": "Auburn University"
  },
  {
    "institution": "Birmingham Southern College"
  },
  {
    "institution": "Chattahoochee Valley Community College"
  },
  {
    "institution": "Concordia College Alabama"
  },
  {
    "institution": "South University-Montgomery"
  },
  {
    "institution": "Enterprise State Community College"
  },
  {
    "institution": "James H Faulkner State Community College"
  },
  {
    "institution": "Faulkner University"
  },
  {
    "institution": "Gadsden State Community College"
  },
  {
    "institution": "New Beginning College of Cosmetology"
  },
  {
    "institution": "George C Wallace State Community College-Dothan"
  },
  {
    "institution": "George C Wallace State Community College-Hanceville"
  },
  {
    "institution": "George C Wallace State Community College-Selma"
  },
  {
    "institution": "Herzing University-Birmingham"
  },
  {
    "institution": "Huntingdon College"
  },
  {
    "institution": "Heritage Christian University"
  },
  {
    "institution": "J F Drake State Community and Technical College"
  },
  {
    "institution": "J F Ingram State Technical College"
  },
  {
    "institution": "Jacksonville State University"
  },
  {
    "institution": "Jefferson Davis Community College"
  },
  {
    "institution": "Jefferson State Community College"
  },
  {
    "institution": "John C Calhoun State Community College"
  },
  {
    "institution": "Judson College"
  },
  {
    "institution": "Lawson State Community College-Birmingham Campus"
  },
  {
    "institution": "University of West Alabama"
  },
  {
    "institution": "Lurleen B Wallace Community College"
  },
  {
    "institution": "Marion Military Institute"
  },
  {
    "institution": "Miles College"
  },
  {
    "institution": "University of Mobile"
  },
  {
    "institution": "University of Montevallo"
  },
  {
    "institution": "Northwest-Shoals Community College"
  },
  {
    "institution": "University of North Alabama"
  },
  {
    "institution": "Northeast Alabama Community College"
  },
  {
    "institution": "Oakwood University"
  },
  {
    "institution": "Alabama Southern Community College"
  },
  {
    "institution": "Prince Institute-Southeast"
  },
  {
    "institution": "Reid State Technical College"
  },
  {
    "institution": "Bishop State Community College"
  },
  {
    "institution": "Samford University"
  },
  {
    "institution": "Selma University"
  },
  {
    "institution": "Shelton State Community College"
  },
  {
    "institution": "Snead State Community College"
  },
  {
    "institution": "University of South Alabama"
  },
  {
    "institution": "Spring Hill College"
  },
  {
    "institution": "Southeastern Bible College"
  },
  {
    "institution": "Stillman College"
  },
  {
    "institution": "Talladega College"
  },
  {
    "institution": "H Councill Trenholm State Technical College"
  },
  {
    "institution": "Troy University"
  },
  {
    "institution": "Tuskegee University"
  },
  {
    "institution": "United States Sports Academy"
  },
  {
    "institution": "Bevill State Community College"
  },
  {
    "institution": "University of Alaska Anchorage"
  },
  {
    "institution": "Alaska Bible College"
  },
  {
    "institution": "University of Alaska Fairbanks"
  },
  {
    "institution": "University of Alaska Southeast"
  },
  {
    "institution": "Alaska Pacific University"
  },
  {
    "institution": "AVTEC-Alaska's Institute of Technology"
  },
  {
    "institution": "Charter College-Anchorage"
  }]
    """

private val names = listOf(
    "Aaren",
    "Aarika",
    "Abagael",
    "Abagail",
    "Abbe",
    "Abbey",
    "Abbi",
    "Abbie",
    "Abby",
    "Abbye",
    "Abigael",
    "Abigail",
    "Abigale",
    "Abra",
    "Ada",
    "Adah",
    "Adaline",
    "Adan",
    "Adara",
    "Adda",
    "Addi",
    "Addia",
    "Addie",
    "Addy",
    "Adel",
    "Adela",
    "Adelaida",
    "Adelaide",
    "Adele",
    "Adelheid",
    "Adelice",
    "Adelina",
    "Adelind",
    "Adeline",
    "Adella",
    "Adelle",
    "Adena",
    "Adey",
    "Adi",
    "Adiana",
    "Adina",
    "Adora",
    "Adore",
    "Adoree",
    "Adorne",
    "Adrea",
    "Adria",
    "Adriaens",
    "Adrian",
    "Adriana",
    "Adriane",
    "Adrianna",
    "Adrianne",
    "Adriena",
    "Adrienne",
    "Aeriel",
    "Aeriela",
    "Aeriell",
    "Afton",
    "Ag",
    "Agace",
    "Agata",
    "Agatha",
    "Agathe",
    "Aggi",
    "Aggie",
    "Aggy",
    "Agna",
    "Agnella",
    "Agnes",
    "Agnese",
    "Agnesse",
    "Agneta",
    "Agnola",
    "Agretha",
    "Aida",
    "Aidan",
    "Aigneis",
    "Aila",
    "Aile",
    "Ailee",
    "Aileen",
    "Ailene",
    "Ailey",
    "Aili",
    "Ailina",
    "Ailis",
    "Ailsun",
    "Ailyn",
    "Aime",
    "Aimee",
    "Aimil",
    "Aindrea",
    "Ainslee",
    "Ainsley",
    "Ainslie",
    "Ajay",
    "Alaine",
    "Alameda",
    "Alana",
    "Alanah",
    "Alane",
    "Alanna",
    "Alayne",
    "Alberta",
    "Albertina",
    "Albertine",
    "Albina",
    "Alecia",
    "Aleda",
    "Aleece",
    "Aleen",
    "Alejandra",
    "Alejandrina",
    "Alena",
    "Alene",
    "Alessandra",
    "Aleta",
    "Alethea",
    "Alex",
    "Alexa",
    "Alexandra",
    "Alexandrina",
    "Alexi",
    "Alexia",
    "Alexina",
    "Alexine",
    "Alexis",
    "Alfi",
    "Alfie",
    "Alfreda",
    "Alfy",
    "Ali",
    "Alia",
    "Alica",
    "Alice",
    "Alicea",
    "Alicia",
    "Alida",
    "Alidia",
    "Alie",
    "Alika",
    "Alikee",
    "Alina",
    "Aline",
    "Alis",
    "Alisa",
    "Alisha",
    "Alison",
    "Alissa",
    "Alisun",
    "Alix",
    "Aliza",
    "Alla",
    "Alleen",
    "Allegra",
    "Allene",
    "Alli",
    "Allianora",
    "Allie",
    "Allina",
    "Allis",
    "Allison",
    "Allissa",
    "Allix",
    "Allsun",
    "Allx",
    "Ally",
    "Allyce",
    "Allyn",
    "Allys",
    "Allyson",
    "Alma",
    "Almeda",
    "Almeria",
    "Almeta",
    "Almira",
    "Almire",
    "Aloise",
    "Aloisia",
    "Aloysia",
    "Alta",
    "Althea",
    "Alvera",
    "Alverta",
    "Alvina",
    "Alvinia",
    "Alvira")