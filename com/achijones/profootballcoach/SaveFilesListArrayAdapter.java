// 
// Decompiled by Procyon v0.5.36
// 

package com.achijones.profootballcoach;

import android.widget.TextView;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.content.Context;
import android.widget.ArrayAdapter;

public class SaveFilesListArrayAdapter extends ArrayAdapter<String>
{
    private final Context context;
    private final String[] values;
    
    public SaveFilesListArrayAdapter(final Context context, final String[] values) {
        super(context, 2130968609, (Object[])values);
        this.context = context;
        this.values = values;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        inflate = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130968609, viewGroup, false);
        ((Button)inflate.findViewById(2131558555)).setVisibility(8);
        ((Button)inflate.findViewById(2131558556)).setVisibility(8);
        final String[] split = this.values[n].split(">");
        final TextView textView = (TextView)inflate.findViewById(2131558552);
        textView.setPadding(5, 0, 5, 0);
        textView.setText((CharSequence)split[0]);
        textView.setClickable(false);
        return inflate;
    }
}
