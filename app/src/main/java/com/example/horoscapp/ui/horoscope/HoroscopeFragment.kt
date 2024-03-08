package com.example.horoscapp.ui.horoscope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.horoscapp.databinding.FragmentHoroscopeBinding

class HoroscopeFragment : Fragment() {

    //Crear binding
    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!! //Devuelve el _binding sin ser nulo
    override fun onCreateView( //Se llama justo cuando se crea la vista
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Inicia el binding
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}