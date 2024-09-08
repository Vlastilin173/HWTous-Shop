import static Files.Vars.allToys;
import static Files.Vars.winsToys;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Files.MyFileWriter;
import Toy.Toy;

public class Main {

    public static void main(String[] args) {
        //Сначала создаем произвольные игрушки
        Toy doll = new Toy(1, "doll", 10, 15);
        Toy car = new Toy(2, "car", 3, 30);
        Toy cat = new Toy(3, "cat", 4, 20);
        Toy elephant = new Toy(4, "elefant", 10, 100);
        Toy designer = new Toy(5, "designer", 6, 40);
        Toy ball = new Toy(6, "ball", 5, 8);

        //добавляем созданные игрушки в список (в обычный непризовой) с новым весом
        addToListWithNewWeight(doll, 5);
        addToListWithNewWeight(car, 10);
        addToListWithNewWeight(cat, 21);
        addToListWithNewWeight(elephant, 7);
        addToListWithNewWeight(designer, 45);
        addToListWithNewWeight(ball, 67);

        //показываем что у нас в общем списке игрушек
        System.out.println("Имеющиеся игрушки: ");
        showNewList(allToys);

        //формируем список призовых игрушек (пусть это будут с весом от 20)
        List<Toy> addedWinners = selectToyByMinWeight(allToys, 20);
        winsToys.addAll(addedWinners);

        //отображаем призовые игрушки
        System.out.println("Призовые игрушки: ");
        showNewList(winsToys);

        //выбираем призовую игрушку на выдачу из призового списка (пусть случайным образом)
        Toy givedWinner = selectRandomToy(winsToys);
        //удаляем выданную игрушку из списка к выдаче
        winsToys.remove(givedWinner);

        //отображаем инфо о призовой игрушке на выдачу
        System.out.println("Победитель получает: " + givedWinner.info());

        //пишем инфо в текстовый файл
        MyFileWriter.writeToy(givedWinner);

    }

    private static List<Toy> selectToyByMinWeight(ArrayList<Toy> allToys, int minWeight) {
        List<Toy> result = new ArrayList<>();
        for (Toy currentToy : allToys) {
            if (currentToy.getWeight() <= minWeight) {
                result.add(currentToy);
            }
        }
        return result;
    }

    private static Toy selectRandomToy(ArrayList<Toy> winsToys) {
        Random rand = new Random();
        int winnerElement = rand.nextInt(winsToys.size());
        return winsToys.get(winnerElement);
    }

    private static void addToListWithNewWeight(Toy newToy, int newWeight) {
        newToy.setWeight(newWeight);
        allToys.add(newToy);
    }

    private static void showNewList(ArrayList<Toy> myList) {
        int winListSize = myList.size();
        for (int i = 0; i < winListSize; i++) {
            System.out.print("Игрушка [" + i + "]: " + myList.get(i).getName() + ", ");
        }
        System.out.println("");
    }
}