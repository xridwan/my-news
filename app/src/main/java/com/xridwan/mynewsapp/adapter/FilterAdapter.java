package com.xridwan.mynewsapp.adapter;

import android.widget.Filter;

import com.xridwan.mynewsapp.models.SourceList;

import java.util.ArrayList;
import java.util.List;

public class FilterAdapter extends Filter {

    private final SourceListAdapter adapter;
    private final List<SourceList> filterList;

    public FilterAdapter(SourceListAdapter adapter, List<SourceList> filterList) {
        this.adapter = adapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();

        if (constraint != null && constraint.length() > 0) {
            constraint = constraint.toString().toUpperCase();

            List<SourceList> filterSourceList = new ArrayList<>();
            for (int i = 0; i < filterList.size(); i++) {
                if (filterList.get(i).getName().toUpperCase().contains(constraint)) {
                    filterSourceList.add(filterList.get(i));
                }
            }
            results.count = filterList.size();
            results.values = filterSourceList;

        } else {
            results.count = filterList.size();
            results.values = filterList;

        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.sourceLists = (ArrayList<SourceList>) results.values;
        adapter.notifyDataSetChanged();
    }
}
