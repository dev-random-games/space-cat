package android.game;

import com.badlogic.gdx.backends.android.AndroidApplication;

import android.os.Bundle;

public class androidGameActivity extends AndroidApplication {
    /** Called when the activity is first created. */
	
	LibgdxTest model;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        model = new LibgdxTest();
        
        initialize(model, false);
    }
}