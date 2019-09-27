package csuitor.uvm.edu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class CrimeActivity extends SingleFragmentActivity {

    // create a fragment using SingleFragmentActivity.java
    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }
}
