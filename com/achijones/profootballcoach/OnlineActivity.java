package com.achijones.profootballcoach;

import PFCpack.Game;
import PFCpack.Team;
import PFCpack.TeamStrategy;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class OnlineActivity
  extends AppCompatActivity
{
  Game currGame;
  int currTab;
  ArrayAdapter<String> dataAdapterTeam;
  Spinner examineTeamSpinner;
  LinearLayout examineTeamView;
  ExpandableListView expListPlayerStats;
  boolean gettingRandTeam;
  boolean gettingUserTeam;
  ListView mainList;
  Team oppTeam;
  Button playWeekButton;
  List<String> teamList;
  Team userTeam;
  String userTeamStr;
  
  private void exitToMainMeu()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage("Are you sure you want to return to main menu? Progress will be saved.").setPositiveButton("Yes, Exit", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = new Intent(OnlineActivity.this, Home.class);
        startActivity(paramAnonymousDialogInterface);
      }
    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    localBuilder.create().show();
  }
  
  private void showTeamStrategyDialog()
  {
    final Object localObject1 = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject1).setTitle("Default Team Strategy").setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        try
        {
          paramAnonymousDialogInterface = new JSONObject();
          paramAnonymousDialogInterface.put("name", userTeam.name);
          paramAnonymousDialogInterface.put("off_strat", userTeam.teamStratOffNum);
          paramAnonymousDialogInterface.put("def_strat", userTeam.teamStratDefNum);
          new OnlineActivity.UpdateTeamStratsOnline(OnlineActivity.this).execute(new String[] { paramAnonymousDialogInterface.toString() });
          return;
        }
        catch (Exception paramAnonymousDialogInterface)
        {
          paramAnonymousDialogInterface.printStackTrace();
        }
      }
    }).setView(getLayoutInflater().inflate(2130968668, null));
    Object localObject3 = ((AlertDialog.Builder)localObject1).create();
    ((AlertDialog)localObject3).show();
    final TeamStrategy[] arrayOfTeamStrategy = userTeam.getTeamStrategiesOff();
    localObject1 = userTeam.getTeamStrategiesDef();
    int j = 0;
    int k = 0;
    Object localObject4 = new String[arrayOfTeamStrategy.length];
    int i = 0;
    while (i < arrayOfTeamStrategy.length)
    {
      localObject4[i] = arrayOfTeamStrategy[i].getStratName();
      if (localObject4[i].equals(userTeam.teamStratOff.getStratName())) {
        j = i;
      }
      i += 1;
    }
    Object localObject2 = new String[localObject1.length];
    i = 0;
    while (i < localObject1.length)
    {
      localObject2[i] = localObject1[i].getStratName();
      if (localObject2[i].equals(userTeam.teamStratDef.getStratName())) {
        k = i;
      }
      i += 1;
    }
    final TextView localTextView2 = (TextView)((AlertDialog)localObject3).findViewById(2131558700);
    final TextView localTextView1 = (TextView)((AlertDialog)localObject3).findViewById(2131558702);
    Spinner localSpinner = (Spinner)((AlertDialog)localObject3).findViewById(2131558699);
    localObject4 = new ArrayAdapter(this, 17367048, (Object[])localObject4);
    ((ArrayAdapter)localObject4).setDropDownViewResource(17367049);
    localSpinner.setAdapter((SpinnerAdapter)localObject4);
    localSpinner.setSelection(j);
    localSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        localTextView2.setText(arrayOfTeamStrategy[paramAnonymousInt].getStratDescription());
        userTeam.teamStratOff = arrayOfTeamStrategy[paramAnonymousInt];
        userTeam.teamStratOffNum = paramAnonymousInt;
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
    localObject3 = (Spinner)((AlertDialog)localObject3).findViewById(2131558701);
    localObject2 = new ArrayAdapter(this, 17367048, (Object[])localObject2);
    ((ArrayAdapter)localObject2).setDropDownViewResource(17367049);
    ((Spinner)localObject3).setAdapter((SpinnerAdapter)localObject2);
    ((Spinner)localObject3).setSelection(k);
    ((Spinner)localObject3).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        localTextView1.setText(localObject1[paramAnonymousInt].getStratDescription());
        userTeam.teamStratDef = localObject1[paramAnonymousInt];
        userTeam.teamStratDefNum = paramAnonymousInt;
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
  }
  
  private void updatePlayerStats(boolean paramBoolean)
  {
    mainList.setVisibility(8);
    expListPlayerStats.setVisibility(0);
    Object localObject;
    if (oppTeam != null)
    {
      examineTeamView.setVisibility(0);
      if ((!paramBoolean) && (oppTeam != null)) {
        break label92;
      }
      localObject = userTeam.getOnlinePlayerStatsExpandListStr();
    }
    for (Map localMap = userTeam.getOnlinePlayerStatsExpandListMap((List)localObject);; localMap = oppTeam.getOnlinePlayerStatsExpandListMap((List)localObject))
    {
      localObject = new OnlineExpandableListAdapterPlayerStats(this, (List)localObject, localMap);
      expListPlayerStats.setAdapter((ExpandableListAdapter)localObject);
      return;
      examineTeamView.setVisibility(8);
      break;
      label92:
      localObject = oppTeam.getOnlinePlayerStatsExpandListStr();
    }
  }
  
  private void updateSchedule()
  {
    mainList.setVisibility(0);
    expListPlayerStats.setVisibility(8);
    examineTeamView.setVisibility(8);
    Game[] arrayOfGame = new Game[userTeam.gameSchedule.size()];
    int i = 0;
    while (i < arrayOfGame.length)
    {
      arrayOfGame[i] = ((Game)userTeam.gameSchedule.get(i));
      i += 1;
    }
    mainList.setAdapter(new OnlineGameScheduleListArrayAdapter(this, this, userTeam, arrayOfGame));
  }
  
  private void updateTeamStats()
  {
    mainList.setVisibility(0);
    expListPlayerStats.setVisibility(8);
    examineTeamView.setVisibility(8);
    String[] arrayOfString = new String[15];
    if (oppTeam == null)
    {
      arrayOfString[0] = ("NONE,Team," + userTeam.abbr);
      arrayOfString[1] = ("--,ELO," + userTeam.teamELO);
      arrayOfString[2] = ("--,Wins," + userTeam.wins);
      arrayOfString[3] = ("--,Losses," + userTeam.losses);
      arrayOfString[4] = ("--,Points," + userTeam.getPPG());
      arrayOfString[5] = ("--,Opp Points," + userTeam.getOPPG());
      arrayOfString[6] = ("--,Yards," + userTeam.getYPG());
      arrayOfString[7] = ("--,Opp Yards," + userTeam.getOYPG());
      arrayOfString[8] = ("--,Pass Yards," + userTeam.getPYPG());
      arrayOfString[9] = ("--,Rush Yards," + userTeam.getRYPG());
      arrayOfString[10] = ("--,Opp Pass Yards," + userTeam.getOPYPG());
      arrayOfString[11] = ("--,Opp Rush Yards," + userTeam.getORYPG());
      arrayOfString[12] = ("--,TO Diff," + userTeam.getTODiff());
      arrayOfString[13] = ("--,Off Talent," + userTeam.getOffTalent());
      arrayOfString[14] = ("--,Def Talent," + userTeam.getDefTalent());
    }
    for (;;)
    {
      mainList.setAdapter(new OnlineTeamStatsListArrayAdapter(this, arrayOfString));
      return;
      arrayOfString[0] = (oppTeam.abbr + ",Team," + userTeam.abbr);
      arrayOfString[1] = (oppTeam.teamELO + ",ELO," + userTeam.teamELO);
      arrayOfString[2] = (oppTeam.wins + ",Wins," + userTeam.wins);
      arrayOfString[3] = (oppTeam.losses + ",Losses," + userTeam.losses);
      arrayOfString[4] = (oppTeam.getPPG() + ",Points," + userTeam.getPPG());
      arrayOfString[5] = (oppTeam.getOPPG() + ",Opp Points," + userTeam.getOPPG());
      arrayOfString[6] = (oppTeam.getYPG() + ",Yards," + userTeam.getYPG());
      arrayOfString[7] = (oppTeam.getOYPG() + ",Opp Yards," + userTeam.getOYPG());
      arrayOfString[8] = (oppTeam.getPYPG() + ",Pass Yards," + userTeam.getPYPG());
      arrayOfString[9] = (oppTeam.getRYPG() + ",Rush Yards," + userTeam.getRYPG());
      arrayOfString[10] = (oppTeam.getOPYPG() + ",Opp Pass Yards," + userTeam.getOPYPG());
      arrayOfString[11] = (oppTeam.getORYPG() + ",Opp Rush Yards," + userTeam.getORYPG());
      arrayOfString[12] = (oppTeam.getTODiff() + ",TO Diff," + userTeam.getTODiff());
      arrayOfString[13] = (oppTeam.getOffTalent() + ",Off Talent," + userTeam.getOffTalent());
      arrayOfString[14] = (oppTeam.getDefTalent() + ",Def Talent," + userTeam.getDefTalent());
    }
  }
  
  public void customOnlineGame()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle("Opponent Team Name:");
    final EditText localEditText = new EditText(this);
    localEditText.setInputType(1);
    localBuilder.setView(localEditText);
    localBuilder.setPositiveButton("Schedule", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (localEditText.getText().toString().trim().equals(userTeam.name))
        {
          Toast.makeText(OnlineActivity.this, "Can't schedule your own team!", 0).show();
          return;
        }
        try
        {
          gettingUserTeam = false;
          gettingRandTeam = false;
          paramAnonymousDialogInterface = new JSONObject();
          paramAnonymousDialogInterface.put("name", localEditText.getText().toString().trim());
          new OnlineActivity.GetTeamOnline(OnlineActivity.this).execute(new String[] { paramAnonymousDialogInterface.toString() });
          playWeekButton.setText("Play Custom Game");
          return;
        }
        catch (Exception paramAnonymousDialogInterface)
        {
          paramAnonymousDialogInterface.printStackTrace();
        }
      }
    });
    localBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    });
    localBuilder.show();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968606);
    setSupportActionBar((Toolbar)findViewById(2131558542));
    mainList = ((ListView)findViewById(2131558575));
    expListPlayerStats = ((ExpandableListView)findViewById(2131558576));
    examineTeamSpinner = ((Spinner)findViewById(2131558569));
    examineTeamView = ((LinearLayout)findViewById(2131558585));
    paramBundle = getIntent().getExtras();
    if (paramBundle != null)
    {
      paramBundle = paramBundle.getString("ONLINE_TEAM");
      gettingUserTeam = true;
      gettingRandTeam = false;
    }
    try
    {
      Object localObject = new JSONObject();
      ((JSONObject)localObject).put("name", paramBundle);
      new GetTeamOnline().execute(new String[] { ((JSONObject)localObject).toString() });
      paramBundle = (Button)findViewById(2131558571);
      localObject = (Button)findViewById(2131558572);
      Button localButton = (Button)findViewById(2131558573);
      paramBundle.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          currTab = 0;
          OnlineActivity.this.updateTeamStats();
        }
      });
      ((Button)localObject).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          currTab = 1;
          examineTeamSpinner.setSelection(0);
          OnlineActivity.this.updatePlayerStats(true);
        }
      });
      localButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          currTab = 2;
          OnlineActivity.this.updateSchedule();
        }
      });
      playWeekButton = ((Button)findViewById(2131558586));
      playWeekButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (!playWeekButton.getText().toString().equals("Schedule Ranked Game"))
          {
            playGame();
            playWeekButton.setText("Schedule Ranked Game");
            return;
          }
          gettingUserTeam = false;
          gettingRandTeam = true;
          try
          {
            paramAnonymousView = new JSONObject();
            paramAnonymousView.put("name", userTeam.name);
            new OnlineActivity.GetTeamOnline(OnlineActivity.this).execute(new String[] { paramAnonymousView.toString() });
            playWeekButton.setText("Play Ranked Game");
            return;
          }
          catch (Exception paramAnonymousView)
          {
            for (;;)
            {
              paramAnonymousView.printStackTrace();
            }
          }
        }
      });
      examineTeamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
      {
        public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          System.out.println("selected in spinner " + paramAnonymousInt);
          if (paramAnonymousInt == 0)
          {
            OnlineActivity.this.updatePlayerStats(true);
            return;
          }
          OnlineActivity.this.updatePlayerStats(false);
        }
        
        public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
      });
      return;
    }
    catch (Exception paramBundle)
    {
      for (;;)
      {
        paramBundle.printStackTrace();
      }
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131623937, paramMenu);
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    int i = paramMenuItem.getItemId();
    if (i == 2131558723) {
      showTeamStrategyDialog();
    }
    for (;;)
    {
      return super.onOptionsItemSelected(paramMenuItem);
      if (i == 2131558727) {
        customOnlineGame();
      } else if (i == 2131558726) {
        exitToMainMeu();
      } else if (i == 2131558728) {
        new GetLeaderboard().execute(new String[] { "test" });
      }
    }
  }
  
  public void playGame()
  {
    currGame.playGame();
    if (currTab == 0) {
      updateTeamStats();
    }
    for (;;)
    {
      showGameDialog(currGame);
      updateDatabase(currGame);
      updateUI();
      return;
      if (currTab == 1) {
        updatePlayerStats(true);
      } else if (currTab == 2) {
        updateSchedule();
      }
    }
  }
  
  public void scheduleGame(boolean paramBoolean)
  {
    currGame = new Game(userTeam, oppTeam, oppTeam.name + " @ " + userTeam.name, 0);
    currGame.isOnline = true;
    currGame.affectsELO = paramBoolean;
    userTeam.gameSchedule.add(currGame);
    showGameScoutDialog(currGame);
  }
  
  public void showGameDialog(Game paramGame)
  {
    String[] arrayOfString = paramGame.getGameSummaryStr();
    Object localObject = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject).setTitle(gameName).setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).setView(getLayoutInflater().inflate(2130968630, null));
    localObject = ((AlertDialog.Builder)localObject).create();
    ((AlertDialog)localObject).show();
    TextView localTextView1 = (TextView)((AlertDialog)localObject).findViewById(2131558610);
    TextView localTextView2 = (TextView)((AlertDialog)localObject).findViewById(2131558612);
    TextView localTextView3 = (TextView)((AlertDialog)localObject).findViewById(2131558613);
    TextView localTextView4 = (TextView)((AlertDialog)localObject).findViewById(2131558615);
    localTextView1.setText(awayScore + "");
    localTextView2.setText(homeScore + "");
    localTextView3.setText(awayTeam.getStrAbbrWL_2Lines());
    localTextView4.setText(homeTeam.getStrAbbrWL_2Lines());
    localTextView1 = (TextView)((AlertDialog)localObject).findViewById(2131558614);
    if (numOT > 0) {
      localTextView1.setText(numOT + "OT");
    }
    for (;;)
    {
      ((TextView)((AlertDialog)localObject).findViewById(2131558616)).setText(arrayOfString[0]);
      ((TextView)((AlertDialog)localObject).findViewById(2131558617)).setText(arrayOfString[1]);
      ((TextView)((AlertDialog)localObject).findViewById(2131558618)).setText(arrayOfString[2]);
      ((TextView)((AlertDialog)localObject).findViewById(2131558619)).setText(arrayOfString[3] + "\n\n");
      return;
      localTextView1.setText("@");
    }
  }
  
  public void showGameScoutDialog(Game paramGame)
  {
    final Object localObject1 = paramGame.getGameScoutStr();
    Object localObject2 = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject2).setTitle("Opponent: " + awayTeam.name).setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).setView(getLayoutInflater().inflate(2130968632, null));
    paramGame = ((AlertDialog.Builder)localObject2).create();
    paramGame.show();
    ((TextView)paramGame.findViewById(2131558624)).setText(localObject1[0]);
    ((TextView)paramGame.findViewById(2131558625)).setText(localObject1[1]);
    ((TextView)paramGame.findViewById(2131558626)).setText(localObject1[2]);
    ((TextView)paramGame.findViewById(2131558631)).setText(localObject1[3]);
    localObject1 = (TextView)paramGame.findViewById(2131558627);
    localObject2 = (TextView)paramGame.findViewById(2131558629);
    ((TextView)localObject1).setText(userTeam.abbr + " Off Strategy:");
    ((TextView)localObject2).setText(userTeam.abbr + " Def Strategy:");
    final TeamStrategy[] arrayOfTeamStrategy = userTeam.getTeamStrategiesOff();
    localObject1 = userTeam.getTeamStrategiesDef();
    int j = 0;
    int k = 0;
    Object localObject3 = new String[arrayOfTeamStrategy.length];
    int i = 0;
    while (i < arrayOfTeamStrategy.length)
    {
      localObject3[i] = arrayOfTeamStrategy[i].getStratName();
      if (localObject3[i].equals(userTeam.teamStratOff.getStratName())) {
        j = i;
      }
      i += 1;
    }
    localObject2 = new String[localObject1.length];
    i = 0;
    while (i < localObject1.length)
    {
      localObject2[i] = localObject1[i].getStratName();
      if (localObject2[i].equals(userTeam.teamStratDef.getStratName())) {
        k = i;
      }
      i += 1;
    }
    Spinner localSpinner = (Spinner)paramGame.findViewById(2131558628);
    localObject3 = new ArrayAdapter(this, 17367048, (Object[])localObject3);
    ((ArrayAdapter)localObject3).setDropDownViewResource(17367049);
    localSpinner.setAdapter((SpinnerAdapter)localObject3);
    localSpinner.setSelection(j);
    localSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        userTeam.teamStratOff = arrayOfTeamStrategy[paramAnonymousInt];
        userTeam.teamStratOffNum = paramAnonymousInt;
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
    paramGame = (Spinner)paramGame.findViewById(2131558630);
    localObject2 = new ArrayAdapter(this, 17367048, (Object[])localObject2);
    ((ArrayAdapter)localObject2).setDropDownViewResource(17367049);
    paramGame.setAdapter((SpinnerAdapter)localObject2);
    paramGame.setSelection(k);
    paramGame.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        userTeam.teamStratDef = localObject1[paramAnonymousInt];
        userTeam.teamStratDefNum = paramAnonymousInt;
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
  }
  
  public void updateDatabase(Game paramGame)
  {
    for (;;)
    {
      int i;
      try
      {
        JSONObject localJSONObject1 = paramGame.getJSONGameResult(true);
        JSONObject localJSONObject2 = paramGame.getJSONGameResult(false);
        if (userTeam == homeTeam)
        {
          i = homeELO;
          if (userTeam != paramGame.getWinner()) {
            break label157;
          }
          if (affectsELO)
          {
            Toast.makeText(this, "You Win! Gained " + i + " ELO.", 0).show();
            new UpdateTeamOnline().execute(new String[] { localJSONObject1.toString() });
            new UpdateTeamOnline().execute(new String[] { localJSONObject2.toString() });
          }
        }
        else
        {
          i = awayELO;
          continue;
        }
        Toast.makeText(this, "You Win! Since it was custom game, your ELO is not changed.", 0).show();
        continue;
        if (!affectsELO) {
          break label204;
        }
      }
      catch (Exception paramGame)
      {
        paramGame.printStackTrace();
        return;
      }
      label157:
      Toast.makeText(this, "You lost, and lost " + Math.abs(i) + " ELO.", 0).show();
      continue;
      label204:
      Toast.makeText(this, "You Lost. Since it was custom game, your ELO is not changed.", 0).show();
    }
  }
  
  public void updateUI()
  {
    ((TextView)findViewById(2131558570)).setText(userTeam.name + " (" + userTeam.wins + "-" + userTeam.losses + ")");
    if (oppTeam != null)
    {
      ArrayAdapter localArrayAdapter = new ArrayAdapter(this, 17367048, new String[] { userTeam.name, oppTeam.name });
      localArrayAdapter.setDropDownViewResource(17367049);
      examineTeamSpinner.setAdapter(localArrayAdapter);
    }
  }
  
  class GetLeaderboard
    extends AsyncTask<String, String, String>
  {
    JSONObject leaderboardJSON;
    boolean success;
    
    GetLeaderboard() {}
    
    /* Error */
    protected String doInBackground(String... paramVarArgs)
    {
      // Byte code:
      //   0: aload_1
      //   1: iconst_0
      //   2: aaload
      //   3: astore 8
      //   5: aconst_null
      //   6: astore 5
      //   8: aconst_null
      //   9: astore_3
      //   10: aconst_null
      //   11: astore 6
      //   13: aconst_null
      //   14: astore 7
      //   16: aload 6
      //   18: astore 4
      //   20: new 37	java/net/URL
      //   23: dup
      //   24: ldc 39
      //   26: invokespecial 42	java/net/URL:<init>	(Ljava/lang/String;)V
      //   29: invokevirtual 46	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   32: checkcast 48	java/net/HttpURLConnection
      //   35: astore_1
      //   36: aload 6
      //   38: astore 4
      //   40: aload_1
      //   41: astore_3
      //   42: aload_1
      //   43: astore 5
      //   45: aload_1
      //   46: iconst_1
      //   47: invokevirtual 52	java/net/HttpURLConnection:setDoOutput	(Z)V
      //   50: aload 6
      //   52: astore 4
      //   54: aload_1
      //   55: astore_3
      //   56: aload_1
      //   57: astore 5
      //   59: aload_1
      //   60: ldc 54
      //   62: invokevirtual 57	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
      //   65: aload 6
      //   67: astore 4
      //   69: aload_1
      //   70: astore_3
      //   71: aload_1
      //   72: astore 5
      //   74: aload_1
      //   75: ldc 59
      //   77: ldc 61
      //   79: invokevirtual 65	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   82: aload 6
      //   84: astore 4
      //   86: aload_1
      //   87: astore_3
      //   88: aload_1
      //   89: astore 5
      //   91: aload_1
      //   92: ldc 67
      //   94: ldc 61
      //   96: invokevirtual 65	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   99: aload 6
      //   101: astore 4
      //   103: aload_1
      //   104: astore_3
      //   105: aload_1
      //   106: astore 5
      //   108: new 69	java/io/BufferedWriter
      //   111: dup
      //   112: new 71	java/io/OutputStreamWriter
      //   115: dup
      //   116: aload_1
      //   117: invokevirtual 75	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   120: ldc 77
      //   122: invokespecial 80	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;Ljava/lang/String;)V
      //   125: invokespecial 83	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
      //   128: astore 9
      //   130: aload 6
      //   132: astore 4
      //   134: aload_1
      //   135: astore_3
      //   136: aload_1
      //   137: astore 5
      //   139: aload 9
      //   141: aload 8
      //   143: invokevirtual 88	java/io/Writer:write	(Ljava/lang/String;)V
      //   146: aload 6
      //   148: astore 4
      //   150: aload_1
      //   151: astore_3
      //   152: aload_1
      //   153: astore 5
      //   155: aload 9
      //   157: invokevirtual 91	java/io/Writer:flush	()V
      //   160: aload 6
      //   162: astore 4
      //   164: aload_1
      //   165: astore_3
      //   166: aload_1
      //   167: astore 5
      //   169: aload 9
      //   171: invokevirtual 94	java/io/Writer:close	()V
      //   174: aload 6
      //   176: astore 4
      //   178: aload_1
      //   179: astore_3
      //   180: aload_1
      //   181: astore 5
      //   183: aload_1
      //   184: invokevirtual 98	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   187: astore 9
      //   189: aload 6
      //   191: astore 4
      //   193: aload_1
      //   194: astore_3
      //   195: aload_1
      //   196: astore 5
      //   198: new 100	java/lang/StringBuffer
      //   201: dup
      //   202: invokespecial 101	java/lang/StringBuffer:<init>	()V
      //   205: astore 8
      //   207: aload 9
      //   209: ifnonnull +32 -> 241
      //   212: aload_1
      //   213: ifnull +7 -> 220
      //   216: aload_1
      //   217: invokevirtual 104	java/net/HttpURLConnection:disconnect	()V
      //   220: iconst_0
      //   221: ifeq +11 -> 232
      //   224: new 106	java/lang/NullPointerException
      //   227: dup
      //   228: invokespecial 107	java/lang/NullPointerException:<init>	()V
      //   231: athrow
      //   232: aconst_null
      //   233: areturn
      //   234: astore_1
      //   235: aload_1
      //   236: invokevirtual 110	java/io/IOException:printStackTrace	()V
      //   239: aconst_null
      //   240: areturn
      //   241: aload 6
      //   243: astore 4
      //   245: aload_1
      //   246: astore_3
      //   247: aload_1
      //   248: astore 5
      //   250: new 112	java/io/BufferedReader
      //   253: dup
      //   254: new 114	java/io/InputStreamReader
      //   257: dup
      //   258: aload 9
      //   260: invokespecial 117	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
      //   263: invokespecial 120	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
      //   266: astore 6
      //   268: aload 6
      //   270: invokevirtual 124	java/io/BufferedReader:readLine	()Ljava/lang/String;
      //   273: astore_3
      //   274: aload_3
      //   275: ifnull +72 -> 347
      //   278: aload 8
      //   280: new 126	java/lang/StringBuilder
      //   283: dup
      //   284: invokespecial 127	java/lang/StringBuilder:<init>	()V
      //   287: aload_3
      //   288: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   291: ldc -123
      //   293: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   296: invokevirtual 136	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   299: invokevirtual 139	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   302: pop
      //   303: goto -35 -> 268
      //   306: astore_3
      //   307: aload_1
      //   308: astore 5
      //   310: aload 6
      //   312: astore_1
      //   313: aload_3
      //   314: astore 6
      //   316: aload_1
      //   317: astore 4
      //   319: aload 5
      //   321: astore_3
      //   322: aload 6
      //   324: invokevirtual 110	java/io/IOException:printStackTrace	()V
      //   327: aload 5
      //   329: ifnull +8 -> 337
      //   332: aload 5
      //   334: invokevirtual 104	java/net/HttpURLConnection:disconnect	()V
      //   337: aload_1
      //   338: ifnull +7 -> 345
      //   341: aload_1
      //   342: invokevirtual 140	java/io/BufferedReader:close	()V
      //   345: aconst_null
      //   346: areturn
      //   347: aload 8
      //   349: invokevirtual 144	java/lang/StringBuffer:length	()I
      //   352: istore_2
      //   353: iload_2
      //   354: ifne +31 -> 385
      //   357: aload_1
      //   358: ifnull +7 -> 365
      //   361: aload_1
      //   362: invokevirtual 104	java/net/HttpURLConnection:disconnect	()V
      //   365: aload 6
      //   367: ifnull +8 -> 375
      //   370: aload 6
      //   372: invokevirtual 140	java/io/BufferedReader:close	()V
      //   375: aconst_null
      //   376: areturn
      //   377: astore_1
      //   378: aload_1
      //   379: invokevirtual 110	java/io/IOException:printStackTrace	()V
      //   382: goto -7 -> 375
      //   385: aload 8
      //   387: invokevirtual 145	java/lang/StringBuffer:toString	()Ljava/lang/String;
      //   390: astore_3
      //   391: getstatic 151	java/lang/System:out	Ljava/io/PrintStream;
      //   394: new 126	java/lang/StringBuilder
      //   397: dup
      //   398: invokespecial 127	java/lang/StringBuilder:<init>	()V
      //   401: ldc -103
      //   403: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   406: aload_3
      //   407: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   410: invokevirtual 136	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   413: invokevirtual 158	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   416: new 160	org/json/JSONObject
      //   419: dup
      //   420: aload_3
      //   421: invokespecial 161	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   424: astore 4
      //   426: aload 4
      //   428: ldc -94
      //   430: invokevirtual 166	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   433: checkcast 168	java/lang/Integer
      //   436: invokevirtual 171	java/lang/Integer:intValue	()I
      //   439: ifne +36 -> 475
      //   442: getstatic 151	java/lang/System:out	Ljava/io/PrintStream;
      //   445: ldc -83
      //   447: invokevirtual 158	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   450: aload_0
      //   451: iconst_0
      //   452: putfield 175	com/achijones/profootballcoach/OnlineActivity$GetLeaderboard:success	Z
      //   455: aload_1
      //   456: ifnull +7 -> 463
      //   459: aload_1
      //   460: invokevirtual 104	java/net/HttpURLConnection:disconnect	()V
      //   463: aload 6
      //   465: ifnull +8 -> 473
      //   468: aload 6
      //   470: invokevirtual 140	java/io/BufferedReader:close	()V
      //   473: aload_3
      //   474: areturn
      //   475: aload_0
      //   476: iconst_1
      //   477: putfield 175	com/achijones/profootballcoach/OnlineActivity$GetLeaderboard:success	Z
      //   480: aload_0
      //   481: aload 4
      //   483: putfield 177	com/achijones/profootballcoach/OnlineActivity$GetLeaderboard:leaderboardJSON	Lorg/json/JSONObject;
      //   486: goto -31 -> 455
      //   489: astore 4
      //   491: aload 4
      //   493: invokevirtual 178	java/lang/Exception:printStackTrace	()V
      //   496: goto -41 -> 455
      //   499: astore_3
      //   500: aload 6
      //   502: astore 4
      //   504: aload_1
      //   505: ifnull +7 -> 512
      //   508: aload_1
      //   509: invokevirtual 104	java/net/HttpURLConnection:disconnect	()V
      //   512: aload 4
      //   514: ifnull +8 -> 522
      //   517: aload 4
      //   519: invokevirtual 140	java/io/BufferedReader:close	()V
      //   522: aload_3
      //   523: athrow
      //   524: astore_1
      //   525: aload_1
      //   526: invokevirtual 110	java/io/IOException:printStackTrace	()V
      //   529: goto -56 -> 473
      //   532: astore_1
      //   533: aload_1
      //   534: invokevirtual 110	java/io/IOException:printStackTrace	()V
      //   537: goto -192 -> 345
      //   540: astore_1
      //   541: aload_1
      //   542: invokevirtual 110	java/io/IOException:printStackTrace	()V
      //   545: goto -23 -> 522
      //   548: astore 5
      //   550: aload_3
      //   551: astore_1
      //   552: aload 5
      //   554: astore_3
      //   555: goto -51 -> 504
      //   558: astore 6
      //   560: aload 7
      //   562: astore_1
      //   563: goto -247 -> 316
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	566	0	this	GetLeaderboard
      //   0	566	1	paramVarArgs	String[]
      //   352	2	2	i	int
      //   9	279	3	localObject1	Object
      //   306	8	3	localIOException1	java.io.IOException
      //   321	153	3	localObject2	Object
      //   499	52	3	localObject3	Object
      //   554	1	3	localObject4	Object
      //   18	464	4	localObject5	Object
      //   489	3	4	localException	Exception
      //   502	16	4	localObject6	Object
      //   6	327	5	arrayOfString	String[]
      //   548	5	5	localObject7	Object
      //   11	490	6	localObject8	Object
      //   558	1	6	localIOException2	java.io.IOException
      //   14	547	7	localObject9	Object
      //   3	383	8	localObject10	Object
      //   128	131	9	localObject11	Object
      // Exception table:
      //   from	to	target	type
      //   224	232	234	java/io/IOException
      //   268	274	306	java/io/IOException
      //   278	303	306	java/io/IOException
      //   347	353	306	java/io/IOException
      //   385	416	306	java/io/IOException
      //   416	455	306	java/io/IOException
      //   475	486	306	java/io/IOException
      //   491	496	306	java/io/IOException
      //   370	375	377	java/io/IOException
      //   416	455	489	java/lang/Exception
      //   475	486	489	java/lang/Exception
      //   268	274	499	finally
      //   278	303	499	finally
      //   347	353	499	finally
      //   385	416	499	finally
      //   416	455	499	finally
      //   475	486	499	finally
      //   491	496	499	finally
      //   468	473	524	java/io/IOException
      //   341	345	532	java/io/IOException
      //   517	522	540	java/io/IOException
      //   20	36	548	finally
      //   45	50	548	finally
      //   59	65	548	finally
      //   74	82	548	finally
      //   91	99	548	finally
      //   108	130	548	finally
      //   139	146	548	finally
      //   155	160	548	finally
      //   169	174	548	finally
      //   183	189	548	finally
      //   198	207	548	finally
      //   250	268	548	finally
      //   322	327	548	finally
      //   20	36	558	java/io/IOException
      //   45	50	558	java/io/IOException
      //   59	65	558	java/io/IOException
      //   74	82	558	java/io/IOException
      //   91	99	558	java/io/IOException
      //   108	130	558	java/io/IOException
      //   139	146	558	java/io/IOException
      //   155	160	558	java/io/IOException
      //   169	174	558	java/io/IOException
      //   183	189	558	java/io/IOException
      //   198	207	558	java/io/IOException
      //   250	268	558	java/io/IOException
    }
    
    protected void onPostExecute(String paramString)
    {
      if (!success)
      {
        Toast.makeText(OnlineActivity.this, "Something went wrong.", 0).show();
        return;
      }
      paramString = new AlertDialog.Builder(OnlineActivity.this);
      paramString.setTitle("Online ELO Leaderboard").setPositiveButton("OK", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
      }).setView(getLayoutInflater().inflate(2130968660, null));
      paramString = paramString.create();
      paramString.show();
      ArrayList localArrayList = new ArrayList();
      int i = 0;
      for (;;)
      {
        if (i < 50) {
          try
          {
            JSONObject localJSONObject = new JSONObject(leaderboardJSON.get("" + i).toString());
            localArrayList.add(i + 1 + "," + localJSONObject.get("name") + "," + localJSONObject.get("elo"));
            i += 1;
          }
          catch (Exception localException)
          {
            for (;;)
            {
              localException.printStackTrace();
            }
          }
        }
      }
      ((ListView)paramString.findViewById(2131558679)).setAdapter(new TeamRankingsListArrayAdapter(OnlineActivity.this, localArrayList, userTeam.name));
    }
  }
  
  class GetTeamOnline
    extends AsyncTask<String, String, String>
  {
    boolean success;
    
    GetTeamOnline() {}
    
    /* Error */
    protected String doInBackground(String... paramVarArgs)
    {
      // Byte code:
      //   0: aload_1
      //   1: iconst_0
      //   2: aaload
      //   3: astore 10
      //   5: aconst_null
      //   6: astore 7
      //   8: aconst_null
      //   9: astore 9
      //   11: aconst_null
      //   12: astore 6
      //   14: aconst_null
      //   15: astore 8
      //   17: aload 6
      //   19: astore 4
      //   21: aload 9
      //   23: astore_1
      //   24: aload 7
      //   26: astore 5
      //   28: aload_0
      //   29: getfield 18	com/achijones/profootballcoach/OnlineActivity$GetTeamOnline:this$0	Lcom/achijones/profootballcoach/OnlineActivity;
      //   32: getfield 36	com/achijones/profootballcoach/OnlineActivity:gettingRandTeam	Z
      //   35: ifeq +241 -> 276
      //   38: aload 6
      //   40: astore 4
      //   42: aload 9
      //   44: astore_1
      //   45: aload 7
      //   47: astore 5
      //   49: new 38	java/net/URL
      //   52: dup
      //   53: ldc 40
      //   55: invokespecial 43	java/net/URL:<init>	(Ljava/lang/String;)V
      //   58: astore_3
      //   59: aload 6
      //   61: astore 4
      //   63: aload 9
      //   65: astore_1
      //   66: aload 7
      //   68: astore 5
      //   70: aload_3
      //   71: invokevirtual 47	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   74: checkcast 49	java/net/HttpURLConnection
      //   77: astore_3
      //   78: aload 6
      //   80: astore 4
      //   82: aload_3
      //   83: astore_1
      //   84: aload_3
      //   85: astore 5
      //   87: aload_3
      //   88: iconst_1
      //   89: invokevirtual 53	java/net/HttpURLConnection:setDoOutput	(Z)V
      //   92: aload 6
      //   94: astore 4
      //   96: aload_3
      //   97: astore_1
      //   98: aload_3
      //   99: astore 5
      //   101: aload_3
      //   102: ldc 55
      //   104: invokevirtual 58	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
      //   107: aload 6
      //   109: astore 4
      //   111: aload_3
      //   112: astore_1
      //   113: aload_3
      //   114: astore 5
      //   116: aload_3
      //   117: ldc 60
      //   119: ldc 62
      //   121: invokevirtual 66	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   124: aload 6
      //   126: astore 4
      //   128: aload_3
      //   129: astore_1
      //   130: aload_3
      //   131: astore 5
      //   133: aload_3
      //   134: ldc 68
      //   136: ldc 62
      //   138: invokevirtual 66	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   141: aload 6
      //   143: astore 4
      //   145: aload_3
      //   146: astore_1
      //   147: aload_3
      //   148: astore 5
      //   150: new 70	java/io/BufferedWriter
      //   153: dup
      //   154: new 72	java/io/OutputStreamWriter
      //   157: dup
      //   158: aload_3
      //   159: invokevirtual 76	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   162: ldc 78
      //   164: invokespecial 81	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;Ljava/lang/String;)V
      //   167: invokespecial 84	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
      //   170: astore 7
      //   172: aload 6
      //   174: astore 4
      //   176: aload_3
      //   177: astore_1
      //   178: aload_3
      //   179: astore 5
      //   181: aload 7
      //   183: aload 10
      //   185: invokevirtual 89	java/io/Writer:write	(Ljava/lang/String;)V
      //   188: aload 6
      //   190: astore 4
      //   192: aload_3
      //   193: astore_1
      //   194: aload_3
      //   195: astore 5
      //   197: aload 7
      //   199: invokevirtual 92	java/io/Writer:flush	()V
      //   202: aload 6
      //   204: astore 4
      //   206: aload_3
      //   207: astore_1
      //   208: aload_3
      //   209: astore 5
      //   211: aload 7
      //   213: invokevirtual 95	java/io/Writer:close	()V
      //   216: aload 6
      //   218: astore 4
      //   220: aload_3
      //   221: astore_1
      //   222: aload_3
      //   223: astore 5
      //   225: aload_3
      //   226: invokevirtual 99	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   229: astore 9
      //   231: aload 6
      //   233: astore 4
      //   235: aload_3
      //   236: astore_1
      //   237: aload_3
      //   238: astore 5
      //   240: new 101	java/lang/StringBuffer
      //   243: dup
      //   244: invokespecial 102	java/lang/StringBuffer:<init>	()V
      //   247: astore 7
      //   249: aload 9
      //   251: ifnonnull +56 -> 307
      //   254: aload_3
      //   255: ifnull +7 -> 262
      //   258: aload_3
      //   259: invokevirtual 105	java/net/HttpURLConnection:disconnect	()V
      //   262: iconst_0
      //   263: ifeq +11 -> 274
      //   266: new 107	java/lang/NullPointerException
      //   269: dup
      //   270: invokespecial 108	java/lang/NullPointerException:<init>	()V
      //   273: athrow
      //   274: aconst_null
      //   275: areturn
      //   276: aload 6
      //   278: astore 4
      //   280: aload 9
      //   282: astore_1
      //   283: aload 7
      //   285: astore 5
      //   287: new 38	java/net/URL
      //   290: dup
      //   291: ldc 110
      //   293: invokespecial 43	java/net/URL:<init>	(Ljava/lang/String;)V
      //   296: astore_3
      //   297: goto -238 -> 59
      //   300: astore_1
      //   301: aload_1
      //   302: invokevirtual 113	java/io/IOException:printStackTrace	()V
      //   305: aconst_null
      //   306: areturn
      //   307: aload 6
      //   309: astore 4
      //   311: aload_3
      //   312: astore_1
      //   313: aload_3
      //   314: astore 5
      //   316: new 115	java/io/BufferedReader
      //   319: dup
      //   320: new 117	java/io/InputStreamReader
      //   323: dup
      //   324: aload 9
      //   326: invokespecial 120	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
      //   329: invokespecial 123	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
      //   332: astore 6
      //   334: aload 6
      //   336: invokevirtual 127	java/io/BufferedReader:readLine	()Ljava/lang/String;
      //   339: astore_1
      //   340: aload_1
      //   341: ifnull +64 -> 405
      //   344: aload 7
      //   346: new 129	java/lang/StringBuilder
      //   349: dup
      //   350: invokespecial 130	java/lang/StringBuilder:<init>	()V
      //   353: aload_1
      //   354: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   357: ldc -120
      //   359: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   362: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   365: invokevirtual 142	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   368: pop
      //   369: goto -35 -> 334
      //   372: astore 7
      //   374: aload 6
      //   376: astore 4
      //   378: aload_3
      //   379: astore_1
      //   380: aload 7
      //   382: invokevirtual 113	java/io/IOException:printStackTrace	()V
      //   385: aload_3
      //   386: ifnull +7 -> 393
      //   389: aload_3
      //   390: invokevirtual 105	java/net/HttpURLConnection:disconnect	()V
      //   393: aload 6
      //   395: ifnull +8 -> 403
      //   398: aload 6
      //   400: invokevirtual 143	java/io/BufferedReader:close	()V
      //   403: aconst_null
      //   404: areturn
      //   405: aload 7
      //   407: invokevirtual 147	java/lang/StringBuffer:length	()I
      //   410: istore_2
      //   411: iload_2
      //   412: ifne +31 -> 443
      //   415: aload_3
      //   416: ifnull +7 -> 423
      //   419: aload_3
      //   420: invokevirtual 105	java/net/HttpURLConnection:disconnect	()V
      //   423: aload 6
      //   425: ifnull +8 -> 433
      //   428: aload 6
      //   430: invokevirtual 143	java/io/BufferedReader:close	()V
      //   433: aconst_null
      //   434: areturn
      //   435: astore_1
      //   436: aload_1
      //   437: invokevirtual 113	java/io/IOException:printStackTrace	()V
      //   440: goto -7 -> 433
      //   443: aload 7
      //   445: invokevirtual 148	java/lang/StringBuffer:toString	()Ljava/lang/String;
      //   448: astore_1
      //   449: getstatic 154	java/lang/System:out	Ljava/io/PrintStream;
      //   452: new 129	java/lang/StringBuilder
      //   455: dup
      //   456: invokespecial 130	java/lang/StringBuilder:<init>	()V
      //   459: ldc -100
      //   461: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   464: aload_1
      //   465: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   468: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   471: invokevirtual 161	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   474: new 163	org/json/JSONObject
      //   477: dup
      //   478: aload_1
      //   479: invokespecial 164	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   482: astore 4
      //   484: aload 4
      //   486: ldc -91
      //   488: invokevirtual 169	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   491: checkcast 171	java/lang/Integer
      //   494: invokevirtual 174	java/lang/Integer:intValue	()I
      //   497: ifne +36 -> 533
      //   500: getstatic 154	java/lang/System:out	Ljava/io/PrintStream;
      //   503: ldc -80
      //   505: invokevirtual 161	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   508: aload_0
      //   509: iconst_0
      //   510: putfield 178	com/achijones/profootballcoach/OnlineActivity$GetTeamOnline:success	Z
      //   513: aload_3
      //   514: ifnull +7 -> 521
      //   517: aload_3
      //   518: invokevirtual 105	java/net/HttpURLConnection:disconnect	()V
      //   521: aload 6
      //   523: ifnull +8 -> 531
      //   526: aload 6
      //   528: invokevirtual 143	java/io/BufferedReader:close	()V
      //   531: aload_1
      //   532: areturn
      //   533: aload_0
      //   534: iconst_1
      //   535: putfield 178	com/achijones/profootballcoach/OnlineActivity$GetTeamOnline:success	Z
      //   538: aload_0
      //   539: getfield 18	com/achijones/profootballcoach/OnlineActivity$GetTeamOnline:this$0	Lcom/achijones/profootballcoach/OnlineActivity;
      //   542: getfield 181	com/achijones/profootballcoach/OnlineActivity:gettingUserTeam	Z
      //   545: ifeq +57 -> 602
      //   548: aload_0
      //   549: getfield 18	com/achijones/profootballcoach/OnlineActivity$GetTeamOnline:this$0	Lcom/achijones/profootballcoach/OnlineActivity;
      //   552: new 183	PFCpack/Team
      //   555: dup
      //   556: aload 4
      //   558: invokespecial 186	PFCpack/Team:<init>	(Lorg/json/JSONObject;)V
      //   561: putfield 190	com/achijones/profootballcoach/OnlineActivity:userTeam	LPFCpack/Team;
      //   564: goto -51 -> 513
      //   567: astore 4
      //   569: aload 4
      //   571: invokevirtual 191	java/lang/Exception:printStackTrace	()V
      //   574: goto -61 -> 513
      //   577: astore_1
      //   578: aload 6
      //   580: astore 4
      //   582: aload_3
      //   583: ifnull +7 -> 590
      //   586: aload_3
      //   587: invokevirtual 105	java/net/HttpURLConnection:disconnect	()V
      //   590: aload 4
      //   592: ifnull +8 -> 600
      //   595: aload 4
      //   597: invokevirtual 143	java/io/BufferedReader:close	()V
      //   600: aload_1
      //   601: athrow
      //   602: aload_0
      //   603: getfield 18	com/achijones/profootballcoach/OnlineActivity$GetTeamOnline:this$0	Lcom/achijones/profootballcoach/OnlineActivity;
      //   606: new 183	PFCpack/Team
      //   609: dup
      //   610: aload 4
      //   612: invokespecial 186	PFCpack/Team:<init>	(Lorg/json/JSONObject;)V
      //   615: putfield 194	com/achijones/profootballcoach/OnlineActivity:oppTeam	LPFCpack/Team;
      //   618: goto -105 -> 513
      //   621: astore_3
      //   622: aload_3
      //   623: invokevirtual 113	java/io/IOException:printStackTrace	()V
      //   626: goto -95 -> 531
      //   629: astore_1
      //   630: aload_1
      //   631: invokevirtual 113	java/io/IOException:printStackTrace	()V
      //   634: goto -231 -> 403
      //   637: astore_3
      //   638: aload_3
      //   639: invokevirtual 113	java/io/IOException:printStackTrace	()V
      //   642: goto -42 -> 600
      //   645: astore 5
      //   647: aload_1
      //   648: astore_3
      //   649: aload 5
      //   651: astore_1
      //   652: goto -70 -> 582
      //   655: astore 7
      //   657: aload 8
      //   659: astore 6
      //   661: aload 5
      //   663: astore_3
      //   664: goto -290 -> 374
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	667	0	this	GetTeamOnline
      //   0	667	1	paramVarArgs	String[]
      //   410	2	2	i	int
      //   58	529	3	localObject1	Object
      //   621	2	3	localIOException1	java.io.IOException
      //   637	2	3	localIOException2	java.io.IOException
      //   648	16	3	localObject2	Object
      //   19	538	4	localObject3	Object
      //   567	3	4	localException	Exception
      //   580	31	4	localObject4	Object
      //   26	289	5	localObject5	Object
      //   645	17	5	localObject6	Object
      //   12	648	6	localObject7	Object
      //   6	339	7	localObject8	Object
      //   372	72	7	localIOException3	java.io.IOException
      //   655	1	7	localIOException4	java.io.IOException
      //   15	643	8	localObject9	Object
      //   9	316	9	localInputStream	java.io.InputStream
      //   3	181	10	str	String
      // Exception table:
      //   from	to	target	type
      //   266	274	300	java/io/IOException
      //   334	340	372	java/io/IOException
      //   344	369	372	java/io/IOException
      //   405	411	372	java/io/IOException
      //   443	474	372	java/io/IOException
      //   474	513	372	java/io/IOException
      //   533	564	372	java/io/IOException
      //   569	574	372	java/io/IOException
      //   602	618	372	java/io/IOException
      //   428	433	435	java/io/IOException
      //   474	513	567	java/lang/Exception
      //   533	564	567	java/lang/Exception
      //   602	618	567	java/lang/Exception
      //   334	340	577	finally
      //   344	369	577	finally
      //   405	411	577	finally
      //   443	474	577	finally
      //   474	513	577	finally
      //   533	564	577	finally
      //   569	574	577	finally
      //   602	618	577	finally
      //   526	531	621	java/io/IOException
      //   398	403	629	java/io/IOException
      //   595	600	637	java/io/IOException
      //   28	38	645	finally
      //   49	59	645	finally
      //   70	78	645	finally
      //   87	92	645	finally
      //   101	107	645	finally
      //   116	124	645	finally
      //   133	141	645	finally
      //   150	172	645	finally
      //   181	188	645	finally
      //   197	202	645	finally
      //   211	216	645	finally
      //   225	231	645	finally
      //   240	249	645	finally
      //   287	297	645	finally
      //   316	334	645	finally
      //   380	385	645	finally
      //   28	38	655	java/io/IOException
      //   49	59	655	java/io/IOException
      //   70	78	655	java/io/IOException
      //   87	92	655	java/io/IOException
      //   101	107	655	java/io/IOException
      //   116	124	655	java/io/IOException
      //   133	141	655	java/io/IOException
      //   150	172	655	java/io/IOException
      //   181	188	655	java/io/IOException
      //   197	202	655	java/io/IOException
      //   211	216	655	java/io/IOException
      //   225	231	655	java/io/IOException
      //   240	249	655	java/io/IOException
      //   287	297	655	java/io/IOException
      //   316	334	655	java/io/IOException
    }
    
    protected void onPostExecute(String paramString)
    {
      if (!success)
      {
        Toast.makeText(OnlineActivity.this, "Could not find team.", 0).show();
        playWeekButton.setText("Schedule Ranked Game");
        return;
      }
      if (!gettingUserTeam)
      {
        Toast.makeText(OnlineActivity.this, "Found opponent: " + oppTeam.name + "!", 0).show();
        scheduleGame(gettingRandTeam);
      }
      for (;;)
      {
        updateUI();
        OnlineActivity.this.updatePlayerStats(false);
        examineTeamSpinner.setSelection(1);
        return;
        if ((userTeam.wins == 0) && (userTeam.losses == 0))
        {
          paramString = new AlertDialog.Builder(OnlineActivity.this);
          paramString.setTitle("Welcome to Online Play!").setMessage("Here, you can compete with other teams from around the world!\n\n This multiplayer is asynchronous, which means that you can play against other teams at your own leisure. You can schedule teams to play against, and other players will also schedule your team to play against.\n\n By pressing Schedule Ranked Game, a team will be randomly selected for you to play against. Then you can compare the two teams by viewing their rosters, team stats, and more.\n\n When you are ready, press Play Week to play a game against them. Since you are playing from your device, you will have home field advantage. Each random game is considered Ranked, which means it will affect your ELO ranking.\n\n You can also schedule custom games against any team you like by clicking Custom Game in the menu. These games won't affect ELO.\n\n Be sure to set your default strategy by using the menu as well. This is the strategy that your team will use when other teams play against yours.\n\n Have fun and good luck!").setPositiveButton("OK", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
          });
          paramString = paramString.create();
          paramString.show();
          ((TextView)paramString.findViewById(16908299)).setTextSize(2, 14.0F);
        }
      }
    }
  }
  
  class UpdateTeamOnline
    extends AsyncTask<String, String, String>
  {
    UpdateTeamOnline() {}
    
    /* Error */
    protected String doInBackground(String... paramVarArgs)
    {
      // Byte code:
      //   0: aload_1
      //   1: iconst_0
      //   2: aaload
      //   3: astore 7
      //   5: aconst_null
      //   6: astore 5
      //   8: aconst_null
      //   9: astore_3
      //   10: aconst_null
      //   11: astore 6
      //   13: aconst_null
      //   14: astore 8
      //   16: aload 6
      //   18: astore 4
      //   20: new 31	java/net/URL
      //   23: dup
      //   24: ldc 33
      //   26: invokespecial 36	java/net/URL:<init>	(Ljava/lang/String;)V
      //   29: invokevirtual 40	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   32: checkcast 42	java/net/HttpURLConnection
      //   35: astore_1
      //   36: aload 6
      //   38: astore 4
      //   40: aload_1
      //   41: astore_3
      //   42: aload_1
      //   43: astore 5
      //   45: aload_1
      //   46: iconst_1
      //   47: invokevirtual 46	java/net/HttpURLConnection:setDoOutput	(Z)V
      //   50: aload 6
      //   52: astore 4
      //   54: aload_1
      //   55: astore_3
      //   56: aload_1
      //   57: astore 5
      //   59: aload_1
      //   60: ldc 48
      //   62: invokevirtual 51	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
      //   65: aload 6
      //   67: astore 4
      //   69: aload_1
      //   70: astore_3
      //   71: aload_1
      //   72: astore 5
      //   74: aload_1
      //   75: ldc 53
      //   77: ldc 55
      //   79: invokevirtual 59	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   82: aload 6
      //   84: astore 4
      //   86: aload_1
      //   87: astore_3
      //   88: aload_1
      //   89: astore 5
      //   91: aload_1
      //   92: ldc 61
      //   94: ldc 55
      //   96: invokevirtual 59	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   99: aload 6
      //   101: astore 4
      //   103: aload_1
      //   104: astore_3
      //   105: aload_1
      //   106: astore 5
      //   108: new 63	java/io/BufferedWriter
      //   111: dup
      //   112: new 65	java/io/OutputStreamWriter
      //   115: dup
      //   116: aload_1
      //   117: invokevirtual 69	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   120: ldc 71
      //   122: invokespecial 74	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;Ljava/lang/String;)V
      //   125: invokespecial 77	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
      //   128: astore 9
      //   130: aload 6
      //   132: astore 4
      //   134: aload_1
      //   135: astore_3
      //   136: aload_1
      //   137: astore 5
      //   139: aload 9
      //   141: aload 7
      //   143: invokevirtual 82	java/io/Writer:write	(Ljava/lang/String;)V
      //   146: aload 6
      //   148: astore 4
      //   150: aload_1
      //   151: astore_3
      //   152: aload_1
      //   153: astore 5
      //   155: aload 9
      //   157: invokevirtual 85	java/io/Writer:flush	()V
      //   160: aload 6
      //   162: astore 4
      //   164: aload_1
      //   165: astore_3
      //   166: aload_1
      //   167: astore 5
      //   169: aload 9
      //   171: invokevirtual 88	java/io/Writer:close	()V
      //   174: aload 6
      //   176: astore 4
      //   178: aload_1
      //   179: astore_3
      //   180: aload_1
      //   181: astore 5
      //   183: aload_1
      //   184: invokevirtual 92	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   187: astore 9
      //   189: aload 6
      //   191: astore 4
      //   193: aload_1
      //   194: astore_3
      //   195: aload_1
      //   196: astore 5
      //   198: new 94	java/lang/StringBuffer
      //   201: dup
      //   202: invokespecial 95	java/lang/StringBuffer:<init>	()V
      //   205: astore 7
      //   207: aload 9
      //   209: ifnonnull +32 -> 241
      //   212: aload_1
      //   213: ifnull +7 -> 220
      //   216: aload_1
      //   217: invokevirtual 98	java/net/HttpURLConnection:disconnect	()V
      //   220: iconst_0
      //   221: ifeq +11 -> 232
      //   224: new 100	java/lang/NullPointerException
      //   227: dup
      //   228: invokespecial 101	java/lang/NullPointerException:<init>	()V
      //   231: athrow
      //   232: aconst_null
      //   233: areturn
      //   234: astore_1
      //   235: aload_1
      //   236: invokevirtual 104	java/io/IOException:printStackTrace	()V
      //   239: aconst_null
      //   240: areturn
      //   241: aload 6
      //   243: astore 4
      //   245: aload_1
      //   246: astore_3
      //   247: aload_1
      //   248: astore 5
      //   250: new 106	java/io/BufferedReader
      //   253: dup
      //   254: new 108	java/io/InputStreamReader
      //   257: dup
      //   258: aload 9
      //   260: invokespecial 111	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
      //   263: invokespecial 114	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
      //   266: astore 6
      //   268: aload 6
      //   270: invokevirtual 118	java/io/BufferedReader:readLine	()Ljava/lang/String;
      //   273: astore_3
      //   274: aload_3
      //   275: ifnull +64 -> 339
      //   278: aload 7
      //   280: new 120	java/lang/StringBuilder
      //   283: dup
      //   284: invokespecial 121	java/lang/StringBuilder:<init>	()V
      //   287: aload_3
      //   288: invokevirtual 125	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   291: ldc 127
      //   293: invokevirtual 125	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   296: invokevirtual 130	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   299: invokevirtual 133	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   302: pop
      //   303: goto -35 -> 268
      //   306: astore 7
      //   308: aload 6
      //   310: astore 4
      //   312: aload_1
      //   313: astore_3
      //   314: aload 7
      //   316: invokevirtual 104	java/io/IOException:printStackTrace	()V
      //   319: aload_1
      //   320: ifnull +7 -> 327
      //   323: aload_1
      //   324: invokevirtual 98	java/net/HttpURLConnection:disconnect	()V
      //   327: aload 6
      //   329: ifnull +8 -> 337
      //   332: aload 6
      //   334: invokevirtual 134	java/io/BufferedReader:close	()V
      //   337: aconst_null
      //   338: areturn
      //   339: aload 7
      //   341: invokevirtual 138	java/lang/StringBuffer:length	()I
      //   344: istore_2
      //   345: iload_2
      //   346: ifne +31 -> 377
      //   349: aload_1
      //   350: ifnull +7 -> 357
      //   353: aload_1
      //   354: invokevirtual 98	java/net/HttpURLConnection:disconnect	()V
      //   357: aload 6
      //   359: ifnull +8 -> 367
      //   362: aload 6
      //   364: invokevirtual 134	java/io/BufferedReader:close	()V
      //   367: aconst_null
      //   368: areturn
      //   369: astore_1
      //   370: aload_1
      //   371: invokevirtual 104	java/io/IOException:printStackTrace	()V
      //   374: goto -7 -> 367
      //   377: aload 7
      //   379: invokevirtual 139	java/lang/StringBuffer:toString	()Ljava/lang/String;
      //   382: astore_3
      //   383: getstatic 145	java/lang/System:out	Ljava/io/PrintStream;
      //   386: new 120	java/lang/StringBuilder
      //   389: dup
      //   390: invokespecial 121	java/lang/StringBuilder:<init>	()V
      //   393: ldc -109
      //   395: invokevirtual 125	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   398: aload_3
      //   399: invokevirtual 125	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   402: invokevirtual 130	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   405: invokevirtual 152	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   408: new 154	org/json/JSONObject
      //   411: dup
      //   412: aload_3
      //   413: invokespecial 155	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   416: ldc -99
      //   418: invokevirtual 161	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   421: checkcast 163	java/lang/Integer
      //   424: invokevirtual 166	java/lang/Integer:intValue	()I
      //   427: ifne +11 -> 438
      //   430: getstatic 145	java/lang/System:out	Ljava/io/PrintStream;
      //   433: ldc -88
      //   435: invokevirtual 152	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   438: aload_1
      //   439: ifnull +7 -> 446
      //   442: aload_1
      //   443: invokevirtual 98	java/net/HttpURLConnection:disconnect	()V
      //   446: aload 6
      //   448: ifnull +8 -> 456
      //   451: aload 6
      //   453: invokevirtual 134	java/io/BufferedReader:close	()V
      //   456: aload_3
      //   457: areturn
      //   458: astore 4
      //   460: aload 4
      //   462: invokevirtual 169	java/lang/Exception:printStackTrace	()V
      //   465: goto -27 -> 438
      //   468: astore_3
      //   469: aload 6
      //   471: astore 4
      //   473: aload_1
      //   474: ifnull +7 -> 481
      //   477: aload_1
      //   478: invokevirtual 98	java/net/HttpURLConnection:disconnect	()V
      //   481: aload 4
      //   483: ifnull +8 -> 491
      //   486: aload 4
      //   488: invokevirtual 134	java/io/BufferedReader:close	()V
      //   491: aload_3
      //   492: athrow
      //   493: astore_1
      //   494: aload_1
      //   495: invokevirtual 104	java/io/IOException:printStackTrace	()V
      //   498: goto -42 -> 456
      //   501: astore_1
      //   502: aload_1
      //   503: invokevirtual 104	java/io/IOException:printStackTrace	()V
      //   506: goto -169 -> 337
      //   509: astore_1
      //   510: aload_1
      //   511: invokevirtual 104	java/io/IOException:printStackTrace	()V
      //   514: goto -23 -> 491
      //   517: astore 5
      //   519: aload_3
      //   520: astore_1
      //   521: aload 5
      //   523: astore_3
      //   524: goto -51 -> 473
      //   527: astore 7
      //   529: aload 8
      //   531: astore 6
      //   533: aload 5
      //   535: astore_1
      //   536: goto -228 -> 308
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	539	0	this	UpdateTeamOnline
      //   0	539	1	paramVarArgs	String[]
      //   344	2	2	i	int
      //   9	448	3	localObject1	Object
      //   468	52	3	localObject2	Object
      //   523	1	3	localObject3	Object
      //   18	293	4	localObject4	Object
      //   458	3	4	localException	Exception
      //   471	16	4	localObject5	Object
      //   6	243	5	arrayOfString	String[]
      //   517	17	5	localObject6	Object
      //   11	521	6	localObject7	Object
      //   3	276	7	localObject8	Object
      //   306	72	7	localIOException1	java.io.IOException
      //   527	1	7	localIOException2	java.io.IOException
      //   14	516	8	localObject9	Object
      //   128	131	9	localObject10	Object
      // Exception table:
      //   from	to	target	type
      //   224	232	234	java/io/IOException
      //   268	274	306	java/io/IOException
      //   278	303	306	java/io/IOException
      //   339	345	306	java/io/IOException
      //   377	408	306	java/io/IOException
      //   408	438	306	java/io/IOException
      //   460	465	306	java/io/IOException
      //   362	367	369	java/io/IOException
      //   408	438	458	java/lang/Exception
      //   268	274	468	finally
      //   278	303	468	finally
      //   339	345	468	finally
      //   377	408	468	finally
      //   408	438	468	finally
      //   460	465	468	finally
      //   451	456	493	java/io/IOException
      //   332	337	501	java/io/IOException
      //   486	491	509	java/io/IOException
      //   20	36	517	finally
      //   45	50	517	finally
      //   59	65	517	finally
      //   74	82	517	finally
      //   91	99	517	finally
      //   108	130	517	finally
      //   139	146	517	finally
      //   155	160	517	finally
      //   169	174	517	finally
      //   183	189	517	finally
      //   198	207	517	finally
      //   250	268	517	finally
      //   314	319	517	finally
      //   20	36	527	java/io/IOException
      //   45	50	527	java/io/IOException
      //   59	65	527	java/io/IOException
      //   74	82	527	java/io/IOException
      //   91	99	527	java/io/IOException
      //   108	130	527	java/io/IOException
      //   139	146	527	java/io/IOException
      //   155	160	527	java/io/IOException
      //   169	174	527	java/io/IOException
      //   183	189	527	java/io/IOException
      //   198	207	527	java/io/IOException
      //   250	268	527	java/io/IOException
    }
  }
  
  class UpdateTeamStratsOnline
    extends AsyncTask<String, String, String>
  {
    boolean success;
    
    UpdateTeamStratsOnline() {}
    
    /* Error */
    protected String doInBackground(String... paramVarArgs)
    {
      // Byte code:
      //   0: aload_1
      //   1: iconst_0
      //   2: aaload
      //   3: astore 8
      //   5: aconst_null
      //   6: astore 5
      //   8: aconst_null
      //   9: astore_3
      //   10: aconst_null
      //   11: astore 6
      //   13: aconst_null
      //   14: astore 7
      //   16: aload 6
      //   18: astore 4
      //   20: new 33	java/net/URL
      //   23: dup
      //   24: ldc 35
      //   26: invokespecial 38	java/net/URL:<init>	(Ljava/lang/String;)V
      //   29: invokevirtual 42	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   32: checkcast 44	java/net/HttpURLConnection
      //   35: astore_1
      //   36: aload 6
      //   38: astore 4
      //   40: aload_1
      //   41: astore_3
      //   42: aload_1
      //   43: astore 5
      //   45: aload_1
      //   46: iconst_1
      //   47: invokevirtual 48	java/net/HttpURLConnection:setDoOutput	(Z)V
      //   50: aload 6
      //   52: astore 4
      //   54: aload_1
      //   55: astore_3
      //   56: aload_1
      //   57: astore 5
      //   59: aload_1
      //   60: ldc 50
      //   62: invokevirtual 53	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
      //   65: aload 6
      //   67: astore 4
      //   69: aload_1
      //   70: astore_3
      //   71: aload_1
      //   72: astore 5
      //   74: aload_1
      //   75: ldc 55
      //   77: ldc 57
      //   79: invokevirtual 61	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   82: aload 6
      //   84: astore 4
      //   86: aload_1
      //   87: astore_3
      //   88: aload_1
      //   89: astore 5
      //   91: aload_1
      //   92: ldc 63
      //   94: ldc 57
      //   96: invokevirtual 61	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   99: aload 6
      //   101: astore 4
      //   103: aload_1
      //   104: astore_3
      //   105: aload_1
      //   106: astore 5
      //   108: new 65	java/io/BufferedWriter
      //   111: dup
      //   112: new 67	java/io/OutputStreamWriter
      //   115: dup
      //   116: aload_1
      //   117: invokevirtual 71	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   120: ldc 73
      //   122: invokespecial 76	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;Ljava/lang/String;)V
      //   125: invokespecial 79	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
      //   128: astore 9
      //   130: aload 6
      //   132: astore 4
      //   134: aload_1
      //   135: astore_3
      //   136: aload_1
      //   137: astore 5
      //   139: aload 9
      //   141: aload 8
      //   143: invokevirtual 84	java/io/Writer:write	(Ljava/lang/String;)V
      //   146: aload 6
      //   148: astore 4
      //   150: aload_1
      //   151: astore_3
      //   152: aload_1
      //   153: astore 5
      //   155: aload 9
      //   157: invokevirtual 87	java/io/Writer:flush	()V
      //   160: aload 6
      //   162: astore 4
      //   164: aload_1
      //   165: astore_3
      //   166: aload_1
      //   167: astore 5
      //   169: aload 9
      //   171: invokevirtual 90	java/io/Writer:close	()V
      //   174: aload 6
      //   176: astore 4
      //   178: aload_1
      //   179: astore_3
      //   180: aload_1
      //   181: astore 5
      //   183: aload_1
      //   184: invokevirtual 94	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   187: astore 9
      //   189: aload 6
      //   191: astore 4
      //   193: aload_1
      //   194: astore_3
      //   195: aload_1
      //   196: astore 5
      //   198: new 96	java/lang/StringBuffer
      //   201: dup
      //   202: invokespecial 97	java/lang/StringBuffer:<init>	()V
      //   205: astore 8
      //   207: aload 9
      //   209: ifnonnull +32 -> 241
      //   212: aload_1
      //   213: ifnull +7 -> 220
      //   216: aload_1
      //   217: invokevirtual 100	java/net/HttpURLConnection:disconnect	()V
      //   220: iconst_0
      //   221: ifeq +11 -> 232
      //   224: new 102	java/lang/NullPointerException
      //   227: dup
      //   228: invokespecial 103	java/lang/NullPointerException:<init>	()V
      //   231: athrow
      //   232: aconst_null
      //   233: areturn
      //   234: astore_1
      //   235: aload_1
      //   236: invokevirtual 106	java/io/IOException:printStackTrace	()V
      //   239: aconst_null
      //   240: areturn
      //   241: aload 6
      //   243: astore 4
      //   245: aload_1
      //   246: astore_3
      //   247: aload_1
      //   248: astore 5
      //   250: new 108	java/io/BufferedReader
      //   253: dup
      //   254: new 110	java/io/InputStreamReader
      //   257: dup
      //   258: aload 9
      //   260: invokespecial 113	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
      //   263: invokespecial 116	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
      //   266: astore 6
      //   268: aload 6
      //   270: invokevirtual 120	java/io/BufferedReader:readLine	()Ljava/lang/String;
      //   273: astore_3
      //   274: aload_3
      //   275: ifnull +72 -> 347
      //   278: aload 8
      //   280: new 122	java/lang/StringBuilder
      //   283: dup
      //   284: invokespecial 123	java/lang/StringBuilder:<init>	()V
      //   287: aload_3
      //   288: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   291: ldc -127
      //   293: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   296: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   299: invokevirtual 135	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   302: pop
      //   303: goto -35 -> 268
      //   306: astore_3
      //   307: aload_1
      //   308: astore 5
      //   310: aload 6
      //   312: astore_1
      //   313: aload_3
      //   314: astore 6
      //   316: aload_1
      //   317: astore 4
      //   319: aload 5
      //   321: astore_3
      //   322: aload 6
      //   324: invokevirtual 106	java/io/IOException:printStackTrace	()V
      //   327: aload 5
      //   329: ifnull +8 -> 337
      //   332: aload 5
      //   334: invokevirtual 100	java/net/HttpURLConnection:disconnect	()V
      //   337: aload_1
      //   338: ifnull +7 -> 345
      //   341: aload_1
      //   342: invokevirtual 136	java/io/BufferedReader:close	()V
      //   345: aconst_null
      //   346: areturn
      //   347: aload 8
      //   349: invokevirtual 140	java/lang/StringBuffer:length	()I
      //   352: istore_2
      //   353: iload_2
      //   354: ifne +31 -> 385
      //   357: aload_1
      //   358: ifnull +7 -> 365
      //   361: aload_1
      //   362: invokevirtual 100	java/net/HttpURLConnection:disconnect	()V
      //   365: aload 6
      //   367: ifnull +8 -> 375
      //   370: aload 6
      //   372: invokevirtual 136	java/io/BufferedReader:close	()V
      //   375: aconst_null
      //   376: areturn
      //   377: astore_1
      //   378: aload_1
      //   379: invokevirtual 106	java/io/IOException:printStackTrace	()V
      //   382: goto -7 -> 375
      //   385: aload 8
      //   387: invokevirtual 141	java/lang/StringBuffer:toString	()Ljava/lang/String;
      //   390: astore_3
      //   391: getstatic 147	java/lang/System:out	Ljava/io/PrintStream;
      //   394: new 122	java/lang/StringBuilder
      //   397: dup
      //   398: invokespecial 123	java/lang/StringBuilder:<init>	()V
      //   401: ldc -107
      //   403: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   406: aload_3
      //   407: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   410: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   413: invokevirtual 154	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   416: new 156	org/json/JSONObject
      //   419: dup
      //   420: aload_3
      //   421: invokespecial 157	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   424: ldc -98
      //   426: invokevirtual 162	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   429: checkcast 164	java/lang/Integer
      //   432: invokevirtual 167	java/lang/Integer:intValue	()I
      //   435: ifne +36 -> 471
      //   438: getstatic 147	java/lang/System:out	Ljava/io/PrintStream;
      //   441: ldc -87
      //   443: invokevirtual 154	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   446: aload_0
      //   447: iconst_0
      //   448: putfield 171	com/achijones/profootballcoach/OnlineActivity$UpdateTeamStratsOnline:success	Z
      //   451: aload_1
      //   452: ifnull +7 -> 459
      //   455: aload_1
      //   456: invokevirtual 100	java/net/HttpURLConnection:disconnect	()V
      //   459: aload 6
      //   461: ifnull +8 -> 469
      //   464: aload 6
      //   466: invokevirtual 136	java/io/BufferedReader:close	()V
      //   469: aload_3
      //   470: areturn
      //   471: aload_0
      //   472: iconst_1
      //   473: putfield 171	com/achijones/profootballcoach/OnlineActivity$UpdateTeamStratsOnline:success	Z
      //   476: goto -25 -> 451
      //   479: astore 4
      //   481: aload 4
      //   483: invokevirtual 172	java/lang/Exception:printStackTrace	()V
      //   486: goto -35 -> 451
      //   489: astore_3
      //   490: aload 6
      //   492: astore 4
      //   494: aload_1
      //   495: ifnull +7 -> 502
      //   498: aload_1
      //   499: invokevirtual 100	java/net/HttpURLConnection:disconnect	()V
      //   502: aload 4
      //   504: ifnull +8 -> 512
      //   507: aload 4
      //   509: invokevirtual 136	java/io/BufferedReader:close	()V
      //   512: aload_3
      //   513: athrow
      //   514: astore_1
      //   515: aload_1
      //   516: invokevirtual 106	java/io/IOException:printStackTrace	()V
      //   519: goto -50 -> 469
      //   522: astore_1
      //   523: aload_1
      //   524: invokevirtual 106	java/io/IOException:printStackTrace	()V
      //   527: goto -182 -> 345
      //   530: astore_1
      //   531: aload_1
      //   532: invokevirtual 106	java/io/IOException:printStackTrace	()V
      //   535: goto -23 -> 512
      //   538: astore 5
      //   540: aload_3
      //   541: astore_1
      //   542: aload 5
      //   544: astore_3
      //   545: goto -51 -> 494
      //   548: astore 6
      //   550: aload 7
      //   552: astore_1
      //   553: goto -237 -> 316
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	556	0	this	UpdateTeamStratsOnline
      //   0	556	1	paramVarArgs	String[]
      //   352	2	2	i	int
      //   9	279	3	localObject1	Object
      //   306	8	3	localIOException1	java.io.IOException
      //   321	149	3	localObject2	Object
      //   489	52	3	localObject3	Object
      //   544	1	3	localObject4	Object
      //   18	300	4	localObject5	Object
      //   479	3	4	localException	Exception
      //   492	16	4	localObject6	Object
      //   6	327	5	arrayOfString	String[]
      //   538	5	5	localObject7	Object
      //   11	480	6	localObject8	Object
      //   548	1	6	localIOException2	java.io.IOException
      //   14	537	7	localObject9	Object
      //   3	383	8	localObject10	Object
      //   128	131	9	localObject11	Object
      // Exception table:
      //   from	to	target	type
      //   224	232	234	java/io/IOException
      //   268	274	306	java/io/IOException
      //   278	303	306	java/io/IOException
      //   347	353	306	java/io/IOException
      //   385	416	306	java/io/IOException
      //   416	451	306	java/io/IOException
      //   471	476	306	java/io/IOException
      //   481	486	306	java/io/IOException
      //   370	375	377	java/io/IOException
      //   416	451	479	java/lang/Exception
      //   471	476	479	java/lang/Exception
      //   268	274	489	finally
      //   278	303	489	finally
      //   347	353	489	finally
      //   385	416	489	finally
      //   416	451	489	finally
      //   471	476	489	finally
      //   481	486	489	finally
      //   464	469	514	java/io/IOException
      //   341	345	522	java/io/IOException
      //   507	512	530	java/io/IOException
      //   20	36	538	finally
      //   45	50	538	finally
      //   59	65	538	finally
      //   74	82	538	finally
      //   91	99	538	finally
      //   108	130	538	finally
      //   139	146	538	finally
      //   155	160	538	finally
      //   169	174	538	finally
      //   183	189	538	finally
      //   198	207	538	finally
      //   250	268	538	finally
      //   322	327	538	finally
      //   20	36	548	java/io/IOException
      //   45	50	548	java/io/IOException
      //   59	65	548	java/io/IOException
      //   74	82	548	java/io/IOException
      //   91	99	548	java/io/IOException
      //   108	130	548	java/io/IOException
      //   139	146	548	java/io/IOException
      //   155	160	548	java/io/IOException
      //   169	174	548	java/io/IOException
      //   183	189	548	java/io/IOException
      //   198	207	548	java/io/IOException
      //   250	268	548	java/io/IOException
    }
    
    protected void onPostExecute(String paramString)
    {
      if (success)
      {
        Toast.makeText(OnlineActivity.this, "Successfully updated strategies.", 0).show();
        return;
      }
      Toast.makeText(OnlineActivity.this, "Something went wrong when updating strategies.", 0).show();
    }
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.OnlineActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */