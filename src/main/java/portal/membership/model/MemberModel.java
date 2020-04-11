package portal.membership.model;

import portal.core.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import portal.ranting.model.RantingModel;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "MEMBER")
@Setter
@Getter
public class MemberModel extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private RantingModel ranting;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DOB_PLACE")
    private String dobPlace;

    @Column(name = "BIRTH_DATE", nullable = false)
    private Timestamp birthDate;

    @Column(name = "JOB", nullable = false)
    private String job;

    @Column(name = "NBM")
    private String nbm;
}
