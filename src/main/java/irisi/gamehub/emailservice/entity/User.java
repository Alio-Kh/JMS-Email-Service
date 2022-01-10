package irisi.gamehub.emailservice.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    Long id;
    String username;
    String firstname;
    String lastname;

}
