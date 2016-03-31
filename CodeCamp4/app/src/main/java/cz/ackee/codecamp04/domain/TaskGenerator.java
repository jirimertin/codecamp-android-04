package cz.ackee.codecamp04.domain;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class responsible for generating tasks
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {31.3.16}
 **/
public class TaskGenerator {
    public static final String TAG = TaskGenerator.class.getName();

    private static final String[] VERBS = {
            "uklidit", "uvarit", "vyzehlit", "nakrajet", "ostrouhat", "dodelat", "vypnout", "nastavit",
            "otevrit", "vypit"
    };

    private static final String[] OBJECTS = {
            "vejce", "kuchyn", "kosili", "chleba", "jablko", "ukol", "topeni", "termostat",
            "lahev", "mliko"
    };

    public static ArrayList<Task> generateTasks(int n) {
        int verbsSize = VERBS.length;
        int objectSize = OBJECTS.length;

        ArrayList<Task> toReturn = new ArrayList<>();

        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < n; ++i) {
            builder.append(VERBS[random.nextInt(verbsSize - 1)]);
            builder.append(" ");
            builder.append(OBJECTS[random.nextInt(objectSize - 1)]);
            toReturn.add(new Task(builder.toString()));
            builder.setLength(0);
        }
        return toReturn;
    }
}
