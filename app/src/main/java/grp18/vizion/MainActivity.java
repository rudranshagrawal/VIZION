package grp18.vizion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_ENABLE_BT = 1;
    ArrayList<String> arrayList = new ArrayList<>();
    private static final String TAG = MainActivity.class.getSimpleName();

//    private Scanner_BTLE bleScanner;
    private Button buttonScan;
//    private BluetoothDevice device;
//    private byte[] scanRecord;
//    private int rssi;

    //    For scanLeDevice()
//    private BluetoothManager bluetoothManager = getSystemService(BluetoothManager.class);
//    private BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
//    private BluetoothLeScanner bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
//    private boolean scanning;
//    private Handler handler = new Handler(Looper.getMainLooper());

//    int number_of_clicks = 0;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        bleScanner = new Scanner_BTLE(getApplicationContext());


//        BluetoothManager bluetoothManager = getSystemService(BluetoothManager.class);
//        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
//        if (bluetoothAdapter == null) {
//            // Device doesn't support Bluetooth
//        }
//
//        if (!bluetoothAdapter.isEnabled()) {
//            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED)
//            {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
//                {
//                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 2);
//                    return;
//                }
//            }
//            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
//        }

//        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
//
//        if (pairedDevices.size() > 0) {
//            // There are paired devices. Get the name and address of each paired device.
//            for (BluetoothDevice device : pairedDevices) {
//                String deviceName = device.getName();
//                String deviceHardwareAddress = device.getAddress(); // MAC address
//                arrayList.add(deviceName);
//            }
//        }
//
//
        // Use this check to determine whether BLE is supported on the device. Then
        // you can selectively disable BLE-related features.

        BluetoothManager bluetoothManager = getSystemService(BluetoothManager.class);
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
        }

        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 2);
//                    return;
//                }
//            }
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        Log.d(TAG, pairedDevices.toString());

        buttonScan = findViewById(R.id.scan_BLE_button);

        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (pairedDevices.size() > 0) {
//                    // There are paired devices. Get the name and address of each paired device.
//                    for (BluetoothDevice device : pairedDevices) {
//                        String deviceName = device.getName();
//                        String deviceHardwareAddress = device.getAddress(); // MAC address
//                        arrayList.add(deviceName);

//                    }
//                }

            }
        });


        Button startButton = (Button) findViewById(R.id.button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EyeTest.class);
                startActivity(intent);

            }
        });

    }
}

//    public void onClick(View view)
//    {
//        TextView text = (TextView) findViewById(R.id.textView2);
//        number_of_clicks ++;
//        text.setText("Test Has Been Started " + number_of_clicks + " Times");
//
//    }

//    public boolean connect(final String address) {
//        if (bluetoothAdapter == null || address == null) {
//            Log.w(TAG, "BluetoothAdapter not initialized or unspecified address.");
//            return false;
//        }
//
//        try {
//            final BluetoothDevice device = bluetoothAdapter.getRemoteDevice(address);
//        } catch (IllegalArgumentException exception) {
//            Log.w(TAG, "Device not found with provided address.");
//            return false;
//        }
//        // connect to the GATT server on the device
//    }




    // Stops scanning after 10 seconds.
//    private static final long SCAN_PERIOD = 10000;
//
//    private void scanLeDevice() {
//        if (!scanning) {
//            // Stops scanning after a predefined scan period.
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    scanning = false;
//                    bluetoothLeScanner.stopScan(leScanCallback);
//                }
//            }, SCAN_PERIOD);
//
//            scanning = true;
//            bluetoothLeScanner.startScan(leScanCallback);
//        } else {
//            scanning = false;
//            bluetoothLeScanner.stopScan(leScanCallback);
//        }
//    }
//
//
//}


