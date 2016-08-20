package elsuper.david.com.googleservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class MainActivity extends AppCompatActivity {

    private static final int PLACE_PICKER_REQUEST = 1;
    private Button btnMaps;
    private Button btnAdMob;
    private Button btnLocationPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMaps = (Button)findViewById(R.id.btnMaps);
        btnAdMob = (Button)findViewById(R.id.btnAdMob);
        btnLocationPicker = (Button)findViewById(R.id.btnLocationPicker);

        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MapsActivity.class));
            }
        });

        btnAdMob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainAdMobActivity.class));
            }
        });

        btnLocationPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    startActivityForResult(new PlacePicker.IntentBuilder().build(MainActivity.this),PLACE_PICKER_REQUEST);
                }
                catch (GooglePlayServicesRepairableException e){
                    e.printStackTrace();
                }
                catch (GooglePlayServicesNotAvailableException e){
                    e.printStackTrace();
                }

                //Toast.makeText(MainActivity.this,"hola2",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PLACE_PICKER_REQUEST){
            if(data != null){
                Place place = PlacePicker.getPlace(MainActivity.this,data);
                Toast.makeText(MainActivity.this,place.getAddress(),Toast.LENGTH_SHORT).show();
            }
        }
    }
}
