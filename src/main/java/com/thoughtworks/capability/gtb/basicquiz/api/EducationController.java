package com.thoughtworks.capability.gtb.basicquiz.api;

import com.thoughtworks.capability.gtb.basicquiz.dto.EducationDTO;
import com.thoughtworks.capability.gtb.basicquiz.service.EducationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.naming.CommunicationException;
import javax.validation.Valid;
import java.util.List;

@RestController
public class EducationController {
    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping("/users/{id}/educations")
    @ResponseStatus(HttpStatus.OK)
    public List<EducationDTO> getEducationByUserId(@PathVariable Long id) throws CommunicationException {
        return educationService.getEducationByUserId(id);
    }

    @PostMapping("/users/{id}/educations")
    @ResponseStatus(HttpStatus.CREATED)
    public EducationDTO createEducation(@RequestBody @Valid EducationDTO educationDto,
                                        @PathVariable Long id) throws CommunicationException {
        return educationService.saveEducation(id, educationDto);
    }
}
