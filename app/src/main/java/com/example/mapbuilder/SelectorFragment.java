package com.example.mapbuilder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {//@link Fragment} subclass.
 * Use the {//@link SelectorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectorFragment extends Fragment {

    private class StructureViewHolder extends RecyclerView.ViewHolder {
        public StructureViewHolder(LayoutInflater li, ViewGroup parent) {
            super(li.inflate(R.layout.list_selection, parent, false));
        }

        void bind(Structure structure) {

        }
    }

    private class StructureAdapter extends RecyclerView.Adapter<StructureViewHolder> {
        private List<Structure> data;

        public StructureAdapter(List<Structure> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public StructureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater li = LayoutInflater.from(getActivity());

            return new StructureViewHolder(li, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull StructureViewHolder vh, int position) {
            vh.bind(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SelectorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Selector.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectorFragment newInstance(String param1, String param2) {
        SelectorFragment fragment = new SelectorFragment();
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
        View view = inflater.inflate(R.layout.fragment_selector, container, false);

        RecyclerView rv = view.findViewById(R.id.mapRecyclerView);
        rv.setLayoutManager(new LinearLayoutManager(getActivity());
        List<Structure> data = getStructureData();
        SelectorFragment.StructureAdapter adapter = new SelectorFragment.StructureAdapter(data);
        rv.setAdapter(adapter);

        return view;
    }

    private ArrayList<Structure> getStructureData()
    {
        StructureData structures = StructureData.get();
        ArrayList<Structure> structureArray = new ArrayList<Structure>(structures.size());

        for(int i = 0; i < structures.size(); i++){
            structureArray.add(structures.get(i));
        }

        return structureArray;
    }
}