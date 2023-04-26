package com.example.appmoviles.models

import java.util.Date

data class Performer (
    val performerId:Int,
    val name:String,
    val image:String,
    val description:String,
    val birthDate:String,
    val creationDate:String,
    val type:String,
    val bandId:Int
)