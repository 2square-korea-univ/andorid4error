package com.example.lifecyclelog;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ActivityNotFoundException; // ActivityNotFoundException import
import android.content.Intent;
import android.net.Uri; // Uri import
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast; // Toast import

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityLifecycle";
    public static final String KEY_DATA = "data_from_main";

    private Button buttonToSecond;
    private EditText editTextData;
    private Button buttonOpenWeb; // 웹 브라우저 버튼 멤버 변수 선언
    private Button buttonDial;    // 전화 걸기 버튼 멤버 변수 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate() 호출됨");

        buttonToSecond = findViewById(R.id.buttonToSecond);
        editTextData = findViewById(R.id.editTextData);
        buttonOpenWeb = findViewById(R.id.buttonOpenWeb); // 웹 브라우저 버튼 ID로 뷰 찾기
        buttonDial = findViewById(R.id.buttonDial);       // 전화 걸기 버튼 ID로 뷰 찾기

        buttonToSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dataToSend = editTextData.getText().toString();
                Intent intent = new Intent(MainActivity.this, sub.class);
                intent.putExtra(KEY_DATA, dataToSend);
                startActivity(intent);
            }
        });

        // 웹 브라우저 열기 버튼 클릭 리스너 설정
        buttonOpenWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 웹 페이지를 보기 위한 암시적 인텐트 생성 (ACTION_VIEW)
                String phoneNumber = editTextData.getText().toString();

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http:" + phoneNumber));
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // 이 인텐트를 처리할 수 있는 앱이 없는 경우 예외 발생
                    Log.e(TAG, "웹 브라우저 앱을 찾을 수 없습니다.", e);
                    Toast.makeText(MainActivity.this, "웹 브라우저를 열 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 전화 걸기 화면 열기 버튼 클릭 리스너 설정
        buttonDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText에서 전화번호 가져오기
                String phoneNumber = editTextData.getText().toString();
                if (phoneNumber.isEmpty()) {
                    Toast.makeText(MainActivity.this, "전화번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 전화 걸기 화면을 열기 위한 암시적 인텐트 생성 (ACTION_DIAL)
                // "tel:" 스키마를 사용하여 전화번호 URI 생성
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // 이 인텐트를 처리할 수 있는 전화 앱이 없는 경우 예외 발생
                    Log.e(TAG, "전화 앱을 찾을 수 없습니다.", e);
                    Toast.makeText(MainActivity.this, "전화 걸기 앱을 열 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // ... (다른 생명주기 메서드들)
    }

    // ... (다른 생명주기 메서드들)
}