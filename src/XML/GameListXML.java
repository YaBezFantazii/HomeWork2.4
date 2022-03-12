package XML;

public class GameListXML {
    // Вспомогательный класс для работы с xml файлами (считывание и запись)
    private String NickName1; // Имя игрока 1
    private String NickName2; // Имя игрока 2
    private int cell[]; // Ячейка от 1 до 9, куда сделан ход. Номера элемента массива - порядок ходов
    private int win; // Кто победил: 0 - игрок 1, 1 - игрок 2, 2 - ничья

    public GameListXML(String NickName1, String NickName2, int cell[], int win){
        this.NickName1 = NickName1;
        this.NickName2 = NickName2;
        this.cell = cell;
        this.win = win;
    }

    public GameListXML(String NickName1, String NickName2){
        this.NickName1 = NickName1;
        this.NickName2 = NickName2;
        this.cell = new int[] {0,0,0,0,0,0,0,0,0,0};
    }

    // геттеры и сеттеры
    public int[] getCell() {
        return cell;
    }

    public int getCellId(int id) {
        return cell[id];
    }

    public void setCell(int[] cell) {
        this.cell = cell;
    }

    public void setCellId(int step, int number) {
        this.cell[step] = number;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public String getNickName1() {
        return NickName1;
    }

    public String getNickName2() {
        return NickName2;
    }
}
