package ca.ualberta.cs.lonelytwitter;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by critt on 2018-02-28.
 */

public class EditTweetActivity extends Activity{
    private Tweet input;
    private EditText bodyText;
    private EditTweetActivity activity = this;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Gson gson = new Gson();
            input = gson.fromJson(bundle.getString(EXTRA_MESSAGE), Tweet.class);
        }
        else{
            input = new NormalTweet("Filler Tweet");
        }
        setContentView(R.layout.main);
        bodyText = (EditText) findViewById(R.id.edit);
        bodyText.setText(input.getMessage());
        Button doneButton = (Button) findViewById(R.id.done);
        doneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                String text = bodyText.getText().toString();
                Tweet newTweet = new NormalTweet(text);
                Intent intent = new Intent(activity, LonelyTwitterActivity.class);
                Gson gson = new Gson();
                Type type = new TypeToken<Tweet>(){}.getType();
                String json = gson.toJson(newTweet,type);
                intent.putExtra(EXTRA_MESSAGE, json);
                startActivity(intent);
            }
        });
    }
}
