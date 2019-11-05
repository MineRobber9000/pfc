// 
// Decompiled by Procyon v0.5.36
// 

package com.achijones.profootballcoach;

import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import java.util.List;
import java.util.ArrayList;
import android.content.Context;
import android.widget.ArrayAdapter;

public class NewsStoriesListArrayAdapter extends ArrayAdapter<String>
{
    private final Context context;
    private final ArrayList<String> values;
    
    public NewsStoriesListArrayAdapter(final Context context, final ArrayList<String> values) {
        super(context, 2130968639, (List)values);
        this.context = context;
        this.values = values;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        inflate = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130968639, viewGroup, false);
        final TextView textView = (TextView)inflate.findViewById(2131558648);
        final TextView textView2 = (TextView)inflate.findViewById(2131558649);
        final String[] split = this.values.get(n).split(">");
        textView.setText((CharSequence)split[0]);
        textView2.setText((CharSequence)split[1]);
        return inflate;
    }
}
