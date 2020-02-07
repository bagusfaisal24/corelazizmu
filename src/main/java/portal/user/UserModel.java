package portal.user;

import lombok.Getter;
import lombok.Setter;
import portal.core.model.BaseModel;

import javax.persistence.*;

@Entity
@Table(name = "app_user")
@Setter
@Getter
public class UserModel extends BaseModel {

    @Id
    @GeneratedValue
    @Setter
    @Getter
    private Long id;


    @Column(name = "username", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    private String email;

    @Column(name = "full_name", nullable = false)
    private String fullName;

}
