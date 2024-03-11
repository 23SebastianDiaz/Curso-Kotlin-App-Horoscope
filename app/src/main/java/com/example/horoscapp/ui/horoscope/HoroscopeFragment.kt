package com.example.horoscapp.ui.horoscope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.horoscapp.databinding.FragmentHoroscopeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    //Funcion delegada para conectar fragment con viewModel
    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()

    //Crear binding
    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!! //Devuelve el _binding sin ser nulo

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initUIState()
    }

    //Se crea corrutina para conectar el viewModel con el fragment
    private fun initUIState() {
        lifecycleScope.launch{
            //cuando empiece
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                //Se engancha al viewModel
                horoscopeViewModel.horoscope.collect{

                }
            }
        }
    }

    override fun onCreateView( //Se llama justo cuando se crea la vista
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Inicia el binding
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}