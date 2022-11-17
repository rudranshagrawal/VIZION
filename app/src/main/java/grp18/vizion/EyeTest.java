package grp18.vizion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;


public class EyeTest extends AppCompatActivity {

    int[] bluetoothData = {1, 1, 1, 1, 1, 1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eye_test);

//        for (int data : bluetoothData)
//        {
//            if (data == 1)
//            {
//                this.processBluetoothData();
//            }
//        }




    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        ImageView acuity_image = (ImageView) findViewById(R.id.imageView2);
        acuity_image.setRotation(90);

        return true;
    }

    private void processBluetoothData() {
        ImageView acuity_image = (ImageView) findViewById(R.id.imageView2);
        acuity_image.setRotation(90);
    }

}