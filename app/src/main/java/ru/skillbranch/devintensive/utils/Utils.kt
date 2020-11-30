package ru.skillbranch.devintensive.utils

import java.util.*

object Utils {
    fun parseFullName(fullName:String?):Pair<String?,String?>{
        var parts = fullName?.split(" ")
        val firstName = when(parts?.getOrNull(0)){
            ""-> null
            null->null
            else->parts?.getOrNull(0)
        }
        val lastName =  when(parts?.getOrNull(1)){
            ""-> null
            null->null
            else->parts?.getOrNull(1)
        }
        return Pair(firstName,lastName)
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var firstNameInitials = if (firstName!="" && (firstName?.get(0)!=' ') && firstName!= null) firstName?.get(0)?.toUpperCase() else ""
        var lastNameInitials = if (lastName!="" && (lastName?.get(0)!=' ') && lastName!= null) lastName?.get(0)?.toUpperCase() else ""

        return if (firstNameInitials=="" && lastNameInitials=="") null else "$firstNameInitials$lastNameInitials"
    }

    fun transliteration(payload: String, divider:String = " "): String {
        var (firstName, lastName)= parseFullName(payload)
        firstName=firstName?.toLowerCase()
        lastName=lastName?.toLowerCase()
        var firstTransliterationName: String=""
        var secondTransliterationName: String=""
        if (firstName != null) {
            for(i in firstName){
                    when (i){
                      'а'->   firstTransliterationName+='а'
                        'б'->firstTransliterationName+= 'b'
                        'в'->firstTransliterationName+= 'v'
                         'г'->firstTransliterationName+= 'g'
                        'д'->firstTransliterationName+= 'd'
                        'е'->firstTransliterationName+= 'e'
                        'ё'->firstTransliterationName+= 'e'
                         'ж'->firstTransliterationName+= "zh"
                         'з'->firstTransliterationName+= 'z'
                         'и'->firstTransliterationName+= 'i'
                         'й'->firstTransliterationName+= 'i'
                         'к'->firstTransliterationName+= 'k'
                         'л'->firstTransliterationName+= 'l'
                         'м'->firstTransliterationName+= 'm'
                         'н'->firstTransliterationName+= 'n'
                         'о'->firstTransliterationName+= 'o'
                         'п'->firstTransliterationName+= 'p'
                         'р'->firstTransliterationName+= 'r'
                         'с'->firstTransliterationName+= 's'
                         'т'->firstTransliterationName+= 't'
                         'у'->firstTransliterationName+= 'u'
                         'ф'->firstTransliterationName+= 'f'
                         'х'->firstTransliterationName+= 'h'
                         'ц'->firstTransliterationName+= 'c'
                         'ч'->firstTransliterationName+= "ch"
                         'ш'->firstTransliterationName+= "sh"
                         'щ'->firstTransliterationName+= "sh"
                         'ъ'->firstTransliterationName+= ""
                         'ы'->firstTransliterationName+= 'i'
                         'ь'->firstTransliterationName+= ""
                         'э'->firstTransliterationName+= 'e'
                         'ю'->firstTransliterationName+= "yu"
                         'я'->firstTransliterationName+= "ya"
                    }
            }
        }
        if (lastName != null) {
            for(i in lastName){
                when (i){
                    'а'->   secondTransliterationName+='а'
                    'б'->secondTransliterationName+= 'b'
                    'в'->secondTransliterationName+= 'v'
                    'г'->secondTransliterationName+= 'g'
                    'д'->secondTransliterationName+= 'd'
                    'е'->secondTransliterationName+= 'e'
                    'ё'->secondTransliterationName+= 'e'
                    'ж'->secondTransliterationName+= "zh"
                    'з'->secondTransliterationName+= 'z'
                    'и'->secondTransliterationName+= 'i'
                    'й'->secondTransliterationName+= 'i'
                    'к'->secondTransliterationName+= 'k'
                    'л'->secondTransliterationName+= 'l'
                    'м'->secondTransliterationName+= 'm'
                    'н'->secondTransliterationName+= 'n'
                    'о'->secondTransliterationName+= 'o'
                    'п'->secondTransliterationName+= 'p'
                    'р'->secondTransliterationName+= 'r'
                    'с'->secondTransliterationName+= 's'
                    'т'->secondTransliterationName+= 't'
                    'у'->secondTransliterationName+= 'u'
                    'ф'->secondTransliterationName+= 'f'
                    'х'->secondTransliterationName+= 'h'
                    'ц'->secondTransliterationName+= 'c'
                    'ч'->secondTransliterationName+= "ch"
                    'ш'->secondTransliterationName+= "sh"
                    'щ'->secondTransliterationName+= "sh"
                    'ъ'->secondTransliterationName+= ""
                    'ы'->secondTransliterationName+= 'i'
                    'ь'->secondTransliterationName+= ""
                    'э'->secondTransliterationName+= 'e'
                    'ю'->secondTransliterationName+= "yu"
                    'я'->secondTransliterationName+= "ya"
                }
            }
        }
        return "${firstTransliterationName.capitalize()}$divider${secondTransliterationName.capitalize()}"
    }
}

