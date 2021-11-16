package hk.edu.polyu.comp3211.g27.view;




import com.google.common.base.Joiner;
import hk.edu.polyu.comp3211.g27.controller.GameHolder;
import hk.edu.polyu.comp3211.g27.model.Game;

import java.util.ArrayList;

/**
 * This class renders various components of a {@link Game}.
 */
public class CmdView {
    /**
     * Render the game as a whole.
     *
     * @return the String representation of the current game.
     */
    public String game() {
        //help manual
        Joiner joiner=Joiner.on("\n").skipNulls();
        ArrayList<String> list=new ArrayList<String>();
        list.add("*********************************************************************");
        list.add("               Welcome to The Monopoly Game!");
        list.add("The game is played with a board divided into 20 squares, and a pair of \n" +
                "four-sided (tetrahedral) dice and it can accommodate two to six players. Besides playing the \n" +
                "game, Players should also be able to save and load a game.");
        list.add("**********************************************************************");
        list.add("Here shows the announcement and work flow:");
        list.add("1. Players have money and can own properties. Each player starts with \n" +
                "HKD 1500 and no property.");
        list.add("2. All players start from the first square ('Go').");
        list.add("3. Players take turns in rolling the dice and advancing their respective tokens clockwise on the \n" +
                "board. After reaching square 20, a token moves to square 1 again.");
        list.add("4. Certain squares take effect on a player (see below) when her token \n" +
                "passes or lands on the square. For example, they can change the player’s amount of money.");
        list.add("5. If after taking a turn a player has a negative amount of money, she \n" +
                "retires from the game. All her properties become unowned.");
        list.add("6. A round consists of all players taking their turns once.");
        list.add("7. The game ends either if there is only one player left or after 100 rounds. The winner is \n" +
                "the player with the most money at the end of the game. Ties (multiple winners) are possible.");
        list.add("************************************************************************");
        list.add("Here shows 7 different types of squares in the Game Board:");
        list.add("-------------------------------------------------------------------------");
        list.add("Property squares");
        list.add("They contain the name and the price of the property and can be owned by players. If a player lands on an unowned \n" +
                "property, she can choose to buy it for the written price or do nothing. If a player lands \n" +
                "on a property owned by another player, she has to pay a rent.");
        list.add("--------------------------------------------------------------------------");
        list.add("Here shows the Price and Rent amount:");
        list.add("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        list.add("Pos       Name       Price      Rent");
        list.add("2        Central      800       90");
        list.add("3        Wan Chai     700       65");
        list.add("5        Stanley      600       60");
        list.add("7        Shek O       400       10");
        list.add("8        Mong Kok     500       40");
        list.add("10       Tsing Yi     400       15");
        list.add("12       Shatin       700       75");
        list.add("14       Tuen Mun     400       20");
        list.add("15       Tai Po       500       25");
        list.add("17       Sai Kung     400       10");
        list.add("18       Yuen Long    400       25");
        list.add("20       Tai O        600       25");
        list.add("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        list.add("-----------------------------------------------------------------------------");
        list.add("GO");
        list.add(" Every time a player passes through (not necessarily lands on) this square, she \n" +
                "gets HKD 1500 salary.");
        list.add("------------------------------------------------------------------------------");
        list.add("Chance");
        list.add("If a player lands on one of these squares, she either gains a random amount \n" +
                "(multiple of 10) up to HKD 200 or loses a \n" +
                "random amount (multiple of 10) up to HKD 300.");
        list.add("------------------------------------------------------------------------------");
        list.add("Income tax");
        list.add("If a player lands on this square,she pays 10% of her money (rounded down to \n" +
                "a multiple of 10) as tax.");
        list.add("-------------------------------------------------------------------------------");
        list.add("Free parking");
        list.add("This square has no effect.");
        list.add("-------------------------------------------------------------------------------");
        list.add("Go to Jail");
        list.add(" If a player lands on this square, she immediately goes to the 'In Jail' part of the 'In Jail/Just Visiting' square.");
        list.add("-------------------------------------------------------------------------------");
        list.add("In Jail/Just Visiting");
        list.add("If a player lands on this square, she is 'Just Visiting': the square has no effect.");
        list.add("If the player got here by landing on 'Go to Jail', she is in jail and cannot make a move.");
        list.add("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        list.add("Tip 1: A player gets out of jail by either throwing doubles (i.e., both dice \n" +
                "coming out the same face up) on any of her next three turns (if she succeeds in doing \n" +
                "this, she immediately moves forward by the number of spaces shown by her doubles \n" +
                "throw) or paying a fine of HKD 150 before she rolls the dice on either of her next two \n" +
                "turns.");
        list.add("Tip 2: If the player does not throw doubles by her third turn, she must pay the HKD 150 \n" +
                "fine. She then gets out of jail and immediately moves forward the number of spaces \n" +
                "shown by her throw. ");
        list.add("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        list.add("----------------------------------------------------------------------------------");
        list.add("                       ^-^  Enjoy your game!   ");
        list.add("**********************************************************************************");




        gameBoard();
        list.add(" Hi players, at the beginning of the game, you are all in the Square GO (1), and each of you start with HKD 1500 and no property.");
        list.add("Currently there is no people in jail.");



        int i=0;
        while(i<=100 && GameHolder.get().playersLeft().size()!=0){
            if (i==0){
                list.add("Player      Money        Property");
                for (int j=1;j<=GameHolder.get().playersLeft().size();j++){
                    list.add(GameHolder.get().playersLeft().get(j-1).getId()+"   "+GameHolder.get().currentMoney(GameHolder.get().playersLeft().get(j-1))+"   "+
                            GameHolder.get().propertyHoldingStatusFor(GameHolder.get().playersLeft().get(i)));
                }
            }
            for (int j=0;j<GameHolder.get().playersLeft().size();j++){
                if (i!=0){
                    turn();
                    int number=GameHolder.get().currentSquare(GameHolder.get().playersLeft().get(j)).getIndex();
                    switch(number){
                        case 1:
                            list.add("Congratulations! you have already travelled a round. Now you are again at Square GO (1)" +
                                    ", and 1500 HKD Salary are added into your account.");
                        case 2: case 3: case 5: case 7: case 8: case 10: case 12: case 14: case 15: case 17: case 18:case 20 :
                            list.add("Now you are in the Square "+ GameHolder.get().currentTurn().getOldSquare().getLabel()+
                                    " ( "+GameHolder.get().currentTurn().getOldSquare().getIndex()+" ).");
                        case 9: case 13: case 19:
                            list.add("Now you are in the CHANCE Square. You will GAIN or LOST certain money. Good luck!");
                        case 4:
                            list.add("Now you need to pay 10% of your income tax");
                        case 6:
                            if (GameHolder.get().inJailCheck(GameHolder.get().playersLeft().get(j))==0){
                                list.add("Don't worry, you just visit the jail.");
                            }
                            else{
                                list.add("You are in jail, GOOD LUCK.");
                            }

                        case 11:
                            list.add("This is Free Parking Square, you can just have a rest and wait for your " +
                                    "next turn!");
                        case 16:
                            list.add("Oops, you have unluckily triggered the GO TO JAIL Square, you are now in JAIL and be" +
                                    "moved to the Square 6.");



                    }
                    //table.jail
                    list.add("     Player    |   Money  |   Property   |");
                    for (int n=1;n<=GameHolder.get().playersLeft().size();n++){
                        if (GameHolder.get().propertyHoldingStatusFor(GameHolder.get().playersLeft().get(n-1)).size()==1){
                            list.add(String.format("%-16s",GameHolder.get().playersLeft().get(n-1).getId()) + String.format("%-11s",GameHolder.get().currentMoney(GameHolder.get().playersLeft().get(n-1)))+
                                GameHolder.get().propertyHoldingStatusFor(GameHolder.get().playersLeft().get(n-1)));
                        }
                        else{
                            list.add(String.format("%-16s",GameHolder.get().playersLeft().get(n-1).getId()) +String.format("%-11s",GameHolder.get().currentMoney(GameHolder.get().playersLeft().get(n-1)))+
                                    GameHolder.get().propertyHoldingStatusFor(GameHolder.get().playersLeft().get(n-1)).get(0));
                            for (int k=1;k<GameHolder.get().propertyHoldingStatusFor(GameHolder.get().playersLeft().get(n-1)).size();k++){
                                list.add("                           "+ GameHolder.get().propertyHoldingStatusFor(GameHolder.get().playersLeft().get(n-1)).get(k));
                            }
                        }
                    }

                    list.add("Here shows the players in Jail after this turn: ");
                    int check_num=0;
                    for (int k=1;k<=GameHolder.get().playersLeft().size();k++){
                        if (GameHolder.get().inJailCheck(GameHolder.get().playersLeft().get(k-1))!=0){
                            list.add(GameHolder.get().playersLeft().get(k-1).getId()+"   ");
                            check_num+=1;
                        }
                    }
                    if (check_num==0){
                        list.add("--NA--");
                    }

                }


            }
            i=i+1;
        }

        String list_str= joiner.join(list);
        return list_str;
    }

    public String gameId(){  //
        Joiner joiner=Joiner.on("").skipNulls();
        ArrayList<String> list=new ArrayList<String>();
        list.add(GameHolder.get().getId());
        String list_str= joiner.join(list);
        return list_str;
    }

    public String playersLeft(){
        Joiner joiner=Joiner.on(" ").skipNulls();
        ArrayList<String> list=new ArrayList<String>();
        for (int k=1;k<=GameHolder.get().playersLeft().size();k++){
            list.add(GameHolder.get().playersLeft().get(k-1).getId());
        }

        String list_str= joiner.join(list);
        return list_str;

    }

    public String currentMoney() {
        Joiner joiner=Joiner.on("\n").skipNulls();
        ArrayList<String> list=new ArrayList<String>();
        for (int i=0;i<GameHolder.get().playersLeft().size();i++){
            list.add(String.valueOf(GameHolder.get().currentMoney(GameHolder.get().playersLeft().get(i))));
        }
        String list_str= joiner.join(list);    //
        return list_str;
    }

    public String currentPropertyHolding(){
        Joiner joiner=Joiner.on("\n").skipNulls();
        ArrayList<String> list=new ArrayList<String>();
        for (int i=0;i<GameHolder.get().playersLeft().size();i++){
            list.add((GameHolder.get().propertyHoldingStatusFor(GameHolder.get().playersLeft().get(i))).toString());
        }
        String list_str= joiner.join(list);
        return list_str;
    }

    public String turn() {
        Joiner joiner=Joiner.on("\n").skipNulls();
        ArrayList<String> list=new ArrayList<String>();
        list.add("This is "+GameHolder.get().currentTurn().getPlayer()+" turn.");
        list.add("Now you are in the Square: "+GameHolder.get().currentTurn().getOldSquare().getLabel());
        list.add(" ( "+GameHolder.get().currentTurn().getOldSquare().getIndex()+" ).");
        String list_str= joiner.join(list);
        return list_str;
    }

    public String gameBoard(){
        Joiner joiner=Joiner.on("\n").skipNulls();
        ArrayList<String> list=new ArrayList<String>();
        list.add("        11          12          13          14          15");
        list.add("   |----------|-----------|-----------|-----------|-----------|-----------|");
        list.add("   |   FREE   |  Shatin   |     ?     |  Tuen Mun |   Tai Po  |    GO TO  |");
        list.add("   |          |           |           |           |           |           | 16");
        list.add("   |  PARKING |  HKD  700 |  CHANCE   |  HKD  400 |  HKD  500 |    JAIL   |");
        list.add("   |----------|-----------|-----------|-----------|-----------|-----------|");
        list.add("   | Tsing Yi |                                               |  Sai Kung |");
        list.add("10 |          |                                               |           | 17");
        list.add("   | HKD  400 |                                               |  HKD  400 |");
        list.add("   |----------|                                               |-----------|");
        list.add("   |    ?     |                                               | Yuen Long |");
        list.add(" 9 |          |                                               |           | 18");
        list.add("   |  CHANCE  |                                               |  HKD  400 |");
        list.add("   |----------|                M O N O P O L Y                |-----------|");
        list.add("   | Mong Kok |                                               |     ?     |");
        list.add(" 8 |          |                                               |           | 19");
        list.add("   | HKD  500 |                                               |  CHANCE   |");
        list.add("   |----------|                                               |-----------|");
        list.add("   |  Shek O  |                                               |   Tai O   |");
        list.add(" 7 |          |                                               |           | 20");
        list.add("   | HKD  400 |                                               |  HKD  600 |");
        list.add("   |----------|-----------|-----------|-----------|-----------|-----------|");
        list.add("   |  IN JAIL |  Stanley  |   INCOME  |  Wan Chai |  CENTRAL  |    GO     |");
        list.add(" 6 |  / JUST  |           |    TAX    |           |           |           |");
        list.add("   | VISITING |  HKD  600 |  PAY  10% |  HKD  700 |  HKD  800 |GET HKD1500|");
        list.add("   |----------|-----------|-----------|-----------|-----------|-----------|");
        list.add("                    5           4           3           2           1 ");
        String list_str= joiner.join(list);
        return list_str;
    }
}

