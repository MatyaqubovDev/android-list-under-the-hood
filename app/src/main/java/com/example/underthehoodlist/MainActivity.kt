package com.example.underthehoodlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayout: LinearLayout
    private lateinit var laptops: ArrayList<Laptop>
    lateinit var loadButton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        laptops = ArrayList()

        for (i in 0..9) {
            laptops.add(
                Laptop(
                    R.drawable.one,
                    "Kali laptop",
                    "Transportabel, leistungsfähig, langes Durchhaltevermögen und kompakte Formate: Mit schrumpfenden Platinengrößen sind Notebooks in den letzten drei Jahren dünner und leichter geworden. Dabei haben sich Akkus beständig weiterentwickelt und konnten die Energiedichte erhöhen, während Mobile-CPUs durch eine Fertigung im Zehn-Nanometer- (Intel) beziehungsweise Sieben-Nanometer-Bereich (AMD) energiesparender wurden. "
                )
            )
        }
        initViews()


        if (savedInstanceState!=null){
            val savedlaptops= savedInstanceState.getParcelableArrayList<Laptop>("laptops")
            savedlaptops!!.forEach {
                linearLayout.addView(addItem(it))
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("laptops",laptops)
    }

    private fun initViews() {

        loadButton=findViewById(R.id.btn_load)
        linearLayout = findViewById(R.id.container_root)
        loadButton.setOnClickListener {
            for (laptop in laptops) {
                linearLayout.addView(addItem(laptop))
            }
        }

    }

    private fun addItem(laptop: Laptop): LinearLayout {

        val tempLinearLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            val parms = LinearLayout.LayoutParams(-1, -2)
            parms.setMargins(50, 50, 50, 50)
            layoutParams = parms
            background = getDrawable(R.drawable.rounder_background)
        }

        val imageView = ImageView(this).apply {
            val parms = LinearLayout.LayoutParams(-1, 350)
            setImageResource(laptop.image)
            parms.setMargins(50, 50, 50, 50)
            layoutParams = parms
        }

        val titleTextView = TextView(this).apply {
            text = laptop.title
            val parms = LinearLayout.LayoutParams(-1, -2)
            textSize = 20f

            parms.setMargins(50, 50, 50, 50)
            layoutParams = parms
        }

        val describtionTextView = TextView(this).apply {
            text = laptop.describtion
            val parms = LinearLayout.LayoutParams(-1, -2)
            parms.setMargins(50, 50, 50, 50)
            layoutParams = parms
        }

        tempLinearLayout.addView(imageView)
        tempLinearLayout.addView(titleTextView)
        tempLinearLayout.addView(describtionTextView)

        return tempLinearLayout
    }
}