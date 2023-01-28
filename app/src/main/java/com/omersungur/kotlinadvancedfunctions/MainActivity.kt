package com.omersungur.kotlinadvancedfunctions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class MainActivity : AppCompatActivity(), LifecycleLogger by LifeCycleLoggerImplementation() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //LifecycleLogger by LifeCycleLoggerImplementation() kullandık fakat interface'in metodlarını implement etmedik
        // çünkü by diyerek LifeCycleLoggerImplementation sınıfına delege ettik bu işi(devrettik). -> Delegation


        val myVariable by lazy { // lazy > eğer bu bloğa program ulaşmazsa bu veri bellekte hiç yer tutmayacak
            println("lazy block")

            10
        }

        println(myVariable)

        var myList = listOf<Int>(5, 20, 10, 2, 90, 50, 85, 105, 35)

        /* Filter and Map

        var smallNumbers = myList.filter {it > 30 }


            /*for (numbers in smallNumbers) {
                println(numbers)
            }*/

        var diffNumber = myList.map { it * 2 } // map bir transform işlemidir ve listedeki değerleri istediğim şeye dönüştürür.

        for (numbers in diffNumber) {
            println(numbers)
        }*/

        /*var filterandMap = myList.filter { it > 30 }.map { it * it}

         for (numbers in filterandMap) {
             println(numbers)
         }*/

        /* var musicians = listOf<Musicians>(
             Musicians("Omer",55,"a"),
             Musicians("Ali",50,"b"),
             Musicians("Mehmet",60,"c")
         )

         val filterMusicians = musicians.filter { it.age < 60 }.map { it.instrument }
         for (element in filterMusicians) // 60 yaşından küçük müzisyenlerin instrumentlarını yazdırdık.
             println(element)*/

        //-----------------------------------------------------------------------------------------------------------
        // Predicate

        /*val allCheck = myList.all { it < 20 } // Listedeki bütün değerler 20'den küçükse true döner.
        println(allCheck)

        val anyCheck = myList.any { it < 20 } // Listede bir değer bile 20'den küçükse true döner.
        println(anyCheck)

        val totalCount = myList.count { it < 20 } // Listede 20'den küçük kaç tane sayı varsa toplam adetini yazdırır.
        println(totalCount)

        val findNum = myList.find { it > 20 } // 20'den büyük bulduğu ilk elemanı alır.
        println(findNum)

        val findLastNum = myList.findLast { it > 20} // 20'den büyük listedeki en son elemanı bulur (35 dönecek çünkü 35 100'den daha sonra geliyor)
        println(findLastNum)

        val myPredicate = {numm : Int -> numm > 5} // predicate'ı en başta böyle tanımlayıp her fonksiyon içinde kullanabiliriz.
        val checkk = myList.all(myPredicate) */

        //-----------------------------------------------------------------------------------------------------------
        // let and also

        /*var myIntt : Int? = null

        myIntt = 5

       val myNum = myIntt?.let { // myIntt null gelseydi elvis operatorü çalışacak ve myNum 0 olacaktı.
           it + 5
       } ?: 0

        println(myNum)

        //var myList = listOf<Int>(5,20,10,2,90,50,85,105,35)
        myList.filter { it < 40 }.also { //40'dan küçükleri filtre sonra 2 ile çarp dedik. also içinde direkt işlemi yapabiliriz.
            for (numbersss in it) {
                println(numbersss * 2)
            }
        }
        */

        // apply with

        //apply
        //modify object without hassle

        val intent = Intent()
        intent.putExtra("","")
        intent.putExtra("","")
        intent.`package` = ""
        intent.action = ""

        val intentWithApply = Intent().apply { // apply kullanarak referans alıp bütün fonksiyonları kullanabiliriz. tekrar intent yazmayız.
            putExtra("","")
            putExtra("","")
            `package` = ""
            action = ""
        }

        Musicians("barley",20,"guitar").apply {
            name = "Barley" // this.name = "Barley" olarakta kullanabiliriz.
        }.also {
            println(it.name)
        }

        val newBarley = Musicians("bar",20,"Guitar").apply {
            name = "Barley"
        }
        println(newBarley.name)

        val anotherBarley = with(Musicians("arley",20,"Guitar")){
            name = "Barley" //with'i böyle kullanamayız.
        }

        println(anotherBarley) //Bir şey döndürmez

        val withBarley = Musicians("barr",20,"Guitar")
        with(withBarley) {
            name = "Barley"
            age = 4
        }

        println(withBarley.name)
    }
}

data class Musicians(var name: String, var age: Int, var instrument: String)

interface LifecycleLogger {
    fun registerLifecycleOwner(owner: LifecycleOwner)
}

class LifeCycleLoggerImplementation : LifecycleLogger, LifecycleEventObserver {
    override fun registerLifecycleOwner(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_RESUME -> println("on resume executed")
            Lifecycle.Event.ON_PAUSE -> println("on pause executed")
            else -> Unit
        }
    }
}