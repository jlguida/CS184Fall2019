package edu.ucsb.cs.cs184.hw3_jetpack.thollerermmmedley.ui.tts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TextToSpeechViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TextToSpeechViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setText(String str) {
        mText.setValue(str);
    }

}
