package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//URL pattern --> Spring Data rest framework builds  --> standard resource based URL around Spring data repository
//                http(s)://server:port/basePath/resource
//                Ex: https://localhost:8080/api/speakers
//                                               plural of resource_name first letter in lower case

//In case we want to change the name of the resource from entity name:
//@RepositoryRestResource(path = "authors")
public interface SpeakerJpaRepository extends JpaRepository<Speaker, Long> {


    //these particular methods are custom for there repository  and are based on spring Data JPA standards
    //If we start up application, we can match REST endpoints with these methods
    List<Speaker> findByFirstNameAndLastName(String firstName, String lastName);
    List<Speaker> findByFirstNameOrLastName(String firstName, String lastName);
    List<Speaker> findBySpeakerPhotoNull();
    List<Speaker> findByCompanyIn(List<String> companies);
    List<Speaker> findByCompanyIgnoreCase(String company);
    List<Speaker> findByLastNameOrderByFirstNameAsc(String lastName);

    Speaker findFirstByFirstName(String firstName);
}
