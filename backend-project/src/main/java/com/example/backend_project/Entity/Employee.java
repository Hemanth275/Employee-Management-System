package com.example.backend_project.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Primary;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email" ,nullable = false,unique = true)
    private String email;





}
