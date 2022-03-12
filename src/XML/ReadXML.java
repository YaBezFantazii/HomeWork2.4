package XML;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ReadXML {

    public static void ReadXML(String FileName){
        try {
            GameListXML GameListXML = XML.GameListXML.getInstance();
            String player1,player2;
            // Получение фабрики, чтобы после получить билдер документов.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Получили из фабрики билдер, который парсит XML, создает структуру Document в виде иерархического дерева.
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Запарсили XML, создав структуру Document. Теперь у нас есть доступ ко всем элементам, каким нам нужно.
            Document game = builder.parse(new File(FileName));
            // Получение списка всех элементов employee внутри корневого элемента (getDocumentElement возвращает ROOT элемент XML файла).
            NodeList testList = game.getDocumentElement().getElementsByTagName("Player");

            // Перебор всех элементов
            Node Player1 = testList.item(0);
            Node Player2 = testList.item(1);
            // Получение атрибутов каждого элемента
            NamedNodeMap attributes1 = Player1.getAttributes();
            NamedNodeMap attributes2 = Player2.getAttributes();
            // Записываем имена 1 и 2 игрока
            GameListXML.setNickName(
                    attributes1.getNamedItem("name").getNodeValue(),
                    attributes2.getNamedItem("name").getNodeValue()
            );

            NodeList testList3 = game.getDocumentElement().getElementsByTagName("Step");
            Node step = testList3.item(0);

            // Проходим все элементы <step>, и записываем их по порядку в массив cell класса GameListXML.
            // Так же проверяем формат записи ячеек поля игры, если 1,2,3,4... и т.д., то длина getTextContent().length() будет равна 1
            // если нет, то запись произведена по координатам (к примеру 1,1, 1,2 ...) и т.д., и тогда вызывем метод для преобразования
            // этого формата в 1,2,3,4...
            if (step.getTextContent().length()==1){
                for (int i = 0; i < testList3.getLength(); i++) {
                    step = testList3.item(i);
                    GameListXML.setCellId(Integer.parseInt(step.getTextContent()));
                }
            } else {
                for (int i = 0; i < testList3.getLength(); i++) {
                    step = testList3.item(i);
                    // GameListXML.FormatStep - метод для преобразования данных
                    GameListXML.setCellId(FormatStep.FormatStep(step.getTextContent()));
                }
            }

            // если у <Player> 3 элемента (последний лежит в <GameResult>), значит это не ничья
            if (testList.getLength()==3){
                Node Result = testList.item(2);
                NamedNodeMap attributes3 = Result.getAttributes();
                // Если id 3 элемента <Player> = 1, значит победил игрок 1, иначе игрок 2
                if (Integer.parseInt(attributes3.getNamedItem("id").getNodeValue())==1) {
                    GameListXML.setWin(0);
                } else {
                    GameListXML.setWin(1);
                }
            // ничья
            } else {
                GameListXML.setWin(2);
            }

            // Передаем в метод PrintXML массив xml с полученными данными (метод производит печать
            // данных в консоль).
            PrintXML.PrintXML();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
