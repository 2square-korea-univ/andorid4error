package com.example.lifecyclelog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent; // Intent import
import android.os.Bundle;
import android.widget.TextView; // TextView import
import android.util.Log; // Log import

public class sub extends AppCompatActivity {

    private static final String TAG = "SecondActivityLifecycle";
    private TextView textViewSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Log.d(TAG, "onCreate() 호출됨");

        textViewSecond = findViewById(R.id.textViewSecond); // 레이아웃의 TextView ID 확인 필요

        // 나를 호출한 Intent 가져오기
        Intent intent = getIntent();

        // Intent가 null이 아니고, Extra 데이터가 있는지 확인
        if (intent != null && intent.hasExtra(MainActivity.KEY_DATA)) {
            // KEY_DATA를 사용하여 String 데이터 추출 (기본값 "" 지정 가능)
            String receivedData = intent.getStringExtra(MainActivity.KEY_DATA);

            // TextView에 전달받은 데이터 표시
            textViewSecond.setText("전달받은 데이터: " + receivedData);
            Log.d(TAG, "전달받은 데이터: " + receivedData);
        } else {
            textViewSecond.setText("전달받은 데이터 없음");
            Log.d(TAG, "전달받은 데이터 없음");
        }

        /* Bundle로 데이터를 받았다면
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String data1 = bundle.getString("key1");
            int data2 = bundle.getInt("key2", 0); // 기본값 0 설정
            Log.d(TAG, "Bundle 데이터: " + data1 + ", " + data2);
        }
        */

        // 생명주기 로그 추가 (실습 1처럼 onStart, onResume 등 추가)
        Log.d(TAG, "SecondActivity onCreate 완료");
    }

    // SecondActivity에도 onStart, onResume 등 생명주기 로그 추가 권장
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() 호출됨");
    }
    // ... (onResume, onPause, onStop, onDestroy, onRestart 추가)
}
