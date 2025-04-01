package com.jivan.todo;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class InputDialog extends DialogFragment {

    private EditText etTodoInput;
    private InputDialogListener listener;

    public interface InputDialogListener {
        void onInputSubmitted(String input);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (InputDialogListener) context; // Ensure activity implements interface
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement InputDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.fragment_input_dialog, null);
        etTodoInput = dialogView.findViewById(R.id.etTodoInput);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setView(dialogView)
                .setPositiveButton("Add Todo", (dialog, which) -> {
                    String input = etTodoInput.getText().toString();
                    listener.onInputSubmitted(input); // Send data to activity
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        TextView title = new TextView(getActivity());
// You Can Customise your Title here
        title.setText("So, What's Next?");
        title.setBackgroundColor(Color.DKGRAY);
        title.setPadding(10, 16, 10, 16);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(20);

        builder.setCustomTitle(title);
        return builder.create();
    }
}
