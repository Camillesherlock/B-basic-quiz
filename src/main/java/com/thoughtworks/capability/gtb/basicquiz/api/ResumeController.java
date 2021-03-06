package com.thoughtworks.capability.gtb.basicquiz.api;

import com.thoughtworks.capability.gtb.basicquiz.domain.Education;
import com.thoughtworks.capability.gtb.basicquiz.domain.User;
import com.thoughtworks.capability.gtb.basicquiz.service.ResumeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.naming.CommunicationException;
import javax.validation.Valid;
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
        // GTB: - 不需要 user1 这个变量，直接 inline 掉
        User user1 = resumeService.getUserById(id);
        return user1;
    }
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody @Valid User user){
        return resumeService.addUser(user);
    }

    @GetMapping("/users/{userId}/educations")
    public List<Education> getEducations(@PathVariable long userId) throws CommunicationException {
        return resumeService.getEducations(userId);
    }
    @PostMapping("/users/{userId}/educations")
    @ResponseStatus(HttpStatus.CREATED)
    public Education addEducation(@PathVariable long userId, @RequestBody @Valid Education education){
        return resumeService.addEducation(userId, education);
    }
}
