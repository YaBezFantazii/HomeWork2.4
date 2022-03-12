package XML;

import com.company.Main;

public class PrintXML {

    // Метод, печатающий в консоль ходы игры, полученные из xml файла
    public static void PrintXML(){

        GameListXML xml = GameListXML.getInstance();
        String filed[] = new String[]{"1","2","3","4","5","6","7","8","9"};
        System.out.println();
        // Так как игроки всегда ходят поочередно, 1 игрок всегда Х, 2 всегда 0,
        // то xml.getCellId[i] - массив int, где i[0,2,4..четное] - ходы 1 игрока, i = нечетное - ходы 2 игрока
        // i=1,2,3... - соответствует порядку ходов
        for (int i=0;i<xml.getCell().size();i++){
            if ((i%2)==0) {
                System.out.println("Ход игрока 1 (X) :"+xml.getNickName1());
                filed[xml.getCellId(i)-1] = "X";
                System.out.println(Main.PrintField(filed));
            } else {
                System.out.println("Ход игрока 2 (O) :"+xml.getNickName2());
                filed[xml.getCellId(i)-1] = "O";
                System.out.println(Main.PrintField(filed));
            }
        }

        if (xml.getWin()==0) {
            System.out.println("Player 1 -> " +xml.getNickName1()+ " is winner as 'X'!\n");
        } else if (xml.getWin()==1){
            System.out.println("Player 2 -> " +xml.getNickName2()+ " is winner as 'O'!\n");
        } else {
            System.out.println("Draw!\n");
        }

    }
}
