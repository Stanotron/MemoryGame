package com.example.project1.models

enum class BoardSize(val numcards : Int){
    Easy (8),
    Medium (18),
    Hard (24);
    fun getWidth(): Int{
        return when (this){
            Easy -> 2
            Medium -> 3
            Hard -> 4
        }
    }
    fun getHeight(): Int{
        return numcards/getWidth()
    }
    fun getNumPairs(): Int{
        return numcards/2
    }
}