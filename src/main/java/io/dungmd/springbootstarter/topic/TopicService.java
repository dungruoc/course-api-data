package io.dungmd.springbootstarter.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;

    public List<Topic> getAllTopics() {
    	List<Topic> topics = new ArrayList<Topic>();
    	topicRepository.findAll().forEach(topics::add);
    	return topics;
    }

    public Topic getTopic(String id) {
        return topicRepository.findById(id).get();
    }

    public void addTopic(Topic topic) {
    	if (topicRepository.findById(topic.getId()).isPresent()) {
    		throw new RuntimeException("ID already exists: " + topic.getId());
    	}
        topicRepository.save(topic);
    }

    public void updateTopic(String id, Topic topic) {
    	if (!topic.getId().equals(id)) {
    		throw new RuntimeException("ID inconsistent: " + id);
    	}
    	if (!topicRepository.findById(id).isPresent()) {
    		throw new RuntimeException("ID does not exists: "  + id);
    	}
        topicRepository.save(topic);
    }

    public void deleteTopic(String id) {
    	if (!topicRepository.findById(id).isPresent()) {
    		throw new RuntimeException("ID does not exists: " + id);
    	}
    	topicRepository.deleteById(id);
    }

}
