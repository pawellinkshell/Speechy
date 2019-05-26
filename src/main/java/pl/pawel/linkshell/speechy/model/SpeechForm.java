package pl.pawel.linkshell.speechy.model;

public class SpeechForm {
    private String textareaField;
    private int wordsPerMinute;

    public String getTextareaField() {
        return textareaField;
    }

    public void setTextareaField(final String textareaField) {
        this.textareaField = textareaField;
    }

    public int getWordsPerMinute() {
        return wordsPerMinute;
    }

    public void setWordsPerMinute(final int wordsPerMinute) {
        this.wordsPerMinute = wordsPerMinute;
    }
}
