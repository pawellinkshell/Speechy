package pl.pawel.linkshell.speechy.model;

import java.util.List;

public class Sentence {
    private double wordPerSentence;
    private String sentence;
    private List<String> words;

    public String getSentence() {
        return sentence;
    }

    public void setSentence(final String sentence) {
        this.sentence = sentence;
    }

    public void setWords(final List<String> words) {
        this.words = words;
    }

    public List<String> getWords() {
        return words;
    }

    public double getWordPerSentence() {
        return wordPerSentence;
    }

    public void setWordPerSentence(final double wordPerSentence) {
        this.wordPerSentence = wordPerSentence;
    }
}
