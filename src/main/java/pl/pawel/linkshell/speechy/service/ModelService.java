package pl.pawel.linkshell.speechy.service;

import pl.pawel.linkshell.speechy.model.SpeechData;

public interface ModelService {
    void save(final SpeechData speechData);

    Object find(int id);

    Object find(String name);

    Object last();
}
