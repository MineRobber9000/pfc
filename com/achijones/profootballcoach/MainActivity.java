package com.achijones.profootballcoach;

import PFCpack.Division;
import PFCpack.DraftPick;
import PFCpack.Game;
import PFCpack.Injury;
import PFCpack.League;
import PFCpack.LeagueRecords;
import PFCpack.Player;
import PFCpack.Team;
import PFCpack.TeamStrategy;
import PFCpack.Trade;
import PFCpack.TradePiece;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity
  extends AppCompatActivity
{
  List<String> confList;
  int currTab;
  Division currentDivision;
  Team currentTeam;
  Trade currentTrade;
  ArrayAdapter<String> dataAdapterConf;
  ArrayAdapter<String> dataAdapterTeam;
  Spinner examineConfSpinner;
  Spinner examineTeamSpinner;
  ExpandableListView expListPlayerStats;
  final int[] findFilter = { 0, 50, 50, 50, 50, 20, 40, 0 };
  ListView mainList;
  JSONObject onlineTeamDict;
  int recruitingStage;
  File saveLeagueFile;
  String saveLeagueFileStr;
  File saveScheduleFile;
  String saveScheduleFileStr;
  File saveTeamHistFile;
  String saveTeamHistFileStr;
  File saveTeamsFile;
  String saveTeamsFileStr;
  int season;
  boolean showInjuryReport;
  boolean showToasts;
  boolean showTutorial;
  boolean shownTutRoster;
  boolean shownTutSchedule;
  boolean shownTutTeamStats;
  boolean shownTutTrading;
  League simLeague;
  List<String> teamList;
  Team userTeam;
  String userTeamStr;
  int wantUpdateConf;
  
  private void changeTeamNameDialog()
  {
    final Object localObject = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject).setTitle("Settings / Change Name").setView(getLayoutInflater().inflate(2130968608, null));
    localObject = ((AlertDialog.Builder)localObject).create();
    ((AlertDialog)localObject).show();
    final EditText localEditText = (EditText)((AlertDialog)localObject).findViewById(2131558545);
    localEditText.setText(userTeam.name);
    localEditText.addTextChangedListener(new TextWatcher()
    {
      String newName;
      
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        newName = paramAnonymousEditable.toString().trim();
        if (!simLeague.isNameValid(newName))
        {
          val$invalidNameText.setText("Name already in use or has illegal characters!");
          return;
        }
        val$invalidNameText.setText("");
      }
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        newName = paramAnonymousCharSequence.toString().trim();
        if (!simLeague.isNameValid(newName))
        {
          val$invalidNameText.setText("Name already in use or has illegal characters!");
          return;
        }
        val$invalidNameText.setText("");
      }
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        newName = paramAnonymousCharSequence.toString().trim();
        if (!simLeague.isNameValid(newName))
        {
          val$invalidNameText.setText("Name already in use or has illegal characters!");
          return;
        }
        val$invalidNameText.setText("");
      }
    });
    final CheckBox localCheckBox1 = (CheckBox)((AlertDialog)localObject).findViewById(2131558547);
    localCheckBox1.setChecked(showToasts);
    final CheckBox localCheckBox2 = (CheckBox)((AlertDialog)localObject).findViewById(2131558548);
    localCheckBox2.setChecked(showInjuryReport);
    final CheckBox localCheckBox3 = (CheckBox)((AlertDialog)localObject).findViewById(2131558549);
    localCheckBox3.setChecked(showTutorial);
    Button localButton1 = (Button)((AlertDialog)localObject).findViewById(2131558550);
    Button localButton2 = (Button)((AlertDialog)localObject).findViewById(2131558551);
    localButton1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        showToasts = localCheckBox1.isChecked();
        showInjuryReport = localCheckBox2.isChecked();
        showTutorial = localCheckBox3.isChecked();
        userTeam.showPopups = showToasts;
        localObject.dismiss();
      }
    });
    localButton2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = localEditText.getText().toString().trim();
        if (simLeague.isNameValid(paramAnonymousView))
        {
          userTeam.name = paramAnonymousView;
          getSupportActionBar().setTitle(userTeam.name + " " + season + " Season");
          examineTeam(userTeam.name);
        }
        for (;;)
        {
          showToasts = localCheckBox1.isChecked();
          showInjuryReport = localCheckBox2.isChecked();
          showTutorial = localCheckBox3.isChecked();
          userTeam.showPopups = showToasts;
          localObject.dismiss();
          return;
          if (showToasts) {
            Toast.makeText(MainActivity.this, "Invalid name! Name not changed.", 0).show();
          }
        }
      }
    });
  }
  
  private void exitMainActivity()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage("Are you sure you want to return to main menu? Progress will be saved.").setPositiveButton("Yes, Exit", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MainActivity.this.saveLeagueOnFile();
        paramAnonymousDialogInterface = new Intent(MainActivity.this, Home.class);
        startActivity(paramAnonymousDialogInterface);
      }
    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    localBuilder.create().show();
  }
  
  private String[] getOnlineTeams()
  {
    String[] arrayOfString = new String[5];
    int i = 0;
    if (i < 5)
    {
      Object localObject = new File(getFilesDir(), "onlineTeam" + i + ".cfb");
      if (((File)localObject).exists()) {}
      for (;;)
      {
        try
        {
          localObject = new BufferedReader(new FileReader((File)localObject)).readLine();
          arrayOfString[i] = ((String)localObject).substring(0, ((String)localObject).length());
          i += 1;
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          System.out.println("Unable to open file");
          continue;
        }
        catch (IOException localIOException)
        {
          System.out.println("Error reading file");
          continue;
        }
        catch (NullPointerException localNullPointerException)
        {
          System.out.println("Null pointer exception!");
          continue;
        }
        arrayOfString[i] = "EMPTY";
      }
    }
    return arrayOfString;
  }
  
  private String[] getSaveFileInfos()
  {
    String[] arrayOfString = new String[10];
    int i = 0;
    if (i < 10)
    {
      Object localObject = new File(getFilesDir(), "saveFile" + i + ".cfb");
      if (((File)localObject).exists()) {}
      for (;;)
      {
        try
        {
          localObject = new BufferedReader(new FileReader((File)localObject)).readLine();
          arrayOfString[i] = ((String)localObject).substring(0, ((String)localObject).length() - 1);
          i += 1;
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          System.out.println("Unable to open file");
          continue;
        }
        catch (IOException localIOException)
        {
          System.out.println("Error reading file");
          continue;
        }
        arrayOfString[i] = "EMPTY";
      }
    }
    return arrayOfString;
  }
  
  private void mvpCeremony()
  {
    Object localObject = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject).setTitle("Regular Season Summary").setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).setView(getLayoutInflater().inflate(2130968665, null));
    AlertDialog localAlertDialog = ((AlertDialog.Builder)localObject).create();
    localAlertDialog.show();
    if (simLeague.currentWeek < 16)
    {
      localObject = new String[1];
      localObject[0] = "MVP";
    }
    for (;;)
    {
      Spinner localSpinner = (Spinner)localAlertDialog.findViewById(2131558689);
      localObject = new ArrayAdapter(this, 17367048, (Object[])localObject);
      ((ArrayAdapter)localObject).setDropDownViewResource(17367049);
      localSpinner.setAdapter((SpinnerAdapter)localObject);
      localSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
      {
        public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          if (paramAnonymousInt == 0) {
            val$potyList.setAdapter(new SeasonAwardsListArrayAdapter(MainActivity.this, simLeague.getMVPCeremonyStr().split(">"), userTeam.abbr));
          }
          do
          {
            return;
            if (paramAnonymousInt == 1)
            {
              val$potyList.setAdapter(new SeasonAwardsListArrayAdapter(MainActivity.this, val$allPros, userTeam.abbr));
              return;
            }
            if (paramAnonymousInt == 2)
            {
              val$potyList.setAdapter(new TeamRankingsListArrayAdapter(MainActivity.this, simLeague.getTeamRankingsStr(1), userTeam.strRepWithBowlResults()));
              return;
            }
          } while (paramAnonymousInt != 3);
          val$potyList.setAdapter(new TeamRankingsListArrayAdapter(MainActivity.this, simLeague.getTeamRankingsStr(2), userTeam.strRepWithBowlResults()));
        }
        
        public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
      });
      return;
      localObject = new String[4];
      localObject[0] = "MVP";
      localObject[1] = "All Pro";
      localObject[2] = "Playoff Picture";
      localObject[3] = "Division Standings";
    }
  }
  
  private void saveLeague()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle("Choose Save File to Overwrite:");
    final String[] arrayOfString = getSaveFileInfos();
    localBuilder.setAdapter(new SaveFilesListArrayAdapter(this, arrayOfString), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, final int paramAnonymousInt)
      {
        if (arrayOfString[paramAnonymousInt].equals("EMPTY"))
        {
          saveLeagueFile = new File(getFilesDir(), "saveFile" + paramAnonymousInt + ".cfb");
          saveTeamsFile = new File(getFilesDir(), "saveFile" + paramAnonymousInt + "_teams.cfb");
          saveScheduleFile = new File(getFilesDir(), "saveFile" + paramAnonymousInt + "_schedules.cfb");
          saveTeamHistFile = new File(getFilesDir(), "saveFile" + paramAnonymousInt + "_teamhist.cfb");
          simLeague.saveLeague(saveLeagueFile, saveTeamsFile, saveScheduleFile, saveTeamHistFile, false);
          Toast.makeText(MainActivity.this, "Saved league!", 0).show();
          paramAnonymousDialogInterface.dismiss();
          return;
        }
        paramAnonymousDialogInterface = new AlertDialog.Builder(MainActivity.this);
        paramAnonymousDialogInterface.setMessage("Are you sure you want to overwrite this save file?\n\n" + arrayOfString[paramAnonymousInt]).setPositiveButton("Yes, Overwrite", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            saveLeagueFile = new File(getFilesDir(), "saveFile" + paramAnonymousInt + ".cfb");
            saveTeamsFile = new File(getFilesDir(), "saveFile" + paramAnonymousInt + "_teams.cfb");
            saveScheduleFile = new File(getFilesDir(), "saveFile" + paramAnonymousInt + "_schedules.cfb");
            saveTeamHistFile = new File(getFilesDir(), "saveFile" + paramAnonymousInt + "_teamhist.cfb");
            simLeague.saveLeague(saveLeagueFile, saveTeamsFile, saveScheduleFile, saveTeamHistFile, false);
            Toast.makeText(MainActivity.this, "Saved league!", 0).show();
            paramAnonymous2DialogInterface.dismiss();
          }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface.dismiss();
          }
        });
        paramAnonymousDialogInterface = paramAnonymousDialogInterface.create();
        paramAnonymousDialogInterface.show();
        ((TextView)paramAnonymousDialogInterface.findViewById(16908299)).setTextSize(2, 14.0F);
      }
    });
    localBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    localBuilder.create().show();
  }
  
  private void saveLeagueOnFile()
  {
    simLeague.saveLeague(saveLeagueFile, saveTeamsFile, saveScheduleFile, saveTeamHistFile, false);
    Toast.makeText(this, "Saved league!", 0).show();
  }
  
  private void scrollToLatestGame()
  {
    if ((currTab == 2) && (simLeague.currentWeek > 2)) {
      mainList.setSelection(currentTeam.numGames() - 3);
    }
  }
  
  private void setShowTutorial(boolean paramBoolean)
  {
    showTutorial = paramBoolean;
  }
  
  private void showTeamLineupDialog()
  {
    final Object localObject1 = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject1).setTitle("Set Team Lineup").setView(getLayoutInflater().inflate(2130968663, null));
    localObject1 = ((AlertDialog.Builder)localObject1).create();
    ((AlertDialog)localObject1).show();
    final String[] arrayOfString = new String[9];
    arrayOfString[0] = "QB (1 starter)";
    arrayOfString[1] = "RB (2 starters)";
    arrayOfString[2] = "WR (3 starters)";
    arrayOfString[3] = "OL (5 starters)";
    arrayOfString[4] = "K (1 starter)";
    arrayOfString[5] = "S (1 starter)";
    arrayOfString[6] = "CB (3 starters)";
    arrayOfString[7] = "DL (4 starters)";
    arrayOfString[8] = "LB (3 starters";
    final int[] arrayOfInt = new int[9];
    int[] tmp109_108 = arrayOfInt;
    tmp109_108[0] = 1;
    int[] tmp113_109 = tmp109_108;
    tmp113_109[1] = 2;
    int[] tmp117_113 = tmp113_109;
    tmp117_113[2] = 3;
    int[] tmp121_117 = tmp117_113;
    tmp121_117[3] = 5;
    int[] tmp125_121 = tmp121_117;
    tmp125_121[4] = 1;
    int[] tmp129_125 = tmp125_121;
    tmp129_125[5] = 1;
    int[] tmp133_129 = tmp129_125;
    tmp133_129[6] = 3;
    int[] tmp138_133 = tmp133_129;
    tmp138_133[7] = 4;
    int[] tmp143_138 = tmp138_133;
    tmp143_138[8] = 3;
    tmp143_138;
    final Spinner localSpinner = (Spinner)((AlertDialog)localObject1).findViewById(2131558682);
    final Object localObject2 = new ArrayAdapter(this, 17367048, arrayOfString);
    ((ArrayAdapter)localObject2).setDropDownViewResource(17367049);
    localSpinner.setAdapter((SpinnerAdapter)localObject2);
    localObject2 = (TextView)((AlertDialog)localObject1).findViewById(2131558683);
    final ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(userTeam.teamQBs);
    Object localObject3 = (ListView)((AlertDialog)localObject1).findViewById(2131558684);
    final TeamLineupArrayAdapter localTeamLineupArrayAdapter = new TeamLineupArrayAdapter(this, localArrayList, 1);
    ((ListView)localObject3).setAdapter(localTeamLineupArrayAdapter);
    localSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        MainActivity.this.updateLineupList(paramAnonymousInt, localTeamLineupArrayAdapter, arrayOfInt, localArrayList, localObject2);
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
    localObject3 = (Button)((AlertDialog)localObject1).findViewById(2131558685);
    ((Button)((AlertDialog)localObject1).findViewById(2131558686)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localObject1.dismiss();
        MainActivity.this.updateCurrTeam();
      }
    });
    ((Button)localObject3).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        int i = localSpinner.getSelectedItemPosition();
        if (localTeamLineupArrayAdapterplayersSelected.size() == localTeamLineupArrayAdapterplayersRequired)
        {
          userTeam.setStarters(localTeamLineupArrayAdapterplayersSelected, i);
          MainActivity.this.updateLineupList(i, localTeamLineupArrayAdapter, arrayOfInt, localArrayList, localObject2);
          Toast.makeText(MainActivity.this, "Saved lineup for " + arrayOfString[i] + "!", 0).show();
          return;
        }
        Toast.makeText(MainActivity.this, localTeamLineupArrayAdapterplayersSelected.size() + " players selected.\nNot the correct number of starters (" + localTeamLineupArrayAdapterplayersRequired + ")", 0).show();
      }
    });
  }
  
  private void showTeamStrategyDialog()
  {
    final Object localObject1 = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject1).setTitle("Team Strategy").setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
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
  
  private void showTutorialDialog(String paramString1, String paramString2)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(paramString1).setPositiveButton("Ok", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    }).setView(getLayoutInflater().inflate(2130968673, null));
    paramString1 = localBuilder.create();
    paramString1.show();
    ((TextView)paramString1.findViewById(2131558714)).setText(paramString2);
    paramString1 = (CheckBox)paramString1.findViewById(2131558549);
    paramString1.setChecked(showTutorial);
    paramString1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        MainActivity.this.setShowTutorial(paramAnonymousBoolean);
      }
    });
  }
  
  private void updateCurrDivision()
  {
    if (wantUpdateConf >= 1)
    {
      teamList = new ArrayList();
      dataAdapterTeam.clear();
      int i = 0;
      while (i < currentDivision.divTeams.size())
      {
        teamList.add(((Team)currentDivision.divTeams.get(i)).strRep());
        dataAdapterTeam.add(teamList.get(i));
        i += 1;
      }
      dataAdapterTeam.notifyDataSetChanged();
      examineTeamSpinner.setSelection(0);
      currentTeam = ((Team)currentDivision.divTeams.get(0));
      updateCurrTeam();
      return;
    }
    wantUpdateConf += 1;
  }
  
  private void updateCurrTeam()
  {
    teamList = new ArrayList();
    dataAdapterTeam.clear();
    int i = 0;
    while (i < currentDivision.divTeams.size())
    {
      teamList.add(((Team)currentDivision.divTeams.get(i)).strRep());
      dataAdapterTeam.add(teamList.get(i));
      i += 1;
    }
    dataAdapterTeam.notifyDataSetChanged();
    ((TextView)findViewById(2131558570)).setText(currentTeam.name + " (" + currentTeam.wins + "-" + currentTeam.losses + ") " + currentTeam.divChampion + " " + currentTeam.natChampWL);
    if (currTab == 0)
    {
      updateTeamStats();
      return;
    }
    if (currTab == 1)
    {
      updatePlayerStats();
      return;
    }
    if (currTab == 2)
    {
      updateSchedule();
      return;
    }
    updateTradeBlock();
  }
  
  private void updateLineupList(int paramInt, TeamLineupArrayAdapter paramTeamLineupArrayAdapter, int[] paramArrayOfInt, ArrayList<Player> paramArrayList, TextView paramTextView)
  {
    playersRequired = paramArrayOfInt[paramInt];
    playersSelected.clear();
    players.clear();
    paramArrayList.clear();
    switch (paramInt)
    {
    }
    for (;;)
    {
      paramInt = 0;
      while (paramInt < playersRequired)
      {
        playersSelected.add(paramArrayList.get(paramInt));
        paramInt += 1;
      }
      paramTextView.setText("Name [Yr] Ovr/Pot (Str, Acc, Eva)");
      paramArrayList.addAll(userTeam.teamQBs);
      continue;
      paramTextView.setText("Name [Yr] Ovr/Pot (Pow, Spd, Eva)");
      paramArrayList.addAll(userTeam.teamRBs);
      continue;
      paramTextView.setText("Name [Yr] Ovr/Pot (Cat, Spd, Eva)");
      paramArrayList.addAll(userTeam.teamWRs);
      continue;
      paramTextView.setText("Name [Yr] Ovr/Pot (Str, RunBlk, PassBlk)");
      paramArrayList.addAll(userTeam.teamOLs);
      continue;
      paramTextView.setText("Name [Yr] Ovr/Pot (KStr, KAcc, Clum)");
      paramArrayList.addAll(userTeam.teamKs);
      continue;
      paramTextView.setText("Name [Yr] Ovr/Pot (Cov, Spd, Tack)");
      paramArrayList.addAll(userTeam.teamSs);
      continue;
      paramTextView.setText("Name [Yr] Ovr/Pot (Cov, Spd, Tack)");
      paramArrayList.addAll(userTeam.teamCBs);
      continue;
      paramTextView.setText("Name [Yr] Ovr/Pot (Str, RunDef, PassDef)");
      paramArrayList.addAll(userTeam.teamDLs);
      continue;
      paramTextView.setText("Name [Yr] Ovr/Pot (Tack, RunDef, PassCov)");
      paramArrayList.addAll(userTeam.teamLBs);
    }
    paramTeamLineupArrayAdapter.notifyDataSetChanged();
  }
  
  private void updatePlayerStats()
  {
    mainList.setVisibility(8);
    expListPlayerStats.setVisibility(0);
    final Object localObject = currentTeam.getPlayerStatsExpandListStr();
    localObject = new ExpandableListAdapterPlayerStats(this, this, (List)localObject, currentTeam.getPlayerStatsExpandListMap((List)localObject));
    expListPlayerStats.setAdapter((ExpandableListAdapter)localObject);
    expListPlayerStats.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
    {
      public boolean onChildClick(ExpandableListView paramAnonymousExpandableListView, View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2, long paramAnonymousLong)
      {
        if (localObject.getGroup(paramAnonymousInt1).equals("BENCH > BENCH")) {
          examinePlayer(localObject.getChild(paramAnonymousInt1, paramAnonymousInt2));
        }
        for (;;)
        {
          return false;
          if (localObject.getGroup(paramAnonymousInt1).equals("DRAFT PICKS > DRAFT PICKS")) {
            addTradePick(localObject.getChild(paramAnonymousInt1, paramAnonymousInt2));
          }
        }
      }
    });
    expListPlayerStats.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
    {
      public boolean onItemLongClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        if (ExpandableListView.getPackedPositionType(paramAnonymousLong) == 1)
        {
          paramAnonymousInt = ExpandableListView.getPackedPositionGroup(paramAnonymousLong);
          int i = ExpandableListView.getPackedPositionChild(paramAnonymousLong);
          if (localObject.getGroup(paramAnonymousInt).equals("BENCH > BENCH")) {
            addTradePlayer(localObject.getChild(paramAnonymousInt, i));
          }
          while (!localObject.getGroup(paramAnonymousInt).equals("DRAFT PICKS > DRAFT PICKS")) {
            return true;
          }
          addTradePick(localObject.getChild(paramAnonymousInt, i));
          return true;
        }
        return false;
      }
    });
  }
  
  private void updateSchedule()
  {
    mainList.setVisibility(0);
    expListPlayerStats.setVisibility(8);
    Game[] arrayOfGame = new Game[currentTeam.gameSchedule.size()];
    int i = 0;
    while (i < arrayOfGame.length)
    {
      arrayOfGame[i] = ((Game)currentTeam.gameSchedule.get(i));
      i += 1;
    }
    mainList.setAdapter(new GameScheduleListArrayAdapter(this, this, currentTeam, arrayOfGame));
  }
  
  private void updateTeamStats()
  {
    mainList.setVisibility(0);
    expListPlayerStats.setVisibility(8);
    String[] arrayOfString = currentTeam.getTeamStatsStrCSV().split("%\n");
    mainList.setAdapter(new TeamStatsListArrayAdapter(this, this, arrayOfString));
  }
  
  private void updateTradeBlock()
  {
    mainList.setVisibility(0);
    expListPlayerStats.setVisibility(8);
    if (currentTeam.tradingBlock.size() > 0)
    {
      TradePiece[] arrayOfTradePiece = new TradePiece[currentTeam.tradingBlock.size() + 1];
      int i = 0;
      while (i < arrayOfTradePiece.length - 1)
      {
        arrayOfTradePiece[i] = ((TradePiece)currentTeam.tradingBlock.get(i));
        i += 1;
      }
      arrayOfTradePiece[(arrayOfTradePiece.length - 1)] = null;
      mainList.setAdapter(new TradingBlockListArrayAdapter(this, this, userTeam, currentTeam, arrayOfTradePiece));
      return;
    }
    mainList.setAdapter(new PlayerStatsListArrayAdapter(this, new String[] { "Nothing on trade block. Click TRADE on starters or hold down on bench players and draft picks to add them. > " }));
  }
  
  public void addTradePick(String paramString)
  {
    if ((simLeague.currentWeek <= 7) && (currentTeam.tradingBlock.size() < 5))
    {
      DraftPick localDraftPick = currentTeam.findPick(paramString);
      paramString = localDraftPick;
      if (localDraftPick == null) {
        paramString = (DraftPick)currentTeam.draftPicks.get(0);
      }
      if (!currentTeam.tradingBlock.contains(paramString)) {
        currentTeam.tradingBlock.add(paramString);
      }
      Toast.makeText(this, "Added " + paramString.getStrRep() + " to trading block.", 0).show();
      return;
    }
    if (currentTeam.tradingBlock.size() >= 5)
    {
      Toast.makeText(this, "Cannot trade more than 5 players/picks at a time.", 0).show();
      return;
    }
    Toast.makeText(this, "Trades are not allowed past the deadline.", 0).show();
  }
  
  public void addTradePlayer(String paramString)
  {
    if (simLeague.currentWeek <= 7)
    {
      Player localPlayer = currentTeam.findBenchPlayer(paramString);
      paramString = localPlayer;
      if (localPlayer == null) {
        paramString = currentTeam.getQB(0);
      }
      if (paramString.isInjured())
      {
        Toast.makeText(this, "Injured players cannot be traded.", 0).show();
        return;
      }
      if ((userTeam != currentTeam) && (Trade.checkUntradable(currentTeam, paramString)))
      {
        Toast.makeText(this, currentTeam.abbr + " has no desire to trade " + paramString.getPosNameYrOvrPot_OneLine() + ".", 0).show();
        return;
      }
      if (currentTeam.tradingBlock.size() >= 5)
      {
        Toast.makeText(this, "Cannot trade more than 5 players/picks at a time.", 0).show();
        return;
      }
      if (!currentTeam.tradingBlock.contains(paramString)) {
        currentTeam.tradingBlock.add(paramString);
      }
      Toast.makeText(this, "Added " + paramString.getPosNameYrOvrPot_OneLine() + " to trading block.", 0).show();
      return;
    }
    Toast.makeText(this, "Trades are not allowed past the deadline.", 0).show();
  }
  
  public void advanceOffSeasonDialog()
  {
    simLeague.getPlayersLeaving();
    Object localObject1 = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject1).setTitle(userTeam.abbr + " Players Leaving").setPositiveButton("Go to Offseason", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        advanceOffSeasonLeague();
      }
    }).setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        advanceOffSeasonLeague();
      }
    }).setView(getLayoutInflater().inflate(2130968665, null));
    final Object localObject2 = ((AlertDialog.Builder)localObject1).create();
    ((AlertDialog)localObject2).show();
    localObject1 = (Spinner)((AlertDialog)localObject2).findViewById(2131558689);
    final Object localObject3 = new ArrayAdapter(this, 17367048, new String[] { "Players Retiring", "Team Free Agents" });
    ((ArrayAdapter)localObject3).setDropDownViewResource(17367049);
    ((Spinner)localObject1).setAdapter((SpinnerAdapter)localObject3);
    localObject2 = (ListView)((AlertDialog)localObject2).findViewById(2131558690);
    localObject3 = new PlayerStatsListArrayAdapter(this, userTeam.getPlayersRetiringList());
    final PlayerStatsListArrayAdapter localPlayerStatsListArrayAdapter = new PlayerStatsListArrayAdapter(this, userTeam.getPlayersFAList());
    ((ListView)localObject2).setAdapter((ListAdapter)localObject3);
    ((Spinner)localObject1).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        if (paramAnonymousInt == 0)
        {
          localObject2.setAdapter(localObject3);
          return;
        }
        localObject2.setAdapter(localPlayerStatsListArrayAdapter);
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
  }
  
  public void advanceOffSeasonLeague()
  {
    simLeague.advanceSeason();
    Object localObject = new File(getFilesDir(), "offseason_" + saveLeagueFileStr);
    File localFile1 = new File(getFilesDir(), "offseason_" + saveTeamsFileStr);
    File localFile2 = new File(getFilesDir(), "offseason_" + saveScheduleFileStr);
    File localFile3 = new File(getFilesDir(), "offseason_" + saveTeamHistFileStr);
    simLeague.saveLeague((File)localObject, localFile1, localFile2, localFile3, true);
    localObject = new Intent(this, OffSeasonActivity.class);
    ((Intent)localObject).putExtra("SAVE_FILE_OFFSEASON", "OFFSEASON,offseason_" + saveLeagueFileStr + ",offseason_" + saveTeamsFileStr + ",offseason_" + saveScheduleFileStr + ",offseason_" + saveTeamHistFileStr);
    ((Intent)localObject).putExtra("SAVE_FILE", "REGULAR," + saveLeagueFileStr + "," + saveTeamsFileStr + "," + saveScheduleFileStr + "," + saveTeamHistFileStr);
    startActivity((Intent)localObject);
  }
  
  public int checkAwardPlayer(String paramString)
  {
    paramString = currentTeam.findBenchPlayer(paramString);
    if (paramString == null) {}
    do
    {
      return 0;
      if (paramString.hasWonMVP()) {
        return 2;
      }
    } while (!paramString.hasWonAllPro());
    return 1;
  }
  
  public void examinePlayer(Player paramPlayer)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    ArrayList localArrayList = paramPlayer.getDetailAllStatsList(currentTeam.numGames());
    if (paramPlayer.getInjury() != null) {
      localArrayList.add(0, "[I]Injured: " + paramPlayer.getInjury().toString());
    }
    localArrayList.add(0, "[B]" + paramPlayer.getAgeOvrPot_Str());
    localBuilder.setAdapter(new PlayerStatsListArrayAdapter(this, (String[])localArrayList.toArray(new String[localArrayList.size()])), null).setTitle(paramPlayer.getPosition() + " " + paramPlayer.getName()).setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    localBuilder.create().show();
  }
  
  public void examinePlayer(String paramString)
  {
    Player localPlayer = currentTeam.findBenchPlayer(paramString);
    paramString = localPlayer;
    if (localPlayer == null) {
      paramString = currentTeam.getQB(0);
    }
    examinePlayer(paramString);
  }
  
  public void examineTeam(String paramString)
  {
    wantUpdateConf = 0;
    Team localTeam2 = (Team)simLeague.teamList.get(0);
    Iterator localIterator = simLeague.teamList.iterator();
    Team localTeam1;
    do
    {
      localTeam1 = localTeam2;
      if (!localIterator.hasNext()) {
        break;
      }
      localTeam1 = (Team)localIterator.next();
    } while (!name.equals(paramString));
    currentTeam = localTeam1;
    int i = 0;
    for (;;)
    {
      if (i < simLeague.divisions.size())
      {
        paramString = (Division)simLeague.divisions.get(i);
        if (divName.equals(currentTeam.division))
        {
          if (paramString == currentDivision) {
            wantUpdateConf = 1;
          }
          currentDivision = paramString;
          examineConfSpinner.setSelection(i);
        }
      }
      else
      {
        teamList = new ArrayList();
        dataAdapterTeam.clear();
        i = 0;
        while (i < currentDivision.divTeams.size())
        {
          teamList.add(((Team)currentDivision.divTeams.get(i)).strRep());
          dataAdapterTeam.add(teamList.get(i));
          i += 1;
        }
      }
      i += 1;
    }
    dataAdapterTeam.notifyDataSetChanged();
    i = 0;
    for (;;)
    {
      if (i < currentDivision.divTeams.size())
      {
        if (!((String)dataAdapterTeam.getItem(i)).split(" ")[1].equals(abbr)) {
          break label444;
        }
        examineTeamSpinner.setSelection(i);
        currentTeam = localTeam1;
        ((TextView)findViewById(2131558570)).setText(currentTeam.name + " (" + currentTeam.wins + "-" + currentTeam.losses + ") " + currentTeam.divChampion + " " + currentTeam.natChampWL);
        if (currTab == 0) {
          updateTeamStats();
        }
      }
      else
      {
        return;
      }
      if (currTab == 1)
      {
        updatePlayerStats();
        return;
      }
      if (currTab == 2)
      {
        updateSchedule();
        return;
      }
      updateTradeBlock();
      return;
      label444:
      i += 1;
    }
  }
  
  public void examineTeamRoster(String paramString)
  {
    examineTeam(paramString);
    currTab = 1;
    updatePlayerStats();
  }
  
  public void findPlayers(int[] paramArrayOfInt)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = simLeague.teamList.iterator();
    while (((Iterator)localObject).hasNext()) {
      localArrayList.addAll(((Team)((Iterator)localObject).next()).getPosList(paramArrayOfInt[0]));
    }
    int i = 0;
    while (i < localArrayList.size())
    {
      localObject = (Player)localArrayList.get(i);
      if (((Player)localObject).getRatOvr() < paramArrayOfInt[1]) {
        localArrayList.remove(i);
      } else if (((Player)localObject).getRatPot() < paramArrayOfInt[2]) {
        localArrayList.remove(i);
      } else if (((Player)localObject).getRatDur() < paramArrayOfInt[3]) {
        localArrayList.remove(i);
      } else if (((Player)localObject).getRatFootIQ() < paramArrayOfInt[4]) {
        localArrayList.remove(i);
      } else if (((Player)localObject).getAge() < paramArrayOfInt[5]) {
        localArrayList.remove(i);
      } else if (((Player)localObject).getAge() > paramArrayOfInt[6]) {
        localArrayList.remove(i);
      } else if ((((Player)localObject).isInjured()) && (paramArrayOfInt[7] == 1)) {
        localArrayList.remove(i);
      } else {
        i += 1;
      }
    }
    Collections.sort(localArrayList, new PlayerComparatorNoInjury());
    localObject = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject).setTitle("Player Finder Results").setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    }).setNegativeButton("Change Search", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
        showPlayerFinderDialog();
      }
    });
    paramArrayOfInt = new ExpandableListView(this);
    ((AlertDialog.Builder)localObject).setView(paramArrayOfInt);
    localObject = ((AlertDialog.Builder)localObject).create();
    ((AlertDialog)localObject).show();
    paramArrayOfInt.setAdapter(new ExpandableListAdapterPlayerFinder(this, this, localArrayList, (AlertDialog)localObject));
  }
  
  public void goOnline()
  {
    final Object localObject1 = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject1).setTitle("Send Team Online").setView(getLayoutInflater().inflate(2130968659, null));
    localObject1 = ((AlertDialog.Builder)localObject1).create();
    ((AlertDialog)localObject1).show();
    final Object localObject4 = userTeam.getTeamStrategiesOff();
    final Object localObject2 = userTeam.getTeamStrategiesDef();
    int j = 0;
    int k = 0;
    final Object localObject3 = new String[localObject4.length];
    int i = 0;
    while (i < localObject4.length)
    {
      localObject3[i] = localObject4[i].getStratName();
      if (localObject3[i].equals(userTeam.teamStratOff.getStratName())) {
        j = i;
      }
      i += 1;
    }
    localObject4 = new String[localObject2.length];
    i = 0;
    while (i < localObject2.length)
    {
      localObject4[i] = localObject2[i].getStratName();
      if (localObject4[i].equals(userTeam.teamStratDef.getStratName())) {
        k = i;
      }
      i += 1;
    }
    localObject2 = (Spinner)((AlertDialog)localObject1).findViewById(2131558628);
    localObject3 = new ArrayAdapter(this, 17367048, (Object[])localObject3);
    ((ArrayAdapter)localObject3).setDropDownViewResource(17367049);
    ((Spinner)localObject2).setAdapter((SpinnerAdapter)localObject3);
    ((Spinner)localObject2).setSelection(j);
    localObject3 = (Spinner)((AlertDialog)localObject1).findViewById(2131558630);
    localObject4 = new ArrayAdapter(this, 17367048, (Object[])localObject4);
    ((ArrayAdapter)localObject4).setDropDownViewResource(17367049);
    ((Spinner)localObject3).setAdapter((SpinnerAdapter)localObject4);
    ((Spinner)localObject3).setSelection(k);
    localObject4 = (EditText)((AlertDialog)localObject1).findViewById(2131558673);
    ((EditText)localObject4).setText(userTeam.name);
    final EditText localEditText = (EditText)((AlertDialog)localObject1).findViewById(2131558675);
    localEditText.setText(userTeam.abbr);
    Button localButton1 = (Button)((AlertDialog)localObject1).findViewById(2131558677);
    Button localButton2 = (Button)((AlertDialog)localObject1).findViewById(2131558678);
    localButton1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localObject1.dismiss();
      }
    });
    localButton2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = localObject4.getText().toString().trim();
        Object localObject = localEditText.getText().toString().trim();
        if ((simLeague.isNameValidOnline(paramAnonymousView)) && (simLeague.isAbbrValidOnline((String)localObject)))
        {
          onlineTeamDict = new JSONObject();
          try
          {
            onlineTeamDict.put("name", paramAnonymousView);
            onlineTeamDict.put("abbr", localObject);
            onlineTeamDict.put("off strat", localObject2.getSelectedItemPosition());
            onlineTeamDict.put("def strat", localObject3.getSelectedItemPosition());
            onlineTeamDict.put("players", userTeam.getPlayersCSV_Online());
            localObject = new JSONObject();
          }
          catch (JSONException localJSONException)
          {
            try
            {
              ((JSONObject)localObject).put("name", paramAnonymousView);
              new MainActivity.CheckNameUniqueOnline(MainActivity.this).execute(new String[] { ((JSONObject)localObject).toString() });
              localObject1.dismiss();
              return;
              localJSONException = localJSONException;
              localJSONException.printStackTrace();
            }
            catch (JSONException paramAnonymousView)
            {
              for (;;)
              {
                paramAnonymousView.printStackTrace();
              }
            }
          }
        }
        Toast.makeText(MainActivity.this, "Invalid name or abbr! No special characters are allowed. Name max len = 20, Abbr max len = 5.", 1).show();
      }
    });
  }
  
  /* Error */
  public void insertTeamOnline(int paramInt, String paramString)
  {
    // Byte code:
    //   0: new 423	java/io/File
    //   3: dup
    //   4: aload_0
    //   5: invokevirtual 427	com/achijones/profootballcoach/MainActivity:getFilesDir	()Ljava/io/File;
    //   8: new 429	java/lang/StringBuilder
    //   11: dup
    //   12: invokespecial 430	java/lang/StringBuilder:<init>	()V
    //   15: ldc_w 432
    //   18: invokevirtual 436	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   21: iload_1
    //   22: invokevirtual 439	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   25: ldc_w 441
    //   28: invokevirtual 436	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: invokevirtual 445	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   34: invokespecial 448	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   37: astore_3
    //   38: new 1163	java/io/BufferedWriter
    //   41: dup
    //   42: new 1165	java/io/OutputStreamWriter
    //   45: dup
    //   46: new 1167	java/io/FileOutputStream
    //   49: dup
    //   50: aload_3
    //   51: invokespecial 1168	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   54: ldc_w 1170
    //   57: invokespecial 1173	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;Ljava/lang/String;)V
    //   60: invokespecial 1176	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   63: astore 5
    //   65: aconst_null
    //   66: astore 4
    //   68: aload 5
    //   70: aload_0
    //   71: getfield 1178	com/achijones/profootballcoach/MainActivity:onlineTeamDict	Lorg/json/JSONObject;
    //   74: ldc_w 1179
    //   77: invokevirtual 1184	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   80: invokevirtual 1187	java/lang/Object:toString	()Ljava/lang/String;
    //   83: invokevirtual 1192	java/io/Writer:write	(Ljava/lang/String;)V
    //   86: aload 5
    //   88: ifnull +12 -> 100
    //   91: iconst_0
    //   92: ifeq +68 -> 160
    //   95: aload 5
    //   97: invokevirtual 1195	java/io/Writer:close	()V
    //   100: aload_2
    //   101: ldc_w 1197
    //   104: invokevirtual 682	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   107: ifeq +104 -> 211
    //   110: new 183	com/achijones/profootballcoach/MainActivity$InsertTeamOnline
    //   113: dup
    //   114: aload_0
    //   115: invokespecial 1198	com/achijones/profootballcoach/MainActivity$InsertTeamOnline:<init>	(Lcom/achijones/profootballcoach/MainActivity;)V
    //   118: iconst_1
    //   119: anewarray 421	java/lang/String
    //   122: dup
    //   123: iconst_0
    //   124: aload_0
    //   125: getfield 1178	com/achijones/profootballcoach/MainActivity:onlineTeamDict	Lorg/json/JSONObject;
    //   128: invokevirtual 1199	org/json/JSONObject:toString	()Ljava/lang/String;
    //   131: aastore
    //   132: invokevirtual 1203	com/achijones/profootballcoach/MainActivity$InsertTeamOnline:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   135: pop
    //   136: return
    //   137: astore_3
    //   138: new 419	java/lang/NullPointerException
    //   141: dup
    //   142: invokespecial 1204	java/lang/NullPointerException:<init>	()V
    //   145: athrow
    //   146: astore_3
    //   147: getstatic 479	java/lang/System:out	Ljava/io/PrintStream;
    //   150: aload_3
    //   151: invokevirtual 1205	java/lang/Exception:toString	()Ljava/lang/String;
    //   154: invokevirtual 487	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   157: goto -57 -> 100
    //   160: aload 5
    //   162: invokevirtual 1195	java/io/Writer:close	()V
    //   165: goto -65 -> 100
    //   168: astore 4
    //   170: aload 4
    //   172: athrow
    //   173: astore_3
    //   174: aload 5
    //   176: ifnull +13 -> 189
    //   179: aload 4
    //   181: ifnull +22 -> 203
    //   184: aload 5
    //   186: invokevirtual 1195	java/io/Writer:close	()V
    //   189: aload_3
    //   190: athrow
    //   191: astore 5
    //   193: aload 4
    //   195: aload 5
    //   197: invokevirtual 1209	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   200: goto -11 -> 189
    //   203: aload 5
    //   205: invokevirtual 1195	java/io/Writer:close	()V
    //   208: goto -19 -> 189
    //   211: new 1181	org/json/JSONObject
    //   214: dup
    //   215: invokespecial 1210	org/json/JSONObject:<init>	()V
    //   218: astore_3
    //   219: aload_3
    //   220: ldc_w 1179
    //   223: aload_2
    //   224: invokevirtual 1214	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   227: pop
    //   228: new 186	com/achijones/profootballcoach/MainActivity$RemoveAndInsertTeamOnline
    //   231: dup
    //   232: aload_0
    //   233: invokespecial 1215	com/achijones/profootballcoach/MainActivity$RemoveAndInsertTeamOnline:<init>	(Lcom/achijones/profootballcoach/MainActivity;)V
    //   236: iconst_1
    //   237: anewarray 421	java/lang/String
    //   240: dup
    //   241: iconst_0
    //   242: aload_3
    //   243: invokevirtual 1199	org/json/JSONObject:toString	()Ljava/lang/String;
    //   246: aastore
    //   247: invokevirtual 1216	com/achijones/profootballcoach/MainActivity$RemoveAndInsertTeamOnline:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   250: pop
    //   251: return
    //   252: astore_2
    //   253: aload_2
    //   254: invokevirtual 1219	java/lang/Exception:printStackTrace	()V
    //   257: return
    //   258: astore_3
    //   259: goto -85 -> 174
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	262	0	this	MainActivity
    //   0	262	1	paramInt	int
    //   0	262	2	paramString	String
    //   37	14	3	localFile	File
    //   137	1	3	localThrowable1	Throwable
    //   146	5	3	localException	Exception
    //   173	17	3	localObject1	Object
    //   218	25	3	localJSONObject	JSONObject
    //   258	1	3	localObject2	Object
    //   66	1	4	localObject3	Object
    //   168	26	4	localThrowable2	Throwable
    //   63	122	5	localBufferedWriter	java.io.BufferedWriter
    //   191	13	5	localThrowable3	Throwable
    // Exception table:
    //   from	to	target	type
    //   95	100	137	java/lang/Throwable
    //   38	65	146	java/lang/Exception
    //   95	100	146	java/lang/Exception
    //   138	146	146	java/lang/Exception
    //   160	165	146	java/lang/Exception
    //   184	189	146	java/lang/Exception
    //   189	191	146	java/lang/Exception
    //   193	200	146	java/lang/Exception
    //   203	208	146	java/lang/Exception
    //   68	86	168	java/lang/Throwable
    //   170	173	173	finally
    //   184	189	191	java/lang/Throwable
    //   211	251	252	java/lang/Exception
    //   68	86	258	finally
  }
  
  public void insertTeamOnlineDialog()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle("Choose Team to Replace:");
    final String[] arrayOfString = getOnlineTeams();
    localBuilder.setAdapter(new SaveFilesListArrayAdapter(this, arrayOfString), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, final int paramAnonymousInt)
      {
        if (arrayOfString[paramAnonymousInt].equals("EMPTY"))
        {
          insertTeamOnline(paramAnonymousInt, "");
          return;
        }
        paramAnonymousDialogInterface = new AlertDialog.Builder(MainActivity.this);
        paramAnonymousDialogInterface.setMessage("Are you sure you want to use this? It will replace the team currently online:\n\n" + arrayOfString[paramAnonymousInt]).setPositiveButton("Yes, Overwrite", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            insertTeamOnline(paramAnonymousInt, val$onlineTeams[paramAnonymousInt]);
          }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface.dismiss();
          }
        });
        paramAnonymousDialogInterface = paramAnonymousDialogInterface.create();
        paramAnonymousDialogInterface.show();
        ((TextView)paramAnonymousDialogInterface.findViewById(16908299)).setTextSize(2, 14.0F);
      }
    });
    localBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    localBuilder.create().show();
  }
  
  public void onBackPressed()
  {
    exitMainActivity();
  }
  
  protected void onCreate(final Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968604);
    setSupportActionBar((Toolbar)findViewById(2131558542));
    mainList = ((ListView)findViewById(2131558575));
    expListPlayerStats = ((ExpandableListView)findViewById(2131558576));
    paramBundle = getIntent().getExtras();
    int i = 0;
    final boolean bool = false;
    if (paramBundle != null)
    {
      paramBundle = paramBundle.getString("SAVE_FILE");
      if (paramBundle.equals("NEW_LEAGUE_EASY"))
      {
        simLeague = new League(getString(2131165241), getString(2131165240), false);
        season = 2016;
        recruitingStage = -1;
        wantUpdateConf = 2;
        showToasts = true;
        showInjuryReport = true;
        shownTutRoster = false;
        shownTutTeamStats = false;
        shownTutSchedule = false;
        shownTutTrading = false;
        if (i != 0) {
          break label895;
        }
        userTeam = ((Team)simLeague.teamList.get(0));
        simLeague.userTeam = userTeam;
        userTeam.userControlled = true;
        userTeamStr = userTeam.name;
        currentTeam = userTeam;
        getSupportActionBar().setTitle(userTeam.name + " " + season + " Season");
        showTutorial = true;
        paramBundle = ProgressDialog.show(this, "", "Setting up League. Please wait...", true);
        paramBundle.setCancelable(false);
        new Thread(new Runnable()
        {
          public void run()
          {
            if (!bool) {
              simLeague.generatePlayersNewLeague();
            }
            runOnUiThread(new Runnable()
            {
              public void run()
              {
                val$dialogLoading.dismiss();
                currentTeam = ((Team)simLeague.teamList.get(0));
                currentDivision = ((Division)simLeague.divisions.get(0));
                AlertDialog.Builder localBuilder = new AlertDialog.Builder(MainActivity.this);
                localBuilder.setTitle("Choose your team:");
                localBuilder.setItems(simLeague.getTeamListStr(), new DialogInterface.OnClickListener()
                {
                  public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int)
                  {
                    userTeam.userControlled = false;
                    userTeam = ((Team)simLeague.teamList.get(paramAnonymous3Int));
                    simLeague.userTeam = userTeam;
                    userTeam.userControlled = true;
                    userTeamStr = userTeam.name;
                    currentTeam = userTeam;
                    getSupportActionBar().setTitle(userTeam.name + " " + season + " Season");
                    examineTeam(currentTeam.name);
                    currTab = 1;
                    MainActivity.this.updatePlayerStats();
                    MainActivity.this.saveLeagueOnFile();
                    showTutorialTeamRoster();
                  }
                });
                localBuilder.create().show();
              }
            });
          }
        }).start();
      }
    }
    for (;;)
    {
      examineTeamSpinner = ((Spinner)findViewById(2131558569));
      teamList = new ArrayList();
      j = 0;
      while (j < 10)
      {
        teamList.add(((Team)simLeague.teamList.get(j)).strRep());
        j += 1;
      }
      if (paramBundle.equals("NEW_LEAGUE_HARD"))
      {
        simLeague = new League(getString(2131165241), getString(2131165240), true);
        season = 2016;
        break;
      }
      paramBundle = paramBundle.split(",");
      saveLeagueFileStr = paramBundle[1];
      saveTeamsFileStr = paramBundle[2];
      saveScheduleFileStr = paramBundle[3];
      saveTeamHistFileStr = paramBundle[4];
      saveLeagueFile = new File(getFilesDir(), saveLeagueFileStr);
      saveTeamsFile = new File(getFilesDir(), saveTeamsFileStr);
      saveScheduleFile = new File(getFilesDir(), saveScheduleFileStr);
      saveTeamHistFile = new File(getFilesDir(), saveTeamHistFileStr);
      if (paramBundle[0].equals("LOAD"))
      {
        if ((saveLeagueFile.exists()) && (saveTeamsFile.exists()) && (saveScheduleFile.exists()))
        {
          simLeague = new League(saveLeagueFile, saveTeamsFile, saveScheduleFile, saveTeamHistFile, getString(2131165241), getString(2131165240));
          userTeam = simLeague.userTeam;
          userTeamStr = userTeam.name;
          simLeague.updateTeamTalentRatings();
          season = simLeague.getYear();
          currentTeam = userTeam;
          i = 1;
          break;
        }
        simLeague = new League(getString(2131165241), getString(2131165240), false);
        season = 2016;
        break;
      }
      if (paramBundle[0].equals("ROSTER"))
      {
        bool = true;
        try
        {
          simLeague = new League(getString(2131165241), getString(2131165240), new File(getFilesDir(), "roster.txt"));
          season = 2016;
        }
        catch (Exception paramBundle)
        {
          bool = false;
          Toast.makeText(this, "Error making league from roster file! Generating new league.", 1).show();
          simLeague = new League(getString(2131165241), getString(2131165240), false);
          season = 2016;
        }
        break;
      }
      simLeague = new League(getString(2131165241), getString(2131165240), false);
      season = 2016;
      break;
      simLeague = new League(getString(2131165241), getString(2131165240), false);
      season = 2016;
      break;
      label895:
      getSupportActionBar().setTitle(userTeam.name + " " + season + " Season");
      showTutorial = false;
    }
    dataAdapterTeam = new ArrayAdapter(this, 17367048, teamList);
    dataAdapterTeam.setDropDownViewResource(17367049);
    examineTeamSpinner.setAdapter(dataAdapterTeam);
    examineTeamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        currentTeam = simLeague.findTeam(paramAnonymousAdapterView.getItemAtPosition(paramAnonymousInt).toString());
        MainActivity.this.updateCurrTeam();
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
    examineConfSpinner = ((Spinner)findViewById(2131558568));
    confList = new ArrayList();
    int j = 0;
    while (j < simLeague.divisions.size())
    {
      confList.add(simLeague.divisions.get(j)).divName);
      j += 1;
    }
    dataAdapterConf = new ArrayAdapter(this, 17367048, confList);
    dataAdapterConf.setDropDownViewResource(17367049);
    examineConfSpinner.setAdapter(dataAdapterConf);
    examineConfSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        currentDivision = simLeague.findDivision(paramAnonymousAdapterView.getItemAtPosition(paramAnonymousInt).toString());
        MainActivity.this.updateCurrDivision();
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
    paramBundle = (Button)findViewById(2131558571);
    Button localButton1 = (Button)findViewById(2131558572);
    Button localButton2 = (Button)findViewById(2131558573);
    final Button localButton3 = (Button)findViewById(2131558574);
    if (simLeague.currentWeek >= 8) {
      localButton3.setEnabled(false);
    }
    final Button localButton4 = (Button)findViewById(2131558577);
    if (simLeague.currentWeek < 16) {
      localButton4.setText("Play Week " + (simLeague.currentWeek + 1));
    }
    for (;;)
    {
      localButton4.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (simLeague.currentWeek == 20)
          {
            advanceOffSeasonDialog();
            return;
          }
          localButton4.setEnabled(false);
          int i = userTeam.gameWLSchedule.size();
          simLeague.playWeek();
          if (simLeague.currentWeek == 7)
          {
            paramAnonymousView = new AlertDialog.Builder(MainActivity.this);
            paramAnonymousView.setMessage("This week is the last week for trades! Once the next week is played, trades will not be allowed.").setPositiveButton("Ok", new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
              {
                paramAnonymous2DialogInterface.dismiss();
              }
            });
            paramAnonymousView.create().show();
            if (simLeague.currentWeek == 16)
            {
              MainActivity.this.mvpCeremony();
              if (!simLeague.playoffsNA.contains(userTeam)) {
                break label585;
              }
              if ((simLeague.playoffsNA.get(0) != userTeam) && (simLeague.playoffsNA.get(1) != userTeam)) {
                break label443;
              }
              if (showToasts) {
                Toast.makeText(MainActivity.this, userTeam.abbr + " made the playoffs!\nThey receive a BYE week for Round 1.", 0).show();
              }
            }
            label251:
            if (userTeam.gameWLSchedule.size() > i)
            {
              if (showToasts) {
                Toast.makeText(MainActivity.this, userTeam.weekSummaryStr(), 0).show();
              }
              if (showInjuryReport) {
                showInjuryReportDialog();
              }
            }
            MainActivity.this.updateCurrTeam();
            MainActivity.this.scrollToLatestGame();
            if (simLeague.currentWeek >= 16) {
              break label902;
            }
            localButton4.setText("Play Week " + (simLeague.currentWeek + 1));
          }
          for (;;)
          {
            localButton4.setEnabled(true);
            return;
            if (simLeague.currentWeek < 8) {
              break;
            }
            if (currTab == 3)
            {
              currTab = 2;
              MainActivity.this.updateSchedule();
            }
            localButton3.setEnabled(false);
            break;
            label443:
            if (userTeam.gameSchedule.get(16)).homeTeam == userTeam) {}
            for (paramAnonymousView = userTeam.gameSchedule.get(16)).awayTeam;; paramAnonymousView = userTeam.gameSchedule.get(16)).homeTeam)
            {
              if (!showToasts) {
                break label583;
              }
              Toast.makeText(MainActivity.this, userTeam.abbr + " made the playoffs!\nThey play " + abbr + " in Round 1.", 0).show();
              break;
            }
            label583:
            break label251;
            label585:
            if (simLeague.playoffsAM.contains(userTeam))
            {
              if ((simLeague.playoffsAM.get(0) == userTeam) || (simLeague.playoffsAM.get(1) == userTeam))
              {
                if (!showToasts) {
                  break label251;
                }
                Toast.makeText(MainActivity.this, userTeam.abbr + " made the playoffs!\nThey receive a BYE week for Round 1.", 0).show();
                break label251;
              }
              if (userTeam.gameSchedule.get(16)).homeTeam == userTeam) {}
              for (paramAnonymousView = userTeam.gameSchedule.get(16)).awayTeam;; paramAnonymousView = userTeam.gameSchedule.get(16)).homeTeam)
              {
                if (!showToasts) {
                  break label848;
                }
                Toast.makeText(MainActivity.this, userTeam.abbr + " made the playoffs!\nThey play " + abbr + " in Round 1.", 0).show();
                break;
              }
              label848:
              break label251;
            }
            if (!showToasts) {
              break label251;
            }
            Toast.makeText(MainActivity.this, userTeam.abbr + " did not make the playoffs.", 0).show();
            break label251;
            label902:
            if (simLeague.currentWeek == 16)
            {
              localButton4.setText("Playoffs Round 1");
              examineTeam(currentTeam.name);
            }
            else if (simLeague.currentWeek == 17)
            {
              localButton4.setText("Playoffs Round 2");
              examineTeam(currentTeam.name);
            }
            else if (simLeague.currentWeek == 18)
            {
              localButton4.setText("Play Conf Championships");
            }
            else if (simLeague.currentWeek == 19)
            {
              localButton4.setText("Play Champions Bowl");
            }
            else
            {
              localButton4.setText("Advance to Offseason");
              simLeague.curePlayers();
              simLeague.checkLeagueRecords();
              showSeasonSummaryDialog();
              MainActivity.this.saveLeagueOnFile();
            }
          }
        }
      });
      paramBundle.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          currTab = 0;
          MainActivity.this.updateTeamStats();
          showTutorialTeamStats();
        }
      });
      localButton1.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          currTab = 1;
          MainActivity.this.updatePlayerStats();
          showTutorialTeamRoster();
        }
      });
      localButton2.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          currTab = 2;
          MainActivity.this.updateSchedule();
          showTutorialTeamSchedule();
        }
      });
      localButton3.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          currTab = 3;
          MainActivity.this.updateTradeBlock();
          showTutorialTrading();
        }
      });
      if (i != 0)
      {
        simLeague.setTeamRanks();
        examineTeam(userTeam.name);
        showToasts = userTeam.showPopups;
      }
      return;
      if (simLeague.currentWeek == 16) {
        localButton4.setText("Playoffs Round 1");
      } else if (simLeague.currentWeek == 17) {
        localButton4.setText("Playoffs Round 2");
      } else if (simLeague.currentWeek == 18) {
        localButton4.setText("Play Conf Championships");
      } else if (simLeague.currentWeek == 19) {
        localButton4.setText("Play Champ Bowl");
      } else {
        localButton4.setText("Advance to Offseason");
      }
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131623936, paramMenu);
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
      Object localObject;
      if (i == 2131558720)
      {
        if (simLeague.currentWeek < 16)
        {
          localObject = simLeague.getTop5MVPStr();
          AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
          localBuilder.setMessage((CharSequence)localObject).setTitle("MVP Watch").setPositiveButton("OK", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
          });
          localObject = localBuilder.create();
          ((AlertDialog)localObject).show();
          ((TextView)((AlertDialog)localObject).findViewById(16908299)).setTextSize(2, 14.0F);
        }
        else
        {
          mvpCeremony();
        }
      }
      else if (i == 2131558719) {
        showTeamRankingsDialog();
      } else if (i == 2131558718) {
        showLeagueHistoryDialog();
      } else if (i == 2131558725) {
        saveLeagueOnFile();
      } else if (i == 2131558726) {
        exitMainActivity();
      } else if (i == 2131558717) {
        changeTeamNameDialog();
      } else if (i == 2131558722) {
        showTeamLineupDialog();
      } else if (i == 2131558721) {
        showPlayerFinderDialog();
      } else if (i == 2131558724) {
        if (simLeague.canGoOnline)
        {
          goOnline();
        }
        else
        {
          localObject = new AlertDialog.Builder(this);
          ((AlertDialog.Builder)localObject).setMessage("Teams generated from roster files or made before the online update cannot be taken online.").setTitle("Cannot go online").setPositiveButton("OK", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
          });
          localObject = ((AlertDialog.Builder)localObject).create();
          ((AlertDialog)localObject).show();
          ((TextView)((AlertDialog)localObject).findViewById(16908299)).setTextSize(2, 14.0F);
        }
      }
    }
  }
  
  public void removeTradePiece(TradePiece paramTradePiece)
  {
    if (currentTeam.tradingBlock.contains(paramTradePiece)) {
      currentTeam.tradingBlock.remove(paramTradePiece);
    }
    updateTradeBlock();
  }
  
  public void setShowInjuryReport(boolean paramBoolean)
  {
    showInjuryReport = paramBoolean;
  }
  
  public void showCurrTeamProfile()
  {
    final Team localTeam = currentTeam;
    Object localObject = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject).setTitle(currentTeam.abbr + " Team Profile").setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).setView(getLayoutInflater().inflate(2130968665, null));
    localObject = ((AlertDialog.Builder)localObject).create();
    ((AlertDialog)localObject).show();
    Spinner localSpinner = (Spinner)((AlertDialog)localObject).findViewById(2131558689);
    ArrayAdapter localArrayAdapter = new ArrayAdapter(this, 17367048, new String[] { "Team History", "Team Salary Info" });
    localArrayAdapter.setDropDownViewResource(17367049);
    localSpinner.setAdapter(localArrayAdapter);
    localSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        if (paramAnonymousInt == 0)
        {
          paramAnonymousAdapterView = new TeamHistoryListArrayAdapter(MainActivity.this, localTeam.getTeamHistoryList());
          val$teamHistoryList.setAdapter(paramAnonymousAdapterView);
        }
        while (paramAnonymousInt != 1) {
          return;
        }
        paramAnonymousAdapterView = new TeamHistoryListArrayAdapter(MainActivity.this, localTeam.getCapRoomList());
        val$teamHistoryList.setAdapter(paramAnonymousAdapterView);
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
  }
  
  public void showGameDialog(final Game paramGame)
  {
    final Object localObject3;
    Object localObject4;
    Object localObject5;
    if (hasPlayed)
    {
      localObject1 = paramGame.getGameSummaryStr();
      localObject2 = new AlertDialog.Builder(this);
      ((AlertDialog.Builder)localObject2).setTitle(awayTeam.abbr + " @ " + homeTeam.abbr + ": " + gameName).setPositiveButton("OK", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
      }).setView(getLayoutInflater().inflate(2130968630, null));
      localObject2 = ((AlertDialog.Builder)localObject2).create();
      ((AlertDialog)localObject2).show();
      localObject3 = (TextView)((AlertDialog)localObject2).findViewById(2131558610);
      localObject4 = (TextView)((AlertDialog)localObject2).findViewById(2131558612);
      localObject5 = (TextView)((AlertDialog)localObject2).findViewById(2131558613);
      TextView localTextView = (TextView)((AlertDialog)localObject2).findViewById(2131558615);
      ((TextView)localObject3).setText(awayScore + "");
      ((TextView)localObject4).setText(homeScore + "");
      ((TextView)localObject5).setText(awayTeam.getStrAbbrWL_2Lines());
      localTextView.setText(homeTeam.getStrAbbrWL_2Lines());
      localObject3 = (TextView)((AlertDialog)localObject2).findViewById(2131558614);
      if (numOT > 0) {
        ((TextView)localObject3).setText(numOT + "OT");
      }
      for (;;)
      {
        ((TextView)((AlertDialog)localObject2).findViewById(2131558616)).setText(localObject1[0]);
        ((TextView)((AlertDialog)localObject2).findViewById(2131558617)).setText(localObject1[1]);
        ((TextView)((AlertDialog)localObject2).findViewById(2131558618)).setText(localObject1[2]);
        ((TextView)((AlertDialog)localObject2).findViewById(2131558619)).setText(localObject1[3] + "\n\n");
        return;
        ((TextView)localObject3).setText("@");
      }
    }
    Object localObject2 = paramGame.getGameScoutStr();
    Object localObject1 = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject1).setTitle(awayTeam.abbr + " @ " + homeTeam.abbr + ": " + gameName).setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).setView(getLayoutInflater().inflate(2130968632, null));
    localObject1 = ((AlertDialog.Builder)localObject1).create();
    ((AlertDialog)localObject1).show();
    ((TextView)((AlertDialog)localObject1).findViewById(2131558624)).setText(localObject2[0]);
    ((TextView)((AlertDialog)localObject1).findViewById(2131558625)).setText(localObject2[1]);
    ((TextView)((AlertDialog)localObject1).findViewById(2131558626)).setText(localObject2[2]);
    ((TextView)((AlertDialog)localObject1).findViewById(2131558631)).setText(localObject2[3]);
    if ((awayTeam == userTeam) || (homeTeam == userTeam))
    {
      paramGame = (TextView)((AlertDialog)localObject1).findViewById(2131558627);
      localObject2 = (TextView)((AlertDialog)localObject1).findViewById(2131558629);
      paramGame.setText(userTeam.abbr + " Off Strategy:");
      ((TextView)localObject2).setText(userTeam.abbr + " Def Strategy:");
      localObject3 = userTeam.getTeamStrategiesOff();
      paramGame = userTeam.getTeamStrategiesDef();
      int j = 0;
      int k = 0;
      localObject5 = new String[localObject3.length];
      int i = 0;
      while (i < localObject3.length)
      {
        localObject5[i] = localObject3[i].getStratName();
        if (localObject5[i].equals(userTeam.teamStratOff.getStratName())) {
          j = i;
        }
        i += 1;
      }
      localObject2 = new String[paramGame.length];
      i = 0;
      while (i < paramGame.length)
      {
        localObject2[i] = paramGame[i].getStratName();
        if (localObject2[i].equals(userTeam.teamStratDef.getStratName())) {
          k = i;
        }
        i += 1;
      }
      localObject4 = (Spinner)((AlertDialog)localObject1).findViewById(2131558628);
      localObject5 = new ArrayAdapter(this, 17367048, (Object[])localObject5);
      ((ArrayAdapter)localObject5).setDropDownViewResource(17367049);
      ((Spinner)localObject4).setAdapter((SpinnerAdapter)localObject5);
      ((Spinner)localObject4).setSelection(j);
      ((Spinner)localObject4).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
      {
        public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          userTeam.teamStratOff = localObject3[paramAnonymousInt];
          userTeam.teamStratOffNum = paramAnonymousInt;
        }
        
        public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
      });
      localObject1 = (Spinner)((AlertDialog)localObject1).findViewById(2131558630);
      localObject2 = new ArrayAdapter(this, 17367048, (Object[])localObject2);
      ((ArrayAdapter)localObject2).setDropDownViewResource(17367049);
      ((Spinner)localObject1).setAdapter((SpinnerAdapter)localObject2);
      ((Spinner)localObject1).setSelection(k);
      ((Spinner)localObject1).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
      {
        public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          userTeam.teamStratDef = paramGame[paramAnonymousInt];
          userTeam.teamStratDefNum = paramAnonymousInt;
        }
        
        public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
      });
      return;
    }
    paramGame = (Spinner)((AlertDialog)localObject1).findViewById(2131558628);
    localObject2 = (Spinner)((AlertDialog)localObject1).findViewById(2131558630);
    paramGame.setVisibility(8);
    ((Spinner)localObject2).setVisibility(8);
    paramGame = (TextView)((AlertDialog)localObject1).findViewById(2131558627);
    localObject1 = (TextView)((AlertDialog)localObject1).findViewById(2131558629);
    paramGame.setVisibility(8);
    ((TextView)localObject1).setVisibility(8);
  }
  
  public void showInjuryReportDialog()
  {
    Object localObject1 = userTeam.getInjuryReport();
    if (localObject1 != null)
    {
      Object localObject2 = new AlertDialog.Builder(this);
      ((AlertDialog.Builder)localObject2).setTitle("Injury Report").setPositiveButton("OK", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
      }).setNegativeButton("Set Lineup", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          MainActivity.this.showTeamLineupDialog();
        }
      }).setView(getLayoutInflater().inflate(2130968636, null));
      localObject2 = ((AlertDialog.Builder)localObject2).create();
      ((AlertDialog)localObject2).show();
      ((ListView)((AlertDialog)localObject2).findViewById(2131558640)).setAdapter(new PlayerStatsListArrayAdapter(this, (String[])localObject1));
      localObject1 = (CheckBox)((AlertDialog)localObject2).findViewById(2131558641);
      ((CheckBox)localObject1).setChecked(true);
      ((CheckBox)localObject1).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
      {
        public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
        {
          setShowInjuryReport(paramAnonymousBoolean);
        }
      });
    }
  }
  
  public void showLeagueHistoryDialog()
  {
    Object localObject1 = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject1).setTitle("League History / Records").setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).setView(getLayoutInflater().inflate(2130968665, null));
    final Object localObject2 = ((AlertDialog.Builder)localObject1).create();
    ((AlertDialog)localObject2).show();
    localObject1 = (Spinner)((AlertDialog)localObject2).findViewById(2131558689);
    final Object localObject3 = new ArrayAdapter(this, 17367048, new String[] { "League History", "League Records", "Hall of Fame" });
    ((ArrayAdapter)localObject3).setDropDownViewResource(17367049);
    ((Spinner)localObject1).setAdapter((SpinnerAdapter)localObject3);
    localObject2 = (ListView)((AlertDialog)localObject2).findViewById(2131558690);
    localObject3 = new String[simLeague.hallOfFame.size()];
    int i = 0;
    while (i < simLeague.hallOfFame.size())
    {
      localObject3[i] = ((String)simLeague.hallOfFame.get(i));
      i += 1;
    }
    ((Spinner)localObject1).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        if (paramAnonymousInt == 0)
        {
          paramAnonymousAdapterView = new LeagueHistoryListArrayAdapter(MainActivity.this, simLeague.getLeagueHistoryStr().split("%"), userTeam.abbr);
          localObject2.setAdapter(paramAnonymousAdapterView);
          return;
        }
        if (paramAnonymousInt == 1)
        {
          paramAnonymousAdapterView = new LeagueRecordsListArrayAdapter(MainActivity.this, simLeague.getLeagueRecordsStr().split("\n"), userTeam.abbr);
          localObject2.setAdapter(paramAnonymousAdapterView);
          return;
        }
        paramAnonymousAdapterView = new HallOfFameListArrayAdapter(MainActivity.this, localObject3);
        localObject2.setAdapter(paramAnonymousAdapterView);
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
  }
  
  public void showNewsStoriesDialog()
  {
    Object localObject1 = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject1).setTitle("News Stories").setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).setView(getLayoutInflater().inflate(2130968665, null));
    Object localObject3 = ((AlertDialog.Builder)localObject1).create();
    ((AlertDialog)localObject3).show();
    final Object localObject2 = new ArrayList();
    Object localObject4 = new String[simLeague.currentWeek + 1];
    int i = 0;
    if (i < localObject4.length)
    {
      if (i == 13) {
        localObject4[i] = "Conf Champ Week";
      }
      for (;;)
      {
        i += 1;
        break;
        if (i == 14) {
          localObject4[i] = "Bowl Game Week";
        } else if (i == 15) {
          localObject4[i] = "National Champ";
        } else {
          localObject4[i] = ("Week " + i);
        }
      }
    }
    localObject1 = (Spinner)((AlertDialog)localObject3).findViewById(2131558689);
    localObject4 = new ArrayAdapter(this, 17367048, (Object[])localObject4);
    ((ArrayAdapter)localObject4).setDropDownViewResource(17367049);
    ((Spinner)localObject1).setAdapter((SpinnerAdapter)localObject4);
    ((Spinner)localObject1).setSelection(simLeague.currentWeek);
    localObject3 = (ListView)((AlertDialog)localObject3).findViewById(2131558690);
    localObject2 = new NewsStoriesListArrayAdapter(this, (ArrayList)localObject2);
    ((ListView)localObject3).setAdapter((ListAdapter)localObject2);
    ((Spinner)localObject1).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = (ArrayList)simLeague.newsStories.get(paramAnonymousInt);
        paramAnonymousInt = 0;
        if (paramAnonymousAdapterView.size() == 0)
        {
          paramAnonymousInt = 1;
          paramAnonymousAdapterView.add("No news stories.>I guess this week was a bit boring, huh?");
        }
        localObject2.clear();
        localObject2.addAll(paramAnonymousAdapterView);
        localObject2.notifyDataSetChanged();
        if (paramAnonymousInt != 0) {
          paramAnonymousAdapterView.remove(0);
        }
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
  }
  
  public void showPlayerFinderDialog()
  {
    Object localObject1 = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject1).setTitle("Player Finder").setPositiveButton("Find", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        findPlayers(findFilter);
      }
    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    }).setView(getLayoutInflater().inflate(2130968655, null));
    localObject1 = ((AlertDialog.Builder)localObject1).create();
    ((AlertDialog)localObject1).show();
    final CheckBox localCheckBox = (CheckBox)((AlertDialog)localObject1).findViewById(2131558669);
    if (findFilter[7] == 1) {}
    for (boolean bool = true;; bool = false)
    {
      localCheckBox.setChecked(bool);
      localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
      {
        public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
        {
          paramAnonymousCompoundButton = findFilter;
          if (paramAnonymousBoolean) {}
          for (int i = 1;; i = 0)
          {
            paramAnonymousCompoundButton[7] = i;
            return;
          }
        }
      });
      localObject4 = new String[11];
      i = 0;
      while (i < localObject4.length)
      {
        localObject4[i] = (i * 5 + 50 + "");
        i += 1;
      }
    }
    final Object localObject6 = new String[5];
    localObject6[0] = "F";
    localObject6[1] = "D";
    localObject6[2] = "C";
    localObject6[3] = "B";
    localObject6[4] = "A";
    Object localObject2 = new String[21];
    int i = 0;
    while (i < localObject2.length)
    {
      localObject2[i] = (i + 20 + "");
      i += 1;
    }
    final Spinner localSpinner = (Spinner)((AlertDialog)localObject1).findViewById(2131558671);
    final Object localObject3 = new ArrayAdapter(this, 17367048, new String[] { "All", "QB", "RB", "WR", "OL", "K", "S", "CB", "DL", "LB" });
    ((ArrayAdapter)localObject3).setDropDownViewResource(17367049);
    localSpinner.setAdapter((SpinnerAdapter)localObject3);
    localSpinner.setSelection(findFilter[0]);
    localObject3 = (Spinner)((AlertDialog)localObject1).findViewById(2131558672);
    final Object localObject4 = new ArrayAdapter(this, 17367048, (Object[])localObject4);
    ((ArrayAdapter)localObject4).setDropDownViewResource(17367049);
    ((Spinner)localObject3).setAdapter((SpinnerAdapter)localObject4);
    ((Spinner)localObject3).setSelection((findFilter[1] - 50) / 5);
    localObject4 = (Spinner)((AlertDialog)localObject1).findViewById(2131558603);
    final Object localObject5 = new ArrayAdapter(this, 17367048, (Object[])localObject6);
    ((ArrayAdapter)localObject5).setDropDownViewResource(17367049);
    ((Spinner)localObject4).setAdapter((SpinnerAdapter)localObject5);
    ((Spinner)localObject4).setSelection((findFilter[2] - 50) / 10);
    localObject5 = (Spinner)((AlertDialog)localObject1).findViewById(2131558604);
    final Object localObject7 = new ArrayAdapter(this, 17367048, (Object[])localObject6);
    ((ArrayAdapter)localObject7).setDropDownViewResource(17367049);
    ((Spinner)localObject5).setAdapter((SpinnerAdapter)localObject7);
    ((Spinner)localObject5).setSelection((findFilter[3] - 50) / 10);
    localObject7 = (Spinner)((AlertDialog)localObject1).findViewById(2131558605);
    localObject6 = new ArrayAdapter(this, 17367048, (Object[])localObject6);
    ((ArrayAdapter)localObject6).setDropDownViewResource(17367049);
    ((Spinner)localObject7).setAdapter((SpinnerAdapter)localObject6);
    ((Spinner)localObject7).setSelection((findFilter[4] - 50) / 10);
    localObject6 = (Spinner)((AlertDialog)localObject1).findViewById(2131558606);
    final Object localObject8 = new ArrayAdapter(this, 17367048, (Object[])localObject2);
    ((ArrayAdapter)localObject8).setDropDownViewResource(17367049);
    ((Spinner)localObject6).setAdapter((SpinnerAdapter)localObject8);
    ((Spinner)localObject6).setSelection(findFilter[5] - 20);
    localObject8 = (Spinner)((AlertDialog)localObject1).findViewById(2131558607);
    localObject2 = new ArrayAdapter(this, 17367048, (Object[])localObject2);
    ((ArrayAdapter)localObject2).setDropDownViewResource(17367049);
    ((Spinner)localObject8).setAdapter((SpinnerAdapter)localObject2);
    ((Spinner)localObject8).setSelection(findFilter[6] - 20);
    ((Button)((AlertDialog)localObject1).findViewById(2131558670)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        boolean bool = true;
        findFilter[0] = 0;
        findFilter[1] = 50;
        findFilter[2] = 50;
        findFilter[3] = 50;
        findFilter[4] = 50;
        findFilter[5] = 20;
        findFilter[6] = 40;
        findFilter[7] = 0;
        localSpinner.setSelection(findFilter[0]);
        localObject3.setSelection((findFilter[1] - 50) / 5);
        localObject4.setSelection((findFilter[2] - 50) / 10);
        localObject5.setSelection((findFilter[3] - 50) / 10);
        localObject7.setSelection((findFilter[4] - 50) / 10);
        localObject6.setSelection(findFilter[5] - 20);
        localObject8.setSelection(findFilter[6] - 20);
        paramAnonymousView = localCheckBox;
        if (findFilter[7] == 1) {}
        for (;;)
        {
          paramAnonymousView.setChecked(bool);
          return;
          bool = false;
        }
      }
    });
    localSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        findFilter[0] = paramAnonymousInt;
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
    ((Spinner)localObject3).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        findFilter[1] = (paramAnonymousInt * 5 + 50);
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
    ((Spinner)localObject4).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        findFilter[2] = (paramAnonymousInt * 10 + 50);
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
    ((Spinner)localObject5).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        findFilter[3] = (paramAnonymousInt * 10 + 50);
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
    ((Spinner)localObject7).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        findFilter[4] = (paramAnonymousInt * 10 + 50);
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
    ((Spinner)localObject6).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        findFilter[5] = (paramAnonymousInt + 20);
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
    ((Spinner)localObject8).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        findFilter[6] = (paramAnonymousInt + 20);
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
  }
  
  public void showSeasonSummaryDialog()
  {
    Object localObject = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject).setMessage(simLeague.seasonSummaryStr()).setTitle(userTeam.teamHistory.size() + 2016 + " Season Summary").setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    localObject = ((AlertDialog.Builder)localObject).create();
    ((AlertDialog)localObject).show();
    ((TextView)((AlertDialog)localObject).findViewById(16908299)).setTextSize(2, 14.0F);
  }
  
  public void showTeamHistory(final Team paramTeam)
  {
    Object localObject = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject).setTitle(abbr + " Team History").setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).setView(getLayoutInflater().inflate(2130968665, null));
    localObject = ((AlertDialog.Builder)localObject).create();
    ((AlertDialog)localObject).show();
    Spinner localSpinner = (Spinner)((AlertDialog)localObject).findViewById(2131558689);
    ArrayAdapter localArrayAdapter = new ArrayAdapter(this, 17367048, new String[] { "Team History" });
    localArrayAdapter.setDropDownViewResource(17367049);
    localSpinner.setAdapter(localArrayAdapter);
    localSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        if (paramAnonymousInt == 0)
        {
          paramAnonymousAdapterView = new TeamHistoryListArrayAdapter(MainActivity.this, paramTeam.getTeamHistoryList());
          val$teamHistoryList.setAdapter(paramAnonymousAdapterView);
        }
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
  }
  
  public void showTeamHistoryDialog()
  {
    Object localObject = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject).setTitle("Team History").setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).setView(getLayoutInflater().inflate(2130968665, null));
    localObject = ((AlertDialog.Builder)localObject).create();
    ((AlertDialog)localObject).show();
    Spinner localSpinner = (Spinner)((AlertDialog)localObject).findViewById(2131558689);
    ArrayAdapter localArrayAdapter = new ArrayAdapter(this, 17367048, new String[] { "Team History", "Team Records" });
    localArrayAdapter.setDropDownViewResource(17367049);
    localSpinner.setAdapter(localArrayAdapter);
    localSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        if (paramAnonymousInt == 0)
        {
          paramAnonymousAdapterView = new TeamHistoryListArrayAdapter(MainActivity.this, userTeam.getTeamHistoryList());
          val$teamHistoryList.setAdapter(paramAnonymousAdapterView);
        }
        while (paramAnonymousInt != 1) {
          return;
        }
        paramAnonymousAdapterView = new LeagueRecordsListArrayAdapter(MainActivity.this, simLeague.userTeamRecords.getRecordsStr().split("\n"), "---");
        val$teamHistoryList.setAdapter(paramAnonymousAdapterView);
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
  }
  
  public void showTeamRankingsDialog()
  {
    Object localObject1 = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject1).setTitle("Team Rankings").setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).setView(getLayoutInflater().inflate(2130968665, null));
    Object localObject3 = ((AlertDialog.Builder)localObject1).create();
    ((AlertDialog)localObject3).show();
    final Object localObject2 = new ArrayList();
    localObject1 = (Spinner)((AlertDialog)localObject3).findViewById(2131558689);
    ArrayAdapter localArrayAdapter = new ArrayAdapter(this, 17367048, new String[] { "Power Ranking", "Playoff Picture", "Division Standings", "Strength of Schedule", "Points Per Game", "Opp Points Per Game", "Yards Per Game", "Opp Yards Per Game", "Pass Yards Per Game", "Rush Yards Per Game", "Opp Pass YPG", "Opp Rush YPG", "TO Differential", "Offensive Talent", "Defensive Talent", "Salary Cap Room" });
    localArrayAdapter.setDropDownViewResource(17367049);
    ((Spinner)localObject1).setAdapter(localArrayAdapter);
    localObject3 = (ListView)((AlertDialog)localObject3).findViewById(2131558690);
    localObject2 = new TeamRankingsListArrayAdapter(this, (ArrayList)localObject2, userTeam.strRepWithBowlResults());
    ((ListView)localObject3).setAdapter((ListAdapter)localObject2);
    ((Spinner)localObject1).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = simLeague.getTeamRankingsStr(paramAnonymousInt);
        localObject2.clear();
        localObject2.addAll(paramAnonymousAdapterView);
        localObject2.notifyDataSetChanged();
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
  }
  
  public void showTradeLogDialog()
  {
    Object localObject1 = new StringBuilder();
    Object localObject2 = simLeague.tradeLog.iterator();
    while (((Iterator)localObject2).hasNext())
    {
      Object localObject3 = (Trade)((Iterator)localObject2).next();
      ((StringBuilder)localObject1).append(((Trade)localObject3).getATeam().getStrAbbrWL() + " traded:\n");
      Object localObject4 = ((Trade)localObject3).getAOffer().iterator();
      while (((Iterator)localObject4).hasNext())
      {
        TradePiece localTradePiece = (TradePiece)((Iterator)localObject4).next();
        ((StringBuilder)localObject1).append(localTradePiece.getTradePieceInfo() + "\n");
      }
      ((StringBuilder)localObject1).append("\n" + ((Trade)localObject3).getBTeam().getStrAbbrWL() + " traded:\n");
      localObject3 = ((Trade)localObject3).getBOffer().iterator();
      while (((Iterator)localObject3).hasNext())
      {
        localObject4 = (TradePiece)((Iterator)localObject3).next();
        ((StringBuilder)localObject1).append(((TradePiece)localObject4).getTradePieceInfo() + "\n");
      }
      ((StringBuilder)localObject1).append("\n\n\n");
    }
    localObject2 = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject2).setMessage(((StringBuilder)localObject1).toString()).setTitle("Trade Log").setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    localObject1 = ((AlertDialog.Builder)localObject2).create();
    ((AlertDialog)localObject1).show();
    ((TextView)((AlertDialog)localObject1).findViewById(16908299)).setTextSize(2, 14.0F);
  }
  
  public void showTutorialTeamRoster()
  {
    if ((showTutorial) && (!shownTutRoster))
    {
      showTutorialDialog("Team Roster", getResources().getString(2131165251));
      shownTutRoster = true;
    }
  }
  
  public void showTutorialTeamSchedule()
  {
    if ((showTutorial) && (!shownTutSchedule))
    {
      showTutorialDialog("Game Schedule", getResources().getString(2131165252));
      shownTutSchedule = true;
    }
  }
  
  public void showTutorialTeamStats()
  {
    if ((showTutorial) && (!shownTutTeamStats))
    {
      showTutorialDialog("Team Stats", getResources().getString(2131165253));
      shownTutTeamStats = true;
    }
  }
  
  public void showTutorialTrading()
  {
    if ((showTutorial) && (!shownTutTrading))
    {
      showTutorialDialog("Game Schedule", getResources().getString(2131165254));
      shownTutTrading = true;
    }
  }
  
  public void tradeOffersDialog()
  {
    if (simLeague.currentWeek < 8)
    {
      localObject1 = new AlertDialog.Builder(this);
      ((AlertDialog.Builder)localObject1).setTitle("Trade Offers").setView(getLayoutInflater().inflate(2130968672, null)).setPositiveButton("Accept Trade", null).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          currentTrade = null;
          paramAnonymousDialogInterface.dismiss();
        }
      });
      final Object localObject3 = ((AlertDialog.Builder)localObject1).create();
      ((AlertDialog)localObject3).setOnShowListener(new DialogInterface.OnShowListener()
      {
        public void onShow(DialogInterface paramAnonymousDialogInterface)
        {
          localObject3.getButton(-1).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              paramAnonymous2View = new AlertDialog.Builder(MainActivity.this);
              paramAnonymous2View.setMessage("Are you sure you want to accept this trade? This cannot be undone.").setPositiveButton("Yes, Accept", new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int)
                {
                  if (currentTrade != null)
                  {
                    currentTrade.completeTrade();
                    simLeague.tradeLog.add(currentTrade);
                  }
                  currentTrade = null;
                  examineTeam(userTeam.name);
                  currTab = 1;
                  MainActivity.this.updatePlayerStats();
                  MainActivity.this.saveLeagueOnFile();
                  val$dialog.dismiss();
                }
              }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int) {}
              });
              paramAnonymous2View.create().show();
            }
          });
        }
      });
      ((AlertDialog)localObject3).show();
      final Object localObject4 = new ArrayList();
      int k = 1;
      final Object localObject5;
      final Object localObject2;
      int j;
      if (currentTeam.userControlled)
      {
        localObject5 = new ArrayList();
        localObject1 = simLeague.teamList.iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (Team)((Iterator)localObject1).next();
          if (!userControlled)
          {
            Trade localTrade = Trade.getTradeOfferFromAI((Team)localObject2, userTeam, userTeam.tradingBlock);
            if (!localTrade.getAOffer().isEmpty())
            {
              ((ArrayList)localObject5).add(abbr);
              ((ArrayList)localObject4).add(localTrade);
            }
          }
        }
        if (((ArrayList)localObject5).size() > 0)
        {
          localObject2 = new String[((ArrayList)localObject5).size()];
          int i = 0;
          for (;;)
          {
            j = k;
            localObject1 = localObject2;
            if (i >= localObject2.length) {
              break;
            }
            localObject2[i] = ((String)((ArrayList)localObject5).get(i) + " [" + (i + 1) + "/" + ((ArrayList)localObject5).size() + "]");
            i += 1;
          }
        }
        j = 0;
        localObject1 = new String[1];
        localObject1[0] = "ERROR";
      }
      while (j != 0)
      {
        localObject2 = (Spinner)((AlertDialog)localObject3).findViewById(2131558711);
        localObject5 = new ArrayAdapter(this, 17367048, (Object[])localObject1);
        ((ArrayAdapter)localObject5).setDropDownViewResource(17367049);
        ((Spinner)localObject2).setAdapter((SpinnerAdapter)localObject5);
        localObject5 = (ListView)((AlertDialog)localObject3).findViewById(2131558713);
        ((ListView)localObject5).setAdapter(null);
        ((Spinner)localObject2).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
          public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
          {
            if (currentTeam.userControlled) {}
            for (paramAnonymousAdapterView = (Trade)localObject4.get(paramAnonymousInt);; paramAnonymousAdapterView = Trade.getTradeOfferFromUserTeam(userTeam, currentTeam, currentTeam.tradingBlock))
            {
              paramAnonymousView = new TradePiece[paramAnonymousAdapterView.getAOffer().size() + 2 + paramAnonymousAdapterView.getBOffer().size()];
              paramAnonymousView[0] = null;
              paramAnonymousInt = 0;
              while (paramAnonymousInt < paramAnonymousAdapterView.getAOffer().size())
              {
                paramAnonymousView[(paramAnonymousInt + 1)] = ((TradePiece)paramAnonymousAdapterView.getAOffer().get(paramAnonymousInt));
                paramAnonymousInt += 1;
              }
            }
            paramAnonymousView[(paramAnonymousAdapterView.getAOffer().size() + 1)] = null;
            paramAnonymousInt = 0;
            while (paramAnonymousInt < paramAnonymousAdapterView.getBOffer().size())
            {
              paramAnonymousView[(paramAnonymousAdapterView.getAOffer().size() + paramAnonymousInt + 2)] = ((TradePiece)paramAnonymousAdapterView.getBOffer().get(paramAnonymousInt));
              paramAnonymousInt += 1;
            }
            paramAnonymousView = new TradeListAdapter(MainActivity.this, MainActivity.this, paramAnonymousView, paramAnonymousAdapterView);
            localObject5.setAdapter(paramAnonymousView);
            currentTrade = paramAnonymousAdapterView;
          }
          
          public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
        });
        localObject4 = (Button)((AlertDialog)localObject3).findViewById(2131558712);
        localObject3 = (Button)((AlertDialog)localObject3).findViewById(2131558710);
        if (currentTeam.userControlled)
        {
          ((Button)localObject4).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              int j = localObject2.getSelectedItemPosition() + 1;
              int i = j;
              if (j > localObject1.length - 1) {
                i = 0;
              }
              localObject2.setSelection(i);
            }
          });
          ((Button)localObject3).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              int j = localObject2.getSelectedItemPosition() - 1;
              int i = j;
              if (j < 0) {
                i = localObject1.length - 1;
              }
              localObject2.setSelection(i);
            }
          });
          return;
          localObject1 = new String[1];
          localObject1[0] = (currentTeam.abbr + " [1/1]");
          j = k;
        }
        else
        {
          ((Button)localObject4).setEnabled(false);
          ((Button)localObject3).setEnabled(false);
          return;
        }
      }
      Toast.makeText(this, "No trades were found!", 0).show();
      ((AlertDialog)localObject3).dismiss();
      return;
    }
    final Object localObject1 = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject1).setMessage("You past the trade deadline! Trades are not allowed past week 8.").setPositiveButton("Ok", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    });
    ((AlertDialog.Builder)localObject1).create().show();
  }
  
  class CheckNameUniqueOnline
    extends AsyncTask<String, String, String>
  {
    boolean unique = false;
    
    CheckNameUniqueOnline() {}
    
    /* Error */
    protected String doInBackground(String... paramVarArgs)
    {
      // Byte code:
      //   0: aload_1
      //   1: iconst_0
      //   2: aaload
      //   3: astore 8
      //   5: aconst_null
      //   6: astore 6
      //   8: aconst_null
      //   9: astore 4
      //   11: aconst_null
      //   12: astore 7
      //   14: aconst_null
      //   15: astore 9
      //   17: aload 7
      //   19: astore 5
      //   21: new 35	java/net/URL
      //   24: dup
      //   25: ldc 37
      //   27: invokespecial 40	java/net/URL:<init>	(Ljava/lang/String;)V
      //   30: invokevirtual 44	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   33: checkcast 46	java/net/HttpURLConnection
      //   36: astore_1
      //   37: aload 7
      //   39: astore 5
      //   41: aload_1
      //   42: astore 4
      //   44: aload_1
      //   45: astore 6
      //   47: aload_1
      //   48: iconst_1
      //   49: invokevirtual 50	java/net/HttpURLConnection:setDoOutput	(Z)V
      //   52: aload 7
      //   54: astore 5
      //   56: aload_1
      //   57: astore 4
      //   59: aload_1
      //   60: astore 6
      //   62: aload_1
      //   63: ldc 52
      //   65: invokevirtual 55	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
      //   68: aload 7
      //   70: astore 5
      //   72: aload_1
      //   73: astore 4
      //   75: aload_1
      //   76: astore 6
      //   78: aload_1
      //   79: ldc 57
      //   81: ldc 59
      //   83: invokevirtual 63	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   86: aload 7
      //   88: astore 5
      //   90: aload_1
      //   91: astore 4
      //   93: aload_1
      //   94: astore 6
      //   96: aload_1
      //   97: ldc 65
      //   99: ldc 59
      //   101: invokevirtual 63	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   104: aload 7
      //   106: astore 5
      //   108: aload_1
      //   109: astore 4
      //   111: aload_1
      //   112: astore 6
      //   114: new 67	java/io/BufferedWriter
      //   117: dup
      //   118: new 69	java/io/OutputStreamWriter
      //   121: dup
      //   122: aload_1
      //   123: invokevirtual 73	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   126: ldc 75
      //   128: invokespecial 78	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;Ljava/lang/String;)V
      //   131: invokespecial 81	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
      //   134: astore 10
      //   136: aload 7
      //   138: astore 5
      //   140: aload_1
      //   141: astore 4
      //   143: aload_1
      //   144: astore 6
      //   146: aload 10
      //   148: aload 8
      //   150: invokevirtual 86	java/io/Writer:write	(Ljava/lang/String;)V
      //   153: aload 7
      //   155: astore 5
      //   157: aload_1
      //   158: astore 4
      //   160: aload_1
      //   161: astore 6
      //   163: aload 10
      //   165: invokevirtual 89	java/io/Writer:flush	()V
      //   168: aload 7
      //   170: astore 5
      //   172: aload_1
      //   173: astore 4
      //   175: aload_1
      //   176: astore 6
      //   178: aload 10
      //   180: invokevirtual 92	java/io/Writer:close	()V
      //   183: aload 7
      //   185: astore 5
      //   187: aload_1
      //   188: astore 4
      //   190: aload_1
      //   191: astore 6
      //   193: aload_1
      //   194: invokevirtual 96	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   197: astore 10
      //   199: aload 7
      //   201: astore 5
      //   203: aload_1
      //   204: astore 4
      //   206: aload_1
      //   207: astore 6
      //   209: new 98	java/lang/StringBuffer
      //   212: dup
      //   213: invokespecial 99	java/lang/StringBuffer:<init>	()V
      //   216: astore 8
      //   218: aload 10
      //   220: ifnonnull +32 -> 252
      //   223: aload_1
      //   224: ifnull +7 -> 231
      //   227: aload_1
      //   228: invokevirtual 102	java/net/HttpURLConnection:disconnect	()V
      //   231: iconst_0
      //   232: ifeq +11 -> 243
      //   235: new 104	java/lang/NullPointerException
      //   238: dup
      //   239: invokespecial 105	java/lang/NullPointerException:<init>	()V
      //   242: athrow
      //   243: aconst_null
      //   244: areturn
      //   245: astore_1
      //   246: aload_1
      //   247: invokevirtual 108	java/io/IOException:printStackTrace	()V
      //   250: aconst_null
      //   251: areturn
      //   252: aload 7
      //   254: astore 5
      //   256: aload_1
      //   257: astore 4
      //   259: aload_1
      //   260: astore 6
      //   262: new 110	java/io/BufferedReader
      //   265: dup
      //   266: new 112	java/io/InputStreamReader
      //   269: dup
      //   270: aload 10
      //   272: invokespecial 115	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
      //   275: invokespecial 118	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
      //   278: astore 7
      //   280: aload 7
      //   282: invokevirtual 122	java/io/BufferedReader:readLine	()Ljava/lang/String;
      //   285: astore 4
      //   287: aload 4
      //   289: ifnull +66 -> 355
      //   292: aload 8
      //   294: new 124	java/lang/StringBuilder
      //   297: dup
      //   298: invokespecial 125	java/lang/StringBuilder:<init>	()V
      //   301: aload 4
      //   303: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   306: ldc -125
      //   308: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   311: invokevirtual 134	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   314: invokevirtual 137	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   317: pop
      //   318: goto -38 -> 280
      //   321: astore 8
      //   323: aload 7
      //   325: astore 5
      //   327: aload_1
      //   328: astore 4
      //   330: aload 8
      //   332: invokevirtual 108	java/io/IOException:printStackTrace	()V
      //   335: aload_1
      //   336: ifnull +7 -> 343
      //   339: aload_1
      //   340: invokevirtual 102	java/net/HttpURLConnection:disconnect	()V
      //   343: aload 7
      //   345: ifnull +8 -> 353
      //   348: aload 7
      //   350: invokevirtual 138	java/io/BufferedReader:close	()V
      //   353: aconst_null
      //   354: areturn
      //   355: aload 8
      //   357: invokevirtual 142	java/lang/StringBuffer:length	()I
      //   360: istore_2
      //   361: iload_2
      //   362: ifne +31 -> 393
      //   365: aload_1
      //   366: ifnull +7 -> 373
      //   369: aload_1
      //   370: invokevirtual 102	java/net/HttpURLConnection:disconnect	()V
      //   373: aload 7
      //   375: ifnull +8 -> 383
      //   378: aload 7
      //   380: invokevirtual 138	java/io/BufferedReader:close	()V
      //   383: aconst_null
      //   384: areturn
      //   385: astore_1
      //   386: aload_1
      //   387: invokevirtual 108	java/io/IOException:printStackTrace	()V
      //   390: goto -7 -> 383
      //   393: aload 8
      //   395: invokevirtual 143	java/lang/StringBuffer:toString	()Ljava/lang/String;
      //   398: astore 4
      //   400: getstatic 149	java/lang/System:out	Ljava/io/PrintStream;
      //   403: new 124	java/lang/StringBuilder
      //   406: dup
      //   407: invokespecial 125	java/lang/StringBuilder:<init>	()V
      //   410: ldc -105
      //   412: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   415: aload 4
      //   417: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   420: invokevirtual 134	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   423: invokevirtual 156	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   426: new 158	org/json/JSONObject
      //   429: dup
      //   430: aload 4
      //   432: invokespecial 159	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   435: astore 5
      //   437: getstatic 149	java/lang/System:out	Ljava/io/PrintStream;
      //   440: new 124	java/lang/StringBuilder
      //   443: dup
      //   444: invokespecial 125	java/lang/StringBuilder:<init>	()V
      //   447: ldc -95
      //   449: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   452: aload 5
      //   454: ldc -94
      //   456: invokevirtual 166	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   459: checkcast 168	java/lang/Integer
      //   462: invokevirtual 171	java/lang/Integer:intValue	()I
      //   465: invokevirtual 174	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   468: invokevirtual 134	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   471: invokevirtual 156	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   474: aload 5
      //   476: ldc -94
      //   478: invokevirtual 166	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   481: checkcast 168	java/lang/Integer
      //   484: invokevirtual 171	java/lang/Integer:intValue	()I
      //   487: iconst_1
      //   488: if_icmpne +31 -> 519
      //   491: iconst_1
      //   492: istore_3
      //   493: aload_0
      //   494: iload_3
      //   495: putfield 21	com/achijones/profootballcoach/MainActivity$CheckNameUniqueOnline:unique	Z
      //   498: aload_1
      //   499: ifnull +7 -> 506
      //   502: aload_1
      //   503: invokevirtual 102	java/net/HttpURLConnection:disconnect	()V
      //   506: aload 7
      //   508: ifnull +8 -> 516
      //   511: aload 7
      //   513: invokevirtual 138	java/io/BufferedReader:close	()V
      //   516: aload 4
      //   518: areturn
      //   519: iconst_0
      //   520: istore_3
      //   521: goto -28 -> 493
      //   524: astore 5
      //   526: aload 5
      //   528: invokevirtual 175	java/lang/Exception:printStackTrace	()V
      //   531: goto -33 -> 498
      //   534: astore 4
      //   536: aload 7
      //   538: astore 5
      //   540: aload_1
      //   541: ifnull +7 -> 548
      //   544: aload_1
      //   545: invokevirtual 102	java/net/HttpURLConnection:disconnect	()V
      //   548: aload 5
      //   550: ifnull +8 -> 558
      //   553: aload 5
      //   555: invokevirtual 138	java/io/BufferedReader:close	()V
      //   558: aload 4
      //   560: athrow
      //   561: astore_1
      //   562: aload_1
      //   563: invokevirtual 108	java/io/IOException:printStackTrace	()V
      //   566: goto -50 -> 516
      //   569: astore_1
      //   570: aload_1
      //   571: invokevirtual 108	java/io/IOException:printStackTrace	()V
      //   574: goto -221 -> 353
      //   577: astore_1
      //   578: aload_1
      //   579: invokevirtual 108	java/io/IOException:printStackTrace	()V
      //   582: goto -24 -> 558
      //   585: astore 6
      //   587: aload 4
      //   589: astore_1
      //   590: aload 6
      //   592: astore 4
      //   594: goto -54 -> 540
      //   597: astore 8
      //   599: aload 9
      //   601: astore 7
      //   603: aload 6
      //   605: astore_1
      //   606: goto -283 -> 323
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	609	0	this	CheckNameUniqueOnline
      //   0	609	1	paramVarArgs	String[]
      //   360	2	2	i	int
      //   492	29	3	bool	boolean
      //   9	508	4	localObject1	Object
      //   534	54	4	localObject2	Object
      //   592	1	4	localObject3	Object
      //   19	456	5	localObject4	Object
      //   524	3	5	localException	Exception
      //   538	16	5	localObject5	Object
      //   6	255	6	arrayOfString	String[]
      //   585	19	6	localObject6	Object
      //   12	590	7	localObject7	Object
      //   3	290	8	localObject8	Object
      //   321	73	8	localIOException1	IOException
      //   597	1	8	localIOException2	IOException
      //   15	585	9	localObject9	Object
      //   134	137	10	localObject10	Object
      // Exception table:
      //   from	to	target	type
      //   235	243	245	java/io/IOException
      //   280	287	321	java/io/IOException
      //   292	318	321	java/io/IOException
      //   355	361	321	java/io/IOException
      //   393	426	321	java/io/IOException
      //   426	491	321	java/io/IOException
      //   493	498	321	java/io/IOException
      //   526	531	321	java/io/IOException
      //   378	383	385	java/io/IOException
      //   426	491	524	java/lang/Exception
      //   493	498	524	java/lang/Exception
      //   280	287	534	finally
      //   292	318	534	finally
      //   355	361	534	finally
      //   393	426	534	finally
      //   426	491	534	finally
      //   493	498	534	finally
      //   526	531	534	finally
      //   511	516	561	java/io/IOException
      //   348	353	569	java/io/IOException
      //   553	558	577	java/io/IOException
      //   21	37	585	finally
      //   47	52	585	finally
      //   62	68	585	finally
      //   78	86	585	finally
      //   96	104	585	finally
      //   114	136	585	finally
      //   146	153	585	finally
      //   163	168	585	finally
      //   178	183	585	finally
      //   193	199	585	finally
      //   209	218	585	finally
      //   262	280	585	finally
      //   330	335	585	finally
      //   21	37	597	java/io/IOException
      //   47	52	597	java/io/IOException
      //   62	68	597	java/io/IOException
      //   78	86	597	java/io/IOException
      //   96	104	597	java/io/IOException
      //   114	136	597	java/io/IOException
      //   146	153	597	java/io/IOException
      //   163	168	597	java/io/IOException
      //   178	183	597	java/io/IOException
      //   193	199	597	java/io/IOException
      //   209	218	597	java/io/IOException
      //   262	280	597	java/io/IOException
    }
    
    protected void onPostExecute(String paramString)
    {
      if (unique)
      {
        insertTeamOnlineDialog();
        return;
      }
      Toast.makeText(MainActivity.this, "Team name not unique, or could not connect.", 0).show();
      goOnline();
    }
  }
  
  class InsertTeamOnline
    extends AsyncTask<String, String, String>
  {
    boolean success;
    
    InsertTeamOnline() {}
    
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
      //   141: aload 7
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
      //   205: astore 7
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
      //   275: ifnull +64 -> 339
      //   278: aload 7
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
      //   306: astore 7
      //   308: aload 6
      //   310: astore 4
      //   312: aload_1
      //   313: astore_3
      //   314: aload 7
      //   316: invokevirtual 106	java/io/IOException:printStackTrace	()V
      //   319: aload_1
      //   320: ifnull +7 -> 327
      //   323: aload_1
      //   324: invokevirtual 100	java/net/HttpURLConnection:disconnect	()V
      //   327: aload 6
      //   329: ifnull +8 -> 337
      //   332: aload 6
      //   334: invokevirtual 136	java/io/BufferedReader:close	()V
      //   337: aconst_null
      //   338: areturn
      //   339: aload 7
      //   341: invokevirtual 140	java/lang/StringBuffer:length	()I
      //   344: istore_2
      //   345: iload_2
      //   346: ifne +31 -> 377
      //   349: aload_1
      //   350: ifnull +7 -> 357
      //   353: aload_1
      //   354: invokevirtual 100	java/net/HttpURLConnection:disconnect	()V
      //   357: aload 6
      //   359: ifnull +8 -> 367
      //   362: aload 6
      //   364: invokevirtual 136	java/io/BufferedReader:close	()V
      //   367: aconst_null
      //   368: areturn
      //   369: astore_1
      //   370: aload_1
      //   371: invokevirtual 106	java/io/IOException:printStackTrace	()V
      //   374: goto -7 -> 367
      //   377: aload 7
      //   379: invokevirtual 141	java/lang/StringBuffer:toString	()Ljava/lang/String;
      //   382: astore_3
      //   383: getstatic 147	java/lang/System:out	Ljava/io/PrintStream;
      //   386: new 122	java/lang/StringBuilder
      //   389: dup
      //   390: invokespecial 123	java/lang/StringBuilder:<init>	()V
      //   393: ldc -107
      //   395: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   398: aload_3
      //   399: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   402: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   405: invokevirtual 154	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   408: new 156	org/json/JSONObject
      //   411: dup
      //   412: aload_3
      //   413: invokespecial 157	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   416: ldc -98
      //   418: invokevirtual 162	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   421: checkcast 164	java/lang/Integer
      //   424: invokevirtual 167	java/lang/Integer:intValue	()I
      //   427: ifne +36 -> 463
      //   430: aload_0
      //   431: iconst_0
      //   432: putfield 169	com/achijones/profootballcoach/MainActivity$InsertTeamOnline:success	Z
      //   435: getstatic 147	java/lang/System:out	Ljava/io/PrintStream;
      //   438: ldc -85
      //   440: invokevirtual 154	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   443: aload_1
      //   444: ifnull +7 -> 451
      //   447: aload_1
      //   448: invokevirtual 100	java/net/HttpURLConnection:disconnect	()V
      //   451: aload 6
      //   453: ifnull +8 -> 461
      //   456: aload 6
      //   458: invokevirtual 136	java/io/BufferedReader:close	()V
      //   461: aload_3
      //   462: areturn
      //   463: aload_0
      //   464: iconst_1
      //   465: putfield 169	com/achijones/profootballcoach/MainActivity$InsertTeamOnline:success	Z
      //   468: goto -25 -> 443
      //   471: astore 4
      //   473: aload 4
      //   475: invokevirtual 172	java/lang/Exception:printStackTrace	()V
      //   478: goto -35 -> 443
      //   481: astore_3
      //   482: aload 6
      //   484: astore 4
      //   486: aload_1
      //   487: ifnull +7 -> 494
      //   490: aload_1
      //   491: invokevirtual 100	java/net/HttpURLConnection:disconnect	()V
      //   494: aload 4
      //   496: ifnull +8 -> 504
      //   499: aload 4
      //   501: invokevirtual 136	java/io/BufferedReader:close	()V
      //   504: aload_3
      //   505: athrow
      //   506: astore_1
      //   507: aload_1
      //   508: invokevirtual 106	java/io/IOException:printStackTrace	()V
      //   511: goto -50 -> 461
      //   514: astore_1
      //   515: aload_1
      //   516: invokevirtual 106	java/io/IOException:printStackTrace	()V
      //   519: goto -182 -> 337
      //   522: astore_1
      //   523: aload_1
      //   524: invokevirtual 106	java/io/IOException:printStackTrace	()V
      //   527: goto -23 -> 504
      //   530: astore 5
      //   532: aload_3
      //   533: astore_1
      //   534: aload 5
      //   536: astore_3
      //   537: goto -51 -> 486
      //   540: astore 7
      //   542: aload 8
      //   544: astore 6
      //   546: aload 5
      //   548: astore_1
      //   549: goto -241 -> 308
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	552	0	this	InsertTeamOnline
      //   0	552	1	paramVarArgs	String[]
      //   344	2	2	i	int
      //   9	453	3	localObject1	Object
      //   481	52	3	localObject2	Object
      //   536	1	3	localObject3	Object
      //   18	293	4	localObject4	Object
      //   471	3	4	localException	Exception
      //   484	16	4	localObject5	Object
      //   6	243	5	arrayOfString	String[]
      //   530	17	5	localObject6	Object
      //   11	534	6	localObject7	Object
      //   3	276	7	localObject8	Object
      //   306	72	7	localIOException1	IOException
      //   540	1	7	localIOException2	IOException
      //   14	529	8	localObject9	Object
      //   128	131	9	localObject10	Object
      // Exception table:
      //   from	to	target	type
      //   224	232	234	java/io/IOException
      //   268	274	306	java/io/IOException
      //   278	303	306	java/io/IOException
      //   339	345	306	java/io/IOException
      //   377	408	306	java/io/IOException
      //   408	443	306	java/io/IOException
      //   463	468	306	java/io/IOException
      //   473	478	306	java/io/IOException
      //   362	367	369	java/io/IOException
      //   408	443	471	java/lang/Exception
      //   463	468	471	java/lang/Exception
      //   268	274	481	finally
      //   278	303	481	finally
      //   339	345	481	finally
      //   377	408	481	finally
      //   408	443	481	finally
      //   463	468	481	finally
      //   473	478	481	finally
      //   456	461	506	java/io/IOException
      //   332	337	514	java/io/IOException
      //   499	504	522	java/io/IOException
      //   20	36	530	finally
      //   45	50	530	finally
      //   59	65	530	finally
      //   74	82	530	finally
      //   91	99	530	finally
      //   108	130	530	finally
      //   139	146	530	finally
      //   155	160	530	finally
      //   169	174	530	finally
      //   183	189	530	finally
      //   198	207	530	finally
      //   250	268	530	finally
      //   314	319	530	finally
      //   20	36	540	java/io/IOException
      //   45	50	540	java/io/IOException
      //   59	65	540	java/io/IOException
      //   74	82	540	java/io/IOException
      //   91	99	540	java/io/IOException
      //   108	130	540	java/io/IOException
      //   139	146	540	java/io/IOException
      //   155	160	540	java/io/IOException
      //   169	174	540	java/io/IOException
      //   183	189	540	java/io/IOException
      //   198	207	540	java/io/IOException
      //   250	268	540	java/io/IOException
    }
    
    protected void onPostExecute(String paramString)
    {
      if (success)
      {
        Toast.makeText(MainActivity.this, "Successfully sent team online.", 0).show();
        return;
      }
      Toast.makeText(MainActivity.this, "Something went wrong! Team not placed online.", 0).show();
    }
  }
  
  class RemoveAndInsertTeamOnline
    extends AsyncTask<String, String, String>
  {
    boolean success = false;
    
    RemoveAndInsertTeamOnline() {}
    
    /* Error */
    protected String doInBackground(String... paramVarArgs)
    {
      // Byte code:
      //   0: aload_1
      //   1: iconst_0
      //   2: aaload
      //   3: astore 8
      //   5: aconst_null
      //   6: astore 6
      //   8: aconst_null
      //   9: astore 4
      //   11: aconst_null
      //   12: astore 7
      //   14: aconst_null
      //   15: astore 9
      //   17: aload 7
      //   19: astore 5
      //   21: new 35	java/net/URL
      //   24: dup
      //   25: ldc 37
      //   27: invokespecial 40	java/net/URL:<init>	(Ljava/lang/String;)V
      //   30: invokevirtual 44	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   33: checkcast 46	java/net/HttpURLConnection
      //   36: astore_1
      //   37: aload 7
      //   39: astore 5
      //   41: aload_1
      //   42: astore 4
      //   44: aload_1
      //   45: astore 6
      //   47: aload_1
      //   48: iconst_1
      //   49: invokevirtual 50	java/net/HttpURLConnection:setDoOutput	(Z)V
      //   52: aload 7
      //   54: astore 5
      //   56: aload_1
      //   57: astore 4
      //   59: aload_1
      //   60: astore 6
      //   62: aload_1
      //   63: ldc 52
      //   65: invokevirtual 55	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
      //   68: aload 7
      //   70: astore 5
      //   72: aload_1
      //   73: astore 4
      //   75: aload_1
      //   76: astore 6
      //   78: aload_1
      //   79: ldc 57
      //   81: ldc 59
      //   83: invokevirtual 63	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   86: aload 7
      //   88: astore 5
      //   90: aload_1
      //   91: astore 4
      //   93: aload_1
      //   94: astore 6
      //   96: aload_1
      //   97: ldc 65
      //   99: ldc 59
      //   101: invokevirtual 63	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   104: aload 7
      //   106: astore 5
      //   108: aload_1
      //   109: astore 4
      //   111: aload_1
      //   112: astore 6
      //   114: new 67	java/io/BufferedWriter
      //   117: dup
      //   118: new 69	java/io/OutputStreamWriter
      //   121: dup
      //   122: aload_1
      //   123: invokevirtual 73	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   126: ldc 75
      //   128: invokespecial 78	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;Ljava/lang/String;)V
      //   131: invokespecial 81	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
      //   134: astore 10
      //   136: aload 7
      //   138: astore 5
      //   140: aload_1
      //   141: astore 4
      //   143: aload_1
      //   144: astore 6
      //   146: aload 10
      //   148: aload 8
      //   150: invokevirtual 86	java/io/Writer:write	(Ljava/lang/String;)V
      //   153: aload 7
      //   155: astore 5
      //   157: aload_1
      //   158: astore 4
      //   160: aload_1
      //   161: astore 6
      //   163: aload 10
      //   165: invokevirtual 89	java/io/Writer:flush	()V
      //   168: aload 7
      //   170: astore 5
      //   172: aload_1
      //   173: astore 4
      //   175: aload_1
      //   176: astore 6
      //   178: aload 10
      //   180: invokevirtual 92	java/io/Writer:close	()V
      //   183: aload 7
      //   185: astore 5
      //   187: aload_1
      //   188: astore 4
      //   190: aload_1
      //   191: astore 6
      //   193: aload_1
      //   194: invokevirtual 96	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   197: astore 10
      //   199: aload 7
      //   201: astore 5
      //   203: aload_1
      //   204: astore 4
      //   206: aload_1
      //   207: astore 6
      //   209: new 98	java/lang/StringBuffer
      //   212: dup
      //   213: invokespecial 99	java/lang/StringBuffer:<init>	()V
      //   216: astore 8
      //   218: aload 10
      //   220: ifnonnull +32 -> 252
      //   223: aload_1
      //   224: ifnull +7 -> 231
      //   227: aload_1
      //   228: invokevirtual 102	java/net/HttpURLConnection:disconnect	()V
      //   231: iconst_0
      //   232: ifeq +11 -> 243
      //   235: new 104	java/lang/NullPointerException
      //   238: dup
      //   239: invokespecial 105	java/lang/NullPointerException:<init>	()V
      //   242: athrow
      //   243: aconst_null
      //   244: areturn
      //   245: astore_1
      //   246: aload_1
      //   247: invokevirtual 108	java/io/IOException:printStackTrace	()V
      //   250: aconst_null
      //   251: areturn
      //   252: aload 7
      //   254: astore 5
      //   256: aload_1
      //   257: astore 4
      //   259: aload_1
      //   260: astore 6
      //   262: new 110	java/io/BufferedReader
      //   265: dup
      //   266: new 112	java/io/InputStreamReader
      //   269: dup
      //   270: aload 10
      //   272: invokespecial 115	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
      //   275: invokespecial 118	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
      //   278: astore 7
      //   280: aload 7
      //   282: invokevirtual 122	java/io/BufferedReader:readLine	()Ljava/lang/String;
      //   285: astore 4
      //   287: aload 4
      //   289: ifnull +66 -> 355
      //   292: aload 8
      //   294: new 124	java/lang/StringBuilder
      //   297: dup
      //   298: invokespecial 125	java/lang/StringBuilder:<init>	()V
      //   301: aload 4
      //   303: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   306: ldc -125
      //   308: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   311: invokevirtual 134	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   314: invokevirtual 137	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   317: pop
      //   318: goto -38 -> 280
      //   321: astore 8
      //   323: aload 7
      //   325: astore 5
      //   327: aload_1
      //   328: astore 4
      //   330: aload 8
      //   332: invokevirtual 108	java/io/IOException:printStackTrace	()V
      //   335: aload_1
      //   336: ifnull +7 -> 343
      //   339: aload_1
      //   340: invokevirtual 102	java/net/HttpURLConnection:disconnect	()V
      //   343: aload 7
      //   345: ifnull +8 -> 353
      //   348: aload 7
      //   350: invokevirtual 138	java/io/BufferedReader:close	()V
      //   353: aconst_null
      //   354: areturn
      //   355: aload 8
      //   357: invokevirtual 142	java/lang/StringBuffer:length	()I
      //   360: istore_2
      //   361: iload_2
      //   362: ifne +31 -> 393
      //   365: aload_1
      //   366: ifnull +7 -> 373
      //   369: aload_1
      //   370: invokevirtual 102	java/net/HttpURLConnection:disconnect	()V
      //   373: aload 7
      //   375: ifnull +8 -> 383
      //   378: aload 7
      //   380: invokevirtual 138	java/io/BufferedReader:close	()V
      //   383: aconst_null
      //   384: areturn
      //   385: astore_1
      //   386: aload_1
      //   387: invokevirtual 108	java/io/IOException:printStackTrace	()V
      //   390: goto -7 -> 383
      //   393: aload 8
      //   395: invokevirtual 143	java/lang/StringBuffer:toString	()Ljava/lang/String;
      //   398: astore 4
      //   400: getstatic 149	java/lang/System:out	Ljava/io/PrintStream;
      //   403: new 124	java/lang/StringBuilder
      //   406: dup
      //   407: invokespecial 125	java/lang/StringBuilder:<init>	()V
      //   410: ldc -105
      //   412: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   415: aload 4
      //   417: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   420: invokevirtual 134	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   423: invokevirtual 156	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   426: new 158	org/json/JSONObject
      //   429: dup
      //   430: aload 4
      //   432: invokespecial 159	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   435: ldc -96
      //   437: invokevirtual 164	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   440: checkcast 166	java/lang/Integer
      //   443: invokevirtual 169	java/lang/Integer:intValue	()I
      //   446: iconst_1
      //   447: if_icmpne +31 -> 478
      //   450: iconst_1
      //   451: istore_3
      //   452: aload_0
      //   453: iload_3
      //   454: putfield 21	com/achijones/profootballcoach/MainActivity$RemoveAndInsertTeamOnline:success	Z
      //   457: aload_1
      //   458: ifnull +7 -> 465
      //   461: aload_1
      //   462: invokevirtual 102	java/net/HttpURLConnection:disconnect	()V
      //   465: aload 7
      //   467: ifnull +8 -> 475
      //   470: aload 7
      //   472: invokevirtual 138	java/io/BufferedReader:close	()V
      //   475: aload 4
      //   477: areturn
      //   478: iconst_0
      //   479: istore_3
      //   480: goto -28 -> 452
      //   483: astore 5
      //   485: aload 5
      //   487: invokevirtual 170	java/lang/Exception:printStackTrace	()V
      //   490: goto -33 -> 457
      //   493: astore 4
      //   495: aload 7
      //   497: astore 5
      //   499: aload_1
      //   500: ifnull +7 -> 507
      //   503: aload_1
      //   504: invokevirtual 102	java/net/HttpURLConnection:disconnect	()V
      //   507: aload 5
      //   509: ifnull +8 -> 517
      //   512: aload 5
      //   514: invokevirtual 138	java/io/BufferedReader:close	()V
      //   517: aload 4
      //   519: athrow
      //   520: astore_1
      //   521: aload_1
      //   522: invokevirtual 108	java/io/IOException:printStackTrace	()V
      //   525: goto -50 -> 475
      //   528: astore_1
      //   529: aload_1
      //   530: invokevirtual 108	java/io/IOException:printStackTrace	()V
      //   533: goto -180 -> 353
      //   536: astore_1
      //   537: aload_1
      //   538: invokevirtual 108	java/io/IOException:printStackTrace	()V
      //   541: goto -24 -> 517
      //   544: astore 6
      //   546: aload 4
      //   548: astore_1
      //   549: aload 6
      //   551: astore 4
      //   553: goto -54 -> 499
      //   556: astore 8
      //   558: aload 9
      //   560: astore 7
      //   562: aload 6
      //   564: astore_1
      //   565: goto -242 -> 323
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	568	0	this	RemoveAndInsertTeamOnline
      //   0	568	1	paramVarArgs	String[]
      //   360	2	2	i	int
      //   451	29	3	bool	boolean
      //   9	467	4	localObject1	Object
      //   493	54	4	localObject2	Object
      //   551	1	4	localObject3	Object
      //   19	307	5	localObject4	Object
      //   483	3	5	localException	Exception
      //   497	16	5	localObject5	Object
      //   6	255	6	arrayOfString	String[]
      //   544	19	6	localObject6	Object
      //   12	549	7	localObject7	Object
      //   3	290	8	localObject8	Object
      //   321	73	8	localIOException1	IOException
      //   556	1	8	localIOException2	IOException
      //   15	544	9	localObject9	Object
      //   134	137	10	localObject10	Object
      // Exception table:
      //   from	to	target	type
      //   235	243	245	java/io/IOException
      //   280	287	321	java/io/IOException
      //   292	318	321	java/io/IOException
      //   355	361	321	java/io/IOException
      //   393	426	321	java/io/IOException
      //   426	450	321	java/io/IOException
      //   452	457	321	java/io/IOException
      //   485	490	321	java/io/IOException
      //   378	383	385	java/io/IOException
      //   426	450	483	java/lang/Exception
      //   452	457	483	java/lang/Exception
      //   280	287	493	finally
      //   292	318	493	finally
      //   355	361	493	finally
      //   393	426	493	finally
      //   426	450	493	finally
      //   452	457	493	finally
      //   485	490	493	finally
      //   470	475	520	java/io/IOException
      //   348	353	528	java/io/IOException
      //   512	517	536	java/io/IOException
      //   21	37	544	finally
      //   47	52	544	finally
      //   62	68	544	finally
      //   78	86	544	finally
      //   96	104	544	finally
      //   114	136	544	finally
      //   146	153	544	finally
      //   163	168	544	finally
      //   178	183	544	finally
      //   193	199	544	finally
      //   209	218	544	finally
      //   262	280	544	finally
      //   330	335	544	finally
      //   21	37	556	java/io/IOException
      //   47	52	556	java/io/IOException
      //   62	68	556	java/io/IOException
      //   78	86	556	java/io/IOException
      //   96	104	556	java/io/IOException
      //   114	136	556	java/io/IOException
      //   146	153	556	java/io/IOException
      //   163	168	556	java/io/IOException
      //   178	183	556	java/io/IOException
      //   193	199	556	java/io/IOException
      //   209	218	556	java/io/IOException
      //   262	280	556	java/io/IOException
    }
    
    protected void onPostExecute(String paramString)
    {
      if (success)
      {
        new MainActivity.InsertTeamOnline(MainActivity.this).execute(new String[] { onlineTeamDict.toString() });
        return;
      }
      Toast.makeText(MainActivity.this, "Something went wrong when replacing a team.", 0).show();
      new MainActivity.InsertTeamOnline(MainActivity.this).execute(new String[] { onlineTeamDict.toString() });
    }
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */