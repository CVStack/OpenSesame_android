package com.ehdbsrhktmddn.senierproject;

import android.content.Context;
import android.graphics.Color;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ehdbsrhktmddn.senierproject.DTO.AndroidVO;
import com.ehdbsrhktmddn.senierproject.DTO.JsonUtil;

import java.lang.*;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import org.json.simple.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity<BluetoothSPP> extends AppCompatActivity {

    private WebSocketClient client = null;
    private AndroidVO andvo = new AndroidVO();

    private static final int REQUEST_ENABLE_BT = 10; // 블루투스 활성화 상태

    private BluetoothAdapter bluetoothAdapter; // 블루투스 어댑터

    private Set<BluetoothDevice> devices; // 블루투스 디바이스 데이터 셋

    private BluetoothDevice bluetoothDevice; // 블루투스 디바이스

    private BluetoothSocket bluetoothSocket = null; // 블루투스 소켓

    private OutputStream outputStream = null; // 블루투스에 데이터를 출력하기 위한 출력 스트림

    private InputStream inputStream = null; // 블루투스에 데이터를 입력하기 위한 입력 스트림

    private Thread workerThread = null; // 문자열 수신에 사용되는 쓰레드

    private byte[] readBuffer; // 수신 된 문자열을 저장하기 위한 버퍼

    private int readBufferPosition; // 버퍼 내 문자 저장 위치

    int pariedDeviceCount;
    String[] word;

    private TextView textViewReceive, textViewReceive2; // 수신 된 데이터를 표시하기 위한 텍스트 뷰

    //private EditText editTextSend; // 송신 할 데이터를 작성하기 위한 에딧 텍스트

    private Button buttonSend, buttonSend2, buttonSend3; // 송신하기 위한 버튼

    String h;
    int gongi;
    double mise;

    int swtich = 0;

    private BluetoothSPP bt;
    public ConstraintLayout mLayout;

    //View line;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayout = (ConstraintLayout) findViewById(R.id.mLayout);

        mLayout.setBackgroundColor(Color.rgb(135, 206, 235));
        textViewReceive = (TextView) findViewById(R.id.textView15);
        textViewReceive2 = (TextView) findViewById(R.id.textView16);
        buttonSend = (Button) findViewById(R.id.buttonSend);
        buttonSend2 = (Button) findViewById(R.id.buttonSend2);
        buttonSend3 = (Button) findViewById(R.id.buttonSend3);
        client_connect(); //client connect 함수 실행
//
//


//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        if(client.isOpen())
            textViewReceive.setText("open" + " ppm");
        else
            textViewReceive.setText("closed" + " ppm");

        buttonSend.setOnClickListener(new View.OnClickListener() { //센서값 요청

            @Override

            public void onClick(View v) {
                client_sendRequest_Sensors();
//                if(swtich!=1) {
//                    sendData("1");
//                    swtich = 1;
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            sendData("3");
//                        }
//                    },10000);
//                }

            }

        });


        buttonSend2.setOnClickListener(new View.OnClickListener() { //창문제어

            @Override

            public void onClick(View v) {

                client_sendRequest_Window();
            }

        });

        buttonSend3.setOnClickListener(new View.OnClickListener() { //disconnect

            @Override

            public void onClick(View v) {

                client_sendRequest_disconnect();
            }

        });

        // 블루투스 활성화하기

//        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter(); // 블루투스 어댑터를 디폴트 어댑터로 설정
//
//
//        if (bluetoothAdapter == null) { // 디바이스가 블루투스를 지원하지 않을 때
//
//            // 여기에 처리 할 코드를 작성하세요.
//
//        } else { // 디바이스가 블루투스를 지원 할 때
//
//            if (bluetoothAdapter.isEnabled()) { // 블루투스가 활성화 상태 (기기에 블루투스가 켜져있음)
//
//                selectBluetoothDevice(); // 블루투스 디바이스 선택 함수 호출
//
//            } else { // 블루투스가 비 활성화 상태 (기기에 블루투스가 꺼져있음)
//
//                // 블루투스를 활성화 하기 위한 다이얼로그 출력
//
//                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//
//                // 선택한 값이 onActivityResult 함수에서 콜백된다.
//
//                startActivityForResult(intent, REQUEST_ENABLE_BT);
//
//            }
//
//
//        }
//    }

//    private void sendData(String text) {
//        // 문자열에 개행문자("\n")를 추가해줍니다.
//
//        text += "\n";
//        try {
//
//            // 데이터 송신
//
//            outputStream.write(text.getBytes());
//
//            Log.d("버튼눌림","잘됨");
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//
//            case REQUEST_ENABLE_BT:
//
//                if (requestCode == RESULT_OK) { // '사용'을 눌렀을 때
//
//                    selectBluetoothDevice(); // 블루투스 디바이스 선택 함수 호출
//
//                } else { // '취소'를 눌렀을 때
//
//                    // 여기에 처리 할 코드를 작성하세요.
//
//                }
//
//                break;
//
//        }
//
//    }

//    private void selectBluetoothDevice() {
//
//        // 이미 페어링 되어있는 블루투스 기기를 찾습니다.
//
//        devices = bluetoothAdapter.getBondedDevices();
//
//        // 페어링 된 디바이스의 크기를 저장
//
//        pariedDeviceCount = devices.size();
//
//        // 페어링 되어있는 장치가 없는 경우
//
//        if (pariedDeviceCount == 0) {
//
//            // 페어링을 하기위한 함수 호출
//
//        }
//
//        // 페어링 되어있는 장치가 있는 경우
//
//        else {
//
//            // 디바이스를 선택하기 위한 다이얼로그 생성
//
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//            builder.setTitle("페어링 되어있는 블루투스 디바이스 목록");
//
//            // 페어링 된 각각의 디바이스의 이름과 주소를 저장
//
//            List<String> list = new ArrayList<>();
//
//            // 모든 디바이스의 이름을 리스트에 추가
//
//            for (BluetoothDevice bluetoothDevice : devices) {
//
//                list.add(bluetoothDevice.getName());
//
//            }
//
//            list.add("취소");
//
//
//            // List를 CharSequence 배열로 변경
//
//            final CharSequence[] charSequences = list.toArray(new CharSequence[list.size()]);
//
//            list.toArray(new CharSequence[list.size()]);
//
//
//            // 해당 아이템을 눌렀을 때 호출 되는 이벤트 리스너
//
//            builder.setItems(charSequences, new DialogInterface.OnClickListener() {
//
//                @Override
//
//                public void onClick(DialogInterface dialog, int which) {
//
//                    // 해당 디바이스와 연결하는 함수 호출
//
//                    connectDevice(charSequences[which].toString());
//
//                }
//
//            });
//
//
//            // 뒤로가기 버튼 누를 때 창이 안닫히도록 설정
//
//            builder.setCancelable(false);
//
//            // 다이얼로그 생성
//
//            AlertDialog alertDialog = builder.create();
//
//            alertDialog.show();
//
//        }
//
//    }

//    private void connectDevice(String deviceName) {
//        // 페어링 된 디바이스들을 모두 탐색
//
//        for (BluetoothDevice tempDevice : devices) {
//
//            // 사용자가 선택한 이름과 같은 디바이스로 설정하고 반복문 종료
//
//            if (deviceName.equals(tempDevice.getName())) {
//
//                bluetoothDevice = tempDevice;
//
//                break;
//
//            }
//
//        }
//
//        // UUID 생성
//
//        UUID uuid = java.util.UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
//
//        // Rfcomm 채널을 통해 블루투스 디바이스와 통신하는 소켓 생성
//
//        try {
//
//            bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
//
//            bluetoothSocket.connect();
//
//            // 데이터 송,수신 스트림을 얻어옵니다.
//
//            outputStream = bluetoothSocket.getOutputStream();
//
//            inputStream = bluetoothSocket.getInputStream();
//
//            // 데이터 수신 함수 호출
//
//            receiveData();
//
//        } catch (IOException e) {
//
//            e.printStackTrace();
//
//        }
//
//
//
//    }

//    private void receiveData() {
//        final Handler handler = new Handler();
//
//        // 데이터를 수신하기 위한 버퍼를 생성
//        Log.d("수신","잘됨");
//
//        readBufferPosition = 0;
//
//        readBuffer = new byte[1024];
//
//
//
//        // 데이터를 수신하기 위한 쓰레드 생성
//
//        workerThread = new Thread(new Runnable() {
//
//            @Override
//
//            public void run() {
//
//                while(!(Thread.currentThread().isInterrupted()))
//                {
//
//                    try {
//
//                        // 데이터를 수신했는지 확인합니다.
//
//                        int byteAvailable = inputStream.available();
//
//                        // 데이터가 수신 된 경우
//
//                        if(byteAvailable > 0) {
//
//                            // 입력 스트림에서 바이트 단위로 읽어 옵니다.
//
//                            byte[] bytes = new byte[byteAvailable];
//
//                            inputStream.read(bytes);
//
//                            // 입력 스트림 바이트를 한 바이트씩 읽어 옵니다.
//
//                            for(int i = 0; i < byteAvailable; i++) {
//
//                                byte tempByte = bytes[i];
//
//                                // 개행문자를 기준으로 받음(한줄)
//
//                                if(tempByte == '\n') {
//
//                                    // readBuffer 배열을 encodedBytes로 복사
//
//                                    byte[] encodedBytes = new byte[readBufferPosition];
//
//                                    System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
//
//                                    // 인코딩 된 바이트 배열을 문자열로 변환
//
//                                    final String text = new String(encodedBytes, "US-ASCII");
//
//                                     readBufferPosition = 0;
//
//                                    handler.post(new Runnable() {
//
//                                        @Override
//
//                                        public void run() {
//
//                                            // 텍스트 뷰에 출력
//
//                                            //textViewReceive.append(text + "\n");
//
//                                            word=text.split(",");
//                                            gongi=Integer.parseInt(word[0]);
//
//                                           mise = Double.valueOf(word[1]);
//
//
//
//
//
//                                            textViewReceive.setText(word[0]+" ppm");
//                                            textViewReceive2.setText(word[1]+"㎍/m³");
//
//
//                                            if(gongi>=200 || mise>=80.0) {
//                                                mLayout.setBackgroundColor(Color.rgb(255,0,0));
//                                            }
//
//                                            if((gongi>=200 || mise>=80.0)&& swtich==0) {
//
//                                                sendData("1");
//
//                                                new Handler().postDelayed(new Runnable() {
//                                                    @Override
//                                                    public void run() {
//                                                        sendData("3");
//                                                        swtich=1;
//                                                    }
//                                                },10000);
//                                              }
//
//
//
//
//
//
//
//                                        }
//
//                                    });
//
//                                } // 개행 문자가 아닐 경우
//                                else {
//
//                                    readBuffer[readBufferPosition++] = tempByte;
//
//                                }
//
//                            }
//
//                        }
//
//                    } catch (IOException e) {
//
//                        e.printStackTrace();
//
//                    }
//
//                    try {
//
//                        // 1초마다 받아옴
//
//                        Thread.sleep(1000);
//
//                    } catch (InterruptedException e) {
//
//                        e.printStackTrace();
//
//                    }
//
//                }
//
//            }
//
//        });
//
//        workerThread.start();
//
    }

    private void client_sendRequest_Sensors() { //센서값을 요청함

        andvo.setReqeust_type("sensors");

        if(client.isOpen()) {
            client.send(andvo.androidVO_TO_JSON().toString());
        }
    }

    private void client_sendRequest_Window() { //창문제어를 요청함
        andvo.setReqeust_type("window");

        if(client.isOpen()) {
            client.send(andvo.androidVO_TO_JSON().toString());
        }
    }

    private void client_sendRequest_disconnect() { //disconnect를 요청함
        andvo.setReqeust_type("close");

        if(client.isOpen()) {
            client.send(andvo.androidVO_TO_JSON().toString());
            client.close();
        }
    }

    private void client_connect() {// connect를 요청함
        try {
            client = new WebSocketClient(new URI("ws://192.168.100.22:8080/WebServerTest/WebSocket/WebSocket")) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    andvo.setReqeust_type("connect");
                    andvo.setMAC_ad("1234");
                    send(andvo.androidVO_TO_JSON().toString());
                }

                @Override
                public void onMessage(String message) {
                    JSONObject json = JsonUtil.string_To_Json(message);
                    try {
                        textViewReceive.setText(json.get("sensor1") + " ppm"); //화면상에 띄움.
                        textViewReceive2.setText(json.get("sensor2") + "㎍/m³");
                    } catch (Exception e) {


                    }

                }

                @Override
                public void onClose(int code, String reason, boolean remote) {

                }

                @Override
                public void onError(Exception ex) {

                }
            };
        } catch (Exception E) {


        }
        client.connect(); //연결
    }
}

