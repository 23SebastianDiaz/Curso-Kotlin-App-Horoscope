package com.example.horoscapp.domain

import com.example.horoscapp.data.network.response.PredictionResponse
import com.example.horoscapp.domain.model.PredictionModel

//Interface que va ser llamada en data, el dominio solo sabe del metodo
interface Repository {
    suspend fun getPrediction(sign:String) : PredictionModel?
}