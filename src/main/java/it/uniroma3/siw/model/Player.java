package it.uniroma3.siw.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private String role;
    private LocalDate startDateOfRegistration;
    private LocalDate endDateOfRegistration;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getStartDateOfRegistration() {
        return startDateOfRegistration;
    }

    public void setStartDateOfRegistration(LocalDate startDateOfRegistration) {
        this.startDateOfRegistration = startDateOfRegistration;
    }

    public LocalDate getEndDateOfRegistration() {
        return endDateOfRegistration;
    }

    public void setEndDateOfRegistration(LocalDate endDateOfRegistration) {
        this.endDateOfRegistration = endDateOfRegistration;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}

