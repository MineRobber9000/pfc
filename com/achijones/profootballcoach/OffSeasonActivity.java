package com.achijones.profootballcoach;

import PFCpack.Contract;
import PFCpack.DraftGenerator;
import PFCpack.DraftPick;
import PFCpack.League;
import PFCpack.Player;
import PFCpack.PlayerPickerLogic;
import PFCpack.Team;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OffSeasonActivity
  extends AppCompatActivity
{
  private final int STAGE_ALL_FREE_AGENCY = 1;
  private final int STAGE_DRAFT = 2;
  private final int STAGE_TEAM_FREE_AGENCY = 0;
  private ArrayList<Player> currPlayers;
  private Map<Player, List<String>> currPlayersInfo;
  private ArrayList<Player> currPlayersUnfiltered;
  private String currentPosition;
  private ArrayAdapter dataAdapterPosition;
  int dayFreeAgency;
  private Button doneButton;
  int draftPickNum;
  ArrayList<ArrayList<Player>> draftPlayers;
  ArrayList<String> draftResultsList;
  int draftRound;
  private ExpandableListAdapterDraft expListAdapter;
  private ExpandableListView expandList;
  ArrayList<ArrayList<Player>> freeAgencyPlayers;
  private int[] indexHeaderPosition;
  boolean isDraft;
  ArrayList<String> leagueFAResultsList;
  private int maximumAge;
  private int minimumAge;
  private int minimumDur;
  private int minimumFootIQ;
  private int minimumPot;
  private TextView offSeasonText;
  private Spinner positionSpinner;
  private ArrayList<String> positions;
  File regSaveLeagueFile;
  String regSaveLeagueFileStr;
  File regSaveScheduleFile;
  String regSaveScheduleFileStr;
  File regSaveTeamHistFile;
  String regSaveTeamHistFileStr;
  File regSaveTeamsFile;
  String regSaveTeamsFileStr;
  private boolean removeUnaffordable;
  private Button resultsButton;
  File saveLeagueFile;
  String saveLeagueFileStr;
  File saveScheduleFile;
  String saveScheduleFileStr;
  File saveTeamHistFile;
  String saveTeamHistFileStr;
  File saveTeamsFile;
  String saveTeamsFileStr;
  int season;
  private boolean showConfirmation;
  League simLeague;
  private int stage;
  ArrayList<String> teamFAResultsList;
  ArrayList<ArrayList<Player>> teamFreeAgencyPlayers;
  Team userTeam;
  
  private void beginDraft(boolean paramBoolean)
  {
    Object localObject = getDraftTeam();
    Collections.sort((List)draftPlayers.get(0), new PlayerComparator());
    while ((draftRound <= 7) && (((localObject != userTeam) && (!paramBoolean)) || (paramBoolean)))
    {
      boolean bool = false;
      if (draftPickNum > 75) {
        bool = true;
      }
      Player localPlayer = PlayerPickerLogic.draftPlayer((Team)localObject, (ArrayList)draftPlayers.get(0), bool);
      localPlayer.setContract(Contract.getContractDraft(localPlayer, draftPickNum));
      localPlayer.setDraftPickNum(draftPickNum);
      localPlayer.fudgePotentialRookie();
      localPlayer.randomJumpRookie(draftPickNum);
      draftPlayerLists(localPlayer);
      draftPicks.remove(getDraftPick());
      getDraftPickgetTeamOriginalpositionalDraftPicks.remove(getDraftPick());
      expListAdapter.notifyDataSetChanged();
      draftPickNum += 1;
      draftRound = ((draftPickNum - 1) / 32 + 1);
      localObject = "Draft: Round " + draftRound + ", Pick " + draftPickNum;
      offSeasonText.setText((CharSequence)localObject);
      localObject = getDraftTeam();
    }
    if (draftRound > 7)
    {
      currPlayers.clear();
      expListAdapter.notifyDataSetChanged();
      showDraftSummaryDialog(true);
    }
  }
  
  private void colorizeRatings(TextView paramTextView)
  {
    Object localObject = paramTextView.getText().toString().split(" ");
    if (localObject.length > 0)
    {
      localObject = localObject[(localObject.length - 1)];
      if ((!((String)localObject).equals("A")) && (!((String)localObject).equals("A+"))) {
        break label59;
      }
      paramTextView.setTextColor(Color.parseColor("#006600"));
    }
    label59:
    do
    {
      return;
      if ((((String)localObject).equals("B")) || (((String)localObject).equals("B+")))
      {
        paramTextView.setTextColor(Color.parseColor("#00b300"));
        return;
      }
      if ((((String)localObject).equals("C")) || (((String)localObject).equals("C+")))
      {
        paramTextView.setTextColor(Color.parseColor("#e68a00"));
        return;
      }
      if ((((String)localObject).equals("D")) || (((String)localObject).equals("D+")))
      {
        paramTextView.setTextColor(Color.parseColor("#cc3300"));
        return;
      }
    } while ((!((String)localObject).equals("F")) && (!((String)localObject).equals("F+")));
    paramTextView.setTextColor(Color.parseColor("#990000"));
  }
  
  private void draftPlayerLists(Player paramPlayer)
  {
    int i = 0;
    while (i < draftPlayers.size())
    {
      if (((ArrayList)draftPlayers.get(i)).contains(paramPlayer)) {
        ((ArrayList)draftPlayers.get(i)).remove(paramPlayer);
      }
      i += 1;
    }
    currPlayers.remove(paramPlayer);
    DraftPick localDraftPick = getDraftPick();
    Team localTeam = localDraftPick.getTeamOwner();
    Contract localContract = Contract.getContractDraft(paramPlayer, draftPickNum);
    draftResultsList.add(draftPickNum + ": " + abbr + " drafts " + paramPlayer.getPosNameYrOvrPot_Compact() + "\n\t\tUsed " + localDraftPick.getTeamOriginal().getStrAbbrWL() + "'s round " + localDraftPick.getRound() + " pick\n\t\tSigned to a " + localContract.toString() + " contract");
  }
  
  private void draftPlayerUserTeamConfirm(Player paramPlayer)
  {
    paramPlayer.setContract(Contract.getContractDraft(paramPlayer, draftPickNum));
    paramPlayer.setTeam(userTeam);
    paramPlayer.setDraftPickNum(draftPickNum);
    paramPlayer.fudgePotentialRookie();
    if (paramPlayer.randomJumpRookie(draftPickNum)) {
      Toast.makeText(this, "Drafted " + paramPlayer.getPosNameYrOvrPot_OneLine() + " with the number " + draftPickNum + " pick. He looks much better than expected!", 0).show();
    }
    for (;;)
    {
      draftPlayerLists(paramPlayer);
      userTeam.addPlayer(paramPlayer);
      paramPlayer.addTeamPlayedFor(userTeam.abbr, simLeague.getYear());
      updatePositionNeeds();
      userTeam.draftPicks.remove(getDraftPick());
      getDraftPickgetTeamOriginalpositionalDraftPicks.remove(getDraftPick());
      expListAdapter.notifyDataSetChanged();
      draftPickNum += 1;
      draftRound = ((draftPickNum - 1) / 32 + 1);
      paramPlayer = "Draft: Round " + draftRound + ", Pick " + draftPickNum;
      offSeasonText.setText(paramPlayer);
      beginDraft(false);
      return;
      Toast.makeText(this, "Drafted " + paramPlayer.getPosNameYrOvrPot_OneLine() + " with the number " + draftPickNum + " pick.", 0).show();
    }
  }
  
  private void faPlayerLists(Player paramPlayer)
  {
    int i = 0;
    while (i < freeAgencyPlayers.size())
    {
      if (((ArrayList)freeAgencyPlayers.get(i)).contains(paramPlayer)) {
        ((ArrayList)freeAgencyPlayers.get(i)).remove(paramPlayer);
      }
      i += 1;
    }
    simLeague.freeAgents.remove(paramPlayer);
  }
  
  private DraftPick getDraftPick()
  {
    return (DraftPick)simLeague.teamList.get((draftPickNum - 1) % 32)).positionalDraftPicks.get(0);
  }
  
  private Team getDraftTeam()
  {
    return ((DraftPick)simLeague.teamList.get((draftPickNum - 1) % 32)).positionalDraftPicks.get(0)).getTeamOwner();
  }
  
  private String getPlayerDetails(Player paramPlayer)
  {
    String str1 = paramPlayer.getPosition();
    String str2 = Player.getLetterGrade(paramPlayer.getRatFootIQ());
    paramPlayer = paramPlayer.getRatings();
    if (str1.equals("QB")) {
      return "Football IQ: " + str2 + ">Strength: " + Player.getLetterGrade(paramPlayer[0]) + ">Accuracy: " + Player.getLetterGrade(paramPlayer[1]) + ">Evasion: " + Player.getLetterGrade(paramPlayer[2]);
    }
    if (str1.equals("RB")) {
      return "Football IQ: " + str2 + ">Power: " + Player.getLetterGrade(paramPlayer[0]) + ">Speed: " + Player.getLetterGrade(paramPlayer[1]) + ">Evasion: " + Player.getLetterGrade(paramPlayer[2]);
    }
    if (str1.equals("WR")) {
      return "Football IQ: " + str2 + ">Catching: " + Player.getLetterGrade(paramPlayer[0]) + ">Speed: " + Player.getLetterGrade(paramPlayer[1]) + ">Evasion: " + Player.getLetterGrade(paramPlayer[2]);
    }
    if (str1.equals("OL")) {
      return "Football IQ: " + str2 + ">Strength: " + Player.getLetterGrade(paramPlayer[0]) + ">Rush Blk: " + Player.getLetterGrade(paramPlayer[1]) + ">Pass Blk: " + Player.getLetterGrade(paramPlayer[2]);
    }
    if (str1.equals("K")) {
      return "Football IQ: " + str2 + ">Kick Power: " + Player.getLetterGrade(paramPlayer[0]) + ">Accuracy: " + Player.getLetterGrade(paramPlayer[1]) + ">Clumsiness: " + Player.getLetterGrade(paramPlayer[2]);
    }
    if (str1.equals("S")) {
      return "Football IQ: " + str2 + ">Coverage: " + Player.getLetterGrade(paramPlayer[0]) + ">Speed: " + Player.getLetterGrade(paramPlayer[1]) + ">Tackling: " + Player.getLetterGrade(paramPlayer[2]);
    }
    if (str1.equals("CB")) {
      return "Football IQ: " + str2 + ">Coverage: " + Player.getLetterGrade(paramPlayer[0]) + ">Speed: " + Player.getLetterGrade(paramPlayer[1]) + ">Tackling: " + Player.getLetterGrade(paramPlayer[2]);
    }
    if (str1.equals("DL")) {
      return "Football IQ: " + str2 + ">Strength: " + Player.getLetterGrade(paramPlayer[0]) + ">Run Stop: " + Player.getLetterGrade(paramPlayer[1]) + ">Pass Press: " + Player.getLetterGrade(paramPlayer[2]);
    }
    if (str1.equals("LB")) {
      return "Football IQ: " + str2 + ">Tackling: " + Player.getLetterGrade(paramPlayer[0]) + ">Run Stop: " + Player.getLetterGrade(paramPlayer[1]) + ">Pass Cover: " + Player.getLetterGrade(paramPlayer[2]);
    }
    return "ERROR";
  }
  
  private void setPlayerInfoMap()
  {
    currPlayersInfo = new LinkedHashMap();
    Iterator localIterator = currPlayers.iterator();
    while (localIterator.hasNext())
    {
      Player localPlayer = (Player)localIterator.next();
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(getPlayerDetails(localPlayer));
      currPlayersInfo.put(localPlayer, localArrayList);
    }
  }
  
  private Map<Player, List<String>> setPlayerInfoMapRoster(ArrayList<Player> paramArrayList)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      Player localPlayer = (Player)paramArrayList.next();
      if (localPlayer != null)
      {
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(getPlayerDetails(localPlayer) + ">Contract: " + localPlayer.getContract().toString());
        localLinkedHashMap.put(localPlayer, localArrayList);
      }
    }
    return localLinkedHashMap;
  }
  
  private void setPlayerList(String paramString)
  {
    int i;
    if (stage == 0) {
      if (!paramString.equals("All Players"))
      {
        currPlayersUnfiltered = ((ArrayList)teamFreeAgencyPlayers.get(Player.getPosNumber(paramString) + 1));
        currPlayers = new ArrayList();
        currPlayers.addAll(currPlayersUnfiltered);
        i = 0;
      }
    }
    for (;;)
    {
      if (i >= currPlayers.size()) {
        break label452;
      }
      if (((Player)currPlayers.get(i)).getRatDur() < minimumDur)
      {
        currPlayers.remove(i);
        continue;
        currPlayersUnfiltered = ((ArrayList)teamFreeAgencyPlayers.get(0));
        break;
        if (stage == 2)
        {
          if (!paramString.equals("All Players"))
          {
            currPlayersUnfiltered = ((ArrayList)draftPlayers.get(Player.getPosNumber(paramString) + 1));
            break;
          }
          currPlayersUnfiltered = ((ArrayList)draftPlayers.get(0));
          break;
        }
        if (!paramString.equals("All Players"))
        {
          currPlayersUnfiltered = ((ArrayList)freeAgencyPlayers.get(Player.getPosNumber(paramString) + 1));
          break;
        }
        currPlayersUnfiltered = ((ArrayList)freeAgencyPlayers.get(0));
        break;
      }
      if (((Player)currPlayers.get(i)).getRatPot() < minimumPot) {
        currPlayers.remove(i);
      } else if (((Player)currPlayers.get(i)).getRatFootIQ() < minimumFootIQ) {
        currPlayers.remove(i);
      } else if (((Player)currPlayers.get(i)).getAge() < minimumAge) {
        currPlayers.remove(i);
      } else if (((Player)currPlayers.get(i)).getAge() > maximumAge) {
        currPlayers.remove(i);
      } else if ((removeUnaffordable) && (stage != 2) && (userTeam.getSalaryCapRoom() < Contract.getContractFA((Player)currPlayers.get(i)).getMoneyPerYear()) && (Contract.getContractFA((Player)currPlayers.get(i)).getMoneyPerYear() > 0.5D)) {
        currPlayers.remove(i);
      } else {
        i += 1;
      }
    }
    label452:
    setPlayerInfoMap();
  }
  
  private void signFAUserTeamConfirm(Player paramPlayer)
  {
    Contract localContract = Contract.getContractFA(paramPlayer);
    paramPlayer.setContract(localContract);
    paramPlayer.setTeam(userTeam);
    faPlayerLists(paramPlayer);
    userTeam.addPlayer(paramPlayer);
    updatePositionNeeds();
    currPlayers.remove(paramPlayer);
    paramPlayer.addTeamPlayedFor(userTeam.abbr, simLeague.getYear());
    Toast.makeText(this, "Signed " + paramPlayer.getPosNameYrOvrPot_OneLine() + ", advancing day in free agency", 0).show();
    leagueFAResultsList.add(userTeam.abbr + " signs " + paramPlayer.getPosNameAge_Str() + "\n\t\t" + paramPlayer.getOvrPotDurFootIQ_Str() + "\n\t\tSigned to a " + localContract.toString() + " contract");
    int i = 0;
    while (i < currPlayers.size()) {
      if ((removeUnaffordable) && (stage != 2) && (userTeam.getSalaryCapRoom() < Contract.getContractFA((Player)currPlayers.get(i)).getMoneyPerYear()) && (Contract.getContractFA((Player)currPlayers.get(i)).getMoneyPerYear() > 0.5D)) {
        currPlayers.remove(i);
      } else {
        i += 1;
      }
    }
    dayFreeAgency += 1;
    simLeague.freeAgencyDay(leagueFAResultsList);
    freeAgencyPlayers = transferListsFA(simLeague.freeAgents);
    currentPosition = positionSpinner.getItemAtPosition(positionSpinner.getSelectedItemPosition()).toString();
    if (positionSpinner.getSelectedItemPosition() == 0) {
      setPlayerList(currentPosition);
    }
    for (;;)
    {
      expListAdapter = new ExpandableListAdapterDraft(this, currPlayers, currPlayersInfo, true);
      expandList.setAdapter(expListAdapter);
      expListAdapter.notifyDataSetChanged();
      paramPlayer = "Day " + dayFreeAgency + ", Free Agency: $" + userTeam.getSalaryCapRoom() + " mil room";
      offSeasonText.setText(paramPlayer);
      return;
      setPlayerList(currentPosition.substring(0, 2).trim());
    }
  }
  
  private void signTeamFAPlayerUserTeamConfirm(Player paramPlayer)
  {
    Contract localContract = Contract.getContractFA(paramPlayer);
    paramPlayer.setContract(localContract);
    teamFAPlayerLists(paramPlayer);
    userTeam.addPlayer(paramPlayer);
    userTeam.playersFAs.remove(paramPlayer);
    updatePositionNeeds();
    currPlayers.remove(paramPlayer);
    Toast.makeText(this, "Signed " + paramPlayer.getPosNameYrOvrPot_OneLine(), 0).show();
    teamFAResultsList.add(userTeam.abbr + " signs " + paramPlayer.getPosNameAge_Str() + "\n\t\t" + paramPlayer.getOvrPotDurFootIQ_Str() + "\n\t\tSigned to a " + localContract.toString() + " contract");
    int i = 0;
    while (i < currPlayers.size()) {
      if ((removeUnaffordable) && (stage != 2) && (userTeam.getSalaryCapRoom() < Contract.getContractFA((Player)currPlayers.get(i)).getMoneyPerYear())) {
        currPlayers.remove(i);
      } else {
        i += 1;
      }
    }
    expListAdapter.notifyDataSetChanged();
    paramPlayer = "Team Free Agency: $" + userTeam.getSalaryCapRoom() + " mil room";
    offSeasonText.setText(paramPlayer);
  }
  
  private void teamFAPlayerLists(Player paramPlayer)
  {
    int i = 0;
    while (i < teamFreeAgencyPlayers.size())
    {
      if (((ArrayList)teamFreeAgencyPlayers.get(i)).contains(paramPlayer)) {
        ((ArrayList)teamFreeAgencyPlayers.get(i)).remove(paramPlayer);
      }
      i += 1;
    }
  }
  
  public void advanceNextSeason()
  {
    simLeague.resetForNewSeason();
    simLeague.saveLeague(regSaveLeagueFile, regSaveTeamsFile, regSaveScheduleFile, regSaveTeamHistFile, false);
    Toast.makeText(this, "Saved league!", 0).show();
    Intent localIntent = new Intent(this, MainActivity.class);
    localIntent.putExtra("SAVE_FILE", "LOAD," + regSaveLeagueFileStr + "," + regSaveTeamsFileStr + "," + regSaveScheduleFileStr + "," + regSaveTeamHistFileStr);
    startActivity(localIntent);
  }
  
  public void draftPlayerUserTeam(final Player paramPlayer)
  {
    if (showConfirmation)
    {
      Object localObject = new AlertDialog.Builder(this);
      ((AlertDialog.Builder)localObject).setTitle("Confirm Signing").setPositiveButton("Yes", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          OffSeasonActivity.this.draftPlayerUserTeamConfirm(paramPlayer);
        }
      }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.dismiss();
        }
      }).setView(getLayoutInflater().inflate(2130968673, null));
      localObject = ((AlertDialog.Builder)localObject).create();
      ((AlertDialog)localObject).show();
      ((TextView)((AlertDialog)localObject).findViewById(2131558714)).setText("Are you sure you want to draft this player?\n" + paramPlayer.getPosNameYrOvrPot_Compact());
      paramPlayer = (CheckBox)((AlertDialog)localObject).findViewById(2131558549);
      paramPlayer.setText("Show Confirmation Dialogs");
      paramPlayer.setChecked(showConfirmation);
      paramPlayer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
      {
        public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
        {
          OffSeasonActivity.access$1502(OffSeasonActivity.this, paramAnonymousBoolean);
        }
      });
      return;
    }
    draftPlayerUserTeamConfirm(paramPlayer);
  }
  
  public String[] getStrArrayFromList(ArrayList<String> paramArrayList)
  {
    String[] arrayOfString = new String[paramArrayList.size()];
    int i = 0;
    while (i < arrayOfString.length)
    {
      arrayOfString[i] = ((String)paramArrayList.get(i));
      i += 1;
    }
    return arrayOfString;
  }
  
  public String getUserDraftPicksStr()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = userTeam.draftPicks.iterator();
    while (localIterator.hasNext())
    {
      DraftPick localDraftPick = (DraftPick)localIterator.next();
      if (localDraftPick.getYear() == simLeague.getYear() - 1) {
        localStringBuilder.append("\t\t" + localDraftPick.getTeamOriginal().getStrAbbrWL() + "'s Round " + localDraftPick.getRound() + " pick\n");
      }
    }
    return localStringBuilder.toString();
  }
  
  public void onBackPressed() {}
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968605);
    setSupportActionBar((Toolbar)findViewById(2131558542));
    paramBundle = getIntent().getExtras();
    if (paramBundle != null)
    {
      String[] arrayOfString = paramBundle.getString("SAVE_FILE_OFFSEASON").split(",");
      saveLeagueFileStr = arrayOfString[1];
      saveTeamsFileStr = arrayOfString[2];
      saveScheduleFileStr = arrayOfString[3];
      saveTeamHistFileStr = arrayOfString[4];
      saveLeagueFile = new File(getFilesDir(), arrayOfString[1]);
      saveTeamsFile = new File(getFilesDir(), arrayOfString[2]);
      saveScheduleFile = new File(getFilesDir(), arrayOfString[3]);
      saveTeamHistFile = new File(getFilesDir(), arrayOfString[4]);
      paramBundle = paramBundle.getString("SAVE_FILE").split(",");
      regSaveLeagueFileStr = paramBundle[1];
      regSaveTeamsFileStr = paramBundle[2];
      regSaveScheduleFileStr = paramBundle[3];
      regSaveTeamHistFileStr = paramBundle[4];
      regSaveLeagueFile = new File(getFilesDir(), paramBundle[1]);
      regSaveTeamsFile = new File(getFilesDir(), paramBundle[2]);
      regSaveScheduleFile = new File(getFilesDir(), paramBundle[3]);
      regSaveTeamHistFile = new File(getFilesDir(), paramBundle[4]);
      simLeague = new League(saveLeagueFile, saveTeamsFile, saveScheduleFile, saveTeamHistFile, getString(2131165241), getString(2131165240));
      userTeam = simLeague.userTeam;
      season = simLeague.getYear();
    }
    getSupportActionBar().setTitle(userTeam.abbr + " Offseason, " + (simLeague.getYear() - 1));
    draftPlayers = DraftGenerator.generateRookies(simLeague);
    draftRound = 1;
    draftPickNum = 1;
    isDraft = false;
    dayFreeAgency = 1;
    showConfirmation = true;
    draftResultsList = new ArrayList();
    teamFAResultsList = new ArrayList();
    leagueFAResultsList = new ArrayList();
    indexHeaderPosition = new int[8];
    int i = 0;
    while (i < 8)
    {
      indexHeaderPosition[i] = -1;
      i += 1;
    }
    offSeasonText = ((TextView)findViewById(2131558578));
    paramBundle = "Team Free Agency: $" + userTeam.getSalaryCapRoom() + " mil room";
    offSeasonText.setText(paramBundle);
    positionSpinner = ((Spinner)findViewById(2131558580));
    positions = new ArrayList();
    positions.add("All Players");
    positions.add("QB (Needs: " + userTeam.getNeedsPosition("QB") + ")");
    positions.add("RB (Needs: " + userTeam.getNeedsPosition("RB") + ")");
    positions.add("WR (Needs: " + userTeam.getNeedsPosition("WR") + ")");
    positions.add("OL (Needs: " + userTeam.getNeedsPosition("OL") + ")");
    positions.add("K (Needs: " + userTeam.getNeedsPosition("K") + ")");
    positions.add("S (Needs: " + userTeam.getNeedsPosition("S") + ")");
    positions.add("CB (Needs: " + userTeam.getNeedsPosition("CB") + ")");
    positions.add("DL (Needs: " + userTeam.getNeedsPosition("DL") + ")");
    positions.add("LB (Needs: " + userTeam.getNeedsPosition("LB") + ")");
    dataAdapterPosition = new ArrayAdapter(this, 17367048, positions);
    dataAdapterPosition.setDropDownViewResource(17367049);
    positionSpinner.setAdapter(dataAdapterPosition);
    positionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        OffSeasonActivity.access$002(OffSeasonActivity.this, paramAnonymousAdapterView.getItemAtPosition(paramAnonymousInt).toString());
        if (paramAnonymousInt == 0) {
          OffSeasonActivity.this.setPlayerList(currentPosition);
        }
        for (;;)
        {
          OffSeasonActivity.access$202(OffSeasonActivity.this, new OffSeasonActivity.ExpandableListAdapterDraft(OffSeasonActivity.this, OffSeasonActivity.this, currPlayers, currPlayersInfo, true));
          expandList.setAdapter(expListAdapter);
          expListAdapter.notifyDataSetChanged();
          return;
          OffSeasonActivity.this.setPlayerList(currentPosition.substring(0, 2).trim());
        }
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
    resultsButton = ((Button)findViewById(2131558583));
    resultsButton.setText("Team FA Results");
    resultsButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (stage == 2) {
          showDraftSummaryDialog(false);
        }
        do
        {
          return;
          if (stage == 0)
          {
            showFreeAgencyResultsDialog(teamFAResultsList, "Team Free Agency");
            return;
          }
        } while (stage != 1);
        showFreeAgencyResultsDialog(leagueFAResultsList, "League Free Agency");
      }
    });
    doneButton = ((Button)findViewById(2131558584));
    doneButton.setText("Done");
    doneButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (stage == 2)
        {
          paramAnonymousView = new AlertDialog.Builder(OffSeasonActivity.this);
          paramAnonymousView.setMessage("Are you sure you want to finish the draft? The rest of your picks will be selected for you:\n\n" + getUserDraftPicksStr()).setPositiveButton("Yes, simulate the draft", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              OffSeasonActivity.this.beginDraft(true);
            }
          }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
          });
          paramAnonymousView.create().show();
        }
        do
        {
          return;
          if (stage == 0)
          {
            paramAnonymousView = new AlertDialog.Builder(OffSeasonActivity.this);
            paramAnonymousView.setMessage("Are you sure you are done resigning your team free agents?").setPositiveButton("Yes, go to league FA", new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
              {
                simLeague.transferTeamFAs();
                OffSeasonActivity.access$602(OffSeasonActivity.this, 1);
                resultsButton.setText("League FA Results");
                isDraft = false;
                simLeague.sortFAs();
                freeAgencyPlayers = transferListsFA(simLeague.freeAgents);
                positionSpinner.setSelection(0);
                OffSeasonActivity.this.setPlayerList("All Players");
                OffSeasonActivity.this.setPlayerInfoMap();
                OffSeasonActivity.access$202(OffSeasonActivity.this, new OffSeasonActivity.ExpandableListAdapterDraft(OffSeasonActivity.this, OffSeasonActivity.this, currPlayers, currPlayersInfo, true));
                expandList.setAdapter(expListAdapter);
                expListAdapter.notifyDataSetChanged();
                paramAnonymous2DialogInterface = "Day " + dayFreeAgency + ", Free Agency: $" + userTeam.getSalaryCapRoom() + " mil room";
                offSeasonText.setText(paramAnonymous2DialogInterface);
                showIntroLeagueFADialog();
              }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
            });
            paramAnonymousView.create().show();
            return;
          }
        } while (stage != 1);
        paramAnonymousView = new StringBuilder();
        paramAnonymousView.append("Are you sure you are done signing free agents? Once you are done you cannot go back.\n\n");
        paramAnonymousView.append("In the upcoming draft you have the following picks:\n");
        paramAnonymousView.append(getUserDraftPicksStr());
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(OffSeasonActivity.this);
        localBuilder.setMessage(paramAnonymousView.toString()).setPositiveButton("Yes, go to Draft", new DialogInterface.OnClickListener()
        {
          public void onClick(final DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface = ProgressDialog.show(OffSeasonActivity.this, "", "Simulating Free Agency. Please wait...", true);
            paramAnonymous2DialogInterface.setCancelable(false);
            new Thread(new Runnable()
            {
              public void run()
              {
                simLeague.freeAgency();
                runOnUiThread(new Runnable()
                {
                  public void run()
                  {
                    val$dialogLoading.dismiss();
                    OffSeasonActivity.access$602(OffSeasonActivity.this, 2);
                    simLeague.prepareForDraft();
                    positionSpinner.setSelection(0);
                    OffSeasonActivity.this.setPlayerList("All Players");
                    OffSeasonActivity.this.setPlayerInfoMap();
                    OffSeasonActivity.access$202(OffSeasonActivity.this, new OffSeasonActivity.ExpandableListAdapterDraft(OffSeasonActivity.this, OffSeasonActivity.this, currPlayers, currPlayersInfo, true));
                    expandList.setAdapter(expListAdapter);
                    String str = "Draft: Round " + draftRound + ", Pick " + draftPickNum;
                    offSeasonText.setText(str);
                    resultsButton.setText("Draft Results");
                    isDraft = true;
                    OffSeasonActivity.this.beginDraft(false);
                  }
                });
              }
            }).start();
          }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
        });
        localBuilder.create().show();
      }
    });
    ((Button)findViewById(2131558582)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new AlertDialog.Builder(OffSeasonActivity.this);
        paramAnonymousView.setTitle("Current Roster");
        OffSeasonActivity.access$1202(OffSeasonActivity.this, new int[8]);
        ArrayList localArrayList = new ArrayList();
        userTeam.sortPlayers();
        localArrayList.add(null);
        localArrayList.addAll(userTeam.teamQBs);
        indexHeaderPosition[0] = (userTeam.teamQBs.size() + 1);
        localArrayList.add(null);
        localArrayList.addAll(userTeam.teamRBs);
        indexHeaderPosition[1] = (indexHeaderPosition[0] + userTeam.teamRBs.size() + 1);
        localArrayList.add(null);
        localArrayList.addAll(userTeam.teamWRs);
        indexHeaderPosition[2] = (indexHeaderPosition[1] + userTeam.teamWRs.size() + 1);
        localArrayList.add(null);
        localArrayList.addAll(userTeam.teamOLs);
        indexHeaderPosition[3] = (indexHeaderPosition[2] + userTeam.teamOLs.size() + 1);
        localArrayList.add(null);
        localArrayList.addAll(userTeam.teamKs);
        indexHeaderPosition[4] = (indexHeaderPosition[3] + userTeam.teamKs.size() + 1);
        localArrayList.add(null);
        localArrayList.addAll(userTeam.teamSs);
        indexHeaderPosition[5] = (indexHeaderPosition[4] + userTeam.teamSs.size() + 1);
        localArrayList.add(null);
        localArrayList.addAll(userTeam.teamCBs);
        indexHeaderPosition[6] = (indexHeaderPosition[5] + userTeam.teamCBs.size() + 1);
        localArrayList.add(null);
        localArrayList.addAll(userTeam.teamDLs);
        indexHeaderPosition[7] = (indexHeaderPosition[6] + userTeam.teamDLs.size() + 1);
        localArrayList.add(null);
        localArrayList.addAll(userTeam.teamLBs);
        Map localMap = OffSeasonActivity.this.setPlayerInfoMapRoster(localArrayList);
        ExpandableListView localExpandableListView = new ExpandableListView(OffSeasonActivity.this);
        localExpandableListView.setAdapter(new OffSeasonActivity.ExpandableListAdapterDraft(OffSeasonActivity.this, OffSeasonActivity.this, localArrayList, localMap, false));
        paramAnonymousView.setView(localExpandableListView);
        paramAnonymousView.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface.dismiss();
          }
        });
        paramAnonymousView.create().show();
      }
    });
    stage = 0;
    simLeague.signExpiringFAs();
    Collections.sort(userTeam.playersFAs, new PlayerComparator());
    teamFreeAgencyPlayers = transferListsFA(userTeam.playersFAs);
    expandList = ((ExpandableListView)findViewById(2131558581));
    setPlayerList("All Players");
    setPlayerInfoMap();
    expListAdapter = new ExpandableListAdapterDraft(this, currPlayers, currPlayersInfo, false);
    expandList.setAdapter(expListAdapter);
    minimumDur = 50;
    minimumPot = 50;
    minimumFootIQ = 50;
    minimumAge = 20;
    maximumAge = 40;
    removeUnaffordable = false;
    ((Button)findViewById(2131558579)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        showFilterDialog();
      }
    });
  }
  
  public void setMaxAge(int paramInt)
  {
    maximumAge = paramInt;
  }
  
  public void setMinAge(int paramInt)
  {
    minimumAge = paramInt;
  }
  
  public void setMinDurability(int paramInt)
  {
    minimumDur = paramInt;
  }
  
  public void setMinFootIQ(int paramInt)
  {
    minimumFootIQ = paramInt;
  }
  
  public void setMinPotential(int paramInt)
  {
    minimumPot = paramInt;
  }
  
  public void setRemoveUnaffordable(boolean paramBoolean)
  {
    removeUnaffordable = paramBoolean;
  }
  
  public void showDraftSummaryDialog(boolean paramBoolean)
  {
    if (!paramBoolean)
    {
      localObject = new AlertDialog.Builder(this);
      ((AlertDialog.Builder)localObject).setTitle("Draft Summary").setPositiveButton("OK", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
      }).setView(getLayoutInflater().inflate(2130968660, null));
      localObject = ((AlertDialog.Builder)localObject).create();
      ((AlertDialog)localObject).show();
      ((ListView)((AlertDialog)localObject).findViewById(2131558679)).setAdapter(new LeagueHistoryListArrayAdapter(this, getStrArrayFromList(draftResultsList), userTeam.abbr));
      return;
    }
    Object localObject = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject).setTitle("Draft Summary").setPositiveButton("Advance to Season", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        advanceNextSeason();
      }
    }).setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        advanceNextSeason();
      }
    }).setCancelable(false).setView(getLayoutInflater().inflate(2130968660, null));
    localObject = ((AlertDialog.Builder)localObject).create();
    ((AlertDialog)localObject).show();
    ((ListView)((AlertDialog)localObject).findViewById(2131558679)).setAdapter(new LeagueHistoryListArrayAdapter(this, getStrArrayFromList(draftResultsList), userTeam.abbr));
  }
  
  public void showFilterDialog()
  {
    final Object localObject1 = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject1).setTitle("Filter Players").setPositiveButton("Apply", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        OffSeasonActivity.access$002(OffSeasonActivity.this, positionSpinner.getItemAtPosition(positionSpinner.getSelectedItemPosition()).toString());
        if (positionSpinner.getSelectedItemPosition() == 0) {
          OffSeasonActivity.this.setPlayerList(currentPosition);
        }
        for (;;)
        {
          OffSeasonActivity.access$202(OffSeasonActivity.this, new OffSeasonActivity.ExpandableListAdapterDraft(OffSeasonActivity.this, OffSeasonActivity.this, currPlayers, currPlayersInfo, true));
          expandList.setAdapter(expListAdapter);
          expListAdapter.notifyDataSetChanged();
          return;
          OffSeasonActivity.this.setPlayerList(currentPosition.substring(0, 2).trim());
        }
      }
    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    }).setView(getLayoutInflater().inflate(2130968629, null));
    localObject1 = ((AlertDialog.Builder)localObject1).create();
    ((AlertDialog)localObject1).show();
    ((Button)((AlertDialog)localObject1).findViewById(2131558601)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        int i = 0;
        while (i < currPlayers.size())
        {
          expandList.expandGroup(i, false);
          i += 1;
        }
        localObject1.dismiss();
      }
    });
    ((Button)((AlertDialog)localObject1).findViewById(2131558602)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        int i = 0;
        while (i < currPlayers.size())
        {
          expandList.collapseGroup(i);
          i += 1;
        }
        localObject1.dismiss();
      }
    });
    Object localObject5 = new String[5];
    localObject5[0] = "F";
    localObject5[1] = "D";
    localObject5[2] = "C";
    localObject5[3] = "B";
    localObject5[4] = "A";
    Object localObject2 = new String[21];
    int i = 0;
    while (i < localObject2.length)
    {
      localObject2[i] = (i + 20 + "");
      i += 1;
    }
    Spinner localSpinner = (Spinner)((AlertDialog)localObject1).findViewById(2131558603);
    Object localObject3 = new ArrayAdapter(this, 17367048, (Object[])localObject5);
    ((ArrayAdapter)localObject3).setDropDownViewResource(17367049);
    localSpinner.setAdapter((SpinnerAdapter)localObject3);
    localSpinner.setSelection((minimumPot - 50) / 10);
    localObject3 = (Spinner)((AlertDialog)localObject1).findViewById(2131558604);
    Object localObject4 = new ArrayAdapter(this, 17367048, (Object[])localObject5);
    ((ArrayAdapter)localObject4).setDropDownViewResource(17367049);
    ((Spinner)localObject3).setAdapter((SpinnerAdapter)localObject4);
    ((Spinner)localObject3).setSelection((minimumDur - 50) / 10);
    localObject4 = (Spinner)((AlertDialog)localObject1).findViewById(2131558605);
    localObject5 = new ArrayAdapter(this, 17367048, (Object[])localObject5);
    ((ArrayAdapter)localObject5).setDropDownViewResource(17367049);
    ((Spinner)localObject4).setAdapter((SpinnerAdapter)localObject5);
    ((Spinner)localObject4).setSelection((minimumFootIQ - 50) / 10);
    localObject5 = (Spinner)((AlertDialog)localObject1).findViewById(2131558606);
    Object localObject6 = new ArrayAdapter(this, 17367048, (Object[])localObject2);
    ((ArrayAdapter)localObject6).setDropDownViewResource(17367049);
    ((Spinner)localObject5).setAdapter((SpinnerAdapter)localObject6);
    ((Spinner)localObject5).setSelection(minimumAge - 20);
    localObject6 = (Spinner)((AlertDialog)localObject1).findViewById(2131558607);
    localObject2 = new ArrayAdapter(this, 17367048, (Object[])localObject2);
    ((ArrayAdapter)localObject2).setDropDownViewResource(17367049);
    ((Spinner)localObject6).setAdapter((SpinnerAdapter)localObject2);
    ((Spinner)localObject6).setSelection(maximumAge - 20);
    localSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        setMinPotential(paramAnonymousInt * 10 + 50);
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
    ((Spinner)localObject3).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        setMinDurability(paramAnonymousInt * 10 + 50);
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
    ((Spinner)localObject4).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        setMinFootIQ(paramAnonymousInt * 10 + 50);
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
    ((Spinner)localObject5).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        setMinAge(paramAnonymousInt + 20);
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
    ((Spinner)localObject6).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        setMaxAge(paramAnonymousInt + 20);
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
    localObject1 = (CheckBox)((AlertDialog)localObject1).findViewById(2131558608);
    ((CheckBox)localObject1).setChecked(removeUnaffordable);
    ((CheckBox)localObject1).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        setRemoveUnaffordable(paramAnonymousBoolean);
      }
    });
  }
  
  public void showFreeAgencyResultsDialog(ArrayList<String> paramArrayList, String paramString)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(paramString).setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).setView(getLayoutInflater().inflate(2130968660, null));
    paramString = localBuilder.create();
    paramString.show();
    ((ListView)paramString.findViewById(2131558679)).setAdapter(new LeagueHistoryListArrayAdapter(this, getStrArrayFromList(paramArrayList), userTeam.abbr));
  }
  
  public void showIntroLeagueFADialog()
  {
    Object localObject = getResources().getString(2131165250);
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage((CharSequence)localObject).setTitle("League Free Agency").setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    localObject = localBuilder.create();
    ((AlertDialog)localObject).show();
    ((TextView)((AlertDialog)localObject).findViewById(16908299)).setTextSize(2, 14.0F);
  }
  
  public void signFAPlayerUserTeam(final Player paramPlayer)
  {
    if (showConfirmation)
    {
      Object localObject = new AlertDialog.Builder(this);
      ((AlertDialog.Builder)localObject).setTitle("Confirm Signing").setPositiveButton("Yes", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          OffSeasonActivity.this.signFAUserTeamConfirm(paramPlayer);
        }
      }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.dismiss();
        }
      }).setView(getLayoutInflater().inflate(2130968673, null));
      localObject = ((AlertDialog.Builder)localObject).create();
      ((AlertDialog)localObject).show();
      ((TextView)((AlertDialog)localObject).findViewById(2131558714)).setText("Are you sure you want to sign this player?\n" + paramPlayer.getPosNameYrOvrPot_Compact());
      paramPlayer = (CheckBox)((AlertDialog)localObject).findViewById(2131558549);
      paramPlayer.setText("Show Confirmation Dialogs");
      paramPlayer.setChecked(showConfirmation);
      paramPlayer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
      {
        public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
        {
          OffSeasonActivity.access$1502(OffSeasonActivity.this, paramAnonymousBoolean);
        }
      });
      return;
    }
    signFAUserTeamConfirm(paramPlayer);
  }
  
  public void signTeamFAPlayerUserTeam(final Player paramPlayer)
  {
    if (showConfirmation)
    {
      Object localObject = new AlertDialog.Builder(this);
      ((AlertDialog.Builder)localObject).setTitle("Confirm Signing").setPositiveButton("Yes", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          OffSeasonActivity.this.signTeamFAPlayerUserTeamConfirm(paramPlayer);
        }
      }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.dismiss();
        }
      }).setView(getLayoutInflater().inflate(2130968673, null));
      localObject = ((AlertDialog.Builder)localObject).create();
      ((AlertDialog)localObject).show();
      ((TextView)((AlertDialog)localObject).findViewById(2131558714)).setText("Are you sure you want to sign this player?\n" + paramPlayer.getPosNameYrOvrPot_Compact());
      paramPlayer = (CheckBox)((AlertDialog)localObject).findViewById(2131558549);
      paramPlayer.setText("Show Confirmation Dialogs");
      paramPlayer.setChecked(showConfirmation);
      paramPlayer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
      {
        public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
        {
          OffSeasonActivity.access$1502(OffSeasonActivity.this, paramAnonymousBoolean);
        }
      });
      return;
    }
    signTeamFAPlayerUserTeamConfirm(paramPlayer);
  }
  
  public ArrayList<ArrayList<Player>> transferListsFA(ArrayList<Player> paramArrayList)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < 10)
    {
      localArrayList.add(new ArrayList());
      i += 1;
    }
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      Player localPlayer = (Player)paramArrayList.next();
      ((ArrayList)localArrayList.get(0)).add(localPlayer);
      ((ArrayList)localArrayList.get(Player.getPosNumber(localPlayer.getPosition()) + 1)).add(localPlayer);
    }
    return localArrayList;
  }
  
  public void updatePositionNeeds()
  {
    if (dataAdapterPosition != null)
    {
      positions = new ArrayList();
      positions.add("All Players");
      positions.add("QB (Needs: " + userTeam.getNeedsPosition("QB") + ")");
      positions.add("RB (Needs: " + userTeam.getNeedsPosition("RB") + ")");
      positions.add("WR (Needs: " + userTeam.getNeedsPosition("WR") + ")");
      positions.add("OL (Needs: " + userTeam.getNeedsPosition("OL") + ")");
      positions.add("K (Needs: " + userTeam.getNeedsPosition("K") + ")");
      positions.add("S (Needs: " + userTeam.getNeedsPosition("S") + ")");
      positions.add("CB (Needs: " + userTeam.getNeedsPosition("CB") + ")");
      positions.add("DL (Needs: " + userTeam.getNeedsPosition("DL") + ")");
      positions.add("LB (Needs: " + userTeam.getNeedsPosition("LB") + ")");
      dataAdapterPosition.clear();
      Iterator localIterator = positions.iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        dataAdapterPosition.add(str);
      }
      dataAdapterPosition.notifyDataSetChanged();
    }
  }
  
  public class ExpandableListAdapterDraft
    extends BaseExpandableListAdapter
  {
    private Activity context;
    private boolean isDraftOrFA;
    private ArrayList<Player> listCurrPlayers;
    private Map<Player, List<String>> listCurrPlayersInfo;
    
    public ExpandableListAdapterDraft(ArrayList<Player> paramArrayList, Map<Player, List<String>> paramMap, boolean paramBoolean)
    {
      context = paramArrayList;
      listCurrPlayers = paramMap;
      listCurrPlayersInfo = paramBoolean;
      boolean bool;
      isDraftOrFA = bool;
    }
    
    public String getChild(int paramInt1, int paramInt2)
    {
      return (String)((List)listCurrPlayersInfo.get(listCurrPlayers.get(paramInt1))).get(paramInt2);
    }
    
    public long getChildId(int paramInt1, int paramInt2)
    {
      return paramInt2;
    }
    
    public View getChildView(int paramInt1, int paramInt2, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
    {
      Object localObject1 = getChild(paramInt1, paramInt2);
      final Player localPlayer = getGroup(paramInt1);
      Object localObject2 = context.getLayoutInflater();
      paramViewGroup = paramView;
      if (paramView == null) {
        paramViewGroup = ((LayoutInflater)localObject2).inflate(2130968610, null);
      }
      paramView = (TextView)paramViewGroup.findViewById(2131558557);
      localObject2 = (TextView)paramViewGroup.findViewById(2131558559);
      TextView localTextView1 = (TextView)paramViewGroup.findViewById(2131558561);
      TextView localTextView2 = (TextView)paramViewGroup.findViewById(2131558558);
      TextView localTextView3 = (TextView)paramViewGroup.findViewById(2131558560);
      TextView localTextView4 = (TextView)paramViewGroup.findViewById(2131558562);
      TextView localTextView5 = (TextView)paramViewGroup.findViewById(2131558563);
      paramView.setText("Potential: " + Player.getLetterGrade(localPlayer.getRatPot()));
      ((TextView)localObject2).setText("Durability: " + Player.getLetterGrade(localPlayer.getRatDur()));
      localObject1 = ((String)localObject1).split(">");
      localTextView1.setText(localObject1[0]);
      localTextView2.setText(localObject1[1]);
      localTextView3.setText(localObject1[2]);
      localTextView4.setText(localObject1[3]);
      if (localObject1.length > 4) {
        localTextView5.setText(localObject1[4]);
      }
      for (;;)
      {
        localObject1 = new TextView[6];
        localObject1[0] = paramView;
        localObject1[1] = localObject2;
        localObject1[2] = localTextView1;
        localObject1[3] = localTextView2;
        localObject1[4] = localTextView3;
        localObject1[5] = localTextView4;
        paramInt2 = localObject1.length;
        paramInt1 = 0;
        while (paramInt1 < paramInt2)
        {
          paramView = localObject1[paramInt1];
          OffSeasonActivity.this.colorizeRatings(paramView);
          paramInt1 += 1;
        }
        localTextView5.setVisibility(8);
      }
      paramView = (Button)paramViewGroup.findViewById(2131558565);
      if ((stage != 2) || (!isDraftOrFA))
      {
        paramView.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            paramAnonymousView = new AlertDialog.Builder(OffSeasonActivity.this);
            Object localObject = localPlayer.getDetailStatsOffseason();
            ((ArrayList)localObject).add(0, "[B]" + localPlayer.getAgeOvrPot_Str());
            localObject = (String[])((ArrayList)localObject).toArray(new String[((ArrayList)localObject).size()]);
            paramAnonymousView.setAdapter(new PlayerStatsListArrayAdapter(OffSeasonActivity.this, (String[])localObject), null).setTitle(localPlayer.getPosition() + " " + localPlayer.getName()).setPositiveButton("OK", new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
            });
            paramAnonymousView.create().show();
          }
        });
        if (!isDraftOrFA) {
          break label509;
        }
        localObject1 = (Button)paramViewGroup.findViewById(2131558566);
        if (stage != 2) {
          break label499;
        }
      }
      label499:
      for (paramView = Contract.getContractDraft(localPlayer, draftPickNum);; paramView = Contract.getContractFA(localPlayer))
      {
        ((Button)localObject1).setText("Sign for " + paramView.getYearsLeft() + " yrs, $" + paramView.getMoneyPerYear() + "mil/yr");
        ((Button)localObject1).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if ((stage == 2) && (draftRound <= 7))
            {
              draftPlayerUserTeam(localPlayer);
              return;
            }
            if (stage == 0)
            {
              if ((userTeam.getSalaryCapRoom() >= Contract.getContractFA(localPlayer).getMoneyPerYear()) || (Contract.getContractFA(localPlayer).getMoneyPerYear() <= 0.5D))
              {
                signTeamFAPlayerUserTeam(localPlayer);
                return;
              }
              Toast.makeText(OffSeasonActivity.this, "Not enough cap room!", 0).show();
              return;
            }
            if ((userTeam.getSalaryCapRoom() >= Contract.getContractFA(localPlayer).getMoneyPerYear()) || (Contract.getContractFA(localPlayer).getMoneyPerYear() <= 0.5D))
            {
              signFAPlayerUserTeam(localPlayer);
              return;
            }
            Toast.makeText(OffSeasonActivity.this, "Not enough cap room!", 0).show();
          }
        });
        return paramViewGroup;
        paramView.setVisibility(8);
        break;
      }
      label509:
      ((Button)paramViewGroup.findViewById(2131558566)).setVisibility(8);
      return paramViewGroup;
    }
    
    public int getChildrenCount(int paramInt)
    {
      if (listCurrPlayers.get(paramInt) != null) {
        return ((List)listCurrPlayersInfo.get(listCurrPlayers.get(paramInt))).size();
      }
      return 0;
    }
    
    public Player getGroup(int paramInt)
    {
      return (Player)listCurrPlayers.get(paramInt);
    }
    
    public int getGroupCount()
    {
      return listCurrPlayers.size();
    }
    
    public long getGroupId(int paramInt)
    {
      return paramInt;
    }
    
    public View getGroupView(int paramInt, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
    {
      if (listCurrPlayers.size() > 0)
      {
        Object localObject1;
        Object localObject2;
        TextView localTextView;
        if ((isDraftOrFA) && (listCurrPlayers.get(paramInt) != null))
        {
          paramViewGroup = ((Player)listCurrPlayers.get(paramInt)).getPosNameYrOvrPotImprove_Str().split(">");
          localObject1 = paramViewGroup[0];
          localObject2 = paramViewGroup[1];
          paramViewGroup = paramView;
          if (paramView == null) {
            paramViewGroup = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130968634, null);
          }
          localTextView = (TextView)paramViewGroup.findViewById(2131558634);
          localTextView.setTypeface(null, 1);
          localTextView.setText((CharSequence)localObject1);
          localObject1 = (TextView)paramViewGroup.findViewById(2131558636);
          ((TextView)localObject1).setTypeface(null, 1);
          ((TextView)localObject1).setText((CharSequence)localObject2);
          paramView = (TextView)paramViewGroup.findViewById(2131558635);
          paramView.setTypeface(null, 1);
          paramView.setText("");
          paramView = paramViewGroup;
          if (!isDraftOrFA)
          {
            paramView = paramViewGroup;
            if (listCurrPlayers.get(paramInt)).isStarter)
            {
              localTextView.setTextColor(Color.parseColor("#1A75FF"));
              ((TextView)localObject1).setTextColor(Color.parseColor("#1A75FF"));
              paramView = paramViewGroup;
            }
          }
        }
        for (;;)
        {
          return paramView;
          paramViewGroup = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130968634, null);
          if (paramInt == 0)
          {
            paramView = (TextView)paramViewGroup.findViewById(2131558635);
            paramView.setTypeface(null, 1);
            paramView.setText("Quarterback (1 starter)");
            paramView = paramViewGroup;
          }
          else if (paramInt == indexHeaderPosition[0])
          {
            paramView = (TextView)paramViewGroup.findViewById(2131558635);
            paramView.setTypeface(null, 1);
            paramView.setText("Runningback (2 starters)");
            paramView = paramViewGroup;
          }
          else if (paramInt == indexHeaderPosition[1])
          {
            paramView = (TextView)paramViewGroup.findViewById(2131558635);
            paramView.setTypeface(null, 1);
            paramView.setText("Wide Receiver (3 starters)");
            paramView = paramViewGroup;
          }
          else if (paramInt == indexHeaderPosition[2])
          {
            paramView = (TextView)paramViewGroup.findViewById(2131558635);
            paramView.setTypeface(null, 1);
            paramView.setText("Offensive Line (5 starters)");
            paramView = paramViewGroup;
          }
          else if (paramInt == indexHeaderPosition[3])
          {
            paramView = (TextView)paramViewGroup.findViewById(2131558635);
            paramView.setTypeface(null, 1);
            paramView.setText("Kicker (1 starter)");
            paramView = paramViewGroup;
          }
          else if (paramInt == indexHeaderPosition[4])
          {
            paramView = (TextView)paramViewGroup.findViewById(2131558635);
            paramView.setTypeface(null, 1);
            paramView.setText("Safety (1 starter)");
            paramView = paramViewGroup;
          }
          else if (paramInt == indexHeaderPosition[5])
          {
            paramView = (TextView)paramViewGroup.findViewById(2131558635);
            paramView.setTypeface(null, 1);
            paramView.setText("Cornerback (3 starters)");
            paramView = paramViewGroup;
          }
          else if (paramInt == indexHeaderPosition[6])
          {
            paramView = (TextView)paramViewGroup.findViewById(2131558635);
            paramView.setTypeface(null, 1);
            paramView.setText("Defensive Line (4 starters)");
            paramView = paramViewGroup;
          }
          else if (paramInt == indexHeaderPosition[7])
          {
            paramView = (TextView)paramViewGroup.findViewById(2131558635);
            paramView.setTypeface(null, 1);
            paramView.setText("Linebacker (3 starters)");
            paramView = paramViewGroup;
          }
          else
          {
            paramView = paramViewGroup;
            if (listCurrPlayers.get(paramInt) != null)
            {
              localObject1 = ((Player)listCurrPlayers.get(paramInt)).getPosNameYrOvrPotImprove_Str().split(">");
              paramView = localObject1[0];
              localTextView = localObject1[1];
              localObject1 = (TextView)paramViewGroup.findViewById(2131558634);
              ((TextView)localObject1).setTypeface(null, 1);
              ((TextView)localObject1).setText(paramView);
              localObject2 = (TextView)paramViewGroup.findViewById(2131558636);
              ((TextView)localObject2).setTypeface(null, 1);
              ((TextView)localObject2).setText(localTextView);
              paramView = (TextView)paramViewGroup.findViewById(2131558635);
              paramView.setTypeface(null, 1);
              paramView.setText("");
              paramView = paramViewGroup;
              if (listCurrPlayers.get(paramInt)).isStarter)
              {
                ((TextView)localObject1).setTextColor(Color.parseColor("#1A75FF"));
                ((TextView)localObject2).setTextColor(Color.parseColor("#1A75FF"));
                paramView = paramViewGroup;
              }
            }
          }
        }
      }
      return null;
    }
    
    public boolean hasStableIds()
    {
      return true;
    }
    
    public boolean isChildSelectable(int paramInt1, int paramInt2)
    {
      return true;
    }
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.OffSeasonActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */