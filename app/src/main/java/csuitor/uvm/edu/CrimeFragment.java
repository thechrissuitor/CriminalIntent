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

public class CrimeFragment extends Fragment {
    private Crime mCrime;
    private EditText mTitleField; // a text field that the user can edit
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();
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
        // set a listener for checkboxes
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                mCrime.setSolved(isChecked); // update the Crime class boolean, mSolved
            }
        });

        mDateButton = (Button) v.findViewById(R.id.crime_date); // find a button with the correct id
        mDateButton.setText(mCrime.getDate().toString()); // change the text to the current date
        mDateButton.setEnabled(false); // disable the button so as to not confuse the user that it has any function when pressed

        return v;
    }

}