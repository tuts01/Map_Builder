package com.example.mapbuilder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link //Fragment} subclass.
 * Use the {@link //MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment {

    private class MapViewHolder extends RecyclerView.ViewHolder {
        public MapViewHolder(LayoutInflater li, ViewGroup parent) {
            super(li.inflate(R.layout.grid_cell, parent, false));

            int size = parent.getMeasuredHeight() / MapData.HEIGHT + 1;
            ViewGroup.LayoutParams lp = itemView.getLayoutParams();
            lp.width = size;
            lp.height = size;


        }

        void bind(MapElement element)
        {
            ImageView iv;

            iv = itemView.findViewById(R.id.topLeft);
            iv.setImageResource(element.getNorthWest());

            iv = itemView.findViewById(R.id.topRight);
            iv.setImageResource(element.getNorthEast());

            iv = itemView.findViewById(R.id.bottomLeft);
            iv.setImageResource(element.getSouthWest());

            iv = itemView.findViewById(R.id.bottomRight);
            iv.setImageResource(element.getSouthEast());
        }
    }

    private class MapAdapter extends RecyclerView.Adapter<MapViewHolder> {
        private List<MapElement> data;

        public MapAdapter(List<MapElement> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public MapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater li = LayoutInflater.from(getActivity());

            return new MapViewHolder(li, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MapViewHolder vh, int position) {
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

    public MapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Map.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
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
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        RecyclerView rv = view.findViewById(R.id.mapRecyclerView);
        rv.setLayoutManager(new GridLayoutManager(getActivity(), MapData.HEIGHT, GridLayoutManager.HORIZONTAL, false));
        List<MapElement> data = getMapData();
        MapAdapter adapter = new MapAdapter(data);
        rv.setAdapter(adapter);

        return view;
    }

    private ArrayList<MapElement> getMapData()
    {
        MapData mapData = MapData.get();
        ArrayList<MapElement> mapArray = new ArrayList<MapElement>(MapData.WIDTH*MapData.HEIGHT);
        for(int i = 0; i < MapData.WIDTH; i++){
            for(int j = 0; j < MapData.HEIGHT; j++){
                mapArray.add(mapData.get(j,i));
            }
        }
        return mapArray;
    }
}