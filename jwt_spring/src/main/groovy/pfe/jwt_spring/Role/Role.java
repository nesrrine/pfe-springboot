package pfe.jwt_spring.Role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pfe.jwt_spring.User.User;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Role {

    @CreatedDate
    @Column(nullable = false, updatable=false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(insertable = false)
    private  LocalDateTime lastModifiedDate;
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name ;
    private  boolean enabled;

    @ManyToMany (mappedBy = "roles") // Assuming a user can have multiple roles
@JsonIgnore
    private List<User>users;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
