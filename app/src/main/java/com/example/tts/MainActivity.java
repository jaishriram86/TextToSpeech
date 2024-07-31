package com.example.tts;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    TextToSpeech tts;
    EditText editText;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.edtText);
        btn=findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status==TextToSpeech.SUCCESS){
                           int result= tts.setLanguage(Locale.US);
                            tts.setSpeechRate(0.8f);
                            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                                Log.e("TTS", "Language not supported");
                            } else {

                                // Get available voices
                                Set<Voice> voices = tts.getVoices();
                                if (voices != null) {
                                    for (Voice voice : voices) {
                                        // Check if the voice is male
                                        if (voice.getName().toLowerCase().contains("male")) {
                                            tts.setVoice(voice);
                                            break;
                                        }
                                    }
                                }

                                // Speak the text
                                tts.speak(editText.getText().toString(), TextToSpeech.QUEUE_ADD, null, null);
                            }
                        } else {
                            Log.e("TTS", "Initialization failed");
                        }
                    }
                });
            }
        });

        simpleFunc();

    }
    public void simpleFunc(){
        Toast.makeText(this, "hello my friend", Toast.LENGTH_SHORT).show();
    }
}