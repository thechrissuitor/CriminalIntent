package csuitor.uvm.edu;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
 * This class is a singleton. Only one will exist at a time.
 * This class will be a list of Crime objects.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;

    private List<Crime> mCrimes;

    private static final String TAG = "CrimeLab";

    /*
     * This method, get, will check if CrimeLab already exist.
     * If CrimeLab does exist, then it will return the existing CrimeLab.
     * If CrimeLab does not exist, it will make a new one.
     */
    public static CrimeLab get(Context context){
        if(sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    /*
     * CONSTRUCTOR
     * Populate the mCrimes list with 100 arbitrarily generated crimes.
     */
    private CrimeLab(Context context){
        mCrimes = new ArrayList<>();
        for (int i=0; i<100; i++){
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0); // Every other crime will be solved
            mCrimes.add(crime);
            Log.d(TAG, "I made it!");
        }
    }

    // Return the mCrimes list
    public List<Crime> getCrimes(){
        return mCrimes;
    }

    /*
     * @param: UUID id = the id of the crime you are looking
     * @return: return the crime that has the id of the param
     */
    public Crime getCrime(UUID id){
        for(Crime crime : mCrimes){
            if(crime.getId().equals(id)){
                return crime;
            }
        }
        return null;
    }
}
