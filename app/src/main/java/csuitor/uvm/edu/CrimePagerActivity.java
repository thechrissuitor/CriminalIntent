package csuitor.uvm.edu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {

    private static final String EXTRA_CRIME_ID = "csuitor.uvm.edu.crime_id"; // this line might cause problems

    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    public static Intent newInent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState); // on create
        setContentView(R.layout.activity_crime_pager); // grab the xml file

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        mViewPager = (ViewPager) findViewById(R.id.crime_view_pager);

        mCrimes = CrimeLab.get(this).getCrimes(); // get the list of crimes
        FragmentManager fragmentManager = getSupportFragmentManager();

        /* FragmentSatePagerAdaper is the agent managing the conversation with ViewPager.
        *  A FragmentManager is passed as a parameter, so the FragmentStatePagerAdapter
        *  can do its job. */
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            /* This method fetches the Crime instance for the given position in the data set.
            *  Then, it uses that Crime's ID to create and return a properly configured CrimeFragment.*/
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId()); // return an instance of a specific crime
            }

            @Override
            // Return the number of items in the array list.
            public int getCount() {
                return mCrimes.size();
            }
        });

        /*
            By default, the ViewPager shows the first item in its PagerAdapter. You can have it show the crime
            that was selected by setting the ViewPager’s current item to the index of the selected crime.
            At the end of CrimePagerActivity.onCreate(Bundle), find the index of the crime to display by
            looping through and checking each crime’s ID. When you find the Crime instance whose mId matches
            the crimeId in the intent extra, set the current item to the index of that Crime.
         */
        for(int i = 0; i < mCrimes.size(); i++){
            if(mCrimes.get(i).getId().equals(crimeId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
