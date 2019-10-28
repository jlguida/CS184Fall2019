package com.apps.guida.cs184_f19_hw3_solution.ui.tts

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.apps.guida.cs184_f19_hw3_solution.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class TextToSpeechFragment : Fragment(), TextToSpeech.OnInitListener {

    private lateinit var textToSpeechViewModel: TextToSpeechViewModel
    private var tts: TextToSpeech? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Initialize text to speech engine
        tts = TextToSpeech(context, this)

        //Query the view model provider for the view model
        textToSpeechViewModel = ViewModelProviders.of(this).get(TextToSpeechViewModel::class.java)

        //Inflate fragment XML as normal
        val root = inflater.inflate(R.layout.fragment_share, container, false)

        //Create Observer object to listen to changes in the view model live data
        val textObserver = Observer<String> { spokenText ->
            //Output speech of updated text
            tts!!.speak(spokenText, TextToSpeech.QUEUE_FLUSH, null, "")
        }

        //Attach observer to view model
        textToSpeechViewModel.text.observe(this, textObserver)

        //Find EditText value
        val editText: EditText = root.findViewById(R.id.editText)

        //Handle pressing of the FAB
        val fab: FloatingActionButton = root.findViewById(R.id.floatingActionButton)

        fab.setOnClickListener{
            textToSpeechViewModel.text.value = editText.text.toString()
        }
        return root
    }

    override fun onInit(status: Int) {
        tts!!.language = Locale.US
    }
}