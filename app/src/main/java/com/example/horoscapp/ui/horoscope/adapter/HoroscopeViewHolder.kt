package com.example.horoscapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscapp.databinding.ItemHoroscopeBinding
import com.example.horoscapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemHoroscopeBinding.bind(view)
    fun render(horoscopeInfo: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit) {

        val context = binding.tvHoroscope.context

        binding.ivHoroscope.setImageResource(horoscopeInfo.img) //Añade la imagen
        binding.tvHoroscope.text = context.getString(horoscopeInfo.name) //Añade el texto

        binding.parent.setOnClickListener {
            //Se crea nueva funcion Lambda para que devuelva la Lambda principal de onItemSelected
            startRotationAnimation(binding.ivHoroscope, newLamda = {onItemSelected(horoscopeInfo)})
        }
    }

    //Funcion para animacion
    private fun startRotationAnimation(view: View, newLamda:() -> Unit) {
        view.animate().apply {
            duration = 500 //Duracion de animacion
            interpolator = LinearInterpolator() //Sea una velocidad lineal
            rotationBy(360f) //Rotacion de 360º
            withEndAction { newLamda() } //Se ejecuta cuando termine la animacion
            start() //Se inicie
        }
    }

}