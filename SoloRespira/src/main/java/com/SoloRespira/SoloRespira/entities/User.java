package com.SoloRespira.SoloRespira.entities;

import com.SoloRespira.SoloRespira.entities.Image;
import com.SoloRespira.SoloRespira.enums.Role;
import jakarta.persistence.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "email_unique",
                columnNames = "email_address"
        )
)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String lastName;
    private String firstName;
    @Column(
            name = "email_address",
            nullable = false
    )
    private String email;
    private String userName;
    private String password;
    private Role role;
    
    @OneToOne
    private Image image;
}
