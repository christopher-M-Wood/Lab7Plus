package ca.ualberta.cs.lonelytwitter;

import android.os.Bundle;
import android.app.Activity;
import com.google.gson.Gson;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by critt on 2018-02-28.
 */

public class EditTweetActivity extends Activity{
    private Tweet input;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        Gson gson = new Gson();
        input = gson.fromJson(bundle.getString(EXTRA_MESSAGE),Tweet.class);
    }
}
