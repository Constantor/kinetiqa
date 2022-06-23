package bio.kinetiqa.android;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

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
        substances.add(new Substance("Каменный уголь", "аыыыаыа", R.drawable.test_photo));
        substances.add(new Substance("Каменный уголь", "аыыыаыа", R.drawable.test_photo));
        substances.add(new Substance("Каменный уголь", "аыыыаыа", R.drawable.test_photo));
        substances.add(new Substance("Каменный уголь", "аыыыаыа", R.drawable.test_photo));
        return substances;
        //TODO
    }
}
