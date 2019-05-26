package pl.pawel.linkshell.speechy.service.impl;

import org.springframework.stereotype.Service;
import pl.pawel.linkshell.speechy.model.SpeechData;
import pl.pawel.linkshell.speechy.service.ModelService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultModelService implements ModelService {

    private List<SpeechData> speechDataList;

    public DefaultModelService() {
        this.speechDataList = new ArrayList<>();
    }

    @Override
    public void save(final SpeechData speechData) {
        speechDataList.add(speechData);
    }

    @Override
    public Object find(final int id) {
        SpeechData speechData = new SpeechData();
        if (!speechDataList.isEmpty()) {
            speechData = speechDataList.get(id);
        }
        return speechData;
    }

    @Override
    public Object find(final String name) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Object last() {
        SpeechData speechData = new SpeechData();
        if (!speechDataList.isEmpty()) {
            speechData = speechDataList.get(speechDataList.size() - 1);
        }
        return speechData;
    }
}
