package ca.ualberta.cs.lonelytwitter;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by critt on 2018-02-28.
 */

public class EditTweetActivity extends Activity{
    private Tweet input = new NormalTweet("");
    private EditText bodyText;
    private EditTweetActivity activity = this;
    private static final String FILENAME = "file.sav";

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        bodyText = (EditText) findViewById(R.id.edit);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Gson gson = new Gson();
            Type tweetType = new TypeToken<NormalTweet>(){}.getType();
            input = gson.fromJson(bundle.getString(EXTRA_MESSAGE), tweetType);
        }
        else{
            input = new NormalTweet("Filler Tweet");
        }
        bodyText.setText("Test Tweet!", TextView.BufferType.EDITABLE);
        Button doneButton = (Button) findViewById(R.id.done);
        doneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                String text = bodyText.getText().toString();
                saveInFile(new NormalTweet(text));
                Intent intent = new Intent(activity, LonelyTwitterActivity.class);
                startActivity(intent);
            }
        });
    }
    private void saveInFile(Tweet tweet) {
        try {

            FileOutputStream fos = openFileOutput(FILENAME,0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(tweet, writer);
            writer.flush();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}
