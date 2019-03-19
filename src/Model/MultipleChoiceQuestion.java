package Model;

import java.util.*;

public class MultipleChoiceQuestion extends Question {
    private final int NUMBER_OF_CHOICES = 4;
    private ArrayList<String> choices;
    private int answer;
    private long startTime;

    public MultipleChoiceQuestion() {
        super();
        choices = new ArrayList<>(4);
        for (int i = 0; i < NUMBER_OF_CHOICES; i++) choices.set(i, "");
        answer = -1;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public void beginTimer() {
        startTime = Calendar.getInstance().getTimeInMillis();
    }

    public int getPoints() {
        long endTime = Calendar.getInstance().getTimeInMillis();
        return 600 - (int)(endTime - startTime) / 1000 * 10;
    }

    public boolean isCorrect(int answer) {
        return this.answer == answer;
    }

    @Override
    public boolean isValid() {
        if (getPrompt().equals("") || answer == -1) return false;
        for (int i = 0; i < NUMBER_OF_CHOICES; i++) if (choices.get(i).equals("")) return false;
        return true;
    }
}
