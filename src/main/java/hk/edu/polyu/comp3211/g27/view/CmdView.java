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
        Joiner joiner=Joiner.on("\n").skipNulls();
        ArrayList<String> list=new ArrayList<String>();
        int[] square_pos= {1906,1894,1882,1870,1858,1846,1526,1206,886,566,246,258,270,282,294,306,626,946,1266,1586};
        StringBuilder newBoard=new StringBuilder(2161);
        newBoard.append(gameBoard());
        for (int i=0;i<GameHolder.get().playersLeft().size();i++) {
            int number=GameHolder.get().currentSquare(GameHolder.get().playersLeft().get(i)).getIndex()-1;
            int position=square_pos[number];
            while (newBoard.charAt(position)!=' '){
                position+=1;
            }
            //get the player relatively symbol to put into the pos.
            int symbol_position=symbol_map().indexOf(GameHolder.get().playersLeft().get(i).getId())+GameHolder.get().playersLeft().get(i).getId().length();
            String s=String.valueOf(symbol_map().charAt(symbol_position));
            newBoard.replace(position,position+1,s);
        }


        //player matching table
        StringBuilder b1=new StringBuilder(10);
        b1.append("     Player    |  Symbol  |   Property   \n");
        for (int n=1;n<=GameHolder.get().playersLeft().size();n++){
            int symbol_position=symbol_map().indexOf(GameHolder.get().playersLeft().get(n-1).getId())+GameHolder.get().playersLeft().get(n-1).getId().length();
            String s=String.valueOf(symbol_map().charAt(symbol_position));
            b1.append(String.format("%-16s",GameHolder.get().playersLeft().get(n-1).getId()) + String.format("%-11s",s)+
                        GameHolder.get().propertyHoldingStatusFor(GameHolder.get().playersLeft().get(n-1))+"\n");
        }

        //table.jail
        b1.append("     Player    |   Money  |   Property   \n");
        for (int n=1;n<=GameHolder.get().playersLeft().size();n++){
            if (GameHolder.get().propertyHoldingStatusFor(GameHolder.get().playersLeft().get(n-1)).size()==1){
                b1.append(String.format("%-16s",GameHolder.get().playersLeft().get(n-1).getId()) + String.format("%-11s",GameHolder.get().currentMoney(GameHolder.get().playersLeft().get(n-1)))+
                        GameHolder.get().propertyHoldingStatusFor(GameHolder.get().playersLeft().get(n-1))+"\n");
            }
            else if(GameHolder.get().propertyHoldingStatusFor(GameHolder.get().playersLeft().get(n-1)).size()==0){
                b1.append(String.format("%-16s",GameHolder.get().playersLeft().get(n-1).getId()) + String.format("%-11s",GameHolder.get().currentMoney(GameHolder.get().playersLeft().get(n-1)))+
                       "--NA--"+"\n");
            }
            else{
                b1.append(String.format("%-16s",GameHolder.get().playersLeft().get(n-1).getId()) +String.format("%-11s",GameHolder.get().currentMoney(GameHolder.get().playersLeft().get(n-1)))+
                        GameHolder.get().propertyHoldingStatusFor(GameHolder.get().playersLeft().get(n-1)).get(0));
                for (int k=1;k<GameHolder.get().propertyHoldingStatusFor(GameHolder.get().playersLeft().get(n-1)).size();k++){
                    b1.append("                           "+ GameHolder.get().propertyHoldingStatusFor(GameHolder.get().playersLeft().get(n-1)).get(k)+"\n");
                }
            }
        }

//        b1.append("Here shows the players in Jail after this turn: \n");
//        int check_num=0;
//        for (int k=1;k<=GameHolder.get().playersLeft().size();k++){
//            if (GameHolder.get().inJailCheck(GameHolder.get().playersLeft().get(k-1))!=0){
//                b1.append(GameHolder.get().playersLeft().get(k-1).getId()+"   "+"\n");
//                check_num+=1;
//            }
//        }
//        if (check_num==0){
//            b1.append("--NA--"+"\n");
//        }

        return joiner.join(newBoard, b1);
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

    //mapping players id with symbol
    public String symbol_map(){
        Joiner joiner=Joiner.on("").skipNulls();
        String[] array=new String[]{"@","#","$","%","&","*"};
        ArrayList<String> list1=new ArrayList<String>();
        for (int i=0;i<GameHolder.get().playersLeft().size();i++){
            list1.add(GameHolder.get().playersLeft().get(i).getId());
            list1.add(array[i]);
        }
        String list_str=joiner.join(list1);
        return list_str;
    }


    public StringBuilder gameBoard(){
        Joiner joiner=Joiner.on("\n").skipNulls();
        ArrayList<String> list=new ArrayList<String>();
        StringBuilder s=new StringBuilder(2161);
        s.append("        11          12          13          14          15                     \n");
        s.append("   |-----------|-----------|-----------|-----------|-----------|-----------|   \n");
        s.append("   |   FREE    |  Shatin   |     ?     |  Tuen Mun |   Tai Po  |   GO TO   |   \n");
        s.append("   |           |           |           |           |           |           | 16\n");
        s.append("   |  PARKING  |  HKD  700 |  CHANCE   |  HKD  400 |  HKD  500 |    JAIL   |   \n");
        s.append("   |-----------|-----------|-----------|-----------|-----------|-----------|   \n");
        s.append("   | Tsing Yi  |                                               |  Sai Kung |   \n");
        s.append("10 |           |                                               |           | 17\n");
        s.append("   | HKD  400  |                                               |  HKD  400 |   \n");
        s.append("   |-----------|                                               |-----------|   \n");
        s.append("   |    ?      |                                               | Yuen Long |   \n");
        s.append(" 9 |           |                                               |           | 18\n");
        s.append("   |  CHANCE   |                                               |  HKD  400 |   \n");
        s.append("   |-----------|                M O N O P O L Y                |-----------|   \n");
        s.append("   | Mong Kok  |                                               |     ?     |   \n");
        s.append(" 8 |           |                                               |           | 19\n");
        s.append("   | HKD  500  |                                               |  CHANCE   |   \n");
        s.append("   |-----------|                                               |-----------|   \n");
        s.append("   |  Shek O   |                                               |   Tai O   |   \n");
        s.append(" 7 |           |                                               |           | 20\n");
        s.append("   | HKD  400  |                                               |  HKD  600 |   \n");
        s.append("   |-----------|-----------|-----------|-----------|-----------|-----------|   \n");
        s.append("   |  IN JAIL  |  Stanley  |INCOME TAX |  Wan Chai |  CENTRAL  |    GO     |   \n");
        s.append(" 6 |           |           |           |           |           |           |   \n");
        s.append("   | /VISITING |  HKD  600 |  PAY  10% |  HKD  700 |  HKD  800 |GET HKD1500|   \n");
        s.append("   |-----------|-----------|-----------|-----------|-----------|-----------|   \n");
        s.append("                     5           4           3           2           1         \n");

        return s;
    }
}



