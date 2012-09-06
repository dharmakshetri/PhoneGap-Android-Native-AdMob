Integrating AdMob's Native Android SDK with your Android PhoneGap App
=

Versions Used / Confirmed Working
-
- PhoneGap 2.0.0
- AdMob Native Android SDK: 6.1.0

Background
-
Traditionally one of the most popular ways to monetize PhoneGap apps with ads has been to use AdMob's Smartphone Web Ads. These JavaScript powered ads fit in nicely with PhoneGap's build once, deploy everywhere paradigm. However <a href="http://support.google.com/admob/bin/answer.py?hl=en&answer=1678439">earlier this year Google disabled the Smartphone Web ad types in favor of AdSense</a>. The caveat with this being, in order to integrate AdSense and stay compliant with AdSense's terms and conditions, the ads must be embedded in a location that is freely accessible on the web to utilize AdSense's automatic contextualization, and that requires the location to be crawleable. Do not expect to be able to copy and paste in AdSense's ad code into your PhoneGap app, as you would do on a normal mobile site, your AdSense account will most likely be quickly closed for violating the terms and conditions.

Solution
-
It is easy to use <a href="https://developers.google.com/mobile-ads-sdk/download#downloadandroid">AdMob's Native Android SDK</a> to integrate ads into your PhoneGap app. This will display AdMob in addition to AdSense ads (in a Terms and Conditions compliant manor) to help monetize your PhoneGap app as efficiently as possible. If you are not an AdSense user, or wish to only include AdMob ads, you can opt to not enable AdSense under the AdMob control panel. 

Step 1) Install AdMob's Native Android SDK
-
- Download the <a href="https://developers.google.com/mobile-ads-sdk/download#downloadandroid">Android AdMob SDK</a>
- Copy GoogleAdMobAdsSdk-\*.\*.\*.jar to your PhoneGap's libs directory.
- In Eclipse, Right or secondary click on your project, choose 'Build Path' then the last menu item 'Configure Build Path'.
- Select the 'Libraries' tab
- Click 'Add Jars'
- Select yourProject/libs/GoogleAdMobAdsSdk-\*.\*.\*.jar

Step 2) Include Newly Installed Library
-
- Open yourProject/src/com.\*.\*/MainActivity.java
- Expand the collapsed inclusions (The + next to import android.os.Bundle; by default)
- Add import com.google.ads.*;

Step 3) Configure your AdMob ad unit ID
-
Inside your MainActivity create a string for your ad id with the following code:

    private static final String AdMob_Ad_Unit = "Unit_ID_Here";
**Be sure to include your own ad unit id, otherwise no ads will be displayed**

Step 4) Create your Ad View
-
Below your unit id string, add a new view:

    private AdView adView;

Following PhoneGap's super.loadUrl call, you'll configure and initialize your new Ad View as follows:

    adView = new AdView(this, AdSize.BANNER, AdMob_Ad_Unit); 
    LinearLayout layout = super.root;
    layout.addView(adView); 
    AdRequest request = new AdRequest();
    request.setTesting(true);
    adView.loadAd(request);

<a href="https://github.com/sainttex/PhoneGap-Android-Native-AdMob/blob/master/src/com/phonegap/admob/MainActivity.java">View complete example of this file</a>

Step 5) Define activity in AndroidManifest.xml
-
After your MainActivity, create define the ad activity:

    <activity android:name="com.google.ads.AdActivity"
      android:configChanges="keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize"/>

<a href="https://github.com/sainttex/PhoneGap-Android-Native-AdMob/blob/master/AndroidManifest.xml">View complete example of this file</a>

Step 6) Disable Testing Mode
-
Be sure to disable testing mode before deploying to Google Play by changing the value to false, commenting out or deleting the following line from your MainActivity.java

    request.setTesting(true);