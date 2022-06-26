package bio.kinetiqa.android;

import com.github.mikephil.charting.data.Entry;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    public static Integer UserId = -1;
    public static ArrayList<Integer> graphInfoID = new ArrayList<>();

    static void addNotificationToBase(Substance state) {
        //TODO
    }

    static void deleteNotificationFromBase(Substance state) {
        //TODO
    }

    static void addSubstanceToBase(Substance state) {
        //TODO
    }

    static void deleteSubstanceFromBase(Substance state) {
        //TODO
    }

    @NotNull
    public static List<Substance> getListOfSubstances() {
        List<Substance> substances = new ArrayList<>();
        substances.add(new Substance("Каменный уголь", "какое-то описание", R.drawable.test_photo, 0));
        substances.add(new Substance("Каменный уголь", "какое-то описание", R.drawable.test_photo, 0));
        substances.add(new Substance("Каменный уголь", "какое-то описание", R.drawable.test_photo, 0));
        substances.add(new Substance("Каменный уголь", "какое-то описание", R.drawable.test_photo, 0));
        return substances;
        //TODO
    }

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
    }

    public static void addSubstanceOnGraph(Substance state) {
        graphInfoID.add(state.getResourceID());
    }

    public static void deleteSubstanceFromGraph(Substance state) {
        graphInfoID.remove(state.getResourceID());
    }

    public static ArrayList<Substance> getMainSubstanceBase() {
        ArrayList<Substance> products = new ArrayList<>();
        products.add(new Substance("Каменный уголь", "какое-то описание", R.drawable.test_photo, 0));
        products.add(new Substance("Каменный уголь", "какое-то описание", R.drawable.test_photo, 0));
        products.add(new Substance("Каменный уголь", "какое-то описание", R.drawable.test_photo, 0));
        products.add(new Substance("Каменный уголь", "какое-то описание", R.drawable.test_photo, 0));
        products.add(new Substance("Каменный уголь", "какое-то описание", R.drawable.test_photo, 0));
        return products;
        //TODO
    }
}
