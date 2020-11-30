package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User (
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int=0,
    var respect: Int = 0,
    val lastVisit: Date? =Date(),
    val isOnline: Boolean = false
){
    constructor(id: String, firstName: String?, lastName:String?):this(
        id=id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )
    constructor(id:String): this(id,"john","Doe")

    init{
        println("It's Alive!! \n ${if(lastName=="Doe" ) "his name $firstName $lastName" else "$firstName $lastName"} ")
    }

    fun printMe(){
        println("""
        id $id 
        firstName $firstName
        lastName $lastName 
        avatar $avatar 
        rating $rating 
        respect $respect 
        lastVisit $lastVisit
        """.trimIndent())
    }

    companion object Factory{
        var lastId: Int = -1;
        fun makeUser(fullName: String?): User{
           val (firstName,lastName)= Utils.parseFullName(fullName)
            lastId++
            return User(id = "$lastId", firstName = firstName, lastName = lastName)

        }
    }


}