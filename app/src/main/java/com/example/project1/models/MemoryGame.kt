package com.example.project1.models

import com.example.project1.utils.default_icons

class MemoryGame( private val boardSize: BoardSize){
    private var numCardFlips = 0
    private var indexOfSingleSelectedCard: Int? = null
    val cards: List<MemoryCard>
    var numPairsFound = 0
    init {
        val randomImages = default_icons.shuffled().take(boardSize.getNumPairs())
        val chosen = (randomImages+randomImages).shuffled()
        cards = chosen.map{MemoryCard(it)}
    }
    fun flipCard(position: Int): Boolean {
        numCardFlips++
        val card = cards[position]
        var foundMatch = false
        if(indexOfSingleSelectedCard==null){
            restoreCards()
            indexOfSingleSelectedCard =position
        }
        else{
            foundMatch = checkForMatch(indexOfSingleSelectedCard!!,position)
            indexOfSingleSelectedCard = null
        }
        card.isFaceUp  = !card.isFaceUp
        return foundMatch
    }

    private fun checkForMatch(position1: Int, position2: Int): Boolean{
        if(cards[position1].identifier != cards[position2].identifier){
            return false
        }
        cards[position1].isMatched = true
        cards[position2].isMatched = true
        numPairsFound++
        return true
    }

    private fun restoreCards() {
        for(card in cards){
            if (!card.isMatched) {
                card.isFaceUp = false
            }
        }
    }

    fun haveWonGame(): Boolean {
        return numPairsFound == boardSize.getNumPairs()
    }

    fun isCardfacedUp(position: Int): Boolean {
        return cards[position].isFaceUp
    }

    fun getNumMoves(): Int {
        return numCardFlips/2
    }
}