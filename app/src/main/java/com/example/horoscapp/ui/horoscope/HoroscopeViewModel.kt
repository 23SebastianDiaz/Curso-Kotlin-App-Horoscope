package com.example.horoscapp.ui.horoscope

import androidx.lifecycle.ViewModel
import com.example.horoscapp.domain.model.HoroscopeInfo
import com.example.horoscapp.domain.model.HoroscopeInfo.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel

//El inject se encarga de inyectar cualquier clase y se extiende de tipo viewModel
class HoroscopeViewModel @Inject constructor()  : ViewModel() {

    //Se crea el stateFlow donde se almacenara el listado del horoscopo
    private  var _horoscope = MutableStateFlow<List<HoroscopeInfo>>(emptyList())
    val horoscope:StateFlow<List<HoroscopeInfo>> = _horoscope

    //se crea funcion Init con la lista de los signos sodiacales de la lista de horoscopo
    init {
        _horoscope.value = listOf(
                Aries, Taurus, Gemini
        )
    }
}