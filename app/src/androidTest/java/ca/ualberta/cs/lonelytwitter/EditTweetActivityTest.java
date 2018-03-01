package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;

/**
 * Created by critt on 2018-02-28.
 */

public class EditTweetActivityTest extends ActivityInstrumentationTestCase2 {
    private Solo solo;

    public EditTweetActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }
    public void setUp() throws Exception{
        solo = new Solo(getInstrumentation(), getActivity());
    }
    public void testStart() throws Exception {
        Activity activity = getActivity();
    }
    public void testEdit(){
        solo.assertCurrentActivity("Wrong Activity", LonelyTwitterActivity.class);
        solo.enterText((EditText) solo.getView(R.id.body), "Test Tweet!");
        solo.clickOnButton("Save");
        solo.clearEditText((EditText) solo.getView(R.id.body));
        assertTrue(solo.waitForText("Test Tweet!"));
        solo.clickInList(0);
        solo.assertCurrentActivity("Wrong Activity, no change", EditTweetActivity.class);
        assertTrue(solo.waitForText("Test Tweet!"));
    }
    @Override
    public void tearDown() throws Exception{
        solo.finishOpenedActivities();
    }
}
