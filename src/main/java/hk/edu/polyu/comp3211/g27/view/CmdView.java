package hk.edu.polyu.comp3211.g27.view;

import com.google.common.base.Joiner;
import hk.edu.polyu.comp3211.g27.controller.GameHolder;
import hk.edu.polyu.comp3211.g27.model.Game;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class renders various components of a {@link Game}.
 */
public class CmdView {
    private static final int Square1_pos3=1906;
    private static final int Square2_pos3=1894;
    private static final int Square3_pos3=1882;
    private static final int Square4_pos3=1870;
    private static final int Square5_pos3=1858;
    private static final int Square6_pos3=1846;
    private static final int Square7_pos3=1526;
    private static final int Square8_pos3=1206;
    private static final int Square9_pos3=886;
    private static final int Square10_pos3=566;
    private static final int Square11_pos3=246;
    private static final int Square12_pos3=258;
    private static final int Square13_pos3=270;
    private static final int Square14_pos3=282;
    private static final int Square15_pos3=294;
    private static final int Square16_pos3=306;
    private static final int Square17_pos3=626;
    private static final int Square18_pos3=946;
    private static final int Square19_pos3=1266;
    private static final int Square20_pos3=1586;

    /**
     * Render the game as a whole.
     *
     * @return the String representation of the current game.
     */
    public String game() {
        Joiner joiner=Joiner.on("\n").skipNulls();
        ArrayList<String> list=new ArrayList<String>();
        int[] square_pos= {Square1_pos3,Square2_pos3,Square3_pos3,Square4_pos3,Square5_pos3,Square6_pos3,
                Square7_pos3,Square8_pos3,Square9_pos3,Square10_pos3,Square11_pos3,Square12_pos3,
                Square13_pos3,Square14_pos3,Square15_pos3,Square16_pos3,Square17_pos3,Square18_pos3,Square19_pos3,Square20_pos3};
        StringBuilder newBoard=new StringBuilder(2161);
        newBoard.append(gameBoard());
        for (int i=0;i<GameHolder.get().playersLeft().size();i++) {
            int number=GameHolder.get().currentSquare(GameHolder.get().playersLeft().get(i)).getIndex() - 1;
            int position=square_pos[number];
            while (newBoard.charAt(position)!=' '){
                position+=1;
            }
            //get the player relatively symbol to put into the pos.
            String s=symbol_map().get(GameHolder.get().playersLeft().get(i).getId());
            newBoard.replace(position,position+1,s);
        }


        //player matching table
        StringBuilder b1=new StringBuilder(10);
        b1.append("     Player    |   Money  |  In Jail  |    Property   \n");
        for (int n=1;n<=GameHolder.get().playersLeft().size();n++){
            String s=symbol_map().get(GameHolder.get().playersLeft().get(n-1).getId());
            b1.append(String.format("%-16s",GameHolder.get().playersLeft().get(n-1).getId()) + "  "+String.format("%-11s",GameHolder.get().currentMoney(GameHolder.get().playersLeft().get(n-1)))+
                    "   "+String.format("%-8s",GameHolder.get().inJailCheck(GameHolder.get().playersLeft().get(n-1)))+"   "+
                    GameHolder.get().propertyHoldingStatusFor(GameHolder.get().playersLeft().get(n-1))+"\n");
        }


        String list_str= joiner.join(newBoard,b1);
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

    //mapping players id with symbol
    public HashMap<String, String> symbol_map(){
        String[] array=new String[]{"1","2","3","4","5","6"};
        HashMap<String,String> mapping=new HashMap<String,String>();
        for (int i=0;i<GameHolder.get().playersLeft().size();i++){
            mapping.put(GameHolder.get().playersLeft().get(i).getId(),array[i]);
        }
        return mapping;
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

