package portal.user.model;

import lombok.Getter;
import lombok.Setter;
import portal.core.model.BaseModel;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_attemps")
@Setter
@Getter
public class UserAttemps extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "attemps", nullable = false)
    private int attemps;

    @Column(name = "last_modified")
    private Timestamp lastModified;

}
