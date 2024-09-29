package entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String telegram;

    @Column(nullable = false)
    private String password;
}
