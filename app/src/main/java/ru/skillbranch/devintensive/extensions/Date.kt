package ru.skillbranch.devintensive.extensions


import java.text.SimpleDateFormat
import java.util.*

const val SECONDS =1000L
const val MINUTES = SECONDS * 60
const val HOURS = MINUTES * 60
const val DAYS= HOURS*24

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"):String{
    val dateFormat = SimpleDateFormat(pattern, Locale ("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits=TimeUnits.SECONDS):Date{
    this.time+= when (units){
        TimeUnits.SECONDS->value*SECONDS
        TimeUnits.MINUTES->value* MINUTES
        TimeUnits.HOURS->value*HOURS
        TimeUnits.DAYS->value* DAYS
    }
    return this
}

public fun Date.humanizeDiff(date:Date=Date()): String {
   if((date.time-this.time)>=0) {
    return when ((date.time-this.time)/SECONDS){
        in 0..1 -> "только что"
        in 2..45-> "несколько секунд назад"
        in 46..75->"минуту назад"
        in 76..(45*60)-> when ((date.time-this.time)/ MINUTES){
            1L,21L,31L,41L->"${(date.time-this.time)/ MINUTES} минуту назад"
            2L,3L,4L,22L,23L,24L,32L,33L,34L,42L,43L,44L->"${(date.time-this.time)/ MINUTES} минуты назад"
            else->"${(date.time-this.time)/ MINUTES} минут назад"
        }
        in (45* 60+ 1)..(75* 60)->"час назад"
        in (75* 60+ 1)..(22* 3600)-> when ((date.time-this.time)/ HOURS){
            1L,21L->"${(date.time-this.time)/ HOURS} час назад"
            2L,3L,4L,22L ->"${(date.time-this.time)/ HOURS} часа назад"
            else->"${(date.time-this.time)/ HOURS} часов назад"
        }
        in (22* 3600+ 1)..(26*3600)-> "день назад"
        in (26* 3600+ 1)..(360* 3600*24)-> when (((date.time-this.time)/ DAYS)%10){
        1L->if((date.time-this.time)/ DAYS!=11L) "${(date.time-this.time)/ DAYS} день назад" else "11 дней назад"
        in 2..4 ->if ((date.time-this.time)/ DAYS!=12L && (date.time-this.time)/ DAYS!= 13L &&
            (date.time-this.time)/ DAYS!=14L) "${(date.time-this.time)/ DAYS} дня назад" else "${(date.time-this.time)/ DAYS} дней назад"

        else->"${(date.time-this.time)/ DAYS} дней назад"
    }
        else -> "более года назад"
    }
   }else{
       return when ((this.time-date.time)/SECONDS){
           in 0..1 -> "только что"
           in 2..45-> "через несколько секунд"
           in 46..75->"через минуту"
           in 76..(45* 60)-> when (((this.time - date.time)/ MINUTES)%10){
               1L->if((this.time - date.time)/ MINUTES!=11L) "через ${(this.time - date.time)/ MINUTES} минуту" else "через 11 минут"
               in 2..4 ->if (((this.time - date.time)/ MINUTES!=12L) && (this.time - date.time)/ MINUTES!= 13L &&
                   (this.time - date.time)/ MINUTES!=14L) "через ${(this.time - date.time)/ MINUTES} минуты" else "через ${(this.time - date.time)/ MINUTES} минут"

               else->"через ${(this.time - date.time)/ MINUTES} минут"
           }
           in (45* 60+ 1)..(75* 60)->"через час"
           in (75* 60+ 1)..(22* 3600)->when (((this.time - date.time)/ HOURS)%10){
               1L->if((this.time - date.time)/ HOURS!=11L) "через ${(this.time - date.time)/ HOURS} час" else "через 11 часов"
               in 2..4 ->if (((this.time - date.time)/ HOURS!=12L) && (this.time - date.time)/ HOURS!= 13L &&
                   (this.time - date.time)/ HOURS!=14L) "через ${(this.time - date.time)/ HOURS} часа" else "через ${(this.time - date.time)/ HOURS} часов"

               else->"через ${(this.time - date.time)/ HOURS} часов"
           }
           in (22* 3600+ 1)..(26*3600)-> "через день"
           in (26* 3600+ 1)..(360* 3600*24)-> when (((this.time - date.time)/ DAYS)%10){
               1L->if((this.time - date.time)/ DAYS!=11L) "через ${(this.time - date.time)/ DAYS} день" else "через 11 дней"
               in 2..4 ->if (((this.time - date.time)/ DAYS!=12L) && (this.time - date.time)/ DAYS!= 13L &&
                   (this.time - date.time)/ DAYS!=14L) "через ${(this.time - date.time)/ DAYS} дня" else "через ${(this.time - date.time)/ DAYS} дней"

               else->"через ${(this.time - date.time)/ DAYS} дней"
           }
           else -> "более чем через год"
       }
   }
}

enum class TimeUnits{
    SECONDS{
           override fun plural(value: Int):String{
               return when(value%10){
                   1-> if (value!=11) "$value секунду" else "11 секунд"
                   in 2..4 -> if (value!=12 && value!=12 && value!=14) "$value секунды" else "$value секунд"
                   else -> "$value секунд"
               }
           }
           },
    MINUTES{
            override fun plural(value: Int):String{
            return when(value%10){
                1-> if (value!=11) "$value минуту" else "11 минут"
                in 2..4 -> if (value!=12 && value!=12 && value!=14) "$value минуты" else "$value минут"
                else -> "$value минут"
            }
            }
           },
    HOURS{
        override fun plural(value: Int):String{
            return when(value%10){
                1-> if (value!=11) "$value час" else "11 часов"
                in 2..4 -> if (value!=12 && value!=12 && value!=14) "$value часа" else "$value часов"
                else -> "$value часов"
            }
        }
         },
    DAYS{
       override fun plural(value: Int):String{
            return when(value%10){
                1-> if (value!=11) "$value день" else "11 дней"
                in 2..4 -> if (value!=12 && value!=12 && value!=14) "$value дня" else "$value дней"
                else -> "$value дней"
            }
        }
    };

    abstract fun plural(value: Int):String


}