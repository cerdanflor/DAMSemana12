package com.dam.prueba;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

public class NuevaNotaDialogFragment extends DialogFragment {

    private NuevaNotaDialogViewModel mViewModel;
    private View view;
    private EditText etTitulo, etContenido;
    private RadioGroup rgColor;
    private Switch swNotaFavorita;

    public static NuevaNotaDialogFragment newInstance() {
        return new NuevaNotaDialogFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NuevaNotaDialogViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Nueva Nota");
        builder.setMessage("Introduzca los datos de la nueva nota")
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String titulo = etTitulo.getText().toString();
                        String contenido = etContenido.getText().toString();
                        String color = "azul";
                        switch (rgColor.getCheckedRadioButtonId()){
                            case R.id.radioButtonColorRojo:
                                color = "rojo";
                                break;
                            case R.id.radioButtonColorVerde:
                                color = "verde";
                                break;
                        }
                        boolean esFavorita = swNotaFavorita.isChecked();

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.nueva_nota_dialog_fragment,null);

        etTitulo = view.findViewById(R.id.editTextTitulo);
        etContenido = view.findViewById(R.id.editTextContenido);
        rgColor = view.findViewById(R.id.radioGroupColor);
        swNotaFavorita = view.findViewById(R.id.switchNotaFavorita);

        builder.setView(view);
        // Create the AlertDialog object and return it
        return builder.create();
    }

}