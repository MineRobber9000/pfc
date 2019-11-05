// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import java.util.Iterator;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;

public class League
{
    public static final String[] donationNames;
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
    
    static {
        donationNames = new String[] { "Mark Eeslee", "Lee Sin", "Brent Uttwipe", "Gabriel Kemble", "Jon Stupak", "Kiergan Ren", "Declan Greally", "Parks Wilson" };
    }
    
    public League(final File p0, final File p1, final File p2, final File p3, final String p4, final String p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokespecial   java/lang/Object.<init>:()V
        //     4: aload_0        
        //     5: iconst_0       
        //     6: putfield        PFCpack/League.mvpDecided:Z
        //     9: aload_0        
        //    10: bipush          11
        //    12: anewarray       LPFCpack/Game;
        //    15: putfield        PFCpack/League.playoffGames:[LPFCpack/Game;
        //    18: aload_0        
        //    19: iconst_0       
        //    20: putfield        PFCpack/League.currentWeek:I
        //    23: aload_0        
        //    24: new             LPFCpack/LeagueRecords;
        //    27: dup            
        //    28: invokespecial   PFCpack/LeagueRecords.<init>:()V
        //    31: putfield        PFCpack/League.leagueRecords:LPFCpack/LeagueRecords;
        //    34: aload_0        
        //    35: new             LPFCpack/LeagueRecords;
        //    38: dup            
        //    39: invokespecial   PFCpack/LeagueRecords.<init>:()V
        //    42: putfield        PFCpack/League.userTeamRecords:LPFCpack/LeagueRecords;
        //    45: aload_0        
        //    46: new             LPFCpack/TeamStreak;
        //    49: dup            
        //    50: sipush          2016
        //    53: sipush          2016
        //    56: iconst_0       
        //    57: ldc             "XXX"
        //    59: invokespecial   PFCpack/TeamStreak.<init>:(IIILjava/lang/String;)V
        //    62: putfield        PFCpack/League.longestWinStreak:LPFCpack/TeamStreak;
        //    65: aload_0        
        //    66: new             LPFCpack/TeamStreak;
        //    69: dup            
        //    70: sipush          2016
        //    73: sipush          2016
        //    76: iconst_0       
        //    77: ldc             "XXX"
        //    79: invokespecial   PFCpack/TeamStreak.<init>:(IIILjava/lang/String;)V
        //    82: putfield        PFCpack/League.yearStartLongestWinStreak:LPFCpack/TeamStreak;
        //    85: aload_0        
        //    86: new             LPFCpack/TeamStreak;
        //    89: dup            
        //    90: sipush          2016
        //    93: sipush          2016
        //    96: iconst_0       
        //    97: ldc             "XXX"
        //    99: invokespecial   PFCpack/TeamStreak.<init>:(IIILjava/lang/String;)V
        //   102: putfield        PFCpack/League.longestActiveWinStreak:LPFCpack/TeamStreak;
        //   105: aload_0        
        //   106: new             Ljava/util/ArrayList;
        //   109: dup            
        //   110: invokespecial   java/util/ArrayList.<init>:()V
        //   113: putfield        PFCpack/League.divisions:Ljava/util/ArrayList;
        //   116: aload_0        
        //   117: new             Ljava/util/ArrayList;
        //   120: dup            
        //   121: invokespecial   java/util/ArrayList.<init>:()V
        //   124: putfield        PFCpack/League.teamList:Ljava/util/ArrayList;
        //   127: aload_0        
        //   128: getfield        PFCpack/League.divisions:Ljava/util/ArrayList;
        //   131: new             LPFCpack/Division;
        //   134: dup            
        //   135: ldc             "AMNOR"
        //   137: aload_0        
        //   138: invokespecial   PFCpack/Division.<init>:(Ljava/lang/String;LPFCpack/League;)V
        //   141: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   144: pop            
        //   145: aload_0        
        //   146: getfield        PFCpack/League.divisions:Ljava/util/ArrayList;
        //   149: new             LPFCpack/Division;
        //   152: dup            
        //   153: ldc             "AMEAS"
        //   155: aload_0        
        //   156: invokespecial   PFCpack/Division.<init>:(Ljava/lang/String;LPFCpack/League;)V
        //   159: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   162: pop            
        //   163: aload_0        
        //   164: getfield        PFCpack/League.divisions:Ljava/util/ArrayList;
        //   167: new             LPFCpack/Division;
        //   170: dup            
        //   171: ldc             "AMSOU"
        //   173: aload_0        
        //   174: invokespecial   PFCpack/Division.<init>:(Ljava/lang/String;LPFCpack/League;)V
        //   177: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   180: pop            
        //   181: aload_0        
        //   182: getfield        PFCpack/League.divisions:Ljava/util/ArrayList;
        //   185: new             LPFCpack/Division;
        //   188: dup            
        //   189: ldc             "AMWES"
        //   191: aload_0        
        //   192: invokespecial   PFCpack/Division.<init>:(Ljava/lang/String;LPFCpack/League;)V
        //   195: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   198: pop            
        //   199: aload_0        
        //   200: getfield        PFCpack/League.divisions:Ljava/util/ArrayList;
        //   203: new             LPFCpack/Division;
        //   206: dup            
        //   207: ldc             "NANOR"
        //   209: aload_0        
        //   210: invokespecial   PFCpack/Division.<init>:(Ljava/lang/String;LPFCpack/League;)V
        //   213: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   216: pop            
        //   217: aload_0        
        //   218: getfield        PFCpack/League.divisions:Ljava/util/ArrayList;
        //   221: new             LPFCpack/Division;
        //   224: dup            
        //   225: ldc             "NAEAS"
        //   227: aload_0        
        //   228: invokespecial   PFCpack/Division.<init>:(Ljava/lang/String;LPFCpack/League;)V
        //   231: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   234: pop            
        //   235: aload_0        
        //   236: getfield        PFCpack/League.divisions:Ljava/util/ArrayList;
        //   239: new             LPFCpack/Division;
        //   242: dup            
        //   243: ldc             "NASOU"
        //   245: aload_0        
        //   246: invokespecial   PFCpack/Division.<init>:(Ljava/lang/String;LPFCpack/League;)V
        //   249: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   252: pop            
        //   253: aload_0        
        //   254: getfield        PFCpack/League.divisions:Ljava/util/ArrayList;
        //   257: new             LPFCpack/Division;
        //   260: dup            
        //   261: ldc             "NAWES"
        //   263: aload_0        
        //   264: invokespecial   PFCpack/Division.<init>:(Ljava/lang/String;LPFCpack/League;)V
        //   267: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   270: pop            
        //   271: aload_0        
        //   272: new             Ljava/util/ArrayList;
        //   275: dup            
        //   276: invokespecial   java/util/ArrayList.<init>:()V
        //   279: putfield        PFCpack/League.allPros:Ljava/util/ArrayList;
        //   282: aload_0        
        //   283: new             Ljava/util/ArrayList;
        //   286: dup            
        //   287: invokespecial   java/util/ArrayList.<init>:()V
        //   290: putfield        PFCpack/League.playoffsAM:Ljava/util/ArrayList;
        //   293: aload_0        
        //   294: new             Ljava/util/ArrayList;
        //   297: dup            
        //   298: invokespecial   java/util/ArrayList.<init>:()V
        //   301: putfield        PFCpack/League.playoffsNA:Ljava/util/ArrayList;
        //   304: aload_0        
        //   305: new             Ljava/util/ArrayList;
        //   308: dup            
        //   309: invokespecial   java/util/ArrayList.<init>:()V
        //   312: putfield        PFCpack/League.freeAgents:Ljava/util/ArrayList;
        //   315: aload_0        
        //   316: new             Ljava/util/ArrayList;
        //   319: dup            
        //   320: invokespecial   java/util/ArrayList.<init>:()V
        //   323: putfield        PFCpack/League.tradeLog:Ljava/util/ArrayList;
        //   326: new             Ljava/io/BufferedReader;
        //   329: dup            
        //   330: new             Ljava/io/FileReader;
        //   333: dup            
        //   334: aload_2        
        //   335: invokespecial   java/io/FileReader.<init>:(Ljava/io/File;)V
        //   338: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
        //   341: astore_2       
        //   342: iconst_0       
        //   343: istore          7
        //   345: iload           7
        //   347: bipush          32
        //   349: if_icmpge       825
        //   352: new             Ljava/lang/StringBuilder;
        //   355: dup            
        //   356: invokespecial   java/lang/StringBuilder.<init>:()V
        //   359: astore          11
        //   361: aload_2        
        //   362: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //   365: astore          12
        //   367: aload           12
        //   369: ifnull          739
        //   372: aload           12
        //   374: ldc             "END_PLAYERS"
        //   376: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   379: ifne            739
        //   382: aload           11
        //   384: aload           12
        //   386: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   389: pop            
        //   390: goto            361
        //   393: astore_2       
        //   394: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   397: ldc             "Unable to open file"
        //   399: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   402: new             Ljava/io/BufferedReader;
        //   405: dup            
        //   406: new             Ljava/io/FileReader;
        //   409: dup            
        //   410: aload_1        
        //   411: invokespecial   java/io/FileReader.<init>:(Ljava/io/File;)V
        //   414: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
        //   417: astore          11
        //   419: aload           11
        //   421: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //   424: astore_1       
        //   425: aload_0        
        //   426: aload_1        
        //   427: ldc             " "
        //   429: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //   432: iconst_2       
        //   433: aaload         
        //   434: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   437: putfield        PFCpack/League.currentWeek:I
        //   440: aload_0        
        //   441: aload_0        
        //   442: aload_1        
        //   443: ldc             " "
        //   445: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //   448: iconst_4       
        //   449: aaload         
        //   450: invokevirtual   PFCpack/League.findTeamAbbr:(Ljava/lang/String;)LPFCpack/Team;
        //   453: putfield        PFCpack/League.userTeam:LPFCpack/Team;
        //   456: aload_0        
        //   457: getfield        PFCpack/League.userTeam:LPFCpack/Team;
        //   460: iconst_1       
        //   461: putfield        PFCpack/Team.userControlled:Z
        //   464: aload_0        
        //   465: new             Ljava/util/ArrayList;
        //   468: dup            
        //   469: invokespecial   java/util/ArrayList.<init>:()V
        //   472: putfield        PFCpack/League.leagueHistory:Ljava/util/ArrayList;
        //   475: aload           11
        //   477: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //   480: astore_1       
        //   481: aload_1        
        //   482: ifnull          933
        //   485: aload_1        
        //   486: ldc             "END_LEAGUE_HIST"
        //   488: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   491: ifne            933
        //   494: aload_0        
        //   495: getfield        PFCpack/League.leagueHistory:Ljava/util/ArrayList;
        //   498: aload_1        
        //   499: ldc             "%"
        //   501: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //   504: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   507: pop            
        //   508: goto            475
        //   511: astore_1       
        //   512: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   515: ldc             "Unable to open file"
        //   517: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   520: new             Ljava/io/BufferedReader;
        //   523: dup            
        //   524: new             Ljava/io/FileReader;
        //   527: dup            
        //   528: aload           4
        //   530: invokespecial   java/io/FileReader.<init>:(Ljava/io/File;)V
        //   533: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
        //   536: astore_1       
        //   537: iconst_0       
        //   538: istore          7
        //   540: iload           7
        //   542: bipush          32
        //   544: if_icmpge       2042
        //   547: aload_0        
        //   548: aload_1        
        //   549: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //   552: invokevirtual   PFCpack/League.findTeamAbbr:(Ljava/lang/String;)LPFCpack/Team;
        //   555: astore_2       
        //   556: aload_1        
        //   557: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //   560: astore          4
        //   562: aload           4
        //   564: ifnull          2033
        //   567: aload           4
        //   569: ldc             "END_TEAM_HISTORY"
        //   571: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   574: ifne            2033
        //   577: aload_2        
        //   578: getfield        PFCpack/Team.teamHistory:Ljava/util/ArrayList;
        //   581: aload           4
        //   583: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   586: pop            
        //   587: goto            556
        //   590: astore_1       
        //   591: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   594: ldc             "Unable to open file"
        //   596: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   599: new             Ljava/io/BufferedReader;
        //   602: dup            
        //   603: new             Ljava/io/FileReader;
        //   606: dup            
        //   607: aload_3        
        //   608: invokespecial   java/io/FileReader.<init>:(Ljava/io/File;)V
        //   611: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
        //   614: astore_1       
        //   615: aload_1        
        //   616: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //   619: astore_2       
        //   620: aload_2        
        //   621: ifnull          2161
        //   624: aload_2        
        //   625: ldc             "END_REGULAR_SEASON"
        //   627: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   630: ifne            2161
        //   633: aload_2        
        //   634: ldc             ">"
        //   636: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //   639: astore_2       
        //   640: aload_0        
        //   641: aload_2        
        //   642: iconst_0       
        //   643: aaload         
        //   644: invokevirtual   PFCpack/League.findTeamAbbr:(Ljava/lang/String;)LPFCpack/Team;
        //   647: astore_3       
        //   648: iconst_1       
        //   649: istore          7
        //   651: iload           7
        //   653: aload_2        
        //   654: arraylength    
        //   655: if_icmpge       615
        //   658: new             LPFCpack/Game;
        //   661: dup            
        //   662: aload_0        
        //   663: aload_2        
        //   664: iload           7
        //   666: aaload         
        //   667: iload           7
        //   669: iconst_1       
        //   670: isub           
        //   671: invokespecial   PFCpack/Game.<init>:(LPFCpack/League;Ljava/lang/String;I)V
        //   674: astore          4
        //   676: aload           4
        //   678: getfield        PFCpack/Game.homeTeam:LPFCpack/Team;
        //   681: aload_3        
        //   682: if_acmpne       698
        //   685: aload           4
        //   687: getfield        PFCpack/Game.homeScore:I
        //   690: aload           4
        //   692: getfield        PFCpack/Game.awayScore:I
        //   695: if_icmpgt       720
        //   698: aload           4
        //   700: getfield        PFCpack/Game.awayTeam:LPFCpack/Team;
        //   703: aload_3        
        //   704: if_acmpne       2062
        //   707: aload           4
        //   709: getfield        PFCpack/Game.homeScore:I
        //   712: aload           4
        //   714: getfield        PFCpack/Game.awayScore:I
        //   717: if_icmpge       2062
        //   720: aload_3        
        //   721: getfield        PFCpack/Team.gameWLSchedule:Ljava/util/ArrayList;
        //   724: ldc             "W"
        //   726: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   729: pop            
        //   730: iload           7
        //   732: iconst_1       
        //   733: iadd           
        //   734: istore          7
        //   736: goto            651
        //   739: new             LPFCpack/Team;
        //   742: dup            
        //   743: aload           11
        //   745: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   748: aload_0        
        //   749: invokespecial   PFCpack/Team.<init>:(Ljava/lang/String;LPFCpack/League;)V
        //   752: astore          11
        //   754: aload_0        
        //   755: getfield        PFCpack/League.divisions:Ljava/util/ArrayList;
        //   758: aload_0        
        //   759: aload           11
        //   761: getfield        PFCpack/Team.division:Ljava/lang/String;
        //   764: invokevirtual   PFCpack/League.getDivNumber:(Ljava/lang/String;)I
        //   767: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   770: checkcast       LPFCpack/Division;
        //   773: getfield        PFCpack/Division.divTeams:Ljava/util/ArrayList;
        //   776: aload           11
        //   778: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   781: pop            
        //   782: aload_0        
        //   783: getfield        PFCpack/League.teamList:Ljava/util/ArrayList;
        //   786: aload           11
        //   788: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   791: pop            
        //   792: aload           11
        //   794: new             Ljava/util/ArrayList;
        //   797: dup            
        //   798: invokespecial   java/util/ArrayList.<init>:()V
        //   801: putfield        PFCpack/Team.draftPicks:Ljava/util/ArrayList;
        //   804: aload           11
        //   806: new             Ljava/util/ArrayList;
        //   809: dup            
        //   810: invokespecial   java/util/ArrayList.<init>:()V
        //   813: putfield        PFCpack/Team.positionalDraftPicks:Ljava/util/ArrayList;
        //   816: iload           7
        //   818: iconst_1       
        //   819: iadd           
        //   820: istore          7
        //   822: goto            345
        //   825: aload_2        
        //   826: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //   829: pop            
        //   830: aload_2        
        //   831: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //   834: astore          11
        //   836: aload           11
        //   838: ifnull          913
        //   841: aload           11
        //   843: ldc_w           "END_DRAFT_PICKS"
        //   846: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   849: ifne            913
        //   852: aload           11
        //   854: ldc             ">"
        //   856: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //   859: astore          11
        //   861: aload_0        
        //   862: aload           11
        //   864: iconst_0       
        //   865: aaload         
        //   866: invokevirtual   PFCpack/League.findTeamAbbr:(Ljava/lang/String;)LPFCpack/Team;
        //   869: astore          12
        //   871: iconst_1       
        //   872: istore          7
        //   874: iload           7
        //   876: aload           11
        //   878: arraylength    
        //   879: if_icmpge       830
        //   882: aload           12
        //   884: getfield        PFCpack/Team.draftPicks:Ljava/util/ArrayList;
        //   887: new             LPFCpack/DraftPick;
        //   890: dup            
        //   891: aload_0        
        //   892: aload           11
        //   894: iload           7
        //   896: aaload         
        //   897: invokespecial   PFCpack/DraftPick.<init>:(LPFCpack/League;Ljava/lang/String;)V
        //   900: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   903: pop            
        //   904: iload           7
        //   906: iconst_1       
        //   907: iadd           
        //   908: istore          7
        //   910: goto            874
        //   913: aload_2        
        //   914: invokevirtual   java/io/BufferedReader.close:()V
        //   917: goto            402
        //   920: astore_2       
        //   921: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   924: ldc_w           "Error reading file"
        //   927: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   930: goto            402
        //   933: aload_0        
        //   934: new             Ljava/util/ArrayList;
        //   937: dup            
        //   938: invokespecial   java/util/ArrayList.<init>:()V
        //   941: putfield        PFCpack/League.mvpHistory:Ljava/util/ArrayList;
        //   944: aload           11
        //   946: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //   949: astore_1       
        //   950: aload_1        
        //   951: ifnull          989
        //   954: aload_1        
        //   955: ldc_w           "END_MVP_HIST"
        //   958: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   961: ifne            989
        //   964: aload_0        
        //   965: getfield        PFCpack/League.mvpHistory:Ljava/util/ArrayList;
        //   968: aload_1        
        //   969: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   972: pop            
        //   973: goto            944
        //   976: astore_1       
        //   977: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   980: ldc_w           "Error reading file"
        //   983: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   986: goto            520
        //   989: aload           11
        //   991: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //   994: astore_1       
        //   995: aload_1        
        //   996: ifnull          1057
        //   999: aload_1        
        //  1000: ldc_w           "END_LEAGUE_RECORDS"
        //  1003: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1006: ifne            1057
        //  1009: aload_1        
        //  1010: ldc_w           ","
        //  1013: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //  1016: astore_1       
        //  1017: aload_1        
        //  1018: iconst_1       
        //  1019: aaload         
        //  1020: ldc_w           "-1"
        //  1023: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1026: ifne            989
        //  1029: aload_0        
        //  1030: getfield        PFCpack/League.leagueRecords:LPFCpack/LeagueRecords;
        //  1033: aload_1        
        //  1034: iconst_0       
        //  1035: aaload         
        //  1036: aload_1        
        //  1037: iconst_1       
        //  1038: aaload         
        //  1039: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //  1042: aload_1        
        //  1043: iconst_2       
        //  1044: aaload         
        //  1045: aload_1        
        //  1046: iconst_3       
        //  1047: aaload         
        //  1048: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //  1051: invokevirtual   PFCpack/LeagueRecords.checkRecord:(Ljava/lang/String;ILjava/lang/String;I)V
        //  1054: goto            989
        //  1057: aload           11
        //  1059: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //  1062: astore_1       
        //  1063: aload_1        
        //  1064: ifnull          1152
        //  1067: aload_1        
        //  1068: ldc_w           "END_LEAGUE_WIN_STREAK"
        //  1071: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1074: ifne            1152
        //  1077: aload_1        
        //  1078: ldc_w           ","
        //  1081: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //  1084: astore_1       
        //  1085: aload_0        
        //  1086: new             LPFCpack/TeamStreak;
        //  1089: dup            
        //  1090: aload_1        
        //  1091: iconst_2       
        //  1092: aaload         
        //  1093: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //  1096: aload_1        
        //  1097: iconst_3       
        //  1098: aaload         
        //  1099: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //  1102: aload_1        
        //  1103: iconst_0       
        //  1104: aaload         
        //  1105: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //  1108: aload_1        
        //  1109: iconst_1       
        //  1110: aaload         
        //  1111: invokespecial   PFCpack/TeamStreak.<init>:(IIILjava/lang/String;)V
        //  1114: putfield        PFCpack/League.longestWinStreak:LPFCpack/TeamStreak;
        //  1117: aload_0        
        //  1118: new             LPFCpack/TeamStreak;
        //  1121: dup            
        //  1122: aload_1        
        //  1123: iconst_2       
        //  1124: aaload         
        //  1125: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //  1128: aload_1        
        //  1129: iconst_3       
        //  1130: aaload         
        //  1131: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //  1134: aload_1        
        //  1135: iconst_0       
        //  1136: aaload         
        //  1137: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //  1140: aload_1        
        //  1141: iconst_1       
        //  1142: aaload         
        //  1143: invokespecial   PFCpack/TeamStreak.<init>:(IIILjava/lang/String;)V
        //  1146: putfield        PFCpack/League.yearStartLongestWinStreak:LPFCpack/TeamStreak;
        //  1149: goto            1057
        //  1152: aload           11
        //  1154: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //  1157: astore_1       
        //  1158: aload_1        
        //  1159: ifnull          1220
        //  1162: aload_1        
        //  1163: ldc_w           "END_USER_TEAM_RECORDS"
        //  1166: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1169: ifne            1220
        //  1172: aload_1        
        //  1173: ldc_w           ","
        //  1176: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //  1179: astore_1       
        //  1180: aload_1        
        //  1181: iconst_1       
        //  1182: aaload         
        //  1183: ldc_w           "-1"
        //  1186: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1189: ifne            1152
        //  1192: aload_0        
        //  1193: getfield        PFCpack/League.userTeamRecords:LPFCpack/LeagueRecords;
        //  1196: aload_1        
        //  1197: iconst_0       
        //  1198: aaload         
        //  1199: aload_1        
        //  1200: iconst_1       
        //  1201: aaload         
        //  1202: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //  1205: aload_1        
        //  1206: iconst_2       
        //  1207: aaload         
        //  1208: aload_1        
        //  1209: iconst_3       
        //  1210: aaload         
        //  1211: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //  1214: invokevirtual   PFCpack/LeagueRecords.checkRecord:(Ljava/lang/String;ILjava/lang/String;I)V
        //  1217: goto            1152
        //  1220: aload_0        
        //  1221: new             Ljava/util/ArrayList;
        //  1224: dup            
        //  1225: invokespecial   java/util/ArrayList.<init>:()V
        //  1228: putfield        PFCpack/League.hallOfFame:Ljava/util/ArrayList;
        //  1231: aload           11
        //  1233: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //  1236: astore_1       
        //  1237: aload_1        
        //  1238: ifnull          1263
        //  1241: aload_1        
        //  1242: ldc_w           "END_HALL_OF_FAME"
        //  1245: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1248: ifne            1263
        //  1251: aload_0        
        //  1252: getfield        PFCpack/League.hallOfFame:Ljava/util/ArrayList;
        //  1255: aload_1        
        //  1256: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //  1259: pop            
        //  1260: goto            1231
        //  1263: new             Ljava/lang/StringBuilder;
        //  1266: dup            
        //  1267: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1270: astore_1       
        //  1271: aload           11
        //  1273: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //  1276: astore_2       
        //  1277: aload_2        
        //  1278: ifnull          1319
        //  1281: aload_2        
        //  1282: ldc_w           "END_ALL_PRO"
        //  1285: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1288: ifne            1319
        //  1291: aload_1        
        //  1292: new             Ljava/lang/StringBuilder;
        //  1295: dup            
        //  1296: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1299: aload_2        
        //  1300: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1303: ldc_w           "\n"
        //  1306: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1309: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1312: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1315: pop            
        //  1316: goto            1271
        //  1319: aload_0        
        //  1320: aload_1        
        //  1321: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1324: putfield        PFCpack/League.allProStrFull:Ljava/lang/String;
        //  1327: new             Ljava/lang/StringBuilder;
        //  1330: dup            
        //  1331: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1334: astore_1       
        //  1335: aload           11
        //  1337: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //  1340: astore_2       
        //  1341: aload_2        
        //  1342: ifnull          1383
        //  1345: aload_2        
        //  1346: ldc_w           "END_MVP"
        //  1349: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1352: ifne            1383
        //  1355: aload_1        
        //  1356: new             Ljava/lang/StringBuilder;
        //  1359: dup            
        //  1360: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1363: aload_2        
        //  1364: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1367: ldc_w           "\n"
        //  1370: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1373: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1376: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1379: pop            
        //  1380: goto            1335
        //  1383: aload_0        
        //  1384: aload_1        
        //  1385: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1388: putfield        PFCpack/League.mvpWinnerStrFull:Ljava/lang/String;
        //  1391: aload_0        
        //  1392: getfield        PFCpack/League.mvpWinnerStrFull:Ljava/lang/String;
        //  1395: ldc_w           ""
        //  1398: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1401: ifne            2692
        //  1404: aload_0        
        //  1405: iconst_1       
        //  1406: putfield        PFCpack/League.mvpDecided:Z
        //  1409: goto            2692
        //  1412: aload           11
        //  1414: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //  1417: astore_1       
        //  1418: aload_1        
        //  1419: ifnull          1521
        //  1422: aload_1        
        //  1423: ldc_w           "END_PLAYOFF_TEAMS"
        //  1426: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1429: ifne            1521
        //  1432: iload           7
        //  1434: iconst_1       
        //  1435: iadd           
        //  1436: istore          8
        //  1438: iload           7
        //  1440: bipush          6
        //  1442: if_icmpge       1465
        //  1445: aload_0        
        //  1446: getfield        PFCpack/League.playoffsAM:Ljava/util/ArrayList;
        //  1449: aload_0        
        //  1450: aload_1        
        //  1451: invokevirtual   PFCpack/League.findTeamAbbr:(Ljava/lang/String;)LPFCpack/Team;
        //  1454: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //  1457: pop            
        //  1458: iload           8
        //  1460: istore          7
        //  1462: goto            1412
        //  1465: aload_0        
        //  1466: getfield        PFCpack/League.playoffsNA:Ljava/util/ArrayList;
        //  1469: aload_0        
        //  1470: aload_1        
        //  1471: invokevirtual   PFCpack/League.findTeamAbbr:(Ljava/lang/String;)LPFCpack/Team;
        //  1474: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //  1477: pop            
        //  1478: iload           8
        //  1480: istore          7
        //  1482: goto            1412
        //  1485: aload           12
        //  1487: iconst_0       
        //  1488: aaload         
        //  1489: ldc_w           "QB"
        //  1492: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1495: ifeq            1673
        //  1498: aload_0        
        //  1499: getfield        PFCpack/League.freeAgents:Ljava/util/ArrayList;
        //  1502: new             LPFCpack/PlayerQB;
        //  1505: dup            
        //  1506: aload           13
        //  1508: aconst_null    
        //  1509: aload           14
        //  1511: aload           15
        //  1513: aload_1        
        //  1514: invokespecial   PFCpack/PlayerQB.<init>:(Ljava/lang/String;LPFCpack/Team;[ILjava/lang/String;[I)V
        //  1517: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //  1520: pop            
        //  1521: aload           11
        //  1523: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //  1526: astore_1       
        //  1527: aload_1        
        //  1528: ifnull          1980
        //  1531: aload_1        
        //  1532: ldc_w           "END_FREE_AGENTS"
        //  1535: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1538: ifne            1980
        //  1541: aload_1        
        //  1542: ldc             ">"
        //  1544: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //  1547: astore_2       
        //  1548: aload_2        
        //  1549: iconst_0       
        //  1550: aaload         
        //  1551: ldc_w           ","
        //  1554: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //  1557: astore          12
        //  1559: aload           12
        //  1561: iconst_1       
        //  1562: aaload         
        //  1563: astore          13
        //  1565: aload           12
        //  1567: arraylength    
        //  1568: iconst_2       
        //  1569: isub           
        //  1570: newarray        I
        //  1572: astore          14
        //  1574: iconst_0       
        //  1575: istore          7
        //  1577: iload           7
        //  1579: aload           14
        //  1581: arraylength    
        //  1582: if_icmpge       1609
        //  1585: aload           14
        //  1587: iload           7
        //  1589: aload           12
        //  1591: iload           7
        //  1593: iconst_2       
        //  1594: iadd           
        //  1595: aaload         
        //  1596: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //  1599: iastore        
        //  1600: iload           7
        //  1602: iconst_1       
        //  1603: iadd           
        //  1604: istore          7
        //  1606: goto            1577
        //  1609: aload_2        
        //  1610: iconst_1       
        //  1611: aaload         
        //  1612: astore          15
        //  1614: aconst_null    
        //  1615: astore_1       
        //  1616: aload_2        
        //  1617: arraylength    
        //  1618: iconst_3       
        //  1619: if_icmpne       1485
        //  1622: aload_2        
        //  1623: iconst_2       
        //  1624: aaload         
        //  1625: ldc_w           ","
        //  1628: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //  1631: astore          16
        //  1633: aload           16
        //  1635: arraylength    
        //  1636: newarray        I
        //  1638: astore_2       
        //  1639: iconst_0       
        //  1640: istore          7
        //  1642: aload_2        
        //  1643: astore_1       
        //  1644: iload           7
        //  1646: aload           16
        //  1648: arraylength    
        //  1649: if_icmpge       1485
        //  1652: aload_2        
        //  1653: iload           7
        //  1655: aload           16
        //  1657: iload           7
        //  1659: aaload         
        //  1660: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //  1663: iastore        
        //  1664: iload           7
        //  1666: iconst_1       
        //  1667: iadd           
        //  1668: istore          7
        //  1670: goto            1642
        //  1673: aload           12
        //  1675: iconst_0       
        //  1676: aaload         
        //  1677: ldc_w           "RB"
        //  1680: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1683: ifeq            1712
        //  1686: aload_0        
        //  1687: getfield        PFCpack/League.freeAgents:Ljava/util/ArrayList;
        //  1690: new             LPFCpack/PlayerRB;
        //  1693: dup            
        //  1694: aload           13
        //  1696: aconst_null    
        //  1697: aload           14
        //  1699: aload           15
        //  1701: aload_1        
        //  1702: invokespecial   PFCpack/PlayerRB.<init>:(Ljava/lang/String;LPFCpack/Team;[ILjava/lang/String;[I)V
        //  1705: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //  1708: pop            
        //  1709: goto            1521
        //  1712: aload           12
        //  1714: iconst_0       
        //  1715: aaload         
        //  1716: ldc_w           "WR"
        //  1719: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1722: ifeq            1751
        //  1725: aload_0        
        //  1726: getfield        PFCpack/League.freeAgents:Ljava/util/ArrayList;
        //  1729: new             LPFCpack/PlayerWR;
        //  1732: dup            
        //  1733: aload           13
        //  1735: aconst_null    
        //  1736: aload           14
        //  1738: aload           15
        //  1740: aload_1        
        //  1741: invokespecial   PFCpack/PlayerWR.<init>:(Ljava/lang/String;LPFCpack/Team;[ILjava/lang/String;[I)V
        //  1744: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //  1747: pop            
        //  1748: goto            1521
        //  1751: aload           12
        //  1753: iconst_0       
        //  1754: aaload         
        //  1755: ldc_w           "OL"
        //  1758: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1761: ifeq            1789
        //  1764: aload_0        
        //  1765: getfield        PFCpack/League.freeAgents:Ljava/util/ArrayList;
        //  1768: new             LPFCpack/PlayerOL;
        //  1771: dup            
        //  1772: aload           13
        //  1774: aconst_null    
        //  1775: aload           14
        //  1777: aload           15
        //  1779: invokespecial   PFCpack/PlayerOL.<init>:(Ljava/lang/String;LPFCpack/Team;[ILjava/lang/String;)V
        //  1782: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //  1785: pop            
        //  1786: goto            1521
        //  1789: aload           12
        //  1791: iconst_0       
        //  1792: aaload         
        //  1793: ldc_w           "K"
        //  1796: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1799: ifeq            1828
        //  1802: aload_0        
        //  1803: getfield        PFCpack/League.freeAgents:Ljava/util/ArrayList;
        //  1806: new             LPFCpack/PlayerK;
        //  1809: dup            
        //  1810: aload           13
        //  1812: aconst_null    
        //  1813: aload           14
        //  1815: aload           15
        //  1817: aload_1        
        //  1818: invokespecial   PFCpack/PlayerK.<init>:(Ljava/lang/String;LPFCpack/Team;[ILjava/lang/String;[I)V
        //  1821: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //  1824: pop            
        //  1825: goto            1521
        //  1828: aload           12
        //  1830: iconst_0       
        //  1831: aaload         
        //  1832: ldc_w           "S"
        //  1835: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1838: ifeq            1866
        //  1841: aload_0        
        //  1842: getfield        PFCpack/League.freeAgents:Ljava/util/ArrayList;
        //  1845: new             LPFCpack/PlayerS;
        //  1848: dup            
        //  1849: aload           13
        //  1851: aconst_null    
        //  1852: aload           14
        //  1854: aload           15
        //  1856: invokespecial   PFCpack/PlayerS.<init>:(Ljava/lang/String;LPFCpack/Team;[ILjava/lang/String;)V
        //  1859: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //  1862: pop            
        //  1863: goto            1521
        //  1866: aload           12
        //  1868: iconst_0       
        //  1869: aaload         
        //  1870: ldc_w           "CB"
        //  1873: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1876: ifeq            1904
        //  1879: aload_0        
        //  1880: getfield        PFCpack/League.freeAgents:Ljava/util/ArrayList;
        //  1883: new             LPFCpack/PlayerCB;
        //  1886: dup            
        //  1887: aload           13
        //  1889: aconst_null    
        //  1890: aload           14
        //  1892: aload           15
        //  1894: invokespecial   PFCpack/PlayerCB.<init>:(Ljava/lang/String;LPFCpack/Team;[ILjava/lang/String;)V
        //  1897: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //  1900: pop            
        //  1901: goto            1521
        //  1904: aload           12
        //  1906: iconst_0       
        //  1907: aaload         
        //  1908: ldc_w           "DL"
        //  1911: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1914: ifeq            1942
        //  1917: aload_0        
        //  1918: getfield        PFCpack/League.freeAgents:Ljava/util/ArrayList;
        //  1921: new             LPFCpack/PlayerDL;
        //  1924: dup            
        //  1925: aload           13
        //  1927: aconst_null    
        //  1928: aload           14
        //  1930: aload           15
        //  1932: invokespecial   PFCpack/PlayerDL.<init>:(Ljava/lang/String;LPFCpack/Team;[ILjava/lang/String;)V
        //  1935: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //  1938: pop            
        //  1939: goto            1521
        //  1942: aload           12
        //  1944: iconst_0       
        //  1945: aaload         
        //  1946: ldc_w           "LB"
        //  1949: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1952: ifeq            1521
        //  1955: aload_0        
        //  1956: getfield        PFCpack/League.freeAgents:Ljava/util/ArrayList;
        //  1959: new             LPFCpack/PlayerLB;
        //  1962: dup            
        //  1963: aload           13
        //  1965: aconst_null    
        //  1966: aload           14
        //  1968: aload           15
        //  1970: invokespecial   PFCpack/PlayerLB.<init>:(Ljava/lang/String;LPFCpack/Team;[ILjava/lang/String;)V
        //  1973: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //  1976: pop            
        //  1977: goto            1521
        //  1980: aload_0        
        //  1981: iconst_0       
        //  1982: putfield        PFCpack/League.canGoOnline:Z
        //  1985: aload           11
        //  1987: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //  1990: astore_1       
        //  1991: aload_1        
        //  1992: ifnull          2025
        //  1995: aload_1        
        //  1996: ldc_w           "END_GO_ONLINE"
        //  1999: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  2002: ifne            2025
        //  2005: aload_1        
        //  2006: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //  2009: iconst_1       
        //  2010: if_icmpne       2698
        //  2013: iconst_1       
        //  2014: istore          10
        //  2016: aload_0        
        //  2017: iload           10
        //  2019: putfield        PFCpack/League.canGoOnline:Z
        //  2022: goto            1985
        //  2025: aload           11
        //  2027: invokevirtual   java/io/BufferedReader.close:()V
        //  2030: goto            520
        //  2033: iload           7
        //  2035: iconst_1       
        //  2036: iadd           
        //  2037: istore          7
        //  2039: goto            540
        //  2042: aload_1        
        //  2043: invokevirtual   java/io/BufferedReader.close:()V
        //  2046: goto            599
        //  2049: astore_1       
        //  2050: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //  2053: ldc_w           "Error reading file"
        //  2056: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //  2059: goto            599
        //  2062: aload           4
        //  2064: getfield        PFCpack/Game.homeScore:I
        //  2067: ifne            2078
        //  2070: aload           4
        //  2072: getfield        PFCpack/Game.awayScore:I
        //  2075: ifeq            730
        //  2078: aload_3        
        //  2079: getfield        PFCpack/Team.gameWLSchedule:Ljava/util/ArrayList;
        //  2082: ldc_w           "L"
        //  2085: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //  2088: pop            
        //  2089: goto            730
        //  2092: astore_1       
        //  2093: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //  2096: ldc             "Unable to open file"
        //  2098: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //  2101: aload_0        
        //  2102: new             Ljava/util/ArrayList;
        //  2105: dup            
        //  2106: invokespecial   java/util/ArrayList.<init>:()V
        //  2109: putfield        PFCpack/League.nameList:Ljava/util/ArrayList;
        //  2112: aload           5
        //  2114: ldc_w           ","
        //  2117: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //  2120: astore_1       
        //  2121: aload_1        
        //  2122: arraylength    
        //  2123: istore          8
        //  2125: iconst_0       
        //  2126: istore          7
        //  2128: iload           7
        //  2130: iload           8
        //  2132: if_icmpge       2534
        //  2135: aload_1        
        //  2136: iload           7
        //  2138: aaload         
        //  2139: astore_2       
        //  2140: aload_0        
        //  2141: getfield        PFCpack/League.nameList:Ljava/util/ArrayList;
        //  2144: aload_2        
        //  2145: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //  2148: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //  2151: pop            
        //  2152: iload           7
        //  2154: iconst_1       
        //  2155: iadd           
        //  2156: istore          7
        //  2158: goto            2128
        //  2161: aload_0        
        //  2162: getfield        PFCpack/League.teamList:Ljava/util/ArrayList;
        //  2165: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
        //  2168: astore_2       
        //  2169: aload_2        
        //  2170: invokeinterface java/util/Iterator.hasNext:()Z
        //  2175: ifeq            2704
        //  2178: aload_2        
        //  2179: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  2184: checkcast       LPFCpack/Team;
        //  2187: astore_3       
        //  2188: iconst_0       
        //  2189: istore          7
        //  2191: iload           7
        //  2193: bipush          16
        //  2195: if_icmpge       2169
        //  2198: aload_3        
        //  2199: getfield        PFCpack/Team.regSeasonSchedule:[LPFCpack/Game;
        //  2202: iload           7
        //  2204: aaload         
        //  2205: astore          4
        //  2207: aload           4
        //  2209: ifnonnull       2252
        //  2212: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //  2215: new             Ljava/lang/StringBuilder;
        //  2218: dup            
        //  2219: invokespecial   java/lang/StringBuilder.<init>:()V
        //  2222: ldc_w           "GAME: #"
        //  2225: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2228: iload           7
        //  2230: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  2233: ldc_w           " is null for team: "
        //  2236: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2239: aload_3        
        //  2240: getfield        PFCpack/Team.abbr:Ljava/lang/String;
        //  2243: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2246: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  2249: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //  2252: aload_3        
        //  2253: getfield        PFCpack/Team.gameSchedule:Ljava/util/ArrayList;
        //  2256: aload           4
        //  2258: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //  2261: pop            
        //  2262: iload           7
        //  2264: iconst_1       
        //  2265: iadd           
        //  2266: istore          7
        //  2268: goto            2191
        //  2271: aload_1        
        //  2272: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //  2275: astore_2       
        //  2276: aload_2        
        //  2277: ifnull          2527
        //  2280: bipush          16
        //  2282: istore          9
        //  2284: iload           7
        //  2286: iconst_1       
        //  2287: iadd           
        //  2288: istore          8
        //  2290: iload           8
        //  2292: iconst_4       
        //  2293: if_icmple       2443
        //  2296: bipush          17
        //  2298: istore          7
        //  2300: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //  2303: new             Ljava/lang/StringBuilder;
        //  2306: dup            
        //  2307: invokespecial   java/lang/StringBuilder.<init>:()V
        //  2310: ldc_w           "PLAYOFF GAME "
        //  2313: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2316: iload           8
        //  2318: iconst_1       
        //  2319: isub           
        //  2320: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  2323: ldc_w           ": "
        //  2326: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2329: aload_2        
        //  2330: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2333: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  2336: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //  2339: new             LPFCpack/Game;
        //  2342: dup            
        //  2343: aload_0        
        //  2344: aload_2        
        //  2345: iload           7
        //  2347: invokespecial   PFCpack/Game.<init>:(LPFCpack/League;Ljava/lang/String;I)V
        //  2350: astore_2       
        //  2351: aload_0        
        //  2352: getfield        PFCpack/League.playoffGames:[LPFCpack/Game;
        //  2355: iload           8
        //  2357: iconst_1       
        //  2358: isub           
        //  2359: aload_2        
        //  2360: aastore        
        //  2361: aload_2        
        //  2362: getfield        PFCpack/Game.homeTeam:LPFCpack/Team;
        //  2365: getfield        PFCpack/Team.gameSchedule:Ljava/util/ArrayList;
        //  2368: aload_2        
        //  2369: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //  2372: pop            
        //  2373: aload_2        
        //  2374: getfield        PFCpack/Game.awayTeam:LPFCpack/Team;
        //  2377: getfield        PFCpack/Team.gameSchedule:Ljava/util/ArrayList;
        //  2380: aload_2        
        //  2381: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //  2384: pop            
        //  2385: aload_2        
        //  2386: getfield        PFCpack/Game.homeScore:I
        //  2389: aload_2        
        //  2390: getfield        PFCpack/Game.awayScore:I
        //  2393: if_icmple       2475
        //  2396: aload_2        
        //  2397: getfield        PFCpack/Game.homeTeam:LPFCpack/Team;
        //  2400: getfield        PFCpack/Team.gameWLSchedule:Ljava/util/ArrayList;
        //  2403: ldc             "W"
        //  2405: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //  2408: pop            
        //  2409: aload_2        
        //  2410: getfield        PFCpack/Game.awayTeam:LPFCpack/Team;
        //  2413: getfield        PFCpack/Team.gameWLSchedule:Ljava/util/ArrayList;
        //  2416: ldc_w           "L"
        //  2419: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //  2422: pop            
        //  2423: iload           8
        //  2425: istore          7
        //  2427: goto            2271
        //  2430: astore_1       
        //  2431: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //  2434: ldc_w           "Error reading file"
        //  2437: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //  2440: goto            2101
        //  2443: iload           8
        //  2445: bipush          8
        //  2447: if_icmple       2457
        //  2450: bipush          18
        //  2452: istore          7
        //  2454: goto            2300
        //  2457: iload           9
        //  2459: istore          7
        //  2461: iload           8
        //  2463: bipush          10
        //  2465: if_icmple       2300
        //  2468: bipush          19
        //  2470: istore          7
        //  2472: goto            2300
        //  2475: aload_2        
        //  2476: getfield        PFCpack/Game.homeScore:I
        //  2479: ifne            2493
        //  2482: iload           8
        //  2484: istore          7
        //  2486: aload_2        
        //  2487: getfield        PFCpack/Game.awayScore:I
        //  2490: ifeq            2271
        //  2493: aload_2        
        //  2494: getfield        PFCpack/Game.homeTeam:LPFCpack/Team;
        //  2497: getfield        PFCpack/Team.gameWLSchedule:Ljava/util/ArrayList;
        //  2500: ldc_w           "L"
        //  2503: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //  2506: pop            
        //  2507: aload_2        
        //  2508: getfield        PFCpack/Game.awayTeam:LPFCpack/Team;
        //  2511: getfield        PFCpack/Team.gameWLSchedule:Ljava/util/ArrayList;
        //  2514: ldc             "W"
        //  2516: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //  2519: pop            
        //  2520: iload           8
        //  2522: istore          7
        //  2524: goto            2271
        //  2527: aload_1        
        //  2528: invokevirtual   java/io/BufferedReader.close:()V
        //  2531: goto            2101
        //  2534: aload_0        
        //  2535: new             Ljava/util/ArrayList;
        //  2538: dup            
        //  2539: invokespecial   java/util/ArrayList.<init>:()V
        //  2542: putfield        PFCpack/League.lastNameList:Ljava/util/ArrayList;
        //  2545: aload           6
        //  2547: ldc_w           ","
        //  2550: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //  2553: astore_1       
        //  2554: aload_1        
        //  2555: arraylength    
        //  2556: istore          8
        //  2558: iconst_0       
        //  2559: istore          7
        //  2561: iload           7
        //  2563: iload           8
        //  2565: if_icmpge       2594
        //  2568: aload_1        
        //  2569: iload           7
        //  2571: aaload         
        //  2572: astore_2       
        //  2573: aload_0        
        //  2574: getfield        PFCpack/League.lastNameList:Ljava/util/ArrayList;
        //  2577: aload_2        
        //  2578: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //  2581: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //  2584: pop            
        //  2585: iload           7
        //  2587: iconst_1       
        //  2588: iadd           
        //  2589: istore          7
        //  2591: goto            2561
        //  2594: aload_0        
        //  2595: invokevirtual   PFCpack/League.updateLongestActiveWinStreak:()V
        //  2598: aload_0        
        //  2599: new             Ljava/util/ArrayList;
        //  2602: dup            
        //  2603: invokespecial   java/util/ArrayList.<init>:()V
        //  2606: putfield        PFCpack/League.newsStories:Ljava/util/ArrayList;
        //  2609: iconst_0       
        //  2610: istore          7
        //  2612: iload           7
        //  2614: bipush          25
        //  2616: if_icmpge       2643
        //  2619: aload_0        
        //  2620: getfield        PFCpack/League.newsStories:Ljava/util/ArrayList;
        //  2623: new             Ljava/util/ArrayList;
        //  2626: dup            
        //  2627: invokespecial   java/util/ArrayList.<init>:()V
        //  2630: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //  2633: pop            
        //  2634: iload           7
        //  2636: iconst_1       
        //  2637: iadd           
        //  2638: istore          7
        //  2640: goto            2612
        //  2643: aload_0        
        //  2644: getfield        PFCpack/League.currentWeek:I
        //  2647: ifne            2691
        //  2650: aload_0        
        //  2651: iconst_0       
        //  2652: putfield        PFCpack/League.mvpDecided:Z
        //  2655: aload_0        
        //  2656: new             Ljava/util/ArrayList;
        //  2659: dup            
        //  2660: invokespecial   java/util/ArrayList.<init>:()V
        //  2663: putfield        PFCpack/League.mvpCandidates:Ljava/util/ArrayList;
        //  2666: aload_0        
        //  2667: ldc_w           ""
        //  2670: putfield        PFCpack/League.mvpWinnerStrFull:Ljava/lang/String;
        //  2673: aload_0        
        //  2674: ldc_w           ""
        //  2677: putfield        PFCpack/League.allProStrFull:Ljava/lang/String;
        //  2680: aload_0        
        //  2681: new             Ljava/util/ArrayList;
        //  2684: dup            
        //  2685: invokespecial   java/util/ArrayList.<init>:()V
        //  2688: putfield        PFCpack/League.allPros:Ljava/util/ArrayList;
        //  2691: return         
        //  2692: iconst_0       
        //  2693: istore          7
        //  2695: goto            1412
        //  2698: iconst_0       
        //  2699: istore          10
        //  2701: goto            2016
        //  2704: iconst_0       
        //  2705: istore          7
        //  2707: goto            2271
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  326    342    393    402    Ljava/io/FileNotFoundException;
        //  326    342    920    933    Ljava/io/IOException;
        //  352    361    393    402    Ljava/io/FileNotFoundException;
        //  352    361    920    933    Ljava/io/IOException;
        //  361    367    393    402    Ljava/io/FileNotFoundException;
        //  361    367    920    933    Ljava/io/IOException;
        //  372    390    393    402    Ljava/io/FileNotFoundException;
        //  372    390    920    933    Ljava/io/IOException;
        //  402    475    511    520    Ljava/io/FileNotFoundException;
        //  402    475    976    989    Ljava/io/IOException;
        //  475    481    511    520    Ljava/io/FileNotFoundException;
        //  475    481    976    989    Ljava/io/IOException;
        //  485    508    511    520    Ljava/io/FileNotFoundException;
        //  485    508    976    989    Ljava/io/IOException;
        //  520    537    590    599    Ljava/io/FileNotFoundException;
        //  520    537    2049   2062   Ljava/io/IOException;
        //  547    556    590    599    Ljava/io/FileNotFoundException;
        //  547    556    2049   2062   Ljava/io/IOException;
        //  556    562    590    599    Ljava/io/FileNotFoundException;
        //  556    562    2049   2062   Ljava/io/IOException;
        //  567    587    590    599    Ljava/io/FileNotFoundException;
        //  567    587    2049   2062   Ljava/io/IOException;
        //  599    615    2092   2101   Ljava/io/FileNotFoundException;
        //  599    615    2430   2443   Ljava/io/IOException;
        //  615    620    2092   2101   Ljava/io/FileNotFoundException;
        //  615    620    2430   2443   Ljava/io/IOException;
        //  624    648    2092   2101   Ljava/io/FileNotFoundException;
        //  624    648    2430   2443   Ljava/io/IOException;
        //  651    698    2092   2101   Ljava/io/FileNotFoundException;
        //  651    698    2430   2443   Ljava/io/IOException;
        //  698    720    2092   2101   Ljava/io/FileNotFoundException;
        //  698    720    2430   2443   Ljava/io/IOException;
        //  720    730    2092   2101   Ljava/io/FileNotFoundException;
        //  720    730    2430   2443   Ljava/io/IOException;
        //  739    816    393    402    Ljava/io/FileNotFoundException;
        //  739    816    920    933    Ljava/io/IOException;
        //  825    830    393    402    Ljava/io/FileNotFoundException;
        //  825    830    920    933    Ljava/io/IOException;
        //  830    836    393    402    Ljava/io/FileNotFoundException;
        //  830    836    920    933    Ljava/io/IOException;
        //  841    871    393    402    Ljava/io/FileNotFoundException;
        //  841    871    920    933    Ljava/io/IOException;
        //  874    904    393    402    Ljava/io/FileNotFoundException;
        //  874    904    920    933    Ljava/io/IOException;
        //  913    917    393    402    Ljava/io/FileNotFoundException;
        //  913    917    920    933    Ljava/io/IOException;
        //  933    944    511    520    Ljava/io/FileNotFoundException;
        //  933    944    976    989    Ljava/io/IOException;
        //  944    950    511    520    Ljava/io/FileNotFoundException;
        //  944    950    976    989    Ljava/io/IOException;
        //  954    973    511    520    Ljava/io/FileNotFoundException;
        //  954    973    976    989    Ljava/io/IOException;
        //  989    995    511    520    Ljava/io/FileNotFoundException;
        //  989    995    976    989    Ljava/io/IOException;
        //  999    1054   511    520    Ljava/io/FileNotFoundException;
        //  999    1054   976    989    Ljava/io/IOException;
        //  1057   1063   511    520    Ljava/io/FileNotFoundException;
        //  1057   1063   976    989    Ljava/io/IOException;
        //  1067   1149   511    520    Ljava/io/FileNotFoundException;
        //  1067   1149   976    989    Ljava/io/IOException;
        //  1152   1158   511    520    Ljava/io/FileNotFoundException;
        //  1152   1158   976    989    Ljava/io/IOException;
        //  1162   1217   511    520    Ljava/io/FileNotFoundException;
        //  1162   1217   976    989    Ljava/io/IOException;
        //  1220   1231   511    520    Ljava/io/FileNotFoundException;
        //  1220   1231   976    989    Ljava/io/IOException;
        //  1231   1237   511    520    Ljava/io/FileNotFoundException;
        //  1231   1237   976    989    Ljava/io/IOException;
        //  1241   1260   511    520    Ljava/io/FileNotFoundException;
        //  1241   1260   976    989    Ljava/io/IOException;
        //  1263   1271   511    520    Ljava/io/FileNotFoundException;
        //  1263   1271   976    989    Ljava/io/IOException;
        //  1271   1277   511    520    Ljava/io/FileNotFoundException;
        //  1271   1277   976    989    Ljava/io/IOException;
        //  1281   1316   511    520    Ljava/io/FileNotFoundException;
        //  1281   1316   976    989    Ljava/io/IOException;
        //  1319   1335   511    520    Ljava/io/FileNotFoundException;
        //  1319   1335   976    989    Ljava/io/IOException;
        //  1335   1341   511    520    Ljava/io/FileNotFoundException;
        //  1335   1341   976    989    Ljava/io/IOException;
        //  1345   1380   511    520    Ljava/io/FileNotFoundException;
        //  1345   1380   976    989    Ljava/io/IOException;
        //  1383   1409   511    520    Ljava/io/FileNotFoundException;
        //  1383   1409   976    989    Ljava/io/IOException;
        //  1412   1418   511    520    Ljava/io/FileNotFoundException;
        //  1412   1418   976    989    Ljava/io/IOException;
        //  1422   1432   511    520    Ljava/io/FileNotFoundException;
        //  1422   1432   976    989    Ljava/io/IOException;
        //  1445   1458   511    520    Ljava/io/FileNotFoundException;
        //  1445   1458   976    989    Ljava/io/IOException;
        //  1465   1478   511    520    Ljava/io/FileNotFoundException;
        //  1465   1478   976    989    Ljava/io/IOException;
        //  1485   1521   511    520    Ljava/io/FileNotFoundException;
        //  1485   1521   976    989    Ljava/io/IOException;
        //  1521   1527   511    520    Ljava/io/FileNotFoundException;
        //  1521   1527   976    989    Ljava/io/IOException;
        //  1531   1559   511    520    Ljava/io/FileNotFoundException;
        //  1531   1559   976    989    Ljava/io/IOException;
        //  1565   1574   511    520    Ljava/io/FileNotFoundException;
        //  1565   1574   976    989    Ljava/io/IOException;
        //  1577   1600   511    520    Ljava/io/FileNotFoundException;
        //  1577   1600   976    989    Ljava/io/IOException;
        //  1616   1639   511    520    Ljava/io/FileNotFoundException;
        //  1616   1639   976    989    Ljava/io/IOException;
        //  1644   1664   511    520    Ljava/io/FileNotFoundException;
        //  1644   1664   976    989    Ljava/io/IOException;
        //  1673   1709   511    520    Ljava/io/FileNotFoundException;
        //  1673   1709   976    989    Ljava/io/IOException;
        //  1712   1748   511    520    Ljava/io/FileNotFoundException;
        //  1712   1748   976    989    Ljava/io/IOException;
        //  1751   1786   511    520    Ljava/io/FileNotFoundException;
        //  1751   1786   976    989    Ljava/io/IOException;
        //  1789   1825   511    520    Ljava/io/FileNotFoundException;
        //  1789   1825   976    989    Ljava/io/IOException;
        //  1828   1863   511    520    Ljava/io/FileNotFoundException;
        //  1828   1863   976    989    Ljava/io/IOException;
        //  1866   1901   511    520    Ljava/io/FileNotFoundException;
        //  1866   1901   976    989    Ljava/io/IOException;
        //  1904   1939   511    520    Ljava/io/FileNotFoundException;
        //  1904   1939   976    989    Ljava/io/IOException;
        //  1942   1977   511    520    Ljava/io/FileNotFoundException;
        //  1942   1977   976    989    Ljava/io/IOException;
        //  1980   1985   511    520    Ljava/io/FileNotFoundException;
        //  1980   1985   976    989    Ljava/io/IOException;
        //  1985   1991   511    520    Ljava/io/FileNotFoundException;
        //  1985   1991   976    989    Ljava/io/IOException;
        //  1995   2013   511    520    Ljava/io/FileNotFoundException;
        //  1995   2013   976    989    Ljava/io/IOException;
        //  2016   2022   511    520    Ljava/io/FileNotFoundException;
        //  2016   2022   976    989    Ljava/io/IOException;
        //  2025   2030   511    520    Ljava/io/FileNotFoundException;
        //  2025   2030   976    989    Ljava/io/IOException;
        //  2042   2046   590    599    Ljava/io/FileNotFoundException;
        //  2042   2046   2049   2062   Ljava/io/IOException;
        //  2062   2078   2092   2101   Ljava/io/FileNotFoundException;
        //  2062   2078   2430   2443   Ljava/io/IOException;
        //  2078   2089   2092   2101   Ljava/io/FileNotFoundException;
        //  2078   2089   2430   2443   Ljava/io/IOException;
        //  2161   2169   2092   2101   Ljava/io/FileNotFoundException;
        //  2161   2169   2430   2443   Ljava/io/IOException;
        //  2169   2188   2092   2101   Ljava/io/FileNotFoundException;
        //  2169   2188   2430   2443   Ljava/io/IOException;
        //  2198   2207   2092   2101   Ljava/io/FileNotFoundException;
        //  2198   2207   2430   2443   Ljava/io/IOException;
        //  2212   2252   2092   2101   Ljava/io/FileNotFoundException;
        //  2212   2252   2430   2443   Ljava/io/IOException;
        //  2252   2262   2092   2101   Ljava/io/FileNotFoundException;
        //  2252   2262   2430   2443   Ljava/io/IOException;
        //  2271   2276   2092   2101   Ljava/io/FileNotFoundException;
        //  2271   2276   2430   2443   Ljava/io/IOException;
        //  2300   2423   2092   2101   Ljava/io/FileNotFoundException;
        //  2300   2423   2430   2443   Ljava/io/IOException;
        //  2475   2482   2092   2101   Ljava/io/FileNotFoundException;
        //  2475   2482   2430   2443   Ljava/io/IOException;
        //  2486   2493   2092   2101   Ljava/io/FileNotFoundException;
        //  2486   2493   2430   2443   Ljava/io/IOException;
        //  2493   2520   2092   2101   Ljava/io/FileNotFoundException;
        //  2493   2520   2430   2443   Ljava/io/IOException;
        //  2527   2531   2092   2101   Ljava/io/FileNotFoundException;
        //  2527   2531   2430   2443   Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 1324, Size: 1324
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:713)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:549)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public League(final String s, String s2, final File file) {
        this.isHardMode = false;
        this.mvpDecided = false;
        this.canGoOnline = false;
        this.playoffGames = new Game[11];
        this.leagueHistory = new ArrayList<String[]>();
        this.mvpHistory = new ArrayList<String>();
        this.hallOfFame = new ArrayList<String>();
        this.currentWeek = 0;
        (this.divisions = new ArrayList<Division>()).add(new Division("AMNOR", this));
        this.divisions.add(new Division("AMEAS", this));
        this.divisions.add(new Division("AMSOU", this));
        this.divisions.add(new Division("AMWES", this));
        this.divisions.add(new Division("NANOR", this));
        this.divisions.add(new Division("NAEAS", this));
        this.divisions.add(new Division("NASOU", this));
        this.divisions.add(new Division("NAWES", this));
        this.allPros = new ArrayList<Player>();
        this.playoffsAM = new ArrayList<Team>();
        this.playoffsNA = new ArrayList<Team>();
        this.freeAgents = new ArrayList<Player>();
        this.tradeLog = new ArrayList<Trade>();
        this.leagueRecords = new LeagueRecords();
        this.userTeamRecords = new LeagueRecords();
        this.longestWinStreak = new TeamStreak(this.getYear(), this.getYear(), 0, "XXX");
        this.yearStartLongestWinStreak = new TeamStreak(this.getYear(), this.getYear(), 0, "XXX");
        this.longestActiveWinStreak = new TeamStreak(this.getYear(), this.getYear(), 0, "XXX");
        this.nameList = new ArrayList<String>();
        final String[] split = s.split(",");
        for (int length = split.length, i = 0; i < length; ++i) {
            this.nameList.add(split[i].trim());
        }
        this.lastNameList = new ArrayList<String>();
        final String[] split2 = s2.split(",");
        for (int length2 = split2.length, j = 0; j < length2; ++j) {
            s2 = split2[j];
            this.lastNameList.add(s2.trim());
        }
        this.teamList = new ArrayList<Team>();
        try {
            final BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            if (0 >= 32) {
                goto Label_0763;
            }
            final StringBuilder sb = new StringBuilder();
            while (true) {
                final String line = bufferedReader.readLine();
                if (line == null || line.equals("END_PLAYERS")) {
                    goto Label_0630;
                }
                if (line.substring(0, 1).equals("#")) {
                    continue;
                }
                sb.append(line).append("%");
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println("Unable to open file");
        }
        catch (IOException ex2) {
            System.out.println("Error reading file");
            goto Label_0585;
        }
        this.newsStories.get(0).add("New Season!>Ready for the new season, coach? Whether the National Championship is on your mind, or just a winning season, good luck!");
        this.setUpSchedule();
    }
    
    public League(final String s, String s2, final boolean isHardMode) {
        this.isHardMode = isHardMode;
        this.canGoOnline = true;
        this.mvpDecided = false;
        this.playoffGames = new Game[11];
        this.leagueHistory = new ArrayList<String[]>();
        this.mvpHistory = new ArrayList<String>();
        this.hallOfFame = new ArrayList<String>();
        this.currentWeek = 0;
        (this.divisions = new ArrayList<Division>()).add(new Division("AMNOR", this));
        this.divisions.add(new Division("AMEAS", this));
        this.divisions.add(new Division("AMSOU", this));
        this.divisions.add(new Division("AMWES", this));
        this.divisions.add(new Division("NANOR", this));
        this.divisions.add(new Division("NAEAS", this));
        this.divisions.add(new Division("NASOU", this));
        this.divisions.add(new Division("NAWES", this));
        this.allPros = new ArrayList<Player>();
        this.playoffsAM = new ArrayList<Team>();
        this.playoffsNA = new ArrayList<Team>();
        this.freeAgents = new ArrayList<Player>();
        this.tradeLog = new ArrayList<Trade>();
        this.newsStories = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < 25; ++i) {
            this.newsStories.add(new ArrayList<String>());
        }
        this.newsStories.get(0).add("New Season!>Ready for the new season, coach? Whether the National Championship is on your mind, or just a winning season, good luck!");
        this.leagueRecords = new LeagueRecords();
        this.userTeamRecords = new LeagueRecords();
        this.longestWinStreak = new TeamStreak(this.getYear(), this.getYear(), 0, "XXX");
        this.yearStartLongestWinStreak = new TeamStreak(this.getYear(), this.getYear(), 0, "XXX");
        this.longestActiveWinStreak = new TeamStreak(this.getYear(), this.getYear(), 0, "XXX");
        this.nameList = new ArrayList<String>();
        final String[] split = s.split(",");
        for (int length = split.length, j = 0; j < length; ++j) {
            this.nameList.add(split[j].trim());
        }
        this.lastNameList = new ArrayList<String>();
        final String[] split2 = s2.split(",");
        for (int length2 = split2.length, k = 0; k < length2; ++k) {
            s2 = split2[k];
            this.lastNameList.add(s2.trim());
        }
        final Iterator<Division> iterator = this.divisions.iterator();
        while (iterator.hasNext()) {
            iterator.next().generateTeams();
        }
        this.teamList = new ArrayList<Team>();
        for (int l = 0; l < this.divisions.size(); ++l) {
            for (int n = 0; n < this.divisions.get(l).divTeams.size(); ++n) {
                this.teamList.add(this.divisions.get(l).divTeams.get(n));
            }
        }
        this.setUpSchedule();
    }
    
    public void advanceSeason() {
        this.updateTeamHistories();
        this.updateLeagueHistory();
        this.currentWeek = 0;
        for (int i = 0; i < this.teamList.size(); ++i) {
            this.teamList.get(i).advanceSeason();
        }
        this.advanceSeasonWinStreaks();
        for (int j = 0; j < this.divisions.size(); ++j) {
            this.divisions.get(j).robinWeek = 0;
            this.divisions.get(j).week = 0;
        }
        this.setUpSchedule();
    }
    
    public void advanceSeasonWinStreaks() {
        this.yearStartLongestWinStreak = this.longestWinStreak;
        for (final Team team : this.teamList) {
            team.yearStartWinStreak = team.winStreak;
        }
    }
    
    public void changeAbbrHistoryRecords(final String s, final String s2) {
        this.leagueRecords.changeAbbrRecords(this.userTeam.abbr, s2);
        this.userTeamRecords.changeAbbrRecords(this.userTeam.abbr, s2);
        this.changeAbbrWinStreaks(this.userTeam.abbr, s2);
        this.userTeam.winStreak.changeAbbr(s2);
        this.userTeam.yearStartWinStreak.changeAbbr(s2);
        for (final String[] array : this.leagueHistory) {
            for (int i = 0; i < array.length; ++i) {
                if (array[i].split(" ")[0].equals(s)) {
                    array[i] = s2 + " " + array[i].split(" ")[1];
                }
            }
        }
        for (int j = 0; j < this.mvpHistory.size(); ++j) {
            final String s3 = this.mvpHistory.get(j);
            if (s3.split(" ")[4].equals(s)) {
                this.mvpHistory.set(j, s3.split(" ")[0] + " " + s3.split(" ")[1] + " " + s3.split(" ")[2] + " " + s3.split(" ")[3] + " " + s2 + " " + s3.split(" ")[5]);
            }
        }
    }
    
    public void changeAbbrWinStreaks(final String s, final String s2) {
        if (this.longestWinStreak.getTeam().equals(s)) {
            this.longestWinStreak.changeAbbr(s2);
        }
        if (this.yearStartLongestWinStreak.getTeam().equals(s)) {
            this.yearStartLongestWinStreak.changeAbbr(s2);
        }
    }
    
    public void checkHallofFame(final ArrayList<Player> list) {
        for (final Player player : list) {
            final int statsWins = player.statsWins;
            final int careerWins = player.careerWins;
            final int careerAllPro = player.careerAllPro;
            int n;
            if (player.wonAllPro) {
                n = 1;
            }
            else {
                n = 0;
            }
            final int careerMVP = player.careerMVP;
            int n2;
            if (player.wonMVP) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
            if ((statsWins + careerWins) / 5 + (careerAllPro + n) * 10 + (careerMVP + n2) * 50 > 50) {
                final ArrayList<String> careerStatsList = player.getCareerStatsList();
                final StringBuilder sb = new StringBuilder();
                sb.append(this.getYear() - 1 + ": " + player.getPosNameYrOvr_Str() + "&");
                final Iterator<String> iterator2 = careerStatsList.iterator();
                while (iterator2.hasNext()) {
                    sb.append(iterator2.next() + "&");
                }
                this.hallOfFame.add(sb.toString());
            }
        }
    }
    
    public void checkLeagueRecords() {
        final Iterator<Team> iterator = this.teamList.iterator();
        while (iterator.hasNext()) {
            iterator.next().checkLeagueRecords(this.leagueRecords);
        }
        this.userTeam.checkLeagueRecords(this.userTeamRecords);
    }
    
    public void checkLongestWinStreak(final TeamStreak teamStreak) {
        if (teamStreak.getStreakLength() > this.longestWinStreak.getStreakLength()) {
            this.longestWinStreak = new TeamStreak(teamStreak.getStartYear(), teamStreak.getEndYear(), teamStreak.getStreakLength(), teamStreak.getTeam());
        }
    }
    
    public void curePlayers() {
        final Iterator<Team> iterator = this.teamList.iterator();
        while (iterator.hasNext()) {
            iterator.next().curePlayers();
        }
    }
    
    public void exportRosters(final File p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_1        
        //     5: invokespecial   java/io/PrintWriter.<init>:(Ljava/io/File;)V
        //     8: astore_2       
        //     9: aload_2        
        //    10: ldc_w           ""
        //    13: invokevirtual   java/io/PrintWriter.print:(Ljava/lang/String;)V
        //    16: aload_2        
        //    17: invokevirtual   java/io/PrintWriter.close:()V
        //    20: new             Ljava/io/FileWriter;
        //    23: dup            
        //    24: aload_1        
        //    25: iconst_1       
        //    26: invokespecial   java/io/FileWriter.<init>:(Ljava/io/File;Z)V
        //    29: astore_3       
        //    30: new             Ljava/io/BufferedWriter;
        //    33: dup            
        //    34: aload_3        
        //    35: invokespecial   java/io/BufferedWriter.<init>:(Ljava/io/Writer;)V
        //    38: astore          4
        //    40: new             Ljava/io/PrintWriter;
        //    43: dup            
        //    44: aload           4
        //    46: invokespecial   java/io/PrintWriter.<init>:(Ljava/io/Writer;)V
        //    49: astore          5
        //    51: aconst_null    
        //    52: astore_2       
        //    53: aload_0        
        //    54: getfield        PFCpack/League.divisions:Ljava/util/ArrayList;
        //    57: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
        //    60: astore_1       
        //    61: aload_1        
        //    62: invokeinterface java/util/Iterator.hasNext:()Z
        //    67: ifeq            292
        //    70: aload_1        
        //    71: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    76: checkcast       LPFCpack/Division;
        //    79: getfield        PFCpack/Division.divTeams:Ljava/util/ArrayList;
        //    82: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
        //    85: astore          6
        //    87: aload           6
        //    89: invokeinterface java/util/Iterator.hasNext:()Z
        //    94: ifeq            61
        //    97: aload           6
        //    99: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   104: checkcast       LPFCpack/Team;
        //   107: astore          7
        //   109: aload           5
        //   111: new             Ljava/lang/StringBuilder;
        //   114: dup            
        //   115: invokespecial   java/lang/StringBuilder.<init>:()V
        //   118: aload           7
        //   120: getfield        PFCpack/Team.name:Ljava/lang/String;
        //   123: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   126: ldc_w           ","
        //   129: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   132: aload           7
        //   134: getfield        PFCpack/Team.abbr:Ljava/lang/String;
        //   137: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   140: ldc_w           "\n"
        //   143: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   146: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   149: invokevirtual   java/io/PrintWriter.print:(Ljava/lang/String;)V
        //   152: aload           7
        //   154: invokevirtual   PFCpack/Team.getAllPlayers:()Ljava/util/ArrayList;
        //   157: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
        //   160: astore          7
        //   162: aload           7
        //   164: invokeinterface java/util/Iterator.hasNext:()Z
        //   169: ifeq            277
        //   172: aload           5
        //   174: aload           7
        //   176: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   181: checkcast       LPFCpack/Player;
        //   184: invokevirtual   PFCpack/Player.getCSVRosterFile:()Ljava/lang/String;
        //   187: invokevirtual   java/io/PrintWriter.println:(Ljava/lang/String;)V
        //   190: goto            162
        //   193: astore_2       
        //   194: aload_2        
        //   195: athrow         
        //   196: astore_1       
        //   197: aload           5
        //   199: ifnull          211
        //   202: aload_2        
        //   203: ifnull          370
        //   206: aload           5
        //   208: invokevirtual   java/io/PrintWriter.close:()V
        //   211: aload_1        
        //   212: athrow         
        //   213: astore_2       
        //   214: aload_2        
        //   215: athrow         
        //   216: astore_1       
        //   217: aload           4
        //   219: ifnull          231
        //   222: aload_2        
        //   223: ifnull          406
        //   226: aload           4
        //   228: invokevirtual   java/io/BufferedWriter.close:()V
        //   231: aload_1        
        //   232: athrow         
        //   233: astore_1       
        //   234: aload_1        
        //   235: athrow         
        //   236: astore_2       
        //   237: aload_3        
        //   238: ifnull          249
        //   241: aload_1        
        //   242: ifnull          428
        //   245: aload_3        
        //   246: invokevirtual   java/io/FileWriter.close:()V
        //   249: aload_2        
        //   250: athrow         
        //   251: astore_1       
        //   252: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   255: aload_1        
        //   256: invokevirtual   java/io/IOException.toString:()Ljava/lang/String;
        //   259: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   262: return         
        //   263: astore_2       
        //   264: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   267: aload_2        
        //   268: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //   271: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   274: goto            20
        //   277: aload           5
        //   279: ldc_w           "END_PLAYERS\n"
        //   282: invokevirtual   java/io/PrintWriter.print:(Ljava/lang/String;)V
        //   285: goto            87
        //   288: astore_1       
        //   289: goto            197
        //   292: aload           5
        //   294: ifnull          306
        //   297: iconst_0       
        //   298: ifeq            351
        //   301: aload           5
        //   303: invokevirtual   java/io/PrintWriter.close:()V
        //   306: aload           4
        //   308: ifnull          320
        //   311: iconst_0       
        //   312: ifeq            387
        //   315: aload           4
        //   317: invokevirtual   java/io/BufferedWriter.close:()V
        //   320: aload_3        
        //   321: ifnull          262
        //   324: iconst_0       
        //   325: ifeq            414
        //   328: aload_3        
        //   329: invokevirtual   java/io/FileWriter.close:()V
        //   332: return         
        //   333: astore_1       
        //   334: new             Ljava/lang/NullPointerException;
        //   337: dup            
        //   338: invokespecial   java/lang/NullPointerException.<init>:()V
        //   341: athrow         
        //   342: astore_1       
        //   343: new             Ljava/lang/NullPointerException;
        //   346: dup            
        //   347: invokespecial   java/lang/NullPointerException.<init>:()V
        //   350: athrow         
        //   351: aload           5
        //   353: invokevirtual   java/io/PrintWriter.close:()V
        //   356: goto            306
        //   359: astore          5
        //   361: aload_2        
        //   362: aload           5
        //   364: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   367: goto            211
        //   370: aload           5
        //   372: invokevirtual   java/io/PrintWriter.close:()V
        //   375: goto            211
        //   378: astore_1       
        //   379: new             Ljava/lang/NullPointerException;
        //   382: dup            
        //   383: invokespecial   java/lang/NullPointerException.<init>:()V
        //   386: athrow         
        //   387: aload           4
        //   389: invokevirtual   java/io/BufferedWriter.close:()V
        //   392: goto            320
        //   395: astore          4
        //   397: aload_2        
        //   398: aload           4
        //   400: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   403: goto            231
        //   406: aload           4
        //   408: invokevirtual   java/io/BufferedWriter.close:()V
        //   411: goto            231
        //   414: aload_3        
        //   415: invokevirtual   java/io/FileWriter.close:()V
        //   418: return         
        //   419: astore_3       
        //   420: aload_1        
        //   421: aload_3        
        //   422: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   425: goto            249
        //   428: aload_3        
        //   429: invokevirtual   java/io/FileWriter.close:()V
        //   432: goto            249
        //   435: astore_1       
        //   436: aconst_null    
        //   437: astore_2       
        //   438: goto            217
        //   441: astore_2       
        //   442: aconst_null    
        //   443: astore_1       
        //   444: goto            237
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      20     263    277    Ljava/lang/Exception;
        //  20     30     251    262    Ljava/io/IOException;
        //  30     40     233    237    Ljava/lang/Throwable;
        //  30     40     441    447    Any
        //  40     51     213    217    Ljava/lang/Throwable;
        //  40     51     435    441    Any
        //  53     61     193    197    Ljava/lang/Throwable;
        //  53     61     288    292    Any
        //  61     87     193    197    Ljava/lang/Throwable;
        //  61     87     288    292    Any
        //  87     162    193    197    Ljava/lang/Throwable;
        //  87     162    288    292    Any
        //  162    190    193    197    Ljava/lang/Throwable;
        //  162    190    288    292    Any
        //  194    196    196    197    Any
        //  206    211    359    370    Ljava/lang/Throwable;
        //  206    211    435    441    Any
        //  211    213    213    217    Ljava/lang/Throwable;
        //  211    213    435    441    Any
        //  214    216    216    217    Any
        //  226    231    395    406    Ljava/lang/Throwable;
        //  226    231    441    447    Any
        //  231    233    233    237    Ljava/lang/Throwable;
        //  231    233    441    447    Any
        //  234    236    236    237    Any
        //  245    249    419    428    Ljava/lang/Throwable;
        //  245    249    251    262    Ljava/io/IOException;
        //  249    251    251    262    Ljava/io/IOException;
        //  277    285    193    197    Ljava/lang/Throwable;
        //  277    285    288    292    Any
        //  301    306    342    351    Ljava/lang/Throwable;
        //  301    306    435    441    Any
        //  315    320    378    387    Ljava/lang/Throwable;
        //  315    320    441    447    Any
        //  328    332    333    342    Ljava/lang/Throwable;
        //  328    332    251    262    Ljava/io/IOException;
        //  334    342    251    262    Ljava/io/IOException;
        //  343    351    213    217    Ljava/lang/Throwable;
        //  343    351    435    441    Any
        //  351    356    213    217    Ljava/lang/Throwable;
        //  351    356    435    441    Any
        //  361    367    213    217    Ljava/lang/Throwable;
        //  361    367    435    441    Any
        //  370    375    213    217    Ljava/lang/Throwable;
        //  370    375    435    441    Any
        //  379    387    233    237    Ljava/lang/Throwable;
        //  379    387    441    447    Any
        //  387    392    233    237    Ljava/lang/Throwable;
        //  387    392    441    447    Any
        //  397    403    233    237    Ljava/lang/Throwable;
        //  397    403    441    447    Any
        //  406    411    233    237    Ljava/lang/Throwable;
        //  406    411    441    447    Any
        //  414    418    251    262    Ljava/io/IOException;
        //  420    425    251    262    Ljava/io/IOException;
        //  428    432    251    262    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 207, Size: 207
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3569)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3435)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3435)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3435)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public Division findDivision(final String s) {
        for (int i = 0; i < this.teamList.size(); ++i) {
            if (this.divisions.get(i).divName.equals(s)) {
                return this.divisions.get(i);
            }
        }
        return this.divisions.get(0);
    }
    
    public Team findTeam(final String s) {
        for (int i = 0; i < this.teamList.size(); ++i) {
            if (this.teamList.get(i).strRep().equals(s)) {
                return this.teamList.get(i);
            }
        }
        return this.teamList.get(0);
    }
    
    public Team findTeamAbbr(final String s) {
        for (int i = 0; i < this.teamList.size(); ++i) {
            if (this.teamList.get(i).abbr.equals(s)) {
                return this.teamList.get(i);
            }
        }
        return this.teamList.get(0);
    }
    
    public void freeAgency() {
        this.sortFAs();
        int i = 0;
        while (i < this.freeAgents.size()) {
            if (PlayerPickerLogic.signFreeAgentForTeam(this.teamList, this.freeAgents.get(i), null, 70, 73, false)) {
                this.freeAgents.remove(i);
            }
            else {
                ++i;
            }
        }
        for (final Player player : this.freeAgents) {
            System.out.println("FA LEFT OVER: " + player.getPosNameYrOvrPot_OneLine());
            if (player instanceof PlayerQB) {
                final PlayerQB playerQB = (PlayerQB)player;
                this.leagueRecords.checkRecord("Career Pass Yards", playerQB.statsPassYards + playerQB.careerPassYards, player.team.abbr + " " + playerQB.getInitialName(), this.getYear() - 1);
                this.leagueRecords.checkRecord("Career Pass TDs", playerQB.statsTD + playerQB.careerTDs, player.team.abbr + " " + playerQB.getInitialName(), this.getYear() - 1);
                this.leagueRecords.checkRecord("Career Interceptions", playerQB.statsInt + playerQB.careerInt, player.team.abbr + " " + playerQB.getInitialName(), this.getYear() - 1);
            }
            else if (player instanceof PlayerRB) {
                final PlayerRB playerRB = (PlayerRB)player;
                this.leagueRecords.checkRecord("Career Rush Yards", playerRB.statsRushYards + playerRB.careerRushYards, player.team.abbr + " " + playerRB.getInitialName(), this.getYear() - 1);
                this.leagueRecords.checkRecord("Career Rush TDs", playerRB.statsTD + playerRB.careerTDs, player.team.abbr + " " + playerRB.getInitialName(), this.getYear() - 1);
                this.leagueRecords.checkRecord("Career Rush Fumbles", playerRB.statsFumbles + playerRB.careerFumbles, player.team.abbr + " " + playerRB.getInitialName(), this.getYear() - 1);
            }
            else {
                if (!(player instanceof PlayerWR)) {
                    continue;
                }
                final PlayerWR playerWR = (PlayerWR)player;
                this.leagueRecords.checkRecord("Career Rec Yards", playerWR.statsRecYards + playerWR.careerRecYards, player.team.abbr + " " + playerWR.getInitialName(), this.getYear() - 1);
                this.leagueRecords.checkRecord("Career Rec TDs", playerWR.statsTD + playerWR.careerTD, player.team.abbr + " " + playerWR.getInitialName(), this.getYear() - 1);
            }
        }
        this.checkHallofFame(this.freeAgents);
    }
    
    public void freeAgencyDay(final ArrayList<String> list) {
        this.sortFAs();
        final ArrayList<Team> list2 = new ArrayList<Team>();
        list2.addAll(this.teamList);
        for (int n = 0; n < 20 && this.freeAgents.size() > 20; ++n) {
            final int n2 = (int)(10.0 * Math.random());
            if (PlayerPickerLogic.signFreeAgentForTeam(list2, this.freeAgents.get(n2), list, 70, 73, false)) {
                list2.remove(this.freeAgents.get(n2).getTeam());
                this.freeAgents.remove(n2);
            }
        }
    }
    
    public void generatePlayersNewLeague() {
        final ArrayList<Player> generateLeaguePlayers = DraftGenerator.generateLeaguePlayers(this);
        final ArrayList<Team> list = new ArrayList<Team>();
        for (int i = 0; i < 60; ++i) {
            list.addAll(this.teamList);
            for (int j = 0; j < this.teamList.size(); ++j) {
                if (PlayerPickerLogic.signFreeAgentForTeam(list, generateLeaguePlayers.get(0), null, 70, 75, true)) {
                    list.remove(generateLeaguePlayers.get(0).getTeam());
                    generateLeaguePlayers.get(0).getContract().setYearsLeft((int)(Math.random() * generateLeaguePlayers.get(0).getContract().getYearsLeft()) + 1);
                }
                generateLeaguePlayers.remove(0);
            }
            list.clear();
        }
        final Iterator<Team> iterator = this.teamList.iterator();
        while (iterator.hasNext()) {
            iterator.next().setInitialStats();
        }
    }
    
    public String getAllDivStr(int i) {
        final ArrayList<Player> allDivPlayers = this.divisions.get(i).getAllDivPlayers();
        final StringBuilder sb = new StringBuilder();
        Player player;
        PlayerQB playerQB;
        PlayerRB playerRB;
        PlayerWR playerWR;
        PlayerK playerK;
        for (i = 0; i < allDivPlayers.size(); ++i) {
            player = allDivPlayers.get(i);
            sb.append(player.team.abbr + "(" + player.team.wins + "-" + player.team.losses + ") - ");
            if (player instanceof PlayerQB) {
                playerQB = (PlayerQB)player;
                sb.append(" QB " + playerQB.name + " [" + playerQB.age + "]\n \t\t" + playerQB.statsTD + " TDs, " + playerQB.statsInt + " Int, " + playerQB.statsPassYards + " Yds\n");
            }
            else if (player instanceof PlayerRB) {
                playerRB = (PlayerRB)player;
                sb.append(" RB " + playerRB.name + " [" + playerRB.age + "]\n \t\t" + playerRB.statsTD + " TDs, " + playerRB.statsFumbles + " Fum, " + playerRB.statsRushYards + " Yds\n");
            }
            else if (player instanceof PlayerWR) {
                playerWR = (PlayerWR)player;
                sb.append(" WR " + playerWR.name + " [" + playerWR.age + "]\n \t\t" + playerWR.statsTD + " TDs, " + playerWR.statsFumbles + " Fum, " + playerWR.statsRecYards + " Yds\n");
            }
            else if (player instanceof PlayerK) {
                playerK = (PlayerK)player;
                sb.append(" K " + playerK.name + " [" + playerK.age + "]\n \t\tFGs: " + playerK.statsFGMade + "/" + playerK.statsFGAtt + ", XPs: " + playerK.statsXPMade + "/" + playerK.statsXPAtt + "\n");
            }
            else {
                sb.append(" " + player.position + " " + player.name + " [" + player.age + "]\n");
            }
            sb.append(" \t\tOverall: " + player.ratOvr + ", Potential: " + Player.getLetterGrade(player.ratPot) + "\n\n>");
        }
        return sb.toString();
    }
    
    public String getAllProStr() {
        if (this.allPros.isEmpty() && (this.allProStrFull == null || this.allProStrFull.equals(""))) {
            final ArrayList<Object> list = new ArrayList<Object>();
            final ArrayList<Object> list2 = new ArrayList<Object>();
            final ArrayList<Object> list3 = new ArrayList<Object>();
            final ArrayList<Object> list4 = new ArrayList<Object>();
            final ArrayList<Object> list5 = new ArrayList<Object>();
            final ArrayList<Object> list6 = new ArrayList<Object>();
            final ArrayList<Object> list7 = new ArrayList<Object>();
            final ArrayList<Object> list8 = new ArrayList<Object>();
            final ArrayList<Object> list9 = new ArrayList<Object>();
            for (final Division division : this.divisions) {
                division.getAllDivPlayers();
                list.add(division.allDivPlayers.get(0));
                list2.add(division.allDivPlayers.get(1));
                list2.add(division.allDivPlayers.get(2));
                list3.add(division.allDivPlayers.get(3));
                list3.add(division.allDivPlayers.get(4));
                list3.add(division.allDivPlayers.get(5));
                for (int i = 6; i < 11; ++i) {
                    list4.add(division.allDivPlayers.get(i));
                }
                list5.add(division.allDivPlayers.get(11));
                list6.add(division.allDivPlayers.get(12));
                for (int j = 13; j < 16; ++j) {
                    list7.add(division.allDivPlayers.get(j));
                }
                for (int k = 16; k < 20; ++k) {
                    list8.add(division.allDivPlayers.get(k));
                }
                for (int l = 20; l < 23; ++l) {
                    list9.add(division.allDivPlayers.get(l));
                }
            }
            Collections.sort(list, (Comparator<? super Object>)new PlayerMVPComp());
            Collections.sort(list2, (Comparator<? super Object>)new PlayerMVPComp());
            Collections.sort(list3, (Comparator<? super Object>)new PlayerMVPComp());
            Collections.sort(list4, (Comparator<? super Object>)new PlayerMVPComp());
            Collections.sort(list5, (Comparator<? super Object>)new PlayerMVPComp());
            Collections.sort(list6, (Comparator<? super Object>)new PlayerMVPComp());
            Collections.sort(list7, (Comparator<? super Object>)new PlayerMVPComp());
            Collections.sort(list8, (Comparator<? super Object>)new PlayerMVPComp());
            Collections.sort(list9, (Comparator<? super Object>)new PlayerMVPComp());
            this.allPros.add((Player)list.get(0));
            ((PlayerQB)list.get(0)).wonAllPro = true;
            this.allPros.add((Player)list2.get(0));
            ((PlayerRB)list2.get(0)).wonAllPro = true;
            this.allPros.add((Player)list2.get(1));
            ((PlayerRB)list2.get(1)).wonAllPro = true;
            for (int n = 0; n < 3; ++n) {
                this.allPros.add(list3.get(n));
                list3.get(n).wonAllPro = true;
            }
            for (int n2 = 0; n2 < 5; ++n2) {
                this.allPros.add(list4.get(n2));
                list4.get(n2).wonAllPro = true;
            }
            this.allPros.add((Player)list5.get(0));
            ((PlayerK)list5.get(0)).wonAllPro = true;
            this.allPros.add((Player)list6.get(0));
            ((PlayerS)list6.get(0)).wonAllPro = true;
            for (int n3 = 0; n3 < 3; ++n3) {
                this.allPros.add(list7.get(n3));
                list7.get(n3).wonAllPro = true;
            }
            for (int n4 = 0; n4 < 4; ++n4) {
                this.allPros.add(list8.get(n4));
                list8.get(n4).wonAllPro = true;
            }
            for (int n5 = 0; n5 < 3; ++n5) {
                this.allPros.add(list9.get(n5));
                list9.get(n5).wonAllPro = true;
            }
        }
        if (this.allProStrFull == null || this.allProStrFull.equals("")) {
            final StringBuilder sb = new StringBuilder();
            for (int n6 = 0; n6 < this.allPros.size(); ++n6) {
                final Player player = this.allPros.get(n6);
                sb.append(player.team.abbr + "(" + player.team.wins + "-" + player.team.losses + ") - ");
                if (player instanceof PlayerQB) {
                    final PlayerQB playerQB = (PlayerQB)player;
                    sb.append(" QB " + playerQB.name + " [" + playerQB.age + "]\n \t\t" + playerQB.statsTD + " TDs, " + playerQB.statsInt + " Int, " + playerQB.statsPassYards + " Yds\n");
                }
                else if (player instanceof PlayerRB) {
                    final PlayerRB playerRB = (PlayerRB)player;
                    sb.append(" RB " + playerRB.name + " [" + playerRB.age + "]\n \t\t" + playerRB.statsTD + " TDs, " + playerRB.statsFumbles + " Fum, " + playerRB.statsRushYards + " Yds\n");
                }
                else if (player instanceof PlayerWR) {
                    final PlayerWR playerWR = (PlayerWR)player;
                    sb.append(" WR " + playerWR.name + " [" + playerWR.age + "]\n \t\t" + playerWR.statsTD + " TDs, " + playerWR.statsFumbles + " Fum, " + playerWR.statsRecYards + " Yds\n");
                }
                else if (player instanceof PlayerK) {
                    final PlayerK playerK = (PlayerK)player;
                    sb.append(" K " + playerK.name + " [" + playerK.age + "]\n \t\tFGs: " + playerK.statsFGMade + "/" + playerK.statsFGAtt + ", XPs: " + playerK.statsXPMade + "/" + playerK.statsXPAtt + "\n");
                }
                else {
                    sb.append(" " + player.position + " " + player.name + " [" + player.age + "]\n");
                }
                sb.append(" \t\tOverall: " + player.ratOvr + ", Potential: " + Player.getLetterGrade(player.ratPot) + "\n\n>");
            }
            this.allProStrFull = sb.toString();
            return sb.toString();
        }
        return this.allProStrFull;
    }
    
    public int getDivNumber(final String s) {
        for (int i = 0; i < this.divisions.size(); ++i) {
            if (this.divisions.get(i).divName.equals(s)) {
                return i;
            }
        }
        return 0;
    }
    
    public ArrayList<String> getDivStandings() {
        final ArrayList<String> list = new ArrayList<String>();
        final ArrayList<Object> list2 = new ArrayList<Object>();
        for (final Division division : this.divisions) {
            list2.addAll(division.divTeams);
            Collections.sort(list2, (Comparator<? super Object>)new TeamCompDivision());
            list.add(" ," + division.divName + " division, ");
            for (int i = 0; i < list2.size(); ++i) {
                final Team team = list2.get(i);
                list.add(team.getRankStrStarUser(i + 1) + "," + team.strRepWithBowlResults() + "," + team.getDivWins() + "-" + team.getDivLosses());
            }
            list2.clear();
        }
        return list;
    }
    
    public String getGameSummaryBowl(final Game game) {
        final StringBuilder sb = new StringBuilder();
        if (!game.hasPlayed) {
            return game.homeTeam.strRep() + " vs " + game.awayTeam.strRep();
        }
        if (game.homeScore > game.awayScore) {
            final Team homeTeam = game.homeTeam;
            final Team awayTeam = game.awayTeam;
            sb.append(homeTeam.strRep() + " W ");
            sb.append(game.homeScore + "-" + game.awayScore + " ");
            sb.append("vs " + awayTeam.strRep());
            return sb.toString();
        }
        final Team awayTeam2 = game.awayTeam;
        final Team homeTeam2 = game.homeTeam;
        sb.append(awayTeam2.strRep() + " W ");
        sb.append(game.awayScore + "-" + game.homeScore + " ");
        sb.append("@ " + homeTeam2.strRep());
        return sb.toString();
    }
    
    public String getLeagueHistoryStr() {
        String s = "";
        for (int i = 0; i < this.leagueHistory.size(); ++i) {
            final String string = s + (i + 2016) + ":\n";
            String s2;
            if (this.leagueHistory.get(i).length > 0) {
                s2 = string + "\tChampions: " + this.leagueHistory.get(i)[0] + "\n";
            }
            else {
                s2 = string + "\tChampions: ERROR\n";
            }
            if (this.mvpHistory.size() > i) {
                s = s2 + "\tMVP: " + this.mvpHistory.get(i) + "\n%";
            }
            else {
                s = s2 + "\tMVP: ERROR\n";
            }
        }
        return s;
    }
    
    public String getLeagueRecordsStr() {
        return "Longest Win Streak," + this.longestWinStreak.getStreakLength() + "," + this.longestWinStreak.getTeam() + "," + this.longestWinStreak.getStartYear() + "-" + this.longestWinStreak.getEndYear() + "\n" + ("Active Win Streak," + this.longestActiveWinStreak.getStreakLength() + "," + this.longestActiveWinStreak.getTeam() + "," + this.longestActiveWinStreak.getStartYear() + "-" + this.longestActiveWinStreak.getEndYear() + "\n") + this.leagueRecords.getRecordsStr();
    }
    
    public ArrayList<Player> getMVP(final boolean b) {
        this.mvp = null;
        int n = 0;
        final ArrayList<PlayerRB> list = new ArrayList<PlayerRB>();
        int n2;
        for (int i = 0; i < this.teamList.size(); ++i, n = n2) {
            if (b && !this.playoffsAM.contains(this.teamList.get(i))) {
                n2 = n;
                if (!this.playoffsNA.contains(this.teamList.get(i))) {
                    continue;
                }
            }
            int n4;
            for (int j = 0; j < this.teamList.get(i).teamQBs.size(); ++j, n = n4) {
                list.add((PlayerRB)this.teamList.get(i).teamQBs.get(j));
                final int n3 = this.teamList.get(i).teamQBs.get(j).getMVPScore() + this.teamList.get(i).wins * 200;
                if (n3 > (n4 = n)) {
                    this.mvp = this.teamList.get(i).teamQBs.get(j);
                    n4 = n3;
                }
            }
            int n6;
            for (int k = 0; k < this.teamList.get(i).teamRBs.size(); ++k, n = n6) {
                list.add(this.teamList.get(i).teamRBs.get(k));
                final int n5 = this.teamList.get(i).teamRBs.get(k).getMVPScore() + this.teamList.get(i).wins * 200;
                if (n5 > (n6 = n)) {
                    this.mvp = this.teamList.get(i).teamRBs.get(k);
                    n6 = n5;
                }
            }
            int n7 = 0;
            while (true) {
                n2 = n;
                if (n7 >= this.teamList.get(i).teamWRs.size()) {
                    break;
                }
                list.add((PlayerRB)this.teamList.get(i).teamWRs.get(n7));
                final int n8 = this.teamList.get(i).teamWRs.get(n7).getMVPScore() + this.teamList.get(i).wins * 200;
                int n9;
                if (n8 > (n9 = n)) {
                    this.mvp = this.teamList.get(i).teamWRs.get(n7);
                    n9 = n8;
                }
                ++n7;
                n = n9;
            }
        }
        Collections.sort((List<Object>)list, (Comparator<? super Object>)new PlayerMVPComp());
        return (ArrayList<Player>)list;
    }
    
    public String getMVPCeremonyStr() {
        if (!this.mvpDecided) {
            this.mvpDecided = true;
            this.mvpCandidates = this.getMVP(true);
            this.mvp = this.mvpCandidates.get(0);
            this.mvp.wonMVP = true;
            this.mvpHistory.add(this.mvp.position + " " + this.mvp.getInitialName() + " [" + this.mvp.age + "], " + this.mvp.team.abbr + " (" + this.mvp.team.wins + "-" + this.mvp.team.losses + ")");
            String s = "\n";
            String s2;
            for (int i = 0; i < 5; ++i, s = s2) {
                final Player player = this.mvpCandidates.get(i);
                final String string = s + (i + 1) + ". " + player.team.abbr + "(" + player.team.wins + "-" + player.team.losses + ") - ";
                if (player instanceof PlayerQB) {
                    final PlayerQB playerQB = (PlayerQB)player;
                    s2 = string + " QB " + playerQB.getInitialName() + ": " + player.getMVPScore() + " votes\n\t(" + playerQB.statsTD + " TDs, " + playerQB.statsInt + " Int, " + playerQB.statsPassYards + " Yds)\n\n";
                }
                else if (player instanceof PlayerRB) {
                    final PlayerRB playerRB = (PlayerRB)player;
                    s2 = string + " RB " + playerRB.getInitialName() + ": " + player.getMVPScore() + " votes\n\t(" + playerRB.statsTD + " TDs, " + playerRB.statsFumbles + " Fum, " + playerRB.statsRushYards + " Yds)\n\n";
                }
                else {
                    s2 = string;
                    if (player instanceof PlayerWR) {
                        final PlayerWR playerWR = (PlayerWR)player;
                        s2 = string + " WR " + playerWR.getInitialName() + ": " + player.getMVPScore() + " votes\n\t(" + playerWR.statsTD + " TDs, " + playerWR.statsFumbles + " Fum, " + playerWR.statsRecYards + " Yds)\n\n";
                    }
                }
            }
            String mvpWinnerStrFull = "";
            String s3 = "";
            if (this.mvp instanceof PlayerQB) {
                final PlayerQB playerQB2 = (PlayerQB)this.mvp;
                s3 = "Congratulations to the Most Valuable Player, " + playerQB2.team.abbr + " QB " + playerQB2.name + " [" + this.mvp.age + "], who had " + playerQB2.statsTD + " TDs, just " + playerQB2.statsInt + " interceptions, and " + playerQB2.statsPassYards + " passing yards. He led " + playerQB2.team.name + " to a " + playerQB2.team.wins + "-" + playerQB2.team.losses + " record and a #" + playerQB2.team.rankTeamPollScore + " power ranking.";
                mvpWinnerStrFull = s3 + "\n\nFull Results:" + s;
            }
            else if (this.mvp instanceof PlayerRB) {
                final PlayerRB playerRB2 = (PlayerRB)this.mvp;
                s3 = "Congratulations to the Most Valuable Player, " + playerRB2.team.abbr + " RB " + playerRB2.name + " [" + this.mvp.age + "], who had " + playerRB2.statsTD + " TDs, just " + playerRB2.statsFumbles + " fumbles, and " + playerRB2.statsRushYards + " rushing yards. He led " + playerRB2.team.name + " to a " + playerRB2.team.wins + "-" + playerRB2.team.losses + " record and a #" + playerRB2.team.rankTeamPollScore + " power ranking.";
                mvpWinnerStrFull = s3 + "\n\nFull Results:" + s;
            }
            else if (this.mvp instanceof PlayerWR) {
                final PlayerWR playerWR2 = (PlayerWR)this.mvp;
                s3 = "Congratulations to the Most Valuable Player, " + playerWR2.team.abbr + " WR " + playerWR2.name + " [" + this.mvp.age + "], who had " + playerWR2.statsTD + " TDs, just " + playerWR2.statsFumbles + " fumbles, and " + playerWR2.statsRecYards + " receiving yards. He led " + playerWR2.team.name + " to a " + playerWR2.team.wins + "-" + playerWR2.team.losses + " record and a #" + playerWR2.team.rankTeamPollScore + " power ranking.";
                mvpWinnerStrFull = s3 + "\n\nFull Results:" + s;
            }
            if (true) {
                this.newsStories.get(13).add(this.mvp.name + " is the Most Valuable Player!>" + s3);
                this.mvpWinnerStrFull = mvpWinnerStrFull;
            }
            return mvpWinnerStrFull;
        }
        return this.mvpWinnerStrFull;
    }
    
    public void getPlayersLeaving() {
        final Iterator<Team> iterator = this.teamList.iterator();
        while (iterator.hasNext()) {
            iterator.next().getPlayersLeaving();
        }
    }
    
    public ArrayList<String> getPlayoffPicture() {
        final ArrayList<Team> list = new ArrayList<Team>();
        final ArrayList<Team> list2 = new ArrayList<Team>();
        final ArrayList<Team> list3 = new ArrayList<Team>();
        ArrayList<Team> playoffsAM = null;
        ArrayList<Team> playoffsNA = null;
        Label_0349: {
            if (this.playoffsAM.isEmpty() && this.playoffsNA.isEmpty()) {
                final ArrayList<Team> list4 = new ArrayList<Team>();
                final ArrayList<Team> list5 = new ArrayList<Team>();
                for (int i = 0; i < this.divisions.size(); ++i) {
                    list.addAll(this.divisions.get(i).divTeams);
                    Collections.sort((List<Object>)list, (Comparator<? super Object>)new TeamCompDivision());
                    if (i < 4) {
                        list2.add(list.get(0));
                        list4.addAll(list);
                    }
                    else {
                        list3.add(list.get(0));
                        list5.addAll(list);
                    }
                    list.clear();
                }
                Collections.sort((List<Object>)list2, (Comparator<? super Object>)new TeamCompDivision());
                Collections.sort((List<Object>)list3, (Comparator<? super Object>)new TeamCompDivision());
                Collections.sort((List<Object>)list4, (Comparator<? super Object>)new TeamCompDivision());
                int n = 0;
                for (final Team team : list4) {
                    int n2 = n;
                    if (!list2.contains(team)) {
                        list2.add(team);
                        n2 = n + 1;
                    }
                    if ((n = n2) >= 2) {
                        break;
                    }
                }
                Collections.sort((List<Object>)list5, (Comparator<? super Object>)new TeamCompDivision());
                int n3 = 0;
                final Iterator<Team> iterator2 = list5.iterator();
                int n4;
                do {
                    playoffsAM = list2;
                    playoffsNA = list3;
                    if (!iterator2.hasNext()) {
                        break Label_0349;
                    }
                    final Team team2 = iterator2.next();
                    n4 = n3;
                    if (list3.contains(team2)) {
                        continue;
                    }
                    list3.add(team2);
                    n4 = n3 + 1;
                } while ((n3 = n4) < 2);
                playoffsNA = list3;
                playoffsAM = list2;
            }
            else {
                playoffsNA = this.playoffsNA;
                playoffsAM = this.playoffsAM;
            }
        }
        final ArrayList<String> list6 = new ArrayList<String>();
        list6.add(" ,American Conference, ");
        for (int j = 0; j < playoffsAM.size(); ++j) {
            final Team team3 = playoffsAM.get(j);
            if (j < 4) {
                list6.add(team3.getRankStrStarUser(j + 1) + "," + team3.strRepWithBowlResults() + "," + team3.division.substring(2));
            }
            else {
                list6.add(team3.getRankStrStarUser(j + 1) + "," + team3.strRepWithBowlResults() + ",WC");
            }
        }
        list6.add(" ,National Conference, ");
        for (int k = 0; k < playoffsNA.size(); ++k) {
            final Team team4 = playoffsNA.get(k);
            if (k < 4) {
                list6.add(team4.getRankStrStarUser(k + 1) + "," + team4.strRepWithBowlResults() + "," + team4.division.substring(2));
            }
            else {
                list6.add(team4.getRankStrStarUser(k + 1) + "," + team4.strRepWithBowlResults() + ",WC");
            }
        }
        return list6;
    }
    
    public String getRandName() {
        if (Math.random() > 0.0025) {
            return this.nameList.get((int)(Math.random() * this.nameList.size())) + " " + this.lastNameList.get((int)(Math.random() * this.lastNameList.size()));
        }
        return League.donationNames[(int)(Math.random() * League.donationNames.length)];
    }
    
    public String[] getTeamListStr() {
        final String[] array = new String[this.teamList.size()];
        for (int i = 0; i < this.teamList.size(); ++i) {
            array[i] = this.teamList.get(i).division + ": " + this.teamList.get(i).name;
        }
        return array;
    }
    
    public ArrayList<String> getTeamRankingsStr(int n) {
        final ArrayList<Team> teamList = this.teamList;
        final ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> playoffPicture = null;
        Label_0283: {
            switch (n) {
                default: {
                    Collections.sort((List<Object>)teamList, (Comparator<? super Object>)new TeamCompPoll());
                    n = 0;
                    while (true) {
                        playoffPicture = list;
                        if (n >= teamList.size()) {
                            break Label_0283;
                        }
                        final Team team = teamList.get(n);
                        list.add(team.getRankStrStarUser(n + 1) + "," + team.strRepWithBowlResults() + "," + team.teamPollScore);
                        ++n;
                    }
                    break;
                }
                case 0: {
                    Collections.sort((List<Object>)teamList, (Comparator<? super Object>)new TeamCompPoll());
                    n = 0;
                    while (true) {
                        playoffPicture = list;
                        if (n >= teamList.size()) {
                            break Label_0283;
                        }
                        final Team team2 = teamList.get(n);
                        list.add(team2.getRankStrStarUser(n + 1) + "," + team2.strRepWithBowlResults() + "," + team2.teamPollScore);
                        ++n;
                    }
                    break;
                }
                case 1: {
                    playoffPicture = this.getPlayoffPicture();
                    break;
                }
                case 2: {
                    return this.getDivStandings();
                }
                case 3: {
                    Collections.sort((List<Object>)teamList, (Comparator<? super Object>)new TeamCompSoW());
                    n = 0;
                    while (true) {
                        playoffPicture = list;
                        if (n >= teamList.size()) {
                            break Label_0283;
                        }
                        final Team team3 = teamList.get(n);
                        list.add(team3.getRankStrStarUser(n + 1) + "," + team3.strRepWithBowlResults() + "," + team3.teamStrengthOfWins);
                        ++n;
                    }
                    break;
                }
                case 4: {
                    Collections.sort((List<Object>)teamList, (Comparator<? super Object>)new TeamCompPPG());
                    n = 0;
                    while (true) {
                        playoffPicture = list;
                        if (n >= teamList.size()) {
                            break Label_0283;
                        }
                        final Team team4 = teamList.get(n);
                        list.add(team4.getRankStrStarUser(n + 1) + "," + team4.strRepWithBowlResults() + "," + team4.teamPoints / team4.numGames());
                        ++n;
                    }
                    break;
                }
                case 5: {
                    Collections.sort((List<Object>)teamList, (Comparator<? super Object>)new TeamCompOPPG());
                    n = 0;
                    while (true) {
                        playoffPicture = list;
                        if (n >= teamList.size()) {
                            break Label_0283;
                        }
                        final Team team5 = teamList.get(n);
                        list.add(team5.getRankStrStarUser(n + 1) + "," + team5.strRepWithBowlResults() + "," + team5.teamOppPoints / team5.numGames());
                        ++n;
                    }
                    break;
                }
                case 6: {
                    Collections.sort((List<Object>)teamList, (Comparator<? super Object>)new TeamCompYPG());
                    n = 0;
                    while (true) {
                        playoffPicture = list;
                        if (n >= teamList.size()) {
                            break Label_0283;
                        }
                        final Team team6 = teamList.get(n);
                        list.add(team6.getRankStrStarUser(n + 1) + "," + team6.strRepWithBowlResults() + "," + team6.teamYards / team6.numGames());
                        ++n;
                    }
                    break;
                }
                case 7: {
                    Collections.sort((List<Object>)teamList, (Comparator<? super Object>)new TeamCompOYPG());
                    n = 0;
                    while (true) {
                        playoffPicture = list;
                        if (n >= teamList.size()) {
                            break Label_0283;
                        }
                        final Team team7 = teamList.get(n);
                        list.add(team7.getRankStrStarUser(n + 1) + "," + team7.strRepWithBowlResults() + "," + team7.teamOppYards / team7.numGames());
                        ++n;
                    }
                    break;
                }
                case 8: {
                    Collections.sort((List<Object>)teamList, (Comparator<? super Object>)new TeamCompPYPG());
                    n = 0;
                    while (true) {
                        playoffPicture = list;
                        if (n >= teamList.size()) {
                            break Label_0283;
                        }
                        final Team team8 = teamList.get(n);
                        list.add(team8.getRankStrStarUser(n + 1) + "," + team8.strRepWithBowlResults() + "," + team8.teamPassYards / team8.numGames());
                        ++n;
                    }
                    break;
                }
                case 9: {
                    Collections.sort((List<Object>)teamList, (Comparator<? super Object>)new TeamCompRYPG());
                    n = 0;
                    while (true) {
                        playoffPicture = list;
                        if (n >= teamList.size()) {
                            break Label_0283;
                        }
                        final Team team9 = teamList.get(n);
                        list.add(team9.getRankStrStarUser(n + 1) + "," + team9.strRepWithBowlResults() + "," + team9.teamRushYards / team9.numGames());
                        ++n;
                    }
                    break;
                }
                case 10: {
                    Collections.sort((List<Object>)teamList, (Comparator<? super Object>)new TeamCompOPYPG());
                    n = 0;
                    while (true) {
                        playoffPicture = list;
                        if (n >= teamList.size()) {
                            break Label_0283;
                        }
                        final Team team10 = teamList.get(n);
                        list.add(team10.getRankStrStarUser(n + 1) + "," + team10.strRepWithBowlResults() + "," + team10.teamOppPassYards / team10.numGames());
                        ++n;
                    }
                    break;
                }
                case 11: {
                    Collections.sort((List<Object>)teamList, (Comparator<? super Object>)new TeamCompORYPG());
                    n = 0;
                    while (true) {
                        playoffPicture = list;
                        if (n >= teamList.size()) {
                            break Label_0283;
                        }
                        final Team team11 = teamList.get(n);
                        list.add(team11.getRankStrStarUser(n + 1) + "," + team11.strRepWithBowlResults() + "," + team11.teamOppRushYards / team11.numGames());
                        ++n;
                    }
                    break;
                }
                case 12: {
                    Collections.sort((List<Object>)teamList, (Comparator<? super Object>)new TeamCompTODiff());
                    n = 0;
                    while (true) {
                        playoffPicture = list;
                        if (n >= teamList.size()) {
                            break Label_0283;
                        }
                        final Team team12 = teamList.get(n);
                        if (team12.teamTODiff > 0) {
                            list.add(team12.getRankStrStarUser(n + 1) + "," + team12.strRepWithBowlResults() + ",+" + team12.teamTODiff);
                        }
                        else {
                            list.add(team12.getRankStrStarUser(n + 1) + "," + team12.strRepWithBowlResults() + "," + team12.teamTODiff);
                        }
                        ++n;
                    }
                    break;
                }
                case 13: {
                    Collections.sort((List<Object>)teamList, (Comparator<? super Object>)new TeamCompOffTalent());
                    n = 0;
                    while (true) {
                        playoffPicture = list;
                        if (n >= teamList.size()) {
                            break Label_0283;
                        }
                        final Team team13 = teamList.get(n);
                        list.add(team13.getRankStrStarUser(n + 1) + "," + team13.strRepWithBowlResults() + "," + team13.teamOffTalent);
                        ++n;
                    }
                    break;
                }
                case 14: {
                    Collections.sort((List<Object>)teamList, (Comparator<? super Object>)new TeamCompDefTalent());
                    n = 0;
                    while (true) {
                        playoffPicture = list;
                        if (n >= teamList.size()) {
                            break Label_0283;
                        }
                        final Team team14 = teamList.get(n);
                        list.add(team14.getRankStrStarUser(n + 1) + "," + team14.strRepWithBowlResults() + "," + team14.teamDefTalent);
                        ++n;
                    }
                    break;
                }
                case 15: {
                    Collections.sort((List<Object>)teamList, (Comparator<? super Object>)new TeamCompCapRoom());
                    n = 0;
                    while (true) {
                        playoffPicture = list;
                        if (n >= teamList.size()) {
                            break Label_0283;
                        }
                        final Team team15 = teamList.get(n);
                        list.add(team15.getRankStrStarUser(n + 1) + "," + team15.strRepWithBowlResults() + ",$" + team15.getSalaryCapRoom());
                        ++n;
                    }
                    break;
                }
            }
        }
        return playoffPicture;
    }
    
    public String getTop5MVPStr() {
        String mvpCeremonyStr;
        if (this.mvpDecided) {
            mvpCeremonyStr = this.getMVPCeremonyStr();
        }
        else {
            final ArrayList<Player> mvp = this.getMVP(false);
            String s = "";
            int n = 0;
            while (true) {
                mvpCeremonyStr = s;
                if (n >= 5) {
                    break;
                }
                final Player player = mvp.get(n);
                final String string = s + (n + 1) + ". " + player.team.abbr + "(" + player.team.wins + "-" + player.team.losses + ") - ";
                if (player instanceof PlayerQB) {
                    final PlayerQB playerQB = (PlayerQB)player;
                    s = string + " QB " + playerQB.name + " [" + playerQB.age + "]\n \t\t(" + playerQB.statsTD + " TDs, " + playerQB.statsInt + " Int, " + playerQB.statsPassYards + " Yds)\n\n";
                }
                else if (player instanceof PlayerRB) {
                    final PlayerRB playerRB = (PlayerRB)player;
                    s = string + " RB " + playerRB.name + " [" + playerRB.age + "]\n \t\t(" + playerRB.statsTD + " TDs, " + playerRB.statsFumbles + " Fum, " + playerRB.statsRushYards + " Yds)\n\n";
                }
                else {
                    s = string;
                    if (player instanceof PlayerWR) {
                        final PlayerWR playerWR = (PlayerWR)player;
                        s = string + " WR " + playerWR.name + " [" + playerWR.age + "]\n \t\t(" + playerWR.statsTD + " TDs, " + playerWR.statsFumbles + " Fum, " + playerWR.statsRecYards + " Yds)\n\n";
                    }
                }
                ++n;
            }
        }
        return mvpCeremonyStr;
    }
    
    public int getYear() {
        return this.leagueHistory.size() + 2016;
    }
    
    public boolean isAbbrValid(final String s) {
        if (s.length() > 3 || s.length() == 0) {
            return false;
        }
        if (s.contains(",") || s.contains(">") || s.contains("%") || s.contains("\\") || s.contains(" ")) {
            return false;
        }
        for (int i = 0; i < this.teamList.size(); ++i) {
            if (this.teamList.get(i).abbr.equals(s) && !this.teamList.get(i).userControlled) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isAbbrValidOnline(final String s) {
        return s.length() != 0 && s.length() <= 5 && !s.contains(",") && !s.contains(">") && !s.contains("%") && !s.contains("\\") && !s.contains(";") && !s.contains("'") && !s.contains("\"");
    }
    
    public boolean isNameValid(final String s) {
        if (s.length() == 0) {
            return false;
        }
        if (s.contains(",") || s.contains(">") || s.contains("%") || s.contains("\\")) {
            return false;
        }
        for (int i = 0; i < this.teamList.size(); ++i) {
            if (this.teamList.get(i).name.toLowerCase().equals(s.toLowerCase()) && !this.teamList.get(i).userControlled) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isNameValidOnline(final String s) {
        return s.length() != 0 && s.length() <= 20 && !s.contains(",") && !s.contains(">") && !s.contains("%") && !s.contains("\\") && !s.contains(";") && !s.contains("'") && !s.contains("\"");
    }
    
    public String ncgSummaryStr() {
        final Game game = this.playoffGames[10];
        if (game.homeScore > game.awayScore) {
            return game.homeTeam.name + " (" + game.homeTeam.wins + "-" + game.homeTeam.losses + ") won the Champions Bowl, beating the " + game.awayTeam.name + " (" + game.awayTeam.wins + "-" + game.awayTeam.losses + ") by a score of " + game.homeScore + "-" + game.awayScore + ".";
        }
        return game.awayTeam.name + " (" + game.awayTeam.wins + "-" + game.awayTeam.losses + ") won the Champions Bowl, beating the " + game.homeTeam.name + " (" + game.homeTeam.wins + "-" + game.homeTeam.losses + ") by a score of " + game.awayScore + "-" + game.homeScore + ".";
    }
    
    public void playWeek() {
        if (this.currentWeek < 16) {
            for (final Team team : this.teamList) {
                if (team.gameSchedule.size() > this.currentWeek && !team.gameSchedule.get(this.currentWeek).hasPlayed) {
                    team.gameSchedule.get(this.currentWeek).playGame();
                }
            }
            this.setTeamRanks();
            this.updateLongestActiveWinStreak();
        }
        if (this.currentWeek >= 15) {
            this.setUpPlayoffs(this.currentWeek - 14);
            this.setTeamRanks();
            this.updateLongestActiveWinStreak();
        }
        ++this.currentWeek;
    }
    
    public void prepareForDraft() {
        Collections.sort(this.teamList, new TeamCompLeastWins());
        final Iterator<Team> iterator = this.teamList.iterator();
        while (iterator.hasNext()) {
            Collections.sort(iterator.next().positionalDraftPicks, new DraftPickComparatorYearRound());
        }
    }
    
    public void resetForNewSeason() {
        this.mvpDecided = false;
        this.playoffGames = new Game[11];
        this.currentWeek = 0;
        this.allPros = new ArrayList<Player>();
        this.playoffsAM = new ArrayList<Team>();
        this.playoffsNA = new ArrayList<Team>();
        this.freeAgents = new ArrayList<Player>();
        this.mvpWinnerStrFull = null;
        for (final Team team : this.teamList) {
            team.signUDFAs();
            System.out.println(team.abbr + " salary cap room: " + team.getSalaryCapRoom());
        }
        for (int i = 0; i < this.divisions.size(); ++i) {
            this.divisions.get(i).robinWeek = 0;
            this.divisions.get(i).week = 0;
        }
        for (final Team team2 : this.teamList) {
            team2.wins = 0;
            team2.losses = 0;
            for (int j = 0; j < 7; ++j) {
                team2.draftPicks.add(new DraftPick(this.getYear() + 1, j + 1, team2, team2));
            }
        }
        this.setUpSchedule();
    }
    
    public boolean saveLeague(final File p0, final File p1, final File p2, final File p3, final boolean p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   java/lang/StringBuilder.<init>:()V
        //     7: astore          9
        //     9: aload           9
        //    11: new             Ljava/lang/StringBuilder;
        //    14: dup            
        //    15: invokespecial   java/lang/StringBuilder.<init>:()V
        //    18: aload_0        
        //    19: getfield        PFCpack/League.leagueHistory:Ljava/util/ArrayList;
        //    22: invokevirtual   java/util/ArrayList.size:()I
        //    25: sipush          2016
        //    28: iadd           
        //    29: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    32: ldc_w           " Wk "
        //    35: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    38: aload_0        
        //    39: getfield        PFCpack/League.currentWeek:I
        //    42: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    45: ldc_w           " : "
        //    48: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    51: aload_0        
        //    52: getfield        PFCpack/League.userTeam:LPFCpack/Team;
        //    55: getfield        PFCpack/Team.abbr:Ljava/lang/String;
        //    58: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    61: ldc_w           " ("
        //    64: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    67: aload_0        
        //    68: getfield        PFCpack/League.userTeam:LPFCpack/Team;
        //    71: getfield        PFCpack/Team.wins:I
        //    74: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    77: ldc_w           "-"
        //    80: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    83: aload_0        
        //    84: getfield        PFCpack/League.userTeam:LPFCpack/Team;
        //    87: getfield        PFCpack/Team.losses:I
        //    90: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    93: ldc_w           ") "
        //    96: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    99: aload_0        
        //   100: getfield        PFCpack/League.userTeam:LPFCpack/Team;
        //   103: getfield        PFCpack/Team.totalDivChamps:I
        //   106: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   109: ldc_w           " DWs, "
        //   112: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   115: aload_0        
        //   116: getfield        PFCpack/League.userTeam:LPFCpack/Team;
        //   119: getfield        PFCpack/Team.totalSuperBowlWins:I
        //   122: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   125: ldc_w           " CBWs%\n"
        //   128: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   131: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   134: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   137: pop            
        //   138: iconst_0       
        //   139: istore          6
        //   141: iload           6
        //   143: aload_0        
        //   144: getfield        PFCpack/League.leagueHistory:Ljava/util/ArrayList;
        //   147: invokevirtual   java/util/ArrayList.size:()I
        //   150: if_icmpge       240
        //   153: iconst_0       
        //   154: istore          7
        //   156: iload           7
        //   158: aload_0        
        //   159: getfield        PFCpack/League.leagueHistory:Ljava/util/ArrayList;
        //   162: iload           6
        //   164: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   167: checkcast       [Ljava/lang/String;
        //   170: arraylength    
        //   171: if_icmpge       222
        //   174: aload           9
        //   176: new             Ljava/lang/StringBuilder;
        //   179: dup            
        //   180: invokespecial   java/lang/StringBuilder.<init>:()V
        //   183: aload_0        
        //   184: getfield        PFCpack/League.leagueHistory:Ljava/util/ArrayList;
        //   187: iload           6
        //   189: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   192: checkcast       [Ljava/lang/String;
        //   195: iload           7
        //   197: aaload         
        //   198: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   201: ldc             "%"
        //   203: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   206: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   209: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   212: pop            
        //   213: iload           7
        //   215: iconst_1       
        //   216: iadd           
        //   217: istore          7
        //   219: goto            156
        //   222: aload           9
        //   224: ldc_w           "\n"
        //   227: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   230: pop            
        //   231: iload           6
        //   233: iconst_1       
        //   234: iadd           
        //   235: istore          6
        //   237: goto            141
        //   240: aload           9
        //   242: ldc_w           "END_LEAGUE_HIST\n"
        //   245: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   248: pop            
        //   249: iconst_0       
        //   250: istore          6
        //   252: iload           6
        //   254: aload_0        
        //   255: getfield        PFCpack/League.mvpHistory:Ljava/util/ArrayList;
        //   258: invokevirtual   java/util/ArrayList.size:()I
        //   261: if_icmpge       310
        //   264: aload           9
        //   266: new             Ljava/lang/StringBuilder;
        //   269: dup            
        //   270: invokespecial   java/lang/StringBuilder.<init>:()V
        //   273: aload_0        
        //   274: getfield        PFCpack/League.mvpHistory:Ljava/util/ArrayList;
        //   277: iload           6
        //   279: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   282: checkcast       Ljava/lang/String;
        //   285: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   288: ldc_w           "\n"
        //   291: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   294: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   297: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   300: pop            
        //   301: iload           6
        //   303: iconst_1       
        //   304: iadd           
        //   305: istore          6
        //   307: goto            252
        //   310: aload           9
        //   312: ldc_w           "END_MVP_HIST\n"
        //   315: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   318: pop            
        //   319: aload           9
        //   321: aload_0        
        //   322: getfield        PFCpack/League.leagueRecords:LPFCpack/LeagueRecords;
        //   325: invokevirtual   PFCpack/LeagueRecords.getRecordsStr:()Ljava/lang/String;
        //   328: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   331: pop            
        //   332: aload           9
        //   334: ldc_w           "END_LEAGUE_RECORDS\n"
        //   337: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   340: pop            
        //   341: aload           9
        //   343: aload_0        
        //   344: getfield        PFCpack/League.longestWinStreak:LPFCpack/TeamStreak;
        //   347: invokevirtual   PFCpack/TeamStreak.getStreakCSV:()Ljava/lang/String;
        //   350: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   353: pop            
        //   354: aload           9
        //   356: ldc_w           "\nEND_LEAGUE_WIN_STREAK\n"
        //   359: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   362: pop            
        //   363: aload           9
        //   365: aload_0        
        //   366: getfield        PFCpack/League.userTeamRecords:LPFCpack/LeagueRecords;
        //   369: invokevirtual   PFCpack/LeagueRecords.getRecordsStr:()Ljava/lang/String;
        //   372: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   375: pop            
        //   376: aload           9
        //   378: ldc_w           "END_USER_TEAM_RECORDS\n"
        //   381: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   384: pop            
        //   385: aload_0        
        //   386: getfield        PFCpack/League.hallOfFame:Ljava/util/ArrayList;
        //   389: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
        //   392: astore          10
        //   394: aload           10
        //   396: invokeinterface java/util/Iterator.hasNext:()Z
        //   401: ifeq            446
        //   404: aload           10
        //   406: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   411: checkcast       Ljava/lang/String;
        //   414: astore          8
        //   416: aload           9
        //   418: new             Ljava/lang/StringBuilder;
        //   421: dup            
        //   422: invokespecial   java/lang/StringBuilder.<init>:()V
        //   425: aload           8
        //   427: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   430: ldc_w           "\n"
        //   433: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   436: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   439: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   442: pop            
        //   443: goto            394
        //   446: aload           9
        //   448: ldc_w           "END_HALL_OF_FAME\n"
        //   451: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   454: pop            
        //   455: aload_0        
        //   456: getfield        PFCpack/League.allPros:Ljava/util/ArrayList;
        //   459: invokevirtual   java/util/ArrayList.isEmpty:()Z
        //   462: ifne            494
        //   465: aload           9
        //   467: new             Ljava/lang/StringBuilder;
        //   470: dup            
        //   471: invokespecial   java/lang/StringBuilder.<init>:()V
        //   474: aload_0        
        //   475: invokevirtual   PFCpack/League.getAllProStr:()Ljava/lang/String;
        //   478: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   481: ldc_w           "\n"
        //   484: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   487: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   490: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   493: pop            
        //   494: aload           9
        //   496: ldc_w           "END_ALL_PRO\n"
        //   499: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   502: pop            
        //   503: aload_0        
        //   504: getfield        PFCpack/League.mvpWinnerStrFull:Ljava/lang/String;
        //   507: ifnull          520
        //   510: aload           9
        //   512: aload_0        
        //   513: getfield        PFCpack/League.mvpWinnerStrFull:Ljava/lang/String;
        //   516: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   519: pop            
        //   520: aload           9
        //   522: ldc_w           "END_MVP\n"
        //   525: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   528: pop            
        //   529: aload_0        
        //   530: getfield        PFCpack/League.currentWeek:I
        //   533: bipush          16
        //   535: if_icmplt       666
        //   538: aload_0        
        //   539: getfield        PFCpack/League.playoffsAM:Ljava/util/ArrayList;
        //   542: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
        //   545: astore          8
        //   547: aload           8
        //   549: invokeinterface java/util/Iterator.hasNext:()Z
        //   554: ifeq            602
        //   557: aload           8
        //   559: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   564: checkcast       LPFCpack/Team;
        //   567: astore          10
        //   569: aload           9
        //   571: new             Ljava/lang/StringBuilder;
        //   574: dup            
        //   575: invokespecial   java/lang/StringBuilder.<init>:()V
        //   578: aload           10
        //   580: getfield        PFCpack/Team.abbr:Ljava/lang/String;
        //   583: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   586: ldc_w           "\n"
        //   589: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   592: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   595: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   598: pop            
        //   599: goto            547
        //   602: aload_0        
        //   603: getfield        PFCpack/League.playoffsNA:Ljava/util/ArrayList;
        //   606: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
        //   609: astore          8
        //   611: aload           8
        //   613: invokeinterface java/util/Iterator.hasNext:()Z
        //   618: ifeq            666
        //   621: aload           8
        //   623: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   628: checkcast       LPFCpack/Team;
        //   631: astore          10
        //   633: aload           9
        //   635: new             Ljava/lang/StringBuilder;
        //   638: dup            
        //   639: invokespecial   java/lang/StringBuilder.<init>:()V
        //   642: aload           10
        //   644: getfield        PFCpack/Team.abbr:Ljava/lang/String;
        //   647: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   650: ldc_w           "\n"
        //   653: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   656: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   659: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   662: pop            
        //   663: goto            611
        //   666: aload           9
        //   668: ldc_w           "END_PLAYOFF_TEAMS\n"
        //   671: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   674: pop            
        //   675: aload_0        
        //   676: getfield        PFCpack/League.freeAgents:Ljava/util/ArrayList;
        //   679: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
        //   682: astore          10
        //   684: aload           10
        //   686: invokeinterface java/util/Iterator.hasNext:()Z
        //   691: ifeq            769
        //   694: aload           10
        //   696: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   701: checkcast       LPFCpack/Player;
        //   704: astore          8
        //   706: aload           9
        //   708: new             Ljava/lang/StringBuilder;
        //   711: dup            
        //   712: invokespecial   java/lang/StringBuilder.<init>:()V
        //   715: aload           8
        //   717: invokevirtual   PFCpack/Player.getCSV:()Ljava/lang/String;
        //   720: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   723: ldc_w           "\n"
        //   726: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   729: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   732: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   735: pop            
        //   736: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   739: new             Ljava/lang/StringBuilder;
        //   742: dup            
        //   743: invokespecial   java/lang/StringBuilder.<init>:()V
        //   746: ldc_w           "FA: "
        //   749: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   752: aload           8
        //   754: invokevirtual   PFCpack/Player.getCSV:()Ljava/lang/String;
        //   757: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   760: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   763: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   766: goto            684
        //   769: aload           9
        //   771: ldc_w           "END_FREE_AGENTS\n"
        //   774: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   777: pop            
        //   778: aload_0        
        //   779: getfield        PFCpack/League.canGoOnline:Z
        //   782: ifeq            1446
        //   785: aload           9
        //   787: ldc_w           "1\n"
        //   790: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   793: pop            
        //   794: aload           9
        //   796: ldc_w           "END_GO_ONLINE\n"
        //   799: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   802: pop            
        //   803: new             Ljava/io/BufferedWriter;
        //   806: dup            
        //   807: new             Ljava/io/OutputStreamWriter;
        //   810: dup            
        //   811: new             Ljava/io/FileOutputStream;
        //   814: dup            
        //   815: aload_1        
        //   816: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //   819: ldc_w           "utf-8"
        //   822: invokespecial   java/io/OutputStreamWriter.<init>:(Ljava/io/OutputStream;Ljava/lang/String;)V
        //   825: invokespecial   java/io/BufferedWriter.<init>:(Ljava/io/Writer;)V
        //   828: astore          10
        //   830: aconst_null    
        //   831: astore          8
        //   833: aload           10
        //   835: aload           9
        //   837: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   840: invokevirtual   java/io/Writer.write:(Ljava/lang/String;)V
        //   843: aload           10
        //   845: ifnull          857
        //   848: iconst_0       
        //   849: ifeq            1481
        //   852: aload           10
        //   854: invokevirtual   java/io/Writer.close:()V
        //   857: aload           9
        //   859: iconst_0       
        //   860: invokevirtual   java/lang/StringBuilder.setLength:(I)V
        //   863: new             Ljava/io/PrintWriter;
        //   866: dup            
        //   867: aload_2        
        //   868: invokespecial   java/io/PrintWriter.<init>:(Ljava/io/File;)V
        //   871: astore_1       
        //   872: aload_1        
        //   873: ldc_w           ""
        //   876: invokevirtual   java/io/PrintWriter.print:(Ljava/lang/String;)V
        //   879: aload_1        
        //   880: invokevirtual   java/io/PrintWriter.close:()V
        //   883: new             Ljava/io/FileWriter;
        //   886: dup            
        //   887: aload_2        
        //   888: iconst_1       
        //   889: invokespecial   java/io/FileWriter.<init>:(Ljava/io/File;Z)V
        //   892: astore          8
        //   894: new             Ljava/io/BufferedWriter;
        //   897: dup            
        //   898: aload           8
        //   900: invokespecial   java/io/BufferedWriter.<init>:(Ljava/io/Writer;)V
        //   903: astore          9
        //   905: new             Ljava/io/PrintWriter;
        //   908: dup            
        //   909: aload           9
        //   911: invokespecial   java/io/PrintWriter.<init>:(Ljava/io/Writer;)V
        //   914: astore          10
        //   916: aconst_null    
        //   917: astore_1       
        //   918: aload_0        
        //   919: getfield        PFCpack/League.teamList:Ljava/util/ArrayList;
        //   922: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
        //   925: astore_2       
        //   926: aload_2        
        //   927: invokeinterface java/util/Iterator.hasNext:()Z
        //   932: ifeq            1546
        //   935: aload_2        
        //   936: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   941: checkcast       LPFCpack/Team;
        //   944: astore          11
        //   946: aload           10
        //   948: new             Ljava/lang/StringBuilder;
        //   951: dup            
        //   952: invokespecial   java/lang/StringBuilder.<init>:()V
        //   955: aload           11
        //   957: invokevirtual   PFCpack/Team.getCSV:()Ljava/lang/String;
        //   960: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   963: ldc_w           "%\n"
        //   966: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   969: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   972: invokevirtual   java/io/PrintWriter.print:(Ljava/lang/String;)V
        //   975: aload           10
        //   977: aload           11
        //   979: iload           5
        //   981: invokevirtual   PFCpack/Team.getPlayerInfoSaveFile:(Z)Ljava/lang/String;
        //   984: invokevirtual   java/io/PrintWriter.print:(Ljava/lang/String;)V
        //   987: aload           10
        //   989: ldc_w           "END_PLAYERS\n"
        //   992: invokevirtual   java/io/PrintWriter.print:(Ljava/lang/String;)V
        //   995: goto            926
        //   998: astore_1       
        //   999: aload_1        
        //  1000: athrow         
        //  1001: astore_2       
        //  1002: aload           10
        //  1004: ifnull          1016
        //  1007: aload_1        
        //  1008: ifnull          1774
        //  1011: aload           10
        //  1013: invokevirtual   java/io/PrintWriter.close:()V
        //  1016: aload_2        
        //  1017: athrow         
        //  1018: astore_1       
        //  1019: aload_1        
        //  1020: athrow         
        //  1021: astore_2       
        //  1022: aload           9
        //  1024: ifnull          1036
        //  1027: aload_1        
        //  1028: ifnull          1810
        //  1031: aload           9
        //  1033: invokevirtual   java/io/BufferedWriter.close:()V
        //  1036: aload_2        
        //  1037: athrow         
        //  1038: astore_1       
        //  1039: aload_1        
        //  1040: athrow         
        //  1041: astore_2       
        //  1042: aload           8
        //  1044: ifnull          1056
        //  1047: aload_1        
        //  1048: ifnull          1837
        //  1051: aload           8
        //  1053: invokevirtual   java/io/FileWriter.close:()V
        //  1056: aload_2        
        //  1057: athrow         
        //  1058: astore_1       
        //  1059: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //  1062: aload_1        
        //  1063: invokevirtual   java/io/IOException.toString:()Ljava/lang/String;
        //  1066: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //  1069: new             Ljava/io/PrintWriter;
        //  1072: dup            
        //  1073: aload           4
        //  1075: invokespecial   java/io/PrintWriter.<init>:(Ljava/io/File;)V
        //  1078: astore_1       
        //  1079: aload_1        
        //  1080: ldc_w           ""
        //  1083: invokevirtual   java/io/PrintWriter.print:(Ljava/lang/String;)V
        //  1086: aload_1        
        //  1087: invokevirtual   java/io/PrintWriter.close:()V
        //  1090: new             Ljava/io/FileWriter;
        //  1093: dup            
        //  1094: aload           4
        //  1096: iconst_1       
        //  1097: invokespecial   java/io/FileWriter.<init>:(Ljava/io/File;Z)V
        //  1100: astore          4
        //  1102: new             Ljava/io/BufferedWriter;
        //  1105: dup            
        //  1106: aload           4
        //  1108: invokespecial   java/io/BufferedWriter.<init>:(Ljava/io/Writer;)V
        //  1111: astore          8
        //  1113: new             Ljava/io/PrintWriter;
        //  1116: dup            
        //  1117: aload           8
        //  1119: invokespecial   java/io/PrintWriter.<init>:(Ljava/io/Writer;)V
        //  1122: astore          9
        //  1124: aconst_null    
        //  1125: astore_1       
        //  1126: aload_0        
        //  1127: getfield        PFCpack/League.teamList:Ljava/util/ArrayList;
        //  1130: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
        //  1133: astore_2       
        //  1134: aload_2        
        //  1135: invokeinterface java/util/Iterator.hasNext:()Z
        //  1140: ifeq            1874
        //  1143: aload_2        
        //  1144: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1149: checkcast       LPFCpack/Team;
        //  1152: astore          10
        //  1154: aload           9
        //  1156: new             Ljava/lang/StringBuilder;
        //  1159: dup            
        //  1160: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1163: aload           10
        //  1165: getfield        PFCpack/Team.abbr:Ljava/lang/String;
        //  1168: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1171: ldc_w           "\n"
        //  1174: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1177: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1180: invokevirtual   java/io/PrintWriter.print:(Ljava/lang/String;)V
        //  1183: aload           10
        //  1185: getfield        PFCpack/Team.teamHistory:Ljava/util/ArrayList;
        //  1188: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
        //  1191: astore          11
        //  1193: aload           11
        //  1195: invokeinterface java/util/Iterator.hasNext:()Z
        //  1200: ifeq            1859
        //  1203: aload           11
        //  1205: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1210: checkcast       Ljava/lang/String;
        //  1213: astore          10
        //  1215: aload           9
        //  1217: new             Ljava/lang/StringBuilder;
        //  1220: dup            
        //  1221: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1224: aload           10
        //  1226: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1229: ldc_w           "\n"
        //  1232: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1235: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1238: invokevirtual   java/io/PrintWriter.print:(Ljava/lang/String;)V
        //  1241: goto            1193
        //  1244: astore_1       
        //  1245: aload_1        
        //  1246: athrow         
        //  1247: astore_2       
        //  1248: aload           9
        //  1250: ifnull          1262
        //  1253: aload_1        
        //  1254: ifnull          1956
        //  1257: aload           9
        //  1259: invokevirtual   java/io/PrintWriter.close:()V
        //  1262: aload_2        
        //  1263: athrow         
        //  1264: astore_1       
        //  1265: aload_1        
        //  1266: athrow         
        //  1267: astore_2       
        //  1268: aload           8
        //  1270: ifnull          1282
        //  1273: aload_1        
        //  1274: ifnull          1992
        //  1277: aload           8
        //  1279: invokevirtual   java/io/BufferedWriter.close:()V
        //  1282: aload_2        
        //  1283: athrow         
        //  1284: astore_1       
        //  1285: aload_1        
        //  1286: athrow         
        //  1287: astore_2       
        //  1288: aload           4
        //  1290: ifnull          1302
        //  1293: aload_1        
        //  1294: ifnull          2019
        //  1297: aload           4
        //  1299: invokevirtual   java/io/FileWriter.close:()V
        //  1302: aload_2        
        //  1303: athrow         
        //  1304: astore_1       
        //  1305: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //  1308: aload_1        
        //  1309: invokevirtual   java/io/IOException.toString:()Ljava/lang/String;
        //  1312: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //  1315: new             Ljava/lang/StringBuilder;
        //  1318: dup            
        //  1319: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1322: astore          4
        //  1324: aload_0        
        //  1325: getfield        PFCpack/League.teamList:Ljava/util/ArrayList;
        //  1328: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
        //  1331: astore_1       
        //  1332: aload_1        
        //  1333: invokeinterface java/util/Iterator.hasNext:()Z
        //  1338: ifeq            2060
        //  1341: aload_1        
        //  1342: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1347: checkcast       LPFCpack/Team;
        //  1350: astore_2       
        //  1351: new             Ljava/lang/StringBuilder;
        //  1354: dup            
        //  1355: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1358: astore          8
        //  1360: aload           8
        //  1362: new             Ljava/lang/StringBuilder;
        //  1365: dup            
        //  1366: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1369: aload_2        
        //  1370: getfield        PFCpack/Team.abbr:Ljava/lang/String;
        //  1373: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1376: ldc             ">"
        //  1378: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1381: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1384: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1387: pop            
        //  1388: iconst_0       
        //  1389: istore          6
        //  1391: iload           6
        //  1393: bipush          16
        //  1395: if_icmpge       2027
        //  1398: aload           8
        //  1400: new             Ljava/lang/StringBuilder;
        //  1403: dup            
        //  1404: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1407: aload_2        
        //  1408: getfield        PFCpack/Team.gameSchedule:Ljava/util/ArrayList;
        //  1411: iload           6
        //  1413: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //  1416: checkcast       LPFCpack/Game;
        //  1419: invokevirtual   PFCpack/Game.getCSV:()Ljava/lang/String;
        //  1422: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1425: ldc             ">"
        //  1427: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1430: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1433: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1436: pop            
        //  1437: iload           6
        //  1439: iconst_1       
        //  1440: iadd           
        //  1441: istore          6
        //  1443: goto            1391
        //  1446: aload           9
        //  1448: ldc_w           "0\n"
        //  1451: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1454: pop            
        //  1455: goto            794
        //  1458: astore_1       
        //  1459: new             Ljava/lang/NullPointerException;
        //  1462: dup            
        //  1463: invokespecial   java/lang/NullPointerException.<init>:()V
        //  1466: athrow         
        //  1467: astore_1       
        //  1468: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //  1471: aload_1        
        //  1472: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //  1475: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //  1478: goto            857
        //  1481: aload           10
        //  1483: invokevirtual   java/io/Writer.close:()V
        //  1486: goto            857
        //  1489: astore          8
        //  1491: aload           8
        //  1493: athrow         
        //  1494: astore_1       
        //  1495: aload           10
        //  1497: ifnull          1510
        //  1500: aload           8
        //  1502: ifnull          1524
        //  1505: aload           10
        //  1507: invokevirtual   java/io/Writer.close:()V
        //  1510: aload_1        
        //  1511: athrow         
        //  1512: astore          10
        //  1514: aload           8
        //  1516: aload           10
        //  1518: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //  1521: goto            1510
        //  1524: aload           10
        //  1526: invokevirtual   java/io/Writer.close:()V
        //  1529: goto            1510
        //  1532: astore_1       
        //  1533: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //  1536: aload_1        
        //  1537: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //  1540: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //  1543: goto            883
        //  1546: aload           10
        //  1548: ldc_w           "END_ALL_PLAYERS\n"
        //  1551: invokevirtual   java/io/PrintWriter.print:(Ljava/lang/String;)V
        //  1554: aload_0        
        //  1555: getfield        PFCpack/League.teamList:Ljava/util/ArrayList;
        //  1558: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
        //  1561: astore          11
        //  1563: aload           11
        //  1565: invokeinterface java/util/Iterator.hasNext:()Z
        //  1570: ifeq            1684
        //  1573: aload           11
        //  1575: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1580: checkcast       LPFCpack/Team;
        //  1583: astore_2       
        //  1584: aload           10
        //  1586: new             Ljava/lang/StringBuilder;
        //  1589: dup            
        //  1590: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1593: aload_2        
        //  1594: getfield        PFCpack/Team.abbr:Ljava/lang/String;
        //  1597: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1600: ldc             ">"
        //  1602: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1605: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1608: invokevirtual   java/io/PrintWriter.print:(Ljava/lang/String;)V
        //  1611: iconst_0       
        //  1612: istore          6
        //  1614: iload           6
        //  1616: aload_2        
        //  1617: getfield        PFCpack/Team.draftPicks:Ljava/util/ArrayList;
        //  1620: invokevirtual   java/util/ArrayList.size:()I
        //  1623: if_icmpge       1673
        //  1626: aload           10
        //  1628: new             Ljava/lang/StringBuilder;
        //  1631: dup            
        //  1632: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1635: aload_2        
        //  1636: getfield        PFCpack/Team.draftPicks:Ljava/util/ArrayList;
        //  1639: iload           6
        //  1641: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //  1644: checkcast       LPFCpack/DraftPick;
        //  1647: invokevirtual   PFCpack/DraftPick.getCSV:()Ljava/lang/String;
        //  1650: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1653: ldc             ">"
        //  1655: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1658: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1661: invokevirtual   java/io/PrintWriter.print:(Ljava/lang/String;)V
        //  1664: iload           6
        //  1666: iconst_1       
        //  1667: iadd           
        //  1668: istore          6
        //  1670: goto            1614
        //  1673: aload           10
        //  1675: ldc_w           "\n"
        //  1678: invokevirtual   java/io/PrintWriter.print:(Ljava/lang/String;)V
        //  1681: goto            1563
        //  1684: aload           10
        //  1686: ldc_w           "END_DRAFT_PICKS"
        //  1689: invokevirtual   java/io/PrintWriter.print:(Ljava/lang/String;)V
        //  1692: aload           10
        //  1694: ifnull          1706
        //  1697: iconst_0       
        //  1698: ifeq            1755
        //  1701: aload           10
        //  1703: invokevirtual   java/io/PrintWriter.close:()V
        //  1706: aload           9
        //  1708: ifnull          1720
        //  1711: iconst_0       
        //  1712: ifeq            1791
        //  1715: aload           9
        //  1717: invokevirtual   java/io/BufferedWriter.close:()V
        //  1720: aload           8
        //  1722: ifnull          1069
        //  1725: iconst_0       
        //  1726: ifeq            1818
        //  1729: aload           8
        //  1731: invokevirtual   java/io/FileWriter.close:()V
        //  1734: goto            1069
        //  1737: astore_1       
        //  1738: new             Ljava/lang/NullPointerException;
        //  1741: dup            
        //  1742: invokespecial   java/lang/NullPointerException.<init>:()V
        //  1745: athrow         
        //  1746: astore_1       
        //  1747: new             Ljava/lang/NullPointerException;
        //  1750: dup            
        //  1751: invokespecial   java/lang/NullPointerException.<init>:()V
        //  1754: athrow         
        //  1755: aload           10
        //  1757: invokevirtual   java/io/PrintWriter.close:()V
        //  1760: goto            1706
        //  1763: astore          10
        //  1765: aload_1        
        //  1766: aload           10
        //  1768: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //  1771: goto            1016
        //  1774: aload           10
        //  1776: invokevirtual   java/io/PrintWriter.close:()V
        //  1779: goto            1016
        //  1782: astore_1       
        //  1783: new             Ljava/lang/NullPointerException;
        //  1786: dup            
        //  1787: invokespecial   java/lang/NullPointerException.<init>:()V
        //  1790: athrow         
        //  1791: aload           9
        //  1793: invokevirtual   java/io/BufferedWriter.close:()V
        //  1796: goto            1720
        //  1799: astore          9
        //  1801: aload_1        
        //  1802: aload           9
        //  1804: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //  1807: goto            1036
        //  1810: aload           9
        //  1812: invokevirtual   java/io/BufferedWriter.close:()V
        //  1815: goto            1036
        //  1818: aload           8
        //  1820: invokevirtual   java/io/FileWriter.close:()V
        //  1823: goto            1069
        //  1826: astore          8
        //  1828: aload_1        
        //  1829: aload           8
        //  1831: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //  1834: goto            1056
        //  1837: aload           8
        //  1839: invokevirtual   java/io/FileWriter.close:()V
        //  1842: goto            1056
        //  1845: astore_1       
        //  1846: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //  1849: aload_1        
        //  1850: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //  1853: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //  1856: goto            1090
        //  1859: aload           9
        //  1861: ldc_w           "END_TEAM_HISTORY\n"
        //  1864: invokevirtual   java/io/PrintWriter.print:(Ljava/lang/String;)V
        //  1867: goto            1134
        //  1870: astore_2       
        //  1871: goto            1248
        //  1874: aload           9
        //  1876: ifnull          1888
        //  1879: iconst_0       
        //  1880: ifeq            1937
        //  1883: aload           9
        //  1885: invokevirtual   java/io/PrintWriter.close:()V
        //  1888: aload           8
        //  1890: ifnull          1902
        //  1893: iconst_0       
        //  1894: ifeq            1973
        //  1897: aload           8
        //  1899: invokevirtual   java/io/BufferedWriter.close:()V
        //  1902: aload           4
        //  1904: ifnull          1315
        //  1907: iconst_0       
        //  1908: ifeq            2000
        //  1911: aload           4
        //  1913: invokevirtual   java/io/FileWriter.close:()V
        //  1916: goto            1315
        //  1919: astore_1       
        //  1920: new             Ljava/lang/NullPointerException;
        //  1923: dup            
        //  1924: invokespecial   java/lang/NullPointerException.<init>:()V
        //  1927: athrow         
        //  1928: astore_1       
        //  1929: new             Ljava/lang/NullPointerException;
        //  1932: dup            
        //  1933: invokespecial   java/lang/NullPointerException.<init>:()V
        //  1936: athrow         
        //  1937: aload           9
        //  1939: invokevirtual   java/io/PrintWriter.close:()V
        //  1942: goto            1888
        //  1945: astore          9
        //  1947: aload_1        
        //  1948: aload           9
        //  1950: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //  1953: goto            1262
        //  1956: aload           9
        //  1958: invokevirtual   java/io/PrintWriter.close:()V
        //  1961: goto            1262
        //  1964: astore_1       
        //  1965: new             Ljava/lang/NullPointerException;
        //  1968: dup            
        //  1969: invokespecial   java/lang/NullPointerException.<init>:()V
        //  1972: athrow         
        //  1973: aload           8
        //  1975: invokevirtual   java/io/BufferedWriter.close:()V
        //  1978: goto            1902
        //  1981: astore          8
        //  1983: aload_1        
        //  1984: aload           8
        //  1986: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //  1989: goto            1282
        //  1992: aload           8
        //  1994: invokevirtual   java/io/BufferedWriter.close:()V
        //  1997: goto            1282
        //  2000: aload           4
        //  2002: invokevirtual   java/io/FileWriter.close:()V
        //  2005: goto            1315
        //  2008: astore          4
        //  2010: aload_1        
        //  2011: aload           4
        //  2013: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //  2016: goto            1302
        //  2019: aload           4
        //  2021: invokevirtual   java/io/FileWriter.close:()V
        //  2024: goto            1302
        //  2027: aload           4
        //  2029: new             Ljava/lang/StringBuilder;
        //  2032: dup            
        //  2033: invokespecial   java/lang/StringBuilder.<init>:()V
        //  2036: aload           8
        //  2038: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  2041: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2044: ldc_w           "\n"
        //  2047: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2050: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  2053: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2056: pop            
        //  2057: goto            1332
        //  2060: aload           4
        //  2062: ldc_w           "END_REGULAR_SEASON\n"
        //  2065: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2068: pop            
        //  2069: aload_0        
        //  2070: getfield        PFCpack/League.playoffGames:[LPFCpack/Game;
        //  2073: astore_2       
        //  2074: aload_2        
        //  2075: arraylength    
        //  2076: istore          7
        //  2078: iconst_0       
        //  2079: istore          6
        //  2081: iload           6
        //  2083: iload           7
        //  2085: if_icmpge       2164
        //  2088: aload_2        
        //  2089: iload           6
        //  2091: aaload         
        //  2092: astore_1       
        //  2093: aload_1        
        //  2094: ifnull          2155
        //  2097: aload           4
        //  2099: new             Ljava/lang/StringBuilder;
        //  2102: dup            
        //  2103: invokespecial   java/lang/StringBuilder.<init>:()V
        //  2106: aload_1        
        //  2107: invokevirtual   PFCpack/Game.getCSV:()Ljava/lang/String;
        //  2110: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2113: ldc_w           "\n"
        //  2116: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2119: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  2122: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2125: pop            
        //  2126: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //  2129: new             Ljava/lang/StringBuilder;
        //  2132: dup            
        //  2133: invokespecial   java/lang/StringBuilder.<init>:()V
        //  2136: ldc_w           "PLAYOFF GAME: "
        //  2139: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2142: aload_1        
        //  2143: invokevirtual   PFCpack/Game.getCSV:()Ljava/lang/String;
        //  2146: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2149: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  2152: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //  2155: iload           6
        //  2157: iconst_1       
        //  2158: iadd           
        //  2159: istore          6
        //  2161: goto            2081
        //  2164: new             Ljava/io/BufferedWriter;
        //  2167: dup            
        //  2168: new             Ljava/io/OutputStreamWriter;
        //  2171: dup            
        //  2172: new             Ljava/io/FileOutputStream;
        //  2175: dup            
        //  2176: aload_3        
        //  2177: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //  2180: ldc_w           "utf-8"
        //  2183: invokespecial   java/io/OutputStreamWriter.<init>:(Ljava/io/OutputStream;Ljava/lang/String;)V
        //  2186: invokespecial   java/io/BufferedWriter.<init>:(Ljava/io/Writer;)V
        //  2189: astore_3       
        //  2190: aconst_null    
        //  2191: astore_2       
        //  2192: aload_3        
        //  2193: aload           4
        //  2195: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  2198: invokevirtual   java/io/Writer.write:(Ljava/lang/String;)V
        //  2201: aload_3        
        //  2202: ifnull          2213
        //  2205: iconst_0       
        //  2206: ifeq            2244
        //  2209: aload_3        
        //  2210: invokevirtual   java/io/Writer.close:()V
        //  2213: aload           4
        //  2215: iconst_0       
        //  2216: invokevirtual   java/lang/StringBuilder.setLength:(I)V
        //  2219: iconst_1       
        //  2220: ireturn        
        //  2221: astore_1       
        //  2222: new             Ljava/lang/NullPointerException;
        //  2225: dup            
        //  2226: invokespecial   java/lang/NullPointerException.<init>:()V
        //  2229: athrow         
        //  2230: astore_1       
        //  2231: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //  2234: aload_1        
        //  2235: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //  2238: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //  2241: goto            2213
        //  2244: aload_3        
        //  2245: invokevirtual   java/io/Writer.close:()V
        //  2248: goto            2213
        //  2251: astore_2       
        //  2252: aload_2        
        //  2253: athrow         
        //  2254: astore_1       
        //  2255: aload_3        
        //  2256: ifnull          2267
        //  2259: aload_2        
        //  2260: ifnull          2278
        //  2263: aload_3        
        //  2264: invokevirtual   java/io/Writer.close:()V
        //  2267: aload_1        
        //  2268: athrow         
        //  2269: astore_3       
        //  2270: aload_2        
        //  2271: aload_3        
        //  2272: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //  2275: goto            2267
        //  2278: aload_3        
        //  2279: invokevirtual   java/io/Writer.close:()V
        //  2282: goto            2267
        //  2285: astore_1       
        //  2286: goto            2255
        //  2289: astore_1       
        //  2290: goto            1495
        //  2293: astore_2       
        //  2294: goto            1002
        //  2297: astore_2       
        //  2298: aconst_null    
        //  2299: astore_1       
        //  2300: goto            1022
        //  2303: astore_2       
        //  2304: aconst_null    
        //  2305: astore_1       
        //  2306: goto            1042
        //  2309: astore_2       
        //  2310: aconst_null    
        //  2311: astore_1       
        //  2312: goto            1268
        //  2315: astore_2       
        //  2316: aconst_null    
        //  2317: astore_1       
        //  2318: goto            1288
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  803    830    1467   1481   Ljava/lang/Exception;
        //  833    843    1489   1495   Ljava/lang/Throwable;
        //  833    843    2289   2293   Any
        //  852    857    1458   1467   Ljava/lang/Throwable;
        //  852    857    1467   1481   Ljava/lang/Exception;
        //  863    883    1532   1546   Ljava/lang/Exception;
        //  883    894    1058   1069   Ljava/io/IOException;
        //  894    905    1038   1042   Ljava/lang/Throwable;
        //  894    905    2303   2309   Any
        //  905    916    1018   1022   Ljava/lang/Throwable;
        //  905    916    2297   2303   Any
        //  918    926    998    1002   Ljava/lang/Throwable;
        //  918    926    2293   2297   Any
        //  926    995    998    1002   Ljava/lang/Throwable;
        //  926    995    2293   2297   Any
        //  999    1001   1001   1002   Any
        //  1011   1016   1763   1774   Ljava/lang/Throwable;
        //  1011   1016   2297   2303   Any
        //  1016   1018   1018   1022   Ljava/lang/Throwable;
        //  1016   1018   2297   2303   Any
        //  1019   1021   1021   1022   Any
        //  1031   1036   1799   1810   Ljava/lang/Throwable;
        //  1031   1036   2303   2309   Any
        //  1036   1038   1038   1042   Ljava/lang/Throwable;
        //  1036   1038   2303   2309   Any
        //  1039   1041   1041   1042   Any
        //  1051   1056   1826   1837   Ljava/lang/Throwable;
        //  1051   1056   1058   1069   Ljava/io/IOException;
        //  1056   1058   1058   1069   Ljava/io/IOException;
        //  1069   1090   1845   1859   Ljava/lang/Exception;
        //  1090   1102   1304   1315   Ljava/io/IOException;
        //  1102   1113   1284   1288   Ljava/lang/Throwable;
        //  1102   1113   2315   2321   Any
        //  1113   1124   1264   1268   Ljava/lang/Throwable;
        //  1113   1124   2309   2315   Any
        //  1126   1134   1244   1248   Ljava/lang/Throwable;
        //  1126   1134   1870   1874   Any
        //  1134   1193   1244   1248   Ljava/lang/Throwable;
        //  1134   1193   1870   1874   Any
        //  1193   1241   1244   1248   Ljava/lang/Throwable;
        //  1193   1241   1870   1874   Any
        //  1245   1247   1247   1248   Any
        //  1257   1262   1945   1956   Ljava/lang/Throwable;
        //  1257   1262   2309   2315   Any
        //  1262   1264   1264   1268   Ljava/lang/Throwable;
        //  1262   1264   2309   2315   Any
        //  1265   1267   1267   1268   Any
        //  1277   1282   1981   1992   Ljava/lang/Throwable;
        //  1277   1282   2315   2321   Any
        //  1282   1284   1284   1288   Ljava/lang/Throwable;
        //  1282   1284   2315   2321   Any
        //  1285   1287   1287   1288   Any
        //  1297   1302   2008   2019   Ljava/lang/Throwable;
        //  1297   1302   1304   1315   Ljava/io/IOException;
        //  1302   1304   1304   1315   Ljava/io/IOException;
        //  1459   1467   1467   1481   Ljava/lang/Exception;
        //  1481   1486   1467   1481   Ljava/lang/Exception;
        //  1491   1494   1494   1495   Any
        //  1505   1510   1512   1524   Ljava/lang/Throwable;
        //  1505   1510   1467   1481   Ljava/lang/Exception;
        //  1510   1512   1467   1481   Ljava/lang/Exception;
        //  1514   1521   1467   1481   Ljava/lang/Exception;
        //  1524   1529   1467   1481   Ljava/lang/Exception;
        //  1546   1563   998    1002   Ljava/lang/Throwable;
        //  1546   1563   2293   2297   Any
        //  1563   1611   998    1002   Ljava/lang/Throwable;
        //  1563   1611   2293   2297   Any
        //  1614   1664   998    1002   Ljava/lang/Throwable;
        //  1614   1664   2293   2297   Any
        //  1673   1681   998    1002   Ljava/lang/Throwable;
        //  1673   1681   2293   2297   Any
        //  1684   1692   998    1002   Ljava/lang/Throwable;
        //  1684   1692   2293   2297   Any
        //  1701   1706   1746   1755   Ljava/lang/Throwable;
        //  1701   1706   2297   2303   Any
        //  1715   1720   1782   1791   Ljava/lang/Throwable;
        //  1715   1720   2303   2309   Any
        //  1729   1734   1737   1746   Ljava/lang/Throwable;
        //  1729   1734   1058   1069   Ljava/io/IOException;
        //  1738   1746   1058   1069   Ljava/io/IOException;
        //  1747   1755   1018   1022   Ljava/lang/Throwable;
        //  1747   1755   2297   2303   Any
        //  1755   1760   1018   1022   Ljava/lang/Throwable;
        //  1755   1760   2297   2303   Any
        //  1765   1771   1018   1022   Ljava/lang/Throwable;
        //  1765   1771   2297   2303   Any
        //  1774   1779   1018   1022   Ljava/lang/Throwable;
        //  1774   1779   2297   2303   Any
        //  1783   1791   1038   1042   Ljava/lang/Throwable;
        //  1783   1791   2303   2309   Any
        //  1791   1796   1038   1042   Ljava/lang/Throwable;
        //  1791   1796   2303   2309   Any
        //  1801   1807   1038   1042   Ljava/lang/Throwable;
        //  1801   1807   2303   2309   Any
        //  1810   1815   1038   1042   Ljava/lang/Throwable;
        //  1810   1815   2303   2309   Any
        //  1818   1823   1058   1069   Ljava/io/IOException;
        //  1828   1834   1058   1069   Ljava/io/IOException;
        //  1837   1842   1058   1069   Ljava/io/IOException;
        //  1859   1867   1244   1248   Ljava/lang/Throwable;
        //  1859   1867   1870   1874   Any
        //  1883   1888   1928   1937   Ljava/lang/Throwable;
        //  1883   1888   2309   2315   Any
        //  1897   1902   1964   1973   Ljava/lang/Throwable;
        //  1897   1902   2315   2321   Any
        //  1911   1916   1919   1928   Ljava/lang/Throwable;
        //  1911   1916   1304   1315   Ljava/io/IOException;
        //  1920   1928   1304   1315   Ljava/io/IOException;
        //  1929   1937   1264   1268   Ljava/lang/Throwable;
        //  1929   1937   2309   2315   Any
        //  1937   1942   1264   1268   Ljava/lang/Throwable;
        //  1937   1942   2309   2315   Any
        //  1947   1953   1264   1268   Ljava/lang/Throwable;
        //  1947   1953   2309   2315   Any
        //  1956   1961   1264   1268   Ljava/lang/Throwable;
        //  1956   1961   2309   2315   Any
        //  1965   1973   1284   1288   Ljava/lang/Throwable;
        //  1965   1973   2315   2321   Any
        //  1973   1978   1284   1288   Ljava/lang/Throwable;
        //  1973   1978   2315   2321   Any
        //  1983   1989   1284   1288   Ljava/lang/Throwable;
        //  1983   1989   2315   2321   Any
        //  1992   1997   1284   1288   Ljava/lang/Throwable;
        //  1992   1997   2315   2321   Any
        //  2000   2005   1304   1315   Ljava/io/IOException;
        //  2010   2016   1304   1315   Ljava/io/IOException;
        //  2019   2024   1304   1315   Ljava/io/IOException;
        //  2164   2190   2230   2244   Ljava/lang/Exception;
        //  2192   2201   2251   2255   Ljava/lang/Throwable;
        //  2192   2201   2285   2289   Any
        //  2209   2213   2221   2230   Ljava/lang/Throwable;
        //  2209   2213   2230   2244   Ljava/lang/Exception;
        //  2222   2230   2230   2244   Ljava/lang/Exception;
        //  2244   2248   2230   2244   Ljava/lang/Exception;
        //  2252   2254   2254   2255   Any
        //  2263   2267   2269   2278   Ljava/lang/Throwable;
        //  2263   2267   2230   2244   Ljava/lang/Exception;
        //  2267   2269   2230   2244   Ljava/lang/Exception;
        //  2270   2275   2230   2244   Ljava/lang/Exception;
        //  2278   2282   2230   2244   Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 1026, Size: 1026
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3569)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3435)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3435)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3435)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3435)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public String seasonSummaryStr() {
        this.setTeamRanks();
        final StringBuilder sb = new StringBuilder();
        sb.append(this.ncgSummaryStr());
        sb.append("\n\n" + this.userTeam.seasonSummaryStr());
        sb.append("\n\n" + this.leagueRecords.brokenRecordsStr(this.getYear(), this.userTeam.abbr));
        return sb.toString();
    }
    
    public void setTeamRanks() {
        for (int i = 0; i < this.teamList.size(); ++i) {
            this.teamList.get(i).updatePollScore();
        }
        Collections.sort(this.teamList, new TeamCompPoll());
        for (int j = 0; j < this.teamList.size(); ++j) {
            this.teamList.get(j).rankTeamPollScore = j + 1;
        }
        Collections.sort(this.teamList, new TeamCompSoW());
        for (int k = 0; k < this.teamList.size(); ++k) {
            this.teamList.get(k).rankTeamStrengthOfWins = k + 1;
        }
        Collections.sort(this.teamList, new TeamCompPPG());
        for (int l = 0; l < this.teamList.size(); ++l) {
            this.teamList.get(l).rankTeamPoints = l + 1;
        }
        Collections.sort(this.teamList, new TeamCompOPPG());
        for (int n = 0; n < this.teamList.size(); ++n) {
            this.teamList.get(n).rankTeamOppPoints = n + 1;
        }
        Collections.sort(this.teamList, new TeamCompYPG());
        for (int n2 = 0; n2 < this.teamList.size(); ++n2) {
            this.teamList.get(n2).rankTeamYards = n2 + 1;
        }
        Collections.sort(this.teamList, new TeamCompOYPG());
        for (int n3 = 0; n3 < this.teamList.size(); ++n3) {
            this.teamList.get(n3).rankTeamOppYards = n3 + 1;
        }
        Collections.sort(this.teamList, new TeamCompPYPG());
        for (int n4 = 0; n4 < this.teamList.size(); ++n4) {
            this.teamList.get(n4).rankTeamPassYards = n4 + 1;
        }
        Collections.sort(this.teamList, new TeamCompRYPG());
        for (int n5 = 0; n5 < this.teamList.size(); ++n5) {
            this.teamList.get(n5).rankTeamRushYards = n5 + 1;
        }
        Collections.sort(this.teamList, new TeamCompOPYPG());
        for (int n6 = 0; n6 < this.teamList.size(); ++n6) {
            this.teamList.get(n6).rankTeamOppPassYards = n6 + 1;
        }
        Collections.sort(this.teamList, new TeamCompORYPG());
        for (int n7 = 0; n7 < this.teamList.size(); ++n7) {
            this.teamList.get(n7).rankTeamOppRushYards = n7 + 1;
        }
        Collections.sort(this.teamList, new TeamCompTODiff());
        for (int n8 = 0; n8 < this.teamList.size(); ++n8) {
            this.teamList.get(n8).rankTeamTODiff = n8 + 1;
        }
        Collections.sort(this.teamList, new TeamCompOffTalent());
        for (int n9 = 0; n9 < this.teamList.size(); ++n9) {
            this.teamList.get(n9).rankTeamOffTalent = n9 + 1;
        }
        Collections.sort(this.teamList, new TeamCompDefTalent());
        for (int n10 = 0; n10 < this.teamList.size(); ++n10) {
            this.teamList.get(n10).rankTeamDefTalent = n10 + 1;
        }
        Collections.sort(this.teamList, new TeamCompPrestige());
        for (int n11 = 0; n11 < this.teamList.size(); ++n11) {
            this.teamList.get(n11).rankTeamPrestige = n11 + 1;
        }
    }
    
    public void setUpPlayoffs(int i) {
        if (i <= 1) {
            final ArrayList<Team> list = new ArrayList<Team>();
            this.playoffsAM = new ArrayList<Team>();
            this.playoffsNA = new ArrayList<Team>();
            final ArrayList<Team> list2 = new ArrayList<Team>();
            final ArrayList<Team> list3 = new ArrayList<Team>();
            Team team;
            for (i = 0; i < this.divisions.size(); ++i) {
                list.addAll(this.divisions.get(i).divTeams);
                Collections.sort((List<Object>)list, (Comparator<? super Object>)new TeamCompDivision());
                if (i < 4) {
                    this.playoffsAM.add(list.get(0));
                    list2.addAll(list);
                }
                else {
                    this.playoffsNA.add(list.get(0));
                    list3.addAll(list);
                }
                list.get(0).divChampion = "DW";
                team = list.get(0);
                ++team.totalDivChamps;
                list.clear();
            }
            Collections.sort(this.playoffsAM, new TeamCompDivision());
            Collections.sort(this.playoffsNA, new TeamCompDivision());
            Collections.sort((List<Object>)list2, (Comparator<? super Object>)new TeamCompDivision());
            i = 0;
            for (final Team team2 : list2) {
                int n = i;
                if (!this.playoffsAM.contains(team2)) {
                    this.playoffsAM.add(team2);
                    n = i + 1;
                }
                if ((i = n) >= 2) {
                    break;
                }
            }
            Collections.sort((List<Object>)list3, (Comparator<? super Object>)new TeamCompDivision());
            i = 0;
            for (final Team team3 : list3) {
                int n2 = i;
                if (!this.playoffsNA.contains(team3)) {
                    this.playoffsNA.add(team3);
                    n2 = i + 1;
                }
                if ((i = n2) >= 2) {
                    break;
                }
            }
            this.playoffGames[0] = new Game(this.playoffsAM.get(2), this.playoffsAM.get(5), "AM Rd 1", 16);
            this.playoffsAM.get(2).gameSchedule.add(this.playoffGames[0]);
            this.playoffsAM.get(5).gameSchedule.add(this.playoffGames[0]);
            this.playoffGames[1] = new Game(this.playoffsAM.get(3), this.playoffsAM.get(4), "AM Rd 1", 16);
            this.playoffsAM.get(3).gameSchedule.add(this.playoffGames[1]);
            this.playoffsAM.get(4).gameSchedule.add(this.playoffGames[1]);
            this.playoffGames[2] = new Game(this.playoffsNA.get(2), this.playoffsNA.get(5), "NA Rd 1", 16);
            this.playoffsNA.get(2).gameSchedule.add(this.playoffGames[2]);
            this.playoffsNA.get(5).gameSchedule.add(this.playoffGames[2]);
            this.playoffGames[3] = new Game(this.playoffsNA.get(3), this.playoffsNA.get(4), "NA Rd 1", 16);
            this.playoffsNA.get(3).gameSchedule.add(this.playoffGames[3]);
            this.playoffsNA.get(4).gameSchedule.add(this.playoffGames[3]);
        }
        else {
            if (i == 2) {
                Team winner;
                Team loser;
                for (i = 0; i < 4; ++i) {
                    this.playoffGames[i].playGame();
                    winner = this.playoffGames[i].getWinner();
                    ++winner.totalPlayoffWins;
                    loser = this.playoffGames[i].getLoser();
                    ++loser.totalPlayoffLosses;
                }
                this.playoffsAM.get(0).advanceInjuriesWeek();
                this.playoffsAM.get(1).advanceInjuriesWeek();
                this.playoffsNA.get(0).advanceInjuriesWeek();
                this.playoffsNA.get(1).advanceInjuriesWeek();
                this.playoffGames[4] = new Game(this.playoffsAM.get(0), this.playoffGames[0].getWinner(), "AM Rd 2", 17);
                this.playoffsAM.get(0).gameSchedule.add(this.playoffGames[4]);
                this.playoffGames[0].getWinner().gameSchedule.add(this.playoffGames[4]);
                this.playoffGames[5] = new Game(this.playoffsAM.get(1), this.playoffGames[1].getWinner(), "AM Rd 2", 17);
                this.playoffsAM.get(1).gameSchedule.add(this.playoffGames[5]);
                this.playoffGames[1].getWinner().gameSchedule.add(this.playoffGames[5]);
                this.playoffGames[6] = new Game(this.playoffsNA.get(0), this.playoffGames[2].getWinner(), "NA Rd 2", 17);
                this.playoffsNA.get(0).gameSchedule.add(this.playoffGames[6]);
                this.playoffGames[2].getWinner().gameSchedule.add(this.playoffGames[6]);
                this.playoffGames[7] = new Game(this.playoffsNA.get(1), this.playoffGames[3].getWinner(), "NA Rd 2", 17);
                this.playoffsNA.get(1).gameSchedule.add(this.playoffGames[7]);
                this.playoffGames[3].getWinner().gameSchedule.add(this.playoffGames[7]);
                return;
            }
            if (i == 3) {
                Team winner2;
                Team loser2;
                for (i = 4; i < 8; ++i) {
                    this.playoffGames[i].playGame();
                    winner2 = this.playoffGames[i].getWinner();
                    ++winner2.totalPlayoffWins;
                    loser2 = this.playoffGames[i].getLoser();
                    ++loser2.totalPlayoffLosses;
                }
                this.playoffGames[8] = new Game(this.playoffGames[4].getWinner(), this.playoffGames[5].getWinner(), "AM CCG", 18);
                this.playoffGames[4].getWinner().gameSchedule.add(this.playoffGames[8]);
                this.playoffGames[5].getWinner().gameSchedule.add(this.playoffGames[8]);
                this.playoffGames[9] = new Game(this.playoffGames[6].getWinner(), this.playoffGames[7].getWinner(), "NA CCG", 18);
                this.playoffGames[6].getWinner().gameSchedule.add(this.playoffGames[9]);
                this.playoffGames[7].getWinner().gameSchedule.add(this.playoffGames[9]);
                return;
            }
            if (i == 4) {
                Team winner3;
                Team loser3;
                for (i = 8; i < 10; ++i) {
                    this.playoffGames[i].playGame();
                    winner3 = this.playoffGames[i].getWinner();
                    ++winner3.totalPlayoffWins;
                    loser3 = this.playoffGames[i].getLoser();
                    ++loser3.totalPlayoffLosses;
                }
                this.playoffGames[10] = new Game(this.playoffGames[8].getWinner(), this.playoffGames[9].getWinner(), "Champ Bowl", 19);
                this.playoffGames[8].getWinner().gameSchedule.add(this.playoffGames[10]);
                this.playoffGames[9].getWinner().gameSchedule.add(this.playoffGames[10]);
                return;
            }
            if (i == 5) {
                this.playoffGames[10].playGame();
                final Team winner4 = this.playoffGames[10].getWinner();
                ++winner4.totalPlayoffWins;
                final Team loser4 = this.playoffGames[10].getLoser();
                ++loser4.totalPlayoffLosses;
                final Team winner5 = this.playoffGames[10].getWinner();
                ++winner5.totalSuperBowlWins;
                final Team loser5 = this.playoffGames[10].getLoser();
                ++loser5.totalSuperBowlLosses;
                this.playoffGames[10].getWinner().natChampWL = "CBW";
                this.playoffGames[10].getLoser().natChampWL = "CBL";
            }
        }
    }
    
    public void setUpSchedule() {
        System.out.println("SETTING UP SCHEDULE!");
        final int[] array2;
        final int[] array = array2 = new int[7];
        array2[0] = 1;
        array2[array2[1] = 2] = 4;
        array2[3] = 7;
        array2[4] = 10;
        array2[5] = 11;
        array2[6] = 13;
        final int[] array4;
        final int[] array3 = array4 = new int[3];
        array4[0] = 5;
        array4[1] = 8;
        array4[2] = 12;
        for (final Division division : this.divisions) {
            new Game(division.divTeams.get(0), division.divTeams.get(1), "Division", 0);
            new Game(division.divTeams.get(2), division.divTeams.get(3), "Division", 0);
            new Game(division.divTeams.get(0), division.divTeams.get(2), "Division", 3);
            new Game(division.divTeams.get(1), division.divTeams.get(3), "Division", 3);
            new Game(division.divTeams.get(0), division.divTeams.get(3), "Division", 6);
            new Game(division.divTeams.get(1), division.divTeams.get(2), "Division", 6);
            new Game(division.divTeams.get(1), division.divTeams.get(0), "Division", 9);
            new Game(division.divTeams.get(3), division.divTeams.get(2), "Division", 9);
            new Game(division.divTeams.get(2), division.divTeams.get(0), "Division", 14);
            new Game(division.divTeams.get(3), division.divTeams.get(1), "Division", 14);
            new Game(division.divTeams.get(3), division.divTeams.get(0), "Division", 15);
            new Game(division.divTeams.get(2), division.divTeams.get(1), "Division", 15);
        }
        final ArrayList<ArrayList<Team>> list = new ArrayList<ArrayList<Team>>();
        for (int i = 0; i < 4; ++i) {
            list.add(new ArrayList<Team>());
            final Iterator<Division> iterator2 = this.divisions.iterator();
            while (iterator2.hasNext()) {
                list.get(i).add(iterator2.next().divTeams.get(i));
            }
        }
        for (int j = 0; j < list.size(); ++j) {
            int n = 0;
            for (int k = 0; k < 7; ++k) {
                for (int l = 0; l < 4; ++l) {
                    final Team team = list.get(j).get((n + l) % 7);
                    Team team2;
                    if (l == 0) {
                        team2 = list.get(j).get(7);
                    }
                    else {
                        team2 = list.get(j).get((7 - l + n) % 7);
                    }
                    if (Math.random() < 0.5) {
                        new Game(team, team2, "Reg Season", array[k]);
                    }
                    else {
                        new Game(team2, team, "Reg Season", array[k]);
                    }
                }
                ++n;
            }
        }
        for (int n2 = 0; n2 < 4; ++n2) {
            final Division division2 = this.divisions.get(n2 * 2);
            final Division division3 = this.divisions.get(n2 * 2 + 1);
            for (int n3 = 0; n3 < 4; ++n3) {
                for (int n4 = 0; n4 < 3; ++n4) {
                    if (Math.random() > 0.5) {
                        new Game(division2.divTeams.get(n3), division3.divTeams.get((n4 + n3) % 4), "Reg Season", array3[n4]);
                    }
                    else {
                        new Game(division3.divTeams.get((n4 + n3) % 4), division2.divTeams.get(n3), "Reg Season", array3[n4]);
                    }
                }
            }
        }
        for (final Team team3 : this.teamList) {
            for (int n5 = 0; n5 < 16; ++n5) {
                final Game game = team3.regSeasonSchedule[n5];
                if (game == null) {
                    System.out.println("GAME: #" + n5 + " is null for team: " + team3.abbr);
                }
                team3.gameSchedule.add(game);
            }
        }
    }
    
    public void signExpiringFAs() {
        for (final Team team : this.teamList) {
            if (team != this.userTeam) {
                int i = 0;
                while (i < team.playersFAs.size()) {
                    final Player player = team.playersFAs.get(i);
                    if (player.ratOvr > 75 && Math.random() < (team.wins + 15) / 40.0) {
                        player.setContract(Contract.getContractFA(player));
                        team.addPlayer(player);
                        team.playersFAs.remove(player);
                    }
                    else {
                        ++i;
                    }
                }
            }
        }
    }
    
    public void sortFAs() {
        Collections.sort(this.freeAgents, new PlayerComparator());
    }
    
    public void transferTeamFAs() {
        for (final Team team : this.teamList) {
            final Iterator<Player> iterator2 = team.playersFAs.iterator();
            while (iterator2.hasNext()) {
                this.freeAgents.add(iterator2.next());
            }
            team.playersFAs.clear();
        }
        Collections.sort(this.freeAgents, new PlayerComparator());
    }
    
    public void updateLeagueHistory() {
        for (int i = 0; i < this.teamList.size(); ++i) {
            this.teamList.get(i).updatePollScore();
        }
        Collections.sort(this.teamList, new TeamCompPoll());
        final String[] array = new String[10];
        for (int j = 0; j < 10; ++j) {
            final Team team = this.teamList.get(j);
            array[j] = team.abbr + " (" + team.wins + "-" + team.losses + ")";
        }
        this.leagueHistory.add(array);
    }
    
    public void updateLongestActiveWinStreak() {
        for (final Team team : this.teamList) {
            if (team.winStreak.getStreakLength() > this.longestActiveWinStreak.getStreakLength()) {
                this.longestActiveWinStreak = team.winStreak;
            }
        }
    }
    
    public void updateTeamHistories() {
        for (int i = 0; i < this.teamList.size(); ++i) {
            this.teamList.get(i).updateTeamHistory();
        }
    }
    
    public void updateTeamTalentRatings() {
        final Iterator<Team> iterator = this.teamList.iterator();
        while (iterator.hasNext()) {
            iterator.next().updateTalentRatings();
        }
    }
}
