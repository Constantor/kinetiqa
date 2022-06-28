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
    public static Set<Integer> graphInfoID = getGraphInfo();
    public static HashMap<Integer, Boolean> notifyStatus = getNotifications();

    private static Set<Integer> getGraphInfo() {
        Set<Integer> graphInfo = new HashSet<>();
        graphInfo.add(6);
        graphInfo.add(7);
        return graphInfo;
    }

    static HashMap<Integer, Boolean> getNotifications() {
        HashMap<Integer, Boolean> map = new HashMap<>();
        map.put(5, false);
        map.put(6, true);
        map.put(7, true);
        map.put(8, false);
        return map;
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
        String desc1 = "Препарат предназначен для симптоматической терапии, уменьшения боли и воспаления на момент использования, на прогрессирование заболевания не влияет.";
        String desc2 = "Супрастин уменьшает выраженность общих симптомов аллергических заболеваний, кроме противоаллергического, оказывает седативное, снотворное и противозудное действие";
        String desc3 = "Ингавирин снижает вирусную нагрузку, ускоряет элиминацию вирусов, сокращает продолжительность болезни, снижает риск развития осложнений.";
        String desc4 = "Эффективен при эндогенных депрессиях и обсессивно-компульсивных расстройствах. Улучшает настроение, снижает напряженность, тревожность и чувство страха";
        String desc5 = "Требуется при дефиците магния, изолированный или связанный с другими дефицитными состояниями, сопровождающийся различными симптомами";
        substances.add(new Substance("Нимесулид", desc1, R.drawable.nayz, 5));
        substances.add(new Substance("Супрастин", desc2, R.drawable.suprastin, 6));
        substances.add(new Substance("Ингавирин", desc3, R.drawable.ingavirin, 7));
        substances.add(new Substance("Флуоксетин", desc4, R.drawable.fluoxetin, 8));
        substances.add(new Substance("Магне B6", desc5, R.drawable.magne, 9));
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
        String desc1 = "Ибупрофен применяется как обезболивающее и противовоспалительное средство при лечении ревматоидного артрита, анкилозирующего спондилита, остеоартроза и других неревматоидных артропатий.";
        String desc2 = "Используется для лечения тромбоза глубоких вен и легочной эмболии, а также для предотвращения образования тромбов при фибрилляции предсердий и после операций на бедре или колене.";
        String desc3 = "Средство, широко применяющееся в кардиологической практике. Показаниями для назначения бисопролола являются артериальная гипертензия, ишемическая болезнь сердца, сердечная недостаточность, нарушения сердечного ритма.";
        String desc4 = "Обладает противовирусным действием, активен в отношении сложноустроенных вирусов. обладает противомикробным, противовоспалительным и местным действием";
        products.add(new Substance("Нурофен", desc1, R.drawable.nurofen, 5));
        products.add(new Substance("Ксарелто", desc2, R.drawable.ksorelto, 6));
        products.add(new Substance("Конкор", desc3, R.drawable.konkor, 7));
        products.add(new Substance("Мирамистин", desc4, R.drawable.miramistin, 8));
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

    public static Substance getSubstanceFromId(int subID) {
        String desc1 = "Клинико-фармакологическая группа: НПВС. Селективный ингибитор ЦОГ-2\n" +
                "Фармако-терапевтическая группа: НПВП\n" +
                "Фармакологическое действие\n" +
                "НПВС из класса сульфонанилидов. Является селективным конкурентным ингибитором ЦОГ-2, тормозит синтез простагландинов в очаге воспаления. Угнетающее влияние на ЦОГ-1 менее выражено (реже вызывает побочные эффекты, связанные с угнетением синтеза простагландинов в здоровых тканях). Оказывает противовоспалительное, анальгезирующее и выраженное жаропонижающее действие.\n" +
                "\n" +
                "Фармакокинетика\n" +
                "Всасывание\n" +
                "\n" +
                "После приема внутрь нимесулид хорошо абсорбируется из ЖКТ. Прием пищи снижает скорость абсорбции, не влияя на ее степень. Cmax нимесулида составляет 3.5-6.5 мг/л.\n" +
                "\n" +
                "Распределение\n" +
                "\n" +
                "Связывание с белками плазмы составляет 95%, с эритроцитами - 2%, с липопротеинами - 1%, с кислыми α1-гликопротеидами - 1%. Доза препарата не влияет на степень связывания с белками крови.\n" +
                "\n" +
                "Vd составляет 0.19-0.35 л/кг. Проникает в ткани женских половых органов, где после однократного приема концентрация нимесулида составляет около 40% от концентрации в плазме. Хорошо проникает в кислую среду очага воспаления (40%), синовиальную жидкость (43%). Легко проникает через гистогематические барьеры.\n" +
                "\n" +
                "Метаболизм\n" +
                "\n" +
                "Нимесулид метаболизируется в печени тканевыми монооксигеназами. Основной метаболит - 4-гидроксинимесулид (25%) обладает сходной фармакологической активностью, но вследствие уменьшения размера молекул способен быстро диффундировать по гидрофобному каналу ЦОГ-2 к активному центру связывания метильной группы. 4-гидроксинимесулид является водорастворимым соединением, для выведения которого не требуются глутатион и реакции конъюгации II фазы метаболизма (сульфатирование, глюкуронирование и другие).\n" +
                "\n" +
                "Выведение\n" +
                "\n" +
                "T1/2 нимесулида составляет 1.56-4.95 ч, T1/2 4-гидроксинимесулида - 2.89-4.78 ч. Метаболит выводится почками (65%) и с желчью (35%), подвергается энтерогепатической рециркуляции.\n" +
                "\n" +
                "Фармакокинетика в особых клинических случаях\n" +
                "\n" +
                "У больных с почечной недостаточностью (КК 30-80 мл/мин), а также у детей и лиц пожилого возраста фармакокинетический профиль нимесулида существенно не меняется.";
        return new Substance("Нимесулид", desc1, R.drawable.nayz, 5);
        //TODO
    }

    public static void addTakeMedicine(Substance substance) {
        //TODO
    }

    public static void deleteUserSubstance(Substance substance) {
        //TODO
    }

    /* public static void login(some info) {
    }  // Думаю можно дать пользователю нулевое id какое-нибудь на самом деле. Но если хочешь реализуй
    */
}
