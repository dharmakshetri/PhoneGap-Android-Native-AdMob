package com.phonegap.admob;

import android.os.Bundle;
import android.widget.LinearLayout; 
import com.google.ads.*;
import org.apache.cordova.*;

public class MainActivity extends DroidGap {
	private static final String AdMob_Ad_Unit = "Unit_ID_Here";
	private AdView adView; 

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.loadUrl("file:///android_asset/www/index.html");

        adView = new AdView(this, AdSize.BANNER, AdMob_Ad_Unit); 
        LinearLayout layout = super.root;
        layout.addView(adView); 
        AdRequest request = new AdRequest();
        request.setTesting(true);
        adView.loadAd(request); 
    }
}
