package com.example.horoscapp.domain.usecase

import com.example.horoscapp.domain.Repository
import javax.inject.Inject

class GetPredictionUseCase @Inject constructor(private val repository: Repository){
    //nos permite sobre escribir
    suspend operator fun invoke(sign:String) = repository.getPrediction(sign)
}