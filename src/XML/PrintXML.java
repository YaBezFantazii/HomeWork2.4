package XML;

import com.company.Main;

import java.util.ArrayList;

public class PrintXML {

    public static void PrintXML(ArrayList<GameListXML> xml){

        String filed[] = new String[]{"1","2","3","4","5","6","7","8","9"};
        int i=0;
        while (xml.get(0).getCellId(i)!=0){
            if ((i%2)==0) {
                System.out.println("Ход игрока 1 (X) :"+xml.get(0).getNickName1());
                filed[xml.get(0).getCellId(i)-1] = "X";
                System.out.println(Main.PrintField(filed));
            } else {
                System.out.println("Ход игрока 2 (O) :"+xml.get(0).getNickName2());
                filed[xml.get(0).getCellId(i)-1] = "O";
                System.out.println(Main.PrintField(filed));
            }
            i++;
        }
        if (xml.get(0).getWin()==0) {
            System.out.println("Player 1 -> " +xml.get(0).getNickName1()+ " is winner as 'X'! ");
        } else if (xml.get(0).getWin()==1){
            System.out.println("Player 2 ->" +xml.get(0).getNickName2()+ " is winner as 'O'! ");
        } else {
            System.out.println("Draw!");
        }

    }
}
