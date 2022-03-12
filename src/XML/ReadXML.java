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
import java.util.ArrayList;

public class ReadXML {

    public static void ReadXML(String FileName){
        try {
            ArrayList<GameListXML> xml = new ArrayList<>();
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
            player1 = attributes1.getNamedItem("name").getNodeValue();
            player2 = attributes2.getNamedItem("name").getNodeValue();

            NodeList testList3 = game.getDocumentElement().getElementsByTagName("Step");
            int cells[] = new int[]{0,0,0,0,0,0,0,0,0,0};
            // Проходим все элементы <step>, и записываем их в массив cells в формате
            // cells[номер шага] = куда поставлено X или 0
            for (int i = 0; i < testList3.getLength(); i++) {
                Node step = testList3.item(i);
                cells[i] = Integer.parseInt(step.getTextContent());
            }

            // если у <Player> 3 элемента (последний лежит в <GameResult>), значит это не ничья
            if (testList.getLength()==3){
                Node Result = testList.item(2);
                NamedNodeMap attributes3 = Result.getAttributes();
                // Если id 3 элемента <Player> = 1, значит победил игрок 1, иначе игрок 2
                if (Integer.parseInt(attributes3.getNamedItem("id").getNodeValue())==1) {
                    xml.add(new GameListXML(player1,player2,cells,0));
                } else {
                    xml.add(new GameListXML(player1,player2,cells,1));
                }
            // ничья
            } else {
                xml.add(new GameListXML(player1,player2,cells,2));
            }

            // Передаем в метод PrintXML массив xml с полученными данными (метод производит печать
            // данных в консоль).
            PrintXML.PrintXML(xml);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
