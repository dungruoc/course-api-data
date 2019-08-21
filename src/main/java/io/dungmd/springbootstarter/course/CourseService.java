package io.dungmd.springbootstarter.course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

    public List<Course> getAllCourses(String topicId) {
    	List<Course> courses = new ArrayList<Course>();
    	courseRepository.findByTopicId(topicId).forEach(courses::add);
    	return courses;
    }

    public Course getCourse(String id) {
        return courseRepository.findById(id).get();
    }

    public void addCourse(Course course) {
    	if (courseRepository.findById(course.getId()).isPresent()) {
    		throw new RuntimeException("ID already exists: " + course.getId());
    	}
        courseRepository.save(course);
    }

    public void updateCourse(String id, Course course) {
    	if (!course.getId().equals(id)) {
    		throw new RuntimeException("ID inconsistent: " + id);
    	}
    	if (!courseRepository.findById(id).isPresent()) {
    		throw new RuntimeException("ID does not exists: "  + id);
    	}
        courseRepository.save(course);
    }

    public void deleteCourse(String id) {
    	if (!courseRepository.findById(id).isPresent()) {
    		throw new RuntimeException("ID does not exists: " + id);
    	}
    	courseRepository.deleteById(id);
    }
}
