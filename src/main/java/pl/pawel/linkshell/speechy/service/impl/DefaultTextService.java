package pl.pawel.linkshell.speechy.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.pawel.linkshell.speechy.model.PunctuationMark;
import pl.pawel.linkshell.speechy.model.Sentence;
import pl.pawel.linkshell.speechy.model.SpeechData;
import pl.pawel.linkshell.speechy.model.Time;
import pl.pawel.linkshell.speechy.service.ModelService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

@Service
public class DefaultTextService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultTextService.class);

    @Autowired
    private ModelService modelService;

    public void analyze(final String textareaField, final int wordsPerMinute) {

        final PunctuationMark punctuation = fillPunctuationMark(textareaField);

        final StringTokenizer tokenizer = new StringTokenizer(textareaField, ".?!");
        final List<Sentence> sentences = createSentences(tokenizer, wordsPerMinute);
        final Time speechTime = createTime(getTotalWords(sentences));
        final SpeechData speechData = createSpeechData(textareaField, punctuation, sentences, speechTime);

        modelService.save(speechData);
    }

    private Time createTime(final int totalWords) {
        double totalReadingSeconds = ((double) totalWords / (double) 250) * 60;
        int readingMinutes = (int) (totalReadingSeconds / 60);
        int readingSeconds = (int) totalReadingSeconds % 60;

        double totalSpeakingSeconds = ((double) totalWords / (double) 130) * 60;
        int speakingMinutes = (int) (totalSpeakingSeconds / 60);
        int speakingSeconds = (int) totalSpeakingSeconds % 60;

        final Time speechTime = new Time();
        speechTime.setReadingMinutes(readingMinutes);
        speechTime.setReadingSeconds(readingSeconds);
        speechTime.setSpeakingMinutes(speakingMinutes);
        speechTime.setSpeakingSeconds(speakingSeconds);
        return speechTime;
    }

    private SpeechData createSpeechData(final String textareaField, final PunctuationMark punctuation, final List<Sentence> sentences, final Time speechTime) {
        final SpeechData speechData = new SpeechData();
        speechData.setCharactersWithWhiteSpaces(textareaField.length());
        speechData.setCharactersWithoutWhiteSpaces(getCharactersWithoutWhiteSpaces(textareaField));
        speechData.setTotalWords(getTotalWords(sentences));
        speechData.setPunctuationMark(punctuation);
        speechData.setSentences(sentences);
        speechData.setTime(speechTime);
        return speechData;
    }

    private List<Sentence> createSentences(final StringTokenizer tokenizer, final int wordsPerMinute) {
        final List<Sentence> sentences = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            sentences.add(createSentence(tokenizer.nextToken(), wordsPerMinute));
        }
        return sentences;
    }

    private Sentence createSentence(final String tokenSentence, final int wordsPerMinute) {
        final List<String> words = getWords(tokenSentence);

        final Sentence sentence = new Sentence();
        sentence.setSentence(tokenSentence);
        sentence.setWords(words);
        double divider = (double) wordsPerMinute / (double) 1000L;
        sentence.setWordPerSentence((words.size() / divider / 2)); //Progress bar will have 100% best ratio in the middle of bar
        return sentence;
    }

    private List<String> getWords(final String tokenSentence) {
        final String[] words = tokenSentence.split("\\P{L}");
        final String[] digits = tokenSentence.split("\\D+");
        List<String> list = new ArrayList<>(Arrays.asList(words));
        list.addAll(Arrays.asList(digits));
        list.removeAll(Arrays.asList("", null));

        return list;
    }

    private PunctuationMark fillPunctuationMark(final String textareaField) {
        final PunctuationMark punctuation = new PunctuationMark();
        punctuation.setPeriod(StringUtils.countOccurrencesOf(textareaField, "."));
        punctuation.setQuestion(StringUtils.countOccurrencesOf(textareaField, "?"));
        punctuation.setExclamation(StringUtils.countOccurrencesOf(textareaField, "!"));

        punctuation.setComma(StringUtils.countOccurrencesOf(textareaField, ","));
        punctuation.setSemicolon(StringUtils.countOccurrencesOf(textareaField, ";"));
        punctuation.setColon(StringUtils.countOccurrencesOf(textareaField, ":"));
        punctuation.setDash(StringUtils.countOccurrencesOf(textareaField, "-"));

        punctuation.setOpenBrackets(StringUtils.countOccurrencesOf(textareaField, "["));
        punctuation.setCloseBrackets(StringUtils.countOccurrencesOf(textareaField, "]"));
        punctuation.setOpenBraces(StringUtils.countOccurrencesOf(textareaField, "{"));
        punctuation.setCloseBraces(StringUtils.countOccurrencesOf(textareaField, "}"));
        punctuation.setOpenParentheses(StringUtils.countOccurrencesOf(textareaField, "("));
        punctuation.setCloseParentheses(StringUtils.countOccurrencesOf(textareaField, ")"));

        punctuation.setApostrophe(StringUtils.countOccurrencesOf(textareaField, "'"));
        punctuation.setQuotation(StringUtils.countOccurrencesOf(textareaField, "\""));
        punctuation.setEllipsis(StringUtils.countOccurrencesOf(textareaField, "...")); //FIXME cannot count this well
        return punctuation;
    }

    private int getCharactersWithoutWhiteSpaces(final String textareaField) {
        return textareaField.replaceAll("\\s+", "").length();
    }

    private int getTotalWords(final List<Sentence> sentences) {
        int totalAmount = 0;
        for (final Sentence sentence : sentences) {
            totalAmount += sentence.getWords().size();
        }
        return totalAmount;
    }

    public SpeechData lastSpeech() {
        final Object speech = modelService.last();
        SpeechData speechData = new SpeechData();
        if (speech instanceof SpeechData) {
            speechData = (SpeechData) speech;
        }

        return speechData;
    }

}
