package com.example.a33

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.JsonReader
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    lateinit var newRecyclerView: RecyclerView
    lateinit var newArrayList: ArrayList<Person>
    var sex:ArrayList<Int> = ArrayList()
    var personName:ArrayList<String> = ArrayList()
    var personPhone:ArrayList<String> = ArrayList()
    var prevSort :String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        read_json()

        newRecyclerView = findViewById(R.id.recyclerView)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        val b1: Button = findViewById(R.id.sortSex)
        val b2: Button = findViewById(R.id.sortName)
        val b3: Button = findViewById(R.id.sortPhone)

        b1.setOnClickListener(){
            getUserdata("sex")
        }

        b2.setOnClickListener(){
            getUserdata("name")
        }

        b3.setOnClickListener(){
            getUserdata("phone")
        }

        getUserdata()

    }

    fun read_json()
    {
        var json: String? = null
        try {
            val inputStream: InputStream = assets.open("persons.json")
            json = inputStream.bufferedReader().use{it.readText()}

            var jsonarr = JSONArray(json)


            for (i in 0 until jsonarr.length()) {
                var jsonobj = jsonarr.getJSONObject(i)
                sex.add(when(jsonobj.getString("sex"))
                {
                    "male" -> R.drawable.male
                    "female" -> R.drawable.female
                    else -> R.drawable.unknown
                })
                personName.add(jsonobj.getString("name"))
                personPhone.add(jsonobj.getString("phoneNumber"))
            }

        }
        catch (e: IOException){

        }
    }

    fun getUserdata(sort : String? = null){
        newArrayList = arrayListOf<Person>()
        for(i in sex.indices){

            val person = Person(sex[i], personName[i], personPhone[i])
            newArrayList.add(person)
        }

        when (sort){
            "sex" -> newArrayList.sortBySex()
            "name" -> newArrayList.sortByName()
            "phone" -> newArrayList.sortByPhone()
        }
        if (prevSort == sort){
            prevSort = null
        }
        else
            prevSort = sort
        newRecyclerView.adapter = MyRecyclerViewAdapter(newArrayList)

    }

    fun ArrayList<Person>.sortBySex(){
        for (i in lastIndex-1 downTo 0){
            for (j in 0..i){
                val obj1 = this[j]
                val obj2 = this[j+1]
                if (compareValues(obj1.sex, obj2.sex) > 0 && prevSort != "sex" || compareValues(obj1.sex, obj2.sex) < 0 && prevSort == "sex" )
                {
                    this[j] = this[j+1].also { this[j+1]=this[j] }
                }
            }
        }
    }

    fun ArrayList<Person>.sortByName(){
        for (i in lastIndex-1 downTo 0){
            for (j in 0..i){
                val obj1 = this[j]
                val obj2 = this[j+1]

                if (compareValues(obj1.name, obj2.name) > 0 && prevSort != "name" || compareValues(obj1.name, obj2.name) < 0 && prevSort == "name" )
                {
                    this[j] = this[j+1].also { this[j+1]=this[j] }
                }
            }
        }
    }

    fun ArrayList<Person>.sortByPhone(){
        for (i in lastIndex-1 downTo 0){
            for (j in 0..i){
                val obj1 = this[j]
                val obj2 = this[j+1]
                if (compareValues(obj1.phone, obj2.phone) > 0 && prevSort != "phone" || compareValues(obj1.phone, obj2.phone) < 0 && prevSort == "phone" )
                {
                    this[j] = this[j+1].also { this[j+1]=this[j] }
                }
            }
        }
    }

}



