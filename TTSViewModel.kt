package com.apps.guida.cs184_f19_hw3_solution.ui.tts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TextToSpeechViewModel : ViewModel() {

    val _text = MutableLiveData<String>().apply {
        value = "This is TTS Fragment"
    }
    val text: MutableLiveData<String> = _text
}