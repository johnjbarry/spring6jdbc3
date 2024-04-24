package com.pluralsight.conference.service;

import com.pluralsight.conference.model.Speaker;

import java.util.List;

public interface SpeakerService {
    List<Speaker> findAll();

    Speaker create(Speaker speaker);
    Speaker update(Speaker speaker);
    Speaker getSpeaker(int id);
    void batch();

    void deleteSpeaker(int id);
}
