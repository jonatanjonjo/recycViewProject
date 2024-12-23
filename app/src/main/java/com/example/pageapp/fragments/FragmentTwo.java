package com.example.pageapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pageapp.R;
import com.example.pageapp.activitys.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTwo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTwo extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentTwo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTwo.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTwo newInstance(String param1, String param2) {
        FragmentTwo fragment = new FragmentTwo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_two, container, false);
        Button signUpButton=view.findViewById(R.id.btnSignup);
        EditText passwordField=view.findViewById(R.id.etPassword);
        EditText confirmPasswordField=view.findViewById(R.id.etConfirmPassword);
        String specialCharactersRegex = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*";
        signUpButton.setOnClickListener(v -> {
            String password = passwordField.getText().toString();
            String confirmPassword = confirmPasswordField.getText().toString();

            if (password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(getContext(), "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                confirmPasswordField.setError("Passwords do not match");
            }else if (password.length()<6 || !password.matches(specialCharactersRegex)){
                Toast.makeText(getContext(), "Password has to be longer than 6 chars and has to have at least on special Characters Regex like !@#", Toast.LENGTH_SHORT).show();
            } else {
                // Proceed with sign-up logic
                Toast.makeText(getContext(), "Sign-up successful!", Toast.LENGTH_SHORT).show();
                MainActivity mainactivity=(MainActivity)getActivity();
                mainactivity.register();
                mainactivity.addData();
                Navigation.findNavController(view).navigate(R.id.action_fragmentTwo_to_fragmentOne);
            }
        });
        return view;
    }
}