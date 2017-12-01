package com.example.receiver;

import android.content.Intent;
import android.net.wifi.WifiManager;

import com.example.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class BootCompleteReceiverTest {

    @Test
    public void registerServiceOnDeviceBootComplete() {

        /*
        * Receiver 를 통해서 실제로 수신할 Intent 를 정의합니다
        * (Intent.ACTION_BOOT_COMPLETED)
        *
        * */
        Intent intentBootCompleted = new Intent(Intent.ACTION_BOOT_COMPLETED);
        Intent intentScanResultsAvailableAction = new Intent(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);

        /*
        * org.robolectric.shadows.ShadowApplication
        * Todo: ShadowApplication 은 정확히 무슨 용도로 사용되는지 확인합시다
        * 앞선 예제(Service) 에서는 RuntimeEnvironment.application 으로 Application 객체를 가져옴
        * */
        ShadowApplication application = ShadowApplication.getInstance();

        /*
        * 단순히 Application 이 해당 Intent 에 대한 Receiver 가 있는지만 바라보는건가? 너무 심플
        * */
        assertTrue("Reboot Listener not registered ", application.hasReceiverForIntent(intentBootCompleted));
        assertFalse("Wi-Fi Scan Listener not registered ", application.hasReceiverForIntent(intentScanResultsAvailableAction));

        BootCompleteReceiver receiver = new BootCompleteReceiver();
        receiver.onReceive(application.getApplicationContext(), intentBootCompleted);
        assertTrue(true);
    }
}
