package br.gov.sp.adviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class MainActivity extends AppCompatActivity implements RewardedVideoAdListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String AD_UNIT_ID = "ca-app-pub-2099197271677892/5222723569";
    private static final String APP_ID = "ca-app-pub-2099197271677892~3745990360";

    private RewardedVideoAd ad;
    private Button btnShowAd;

    @Override
    public void onPause() {
        super.onPause();
        ad.pause(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ad.resume(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MobileAds.initialize(this, APP_ID);

        ad = MobileAds.getRewardedVideoAdInstance(this);
        ad.setRewardedVideoAdListener(this);

        btnShowAd = (Button) findViewById(R.id.btn_show_ad);
        btnShowAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadAd();
                showAd();
            }
        });

    }

    public void loadAd() {
        if (!ad.isLoaded()) {
            ad.loadAd(AD_UNIT_ID, new AdRequest.Builder().build());
        }
    }

    public void showAd() {
        if (ad.isLoaded()) {
            ad.show();
        }
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Log.e(TAG, "onRewardedVideoAdLoaded");
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Log.e(TAG, "onRewardedVideoAdOpened");
    }

    @Override
    public void onRewardedVideoStarted() {
        Log.e(TAG, "onRewardedVideoStarted");
    }

    @Override
    public void onRewardedVideoAdClosed() {
        Log.e(TAG, "onRewardedVideoAdClosed");
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        Log.e(TAG, "onRewarded");
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Log.e(TAG, "onRewardedVideoAdLeftApplication");
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        Log.e(TAG, "onRewardedVideoAdFailedToLoad");
    }
}
