package com.example.lvl7tsk2

import androidx.lifecycle.ViewModel

class QuizViewModel: ViewModel() {
    private val questRepository = QuestRepository()

    var questIndexAt = 0

    fun getQuestion(index: Int) : Question {
        return questRepository.getQuestion(index)
    }

}