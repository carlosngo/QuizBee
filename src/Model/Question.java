package Model;

public abstract class Question {
    private String prompt;

    public Question() {
        prompt = "";
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public abstract boolean isValid();
}
