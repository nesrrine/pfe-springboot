package pfe.jwt_spring.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pfe.jwt_spring.Role.Role;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EntityListeners(AuditingEntityListener.class)
public class User  implements UserDetails , Principal {


    @Id
    @GeneratedValue
    private Long id;
         private String username;
    private String firstName;
    private String lastName;
    private String token;
@Column(unique = true)
@Getter

    private String email;
    private String password ;
    private  boolean accountLocked;
    @Column(name = "verification_code", length = 64)
    private String verificationCode;

    private  boolean enabled;
    @ManyToMany(fetch = FetchType.EAGER) // Assuming a user can have multiple roles
    private List<Role> roles;


    @CreatedDate
@Column(nullable = false, updatable=false)
private LocalDateTime createdDate;
@LastModifiedDate
@Column(insertable = false)
private  LocalDateTime lastModifiedDate;
    @Override
    public String getName() {
        return email;
    }
    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream()
                .map( r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());

    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    public   String  fullName(){
        return firstName +" "+lastName;
    }
}

