package br.gov.sp.adviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements RewardedVideoAdListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String AD_UNIT_ID = "ca-app-pub-2099197271677892/5222723569";
    private static final String APP_ID = "ca-app-pub-2099197271677892~3745990360";

    private RewardedVideoAd ad;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ad.destroy(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        MobileAds.initialize(this, APP_ID);

        ad = MobileAds.getRewardedVideoAdInstance(this);
        ad.setRewardedVideoAdListener(this);

        loadAd();

    }

    @SuppressWarnings("unused")
    @OnClick(R.id.btn_show_ad) public void showAdClicked() {
        showAd();
    }

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
        Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Log.e(TAG, "onRewardedVideoAdOpened");
        Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoStarted() {
        Log.e(TAG, "onRewardedVideoStarted");
        Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
        Log.e(TAG, "onRewardedVideoAdClosed");
        Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        Log.e(TAG, "onRewarded");
        Toast.makeText(this, "onRewarded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Log.e(TAG, "onRewardedVideoAdLeftApplication");
        Toast.makeText(this, "onRewardedVideoAdLeftApplication", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        Log.e(TAG, "onRewardedVideoAdFailedToLoad");
        Toast.makeText(this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
    }
}
