package com.example.service;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.example.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.fakes.RoboSharedPreferences;

import static org.junit.Assert.assertNotSame;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class SampleIntentServiceTest {

    @Test
    public void addsDataToSharedPreference() {
        /*
        * org.robolectric.RuntimeEnvironment
        * static android.app.Application "application" 런타임 application 에 대해서는 static 상수로 제공됩니다
        *
        * */
        Application application = RuntimeEnvironment.application;

        /*
        * org.robolectric.fakes.RoboSharedPreferences
        * 현재 javadoc 에서는 보이지 않습니다.
        * application.getSharedPreferences -> 기본적으로 안드로이드에서 쓰는 SharedPreference 가 생성됩니다
        * 이결 RoboSHaredPreference 로 다시 변환해 줄 필요가 있나요?
        *
        * 아래 라인은, "example" 이라는 이름의 SharedPreference 파일을 생성한다는 뜻
        * 실제 코드와 동일하게 구성했음
        * */
        RoboSharedPreferences preferences =
                (RoboSharedPreferences) application.getSharedPreferences("example", Context.MODE_PRIVATE);

        /*
        * 서비스를 시작하려고 이렇게 하는건가?
        * 일반적으로 IntentService 가 시작될려면,
        * (1) Context 를 가진이가 : context.startService(intent)
        * (2) 그럼 시스템에서      : Service 의 onHandleIntent(intent) 가 시작되도록 해줌
        * (3) ....
        *
        * 이걸 테스트로 직접해주는 것처럼 보인다.
        * (1) Intent 는 넘겨줘야 하니깐, 따로 만들고
        * (2) IntentService#onHandleIntent(Intent)도 자동 실행이 안되니깐,
        *     new 로 객체를 생성하고 직접호출
        *
        * */
        Intent intent =  new Intent(application, SampleIntentService.class);

        SampleIntentService registrationService = new SampleIntentService();
        registrationService.onHandleIntent(intent);

        // Service 에서 하는 동작이 되는지를 체크
        assertNotSame("", preferences.getString("SAMPLE_DATA", ""), "");
    }
}
