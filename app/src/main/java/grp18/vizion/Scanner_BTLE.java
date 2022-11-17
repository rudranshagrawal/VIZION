package grp18.vizion;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class Scanner_BTLE {
    private static final String TAG = Scanner_BTLE.class.getSimpleName();
    private int scanTime = 10000;
    private BluetoothLeScanner leScanner = null;
    private BluetoothAdapter bleAdapter = null;
    private Handler uiHandler = new Handler(Looper.getMainLooper());
    private ScanResultConsumer scanResultConsumer;
    private boolean scanning = false;
    private final ArrayList<BluetoothDevice> foundDeviceList = new ArrayList<>();
    private Context currentContext;
    public Scanner_BTLE(Context context) {
        this.currentContext = context;
        final BluetoothManager bluetoothManager = (BluetoothManager)
                context.getSystemService(Context.BLUETOOTH_SERVICE);

        bleAdapter = bluetoothManager.getAdapter();
        if(bleAdapter == null) {
            Log.d(TAG, "No bluetooth hardware.");
        }
        else if(!bleAdapter.isEnabled()){
            Log.d(TAG, "Blutooth is off.");
        }
    }

    @SuppressLint("MissingPermission")
    public void scan(ScanResultConsumer scanResultConsumer){
        foundDeviceList.clear();
        if (scanning){
            Log.d(TAG, "Already scanning.");
            return;
        }
        Log.d(TAG, "Scanning...");
        if(leScanner == null){
            leScanner = bleAdapter.getBluetoothLeScanner();
        }


        if(scanTime > 0) {
            uiHandler.postDelayed(()-> {
                if (scanning) {
                    Log.d(TAG, "Scanning is stopping.");
                    if(leScanner != null)
                        leScanner.stopScan(scanCallBack);
                    else
                        Log.d(TAG,"Scanner null");
                    setScanning(false);
                }
            }, scanTime);
        }

        this.scanResultConsumer = scanResultConsumer;
        if (ContextCompat.checkSelfPermission(this.currentContext, Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_DENIED)
        {
            return;
        }
        leScanner.startScan(scanCallBack);
        setScanning(true);
    }


    private final ScanCallback scanCallBack = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            if (!scanning){
                return;
            }

            if(foundDeviceList.contains(result.getDevice())) {
                // This device has already been found
                return;
            }

            // New device found, add it to the list in order to prevent duplications
            foundDeviceList.add(result.getDevice());

            if(scanResultConsumer != null) {
                uiHandler.post(() -> {
                    scanResultConsumer.onDeviceFound(result.getDevice(),
                            result.getScanRecord().getBytes(), result.getRssi());
                });
            }
        }
    };

    public boolean isScanning(){
        return scanning;
    }

    void setScanning(boolean scanning){
        this.scanning = scanning;
        uiHandler.post(() -> {
            if(scanResultConsumer == null) return;
            if(!scanning){
                scanResultConsumer.onScanningStopped();
                // Nullify the consumer in order to prevent UI crashes
                scanResultConsumer = null;
            } else{
                scanResultConsumer.onScanningStarted();
            }
        });
    }
}