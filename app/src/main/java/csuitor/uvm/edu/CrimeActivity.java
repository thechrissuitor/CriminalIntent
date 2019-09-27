package csuitor.uvm.edu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    public static final String EXTRA_CRIME_ID = "csuitor.uvm.edu.criminalintent.crime_id"; //TODO: This may be a problem.

    /* CrimeFragment knows which crime to display by passing the crimeID as an Intent
    * extra when CrimeActivity is started.*/
    public static Intent newIntent(Context packageContent, UUID crimeId){
        Intent intent = new Intent(packageContent, csuitor.uvm.edu.CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    // create a fragment using SingleFragmentActivity.java
    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }
}
