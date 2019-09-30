package csuitor.uvm.edu;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import static android.widget.CompoundButton.*;

import java.util.UUID;

public class CrimeFragment extends Fragment {
    private Crime mCrime;
    private EditText mTitleField; // a text field that the user can edit
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId); // use the crime ide to retrieve the crime
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO: ask Prof. Hibbler what it means to "inflate the fragment's view"
        // TODO: also, ask Prof. Hibbler what the book means by, "the second pararment is your view's parent"
        /*
         * @param: R.layout.fragment_crime = find the fragment with the corresponding id
         * @param: container = view's parent
         * @param: false = tells the layout inflater whether to add the inflated view to the view's parent.
         *         "false" was passed because the view will be added to the activity's code.
         * The line of code below inflates the fragment's view.
         */
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        mTitleField = (EditText) v.findViewById(R.id.crime_title); // find an EditText with the corresponding id
        mTitleField.setText(mCrime.getTitle()); // fill the crime's title with what it's been set as
        // make a listener
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }
            //  call toString() on the CharSequence that is the user’s input. Set the returned string as the Crime's title
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved); // find a checkbox with the id "crime_solved"
        mSolvedCheckBox.setChecked(mCrime.isSolved()); // set it as solved/unsolved if it's already been set
        // set a listener for checkboxes
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                mCrime.setSolved(isChecked); // update the Crime class boolean, mSolved
            }
        });

        mDateButton = (Button) v.findViewById(R.id.crime_date); // find a button with the correct id
        mDateButton.setText(mCrime.getDate().toString()); // change the text to the current date
        // show a DatePickerFragment when the date button is pressed
        mDateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.show(manager, DIALOG_DATE);
            }
        });

        return v;
    }

    /* This method creates a bundle to save states for a fragment. */
    public static CrimeFragment newInstance(UUID crimeId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

}