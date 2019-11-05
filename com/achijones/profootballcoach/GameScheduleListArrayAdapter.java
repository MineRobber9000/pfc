// 
// Decompiled by Procyon v0.5.36
// 

package com.achijones.profootballcoach;

import android.view.View$OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import PFCpack.Team;
import android.content.Context;
import PFCpack.Game;
import android.widget.ArrayAdapter;

public class GameScheduleListArrayAdapter extends ArrayAdapter<Game>
{
    private final Context context;
    private final Game[] games;
    private final MainActivity mainAct;
    private final Team team;
    
    public GameScheduleListArrayAdapter(final Context context, final MainActivity mainAct, final Team team, final Game[] games) {
        super(context, 2130968631, (Object[])games);
        this.context = context;
        this.mainAct = mainAct;
        this.games = games;
        this.team = team;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        inflate = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130968631, viewGroup, false);
        final TextView textView = (TextView)inflate.findViewById(2131558620);
        final Button button = (Button)inflate.findViewById(2131558621);
        final Button button2 = (Button)inflate.findViewById(2131558622);
        final String[] gameSummaryStr = this.team.getGameSummaryStr(n);
        textView.setText((CharSequence)gameSummaryStr[0]);
        button.setText((CharSequence)gameSummaryStr[1]);
        button2.setText((CharSequence)gameSummaryStr[2]);
        if (this.team.gameWLSchedule.size() > n) {
            if (this.team.gameWLSchedule.get(n).equals("W")) {
                button.setBackground(this.context.getResources().getDrawable(2130837594));
            }
            else {
                button.setBackground(this.context.getResources().getDrawable(2130837592));
            }
        }
        button.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                GameScheduleListArrayAdapter.this.mainAct.showGameDialog(GameScheduleListArrayAdapter.this.games[n]);
            }
        });
        button2.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (GameScheduleListArrayAdapter.this.games[n].awayTeam == GameScheduleListArrayAdapter.this.team) {
                    GameScheduleListArrayAdapter.this.mainAct.examineTeam(GameScheduleListArrayAdapter.this.games[n].homeTeam.name);
                    return;
                }
                GameScheduleListArrayAdapter.this.mainAct.examineTeam(GameScheduleListArrayAdapter.this.games[n].awayTeam.name);
            }
        });
        return inflate;
    }
}
