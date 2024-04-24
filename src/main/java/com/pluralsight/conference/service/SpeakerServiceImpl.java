package com.pluralsight.conference.service;

import com.pluralsight.conference.model.Speaker;
import com.pluralsight.conference.repository.SpeakerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("speakerService")
public class SpeakerServiceImpl implements SpeakerService {

    private final SpeakerRepository speakerRepository;

    public SpeakerServiceImpl(SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;
    }

    @Override
    public List<Speaker> findAll() {
        return speakerRepository.findAll();
    }

    @Override
    public Speaker create(Speaker speaker)
    {
        return speakerRepository.create(speaker);
    }

    @Override
    public Speaker update(Speaker speaker)
    {
        return speakerRepository.update(speaker);
    }

    @Override
    public Speaker getSpeaker(int id)
    {
        return speakerRepository.getSpeaker(id);
    }

    @Override
    @Transactional
    public void batch()
    {
        List<Speaker> speakers = speakerRepository.findAll();
        List<Object[]> pairs = new ArrayList<>();

        for (Speaker speaker : speakers)
        {
            pairs.add(new Object[] {"Java", speaker.getId()});
        }

        speakerRepository.update(pairs);

//        throw new DataAccessException("Error in Batch") {};
    }

    @Override
    public void deleteSpeaker(int id)
    {
        speakerRepository.delete(id);
    }
}
