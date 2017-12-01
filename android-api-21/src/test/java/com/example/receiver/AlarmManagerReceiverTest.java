package com.example.receiver;

import android.app.Application;
import android.content.Intent;

import com.example.BuildConfig;
import com.example.service.SampleIntentService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class AlarmManagerReceiverTest {

    @Test
    public void startServiceForTheScheduledAlarm() {

        Application application = RuntimeEnvironment.application;
        Intent expectedService = new Intent(application, SampleIntentService.class);

        /*
        * Receiver 에서 인텐트를 받았다 치고, 동작을 시킵니다
        * */
        AlarmManagerReceiver alarmManagerReceiver = new AlarmManagerReceiver();
        alarmManagerReceiver.onReceive(application, new Intent());

        /*
        * getNextStartedService
        * Description: Consumes the most recent Intent started by
        *              startService(android.content.Intent) and returns it
        * Return: the most recently started Intent
        *
        * 가장 최근에 startService 로 시작된 서비스를 소비해버립니다
        * 그리고 그 최근 서비스를 시작시킨 Intent 를 리턴합니다
        * (예) 아래에서는 가장 최근에 시작한 -> SampleIntentService 시작시킨 그 Intent 가 리턴됩니다
        *
        * 시작을 했고, (not null)
        * 예상하던 그 녀석이 맞습니다, (equals)
        *
        * */
        Intent serviceIntent = shadowOf(application).getNextStartedService();
        assertNotNull("Service started ",serviceIntent);
        assertEquals("Started service class ", serviceIntent.getComponent(), expectedService.getComponent());
    }
}
