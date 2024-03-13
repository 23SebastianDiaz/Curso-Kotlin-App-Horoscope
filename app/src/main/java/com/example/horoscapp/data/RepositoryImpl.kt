package com.example.horoscapp.data

import com.example.horoscapp.data.network.HoroscopeApiService
import com.example.horoscapp.data.network.response.PredictionResponse
import com.example.horoscapp.domain.Repository
import com.example.horoscapp.domain.model.PredictionModel
import javax.inject.Inject

//Implementacion de repositorio
class RepositoryImpl @Inject constructor(private val apiService: HoroscopeApiService) :Repository {
    override suspend fun getPrediction(sign: String):PredictionModel? {
        //PeticionRetrofit
        runCatching { apiService.getHoroscope(sign) }
            .onSuccess { return it.toDomain() } //si ha ido bien la llamada
            .onFailure {  } //si no ha ido bien la llamada
        return null
    }

}