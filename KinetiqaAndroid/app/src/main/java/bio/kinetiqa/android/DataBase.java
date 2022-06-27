package bio.kinetiqa.android;

import com.github.mikephil.charting.data.Entry;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataBase {
    public static Integer UserId = -1;
    public static Set<Integer> graphInfoID = new HashSet<>();
    public static HashMap<Integer, Boolean> notifyStatus = getNotifications();

    static HashMap<Integer, Boolean> getNotifications() {
        return new HashMap<>();
        //TODO
    } // Вернуть мапу всех id лекарств пользователя и поставлены ли на них нотификации

    static void addNotificationToBase(Substance state) {
        //TODO
    }//Добавить нотификацию для данного вещества. Можно получить id по state.getResourceID()

    static void deleteNotificationFromBase(Substance state) {
        //TODO
    }// Удалить нотификацию для данного вещества.

    static void addSubstanceToBase(Substance state) {
        //TODO
    } // Вся информация про лекарство в класс state. Добавить вещество пользователю

    static void deleteSubstanceFromBase(Substance state) {
        //TODO
    } // Удалить лекарство для пользователя

    @NotNull
    public static List<Substance> getListOfSubstances() {
        List<Substance> substances = new ArrayList<>();
        substances.add(new Substance("Каменный уголь 5", "какое-то описание", R.drawable.test_photo, 5));
        substances.add(new Substance("Каменный уголь 6", "какое-то описание", R.drawable.test_photo, 6));
        substances.add(new Substance("Каменный уголь 7", "какое-то описание", R.drawable.test_photo, 7));
        substances.add(new Substance("Каменный уголь 8", "какое-то описание", R.drawable.test_photo, 8));
        return substances;
        //TODO
    } // Вернуть список всех лекарств пользователя. Как создавать Объект класса Substance видно

    public static ArrayList<Entry> getGraphLine(int subId) {
        ArrayList<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(1f, 5f));
        entries.add(new Entry(2f, 2f));
        entries.add(new Entry(3f, 1f));
        entries.add(new Entry(4f, -3f));
        entries.add(new Entry(5f, 4f));
        entries.add(new Entry(6f, 1f));
        return entries;
        //TODO
    } // по id лекарства получить список точек графика приема - координат графика - (ось X, ось Y)

    public static void addSubstanceOnGraph(Substance state) {
        graphInfoID.add(state.getResourceID());
    }

    public static void deleteSubstanceFromGraph(Substance state) {
        graphInfoID.remove(state.getResourceID());
    }

    public static ArrayList<Substance> getMainSubstanceBase() {
        ArrayList<Substance> products = new ArrayList<>();
        products.add(new Substance("Каменный уголь 0", "какое-то описание", R.drawable.test_photo, 0));
        products.add(new Substance("Каменный уголь 1", "какое-то описание", R.drawable.test_photo, 1));
        products.add(new Substance("Каменный уголь 2", "какое-то описание", R.drawable.test_photo, 2));
        products.add(new Substance("Каменный уголь 3", "какое-то описание", R.drawable.test_photo, 3));
        products.add(new Substance("Каменный уголь 4", "какое-то описание", R.drawable.test_photo, 4));
        return products;
        //TODO
    } // Список лекарств, которые поддерживает наше приложение, и которые пользователь может добавить себе

    public static boolean notificationStatus(int resourceID) {
        Boolean result = notifyStatus.get(resourceID);
        if (result == null) {
            return false;
        }
        return result;
    }

    public static boolean graphStatus(int resourceId) {
        return graphInfoID.contains(resourceId);
    }

    public static void takingMedication(int resourceId) {
        //TODO
    } // добавить запись о приеме лекарства (Только что увидел, что не добавил кнопку про приём, но это 5 минут работы и будет сделано)

    /* public static void login(some info) {
    }  // Думаю можно дать пользователю нулевое id какое-нибудь на самом деле. Но если хочешь реализуй
    */
}
