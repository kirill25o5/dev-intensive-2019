package ru.skillbranch.devintensive.extensions

fun String.truncate(value:Int=16):String{
    if (value<=this.length) {
        var a: String = this.substring(0, value)
        var i = a[a.length - 1]
        var k = 0
        while (i == ' ') {
            a = a.substring(0, a.length - 1)
            k++
            i = a[a.length - 1]
        }
        return if (k > 1) a else (a + "...")
    }else return this
}

fun String.stripHtml():String{
    var a=this.substring(0)
    var k=0
    for(i in a.indices){
             if (a[i] =='<'){
                 k=i
                 while (a[k]!='>'){
                     k++
                 }
                 k--
                 a=a.replaceRange(i..k,"".padEnd(k-i+1,'>'))
                 continue
             }
             if(a[i]=='&'){
                 k=i
                 while (a[k]!=';'){
                     k++
                 }
                 k--
                 a=a.replaceRange(i..k,"".padEnd(k-i+1,'>'))
                 continue
             }

            if(a[i]==' ' && a[i+1]==' '){
                k=i
                while (a[k]==' '){
                    k++
                }
                k--
                a=a.replaceRange(i+1..k,"".padEnd(k-i,'>'))
                continue
             }
    }
    a=a.replace(">","")
    return a
}