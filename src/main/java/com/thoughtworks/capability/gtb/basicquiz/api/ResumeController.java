package com.thoughtworks.capability.gtb.basicquiz.api;

import com.thoughtworks.capability.gtb.basicquiz.domain.Education;
import com.thoughtworks.capability.gtb.basicquiz.domain.User;
import com.thoughtworks.capability.gtb.basicquiz.service.ResumeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.naming.CommunicationException;
import java.util.List;

@RequestMapping
@RestController
@CrossOrigin
public class ResumeController {
    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping("/users/{id}")
    public User getUserInformation(@PathVariable Long id) throws CommunicationException {
        User user1 = resumeService.getUserById(id);
        return user1;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User user){
        return resumeService.addUser(user);
    }

    @GetMapping("/{userId}/educations")
    public List<Education> getEducations(@PathVariable long userId) {
        return resumeService.getEducations(userId);
    }
    @PostMapping("/{userId}/educations")
    @ResponseStatus(HttpStatus.CREATED)
    public Education addEducation(@PathVariable long userId, @RequestBody Education education){
        return resumeService.addEducation(userId, education);
    }
}
