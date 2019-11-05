package com.achijones.profootballcoach;

import PFCpack.Game;
import PFCpack.Team;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class GameScheduleListArrayAdapter
  extends ArrayAdapter<Game>
{
  private final Context context;
  private final Game[] games;
  private final MainActivity mainAct;
  private final Team team;
  
  public GameScheduleListArrayAdapter(Context paramContext, MainActivity paramMainActivity, Team paramTeam, Game[] paramArrayOfGame)
  {
    super(paramContext, 2130968631, paramArrayOfGame);
    context = paramContext;
    mainAct = paramMainActivity;
    games = paramArrayOfGame;
    team = paramTeam;
  }
  
  public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramView = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130968631, paramViewGroup, false);
    paramViewGroup = (TextView)paramView.findViewById(2131558620);
    Button localButton1 = (Button)paramView.findViewById(2131558621);
    Button localButton2 = (Button)paramView.findViewById(2131558622);
    String[] arrayOfString = team.getGameSummaryStr(paramInt);
    paramViewGroup.setText(arrayOfString[0]);
    localButton1.setText(arrayOfString[1]);
    localButton2.setText(arrayOfString[2]);
    if (team.gameWLSchedule.size() > paramInt)
    {
      if (!((String)team.gameWLSchedule.get(paramInt)).equals("W")) {
        break label171;
      }
      localButton1.setBackground(context.getResources().getDrawable(2130837594));
    }
    for (;;)
    {
      localButton1.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          mainAct.showGameDialog(games[paramInt]);
        }
      });
      localButton2.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (games[paramInt].awayTeam == team)
          {
            mainAct.examineTeam(games[paramInt].homeTeam.name);
            return;
          }
          mainAct.examineTeam(games[paramInt].awayTeam.name);
        }
      });
      return paramView;
      label171:
      localButton1.setBackground(context.getResources().getDrawable(2130837592));
    }
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.GameScheduleListArrayAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */