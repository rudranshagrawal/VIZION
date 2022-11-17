package grp18.vizion;

import android.bluetooth.BluetoothDevice;

public interface ScanResultConsumer {

    public void onDeviceFound(BluetoothDevice device, byte[] scanRecord, int rssi);
    public void onScanningStarted();
    public void onScanningStopped();
}
