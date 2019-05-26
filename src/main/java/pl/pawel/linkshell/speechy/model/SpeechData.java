package pl.pawel.linkshell.speechy.model;

import java.util.List;

public class SpeechData {
    private int charactersWithWhiteSpaces;
    private int charactersWithoutWhiteSpaces;
    private int totalWords;
    private PunctuationMark punctuationMark;
    private List<Sentence> sentences;
    private Time time;

    public void setCharactersWithWhiteSpaces(final int charactersWithWhiteSpaces) {
        this.charactersWithWhiteSpaces = charactersWithWhiteSpaces;
    }

    public int getCharactersWithWhiteSpaces() {
        return charactersWithWhiteSpaces;
    }

    public void setCharactersWithoutWhiteSpaces(final int charactersWithoutWhiteSpaces) {
        this.charactersWithoutWhiteSpaces = charactersWithoutWhiteSpaces;
    }

    public int getCharactersWithoutWhiteSpaces() {
        return charactersWithoutWhiteSpaces;
    }

    public void setTotalWords(final int totalWords) {
        this.totalWords = totalWords;
    }

    public int getTotalWords() {
        return totalWords;
    }

    public void setPunctuationMark(final PunctuationMark punctuationMark) {
        this.punctuationMark = punctuationMark;
    }

    public PunctuationMark getPunctuationMark() {
        return punctuationMark;
    }

    public void setSentences(final List<Sentence> sentences) {
        this.sentences = sentences;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void setTime(final Time time) {
        this.time = time;
    }

    public Time getTime() {
        return time;
    }
}
