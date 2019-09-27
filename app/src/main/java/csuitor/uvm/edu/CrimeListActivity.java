package csuitor.uvm.edu;

import androidx.fragment.app.Fragment;

/*
 * This is a controller class.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    // this method creates a new fragment
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
