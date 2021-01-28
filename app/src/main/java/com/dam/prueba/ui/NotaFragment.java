package com.dam.prueba.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dam.prueba.R;
import com.dam.prueba.db.entity.NotaEntity;

import java.util.ArrayList;
import java.util.List;

public class NotaFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;

    private List<NotaEntity> notaEntityList;
    private MyNotaRecyclerViewAdapter adapterNotas;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NotaFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NotaFragment newInstance(int columnCount) {
        NotaFragment fragment = new NotaFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nota_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (view.getId() == R.id.listPortrait) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                float dpwidth = displayMetrics.widthPixels / displayMetrics.density;
                int numeroColumnas= (int) (dpwidth / 180);


                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(numeroColumnas, StaggeredGridLayoutManager.VERTICAL));
            }
            notaEntityList = new ArrayList<>();
            notaEntityList.add(new NotaEntity("Temas de Examen","Constraint Layout, Drawable, Mobile Navigation", true, android.R.color.holo_blue_light));
            notaEntityList.add(new NotaEntity("Importante","Matricula onLine 20 y 21 de marzo, por la página de la UNTELS, NO OLVIDAR",false, android.R.color.holo_green_light));
            notaEntityList.add(new NotaEntity("Cumpleaños","Se viene el cumpleaño de Maritza Vega, será SORPRESA, la fiesta será por meet (Para Italo)", true, android.R.color.holo_orange_light));
            notaEntityList.add(new NotaEntity("The standard Lorem Ipsum passage","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum", true, android.R.color.holo_orange_light));
            notaEntityList.add(new NotaEntity("Section 1.10.32 of de Finibus Bonorum et Malorum, written by Cicero in 45 BC","Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?", true, android.R.color.holo_blue_light));
            notaEntityList.add(new NotaEntity("1914 translation by H. Rackham","But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure",false, android.R.color.holo_green_light));
            adapterNotas = new MyNotaRecyclerViewAdapter(notaEntityList, getActivity());
            recyclerView.setAdapter(adapterNotas);
        }
        return view;

    }
}
