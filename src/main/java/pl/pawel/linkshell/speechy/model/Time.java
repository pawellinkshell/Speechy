package pl.pawel.linkshell.speechy.model;

public class Time {
    private int readingMinutes;
    private int readingSeconds;
    private int speakingMinutes;
    private int speakingSeconds;

    public int getReadingMinutes() {
        return readingMinutes;
    }

    public int getReadingSeconds() {
        return readingSeconds;
    }

    public void setReadingMinutes(final int readingMinutes) {
        this.readingMinutes = readingMinutes;
    }

    public void setReadingSeconds(final int readingSeconds) {
        this.readingSeconds = readingSeconds;
    }

    public int getSpeakingMinutes() {
        return speakingMinutes;
    }

    public void setSpeakingMinutes(final int speakingMinutes) {
        this.speakingMinutes = speakingMinutes;
    }

    public int getSpeakingSeconds() {
        return speakingSeconds;
    }

    public void setSpeakingSeconds(final int speakingSeconds) {
        this.speakingSeconds = speakingSeconds;
    }
}
