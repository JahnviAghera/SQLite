package com.jhaghera.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class RecordAdapter extends ArrayAdapter<DataModel> {
    private Context context;
    private List<DataModel> records;

    public RecordAdapter(Context context, List<DataModel> records) {
        super(context, R.layout.list_item, records);
        this.context = context;
        this.records = records;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, null);

        TextView nameTextView = view.findViewById(R.id.nameTextView);
        TextView idTextView = view.findViewById(R.id.idTextView);
        TextView ageTextView = view.findViewById(R.id.ageTextView);

        DataModel record = records.get(position);

        nameTextView.setText(record.getF_name());
        ageTextView.setText(String.valueOf(record.getL_name()));
        idTextView.setText(String.valueOf(record.getId()));

        return view;
    }
}
