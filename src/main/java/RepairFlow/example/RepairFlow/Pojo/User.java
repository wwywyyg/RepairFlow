package RepairFlow.example.RepairFlow.Pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_firstName" ,nullable = false)
    private String userFirstName;

    @Column(name = "user_LastName", nullable = false)
    private String userLastName;

    @Column(name = "user_Email",unique = true,nullable = false)
    private String userEmail;

    @Column(name = "user_password",nullable = false)
    private String userPassword;

    @Column(name = "image_url")
    private String imgUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

//    @Column(name = "Created_at",nullable = false)
//    private Timestamp createdAt;
}
