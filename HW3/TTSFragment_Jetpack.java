package edu.ucsb.cs.cs184.hw3_jetpack.thollerermmmedley.ui.tts;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Locale;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import edu.ucsb.cs.cs184.hw3_jetpack.thollerermmmedley.R;

public class TextToSpeechFragment extends Fragment implements View.OnClickListener {
    private TextToSpeech speaker = null;
    private EditText editText = null;
    private TextToSpeechViewModel textToSpeechViewModel = null;
    private boolean readyToSpeak = false;


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            if (textToSpeechViewModel!=null && editText!=null)
                textToSpeechViewModel.setText(editText.getText().toString());
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle state) {
        textToSpeechViewModel =
                ViewModelProviders.of(this).get(TextToSpeechViewModel.class);

        View root = inflater.inflate(R.layout.fragment_text_to_speech, container, false);
        editText = root.findViewById(R.id.text_for_speech);

        textToSpeechViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (readyToSpeak)
                    speaker.speak(textToSpeechViewModel.getText().getValue(), TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_volume_up_black_24dp);
        fab.show();
        fab.setOnClickListener(this);

        if (speaker == null) {
            readyToSpeak = false;
            speaker = new TextToSpeech(getActivity().getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {
                        speaker.setLanguage(Locale.getDefault());
                        readyToSpeak = true;
                    }
                }
            });
        }
    }

}
