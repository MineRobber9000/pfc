package PFCpack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class League
{
  public static final String[] donationNames = { "Mark Eeslee", "Lee Sin", "Brent Uttwipe", "Gabriel Kemble", "Jon Stupak", "Kiergan Ren", "Declan Greally", "Parks Wilson" };
  private String allProStrFull;
  ArrayList<Player> allPros;
  public boolean canGoOnline;
  public int currentWeek;
  public ArrayList<Division> divisions;
  public ArrayList<Player> freeAgents;
  public ArrayList<String> hallOfFame;
  private boolean isHardMode;
  public ArrayList<String> lastNameList;
  public ArrayList<String[]> leagueHistory;
  public LeagueRecords leagueRecords;
  public TeamStreak longestActiveWinStreak;
  public TeamStreak longestWinStreak;
  Player mvp;
  ArrayList<Player> mvpCandidates;
  boolean mvpDecided;
  public ArrayList<String> mvpHistory;
  private String mvpWinnerStrFull;
  public ArrayList<String> nameList;
  public ArrayList<ArrayList<String>> newsStories;
  public Game[] playoffGames;
  public ArrayList<Team> playoffsAM;
  public ArrayList<Team> playoffsNA;
  public ArrayList<Team> teamList;
  public ArrayList<Trade> tradeLog;
  public Team userTeam;
  public LeagueRecords userTeamRecords;
  public TeamStreak yearStartLongestWinStreak;
  
  /* Error */
  public League(File paramFile1, File paramFile2, File paramFile3, File paramFile4, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 81	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: iconst_0
    //   6: putfield 83	PFCpack/League:mvpDecided	Z
    //   9: aload_0
    //   10: bipush 11
    //   12: anewarray 85	PFCpack/Game
    //   15: putfield 87	PFCpack/League:playoffGames	[LPFCpack/Game;
    //   18: aload_0
    //   19: iconst_0
    //   20: putfield 89	PFCpack/League:currentWeek	I
    //   23: aload_0
    //   24: new 91	PFCpack/LeagueRecords
    //   27: dup
    //   28: invokespecial 92	PFCpack/LeagueRecords:<init>	()V
    //   31: putfield 94	PFCpack/League:leagueRecords	LPFCpack/LeagueRecords;
    //   34: aload_0
    //   35: new 91	PFCpack/LeagueRecords
    //   38: dup
    //   39: invokespecial 92	PFCpack/LeagueRecords:<init>	()V
    //   42: putfield 96	PFCpack/League:userTeamRecords	LPFCpack/LeagueRecords;
    //   45: aload_0
    //   46: new 98	PFCpack/TeamStreak
    //   49: dup
    //   50: sipush 2016
    //   53: sipush 2016
    //   56: iconst_0
    //   57: ldc 100
    //   59: invokespecial 103	PFCpack/TeamStreak:<init>	(IIILjava/lang/String;)V
    //   62: putfield 105	PFCpack/League:longestWinStreak	LPFCpack/TeamStreak;
    //   65: aload_0
    //   66: new 98	PFCpack/TeamStreak
    //   69: dup
    //   70: sipush 2016
    //   73: sipush 2016
    //   76: iconst_0
    //   77: ldc 100
    //   79: invokespecial 103	PFCpack/TeamStreak:<init>	(IIILjava/lang/String;)V
    //   82: putfield 107	PFCpack/League:yearStartLongestWinStreak	LPFCpack/TeamStreak;
    //   85: aload_0
    //   86: new 98	PFCpack/TeamStreak
    //   89: dup
    //   90: sipush 2016
    //   93: sipush 2016
    //   96: iconst_0
    //   97: ldc 100
    //   99: invokespecial 103	PFCpack/TeamStreak:<init>	(IIILjava/lang/String;)V
    //   102: putfield 109	PFCpack/League:longestActiveWinStreak	LPFCpack/TeamStreak;
    //   105: aload_0
    //   106: new 111	java/util/ArrayList
    //   109: dup
    //   110: invokespecial 112	java/util/ArrayList:<init>	()V
    //   113: putfield 114	PFCpack/League:divisions	Ljava/util/ArrayList;
    //   116: aload_0
    //   117: new 111	java/util/ArrayList
    //   120: dup
    //   121: invokespecial 112	java/util/ArrayList:<init>	()V
    //   124: putfield 116	PFCpack/League:teamList	Ljava/util/ArrayList;
    //   127: aload_0
    //   128: getfield 114	PFCpack/League:divisions	Ljava/util/ArrayList;
    //   131: new 118	PFCpack/Division
    //   134: dup
    //   135: ldc 120
    //   137: aload_0
    //   138: invokespecial 123	PFCpack/Division:<init>	(Ljava/lang/String;LPFCpack/League;)V
    //   141: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   144: pop
    //   145: aload_0
    //   146: getfield 114	PFCpack/League:divisions	Ljava/util/ArrayList;
    //   149: new 118	PFCpack/Division
    //   152: dup
    //   153: ldc -127
    //   155: aload_0
    //   156: invokespecial 123	PFCpack/Division:<init>	(Ljava/lang/String;LPFCpack/League;)V
    //   159: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   162: pop
    //   163: aload_0
    //   164: getfield 114	PFCpack/League:divisions	Ljava/util/ArrayList;
    //   167: new 118	PFCpack/Division
    //   170: dup
    //   171: ldc -125
    //   173: aload_0
    //   174: invokespecial 123	PFCpack/Division:<init>	(Ljava/lang/String;LPFCpack/League;)V
    //   177: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   180: pop
    //   181: aload_0
    //   182: getfield 114	PFCpack/League:divisions	Ljava/util/ArrayList;
    //   185: new 118	PFCpack/Division
    //   188: dup
    //   189: ldc -123
    //   191: aload_0
    //   192: invokespecial 123	PFCpack/Division:<init>	(Ljava/lang/String;LPFCpack/League;)V
    //   195: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   198: pop
    //   199: aload_0
    //   200: getfield 114	PFCpack/League:divisions	Ljava/util/ArrayList;
    //   203: new 118	PFCpack/Division
    //   206: dup
    //   207: ldc -121
    //   209: aload_0
    //   210: invokespecial 123	PFCpack/Division:<init>	(Ljava/lang/String;LPFCpack/League;)V
    //   213: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   216: pop
    //   217: aload_0
    //   218: getfield 114	PFCpack/League:divisions	Ljava/util/ArrayList;
    //   221: new 118	PFCpack/Division
    //   224: dup
    //   225: ldc -119
    //   227: aload_0
    //   228: invokespecial 123	PFCpack/Division:<init>	(Ljava/lang/String;LPFCpack/League;)V
    //   231: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   234: pop
    //   235: aload_0
    //   236: getfield 114	PFCpack/League:divisions	Ljava/util/ArrayList;
    //   239: new 118	PFCpack/Division
    //   242: dup
    //   243: ldc -117
    //   245: aload_0
    //   246: invokespecial 123	PFCpack/Division:<init>	(Ljava/lang/String;LPFCpack/League;)V
    //   249: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   252: pop
    //   253: aload_0
    //   254: getfield 114	PFCpack/League:divisions	Ljava/util/ArrayList;
    //   257: new 118	PFCpack/Division
    //   260: dup
    //   261: ldc -115
    //   263: aload_0
    //   264: invokespecial 123	PFCpack/Division:<init>	(Ljava/lang/String;LPFCpack/League;)V
    //   267: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   270: pop
    //   271: aload_0
    //   272: new 111	java/util/ArrayList
    //   275: dup
    //   276: invokespecial 112	java/util/ArrayList:<init>	()V
    //   279: putfield 143	PFCpack/League:allPros	Ljava/util/ArrayList;
    //   282: aload_0
    //   283: new 111	java/util/ArrayList
    //   286: dup
    //   287: invokespecial 112	java/util/ArrayList:<init>	()V
    //   290: putfield 145	PFCpack/League:playoffsAM	Ljava/util/ArrayList;
    //   293: aload_0
    //   294: new 111	java/util/ArrayList
    //   297: dup
    //   298: invokespecial 112	java/util/ArrayList:<init>	()V
    //   301: putfield 147	PFCpack/League:playoffsNA	Ljava/util/ArrayList;
    //   304: aload_0
    //   305: new 111	java/util/ArrayList
    //   308: dup
    //   309: invokespecial 112	java/util/ArrayList:<init>	()V
    //   312: putfield 149	PFCpack/League:freeAgents	Ljava/util/ArrayList;
    //   315: aload_0
    //   316: new 111	java/util/ArrayList
    //   319: dup
    //   320: invokespecial 112	java/util/ArrayList:<init>	()V
    //   323: putfield 151	PFCpack/League:tradeLog	Ljava/util/ArrayList;
    //   326: new 153	java/io/BufferedReader
    //   329: dup
    //   330: new 155	java/io/FileReader
    //   333: dup
    //   334: aload_2
    //   335: invokespecial 158	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   338: invokespecial 161	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   341: astore_2
    //   342: iconst_0
    //   343: istore 7
    //   345: iload 7
    //   347: bipush 32
    //   349: if_icmpge +476 -> 825
    //   352: new 163	java/lang/StringBuilder
    //   355: dup
    //   356: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   359: astore 11
    //   361: aload_2
    //   362: invokevirtual 168	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   365: astore 12
    //   367: aload 12
    //   369: ifnull +370 -> 739
    //   372: aload 12
    //   374: ldc -86
    //   376: invokevirtual 173	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   379: ifne +360 -> 739
    //   382: aload 11
    //   384: aload 12
    //   386: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   389: pop
    //   390: goto -29 -> 361
    //   393: astore_2
    //   394: getstatic 183	java/lang/System:out	Ljava/io/PrintStream;
    //   397: ldc -71
    //   399: invokevirtual 191	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   402: new 153	java/io/BufferedReader
    //   405: dup
    //   406: new 155	java/io/FileReader
    //   409: dup
    //   410: aload_1
    //   411: invokespecial 158	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   414: invokespecial 161	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   417: astore 11
    //   419: aload 11
    //   421: invokevirtual 168	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   424: astore_1
    //   425: aload_0
    //   426: aload_1
    //   427: ldc -63
    //   429: invokevirtual 197	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   432: iconst_2
    //   433: aaload
    //   434: invokestatic 203	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   437: putfield 89	PFCpack/League:currentWeek	I
    //   440: aload_0
    //   441: aload_0
    //   442: aload_1
    //   443: ldc -63
    //   445: invokevirtual 197	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   448: iconst_4
    //   449: aaload
    //   450: invokevirtual 207	PFCpack/League:findTeamAbbr	(Ljava/lang/String;)LPFCpack/Team;
    //   453: putfield 209	PFCpack/League:userTeam	LPFCpack/Team;
    //   456: aload_0
    //   457: getfield 209	PFCpack/League:userTeam	LPFCpack/Team;
    //   460: iconst_1
    //   461: putfield 214	PFCpack/Team:userControlled	Z
    //   464: aload_0
    //   465: new 111	java/util/ArrayList
    //   468: dup
    //   469: invokespecial 112	java/util/ArrayList:<init>	()V
    //   472: putfield 216	PFCpack/League:leagueHistory	Ljava/util/ArrayList;
    //   475: aload 11
    //   477: invokevirtual 168	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   480: astore_1
    //   481: aload_1
    //   482: ifnull +451 -> 933
    //   485: aload_1
    //   486: ldc -38
    //   488: invokevirtual 173	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   491: ifne +442 -> 933
    //   494: aload_0
    //   495: getfield 216	PFCpack/League:leagueHistory	Ljava/util/ArrayList;
    //   498: aload_1
    //   499: ldc -36
    //   501: invokevirtual 197	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   504: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   507: pop
    //   508: goto -33 -> 475
    //   511: astore_1
    //   512: getstatic 183	java/lang/System:out	Ljava/io/PrintStream;
    //   515: ldc -71
    //   517: invokevirtual 191	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   520: new 153	java/io/BufferedReader
    //   523: dup
    //   524: new 155	java/io/FileReader
    //   527: dup
    //   528: aload 4
    //   530: invokespecial 158	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   533: invokespecial 161	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   536: astore_1
    //   537: iconst_0
    //   538: istore 7
    //   540: iload 7
    //   542: bipush 32
    //   544: if_icmpge +1498 -> 2042
    //   547: aload_0
    //   548: aload_1
    //   549: invokevirtual 168	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   552: invokevirtual 207	PFCpack/League:findTeamAbbr	(Ljava/lang/String;)LPFCpack/Team;
    //   555: astore_2
    //   556: aload_1
    //   557: invokevirtual 168	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   560: astore 4
    //   562: aload 4
    //   564: ifnull +1469 -> 2033
    //   567: aload 4
    //   569: ldc -34
    //   571: invokevirtual 173	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   574: ifne +1459 -> 2033
    //   577: aload_2
    //   578: getfield 225	PFCpack/Team:teamHistory	Ljava/util/ArrayList;
    //   581: aload 4
    //   583: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   586: pop
    //   587: goto -31 -> 556
    //   590: astore_1
    //   591: getstatic 183	java/lang/System:out	Ljava/io/PrintStream;
    //   594: ldc -71
    //   596: invokevirtual 191	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   599: new 153	java/io/BufferedReader
    //   602: dup
    //   603: new 155	java/io/FileReader
    //   606: dup
    //   607: aload_3
    //   608: invokespecial 158	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   611: invokespecial 161	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   614: astore_1
    //   615: aload_1
    //   616: invokevirtual 168	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   619: astore_2
    //   620: aload_2
    //   621: ifnull +1540 -> 2161
    //   624: aload_2
    //   625: ldc -29
    //   627: invokevirtual 173	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   630: ifne +1531 -> 2161
    //   633: aload_2
    //   634: ldc -27
    //   636: invokevirtual 197	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   639: astore_2
    //   640: aload_0
    //   641: aload_2
    //   642: iconst_0
    //   643: aaload
    //   644: invokevirtual 207	PFCpack/League:findTeamAbbr	(Ljava/lang/String;)LPFCpack/Team;
    //   647: astore_3
    //   648: iconst_1
    //   649: istore 7
    //   651: iload 7
    //   653: aload_2
    //   654: arraylength
    //   655: if_icmpge -40 -> 615
    //   658: new 85	PFCpack/Game
    //   661: dup
    //   662: aload_0
    //   663: aload_2
    //   664: iload 7
    //   666: aaload
    //   667: iload 7
    //   669: iconst_1
    //   670: isub
    //   671: invokespecial 232	PFCpack/Game:<init>	(LPFCpack/League;Ljava/lang/String;I)V
    //   674: astore 4
    //   676: aload 4
    //   678: getfield 235	PFCpack/Game:homeTeam	LPFCpack/Team;
    //   681: aload_3
    //   682: if_acmpne +16 -> 698
    //   685: aload 4
    //   687: getfield 238	PFCpack/Game:homeScore	I
    //   690: aload 4
    //   692: getfield 241	PFCpack/Game:awayScore	I
    //   695: if_icmpgt +25 -> 720
    //   698: aload 4
    //   700: getfield 244	PFCpack/Game:awayTeam	LPFCpack/Team;
    //   703: aload_3
    //   704: if_acmpne +1358 -> 2062
    //   707: aload 4
    //   709: getfield 238	PFCpack/Game:homeScore	I
    //   712: aload 4
    //   714: getfield 241	PFCpack/Game:awayScore	I
    //   717: if_icmpge +1345 -> 2062
    //   720: aload_3
    //   721: getfield 247	PFCpack/Team:gameWLSchedule	Ljava/util/ArrayList;
    //   724: ldc -7
    //   726: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   729: pop
    //   730: iload 7
    //   732: iconst_1
    //   733: iadd
    //   734: istore 7
    //   736: goto -85 -> 651
    //   739: new 211	PFCpack/Team
    //   742: dup
    //   743: aload 11
    //   745: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   748: aload_0
    //   749: invokespecial 253	PFCpack/Team:<init>	(Ljava/lang/String;LPFCpack/League;)V
    //   752: astore 11
    //   754: aload_0
    //   755: getfield 114	PFCpack/League:divisions	Ljava/util/ArrayList;
    //   758: aload_0
    //   759: aload 11
    //   761: getfield 256	PFCpack/Team:division	Ljava/lang/String;
    //   764: invokevirtual 259	PFCpack/League:getDivNumber	(Ljava/lang/String;)I
    //   767: invokevirtual 263	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   770: checkcast 118	PFCpack/Division
    //   773: getfield 266	PFCpack/Division:divTeams	Ljava/util/ArrayList;
    //   776: aload 11
    //   778: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   781: pop
    //   782: aload_0
    //   783: getfield 116	PFCpack/League:teamList	Ljava/util/ArrayList;
    //   786: aload 11
    //   788: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   791: pop
    //   792: aload 11
    //   794: new 111	java/util/ArrayList
    //   797: dup
    //   798: invokespecial 112	java/util/ArrayList:<init>	()V
    //   801: putfield 269	PFCpack/Team:draftPicks	Ljava/util/ArrayList;
    //   804: aload 11
    //   806: new 111	java/util/ArrayList
    //   809: dup
    //   810: invokespecial 112	java/util/ArrayList:<init>	()V
    //   813: putfield 272	PFCpack/Team:positionalDraftPicks	Ljava/util/ArrayList;
    //   816: iload 7
    //   818: iconst_1
    //   819: iadd
    //   820: istore 7
    //   822: goto -477 -> 345
    //   825: aload_2
    //   826: invokevirtual 168	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   829: pop
    //   830: aload_2
    //   831: invokevirtual 168	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   834: astore 11
    //   836: aload 11
    //   838: ifnull +75 -> 913
    //   841: aload 11
    //   843: ldc_w 274
    //   846: invokevirtual 173	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   849: ifne +64 -> 913
    //   852: aload 11
    //   854: ldc -27
    //   856: invokevirtual 197	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   859: astore 11
    //   861: aload_0
    //   862: aload 11
    //   864: iconst_0
    //   865: aaload
    //   866: invokevirtual 207	PFCpack/League:findTeamAbbr	(Ljava/lang/String;)LPFCpack/Team;
    //   869: astore 12
    //   871: iconst_1
    //   872: istore 7
    //   874: iload 7
    //   876: aload 11
    //   878: arraylength
    //   879: if_icmpge -49 -> 830
    //   882: aload 12
    //   884: getfield 269	PFCpack/Team:draftPicks	Ljava/util/ArrayList;
    //   887: new 276	PFCpack/DraftPick
    //   890: dup
    //   891: aload_0
    //   892: aload 11
    //   894: iload 7
    //   896: aaload
    //   897: invokespecial 279	PFCpack/DraftPick:<init>	(LPFCpack/League;Ljava/lang/String;)V
    //   900: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   903: pop
    //   904: iload 7
    //   906: iconst_1
    //   907: iadd
    //   908: istore 7
    //   910: goto -36 -> 874
    //   913: aload_2
    //   914: invokevirtual 282	java/io/BufferedReader:close	()V
    //   917: goto -515 -> 402
    //   920: astore_2
    //   921: getstatic 183	java/lang/System:out	Ljava/io/PrintStream;
    //   924: ldc_w 284
    //   927: invokevirtual 191	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   930: goto -528 -> 402
    //   933: aload_0
    //   934: new 111	java/util/ArrayList
    //   937: dup
    //   938: invokespecial 112	java/util/ArrayList:<init>	()V
    //   941: putfield 286	PFCpack/League:mvpHistory	Ljava/util/ArrayList;
    //   944: aload 11
    //   946: invokevirtual 168	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   949: astore_1
    //   950: aload_1
    //   951: ifnull +38 -> 989
    //   954: aload_1
    //   955: ldc_w 288
    //   958: invokevirtual 173	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   961: ifne +28 -> 989
    //   964: aload_0
    //   965: getfield 286	PFCpack/League:mvpHistory	Ljava/util/ArrayList;
    //   968: aload_1
    //   969: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   972: pop
    //   973: goto -29 -> 944
    //   976: astore_1
    //   977: getstatic 183	java/lang/System:out	Ljava/io/PrintStream;
    //   980: ldc_w 284
    //   983: invokevirtual 191	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   986: goto -466 -> 520
    //   989: aload 11
    //   991: invokevirtual 168	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   994: astore_1
    //   995: aload_1
    //   996: ifnull +61 -> 1057
    //   999: aload_1
    //   1000: ldc_w 290
    //   1003: invokevirtual 173	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1006: ifne +51 -> 1057
    //   1009: aload_1
    //   1010: ldc_w 292
    //   1013: invokevirtual 197	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   1016: astore_1
    //   1017: aload_1
    //   1018: iconst_1
    //   1019: aaload
    //   1020: ldc_w 294
    //   1023: invokevirtual 173	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1026: ifne -37 -> 989
    //   1029: aload_0
    //   1030: getfield 94	PFCpack/League:leagueRecords	LPFCpack/LeagueRecords;
    //   1033: aload_1
    //   1034: iconst_0
    //   1035: aaload
    //   1036: aload_1
    //   1037: iconst_1
    //   1038: aaload
    //   1039: invokestatic 203	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1042: aload_1
    //   1043: iconst_2
    //   1044: aaload
    //   1045: aload_1
    //   1046: iconst_3
    //   1047: aaload
    //   1048: invokestatic 203	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1051: invokevirtual 298	PFCpack/LeagueRecords:checkRecord	(Ljava/lang/String;ILjava/lang/String;I)V
    //   1054: goto -65 -> 989
    //   1057: aload 11
    //   1059: invokevirtual 168	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   1062: astore_1
    //   1063: aload_1
    //   1064: ifnull +88 -> 1152
    //   1067: aload_1
    //   1068: ldc_w 300
    //   1071: invokevirtual 173	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1074: ifne +78 -> 1152
    //   1077: aload_1
    //   1078: ldc_w 292
    //   1081: invokevirtual 197	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   1084: astore_1
    //   1085: aload_0
    //   1086: new 98	PFCpack/TeamStreak
    //   1089: dup
    //   1090: aload_1
    //   1091: iconst_2
    //   1092: aaload
    //   1093: invokestatic 203	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1096: aload_1
    //   1097: iconst_3
    //   1098: aaload
    //   1099: invokestatic 203	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1102: aload_1
    //   1103: iconst_0
    //   1104: aaload
    //   1105: invokestatic 203	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1108: aload_1
    //   1109: iconst_1
    //   1110: aaload
    //   1111: invokespecial 103	PFCpack/TeamStreak:<init>	(IIILjava/lang/String;)V
    //   1114: putfield 105	PFCpack/League:longestWinStreak	LPFCpack/TeamStreak;
    //   1117: aload_0
    //   1118: new 98	PFCpack/TeamStreak
    //   1121: dup
    //   1122: aload_1
    //   1123: iconst_2
    //   1124: aaload
    //   1125: invokestatic 203	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1128: aload_1
    //   1129: iconst_3
    //   1130: aaload
    //   1131: invokestatic 203	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1134: aload_1
    //   1135: iconst_0
    //   1136: aaload
    //   1137: invokestatic 203	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1140: aload_1
    //   1141: iconst_1
    //   1142: aaload
    //   1143: invokespecial 103	PFCpack/TeamStreak:<init>	(IIILjava/lang/String;)V
    //   1146: putfield 107	PFCpack/League:yearStartLongestWinStreak	LPFCpack/TeamStreak;
    //   1149: goto -92 -> 1057
    //   1152: aload 11
    //   1154: invokevirtual 168	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   1157: astore_1
    //   1158: aload_1
    //   1159: ifnull +61 -> 1220
    //   1162: aload_1
    //   1163: ldc_w 302
    //   1166: invokevirtual 173	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1169: ifne +51 -> 1220
    //   1172: aload_1
    //   1173: ldc_w 292
    //   1176: invokevirtual 197	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   1179: astore_1
    //   1180: aload_1
    //   1181: iconst_1
    //   1182: aaload
    //   1183: ldc_w 294
    //   1186: invokevirtual 173	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1189: ifne -37 -> 1152
    //   1192: aload_0
    //   1193: getfield 96	PFCpack/League:userTeamRecords	LPFCpack/LeagueRecords;
    //   1196: aload_1
    //   1197: iconst_0
    //   1198: aaload
    //   1199: aload_1
    //   1200: iconst_1
    //   1201: aaload
    //   1202: invokestatic 203	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1205: aload_1
    //   1206: iconst_2
    //   1207: aaload
    //   1208: aload_1
    //   1209: iconst_3
    //   1210: aaload
    //   1211: invokestatic 203	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1214: invokevirtual 298	PFCpack/LeagueRecords:checkRecord	(Ljava/lang/String;ILjava/lang/String;I)V
    //   1217: goto -65 -> 1152
    //   1220: aload_0
    //   1221: new 111	java/util/ArrayList
    //   1224: dup
    //   1225: invokespecial 112	java/util/ArrayList:<init>	()V
    //   1228: putfield 304	PFCpack/League:hallOfFame	Ljava/util/ArrayList;
    //   1231: aload 11
    //   1233: invokevirtual 168	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   1236: astore_1
    //   1237: aload_1
    //   1238: ifnull +25 -> 1263
    //   1241: aload_1
    //   1242: ldc_w 306
    //   1245: invokevirtual 173	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1248: ifne +15 -> 1263
    //   1251: aload_0
    //   1252: getfield 304	PFCpack/League:hallOfFame	Ljava/util/ArrayList;
    //   1255: aload_1
    //   1256: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1259: pop
    //   1260: goto -29 -> 1231
    //   1263: new 163	java/lang/StringBuilder
    //   1266: dup
    //   1267: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   1270: astore_1
    //   1271: aload 11
    //   1273: invokevirtual 168	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   1276: astore_2
    //   1277: aload_2
    //   1278: ifnull +41 -> 1319
    //   1281: aload_2
    //   1282: ldc_w 308
    //   1285: invokevirtual 173	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1288: ifne +31 -> 1319
    //   1291: aload_1
    //   1292: new 163	java/lang/StringBuilder
    //   1295: dup
    //   1296: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   1299: aload_2
    //   1300: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1303: ldc_w 310
    //   1306: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1309: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1312: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1315: pop
    //   1316: goto -45 -> 1271
    //   1319: aload_0
    //   1320: aload_1
    //   1321: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1324: putfield 312	PFCpack/League:allProStrFull	Ljava/lang/String;
    //   1327: new 163	java/lang/StringBuilder
    //   1330: dup
    //   1331: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   1334: astore_1
    //   1335: aload 11
    //   1337: invokevirtual 168	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   1340: astore_2
    //   1341: aload_2
    //   1342: ifnull +41 -> 1383
    //   1345: aload_2
    //   1346: ldc_w 314
    //   1349: invokevirtual 173	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1352: ifne +31 -> 1383
    //   1355: aload_1
    //   1356: new 163	java/lang/StringBuilder
    //   1359: dup
    //   1360: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   1363: aload_2
    //   1364: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1367: ldc_w 310
    //   1370: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1373: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1376: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1379: pop
    //   1380: goto -45 -> 1335
    //   1383: aload_0
    //   1384: aload_1
    //   1385: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1388: putfield 316	PFCpack/League:mvpWinnerStrFull	Ljava/lang/String;
    //   1391: aload_0
    //   1392: getfield 316	PFCpack/League:mvpWinnerStrFull	Ljava/lang/String;
    //   1395: ldc_w 318
    //   1398: invokevirtual 173	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1401: ifne +1291 -> 2692
    //   1404: aload_0
    //   1405: iconst_1
    //   1406: putfield 83	PFCpack/League:mvpDecided	Z
    //   1409: goto +1283 -> 2692
    //   1412: aload 11
    //   1414: invokevirtual 168	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   1417: astore_1
    //   1418: aload_1
    //   1419: ifnull +102 -> 1521
    //   1422: aload_1
    //   1423: ldc_w 320
    //   1426: invokevirtual 173	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1429: ifne +92 -> 1521
    //   1432: iload 7
    //   1434: iconst_1
    //   1435: iadd
    //   1436: istore 8
    //   1438: iload 7
    //   1440: bipush 6
    //   1442: if_icmpge +23 -> 1465
    //   1445: aload_0
    //   1446: getfield 145	PFCpack/League:playoffsAM	Ljava/util/ArrayList;
    //   1449: aload_0
    //   1450: aload_1
    //   1451: invokevirtual 207	PFCpack/League:findTeamAbbr	(Ljava/lang/String;)LPFCpack/Team;
    //   1454: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1457: pop
    //   1458: iload 8
    //   1460: istore 7
    //   1462: goto -50 -> 1412
    //   1465: aload_0
    //   1466: getfield 147	PFCpack/League:playoffsNA	Ljava/util/ArrayList;
    //   1469: aload_0
    //   1470: aload_1
    //   1471: invokevirtual 207	PFCpack/League:findTeamAbbr	(Ljava/lang/String;)LPFCpack/Team;
    //   1474: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1477: pop
    //   1478: iload 8
    //   1480: istore 7
    //   1482: goto -70 -> 1412
    //   1485: aload 12
    //   1487: iconst_0
    //   1488: aaload
    //   1489: ldc_w 322
    //   1492: invokevirtual 173	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1495: ifeq +178 -> 1673
    //   1498: aload_0
    //   1499: getfield 149	PFCpack/League:freeAgents	Ljava/util/ArrayList;
    //   1502: new 324	PFCpack/PlayerQB
    //   1505: dup
    //   1506: aload 13
    //   1508: aconst_null
    //   1509: aload 14
    //   1511: aload 15
    //   1513: aload_1
    //   1514: invokespecial 327	PFCpack/PlayerQB:<init>	(Ljava/lang/String;LPFCpack/Team;[ILjava/lang/String;[I)V
    //   1517: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1520: pop
    //   1521: aload 11
    //   1523: invokevirtual 168	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   1526: astore_1
    //   1527: aload_1
    //   1528: ifnull +452 -> 1980
    //   1531: aload_1
    //   1532: ldc_w 329
    //   1535: invokevirtual 173	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1538: ifne +442 -> 1980
    //   1541: aload_1
    //   1542: ldc -27
    //   1544: invokevirtual 197	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   1547: astore_2
    //   1548: aload_2
    //   1549: iconst_0
    //   1550: aaload
    //   1551: ldc_w 292
    //   1554: invokevirtual 197	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   1557: astore 12
    //   1559: aload 12
    //   1561: iconst_1
    //   1562: aaload
    //   1563: astore 13
    //   1565: aload 12
    //   1567: arraylength
    //   1568: iconst_2
    //   1569: isub
    //   1570: newarray <illegal type>
    //   1572: astore 14
    //   1574: iconst_0
    //   1575: istore 7
    //   1577: iload 7
    //   1579: aload 14
    //   1581: arraylength
    //   1582: if_icmpge +27 -> 1609
    //   1585: aload 14
    //   1587: iload 7
    //   1589: aload 12
    //   1591: iload 7
    //   1593: iconst_2
    //   1594: iadd
    //   1595: aaload
    //   1596: invokestatic 203	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1599: iastore
    //   1600: iload 7
    //   1602: iconst_1
    //   1603: iadd
    //   1604: istore 7
    //   1606: goto -29 -> 1577
    //   1609: aload_2
    //   1610: iconst_1
    //   1611: aaload
    //   1612: astore 15
    //   1614: aconst_null
    //   1615: astore_1
    //   1616: aload_2
    //   1617: arraylength
    //   1618: iconst_3
    //   1619: if_icmpne -134 -> 1485
    //   1622: aload_2
    //   1623: iconst_2
    //   1624: aaload
    //   1625: ldc_w 292
    //   1628: invokevirtual 197	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   1631: astore 16
    //   1633: aload 16
    //   1635: arraylength
    //   1636: newarray <illegal type>
    //   1638: astore_2
    //   1639: iconst_0
    //   1640: istore 7
    //   1642: aload_2
    //   1643: astore_1
    //   1644: iload 7
    //   1646: aload 16
    //   1648: arraylength
    //   1649: if_icmpge -164 -> 1485
    //   1652: aload_2
    //   1653: iload 7
    //   1655: aload 16
    //   1657: iload 7
    //   1659: aaload
    //   1660: invokestatic 203	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1663: iastore
    //   1664: iload 7
    //   1666: iconst_1
    //   1667: iadd
    //   1668: istore 7
    //   1670: goto -28 -> 1642
    //   1673: aload 12
    //   1675: iconst_0
    //   1676: aaload
    //   1677: ldc_w 331
    //   1680: invokevirtual 173	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1683: ifeq +29 -> 1712
    //   1686: aload_0
    //   1687: getfield 149	PFCpack/League:freeAgents	Ljava/util/ArrayList;
    //   1690: new 333	PFCpack/PlayerRB
    //   1693: dup
    //   1694: aload 13
    //   1696: aconst_null
    //   1697: aload 14
    //   1699: aload 15
    //   1701: aload_1
    //   1702: invokespecial 334	PFCpack/PlayerRB:<init>	(Ljava/lang/String;LPFCpack/Team;[ILjava/lang/String;[I)V
    //   1705: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1708: pop
    //   1709: goto -188 -> 1521
    //   1712: aload 12
    //   1714: iconst_0
    //   1715: aaload
    //   1716: ldc_w 336
    //   1719: invokevirtual 173	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1722: ifeq +29 -> 1751
    //   1725: aload_0
    //   1726: getfield 149	PFCpack/League:freeAgents	Ljava/util/ArrayList;
    //   1729: new 338	PFCpack/PlayerWR
    //   1732: dup
    //   1733: aload 13
    //   1735: aconst_null
    //   1736: aload 14
    //   1738: aload 15
    //   1740: aload_1
    //   1741: invokespecial 339	PFCpack/PlayerWR:<init>	(Ljava/lang/String;LPFCpack/Team;[ILjava/lang/String;[I)V
    //   1744: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1747: pop
    //   1748: goto -227 -> 1521
    //   1751: aload 12
    //   1753: iconst_0
    //   1754: aaload
    //   1755: ldc_w 341
    //   1758: invokevirtual 173	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1761: ifeq +28 -> 1789
    //   1764: aload_0
    //   1765: getfield 149	PFCpack/League:freeAgents	Ljava/util/ArrayList;
    //   1768: new 343	PFCpack/PlayerOL
    //   1771: dup
    //   1772: aload 13
    //   1774: aconst_null
    //   1775: aload 14
    //   1777: aload 15
    //   1779: invokespecial 346	PFCpack/PlayerOL:<init>	(Ljava/lang/String;LPFCpack/Team;[ILjava/lang/String;)V
    //   1782: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1785: pop
    //   1786: goto -265 -> 1521
    //   1789: aload 12
    //   1791: iconst_0
    //   1792: aaload
    //   1793: ldc_w 348
    //   1796: invokevirtual 173	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1799: ifeq +29 -> 1828
    //   1802: aload_0
    //   1803: getfield 149	PFCpack/League:freeAgents	Ljava/util/ArrayList;
    //   1806: new 350	PFCpack/PlayerK
    //   1809: dup
    //   1810: aload 13
    //   1812: aconst_null
    //   1813: aload 14
    //   1815: aload 15
    //   1817: aload_1
    //   1818: invokespecial 351	PFCpack/PlayerK:<init>	(Ljava/lang/String;LPFCpack/Team;[ILjava/lang/String;[I)V
    //   1821: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1824: pop
    //   1825: goto -304 -> 1521
    //   1828: aload 12
    //   1830: iconst_0
    //   1831: aaload
    //   1832: ldc_w 353
    //   1835: invokevirtual 173	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1838: ifeq +28 -> 1866
    //   1841: aload_0
    //   1842: getfield 149	PFCpack/League:freeAgents	Ljava/util/ArrayList;
    //   1845: new 355	PFCpack/PlayerS
    //   1848: dup
    //   1849: aload 13
    //   1851: aconst_null
    //   1852: aload 14
    //   1854: aload 15
    //   1856: invokespecial 356	PFCpack/PlayerS:<init>	(Ljava/lang/String;LPFCpack/Team;[ILjava/lang/String;)V
    //   1859: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1862: pop
    //   1863: goto -342 -> 1521
    //   1866: aload 12
    //   1868: iconst_0
    //   1869: aaload
    //   1870: ldc_w 358
    //   1873: invokevirtual 173	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1876: ifeq +28 -> 1904
    //   1879: aload_0
    //   1880: getfield 149	PFCpack/League:freeAgents	Ljava/util/ArrayList;
    //   1883: new 360	PFCpack/PlayerCB
    //   1886: dup
    //   1887: aload 13
    //   1889: aconst_null
    //   1890: aload 14
    //   1892: aload 15
    //   1894: invokespecial 361	PFCpack/PlayerCB:<init>	(Ljava/lang/String;LPFCpack/Team;[ILjava/lang/String;)V
    //   1897: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1900: pop
    //   1901: goto -380 -> 1521
    //   1904: aload 12
    //   1906: iconst_0
    //   1907: aaload
    //   1908: ldc_w 363
    //   1911: invokevirtual 173	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1914: ifeq +28 -> 1942
    //   1917: aload_0
    //   1918: getfield 149	PFCpack/League:freeAgents	Ljava/util/ArrayList;
    //   1921: new 365	PFCpack/PlayerDL
    //   1924: dup
    //   1925: aload 13
    //   1927: aconst_null
    //   1928: aload 14
    //   1930: aload 15
    //   1932: invokespecial 366	PFCpack/PlayerDL:<init>	(Ljava/lang/String;LPFCpack/Team;[ILjava/lang/String;)V
    //   1935: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1938: pop
    //   1939: goto -418 -> 1521
    //   1942: aload 12
    //   1944: iconst_0
    //   1945: aaload
    //   1946: ldc_w 368
    //   1949: invokevirtual 173	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1952: ifeq -431 -> 1521
    //   1955: aload_0
    //   1956: getfield 149	PFCpack/League:freeAgents	Ljava/util/ArrayList;
    //   1959: new 370	PFCpack/PlayerLB
    //   1962: dup
    //   1963: aload 13
    //   1965: aconst_null
    //   1966: aload 14
    //   1968: aload 15
    //   1970: invokespecial 371	PFCpack/PlayerLB:<init>	(Ljava/lang/String;LPFCpack/Team;[ILjava/lang/String;)V
    //   1973: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1976: pop
    //   1977: goto -456 -> 1521
    //   1980: aload_0
    //   1981: iconst_0
    //   1982: putfield 373	PFCpack/League:canGoOnline	Z
    //   1985: aload 11
    //   1987: invokevirtual 168	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   1990: astore_1
    //   1991: aload_1
    //   1992: ifnull +33 -> 2025
    //   1995: aload_1
    //   1996: ldc_w 375
    //   1999: invokevirtual 173	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2002: ifne +23 -> 2025
    //   2005: aload_1
    //   2006: invokestatic 203	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   2009: iconst_1
    //   2010: if_icmpne +688 -> 2698
    //   2013: iconst_1
    //   2014: istore 10
    //   2016: aload_0
    //   2017: iload 10
    //   2019: putfield 373	PFCpack/League:canGoOnline	Z
    //   2022: goto -37 -> 1985
    //   2025: aload 11
    //   2027: invokevirtual 282	java/io/BufferedReader:close	()V
    //   2030: goto -1510 -> 520
    //   2033: iload 7
    //   2035: iconst_1
    //   2036: iadd
    //   2037: istore 7
    //   2039: goto -1499 -> 540
    //   2042: aload_1
    //   2043: invokevirtual 282	java/io/BufferedReader:close	()V
    //   2046: goto -1447 -> 599
    //   2049: astore_1
    //   2050: getstatic 183	java/lang/System:out	Ljava/io/PrintStream;
    //   2053: ldc_w 284
    //   2056: invokevirtual 191	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   2059: goto -1460 -> 599
    //   2062: aload 4
    //   2064: getfield 238	PFCpack/Game:homeScore	I
    //   2067: ifne +11 -> 2078
    //   2070: aload 4
    //   2072: getfield 241	PFCpack/Game:awayScore	I
    //   2075: ifeq -1345 -> 730
    //   2078: aload_3
    //   2079: getfield 247	PFCpack/Team:gameWLSchedule	Ljava/util/ArrayList;
    //   2082: ldc_w 377
    //   2085: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   2088: pop
    //   2089: goto -1359 -> 730
    //   2092: astore_1
    //   2093: getstatic 183	java/lang/System:out	Ljava/io/PrintStream;
    //   2096: ldc -71
    //   2098: invokevirtual 191	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   2101: aload_0
    //   2102: new 111	java/util/ArrayList
    //   2105: dup
    //   2106: invokespecial 112	java/util/ArrayList:<init>	()V
    //   2109: putfield 379	PFCpack/League:nameList	Ljava/util/ArrayList;
    //   2112: aload 5
    //   2114: ldc_w 292
    //   2117: invokevirtual 197	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   2120: astore_1
    //   2121: aload_1
    //   2122: arraylength
    //   2123: istore 8
    //   2125: iconst_0
    //   2126: istore 7
    //   2128: iload 7
    //   2130: iload 8
    //   2132: if_icmpge +402 -> 2534
    //   2135: aload_1
    //   2136: iload 7
    //   2138: aaload
    //   2139: astore_2
    //   2140: aload_0
    //   2141: getfield 379	PFCpack/League:nameList	Ljava/util/ArrayList;
    //   2144: aload_2
    //   2145: invokevirtual 382	java/lang/String:trim	()Ljava/lang/String;
    //   2148: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   2151: pop
    //   2152: iload 7
    //   2154: iconst_1
    //   2155: iadd
    //   2156: istore 7
    //   2158: goto -30 -> 2128
    //   2161: aload_0
    //   2162: getfield 116	PFCpack/League:teamList	Ljava/util/ArrayList;
    //   2165: invokevirtual 386	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   2168: astore_2
    //   2169: aload_2
    //   2170: invokeinterface 392 1 0
    //   2175: ifeq +529 -> 2704
    //   2178: aload_2
    //   2179: invokeinterface 396 1 0
    //   2184: checkcast 211	PFCpack/Team
    //   2187: astore_3
    //   2188: iconst_0
    //   2189: istore 7
    //   2191: iload 7
    //   2193: bipush 16
    //   2195: if_icmpge -26 -> 2169
    //   2198: aload_3
    //   2199: getfield 399	PFCpack/Team:regSeasonSchedule	[LPFCpack/Game;
    //   2202: iload 7
    //   2204: aaload
    //   2205: astore 4
    //   2207: aload 4
    //   2209: ifnonnull +43 -> 2252
    //   2212: getstatic 183	java/lang/System:out	Ljava/io/PrintStream;
    //   2215: new 163	java/lang/StringBuilder
    //   2218: dup
    //   2219: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   2222: ldc_w 401
    //   2225: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2228: iload 7
    //   2230: invokevirtual 404	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2233: ldc_w 406
    //   2236: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2239: aload_3
    //   2240: getfield 409	PFCpack/Team:abbr	Ljava/lang/String;
    //   2243: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2246: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2249: invokevirtual 191	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   2252: aload_3
    //   2253: getfield 412	PFCpack/Team:gameSchedule	Ljava/util/ArrayList;
    //   2256: aload 4
    //   2258: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   2261: pop
    //   2262: iload 7
    //   2264: iconst_1
    //   2265: iadd
    //   2266: istore 7
    //   2268: goto -77 -> 2191
    //   2271: aload_1
    //   2272: invokevirtual 168	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   2275: astore_2
    //   2276: aload_2
    //   2277: ifnull +250 -> 2527
    //   2280: bipush 16
    //   2282: istore 9
    //   2284: iload 7
    //   2286: iconst_1
    //   2287: iadd
    //   2288: istore 8
    //   2290: iload 8
    //   2292: iconst_4
    //   2293: if_icmple +150 -> 2443
    //   2296: bipush 17
    //   2298: istore 7
    //   2300: getstatic 183	java/lang/System:out	Ljava/io/PrintStream;
    //   2303: new 163	java/lang/StringBuilder
    //   2306: dup
    //   2307: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   2310: ldc_w 414
    //   2313: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2316: iload 8
    //   2318: iconst_1
    //   2319: isub
    //   2320: invokevirtual 404	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2323: ldc_w 416
    //   2326: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2329: aload_2
    //   2330: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2333: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2336: invokevirtual 191	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   2339: new 85	PFCpack/Game
    //   2342: dup
    //   2343: aload_0
    //   2344: aload_2
    //   2345: iload 7
    //   2347: invokespecial 232	PFCpack/Game:<init>	(LPFCpack/League;Ljava/lang/String;I)V
    //   2350: astore_2
    //   2351: aload_0
    //   2352: getfield 87	PFCpack/League:playoffGames	[LPFCpack/Game;
    //   2355: iload 8
    //   2357: iconst_1
    //   2358: isub
    //   2359: aload_2
    //   2360: aastore
    //   2361: aload_2
    //   2362: getfield 235	PFCpack/Game:homeTeam	LPFCpack/Team;
    //   2365: getfield 412	PFCpack/Team:gameSchedule	Ljava/util/ArrayList;
    //   2368: aload_2
    //   2369: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   2372: pop
    //   2373: aload_2
    //   2374: getfield 244	PFCpack/Game:awayTeam	LPFCpack/Team;
    //   2377: getfield 412	PFCpack/Team:gameSchedule	Ljava/util/ArrayList;
    //   2380: aload_2
    //   2381: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   2384: pop
    //   2385: aload_2
    //   2386: getfield 238	PFCpack/Game:homeScore	I
    //   2389: aload_2
    //   2390: getfield 241	PFCpack/Game:awayScore	I
    //   2393: if_icmple +82 -> 2475
    //   2396: aload_2
    //   2397: getfield 235	PFCpack/Game:homeTeam	LPFCpack/Team;
    //   2400: getfield 247	PFCpack/Team:gameWLSchedule	Ljava/util/ArrayList;
    //   2403: ldc -7
    //   2405: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   2408: pop
    //   2409: aload_2
    //   2410: getfield 244	PFCpack/Game:awayTeam	LPFCpack/Team;
    //   2413: getfield 247	PFCpack/Team:gameWLSchedule	Ljava/util/ArrayList;
    //   2416: ldc_w 377
    //   2419: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   2422: pop
    //   2423: iload 8
    //   2425: istore 7
    //   2427: goto -156 -> 2271
    //   2430: astore_1
    //   2431: getstatic 183	java/lang/System:out	Ljava/io/PrintStream;
    //   2434: ldc_w 284
    //   2437: invokevirtual 191	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   2440: goto -339 -> 2101
    //   2443: iload 8
    //   2445: bipush 8
    //   2447: if_icmple +10 -> 2457
    //   2450: bipush 18
    //   2452: istore 7
    //   2454: goto -154 -> 2300
    //   2457: iload 9
    //   2459: istore 7
    //   2461: iload 8
    //   2463: bipush 10
    //   2465: if_icmple -165 -> 2300
    //   2468: bipush 19
    //   2470: istore 7
    //   2472: goto -172 -> 2300
    //   2475: aload_2
    //   2476: getfield 238	PFCpack/Game:homeScore	I
    //   2479: ifne +14 -> 2493
    //   2482: iload 8
    //   2484: istore 7
    //   2486: aload_2
    //   2487: getfield 241	PFCpack/Game:awayScore	I
    //   2490: ifeq -219 -> 2271
    //   2493: aload_2
    //   2494: getfield 235	PFCpack/Game:homeTeam	LPFCpack/Team;
    //   2497: getfield 247	PFCpack/Team:gameWLSchedule	Ljava/util/ArrayList;
    //   2500: ldc_w 377
    //   2503: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   2506: pop
    //   2507: aload_2
    //   2508: getfield 244	PFCpack/Game:awayTeam	LPFCpack/Team;
    //   2511: getfield 247	PFCpack/Team:gameWLSchedule	Ljava/util/ArrayList;
    //   2514: ldc -7
    //   2516: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   2519: pop
    //   2520: iload 8
    //   2522: istore 7
    //   2524: goto -253 -> 2271
    //   2527: aload_1
    //   2528: invokevirtual 282	java/io/BufferedReader:close	()V
    //   2531: goto -430 -> 2101
    //   2534: aload_0
    //   2535: new 111	java/util/ArrayList
    //   2538: dup
    //   2539: invokespecial 112	java/util/ArrayList:<init>	()V
    //   2542: putfield 418	PFCpack/League:lastNameList	Ljava/util/ArrayList;
    //   2545: aload 6
    //   2547: ldc_w 292
    //   2550: invokevirtual 197	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   2553: astore_1
    //   2554: aload_1
    //   2555: arraylength
    //   2556: istore 8
    //   2558: iconst_0
    //   2559: istore 7
    //   2561: iload 7
    //   2563: iload 8
    //   2565: if_icmpge +29 -> 2594
    //   2568: aload_1
    //   2569: iload 7
    //   2571: aaload
    //   2572: astore_2
    //   2573: aload_0
    //   2574: getfield 418	PFCpack/League:lastNameList	Ljava/util/ArrayList;
    //   2577: aload_2
    //   2578: invokevirtual 382	java/lang/String:trim	()Ljava/lang/String;
    //   2581: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   2584: pop
    //   2585: iload 7
    //   2587: iconst_1
    //   2588: iadd
    //   2589: istore 7
    //   2591: goto -30 -> 2561
    //   2594: aload_0
    //   2595: invokevirtual 421	PFCpack/League:updateLongestActiveWinStreak	()V
    //   2598: aload_0
    //   2599: new 111	java/util/ArrayList
    //   2602: dup
    //   2603: invokespecial 112	java/util/ArrayList:<init>	()V
    //   2606: putfield 423	PFCpack/League:newsStories	Ljava/util/ArrayList;
    //   2609: iconst_0
    //   2610: istore 7
    //   2612: iload 7
    //   2614: bipush 25
    //   2616: if_icmpge +27 -> 2643
    //   2619: aload_0
    //   2620: getfield 423	PFCpack/League:newsStories	Ljava/util/ArrayList;
    //   2623: new 111	java/util/ArrayList
    //   2626: dup
    //   2627: invokespecial 112	java/util/ArrayList:<init>	()V
    //   2630: invokevirtual 127	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   2633: pop
    //   2634: iload 7
    //   2636: iconst_1
    //   2637: iadd
    //   2638: istore 7
    //   2640: goto -28 -> 2612
    //   2643: aload_0
    //   2644: getfield 89	PFCpack/League:currentWeek	I
    //   2647: ifne +44 -> 2691
    //   2650: aload_0
    //   2651: iconst_0
    //   2652: putfield 83	PFCpack/League:mvpDecided	Z
    //   2655: aload_0
    //   2656: new 111	java/util/ArrayList
    //   2659: dup
    //   2660: invokespecial 112	java/util/ArrayList:<init>	()V
    //   2663: putfield 425	PFCpack/League:mvpCandidates	Ljava/util/ArrayList;
    //   2666: aload_0
    //   2667: ldc_w 318
    //   2670: putfield 316	PFCpack/League:mvpWinnerStrFull	Ljava/lang/String;
    //   2673: aload_0
    //   2674: ldc_w 318
    //   2677: putfield 312	PFCpack/League:allProStrFull	Ljava/lang/String;
    //   2680: aload_0
    //   2681: new 111	java/util/ArrayList
    //   2684: dup
    //   2685: invokespecial 112	java/util/ArrayList:<init>	()V
    //   2688: putfield 143	PFCpack/League:allPros	Ljava/util/ArrayList;
    //   2691: return
    //   2692: iconst_0
    //   2693: istore 7
    //   2695: goto -1283 -> 1412
    //   2698: iconst_0
    //   2699: istore 10
    //   2701: goto -685 -> 2016
    //   2704: iconst_0
    //   2705: istore 7
    //   2707: goto -436 -> 2271
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2710	0	this	League
    //   0	2710	1	paramFile1	File
    //   0	2710	2	paramFile2	File
    //   0	2710	3	paramFile3	File
    //   0	2710	4	paramFile4	File
    //   0	2710	5	paramString1	String
    //   0	2710	6	paramString2	String
    //   343	2363	7	i	int
    //   1436	1130	8	j	int
    //   2282	176	9	k	int
    //   2014	686	10	bool	boolean
    //   359	1667	11	localObject1	Object
    //   365	1578	12	localObject2	Object
    //   1506	458	13	str1	String
    //   1509	458	14	arrayOfInt	int[]
    //   1511	458	15	str2	String
    //   1631	25	16	arrayOfString	String[]
    // Exception table:
    //   from	to	target	type
    //   326	342	393	java/io/FileNotFoundException
    //   352	361	393	java/io/FileNotFoundException
    //   361	367	393	java/io/FileNotFoundException
    //   372	390	393	java/io/FileNotFoundException
    //   739	816	393	java/io/FileNotFoundException
    //   825	830	393	java/io/FileNotFoundException
    //   830	836	393	java/io/FileNotFoundException
    //   841	871	393	java/io/FileNotFoundException
    //   874	904	393	java/io/FileNotFoundException
    //   913	917	393	java/io/FileNotFoundException
    //   402	475	511	java/io/FileNotFoundException
    //   475	481	511	java/io/FileNotFoundException
    //   485	508	511	java/io/FileNotFoundException
    //   933	944	511	java/io/FileNotFoundException
    //   944	950	511	java/io/FileNotFoundException
    //   954	973	511	java/io/FileNotFoundException
    //   989	995	511	java/io/FileNotFoundException
    //   999	1054	511	java/io/FileNotFoundException
    //   1057	1063	511	java/io/FileNotFoundException
    //   1067	1149	511	java/io/FileNotFoundException
    //   1152	1158	511	java/io/FileNotFoundException
    //   1162	1217	511	java/io/FileNotFoundException
    //   1220	1231	511	java/io/FileNotFoundException
    //   1231	1237	511	java/io/FileNotFoundException
    //   1241	1260	511	java/io/FileNotFoundException
    //   1263	1271	511	java/io/FileNotFoundException
    //   1271	1277	511	java/io/FileNotFoundException
    //   1281	1316	511	java/io/FileNotFoundException
    //   1319	1335	511	java/io/FileNotFoundException
    //   1335	1341	511	java/io/FileNotFoundException
    //   1345	1380	511	java/io/FileNotFoundException
    //   1383	1409	511	java/io/FileNotFoundException
    //   1412	1418	511	java/io/FileNotFoundException
    //   1422	1432	511	java/io/FileNotFoundException
    //   1445	1458	511	java/io/FileNotFoundException
    //   1465	1478	511	java/io/FileNotFoundException
    //   1485	1521	511	java/io/FileNotFoundException
    //   1521	1527	511	java/io/FileNotFoundException
    //   1531	1559	511	java/io/FileNotFoundException
    //   1565	1574	511	java/io/FileNotFoundException
    //   1577	1600	511	java/io/FileNotFoundException
    //   1616	1639	511	java/io/FileNotFoundException
    //   1644	1664	511	java/io/FileNotFoundException
    //   1673	1709	511	java/io/FileNotFoundException
    //   1712	1748	511	java/io/FileNotFoundException
    //   1751	1786	511	java/io/FileNotFoundException
    //   1789	1825	511	java/io/FileNotFoundException
    //   1828	1863	511	java/io/FileNotFoundException
    //   1866	1901	511	java/io/FileNotFoundException
    //   1904	1939	511	java/io/FileNotFoundException
    //   1942	1977	511	java/io/FileNotFoundException
    //   1980	1985	511	java/io/FileNotFoundException
    //   1985	1991	511	java/io/FileNotFoundException
    //   1995	2013	511	java/io/FileNotFoundException
    //   2016	2022	511	java/io/FileNotFoundException
    //   2025	2030	511	java/io/FileNotFoundException
    //   520	537	590	java/io/FileNotFoundException
    //   547	556	590	java/io/FileNotFoundException
    //   556	562	590	java/io/FileNotFoundException
    //   567	587	590	java/io/FileNotFoundException
    //   2042	2046	590	java/io/FileNotFoundException
    //   326	342	920	java/io/IOException
    //   352	361	920	java/io/IOException
    //   361	367	920	java/io/IOException
    //   372	390	920	java/io/IOException
    //   739	816	920	java/io/IOException
    //   825	830	920	java/io/IOException
    //   830	836	920	java/io/IOException
    //   841	871	920	java/io/IOException
    //   874	904	920	java/io/IOException
    //   913	917	920	java/io/IOException
    //   402	475	976	java/io/IOException
    //   475	481	976	java/io/IOException
    //   485	508	976	java/io/IOException
    //   933	944	976	java/io/IOException
    //   944	950	976	java/io/IOException
    //   954	973	976	java/io/IOException
    //   989	995	976	java/io/IOException
    //   999	1054	976	java/io/IOException
    //   1057	1063	976	java/io/IOException
    //   1067	1149	976	java/io/IOException
    //   1152	1158	976	java/io/IOException
    //   1162	1217	976	java/io/IOException
    //   1220	1231	976	java/io/IOException
    //   1231	1237	976	java/io/IOException
    //   1241	1260	976	java/io/IOException
    //   1263	1271	976	java/io/IOException
    //   1271	1277	976	java/io/IOException
    //   1281	1316	976	java/io/IOException
    //   1319	1335	976	java/io/IOException
    //   1335	1341	976	java/io/IOException
    //   1345	1380	976	java/io/IOException
    //   1383	1409	976	java/io/IOException
    //   1412	1418	976	java/io/IOException
    //   1422	1432	976	java/io/IOException
    //   1445	1458	976	java/io/IOException
    //   1465	1478	976	java/io/IOException
    //   1485	1521	976	java/io/IOException
    //   1521	1527	976	java/io/IOException
    //   1531	1559	976	java/io/IOException
    //   1565	1574	976	java/io/IOException
    //   1577	1600	976	java/io/IOException
    //   1616	1639	976	java/io/IOException
    //   1644	1664	976	java/io/IOException
    //   1673	1709	976	java/io/IOException
    //   1712	1748	976	java/io/IOException
    //   1751	1786	976	java/io/IOException
    //   1789	1825	976	java/io/IOException
    //   1828	1863	976	java/io/IOException
    //   1866	1901	976	java/io/IOException
    //   1904	1939	976	java/io/IOException
    //   1942	1977	976	java/io/IOException
    //   1980	1985	976	java/io/IOException
    //   1985	1991	976	java/io/IOException
    //   1995	2013	976	java/io/IOException
    //   2016	2022	976	java/io/IOException
    //   2025	2030	976	java/io/IOException
    //   520	537	2049	java/io/IOException
    //   547	556	2049	java/io/IOException
    //   556	562	2049	java/io/IOException
    //   567	587	2049	java/io/IOException
    //   2042	2046	2049	java/io/IOException
    //   599	615	2092	java/io/FileNotFoundException
    //   615	620	2092	java/io/FileNotFoundException
    //   624	648	2092	java/io/FileNotFoundException
    //   651	698	2092	java/io/FileNotFoundException
    //   698	720	2092	java/io/FileNotFoundException
    //   720	730	2092	java/io/FileNotFoundException
    //   2062	2078	2092	java/io/FileNotFoundException
    //   2078	2089	2092	java/io/FileNotFoundException
    //   2161	2169	2092	java/io/FileNotFoundException
    //   2169	2188	2092	java/io/FileNotFoundException
    //   2198	2207	2092	java/io/FileNotFoundException
    //   2212	2252	2092	java/io/FileNotFoundException
    //   2252	2262	2092	java/io/FileNotFoundException
    //   2271	2276	2092	java/io/FileNotFoundException
    //   2300	2423	2092	java/io/FileNotFoundException
    //   2475	2482	2092	java/io/FileNotFoundException
    //   2486	2493	2092	java/io/FileNotFoundException
    //   2493	2520	2092	java/io/FileNotFoundException
    //   2527	2531	2092	java/io/FileNotFoundException
    //   599	615	2430	java/io/IOException
    //   615	620	2430	java/io/IOException
    //   624	648	2430	java/io/IOException
    //   651	698	2430	java/io/IOException
    //   698	720	2430	java/io/IOException
    //   720	730	2430	java/io/IOException
    //   2062	2078	2430	java/io/IOException
    //   2078	2089	2430	java/io/IOException
    //   2161	2169	2430	java/io/IOException
    //   2169	2188	2430	java/io/IOException
    //   2198	2207	2430	java/io/IOException
    //   2212	2252	2430	java/io/IOException
    //   2252	2262	2430	java/io/IOException
    //   2271	2276	2430	java/io/IOException
    //   2300	2423	2430	java/io/IOException
    //   2475	2482	2430	java/io/IOException
    //   2486	2493	2430	java/io/IOException
    //   2493	2520	2430	java/io/IOException
    //   2527	2531	2430	java/io/IOException
  }
  
  public League(String paramString1, String paramString2, File paramFile)
  {
    isHardMode = false;
    mvpDecided = false;
    canGoOnline = false;
    playoffGames = new Game[11];
    leagueHistory = new ArrayList();
    mvpHistory = new ArrayList();
    hallOfFame = new ArrayList();
    currentWeek = 0;
    divisions = new ArrayList();
    divisions.add(new Division("AMNOR", this));
    divisions.add(new Division("AMEAS", this));
    divisions.add(new Division("AMSOU", this));
    divisions.add(new Division("AMWES", this));
    divisions.add(new Division("NANOR", this));
    divisions.add(new Division("NAEAS", this));
    divisions.add(new Division("NASOU", this));
    divisions.add(new Division("NAWES", this));
    allPros = new ArrayList();
    playoffsAM = new ArrayList();
    playoffsNA = new ArrayList();
    freeAgents = new ArrayList();
    tradeLog = new ArrayList();
    leagueRecords = new LeagueRecords();
    userTeamRecords = new LeagueRecords();
    longestWinStreak = new TeamStreak(getYear(), getYear(), 0, "XXX");
    yearStartLongestWinStreak = new TeamStreak(getYear(), getYear(), 0, "XXX");
    longestActiveWinStreak = new TeamStreak(getYear(), getYear(), 0, "XXX");
    nameList = new ArrayList();
    paramString1 = paramString1.split(",");
    int j = paramString1.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramString1[i];
      nameList.add(((String)localObject).trim());
      i += 1;
    }
    lastNameList = new ArrayList();
    paramString1 = paramString2.split(",");
    j = paramString1.length;
    i = 0;
    while (i < j)
    {
      paramString2 = paramString1[i];
      lastNameList.add(paramString2.trim());
      i += 1;
    }
    teamList = new ArrayList();
    try
    {
      paramString1 = new BufferedReader(new FileReader(paramFile));
      i = 0;
    }
    catch (FileNotFoundException paramString1)
    {
      System.out.println("Unable to open file");
      for (;;)
      {
        newsStories = new ArrayList();
        i = 0;
        while (i < 25)
        {
          newsStories.add(new ArrayList());
          i += 1;
        }
        paramString2 = new Team(paramString2.toString(), this);
        division = divisions.get(i / 4)).divName;
        divisions.get(i / 4)).divTeams.add(paramString2);
        paramString2.setInitialStats();
        teamList.add(paramString2);
        System.out.println("Found Team: " + name + ", added to " + divisions.get(i / 4)).divName);
        i += 1;
        break;
        paramString1.close();
      }
    }
    catch (IOException paramString1)
    {
      for (;;)
      {
        System.out.println("Error reading file");
      }
      ((ArrayList)newsStories.get(0)).add("New Season!>Ready for the new season, coach? Whether the National Championship is on your mind, or just a winning season, good luck!");
      setUpSchedule();
    }
    if (i < 32)
    {
      paramString2 = new StringBuilder();
      for (;;)
      {
        paramFile = paramString1.readLine();
        if ((paramFile == null) || (paramFile.equals("END_PLAYERS"))) {
          break;
        }
        if (!paramFile.substring(0, 1).equals("#")) {
          paramString2.append(paramFile).append("%");
        }
      }
    }
  }
  
  public League(String paramString1, String paramString2, boolean paramBoolean)
  {
    isHardMode = paramBoolean;
    canGoOnline = true;
    mvpDecided = false;
    playoffGames = new Game[11];
    leagueHistory = new ArrayList();
    mvpHistory = new ArrayList();
    hallOfFame = new ArrayList();
    currentWeek = 0;
    divisions = new ArrayList();
    divisions.add(new Division("AMNOR", this));
    divisions.add(new Division("AMEAS", this));
    divisions.add(new Division("AMSOU", this));
    divisions.add(new Division("AMWES", this));
    divisions.add(new Division("NANOR", this));
    divisions.add(new Division("NAEAS", this));
    divisions.add(new Division("NASOU", this));
    divisions.add(new Division("NAWES", this));
    allPros = new ArrayList();
    playoffsAM = new ArrayList();
    playoffsNA = new ArrayList();
    freeAgents = new ArrayList();
    tradeLog = new ArrayList();
    newsStories = new ArrayList();
    int i = 0;
    while (i < 25)
    {
      newsStories.add(new ArrayList());
      i += 1;
    }
    ((ArrayList)newsStories.get(0)).add("New Season!>Ready for the new season, coach? Whether the National Championship is on your mind, or just a winning season, good luck!");
    leagueRecords = new LeagueRecords();
    userTeamRecords = new LeagueRecords();
    longestWinStreak = new TeamStreak(getYear(), getYear(), 0, "XXX");
    yearStartLongestWinStreak = new TeamStreak(getYear(), getYear(), 0, "XXX");
    longestActiveWinStreak = new TeamStreak(getYear(), getYear(), 0, "XXX");
    nameList = new ArrayList();
    paramString1 = paramString1.split(",");
    int j = paramString1.length;
    i = 0;
    while (i < j)
    {
      Object localObject = paramString1[i];
      nameList.add(((String)localObject).trim());
      i += 1;
    }
    lastNameList = new ArrayList();
    paramString1 = paramString2.split(",");
    j = paramString1.length;
    i = 0;
    while (i < j)
    {
      paramString2 = paramString1[i];
      lastNameList.add(paramString2.trim());
      i += 1;
    }
    paramString1 = divisions.iterator();
    while (paramString1.hasNext()) {
      ((Division)paramString1.next()).generateTeams();
    }
    teamList = new ArrayList();
    i = 0;
    while (i < divisions.size())
    {
      j = 0;
      while (j < divisions.get(i)).divTeams.size())
      {
        teamList.add(divisions.get(i)).divTeams.get(j));
        j += 1;
      }
      i += 1;
    }
    setUpSchedule();
  }
  
  public void advanceSeason()
  {
    updateTeamHistories();
    updateLeagueHistory();
    currentWeek = 0;
    int i = 0;
    while (i < teamList.size())
    {
      ((Team)teamList.get(i)).advanceSeason();
      i += 1;
    }
    advanceSeasonWinStreaks();
    i = 0;
    while (i < divisions.size())
    {
      divisions.get(i)).robinWeek = 0;
      divisions.get(i)).week = 0;
      i += 1;
    }
    setUpSchedule();
  }
  
  public void advanceSeasonWinStreaks()
  {
    yearStartLongestWinStreak = longestWinStreak;
    Iterator localIterator = teamList.iterator();
    while (localIterator.hasNext())
    {
      Team localTeam = (Team)localIterator.next();
      yearStartWinStreak = winStreak;
    }
  }
  
  public void changeAbbrHistoryRecords(String paramString1, String paramString2)
  {
    leagueRecords.changeAbbrRecords(userTeam.abbr, paramString2);
    userTeamRecords.changeAbbrRecords(userTeam.abbr, paramString2);
    changeAbbrWinStreaks(userTeam.abbr, paramString2);
    userTeam.winStreak.changeAbbr(paramString2);
    userTeam.yearStartWinStreak.changeAbbr(paramString2);
    Object localObject = leagueHistory.iterator();
    while (((Iterator)localObject).hasNext())
    {
      String[] arrayOfString = (String[])((Iterator)localObject).next();
      i = 0;
      while (i < arrayOfString.length)
      {
        if (arrayOfString[i].split(" ")[0].equals(paramString1)) {
          arrayOfString[i] = (paramString2 + " " + arrayOfString[i].split(" ")[1]);
        }
        i += 1;
      }
    }
    int i = 0;
    while (i < mvpHistory.size())
    {
      localObject = (String)mvpHistory.get(i);
      if (localObject.split(" ")[4].equals(paramString1)) {
        mvpHistory.set(i, localObject.split(" ")[0] + " " + localObject.split(" ")[1] + " " + localObject.split(" ")[2] + " " + localObject.split(" ")[3] + " " + paramString2 + " " + localObject.split(" ")[5]);
      }
      i += 1;
    }
  }
  
  public void changeAbbrWinStreaks(String paramString1, String paramString2)
  {
    if (longestWinStreak.getTeam().equals(paramString1)) {
      longestWinStreak.changeAbbr(paramString2);
    }
    if (yearStartLongestWinStreak.getTeam().equals(paramString1)) {
      yearStartLongestWinStreak.changeAbbr(paramString2);
    }
  }
  
  public void checkHallofFame(ArrayList<Player> paramArrayList)
  {
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      Object localObject1 = (Player)paramArrayList.next();
      int k = statsWins;
      int m = careerWins;
      int n = careerAllPro;
      int i;
      int i1;
      if (wonAllPro)
      {
        i = 1;
        i1 = careerMVP;
        if (!wonMVP) {
          break label226;
        }
      }
      StringBuilder localStringBuilder;
      label226:
      for (int j = 1;; j = 0)
      {
        if ((k + m) / 5 + (n + i) * 10 + (i1 + j) * 50 <= 50) {
          break label229;
        }
        Object localObject2 = ((Player)localObject1).getCareerStatsList();
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(getYear() - 1 + ": " + ((Player)localObject1).getPosNameYrOvr_Str() + "&");
        localObject1 = ((ArrayList)localObject2).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (String)((Iterator)localObject1).next();
          localStringBuilder.append((String)localObject2 + "&");
        }
        i = 0;
        break;
      }
      label229:
      continue;
      hallOfFame.add(localStringBuilder.toString());
    }
  }
  
  public void checkLeagueRecords()
  {
    Iterator localIterator = teamList.iterator();
    while (localIterator.hasNext()) {
      ((Team)localIterator.next()).checkLeagueRecords(leagueRecords);
    }
    userTeam.checkLeagueRecords(userTeamRecords);
  }
  
  public void checkLongestWinStreak(TeamStreak paramTeamStreak)
  {
    if (paramTeamStreak.getStreakLength() > longestWinStreak.getStreakLength()) {
      longestWinStreak = new TeamStreak(paramTeamStreak.getStartYear(), paramTeamStreak.getEndYear(), paramTeamStreak.getStreakLength(), paramTeamStreak.getTeam());
    }
  }
  
  public void curePlayers()
  {
    Iterator localIterator = teamList.iterator();
    while (localIterator.hasNext()) {
      ((Team)localIterator.next()).curePlayers();
    }
  }
  
  /* Error */
  public void exportRosters(File paramFile)
  {
    // Byte code:
    //   0: new 564	java/io/PrintWriter
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 565	java/io/PrintWriter:<init>	(Ljava/io/File;)V
    //   8: astore_2
    //   9: aload_2
    //   10: ldc_w 318
    //   13: invokevirtual 568	java/io/PrintWriter:print	(Ljava/lang/String;)V
    //   16: aload_2
    //   17: invokevirtual 569	java/io/PrintWriter:close	()V
    //   20: new 571	java/io/FileWriter
    //   23: dup
    //   24: aload_1
    //   25: iconst_1
    //   26: invokespecial 574	java/io/FileWriter:<init>	(Ljava/io/File;Z)V
    //   29: astore_3
    //   30: new 576	java/io/BufferedWriter
    //   33: dup
    //   34: aload_3
    //   35: invokespecial 579	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   38: astore 4
    //   40: new 564	java/io/PrintWriter
    //   43: dup
    //   44: aload 4
    //   46: invokespecial 580	java/io/PrintWriter:<init>	(Ljava/io/Writer;)V
    //   49: astore 5
    //   51: aconst_null
    //   52: astore_2
    //   53: aload_0
    //   54: getfield 114	PFCpack/League:divisions	Ljava/util/ArrayList;
    //   57: invokevirtual 386	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   60: astore_1
    //   61: aload_1
    //   62: invokeinterface 392 1 0
    //   67: ifeq +225 -> 292
    //   70: aload_1
    //   71: invokeinterface 396 1 0
    //   76: checkcast 118	PFCpack/Division
    //   79: getfield 266	PFCpack/Division:divTeams	Ljava/util/ArrayList;
    //   82: invokevirtual 386	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   85: astore 6
    //   87: aload 6
    //   89: invokeinterface 392 1 0
    //   94: ifeq -33 -> 61
    //   97: aload 6
    //   99: invokeinterface 396 1 0
    //   104: checkcast 211	PFCpack/Team
    //   107: astore 7
    //   109: aload 5
    //   111: new 163	java/lang/StringBuilder
    //   114: dup
    //   115: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   118: aload 7
    //   120: getfield 449	PFCpack/Team:name	Ljava/lang/String;
    //   123: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: ldc_w 292
    //   129: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: aload 7
    //   134: getfield 409	PFCpack/Team:abbr	Ljava/lang/String;
    //   137: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: ldc_w 310
    //   143: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   149: invokevirtual 568	java/io/PrintWriter:print	(Ljava/lang/String;)V
    //   152: aload 7
    //   154: invokevirtual 583	PFCpack/Team:getAllPlayers	()Ljava/util/ArrayList;
    //   157: invokevirtual 386	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   160: astore 7
    //   162: aload 7
    //   164: invokeinterface 392 1 0
    //   169: ifeq +108 -> 277
    //   172: aload 5
    //   174: aload 7
    //   176: invokeinterface 396 1 0
    //   181: checkcast 510	PFCpack/Player
    //   184: invokevirtual 586	PFCpack/Player:getCSVRosterFile	()Ljava/lang/String;
    //   187: invokevirtual 587	java/io/PrintWriter:println	(Ljava/lang/String;)V
    //   190: goto -28 -> 162
    //   193: astore_2
    //   194: aload_2
    //   195: athrow
    //   196: astore_1
    //   197: aload 5
    //   199: ifnull +12 -> 211
    //   202: aload_2
    //   203: ifnull +167 -> 370
    //   206: aload 5
    //   208: invokevirtual 569	java/io/PrintWriter:close	()V
    //   211: aload_1
    //   212: athrow
    //   213: astore_2
    //   214: aload_2
    //   215: athrow
    //   216: astore_1
    //   217: aload 4
    //   219: ifnull +12 -> 231
    //   222: aload_2
    //   223: ifnull +183 -> 406
    //   226: aload 4
    //   228: invokevirtual 588	java/io/BufferedWriter:close	()V
    //   231: aload_1
    //   232: athrow
    //   233: astore_1
    //   234: aload_1
    //   235: athrow
    //   236: astore_2
    //   237: aload_3
    //   238: ifnull +11 -> 249
    //   241: aload_1
    //   242: ifnull +186 -> 428
    //   245: aload_3
    //   246: invokevirtual 589	java/io/FileWriter:close	()V
    //   249: aload_2
    //   250: athrow
    //   251: astore_1
    //   252: getstatic 183	java/lang/System:out	Ljava/io/PrintStream;
    //   255: aload_1
    //   256: invokevirtual 590	java/io/IOException:toString	()Ljava/lang/String;
    //   259: invokevirtual 191	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   262: return
    //   263: astore_2
    //   264: getstatic 183	java/lang/System:out	Ljava/io/PrintStream;
    //   267: aload_2
    //   268: invokevirtual 591	java/lang/Exception:toString	()Ljava/lang/String;
    //   271: invokevirtual 191	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   274: goto -254 -> 20
    //   277: aload 5
    //   279: ldc_w 593
    //   282: invokevirtual 568	java/io/PrintWriter:print	(Ljava/lang/String;)V
    //   285: goto -198 -> 87
    //   288: astore_1
    //   289: goto -92 -> 197
    //   292: aload 5
    //   294: ifnull +12 -> 306
    //   297: iconst_0
    //   298: ifeq +53 -> 351
    //   301: aload 5
    //   303: invokevirtual 569	java/io/PrintWriter:close	()V
    //   306: aload 4
    //   308: ifnull +12 -> 320
    //   311: iconst_0
    //   312: ifeq +75 -> 387
    //   315: aload 4
    //   317: invokevirtual 588	java/io/BufferedWriter:close	()V
    //   320: aload_3
    //   321: ifnull -59 -> 262
    //   324: iconst_0
    //   325: ifeq +89 -> 414
    //   328: aload_3
    //   329: invokevirtual 589	java/io/FileWriter:close	()V
    //   332: return
    //   333: astore_1
    //   334: new 595	java/lang/NullPointerException
    //   337: dup
    //   338: invokespecial 596	java/lang/NullPointerException:<init>	()V
    //   341: athrow
    //   342: astore_1
    //   343: new 595	java/lang/NullPointerException
    //   346: dup
    //   347: invokespecial 596	java/lang/NullPointerException:<init>	()V
    //   350: athrow
    //   351: aload 5
    //   353: invokevirtual 569	java/io/PrintWriter:close	()V
    //   356: goto -50 -> 306
    //   359: astore 5
    //   361: aload_2
    //   362: aload 5
    //   364: invokevirtual 600	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   367: goto -156 -> 211
    //   370: aload 5
    //   372: invokevirtual 569	java/io/PrintWriter:close	()V
    //   375: goto -164 -> 211
    //   378: astore_1
    //   379: new 595	java/lang/NullPointerException
    //   382: dup
    //   383: invokespecial 596	java/lang/NullPointerException:<init>	()V
    //   386: athrow
    //   387: aload 4
    //   389: invokevirtual 588	java/io/BufferedWriter:close	()V
    //   392: goto -72 -> 320
    //   395: astore 4
    //   397: aload_2
    //   398: aload 4
    //   400: invokevirtual 600	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   403: goto -172 -> 231
    //   406: aload 4
    //   408: invokevirtual 588	java/io/BufferedWriter:close	()V
    //   411: goto -180 -> 231
    //   414: aload_3
    //   415: invokevirtual 589	java/io/FileWriter:close	()V
    //   418: return
    //   419: astore_3
    //   420: aload_1
    //   421: aload_3
    //   422: invokevirtual 600	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   425: goto -176 -> 249
    //   428: aload_3
    //   429: invokevirtual 589	java/io/FileWriter:close	()V
    //   432: goto -183 -> 249
    //   435: astore_1
    //   436: aconst_null
    //   437: astore_2
    //   438: goto -221 -> 217
    //   441: astore_2
    //   442: aconst_null
    //   443: astore_1
    //   444: goto -207 -> 237
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	447	0	this	League
    //   0	447	1	paramFile	File
    //   8	45	2	localPrintWriter1	java.io.PrintWriter
    //   193	10	2	localThrowable1	Throwable
    //   213	10	2	localThrowable2	Throwable
    //   236	14	2	localObject1	Object
    //   263	135	2	localException	Exception
    //   437	1	2	localObject2	Object
    //   441	1	2	localObject3	Object
    //   29	386	3	localFileWriter	java.io.FileWriter
    //   419	10	3	localThrowable3	Throwable
    //   38	350	4	localBufferedWriter	java.io.BufferedWriter
    //   395	12	4	localThrowable4	Throwable
    //   49	303	5	localPrintWriter2	java.io.PrintWriter
    //   359	12	5	localThrowable5	Throwable
    //   85	13	6	localIterator	Iterator
    //   107	68	7	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   53	61	193	java/lang/Throwable
    //   61	87	193	java/lang/Throwable
    //   87	162	193	java/lang/Throwable
    //   162	190	193	java/lang/Throwable
    //   277	285	193	java/lang/Throwable
    //   194	196	196	finally
    //   40	51	213	java/lang/Throwable
    //   211	213	213	java/lang/Throwable
    //   343	351	213	java/lang/Throwable
    //   351	356	213	java/lang/Throwable
    //   361	367	213	java/lang/Throwable
    //   370	375	213	java/lang/Throwable
    //   214	216	216	finally
    //   30	40	233	java/lang/Throwable
    //   231	233	233	java/lang/Throwable
    //   379	387	233	java/lang/Throwable
    //   387	392	233	java/lang/Throwable
    //   397	403	233	java/lang/Throwable
    //   406	411	233	java/lang/Throwable
    //   234	236	236	finally
    //   20	30	251	java/io/IOException
    //   245	249	251	java/io/IOException
    //   249	251	251	java/io/IOException
    //   328	332	251	java/io/IOException
    //   334	342	251	java/io/IOException
    //   414	418	251	java/io/IOException
    //   420	425	251	java/io/IOException
    //   428	432	251	java/io/IOException
    //   0	20	263	java/lang/Exception
    //   53	61	288	finally
    //   61	87	288	finally
    //   87	162	288	finally
    //   162	190	288	finally
    //   277	285	288	finally
    //   328	332	333	java/lang/Throwable
    //   301	306	342	java/lang/Throwable
    //   206	211	359	java/lang/Throwable
    //   315	320	378	java/lang/Throwable
    //   226	231	395	java/lang/Throwable
    //   245	249	419	java/lang/Throwable
    //   40	51	435	finally
    //   206	211	435	finally
    //   211	213	435	finally
    //   301	306	435	finally
    //   343	351	435	finally
    //   351	356	435	finally
    //   361	367	435	finally
    //   370	375	435	finally
    //   30	40	441	finally
    //   226	231	441	finally
    //   231	233	441	finally
    //   315	320	441	finally
    //   379	387	441	finally
    //   387	392	441	finally
    //   397	403	441	finally
    //   406	411	441	finally
  }
  
  public Division findDivision(String paramString)
  {
    int i = 0;
    while (i < teamList.size())
    {
      if (divisions.get(i)).divName.equals(paramString)) {
        return (Division)divisions.get(i);
      }
      i += 1;
    }
    return (Division)divisions.get(0);
  }
  
  public Team findTeam(String paramString)
  {
    int i = 0;
    while (i < teamList.size())
    {
      if (((Team)teamList.get(i)).strRep().equals(paramString)) {
        return (Team)teamList.get(i);
      }
      i += 1;
    }
    return (Team)teamList.get(0);
  }
  
  public Team findTeamAbbr(String paramString)
  {
    int i = 0;
    while (i < teamList.size())
    {
      if (teamList.get(i)).abbr.equals(paramString)) {
        return (Team)teamList.get(i);
      }
      i += 1;
    }
    return (Team)teamList.get(0);
  }
  
  public void freeAgency()
  {
    sortFAs();
    int i = 0;
    while (i < freeAgents.size()) {
      if (PlayerPickerLogic.signFreeAgentForTeam(teamList, (Player)freeAgents.get(i), null, 70, 73, false)) {
        freeAgents.remove(i);
      } else {
        i += 1;
      }
    }
    Iterator localIterator = freeAgents.iterator();
    while (localIterator.hasNext())
    {
      Player localPlayer = (Player)localIterator.next();
      System.out.println("FA LEFT OVER: " + localPlayer.getPosNameYrOvrPot_OneLine());
      Object localObject;
      if ((localPlayer instanceof PlayerQB))
      {
        localObject = (PlayerQB)localPlayer;
        leagueRecords.checkRecord("Career Pass Yards", statsPassYards + careerPassYards, team.abbr + " " + ((PlayerQB)localObject).getInitialName(), getYear() - 1);
        leagueRecords.checkRecord("Career Pass TDs", statsTD + careerTDs, team.abbr + " " + ((PlayerQB)localObject).getInitialName(), getYear() - 1);
        leagueRecords.checkRecord("Career Interceptions", statsInt + careerInt, team.abbr + " " + ((PlayerQB)localObject).getInitialName(), getYear() - 1);
      }
      else if ((localPlayer instanceof PlayerRB))
      {
        localObject = (PlayerRB)localPlayer;
        leagueRecords.checkRecord("Career Rush Yards", statsRushYards + careerRushYards, team.abbr + " " + ((PlayerRB)localObject).getInitialName(), getYear() - 1);
        leagueRecords.checkRecord("Career Rush TDs", statsTD + careerTDs, team.abbr + " " + ((PlayerRB)localObject).getInitialName(), getYear() - 1);
        leagueRecords.checkRecord("Career Rush Fumbles", statsFumbles + careerFumbles, team.abbr + " " + ((PlayerRB)localObject).getInitialName(), getYear() - 1);
      }
      else if ((localPlayer instanceof PlayerWR))
      {
        localObject = (PlayerWR)localPlayer;
        leagueRecords.checkRecord("Career Rec Yards", statsRecYards + careerRecYards, team.abbr + " " + ((PlayerWR)localObject).getInitialName(), getYear() - 1);
        leagueRecords.checkRecord("Career Rec TDs", statsTD + careerTD, team.abbr + " " + ((PlayerWR)localObject).getInitialName(), getYear() - 1);
      }
    }
    checkHallofFame(freeAgents);
  }
  
  public void freeAgencyDay(ArrayList<String> paramArrayList)
  {
    sortFAs();
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(teamList);
    int i = 0;
    while ((i < 20) && (freeAgents.size() > 20))
    {
      int j = (int)(10.0D * Math.random());
      if (PlayerPickerLogic.signFreeAgentForTeam(localArrayList, (Player)freeAgents.get(j), paramArrayList, 70, 73, false))
      {
        localArrayList.remove(((Player)freeAgents.get(j)).getTeam());
        freeAgents.remove(j);
      }
      i += 1;
    }
  }
  
  public void generatePlayersNewLeague()
  {
    Object localObject = DraftGenerator.generateLeaguePlayers(this);
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < 60)
    {
      localArrayList.addAll(teamList);
      int j = 0;
      while (j < teamList.size())
      {
        if (PlayerPickerLogic.signFreeAgentForTeam(localArrayList, (Player)((ArrayList)localObject).get(0), null, 70, 75, true))
        {
          localArrayList.remove(((Player)((ArrayList)localObject).get(0)).getTeam());
          ((Player)((ArrayList)localObject).get(0)).getContract().setYearsLeft((int)(Math.random() * ((Player)((ArrayList)localObject).get(0)).getContract().getYearsLeft()) + 1);
        }
        ((ArrayList)localObject).remove(0);
        j += 1;
      }
      localArrayList.clear();
      i += 1;
    }
    localObject = teamList.iterator();
    while (((Iterator)localObject).hasNext()) {
      ((Team)((Iterator)localObject).next()).setInitialStats();
    }
  }
  
  public String getAllDivStr(int paramInt)
  {
    ArrayList localArrayList = ((Division)divisions.get(paramInt)).getAllDivPlayers();
    StringBuilder localStringBuilder = new StringBuilder();
    paramInt = 0;
    if (paramInt < localArrayList.size())
    {
      Player localPlayer = (Player)localArrayList.get(paramInt);
      localStringBuilder.append(team.abbr + "(" + team.wins + "-" + team.losses + ") - ");
      Object localObject;
      if ((localPlayer instanceof PlayerQB))
      {
        localObject = (PlayerQB)localPlayer;
        localStringBuilder.append(" QB " + name + " [" + age + "]\n \t\t" + statsTD + " TDs, " + statsInt + " Int, " + statsPassYards + " Yds\n");
      }
      for (;;)
      {
        localStringBuilder.append(" \t\tOverall: " + ratOvr + ", Potential: " + Player.getLetterGrade(ratPot) + "\n\n>");
        paramInt += 1;
        break;
        if ((localPlayer instanceof PlayerRB))
        {
          localObject = (PlayerRB)localPlayer;
          localStringBuilder.append(" RB " + name + " [" + age + "]\n \t\t" + statsTD + " TDs, " + statsFumbles + " Fum, " + statsRushYards + " Yds\n");
        }
        else if ((localPlayer instanceof PlayerWR))
        {
          localObject = (PlayerWR)localPlayer;
          localStringBuilder.append(" WR " + name + " [" + age + "]\n \t\t" + statsTD + " TDs, " + statsFumbles + " Fum, " + statsRecYards + " Yds\n");
        }
        else if ((localPlayer instanceof PlayerK))
        {
          localObject = (PlayerK)localPlayer;
          localStringBuilder.append(" K " + name + " [" + age + "]\n \t\tFGs: " + statsFGMade + "/" + statsFGAtt + ", XPs: " + statsXPMade + "/" + statsXPAtt + "\n");
        }
        else
        {
          localStringBuilder.append(" " + position + " " + name + " [" + age + "]\n");
        }
      }
    }
    return localStringBuilder.toString();
  }
  
  public String getAllProStr()
  {
    Object localObject1;
    Object localObject2;
    Object localObject3;
    int i;
    if ((allPros.isEmpty()) && ((allProStrFull == null) || (allProStrFull.equals(""))))
    {
      localObject1 = new ArrayList();
      localObject2 = new ArrayList();
      localObject3 = new ArrayList();
      ArrayList localArrayList1 = new ArrayList();
      ArrayList localArrayList2 = new ArrayList();
      ArrayList localArrayList3 = new ArrayList();
      ArrayList localArrayList4 = new ArrayList();
      ArrayList localArrayList5 = new ArrayList();
      ArrayList localArrayList6 = new ArrayList();
      Iterator localIterator = divisions.iterator();
      while (localIterator.hasNext())
      {
        Division localDivision = (Division)localIterator.next();
        localDivision.getAllDivPlayers();
        ((ArrayList)localObject1).add((PlayerQB)allDivPlayers.get(0));
        ((ArrayList)localObject2).add((PlayerRB)allDivPlayers.get(1));
        ((ArrayList)localObject2).add((PlayerRB)allDivPlayers.get(2));
        ((ArrayList)localObject3).add((PlayerWR)allDivPlayers.get(3));
        ((ArrayList)localObject3).add((PlayerWR)allDivPlayers.get(4));
        ((ArrayList)localObject3).add((PlayerWR)allDivPlayers.get(5));
        i = 6;
        while (i < 11)
        {
          localArrayList1.add((PlayerOL)allDivPlayers.get(i));
          i += 1;
        }
        localArrayList2.add((PlayerK)allDivPlayers.get(11));
        localArrayList3.add((PlayerS)allDivPlayers.get(12));
        i = 13;
        while (i < 16)
        {
          localArrayList4.add((PlayerCB)allDivPlayers.get(i));
          i += 1;
        }
        i = 16;
        while (i < 20)
        {
          localArrayList5.add((PlayerDL)allDivPlayers.get(i));
          i += 1;
        }
        i = 20;
        while (i < 23)
        {
          localArrayList6.add((PlayerLB)allDivPlayers.get(i));
          i += 1;
        }
      }
      Collections.sort((List)localObject1, new PlayerMVPComp());
      Collections.sort((List)localObject2, new PlayerMVPComp());
      Collections.sort((List)localObject3, new PlayerMVPComp());
      Collections.sort(localArrayList1, new PlayerMVPComp());
      Collections.sort(localArrayList2, new PlayerMVPComp());
      Collections.sort(localArrayList3, new PlayerMVPComp());
      Collections.sort(localArrayList4, new PlayerMVPComp());
      Collections.sort(localArrayList5, new PlayerMVPComp());
      Collections.sort(localArrayList6, new PlayerMVPComp());
      allPros.add(((ArrayList)localObject1).get(0));
      get0wonAllPro = true;
      allPros.add(((ArrayList)localObject2).get(0));
      get0wonAllPro = true;
      allPros.add(((ArrayList)localObject2).get(1));
      get1wonAllPro = true;
      i = 0;
      while (i < 3)
      {
        allPros.add(((ArrayList)localObject3).get(i));
        getwonAllPro = true;
        i += 1;
      }
      i = 0;
      while (i < 5)
      {
        allPros.add(localArrayList1.get(i));
        getwonAllPro = true;
        i += 1;
      }
      allPros.add(localArrayList2.get(0));
      get0wonAllPro = true;
      allPros.add(localArrayList3.get(0));
      get0wonAllPro = true;
      i = 0;
      while (i < 3)
      {
        allPros.add(localArrayList4.get(i));
        getwonAllPro = true;
        i += 1;
      }
      i = 0;
      while (i < 4)
      {
        allPros.add(localArrayList5.get(i));
        getwonAllPro = true;
        i += 1;
      }
      i = 0;
      while (i < 3)
      {
        allPros.add(localArrayList6.get(i));
        getwonAllPro = true;
        i += 1;
      }
    }
    if ((allProStrFull == null) || (allProStrFull.equals("")))
    {
      localObject1 = new StringBuilder();
      i = 0;
      if (i < allPros.size())
      {
        localObject2 = (Player)allPros.get(i);
        ((StringBuilder)localObject1).append(team.abbr + "(" + team.wins + "-" + team.losses + ") - ");
        if ((localObject2 instanceof PlayerQB))
        {
          localObject3 = (PlayerQB)localObject2;
          ((StringBuilder)localObject1).append(" QB " + name + " [" + age + "]\n \t\t" + statsTD + " TDs, " + statsInt + " Int, " + statsPassYards + " Yds\n");
        }
        for (;;)
        {
          ((StringBuilder)localObject1).append(" \t\tOverall: " + ratOvr + ", Potential: " + Player.getLetterGrade(ratPot) + "\n\n>");
          i += 1;
          break;
          if ((localObject2 instanceof PlayerRB))
          {
            localObject3 = (PlayerRB)localObject2;
            ((StringBuilder)localObject1).append(" RB " + name + " [" + age + "]\n \t\t" + statsTD + " TDs, " + statsFumbles + " Fum, " + statsRushYards + " Yds\n");
          }
          else if ((localObject2 instanceof PlayerWR))
          {
            localObject3 = (PlayerWR)localObject2;
            ((StringBuilder)localObject1).append(" WR " + name + " [" + age + "]\n \t\t" + statsTD + " TDs, " + statsFumbles + " Fum, " + statsRecYards + " Yds\n");
          }
          else if ((localObject2 instanceof PlayerK))
          {
            localObject3 = (PlayerK)localObject2;
            ((StringBuilder)localObject1).append(" K " + name + " [" + age + "]\n \t\tFGs: " + statsFGMade + "/" + statsFGAtt + ", XPs: " + statsXPMade + "/" + statsXPAtt + "\n");
          }
          else
          {
            ((StringBuilder)localObject1).append(" " + position + " " + name + " [" + age + "]\n");
          }
        }
      }
      allProStrFull = ((StringBuilder)localObject1).toString();
      return ((StringBuilder)localObject1).toString();
    }
    return allProStrFull;
  }
  
  public int getDivNumber(String paramString)
  {
    int i = 0;
    while (i < divisions.size())
    {
      if (divisions.get(i)).divName.equals(paramString)) {
        return i;
      }
      i += 1;
    }
    return 0;
  }
  
  public ArrayList<String> getDivStandings()
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    Iterator localIterator = divisions.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (Division)localIterator.next();
      localArrayList2.addAll(divTeams);
      Collections.sort(localArrayList2, new TeamCompDivision());
      localArrayList1.add(" ," + divName + " division, ");
      int i = 0;
      while (i < localArrayList2.size())
      {
        localObject = (Team)localArrayList2.get(i);
        localArrayList1.add(((Team)localObject).getRankStrStarUser(i + 1) + "," + ((Team)localObject).strRepWithBowlResults() + "," + ((Team)localObject).getDivWins() + "-" + ((Team)localObject).getDivLosses());
        i += 1;
      }
      localArrayList2.clear();
    }
    return localArrayList1;
  }
  
  public String getGameSummaryBowl(Game paramGame)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (!hasPlayed) {
      return homeTeam.strRep() + " vs " + awayTeam.strRep();
    }
    if (homeScore > awayScore)
    {
      localTeam1 = homeTeam;
      localTeam2 = awayTeam;
      localStringBuilder.append(localTeam1.strRep() + " W ");
      localStringBuilder.append(homeScore + "-" + awayScore + " ");
      localStringBuilder.append("vs " + localTeam2.strRep());
      return localStringBuilder.toString();
    }
    Team localTeam1 = awayTeam;
    Team localTeam2 = homeTeam;
    localStringBuilder.append(localTeam1.strRep() + " W ");
    localStringBuilder.append(awayScore + "-" + homeScore + " ");
    localStringBuilder.append("@ " + localTeam2.strRep());
    return localStringBuilder.toString();
  }
  
  public String getLeagueHistoryStr()
  {
    String str = "";
    int i = 0;
    if (i < leagueHistory.size())
    {
      str = str + (i + 2016) + ":\n";
      if (((String[])leagueHistory.get(i)).length > 0)
      {
        str = str + "\tChampions: " + ((String[])leagueHistory.get(i))[0] + "\n";
        label104:
        if (mvpHistory.size() <= i) {
          break label187;
        }
      }
      label187:
      for (str = str + "\tMVP: " + (String)mvpHistory.get(i) + "\n%";; str = str + "\tMVP: ERROR\n")
      {
        i += 1;
        break;
        str = str + "\tChampions: ERROR\n";
        break label104;
      }
    }
    return str;
  }
  
  public String getLeagueRecordsStr()
  {
    String str1 = "Longest Win Streak," + longestWinStreak.getStreakLength() + "," + longestWinStreak.getTeam() + "," + longestWinStreak.getStartYear() + "-" + longestWinStreak.getEndYear() + "\n";
    String str2 = "Active Win Streak," + longestActiveWinStreak.getStreakLength() + "," + longestActiveWinStreak.getTeam() + "," + longestActiveWinStreak.getStartYear() + "-" + longestActiveWinStreak.getEndYear() + "\n";
    return str1 + str2 + leagueRecords.getRecordsStr();
  }
  
  public ArrayList<Player> getMVP(boolean paramBoolean)
  {
    mvp = null;
    int i = 0;
    ArrayList localArrayList = new ArrayList();
    int j = 0;
    while (j < teamList.size())
    {
      int m;
      if ((paramBoolean) && (!playoffsAM.contains(teamList.get(j))))
      {
        m = i;
        if (!playoffsNA.contains(teamList.get(j))) {}
      }
      else
      {
        int k = 0;
        int n;
        while (k < teamList.get(j)).teamQBs.size())
        {
          localArrayList.add(teamList.get(j)).teamQBs.get(k));
          n = ((PlayerQB)teamList.get(j)).teamQBs.get(k)).getMVPScore() + teamList.get(j)).wins * 200;
          m = i;
          if (n > i)
          {
            mvp = ((Player)teamList.get(j)).teamQBs.get(k));
            m = n;
          }
          k += 1;
          i = m;
        }
        k = 0;
        while (k < teamList.get(j)).teamRBs.size())
        {
          localArrayList.add(teamList.get(j)).teamRBs.get(k));
          n = ((PlayerRB)teamList.get(j)).teamRBs.get(k)).getMVPScore() + teamList.get(j)).wins * 200;
          m = i;
          if (n > i)
          {
            mvp = ((Player)teamList.get(j)).teamRBs.get(k));
            m = n;
          }
          k += 1;
          i = m;
        }
        k = 0;
        for (;;)
        {
          m = i;
          if (k >= teamList.get(j)).teamWRs.size()) {
            break;
          }
          localArrayList.add(teamList.get(j)).teamWRs.get(k));
          n = ((PlayerWR)teamList.get(j)).teamWRs.get(k)).getMVPScore() + teamList.get(j)).wins * 200;
          m = i;
          if (n > i)
          {
            mvp = ((Player)teamList.get(j)).teamWRs.get(k));
            m = n;
          }
          k += 1;
          i = m;
        }
      }
      j += 1;
      i = m;
    }
    Collections.sort(localArrayList, new PlayerMVPComp());
    return localArrayList;
  }
  
  public String getMVPCeremonyStr()
  {
    if (!mvpDecided)
    {
      mvpDecided = true;
      mvpCandidates = getMVP(true);
      mvp = ((Player)mvpCandidates.get(0));
      mvp.wonMVP = true;
      mvpHistory.add(mvp.position + " " + mvp.getInitialName() + " [" + mvp.age + "], " + mvp.team.abbr + " (" + mvp.team.wins + "-" + mvp.team.losses + ")");
      Object localObject2 = "\n";
      int i = 0;
      if (i < 5)
      {
        Player localPlayer = (Player)mvpCandidates.get(i);
        str = (String)localObject2 + (i + 1) + ". " + team.abbr + "(" + team.wins + "-" + team.losses + ") - ";
        if ((localPlayer instanceof PlayerQB))
        {
          localObject1 = (PlayerQB)localPlayer;
          localObject1 = str + " QB " + ((PlayerQB)localObject1).getInitialName() + ": " + localPlayer.getMVPScore() + " votes\n\t(" + statsTD + " TDs, " + statsInt + " Int, " + statsPassYards + " Yds)\n\n";
        }
        for (;;)
        {
          i += 1;
          localObject2 = localObject1;
          break;
          if ((localPlayer instanceof PlayerRB))
          {
            localObject1 = (PlayerRB)localPlayer;
            localObject1 = str + " RB " + ((PlayerRB)localObject1).getInitialName() + ": " + localPlayer.getMVPScore() + " votes\n\t(" + statsTD + " TDs, " + statsFumbles + " Fum, " + statsRushYards + " Yds)\n\n";
          }
          else
          {
            localObject1 = str;
            if ((localPlayer instanceof PlayerWR))
            {
              localObject1 = (PlayerWR)localPlayer;
              localObject1 = str + " WR " + ((PlayerWR)localObject1).getInitialName() + ": " + localPlayer.getMVPScore() + " votes\n\t(" + statsTD + " TDs, " + statsFumbles + " Fum, " + statsRecYards + " Yds)\n\n";
            }
          }
        }
      }
      Object localObject1 = "";
      String str = "";
      if ((mvp instanceof PlayerQB))
      {
        localObject1 = (PlayerQB)mvp;
        str = "Congratulations to the Most Valuable Player, " + team.abbr + " QB " + name + " [" + mvp.age + "], who had " + statsTD + " TDs, just " + statsInt + " interceptions, and " + statsPassYards + " passing yards. He led " + team.name + " to a " + team.wins + "-" + team.losses + " record and a #" + team.rankTeamPollScore + " power ranking.";
        localObject1 = str + "\n\nFull Results:" + (String)localObject2;
      }
      for (;;)
      {
        if (1 != 0)
        {
          ((ArrayList)newsStories.get(13)).add(mvp.name + " is the Most Valuable Player!>" + str);
          mvpWinnerStrFull = ((String)localObject1);
        }
        return (String)localObject1;
        if ((mvp instanceof PlayerRB))
        {
          localObject1 = (PlayerRB)mvp;
          str = "Congratulations to the Most Valuable Player, " + team.abbr + " RB " + name + " [" + mvp.age + "], who had " + statsTD + " TDs, just " + statsFumbles + " fumbles, and " + statsRushYards + " rushing yards. He led " + team.name + " to a " + team.wins + "-" + team.losses + " record and a #" + team.rankTeamPollScore + " power ranking.";
          localObject1 = str + "\n\nFull Results:" + (String)localObject2;
        }
        else if ((mvp instanceof PlayerWR))
        {
          localObject1 = (PlayerWR)mvp;
          str = "Congratulations to the Most Valuable Player, " + team.abbr + " WR " + name + " [" + mvp.age + "], who had " + statsTD + " TDs, just " + statsFumbles + " fumbles, and " + statsRecYards + " receiving yards. He led " + team.name + " to a " + team.wins + "-" + team.losses + " record and a #" + team.rankTeamPollScore + " power ranking.";
          localObject1 = str + "\n\nFull Results:" + (String)localObject2;
        }
      }
    }
    return mvpWinnerStrFull;
  }
  
  public void getPlayersLeaving()
  {
    Iterator localIterator = teamList.iterator();
    while (localIterator.hasNext()) {
      ((Team)localIterator.next()).getPlayersLeaving();
    }
  }
  
  public ArrayList<String> getPlayoffPicture()
  {
    Object localObject2 = new ArrayList();
    ArrayList localArrayList = new ArrayList();
    Object localObject3 = new ArrayList();
    Object localObject1;
    if ((playoffsAM.isEmpty()) && (playoffsNA.isEmpty()))
    {
      Object localObject4 = new ArrayList();
      localObject1 = new ArrayList();
      i = 0;
      if (i < divisions.size())
      {
        ((ArrayList)localObject2).addAll(divisions.get(i)).divTeams);
        Collections.sort((List)localObject2, new TeamCompDivision());
        if (i < 4)
        {
          localArrayList.add(((ArrayList)localObject2).get(0));
          ((ArrayList)localObject4).addAll((Collection)localObject2);
        }
        for (;;)
        {
          ((ArrayList)localObject2).clear();
          i += 1;
          break;
          ((ArrayList)localObject3).add(((ArrayList)localObject2).get(0));
          ((ArrayList)localObject1).addAll((Collection)localObject2);
        }
      }
      Collections.sort(localArrayList, new TeamCompDivision());
      Collections.sort((List)localObject3, new TeamCompDivision());
      Collections.sort((List)localObject4, new TeamCompDivision());
      i = 0;
      localObject2 = ((ArrayList)localObject4).iterator();
      int j;
      do
      {
        if (!((Iterator)localObject2).hasNext()) {
          break;
        }
        localObject4 = (Team)((Iterator)localObject2).next();
        j = i;
        if (!localArrayList.contains(localObject4))
        {
          localArrayList.add(localObject4);
          j = i + 1;
        }
        i = j;
      } while (j < 2);
      Collections.sort((List)localObject1, new TeamCompDivision());
      i = 0;
      localObject4 = ((ArrayList)localObject1).iterator();
      do
      {
        localObject1 = localArrayList;
        localObject2 = localObject3;
        if (!((Iterator)localObject4).hasNext()) {
          break;
        }
        localObject1 = (Team)((Iterator)localObject4).next();
        j = i;
        if (!((ArrayList)localObject3).contains(localObject1))
        {
          ((ArrayList)localObject3).add(localObject1);
          j = i + 1;
        }
        i = j;
      } while (j < 2);
      localObject2 = localObject3;
      localObject1 = localArrayList;
      localArrayList = new ArrayList();
      localArrayList.add(" ,American Conference, ");
      i = 0;
      label369:
      if (i >= ((ArrayList)localObject1).size()) {
        break label522;
      }
      localObject3 = (Team)((ArrayList)localObject1).get(i);
      if (i >= 4) {
        break label472;
      }
      localArrayList.add(((Team)localObject3).getRankStrStarUser(i + 1) + "," + ((Team)localObject3).strRepWithBowlResults() + "," + division.substring(2));
    }
    for (;;)
    {
      i += 1;
      break label369;
      localObject2 = playoffsNA;
      localObject1 = playoffsAM;
      break;
      label472:
      localArrayList.add(((Team)localObject3).getRankStrStarUser(i + 1) + "," + ((Team)localObject3).strRepWithBowlResults() + ",WC");
    }
    label522:
    localArrayList.add(" ,National Conference, ");
    int i = 0;
    if (i < ((ArrayList)localObject2).size())
    {
      localObject1 = (Team)((ArrayList)localObject2).get(i);
      if (i < 4) {
        localArrayList.add(((Team)localObject1).getRankStrStarUser(i + 1) + "," + ((Team)localObject1).strRepWithBowlResults() + "," + division.substring(2));
      }
      for (;;)
      {
        i += 1;
        break;
        localArrayList.add(((Team)localObject1).getRankStrStarUser(i + 1) + "," + ((Team)localObject1).strRepWithBowlResults() + ",WC");
      }
    }
    return localArrayList;
  }
  
  public String getRandName()
  {
    if (Math.random() > 0.0025D)
    {
      int i = (int)(Math.random() * nameList.size());
      int j = (int)(Math.random() * lastNameList.size());
      return (String)nameList.get(i) + " " + (String)lastNameList.get(j);
    }
    return donationNames[((int)(Math.random() * donationNames.length))];
  }
  
  public String[] getTeamListStr()
  {
    String[] arrayOfString = new String[teamList.size()];
    int i = 0;
    while (i < teamList.size())
    {
      arrayOfString[i] = (teamList.get(i)).division + ": " + teamList.get(i)).name);
      i += 1;
    }
    return arrayOfString;
  }
  
  public ArrayList<String> getTeamRankingsStr(int paramInt)
  {
    ArrayList localArrayList2 = teamList;
    ArrayList localArrayList1 = new ArrayList();
    Object localObject;
    switch (paramInt)
    {
    default: 
      Collections.sort(localArrayList2, new TeamCompPoll());
      paramInt = 0;
      for (;;)
      {
        localObject = localArrayList1;
        if (paramInt >= localArrayList2.size()) {
          break;
        }
        localObject = (Team)localArrayList2.get(paramInt);
        localArrayList1.add(((Team)localObject).getRankStrStarUser(paramInt + 1) + "," + ((Team)localObject).strRepWithBowlResults() + "," + teamPollScore);
        paramInt += 1;
      }
    case 0: 
      Collections.sort(localArrayList2, new TeamCompPoll());
      paramInt = 0;
      for (;;)
      {
        localObject = localArrayList1;
        if (paramInt >= localArrayList2.size()) {
          break;
        }
        localObject = (Team)localArrayList2.get(paramInt);
        localArrayList1.add(((Team)localObject).getRankStrStarUser(paramInt + 1) + "," + ((Team)localObject).strRepWithBowlResults() + "," + teamPollScore);
        paramInt += 1;
      }
    case 1: 
      localObject = getPlayoffPicture();
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 9: 
    case 10: 
    case 11: 
    case 12: 
      do
      {
        return (ArrayList<String>)localObject;
        return getDivStandings();
        Collections.sort(localArrayList2, new TeamCompSoW());
        paramInt = 0;
        for (;;)
        {
          localObject = localArrayList1;
          if (paramInt >= localArrayList2.size()) {
            break;
          }
          localObject = (Team)localArrayList2.get(paramInt);
          localArrayList1.add(((Team)localObject).getRankStrStarUser(paramInt + 1) + "," + ((Team)localObject).strRepWithBowlResults() + "," + teamStrengthOfWins);
          paramInt += 1;
        }
        Collections.sort(localArrayList2, new TeamCompPPG());
        paramInt = 0;
        for (;;)
        {
          localObject = localArrayList1;
          if (paramInt >= localArrayList2.size()) {
            break;
          }
          localObject = (Team)localArrayList2.get(paramInt);
          localArrayList1.add(((Team)localObject).getRankStrStarUser(paramInt + 1) + "," + ((Team)localObject).strRepWithBowlResults() + "," + teamPoints / ((Team)localObject).numGames());
          paramInt += 1;
        }
        Collections.sort(localArrayList2, new TeamCompOPPG());
        paramInt = 0;
        for (;;)
        {
          localObject = localArrayList1;
          if (paramInt >= localArrayList2.size()) {
            break;
          }
          localObject = (Team)localArrayList2.get(paramInt);
          localArrayList1.add(((Team)localObject).getRankStrStarUser(paramInt + 1) + "," + ((Team)localObject).strRepWithBowlResults() + "," + teamOppPoints / ((Team)localObject).numGames());
          paramInt += 1;
        }
        Collections.sort(localArrayList2, new TeamCompYPG());
        paramInt = 0;
        for (;;)
        {
          localObject = localArrayList1;
          if (paramInt >= localArrayList2.size()) {
            break;
          }
          localObject = (Team)localArrayList2.get(paramInt);
          localArrayList1.add(((Team)localObject).getRankStrStarUser(paramInt + 1) + "," + ((Team)localObject).strRepWithBowlResults() + "," + teamYards / ((Team)localObject).numGames());
          paramInt += 1;
        }
        Collections.sort(localArrayList2, new TeamCompOYPG());
        paramInt = 0;
        for (;;)
        {
          localObject = localArrayList1;
          if (paramInt >= localArrayList2.size()) {
            break;
          }
          localObject = (Team)localArrayList2.get(paramInt);
          localArrayList1.add(((Team)localObject).getRankStrStarUser(paramInt + 1) + "," + ((Team)localObject).strRepWithBowlResults() + "," + teamOppYards / ((Team)localObject).numGames());
          paramInt += 1;
        }
        Collections.sort(localArrayList2, new TeamCompPYPG());
        paramInt = 0;
        for (;;)
        {
          localObject = localArrayList1;
          if (paramInt >= localArrayList2.size()) {
            break;
          }
          localObject = (Team)localArrayList2.get(paramInt);
          localArrayList1.add(((Team)localObject).getRankStrStarUser(paramInt + 1) + "," + ((Team)localObject).strRepWithBowlResults() + "," + teamPassYards / ((Team)localObject).numGames());
          paramInt += 1;
        }
        Collections.sort(localArrayList2, new TeamCompRYPG());
        paramInt = 0;
        for (;;)
        {
          localObject = localArrayList1;
          if (paramInt >= localArrayList2.size()) {
            break;
          }
          localObject = (Team)localArrayList2.get(paramInt);
          localArrayList1.add(((Team)localObject).getRankStrStarUser(paramInt + 1) + "," + ((Team)localObject).strRepWithBowlResults() + "," + teamRushYards / ((Team)localObject).numGames());
          paramInt += 1;
        }
        Collections.sort(localArrayList2, new TeamCompOPYPG());
        paramInt = 0;
        for (;;)
        {
          localObject = localArrayList1;
          if (paramInt >= localArrayList2.size()) {
            break;
          }
          localObject = (Team)localArrayList2.get(paramInt);
          localArrayList1.add(((Team)localObject).getRankStrStarUser(paramInt + 1) + "," + ((Team)localObject).strRepWithBowlResults() + "," + teamOppPassYards / ((Team)localObject).numGames());
          paramInt += 1;
        }
        Collections.sort(localArrayList2, new TeamCompORYPG());
        paramInt = 0;
        for (;;)
        {
          localObject = localArrayList1;
          if (paramInt >= localArrayList2.size()) {
            break;
          }
          localObject = (Team)localArrayList2.get(paramInt);
          localArrayList1.add(((Team)localObject).getRankStrStarUser(paramInt + 1) + "," + ((Team)localObject).strRepWithBowlResults() + "," + teamOppRushYards / ((Team)localObject).numGames());
          paramInt += 1;
        }
        Collections.sort(localArrayList2, new TeamCompTODiff());
        paramInt = 0;
        localObject = localArrayList1;
      } while (paramInt >= localArrayList2.size());
      localObject = (Team)localArrayList2.get(paramInt);
      if (teamTODiff > 0) {
        localArrayList1.add(((Team)localObject).getRankStrStarUser(paramInt + 1) + "," + ((Team)localObject).strRepWithBowlResults() + ",+" + teamTODiff);
      }
      for (;;)
      {
        paramInt += 1;
        break;
        localArrayList1.add(((Team)localObject).getRankStrStarUser(paramInt + 1) + "," + ((Team)localObject).strRepWithBowlResults() + "," + teamTODiff);
      }
    case 13: 
      Collections.sort(localArrayList2, new TeamCompOffTalent());
      paramInt = 0;
      for (;;)
      {
        localObject = localArrayList1;
        if (paramInt >= localArrayList2.size()) {
          break;
        }
        localObject = (Team)localArrayList2.get(paramInt);
        localArrayList1.add(((Team)localObject).getRankStrStarUser(paramInt + 1) + "," + ((Team)localObject).strRepWithBowlResults() + "," + teamOffTalent);
        paramInt += 1;
      }
    case 14: 
      Collections.sort(localArrayList2, new TeamCompDefTalent());
      paramInt = 0;
      for (;;)
      {
        localObject = localArrayList1;
        if (paramInt >= localArrayList2.size()) {
          break;
        }
        localObject = (Team)localArrayList2.get(paramInt);
        localArrayList1.add(((Team)localObject).getRankStrStarUser(paramInt + 1) + "," + ((Team)localObject).strRepWithBowlResults() + "," + teamDefTalent);
        paramInt += 1;
      }
    }
    Collections.sort(localArrayList2, new TeamCompCapRoom());
    paramInt = 0;
    for (;;)
    {
      localObject = localArrayList1;
      if (paramInt >= localArrayList2.size()) {
        break;
      }
      localObject = (Team)localArrayList2.get(paramInt);
      localArrayList1.add(((Team)localObject).getRankStrStarUser(paramInt + 1) + "," + ((Team)localObject).strRepWithBowlResults() + ",$" + ((Team)localObject).getSalaryCapRoom());
      paramInt += 1;
    }
  }
  
  public String getTop5MVPStr()
  {
    if (mvpDecided) {
      localObject2 = getMVPCeremonyStr();
    }
    ArrayList localArrayList;
    Object localObject1;
    int i;
    do
    {
      return (String)localObject2;
      localArrayList = getMVP(false);
      localObject1 = "";
      i = 0;
      localObject2 = localObject1;
    } while (i >= 5);
    Player localPlayer = (Player)localArrayList.get(i);
    Object localObject2 = (String)localObject1 + (i + 1) + ". " + team.abbr + "(" + team.wins + "-" + team.losses + ") - ";
    if ((localPlayer instanceof PlayerQB))
    {
      localObject1 = (PlayerQB)localPlayer;
      localObject1 = (String)localObject2 + " QB " + name + " [" + age + "]\n \t\t(" + statsTD + " TDs, " + statsInt + " Int, " + statsPassYards + " Yds)\n\n";
    }
    for (;;)
    {
      i += 1;
      break;
      if ((localPlayer instanceof PlayerRB))
      {
        localObject1 = (PlayerRB)localPlayer;
        localObject1 = (String)localObject2 + " RB " + name + " [" + age + "]\n \t\t(" + statsTD + " TDs, " + statsFumbles + " Fum, " + statsRushYards + " Yds)\n\n";
      }
      else
      {
        localObject1 = localObject2;
        if ((localPlayer instanceof PlayerWR))
        {
          localObject1 = (PlayerWR)localPlayer;
          localObject1 = (String)localObject2 + " WR " + name + " [" + age + "]\n \t\t(" + statsTD + " TDs, " + statsFumbles + " Fum, " + statsRecYards + " Yds)\n\n";
        }
      }
    }
  }
  
  public int getYear()
  {
    return leagueHistory.size() + 2016;
  }
  
  public boolean isAbbrValid(String paramString)
  {
    if ((paramString.length() > 3) || (paramString.length() == 0)) {
      return false;
    }
    if ((paramString.contains(",")) || (paramString.contains(">")) || (paramString.contains("%")) || (paramString.contains("\\")) || (paramString.contains(" "))) {
      return false;
    }
    int i = 0;
    while (i < teamList.size())
    {
      if ((teamList.get(i)).abbr.equals(paramString)) && (!teamList.get(i)).userControlled)) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public boolean isAbbrValidOnline(String paramString)
  {
    if ((paramString.length() == 0) || (paramString.length() > 5)) {}
    while ((paramString.contains(",")) || (paramString.contains(">")) || (paramString.contains("%")) || (paramString.contains("\\")) || (paramString.contains(";")) || (paramString.contains("'")) || (paramString.contains("\""))) {
      return false;
    }
    return true;
  }
  
  public boolean isNameValid(String paramString)
  {
    if (paramString.length() == 0) {
      return false;
    }
    if ((paramString.contains(",")) || (paramString.contains(">")) || (paramString.contains("%")) || (paramString.contains("\\"))) {
      return false;
    }
    int i = 0;
    while (i < teamList.size())
    {
      if ((teamList.get(i)).name.toLowerCase().equals(paramString.toLowerCase())) && (!teamList.get(i)).userControlled)) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public boolean isNameValidOnline(String paramString)
  {
    if ((paramString.length() == 0) || (paramString.length() > 20)) {}
    while ((paramString.contains(",")) || (paramString.contains(">")) || (paramString.contains("%")) || (paramString.contains("\\")) || (paramString.contains(";")) || (paramString.contains("'")) || (paramString.contains("\""))) {
      return false;
    }
    return true;
  }
  
  public String ncgSummaryStr()
  {
    Game localGame = playoffGames[10];
    if (homeScore > awayScore) {
      return homeTeam.name + " (" + homeTeam.wins + "-" + homeTeam.losses + ") won the Champions Bowl, beating the " + awayTeam.name + " (" + awayTeam.wins + "-" + awayTeam.losses + ") by a score of " + homeScore + "-" + awayScore + ".";
    }
    return awayTeam.name + " (" + awayTeam.wins + "-" + awayTeam.losses + ") won the Champions Bowl, beating the " + homeTeam.name + " (" + homeTeam.wins + "-" + homeTeam.losses + ") by a score of " + awayScore + "-" + homeScore + ".";
  }
  
  public void playWeek()
  {
    if (currentWeek < 16)
    {
      Iterator localIterator = teamList.iterator();
      while (localIterator.hasNext())
      {
        Team localTeam = (Team)localIterator.next();
        if ((gameSchedule.size() > currentWeek) && (!gameSchedule.get(currentWeek)).hasPlayed)) {
          ((Game)gameSchedule.get(currentWeek)).playGame();
        }
      }
      setTeamRanks();
      updateLongestActiveWinStreak();
    }
    if (currentWeek >= 15)
    {
      setUpPlayoffs(currentWeek - 14);
      setTeamRanks();
      updateLongestActiveWinStreak();
    }
    currentWeek += 1;
  }
  
  public void prepareForDraft()
  {
    Collections.sort(teamList, new TeamCompLeastWins());
    Iterator localIterator = teamList.iterator();
    while (localIterator.hasNext()) {
      Collections.sort(nextpositionalDraftPicks, new DraftPickComparatorYearRound());
    }
  }
  
  public void resetForNewSeason()
  {
    mvpDecided = false;
    playoffGames = new Game[11];
    currentWeek = 0;
    allPros = new ArrayList();
    playoffsAM = new ArrayList();
    playoffsNA = new ArrayList();
    freeAgents = new ArrayList();
    mvpWinnerStrFull = null;
    Iterator localIterator = teamList.iterator();
    Team localTeam;
    while (localIterator.hasNext())
    {
      localTeam = (Team)localIterator.next();
      localTeam.signUDFAs();
      System.out.println(abbr + " salary cap room: " + localTeam.getSalaryCapRoom());
    }
    int i = 0;
    while (i < divisions.size())
    {
      divisions.get(i)).robinWeek = 0;
      divisions.get(i)).week = 0;
      i += 1;
    }
    localIterator = teamList.iterator();
    while (localIterator.hasNext())
    {
      localTeam = (Team)localIterator.next();
      wins = 0;
      losses = 0;
      i = 0;
      while (i < 7)
      {
        draftPicks.add(new DraftPick(getYear() + 1, i + 1, localTeam, localTeam));
        i += 1;
      }
    }
    setUpSchedule();
  }
  
  /* Error */
  public boolean saveLeague(File paramFile1, File paramFile2, File paramFile3, File paramFile4, boolean paramBoolean)
  {
    // Byte code:
    //   0: new 163	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   7: astore 9
    //   9: aload 9
    //   11: new 163	java/lang/StringBuilder
    //   14: dup
    //   15: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   18: aload_0
    //   19: getfield 216	PFCpack/League:leagueHistory	Ljava/util/ArrayList;
    //   22: invokevirtual 463	java/util/ArrayList:size	()I
    //   25: sipush 2016
    //   28: iadd
    //   29: invokevirtual 404	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   32: ldc_w 1155
    //   35: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: aload_0
    //   39: getfield 89	PFCpack/League:currentWeek	I
    //   42: invokevirtual 404	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   45: ldc_w 1157
    //   48: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: aload_0
    //   52: getfield 209	PFCpack/League:userTeam	LPFCpack/Team;
    //   55: getfield 409	PFCpack/Team:abbr	Ljava/lang/String;
    //   58: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   61: ldc_w 932
    //   64: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: aload_0
    //   68: getfield 209	PFCpack/League:userTeam	LPFCpack/Team;
    //   71: getfield 744	PFCpack/Team:wins	I
    //   74: invokevirtual 404	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   77: ldc_w 746
    //   80: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: aload_0
    //   84: getfield 209	PFCpack/League:userTeam	LPFCpack/Team;
    //   87: getfield 749	PFCpack/Team:losses	I
    //   90: invokevirtual 404	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   93: ldc_w 1159
    //   96: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: aload_0
    //   100: getfield 209	PFCpack/League:userTeam	LPFCpack/Team;
    //   103: getfield 1162	PFCpack/Team:totalDivChamps	I
    //   106: invokevirtual 404	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   109: ldc_w 1164
    //   112: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   115: aload_0
    //   116: getfield 209	PFCpack/League:userTeam	LPFCpack/Team;
    //   119: getfield 1167	PFCpack/Team:totalSuperBowlWins	I
    //   122: invokevirtual 404	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   125: ldc_w 1169
    //   128: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   131: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   134: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   137: pop
    //   138: iconst_0
    //   139: istore 6
    //   141: iload 6
    //   143: aload_0
    //   144: getfield 216	PFCpack/League:leagueHistory	Ljava/util/ArrayList;
    //   147: invokevirtual 463	java/util/ArrayList:size	()I
    //   150: if_icmpge +90 -> 240
    //   153: iconst_0
    //   154: istore 7
    //   156: iload 7
    //   158: aload_0
    //   159: getfield 216	PFCpack/League:leagueHistory	Ljava/util/ArrayList;
    //   162: iload 6
    //   164: invokevirtual 263	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   167: checkcast 499	[Ljava/lang/String;
    //   170: arraylength
    //   171: if_icmpge +51 -> 222
    //   174: aload 9
    //   176: new 163	java/lang/StringBuilder
    //   179: dup
    //   180: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   183: aload_0
    //   184: getfield 216	PFCpack/League:leagueHistory	Ljava/util/ArrayList;
    //   187: iload 6
    //   189: invokevirtual 263	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   192: checkcast 499	[Ljava/lang/String;
    //   195: iload 7
    //   197: aaload
    //   198: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   201: ldc -36
    //   203: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   206: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   209: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   212: pop
    //   213: iload 7
    //   215: iconst_1
    //   216: iadd
    //   217: istore 7
    //   219: goto -63 -> 156
    //   222: aload 9
    //   224: ldc_w 310
    //   227: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   230: pop
    //   231: iload 6
    //   233: iconst_1
    //   234: iadd
    //   235: istore 6
    //   237: goto -96 -> 141
    //   240: aload 9
    //   242: ldc_w 1171
    //   245: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   248: pop
    //   249: iconst_0
    //   250: istore 6
    //   252: iload 6
    //   254: aload_0
    //   255: getfield 286	PFCpack/League:mvpHistory	Ljava/util/ArrayList;
    //   258: invokevirtual 463	java/util/ArrayList:size	()I
    //   261: if_icmpge +49 -> 310
    //   264: aload 9
    //   266: new 163	java/lang/StringBuilder
    //   269: dup
    //   270: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   273: aload_0
    //   274: getfield 286	PFCpack/League:mvpHistory	Ljava/util/ArrayList;
    //   277: iload 6
    //   279: invokevirtual 263	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   282: checkcast 54	java/lang/String
    //   285: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   288: ldc_w 310
    //   291: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   294: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   297: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   300: pop
    //   301: iload 6
    //   303: iconst_1
    //   304: iadd
    //   305: istore 6
    //   307: goto -55 -> 252
    //   310: aload 9
    //   312: ldc_w 1173
    //   315: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   318: pop
    //   319: aload 9
    //   321: aload_0
    //   322: getfield 94	PFCpack/League:leagueRecords	LPFCpack/LeagueRecords;
    //   325: invokevirtual 902	PFCpack/LeagueRecords:getRecordsStr	()Ljava/lang/String;
    //   328: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   331: pop
    //   332: aload 9
    //   334: ldc_w 1175
    //   337: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   340: pop
    //   341: aload 9
    //   343: aload_0
    //   344: getfield 105	PFCpack/League:longestWinStreak	LPFCpack/TeamStreak;
    //   347: invokevirtual 1178	PFCpack/TeamStreak:getStreakCSV	()Ljava/lang/String;
    //   350: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   353: pop
    //   354: aload 9
    //   356: ldc_w 1180
    //   359: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   362: pop
    //   363: aload 9
    //   365: aload_0
    //   366: getfield 96	PFCpack/League:userTeamRecords	LPFCpack/LeagueRecords;
    //   369: invokevirtual 902	PFCpack/LeagueRecords:getRecordsStr	()Ljava/lang/String;
    //   372: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   375: pop
    //   376: aload 9
    //   378: ldc_w 1182
    //   381: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   384: pop
    //   385: aload_0
    //   386: getfield 304	PFCpack/League:hallOfFame	Ljava/util/ArrayList;
    //   389: invokevirtual 386	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   392: astore 10
    //   394: aload 10
    //   396: invokeinterface 392 1 0
    //   401: ifeq +45 -> 446
    //   404: aload 10
    //   406: invokeinterface 396 1 0
    //   411: checkcast 54	java/lang/String
    //   414: astore 8
    //   416: aload 9
    //   418: new 163	java/lang/StringBuilder
    //   421: dup
    //   422: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   425: aload 8
    //   427: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   430: ldc_w 310
    //   433: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   436: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   439: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   442: pop
    //   443: goto -49 -> 394
    //   446: aload 9
    //   448: ldc_w 1184
    //   451: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   454: pop
    //   455: aload_0
    //   456: getfield 143	PFCpack/League:allPros	Ljava/util/ArrayList;
    //   459: invokevirtual 826	java/util/ArrayList:isEmpty	()Z
    //   462: ifne +32 -> 494
    //   465: aload 9
    //   467: new 163	java/lang/StringBuilder
    //   470: dup
    //   471: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   474: aload_0
    //   475: invokevirtual 1186	PFCpack/League:getAllProStr	()Ljava/lang/String;
    //   478: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   481: ldc_w 310
    //   484: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   487: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   490: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   493: pop
    //   494: aload 9
    //   496: ldc_w 1188
    //   499: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   502: pop
    //   503: aload_0
    //   504: getfield 316	PFCpack/League:mvpWinnerStrFull	Ljava/lang/String;
    //   507: ifnull +13 -> 520
    //   510: aload 9
    //   512: aload_0
    //   513: getfield 316	PFCpack/League:mvpWinnerStrFull	Ljava/lang/String;
    //   516: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   519: pop
    //   520: aload 9
    //   522: ldc_w 1190
    //   525: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   528: pop
    //   529: aload_0
    //   530: getfield 89	PFCpack/League:currentWeek	I
    //   533: bipush 16
    //   535: if_icmplt +131 -> 666
    //   538: aload_0
    //   539: getfield 145	PFCpack/League:playoffsAM	Ljava/util/ArrayList;
    //   542: invokevirtual 386	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   545: astore 8
    //   547: aload 8
    //   549: invokeinterface 392 1 0
    //   554: ifeq +48 -> 602
    //   557: aload 8
    //   559: invokeinterface 396 1 0
    //   564: checkcast 211	PFCpack/Team
    //   567: astore 10
    //   569: aload 9
    //   571: new 163	java/lang/StringBuilder
    //   574: dup
    //   575: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   578: aload 10
    //   580: getfield 409	PFCpack/Team:abbr	Ljava/lang/String;
    //   583: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   586: ldc_w 310
    //   589: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   592: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   595: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   598: pop
    //   599: goto -52 -> 547
    //   602: aload_0
    //   603: getfield 147	PFCpack/League:playoffsNA	Ljava/util/ArrayList;
    //   606: invokevirtual 386	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   609: astore 8
    //   611: aload 8
    //   613: invokeinterface 392 1 0
    //   618: ifeq +48 -> 666
    //   621: aload 8
    //   623: invokeinterface 396 1 0
    //   628: checkcast 211	PFCpack/Team
    //   631: astore 10
    //   633: aload 9
    //   635: new 163	java/lang/StringBuilder
    //   638: dup
    //   639: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   642: aload 10
    //   644: getfield 409	PFCpack/Team:abbr	Ljava/lang/String;
    //   647: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   650: ldc_w 310
    //   653: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   656: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   659: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   662: pop
    //   663: goto -52 -> 611
    //   666: aload 9
    //   668: ldc_w 1192
    //   671: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   674: pop
    //   675: aload_0
    //   676: getfield 149	PFCpack/League:freeAgents	Ljava/util/ArrayList;
    //   679: invokevirtual 386	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   682: astore 10
    //   684: aload 10
    //   686: invokeinterface 392 1 0
    //   691: ifeq +78 -> 769
    //   694: aload 10
    //   696: invokeinterface 396 1 0
    //   701: checkcast 510	PFCpack/Player
    //   704: astore 8
    //   706: aload 9
    //   708: new 163	java/lang/StringBuilder
    //   711: dup
    //   712: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   715: aload 8
    //   717: invokevirtual 1195	PFCpack/Player:getCSV	()Ljava/lang/String;
    //   720: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   723: ldc_w 310
    //   726: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   729: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   732: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   735: pop
    //   736: getstatic 183	java/lang/System:out	Ljava/io/PrintStream;
    //   739: new 163	java/lang/StringBuilder
    //   742: dup
    //   743: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   746: ldc_w 1197
    //   749: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   752: aload 8
    //   754: invokevirtual 1195	PFCpack/Player:getCSV	()Ljava/lang/String;
    //   757: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   760: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   763: invokevirtual 191	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   766: goto -82 -> 684
    //   769: aload 9
    //   771: ldc_w 1199
    //   774: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   777: pop
    //   778: aload_0
    //   779: getfield 373	PFCpack/League:canGoOnline	Z
    //   782: ifeq +664 -> 1446
    //   785: aload 9
    //   787: ldc_w 1201
    //   790: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   793: pop
    //   794: aload 9
    //   796: ldc_w 1203
    //   799: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   802: pop
    //   803: new 576	java/io/BufferedWriter
    //   806: dup
    //   807: new 1205	java/io/OutputStreamWriter
    //   810: dup
    //   811: new 1207	java/io/FileOutputStream
    //   814: dup
    //   815: aload_1
    //   816: invokespecial 1208	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   819: ldc_w 1210
    //   822: invokespecial 1213	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;Ljava/lang/String;)V
    //   825: invokespecial 579	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   828: astore 10
    //   830: aconst_null
    //   831: astore 8
    //   833: aload 10
    //   835: aload 9
    //   837: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   840: invokevirtual 1218	java/io/Writer:write	(Ljava/lang/String;)V
    //   843: aload 10
    //   845: ifnull +12 -> 857
    //   848: iconst_0
    //   849: ifeq +632 -> 1481
    //   852: aload 10
    //   854: invokevirtual 1219	java/io/Writer:close	()V
    //   857: aload 9
    //   859: iconst_0
    //   860: invokevirtual 1222	java/lang/StringBuilder:setLength	(I)V
    //   863: new 564	java/io/PrintWriter
    //   866: dup
    //   867: aload_2
    //   868: invokespecial 565	java/io/PrintWriter:<init>	(Ljava/io/File;)V
    //   871: astore_1
    //   872: aload_1
    //   873: ldc_w 318
    //   876: invokevirtual 568	java/io/PrintWriter:print	(Ljava/lang/String;)V
    //   879: aload_1
    //   880: invokevirtual 569	java/io/PrintWriter:close	()V
    //   883: new 571	java/io/FileWriter
    //   886: dup
    //   887: aload_2
    //   888: iconst_1
    //   889: invokespecial 574	java/io/FileWriter:<init>	(Ljava/io/File;Z)V
    //   892: astore 8
    //   894: new 576	java/io/BufferedWriter
    //   897: dup
    //   898: aload 8
    //   900: invokespecial 579	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   903: astore 9
    //   905: new 564	java/io/PrintWriter
    //   908: dup
    //   909: aload 9
    //   911: invokespecial 580	java/io/PrintWriter:<init>	(Ljava/io/Writer;)V
    //   914: astore 10
    //   916: aconst_null
    //   917: astore_1
    //   918: aload_0
    //   919: getfield 116	PFCpack/League:teamList	Ljava/util/ArrayList;
    //   922: invokevirtual 386	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   925: astore_2
    //   926: aload_2
    //   927: invokeinterface 392 1 0
    //   932: ifeq +614 -> 1546
    //   935: aload_2
    //   936: invokeinterface 396 1 0
    //   941: checkcast 211	PFCpack/Team
    //   944: astore 11
    //   946: aload 10
    //   948: new 163	java/lang/StringBuilder
    //   951: dup
    //   952: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   955: aload 11
    //   957: invokevirtual 1223	PFCpack/Team:getCSV	()Ljava/lang/String;
    //   960: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   963: ldc_w 1225
    //   966: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   969: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   972: invokevirtual 568	java/io/PrintWriter:print	(Ljava/lang/String;)V
    //   975: aload 10
    //   977: aload 11
    //   979: iload 5
    //   981: invokevirtual 1229	PFCpack/Team:getPlayerInfoSaveFile	(Z)Ljava/lang/String;
    //   984: invokevirtual 568	java/io/PrintWriter:print	(Ljava/lang/String;)V
    //   987: aload 10
    //   989: ldc_w 593
    //   992: invokevirtual 568	java/io/PrintWriter:print	(Ljava/lang/String;)V
    //   995: goto -69 -> 926
    //   998: astore_1
    //   999: aload_1
    //   1000: athrow
    //   1001: astore_2
    //   1002: aload 10
    //   1004: ifnull +12 -> 1016
    //   1007: aload_1
    //   1008: ifnull +766 -> 1774
    //   1011: aload 10
    //   1013: invokevirtual 569	java/io/PrintWriter:close	()V
    //   1016: aload_2
    //   1017: athrow
    //   1018: astore_1
    //   1019: aload_1
    //   1020: athrow
    //   1021: astore_2
    //   1022: aload 9
    //   1024: ifnull +12 -> 1036
    //   1027: aload_1
    //   1028: ifnull +782 -> 1810
    //   1031: aload 9
    //   1033: invokevirtual 588	java/io/BufferedWriter:close	()V
    //   1036: aload_2
    //   1037: athrow
    //   1038: astore_1
    //   1039: aload_1
    //   1040: athrow
    //   1041: astore_2
    //   1042: aload 8
    //   1044: ifnull +12 -> 1056
    //   1047: aload_1
    //   1048: ifnull +789 -> 1837
    //   1051: aload 8
    //   1053: invokevirtual 589	java/io/FileWriter:close	()V
    //   1056: aload_2
    //   1057: athrow
    //   1058: astore_1
    //   1059: getstatic 183	java/lang/System:out	Ljava/io/PrintStream;
    //   1062: aload_1
    //   1063: invokevirtual 590	java/io/IOException:toString	()Ljava/lang/String;
    //   1066: invokevirtual 191	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   1069: new 564	java/io/PrintWriter
    //   1072: dup
    //   1073: aload 4
    //   1075: invokespecial 565	java/io/PrintWriter:<init>	(Ljava/io/File;)V
    //   1078: astore_1
    //   1079: aload_1
    //   1080: ldc_w 318
    //   1083: invokevirtual 568	java/io/PrintWriter:print	(Ljava/lang/String;)V
    //   1086: aload_1
    //   1087: invokevirtual 569	java/io/PrintWriter:close	()V
    //   1090: new 571	java/io/FileWriter
    //   1093: dup
    //   1094: aload 4
    //   1096: iconst_1
    //   1097: invokespecial 574	java/io/FileWriter:<init>	(Ljava/io/File;Z)V
    //   1100: astore 4
    //   1102: new 576	java/io/BufferedWriter
    //   1105: dup
    //   1106: aload 4
    //   1108: invokespecial 579	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   1111: astore 8
    //   1113: new 564	java/io/PrintWriter
    //   1116: dup
    //   1117: aload 8
    //   1119: invokespecial 580	java/io/PrintWriter:<init>	(Ljava/io/Writer;)V
    //   1122: astore 9
    //   1124: aconst_null
    //   1125: astore_1
    //   1126: aload_0
    //   1127: getfield 116	PFCpack/League:teamList	Ljava/util/ArrayList;
    //   1130: invokevirtual 386	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   1133: astore_2
    //   1134: aload_2
    //   1135: invokeinterface 392 1 0
    //   1140: ifeq +734 -> 1874
    //   1143: aload_2
    //   1144: invokeinterface 396 1 0
    //   1149: checkcast 211	PFCpack/Team
    //   1152: astore 10
    //   1154: aload 9
    //   1156: new 163	java/lang/StringBuilder
    //   1159: dup
    //   1160: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   1163: aload 10
    //   1165: getfield 409	PFCpack/Team:abbr	Ljava/lang/String;
    //   1168: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1171: ldc_w 310
    //   1174: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1177: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1180: invokevirtual 568	java/io/PrintWriter:print	(Ljava/lang/String;)V
    //   1183: aload 10
    //   1185: getfield 225	PFCpack/Team:teamHistory	Ljava/util/ArrayList;
    //   1188: invokevirtual 386	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   1191: astore 11
    //   1193: aload 11
    //   1195: invokeinterface 392 1 0
    //   1200: ifeq +659 -> 1859
    //   1203: aload 11
    //   1205: invokeinterface 396 1 0
    //   1210: checkcast 54	java/lang/String
    //   1213: astore 10
    //   1215: aload 9
    //   1217: new 163	java/lang/StringBuilder
    //   1220: dup
    //   1221: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   1224: aload 10
    //   1226: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1229: ldc_w 310
    //   1232: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1235: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1238: invokevirtual 568	java/io/PrintWriter:print	(Ljava/lang/String;)V
    //   1241: goto -48 -> 1193
    //   1244: astore_1
    //   1245: aload_1
    //   1246: athrow
    //   1247: astore_2
    //   1248: aload 9
    //   1250: ifnull +12 -> 1262
    //   1253: aload_1
    //   1254: ifnull +702 -> 1956
    //   1257: aload 9
    //   1259: invokevirtual 569	java/io/PrintWriter:close	()V
    //   1262: aload_2
    //   1263: athrow
    //   1264: astore_1
    //   1265: aload_1
    //   1266: athrow
    //   1267: astore_2
    //   1268: aload 8
    //   1270: ifnull +12 -> 1282
    //   1273: aload_1
    //   1274: ifnull +718 -> 1992
    //   1277: aload 8
    //   1279: invokevirtual 588	java/io/BufferedWriter:close	()V
    //   1282: aload_2
    //   1283: athrow
    //   1284: astore_1
    //   1285: aload_1
    //   1286: athrow
    //   1287: astore_2
    //   1288: aload 4
    //   1290: ifnull +12 -> 1302
    //   1293: aload_1
    //   1294: ifnull +725 -> 2019
    //   1297: aload 4
    //   1299: invokevirtual 589	java/io/FileWriter:close	()V
    //   1302: aload_2
    //   1303: athrow
    //   1304: astore_1
    //   1305: getstatic 183	java/lang/System:out	Ljava/io/PrintStream;
    //   1308: aload_1
    //   1309: invokevirtual 590	java/io/IOException:toString	()Ljava/lang/String;
    //   1312: invokevirtual 191	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   1315: new 163	java/lang/StringBuilder
    //   1318: dup
    //   1319: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   1322: astore 4
    //   1324: aload_0
    //   1325: getfield 116	PFCpack/League:teamList	Ljava/util/ArrayList;
    //   1328: invokevirtual 386	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   1331: astore_1
    //   1332: aload_1
    //   1333: invokeinterface 392 1 0
    //   1338: ifeq +722 -> 2060
    //   1341: aload_1
    //   1342: invokeinterface 396 1 0
    //   1347: checkcast 211	PFCpack/Team
    //   1350: astore_2
    //   1351: new 163	java/lang/StringBuilder
    //   1354: dup
    //   1355: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   1358: astore 8
    //   1360: aload 8
    //   1362: new 163	java/lang/StringBuilder
    //   1365: dup
    //   1366: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   1369: aload_2
    //   1370: getfield 409	PFCpack/Team:abbr	Ljava/lang/String;
    //   1373: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1376: ldc -27
    //   1378: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1381: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1384: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1387: pop
    //   1388: iconst_0
    //   1389: istore 6
    //   1391: iload 6
    //   1393: bipush 16
    //   1395: if_icmpge +632 -> 2027
    //   1398: aload 8
    //   1400: new 163	java/lang/StringBuilder
    //   1403: dup
    //   1404: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   1407: aload_2
    //   1408: getfield 412	PFCpack/Team:gameSchedule	Ljava/util/ArrayList;
    //   1411: iload 6
    //   1413: invokevirtual 263	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   1416: checkcast 85	PFCpack/Game
    //   1419: invokevirtual 1230	PFCpack/Game:getCSV	()Ljava/lang/String;
    //   1422: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1425: ldc -27
    //   1427: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1430: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1433: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1436: pop
    //   1437: iload 6
    //   1439: iconst_1
    //   1440: iadd
    //   1441: istore 6
    //   1443: goto -52 -> 1391
    //   1446: aload 9
    //   1448: ldc_w 1232
    //   1451: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1454: pop
    //   1455: goto -661 -> 794
    //   1458: astore_1
    //   1459: new 595	java/lang/NullPointerException
    //   1462: dup
    //   1463: invokespecial 596	java/lang/NullPointerException:<init>	()V
    //   1466: athrow
    //   1467: astore_1
    //   1468: getstatic 183	java/lang/System:out	Ljava/io/PrintStream;
    //   1471: aload_1
    //   1472: invokevirtual 591	java/lang/Exception:toString	()Ljava/lang/String;
    //   1475: invokevirtual 191	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   1478: goto -621 -> 857
    //   1481: aload 10
    //   1483: invokevirtual 1219	java/io/Writer:close	()V
    //   1486: goto -629 -> 857
    //   1489: astore 8
    //   1491: aload 8
    //   1493: athrow
    //   1494: astore_1
    //   1495: aload 10
    //   1497: ifnull +13 -> 1510
    //   1500: aload 8
    //   1502: ifnull +22 -> 1524
    //   1505: aload 10
    //   1507: invokevirtual 1219	java/io/Writer:close	()V
    //   1510: aload_1
    //   1511: athrow
    //   1512: astore 10
    //   1514: aload 8
    //   1516: aload 10
    //   1518: invokevirtual 600	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   1521: goto -11 -> 1510
    //   1524: aload 10
    //   1526: invokevirtual 1219	java/io/Writer:close	()V
    //   1529: goto -19 -> 1510
    //   1532: astore_1
    //   1533: getstatic 183	java/lang/System:out	Ljava/io/PrintStream;
    //   1536: aload_1
    //   1537: invokevirtual 591	java/lang/Exception:toString	()Ljava/lang/String;
    //   1540: invokevirtual 191	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   1543: goto -660 -> 883
    //   1546: aload 10
    //   1548: ldc_w 1234
    //   1551: invokevirtual 568	java/io/PrintWriter:print	(Ljava/lang/String;)V
    //   1554: aload_0
    //   1555: getfield 116	PFCpack/League:teamList	Ljava/util/ArrayList;
    //   1558: invokevirtual 386	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   1561: astore 11
    //   1563: aload 11
    //   1565: invokeinterface 392 1 0
    //   1570: ifeq +114 -> 1684
    //   1573: aload 11
    //   1575: invokeinterface 396 1 0
    //   1580: checkcast 211	PFCpack/Team
    //   1583: astore_2
    //   1584: aload 10
    //   1586: new 163	java/lang/StringBuilder
    //   1589: dup
    //   1590: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   1593: aload_2
    //   1594: getfield 409	PFCpack/Team:abbr	Ljava/lang/String;
    //   1597: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1600: ldc -27
    //   1602: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1605: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1608: invokevirtual 568	java/io/PrintWriter:print	(Ljava/lang/String;)V
    //   1611: iconst_0
    //   1612: istore 6
    //   1614: iload 6
    //   1616: aload_2
    //   1617: getfield 269	PFCpack/Team:draftPicks	Ljava/util/ArrayList;
    //   1620: invokevirtual 463	java/util/ArrayList:size	()I
    //   1623: if_icmpge +50 -> 1673
    //   1626: aload 10
    //   1628: new 163	java/lang/StringBuilder
    //   1631: dup
    //   1632: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   1635: aload_2
    //   1636: getfield 269	PFCpack/Team:draftPicks	Ljava/util/ArrayList;
    //   1639: iload 6
    //   1641: invokevirtual 263	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   1644: checkcast 276	PFCpack/DraftPick
    //   1647: invokevirtual 1235	PFCpack/DraftPick:getCSV	()Ljava/lang/String;
    //   1650: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1653: ldc -27
    //   1655: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1658: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1661: invokevirtual 568	java/io/PrintWriter:print	(Ljava/lang/String;)V
    //   1664: iload 6
    //   1666: iconst_1
    //   1667: iadd
    //   1668: istore 6
    //   1670: goto -56 -> 1614
    //   1673: aload 10
    //   1675: ldc_w 310
    //   1678: invokevirtual 568	java/io/PrintWriter:print	(Ljava/lang/String;)V
    //   1681: goto -118 -> 1563
    //   1684: aload 10
    //   1686: ldc_w 274
    //   1689: invokevirtual 568	java/io/PrintWriter:print	(Ljava/lang/String;)V
    //   1692: aload 10
    //   1694: ifnull +12 -> 1706
    //   1697: iconst_0
    //   1698: ifeq +57 -> 1755
    //   1701: aload 10
    //   1703: invokevirtual 569	java/io/PrintWriter:close	()V
    //   1706: aload 9
    //   1708: ifnull +12 -> 1720
    //   1711: iconst_0
    //   1712: ifeq +79 -> 1791
    //   1715: aload 9
    //   1717: invokevirtual 588	java/io/BufferedWriter:close	()V
    //   1720: aload 8
    //   1722: ifnull -653 -> 1069
    //   1725: iconst_0
    //   1726: ifeq +92 -> 1818
    //   1729: aload 8
    //   1731: invokevirtual 589	java/io/FileWriter:close	()V
    //   1734: goto -665 -> 1069
    //   1737: astore_1
    //   1738: new 595	java/lang/NullPointerException
    //   1741: dup
    //   1742: invokespecial 596	java/lang/NullPointerException:<init>	()V
    //   1745: athrow
    //   1746: astore_1
    //   1747: new 595	java/lang/NullPointerException
    //   1750: dup
    //   1751: invokespecial 596	java/lang/NullPointerException:<init>	()V
    //   1754: athrow
    //   1755: aload 10
    //   1757: invokevirtual 569	java/io/PrintWriter:close	()V
    //   1760: goto -54 -> 1706
    //   1763: astore 10
    //   1765: aload_1
    //   1766: aload 10
    //   1768: invokevirtual 600	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   1771: goto -755 -> 1016
    //   1774: aload 10
    //   1776: invokevirtual 569	java/io/PrintWriter:close	()V
    //   1779: goto -763 -> 1016
    //   1782: astore_1
    //   1783: new 595	java/lang/NullPointerException
    //   1786: dup
    //   1787: invokespecial 596	java/lang/NullPointerException:<init>	()V
    //   1790: athrow
    //   1791: aload 9
    //   1793: invokevirtual 588	java/io/BufferedWriter:close	()V
    //   1796: goto -76 -> 1720
    //   1799: astore 9
    //   1801: aload_1
    //   1802: aload 9
    //   1804: invokevirtual 600	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   1807: goto -771 -> 1036
    //   1810: aload 9
    //   1812: invokevirtual 588	java/io/BufferedWriter:close	()V
    //   1815: goto -779 -> 1036
    //   1818: aload 8
    //   1820: invokevirtual 589	java/io/FileWriter:close	()V
    //   1823: goto -754 -> 1069
    //   1826: astore 8
    //   1828: aload_1
    //   1829: aload 8
    //   1831: invokevirtual 600	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   1834: goto -778 -> 1056
    //   1837: aload 8
    //   1839: invokevirtual 589	java/io/FileWriter:close	()V
    //   1842: goto -786 -> 1056
    //   1845: astore_1
    //   1846: getstatic 183	java/lang/System:out	Ljava/io/PrintStream;
    //   1849: aload_1
    //   1850: invokevirtual 591	java/lang/Exception:toString	()Ljava/lang/String;
    //   1853: invokevirtual 191	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   1856: goto -766 -> 1090
    //   1859: aload 9
    //   1861: ldc_w 1237
    //   1864: invokevirtual 568	java/io/PrintWriter:print	(Ljava/lang/String;)V
    //   1867: goto -733 -> 1134
    //   1870: astore_2
    //   1871: goto -623 -> 1248
    //   1874: aload 9
    //   1876: ifnull +12 -> 1888
    //   1879: iconst_0
    //   1880: ifeq +57 -> 1937
    //   1883: aload 9
    //   1885: invokevirtual 569	java/io/PrintWriter:close	()V
    //   1888: aload 8
    //   1890: ifnull +12 -> 1902
    //   1893: iconst_0
    //   1894: ifeq +79 -> 1973
    //   1897: aload 8
    //   1899: invokevirtual 588	java/io/BufferedWriter:close	()V
    //   1902: aload 4
    //   1904: ifnull -589 -> 1315
    //   1907: iconst_0
    //   1908: ifeq +92 -> 2000
    //   1911: aload 4
    //   1913: invokevirtual 589	java/io/FileWriter:close	()V
    //   1916: goto -601 -> 1315
    //   1919: astore_1
    //   1920: new 595	java/lang/NullPointerException
    //   1923: dup
    //   1924: invokespecial 596	java/lang/NullPointerException:<init>	()V
    //   1927: athrow
    //   1928: astore_1
    //   1929: new 595	java/lang/NullPointerException
    //   1932: dup
    //   1933: invokespecial 596	java/lang/NullPointerException:<init>	()V
    //   1936: athrow
    //   1937: aload 9
    //   1939: invokevirtual 569	java/io/PrintWriter:close	()V
    //   1942: goto -54 -> 1888
    //   1945: astore 9
    //   1947: aload_1
    //   1948: aload 9
    //   1950: invokevirtual 600	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   1953: goto -691 -> 1262
    //   1956: aload 9
    //   1958: invokevirtual 569	java/io/PrintWriter:close	()V
    //   1961: goto -699 -> 1262
    //   1964: astore_1
    //   1965: new 595	java/lang/NullPointerException
    //   1968: dup
    //   1969: invokespecial 596	java/lang/NullPointerException:<init>	()V
    //   1972: athrow
    //   1973: aload 8
    //   1975: invokevirtual 588	java/io/BufferedWriter:close	()V
    //   1978: goto -76 -> 1902
    //   1981: astore 8
    //   1983: aload_1
    //   1984: aload 8
    //   1986: invokevirtual 600	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   1989: goto -707 -> 1282
    //   1992: aload 8
    //   1994: invokevirtual 588	java/io/BufferedWriter:close	()V
    //   1997: goto -715 -> 1282
    //   2000: aload 4
    //   2002: invokevirtual 589	java/io/FileWriter:close	()V
    //   2005: goto -690 -> 1315
    //   2008: astore 4
    //   2010: aload_1
    //   2011: aload 4
    //   2013: invokevirtual 600	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   2016: goto -714 -> 1302
    //   2019: aload 4
    //   2021: invokevirtual 589	java/io/FileWriter:close	()V
    //   2024: goto -722 -> 1302
    //   2027: aload 4
    //   2029: new 163	java/lang/StringBuilder
    //   2032: dup
    //   2033: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   2036: aload 8
    //   2038: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2041: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2044: ldc_w 310
    //   2047: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2050: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2053: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2056: pop
    //   2057: goto -725 -> 1332
    //   2060: aload 4
    //   2062: ldc_w 1239
    //   2065: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2068: pop
    //   2069: aload_0
    //   2070: getfield 87	PFCpack/League:playoffGames	[LPFCpack/Game;
    //   2073: astore_2
    //   2074: aload_2
    //   2075: arraylength
    //   2076: istore 7
    //   2078: iconst_0
    //   2079: istore 6
    //   2081: iload 6
    //   2083: iload 7
    //   2085: if_icmpge +79 -> 2164
    //   2088: aload_2
    //   2089: iload 6
    //   2091: aaload
    //   2092: astore_1
    //   2093: aload_1
    //   2094: ifnull +61 -> 2155
    //   2097: aload 4
    //   2099: new 163	java/lang/StringBuilder
    //   2102: dup
    //   2103: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   2106: aload_1
    //   2107: invokevirtual 1230	PFCpack/Game:getCSV	()Ljava/lang/String;
    //   2110: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2113: ldc_w 310
    //   2116: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2119: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2122: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2125: pop
    //   2126: getstatic 183	java/lang/System:out	Ljava/io/PrintStream;
    //   2129: new 163	java/lang/StringBuilder
    //   2132: dup
    //   2133: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   2136: ldc_w 1241
    //   2139: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2142: aload_1
    //   2143: invokevirtual 1230	PFCpack/Game:getCSV	()Ljava/lang/String;
    //   2146: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2149: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2152: invokevirtual 191	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   2155: iload 6
    //   2157: iconst_1
    //   2158: iadd
    //   2159: istore 6
    //   2161: goto -80 -> 2081
    //   2164: new 576	java/io/BufferedWriter
    //   2167: dup
    //   2168: new 1205	java/io/OutputStreamWriter
    //   2171: dup
    //   2172: new 1207	java/io/FileOutputStream
    //   2175: dup
    //   2176: aload_3
    //   2177: invokespecial 1208	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   2180: ldc_w 1210
    //   2183: invokespecial 1213	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;Ljava/lang/String;)V
    //   2186: invokespecial 579	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   2189: astore_3
    //   2190: aconst_null
    //   2191: astore_2
    //   2192: aload_3
    //   2193: aload 4
    //   2195: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2198: invokevirtual 1218	java/io/Writer:write	(Ljava/lang/String;)V
    //   2201: aload_3
    //   2202: ifnull +11 -> 2213
    //   2205: iconst_0
    //   2206: ifeq +38 -> 2244
    //   2209: aload_3
    //   2210: invokevirtual 1219	java/io/Writer:close	()V
    //   2213: aload 4
    //   2215: iconst_0
    //   2216: invokevirtual 1222	java/lang/StringBuilder:setLength	(I)V
    //   2219: iconst_1
    //   2220: ireturn
    //   2221: astore_1
    //   2222: new 595	java/lang/NullPointerException
    //   2225: dup
    //   2226: invokespecial 596	java/lang/NullPointerException:<init>	()V
    //   2229: athrow
    //   2230: astore_1
    //   2231: getstatic 183	java/lang/System:out	Ljava/io/PrintStream;
    //   2234: aload_1
    //   2235: invokevirtual 591	java/lang/Exception:toString	()Ljava/lang/String;
    //   2238: invokevirtual 191	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   2241: goto -28 -> 2213
    //   2244: aload_3
    //   2245: invokevirtual 1219	java/io/Writer:close	()V
    //   2248: goto -35 -> 2213
    //   2251: astore_2
    //   2252: aload_2
    //   2253: athrow
    //   2254: astore_1
    //   2255: aload_3
    //   2256: ifnull +11 -> 2267
    //   2259: aload_2
    //   2260: ifnull +18 -> 2278
    //   2263: aload_3
    //   2264: invokevirtual 1219	java/io/Writer:close	()V
    //   2267: aload_1
    //   2268: athrow
    //   2269: astore_3
    //   2270: aload_2
    //   2271: aload_3
    //   2272: invokevirtual 600	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   2275: goto -8 -> 2267
    //   2278: aload_3
    //   2279: invokevirtual 1219	java/io/Writer:close	()V
    //   2282: goto -15 -> 2267
    //   2285: astore_1
    //   2286: goto -31 -> 2255
    //   2289: astore_1
    //   2290: goto -795 -> 1495
    //   2293: astore_2
    //   2294: goto -1292 -> 1002
    //   2297: astore_2
    //   2298: aconst_null
    //   2299: astore_1
    //   2300: goto -1278 -> 1022
    //   2303: astore_2
    //   2304: aconst_null
    //   2305: astore_1
    //   2306: goto -1264 -> 1042
    //   2309: astore_2
    //   2310: aconst_null
    //   2311: astore_1
    //   2312: goto -1044 -> 1268
    //   2315: astore_2
    //   2316: aconst_null
    //   2317: astore_1
    //   2318: goto -1030 -> 1288
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2321	0	this	League
    //   0	2321	1	paramFile1	File
    //   0	2321	2	paramFile2	File
    //   0	2321	3	paramFile3	File
    //   0	2321	4	paramFile4	File
    //   0	2321	5	paramBoolean	boolean
    //   139	2021	6	i	int
    //   154	1932	7	j	int
    //   414	985	8	localObject1	Object
    //   1489	330	8	localThrowable1	Throwable
    //   1826	148	8	localThrowable2	Throwable
    //   1981	56	8	localThrowable3	Throwable
    //   7	1785	9	localObject2	Object
    //   1799	139	9	localThrowable4	Throwable
    //   1945	12	9	localThrowable5	Throwable
    //   392	1114	10	localObject3	Object
    //   1512	244	10	localThrowable6	Throwable
    //   1763	12	10	localThrowable7	Throwable
    //   944	630	11	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   918	926	998	java/lang/Throwable
    //   926	995	998	java/lang/Throwable
    //   1546	1563	998	java/lang/Throwable
    //   1563	1611	998	java/lang/Throwable
    //   1614	1664	998	java/lang/Throwable
    //   1673	1681	998	java/lang/Throwable
    //   1684	1692	998	java/lang/Throwable
    //   999	1001	1001	finally
    //   905	916	1018	java/lang/Throwable
    //   1016	1018	1018	java/lang/Throwable
    //   1747	1755	1018	java/lang/Throwable
    //   1755	1760	1018	java/lang/Throwable
    //   1765	1771	1018	java/lang/Throwable
    //   1774	1779	1018	java/lang/Throwable
    //   1019	1021	1021	finally
    //   894	905	1038	java/lang/Throwable
    //   1036	1038	1038	java/lang/Throwable
    //   1783	1791	1038	java/lang/Throwable
    //   1791	1796	1038	java/lang/Throwable
    //   1801	1807	1038	java/lang/Throwable
    //   1810	1815	1038	java/lang/Throwable
    //   1039	1041	1041	finally
    //   883	894	1058	java/io/IOException
    //   1051	1056	1058	java/io/IOException
    //   1056	1058	1058	java/io/IOException
    //   1729	1734	1058	java/io/IOException
    //   1738	1746	1058	java/io/IOException
    //   1818	1823	1058	java/io/IOException
    //   1828	1834	1058	java/io/IOException
    //   1837	1842	1058	java/io/IOException
    //   1126	1134	1244	java/lang/Throwable
    //   1134	1193	1244	java/lang/Throwable
    //   1193	1241	1244	java/lang/Throwable
    //   1859	1867	1244	java/lang/Throwable
    //   1245	1247	1247	finally
    //   1113	1124	1264	java/lang/Throwable
    //   1262	1264	1264	java/lang/Throwable
    //   1929	1937	1264	java/lang/Throwable
    //   1937	1942	1264	java/lang/Throwable
    //   1947	1953	1264	java/lang/Throwable
    //   1956	1961	1264	java/lang/Throwable
    //   1265	1267	1267	finally
    //   1102	1113	1284	java/lang/Throwable
    //   1282	1284	1284	java/lang/Throwable
    //   1965	1973	1284	java/lang/Throwable
    //   1973	1978	1284	java/lang/Throwable
    //   1983	1989	1284	java/lang/Throwable
    //   1992	1997	1284	java/lang/Throwable
    //   1285	1287	1287	finally
    //   1090	1102	1304	java/io/IOException
    //   1297	1302	1304	java/io/IOException
    //   1302	1304	1304	java/io/IOException
    //   1911	1916	1304	java/io/IOException
    //   1920	1928	1304	java/io/IOException
    //   2000	2005	1304	java/io/IOException
    //   2010	2016	1304	java/io/IOException
    //   2019	2024	1304	java/io/IOException
    //   852	857	1458	java/lang/Throwable
    //   803	830	1467	java/lang/Exception
    //   852	857	1467	java/lang/Exception
    //   1459	1467	1467	java/lang/Exception
    //   1481	1486	1467	java/lang/Exception
    //   1505	1510	1467	java/lang/Exception
    //   1510	1512	1467	java/lang/Exception
    //   1514	1521	1467	java/lang/Exception
    //   1524	1529	1467	java/lang/Exception
    //   833	843	1489	java/lang/Throwable
    //   1491	1494	1494	finally
    //   1505	1510	1512	java/lang/Throwable
    //   863	883	1532	java/lang/Exception
    //   1729	1734	1737	java/lang/Throwable
    //   1701	1706	1746	java/lang/Throwable
    //   1011	1016	1763	java/lang/Throwable
    //   1715	1720	1782	java/lang/Throwable
    //   1031	1036	1799	java/lang/Throwable
    //   1051	1056	1826	java/lang/Throwable
    //   1069	1090	1845	java/lang/Exception
    //   1126	1134	1870	finally
    //   1134	1193	1870	finally
    //   1193	1241	1870	finally
    //   1859	1867	1870	finally
    //   1911	1916	1919	java/lang/Throwable
    //   1883	1888	1928	java/lang/Throwable
    //   1257	1262	1945	java/lang/Throwable
    //   1897	1902	1964	java/lang/Throwable
    //   1277	1282	1981	java/lang/Throwable
    //   1297	1302	2008	java/lang/Throwable
    //   2209	2213	2221	java/lang/Throwable
    //   2164	2190	2230	java/lang/Exception
    //   2209	2213	2230	java/lang/Exception
    //   2222	2230	2230	java/lang/Exception
    //   2244	2248	2230	java/lang/Exception
    //   2263	2267	2230	java/lang/Exception
    //   2267	2269	2230	java/lang/Exception
    //   2270	2275	2230	java/lang/Exception
    //   2278	2282	2230	java/lang/Exception
    //   2192	2201	2251	java/lang/Throwable
    //   2252	2254	2254	finally
    //   2263	2267	2269	java/lang/Throwable
    //   2192	2201	2285	finally
    //   833	843	2289	finally
    //   918	926	2293	finally
    //   926	995	2293	finally
    //   1546	1563	2293	finally
    //   1563	1611	2293	finally
    //   1614	1664	2293	finally
    //   1673	1681	2293	finally
    //   1684	1692	2293	finally
    //   905	916	2297	finally
    //   1011	1016	2297	finally
    //   1016	1018	2297	finally
    //   1701	1706	2297	finally
    //   1747	1755	2297	finally
    //   1755	1760	2297	finally
    //   1765	1771	2297	finally
    //   1774	1779	2297	finally
    //   894	905	2303	finally
    //   1031	1036	2303	finally
    //   1036	1038	2303	finally
    //   1715	1720	2303	finally
    //   1783	1791	2303	finally
    //   1791	1796	2303	finally
    //   1801	1807	2303	finally
    //   1810	1815	2303	finally
    //   1113	1124	2309	finally
    //   1257	1262	2309	finally
    //   1262	1264	2309	finally
    //   1883	1888	2309	finally
    //   1929	1937	2309	finally
    //   1937	1942	2309	finally
    //   1947	1953	2309	finally
    //   1956	1961	2309	finally
    //   1102	1113	2315	finally
    //   1277	1282	2315	finally
    //   1282	1284	2315	finally
    //   1897	1902	2315	finally
    //   1965	1973	2315	finally
    //   1973	1978	2315	finally
    //   1983	1989	2315	finally
    //   1992	1997	2315	finally
  }
  
  public String seasonSummaryStr()
  {
    setTeamRanks();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(ncgSummaryStr());
    localStringBuilder.append("\n\n" + userTeam.seasonSummaryStr());
    localStringBuilder.append("\n\n" + leagueRecords.brokenRecordsStr(getYear(), userTeam.abbr));
    return localStringBuilder.toString();
  }
  
  public void setTeamRanks()
  {
    int i = 0;
    while (i < teamList.size())
    {
      ((Team)teamList.get(i)).updatePollScore();
      i += 1;
    }
    Collections.sort(teamList, new TeamCompPoll());
    i = 0;
    while (i < teamList.size())
    {
      teamList.get(i)).rankTeamPollScore = (i + 1);
      i += 1;
    }
    Collections.sort(teamList, new TeamCompSoW());
    i = 0;
    while (i < teamList.size())
    {
      teamList.get(i)).rankTeamStrengthOfWins = (i + 1);
      i += 1;
    }
    Collections.sort(teamList, new TeamCompPPG());
    i = 0;
    while (i < teamList.size())
    {
      teamList.get(i)).rankTeamPoints = (i + 1);
      i += 1;
    }
    Collections.sort(teamList, new TeamCompOPPG());
    i = 0;
    while (i < teamList.size())
    {
      teamList.get(i)).rankTeamOppPoints = (i + 1);
      i += 1;
    }
    Collections.sort(teamList, new TeamCompYPG());
    i = 0;
    while (i < teamList.size())
    {
      teamList.get(i)).rankTeamYards = (i + 1);
      i += 1;
    }
    Collections.sort(teamList, new TeamCompOYPG());
    i = 0;
    while (i < teamList.size())
    {
      teamList.get(i)).rankTeamOppYards = (i + 1);
      i += 1;
    }
    Collections.sort(teamList, new TeamCompPYPG());
    i = 0;
    while (i < teamList.size())
    {
      teamList.get(i)).rankTeamPassYards = (i + 1);
      i += 1;
    }
    Collections.sort(teamList, new TeamCompRYPG());
    i = 0;
    while (i < teamList.size())
    {
      teamList.get(i)).rankTeamRushYards = (i + 1);
      i += 1;
    }
    Collections.sort(teamList, new TeamCompOPYPG());
    i = 0;
    while (i < teamList.size())
    {
      teamList.get(i)).rankTeamOppPassYards = (i + 1);
      i += 1;
    }
    Collections.sort(teamList, new TeamCompORYPG());
    i = 0;
    while (i < teamList.size())
    {
      teamList.get(i)).rankTeamOppRushYards = (i + 1);
      i += 1;
    }
    Collections.sort(teamList, new TeamCompTODiff());
    i = 0;
    while (i < teamList.size())
    {
      teamList.get(i)).rankTeamTODiff = (i + 1);
      i += 1;
    }
    Collections.sort(teamList, new TeamCompOffTalent());
    i = 0;
    while (i < teamList.size())
    {
      teamList.get(i)).rankTeamOffTalent = (i + 1);
      i += 1;
    }
    Collections.sort(teamList, new TeamCompDefTalent());
    i = 0;
    while (i < teamList.size())
    {
      teamList.get(i)).rankTeamDefTalent = (i + 1);
      i += 1;
    }
    Collections.sort(teamList, new TeamCompPrestige());
    i = 0;
    while (i < teamList.size())
    {
      teamList.get(i)).rankTeamPrestige = (i + 1);
      i += 1;
    }
  }
  
  public void setUpPlayoffs(int paramInt)
  {
    if (paramInt <= 1)
    {
      Object localObject2 = new ArrayList();
      playoffsAM = new ArrayList();
      playoffsNA = new ArrayList();
      Object localObject3 = new ArrayList();
      localObject1 = new ArrayList();
      paramInt = 0;
      if (paramInt < divisions.size())
      {
        ((ArrayList)localObject2).addAll(divisions.get(paramInt)).divTeams);
        Collections.sort((List)localObject2, new TeamCompDivision());
        if (paramInt < 4)
        {
          playoffsAM.add(((ArrayList)localObject2).get(0));
          ((ArrayList)localObject3).addAll((Collection)localObject2);
        }
        for (;;)
        {
          get0divChampion = "DW";
          Team localTeam = (Team)((ArrayList)localObject2).get(0);
          totalDivChamps += 1;
          ((ArrayList)localObject2).clear();
          paramInt += 1;
          break;
          playoffsNA.add(((ArrayList)localObject2).get(0));
          ((ArrayList)localObject1).addAll((Collection)localObject2);
        }
      }
      Collections.sort(playoffsAM, new TeamCompDivision());
      Collections.sort(playoffsNA, new TeamCompDivision());
      Collections.sort((List)localObject3, new TeamCompDivision());
      paramInt = 0;
      localObject2 = ((ArrayList)localObject3).iterator();
      int i;
      do
      {
        if (!((Iterator)localObject2).hasNext()) {
          break;
        }
        localObject3 = (Team)((Iterator)localObject2).next();
        i = paramInt;
        if (!playoffsAM.contains(localObject3))
        {
          playoffsAM.add(localObject3);
          i = paramInt + 1;
        }
        paramInt = i;
      } while (i < 2);
      Collections.sort((List)localObject1, new TeamCompDivision());
      paramInt = 0;
      localObject1 = ((ArrayList)localObject1).iterator();
      do
      {
        if (!((Iterator)localObject1).hasNext()) {
          break;
        }
        localObject2 = (Team)((Iterator)localObject1).next();
        i = paramInt;
        if (!playoffsNA.contains(localObject2))
        {
          playoffsNA.add(localObject2);
          i = paramInt + 1;
        }
        paramInt = i;
      } while (i < 2);
      playoffGames[0] = new Game((Team)playoffsAM.get(2), (Team)playoffsAM.get(5), "AM Rd 1", 16);
      playoffsAM.get(2)).gameSchedule.add(playoffGames[0]);
      playoffsAM.get(5)).gameSchedule.add(playoffGames[0]);
      playoffGames[1] = new Game((Team)playoffsAM.get(3), (Team)playoffsAM.get(4), "AM Rd 1", 16);
      playoffsAM.get(3)).gameSchedule.add(playoffGames[1]);
      playoffsAM.get(4)).gameSchedule.add(playoffGames[1]);
      playoffGames[2] = new Game((Team)playoffsNA.get(2), (Team)playoffsNA.get(5), "NA Rd 1", 16);
      playoffsNA.get(2)).gameSchedule.add(playoffGames[2]);
      playoffsNA.get(5)).gameSchedule.add(playoffGames[2]);
      playoffGames[3] = new Game((Team)playoffsNA.get(3), (Team)playoffsNA.get(4), "NA Rd 1", 16);
      playoffsNA.get(3)).gameSchedule.add(playoffGames[3]);
      playoffsNA.get(4)).gameSchedule.add(playoffGames[3]);
    }
    do
    {
      return;
      if (paramInt == 2)
      {
        paramInt = 0;
        while (paramInt < 4)
        {
          playoffGames[paramInt].playGame();
          localObject1 = playoffGames[paramInt].getWinner();
          totalPlayoffWins += 1;
          localObject1 = playoffGames[paramInt].getLoser();
          totalPlayoffLosses += 1;
          paramInt += 1;
        }
        ((Team)playoffsAM.get(0)).advanceInjuriesWeek();
        ((Team)playoffsAM.get(1)).advanceInjuriesWeek();
        ((Team)playoffsNA.get(0)).advanceInjuriesWeek();
        ((Team)playoffsNA.get(1)).advanceInjuriesWeek();
        playoffGames[4] = new Game((Team)playoffsAM.get(0), playoffGames[0].getWinner(), "AM Rd 2", 17);
        playoffsAM.get(0)).gameSchedule.add(playoffGames[4]);
        playoffGames[0].getWinner().gameSchedule.add(playoffGames[4]);
        playoffGames[5] = new Game((Team)playoffsAM.get(1), playoffGames[1].getWinner(), "AM Rd 2", 17);
        playoffsAM.get(1)).gameSchedule.add(playoffGames[5]);
        playoffGames[1].getWinner().gameSchedule.add(playoffGames[5]);
        playoffGames[6] = new Game((Team)playoffsNA.get(0), playoffGames[2].getWinner(), "NA Rd 2", 17);
        playoffsNA.get(0)).gameSchedule.add(playoffGames[6]);
        playoffGames[2].getWinner().gameSchedule.add(playoffGames[6]);
        playoffGames[7] = new Game((Team)playoffsNA.get(1), playoffGames[3].getWinner(), "NA Rd 2", 17);
        playoffsNA.get(1)).gameSchedule.add(playoffGames[7]);
        playoffGames[3].getWinner().gameSchedule.add(playoffGames[7]);
        return;
      }
      if (paramInt == 3)
      {
        paramInt = 4;
        while (paramInt < 8)
        {
          playoffGames[paramInt].playGame();
          localObject1 = playoffGames[paramInt].getWinner();
          totalPlayoffWins += 1;
          localObject1 = playoffGames[paramInt].getLoser();
          totalPlayoffLosses += 1;
          paramInt += 1;
        }
        playoffGames[8] = new Game(playoffGames[4].getWinner(), playoffGames[5].getWinner(), "AM CCG", 18);
        playoffGames[4].getWinner().gameSchedule.add(playoffGames[8]);
        playoffGames[5].getWinner().gameSchedule.add(playoffGames[8]);
        playoffGames[9] = new Game(playoffGames[6].getWinner(), playoffGames[7].getWinner(), "NA CCG", 18);
        playoffGames[6].getWinner().gameSchedule.add(playoffGames[9]);
        playoffGames[7].getWinner().gameSchedule.add(playoffGames[9]);
        return;
      }
      if (paramInt == 4)
      {
        paramInt = 8;
        while (paramInt < 10)
        {
          playoffGames[paramInt].playGame();
          localObject1 = playoffGames[paramInt].getWinner();
          totalPlayoffWins += 1;
          localObject1 = playoffGames[paramInt].getLoser();
          totalPlayoffLosses += 1;
          paramInt += 1;
        }
        playoffGames[10] = new Game(playoffGames[8].getWinner(), playoffGames[9].getWinner(), "Champ Bowl", 19);
        playoffGames[8].getWinner().gameSchedule.add(playoffGames[10]);
        playoffGames[9].getWinner().gameSchedule.add(playoffGames[10]);
        return;
      }
    } while (paramInt != 5);
    playoffGames[10].playGame();
    Object localObject1 = playoffGames[10].getWinner();
    totalPlayoffWins += 1;
    localObject1 = playoffGames[10].getLoser();
    totalPlayoffLosses += 1;
    localObject1 = playoffGames[10].getWinner();
    totalSuperBowlWins += 1;
    localObject1 = playoffGames[10].getLoser();
    totalSuperBowlLosses += 1;
    playoffGames[10].getWinner().natChampWL = "CBW";
    playoffGames[10].getLoser().natChampWL = "CBL";
  }
  
  public void setUpSchedule()
  {
    System.out.println("SETTING UP SCHEDULE!");
    Object localObject3 = new int[7];
    Object tmp17_15 = localObject3;
    tmp17_15[0] = 1;
    Object tmp21_17 = tmp17_15;
    tmp21_17[1] = 2;
    Object tmp25_21 = tmp21_17;
    tmp25_21[2] = 4;
    Object tmp29_25 = tmp25_21;
    tmp29_25[3] = 7;
    Object tmp34_29 = tmp29_25;
    tmp34_29[4] = 10;
    Object tmp39_34 = tmp34_29;
    tmp39_34[5] = 11;
    Object tmp44_39 = tmp39_34;
    tmp44_39[6] = 13;
    tmp44_39;
    Object localObject2 = new int[3];
    Object tmp58_56 = localObject2;
    tmp58_56[0] = 5;
    Object tmp62_58 = tmp58_56;
    tmp62_58[1] = 8;
    Object tmp67_62 = tmp62_58;
    tmp67_62[2] = 12;
    tmp67_62;
    Object localObject1 = divisions.iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject4 = (Division)((Iterator)localObject1).next();
      new Game((Team)divTeams.get(0), (Team)divTeams.get(1), "Division", 0);
      new Game((Team)divTeams.get(2), (Team)divTeams.get(3), "Division", 0);
      new Game((Team)divTeams.get(0), (Team)divTeams.get(2), "Division", 3);
      new Game((Team)divTeams.get(1), (Team)divTeams.get(3), "Division", 3);
      new Game((Team)divTeams.get(0), (Team)divTeams.get(3), "Division", 6);
      new Game((Team)divTeams.get(1), (Team)divTeams.get(2), "Division", 6);
      new Game((Team)divTeams.get(1), (Team)divTeams.get(0), "Division", 9);
      new Game((Team)divTeams.get(3), (Team)divTeams.get(2), "Division", 9);
      new Game((Team)divTeams.get(2), (Team)divTeams.get(0), "Division", 14);
      new Game((Team)divTeams.get(3), (Team)divTeams.get(1), "Division", 14);
      new Game((Team)divTeams.get(3), (Team)divTeams.get(0), "Division", 15);
      new Game((Team)divTeams.get(2), (Team)divTeams.get(1), "Division", 15);
    }
    Object localObject4 = new ArrayList();
    int i = 0;
    Object localObject5;
    while (i < 4)
    {
      ((ArrayList)localObject4).add(new ArrayList());
      localObject1 = divisions.iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject5 = (Division)((Iterator)localObject1).next();
        ((ArrayList)((ArrayList)localObject4).get(i)).add(divTeams.get(i));
      }
      i += 1;
    }
    i = 0;
    int k;
    int j;
    while (i < ((ArrayList)localObject4).size())
    {
      k = 0;
      j = 0;
      while (j < 7)
      {
        int m = 0;
        if (m < 4)
        {
          localObject5 = (Team)((ArrayList)((ArrayList)localObject4).get(i)).get((k + m) % 7);
          if (m == 0)
          {
            localObject1 = (Team)((ArrayList)((ArrayList)localObject4).get(i)).get(7);
            label717:
            if (Math.random() >= 0.5D) {
              break label785;
            }
            new Game((Team)localObject5, (Team)localObject1, "Reg Season", localObject3[j]);
          }
          for (;;)
          {
            m += 1;
            break;
            localObject1 = (Team)((ArrayList)((ArrayList)localObject4).get(i)).get((7 - m + k) % 7);
            break label717;
            label785:
            new Game((Team)localObject1, (Team)localObject5, "Reg Season", localObject3[j]);
          }
        }
        k += 1;
        j += 1;
      }
      i += 1;
    }
    i = 0;
    while (i < 4)
    {
      localObject1 = (Division)divisions.get(i * 2);
      localObject3 = (Division)divisions.get(i * 2 + 1);
      j = 0;
      while (j < 4)
      {
        k = 0;
        if (k < 3)
        {
          if (Math.random() > 0.5D) {
            new Game((Team)divTeams.get(j), (Team)divTeams.get((k + j) % 4), "Reg Season", localObject2[k]);
          }
          for (;;)
          {
            k += 1;
            break;
            new Game((Team)divTeams.get((k + j) % 4), (Team)divTeams.get(j), "Reg Season", localObject2[k]);
          }
        }
        j += 1;
      }
      i += 1;
    }
    localObject1 = teamList.iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (Team)((Iterator)localObject1).next();
      i = 0;
      while (i < 16)
      {
        localObject3 = regSeasonSchedule[i];
        if (localObject3 == null) {
          System.out.println("GAME: #" + i + " is null for team: " + abbr);
        }
        gameSchedule.add(localObject3);
        i += 1;
      }
    }
  }
  
  public void signExpiringFAs()
  {
    Iterator localIterator = teamList.iterator();
    while (localIterator.hasNext())
    {
      Team localTeam = (Team)localIterator.next();
      if (localTeam != userTeam)
      {
        int i = 0;
        while (i < playersFAs.size())
        {
          Player localPlayer = (Player)playersFAs.get(i);
          if ((ratOvr > 75) && (Math.random() < (wins + 15) / 40.0D))
          {
            localPlayer.setContract(Contract.getContractFA(localPlayer));
            localTeam.addPlayer(localPlayer);
            playersFAs.remove(localPlayer);
          }
          else
          {
            i += 1;
          }
        }
      }
    }
  }
  
  public void sortFAs()
  {
    Collections.sort(freeAgents, new PlayerComparator());
  }
  
  public void transferTeamFAs()
  {
    Iterator localIterator1 = teamList.iterator();
    while (localIterator1.hasNext())
    {
      Team localTeam = (Team)localIterator1.next();
      Iterator localIterator2 = playersFAs.iterator();
      while (localIterator2.hasNext())
      {
        Player localPlayer = (Player)localIterator2.next();
        freeAgents.add(localPlayer);
      }
      playersFAs.clear();
    }
    Collections.sort(freeAgents, new PlayerComparator());
  }
  
  public void updateLeagueHistory()
  {
    int i = 0;
    while (i < teamList.size())
    {
      ((Team)teamList.get(i)).updatePollScore();
      i += 1;
    }
    Collections.sort(teamList, new TeamCompPoll());
    String[] arrayOfString = new String[10];
    i = 0;
    while (i < 10)
    {
      Team localTeam = (Team)teamList.get(i);
      arrayOfString[i] = (abbr + " (" + wins + "-" + losses + ")");
      i += 1;
    }
    leagueHistory.add(arrayOfString);
  }
  
  public void updateLongestActiveWinStreak()
  {
    Iterator localIterator = teamList.iterator();
    while (localIterator.hasNext())
    {
      Team localTeam = (Team)localIterator.next();
      if (winStreak.getStreakLength() > longestActiveWinStreak.getStreakLength()) {
        longestActiveWinStreak = winStreak;
      }
    }
  }
  
  public void updateTeamHistories()
  {
    int i = 0;
    while (i < teamList.size())
    {
      ((Team)teamList.get(i)).updateTeamHistory();
      i += 1;
    }
  }
  
  public void updateTeamTalentRatings()
  {
    Iterator localIterator = teamList.iterator();
    while (localIterator.hasNext()) {
      ((Team)localIterator.next()).updateTalentRatings();
    }
  }
}

/* Location:
 * Qualified Name:     PFCpack.League
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */