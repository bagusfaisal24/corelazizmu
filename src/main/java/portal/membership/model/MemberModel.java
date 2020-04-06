package portal.membership.model;

import portal.core.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "MEMBER")
public class MemberModel extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long id;

    @Column(name = "NAME", nullable = false)
    @Setter
    @Getter
    private String name;

    @Column(name = "DOB_PLACE")
    @Setter
    @Getter
    private String dobPlace;

    @Column(name = "BIRTH_DATE", nullable = false)
    @Setter
    @Getter
    private Timestamp birthDate;

    @Column(name = "JOB", nullable = false)
    @Setter
    @Getter
    private String job;

}
