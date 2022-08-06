package com.example.project1.models

data class MemoryCard(
    val identifier : Int,
    var isFaceUp: Boolean = false,
    var isMatched: Boolean = false
)