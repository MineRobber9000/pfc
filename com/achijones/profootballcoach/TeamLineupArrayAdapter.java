// 
// Decompiled by Procyon v0.5.36
// 

package com.achijones.profootballcoach;

import android.widget.CompoundButton;
import android.widget.CompoundButton$OnCheckedChangeListener;
import android.widget.CheckBox;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import java.util.List;
import java.util.ArrayList;
import android.content.Context;
import PFCpack.Player;
import android.widget.ArrayAdapter;

public class TeamLineupArrayAdapter extends ArrayAdapter<Player>
{
    private final Context context;
    public final ArrayList<Player> players;
    public int playersRequired;
    public ArrayList<Player> playersSelected;
    
    public TeamLineupArrayAdapter(final Context context, final ArrayList<Player> players, final int playersRequired) {
        super(context, 2130968664, (List)players);
        this.context = context;
        this.players = players;
        this.playersRequired = playersRequired;
        this.playersSelected = new ArrayList<Player>();
        for (int i = 0; i < playersRequired; ++i) {
            this.playersSelected.add(this.players.get(i));
        }
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        inflate = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130968664, viewGroup, false);
        final TextView textView = (TextView)inflate.findViewById(2131558687);
        if (this.players.get(n).getInjury() == null) {
            textView.setText((CharSequence)this.players.get(n).getInfoForLineup());
        }
        else {
            textView.setText((CharSequence)this.players.get(n).getInfoLineupInjury());
        }
        final CheckBox checkBox = (CheckBox)inflate.findViewById(2131558688);
        if (this.playersSelected.contains(this.players.get(n))) {
            checkBox.setChecked(true);
        }
        else if (this.players.get(n).isInjured()) {
            checkBox.setEnabled(false);
            textView.setTextColor(-65536);
        }
        else {
            checkBox.setChecked(false);
        }
        checkBox.setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)new CompoundButton$OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {
                if (b) {
                    TeamLineupArrayAdapter.this.playersSelected.add(TeamLineupArrayAdapter.this.players.get(n));
                    return;
                }
                TeamLineupArrayAdapter.this.playersSelected.remove(TeamLineupArrayAdapter.this.players.get(n));
            }
        });
        return inflate;
    }
}
