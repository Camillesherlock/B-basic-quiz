package com.thoughtworks.capability.gtb.basicquiz.repository;

import com.thoughtworks.capability.gtb.basicquiz.domain.Education;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends CrudRepository<Education,Long> {

    List<Education> findEducationsByUserId(long userId);
}
