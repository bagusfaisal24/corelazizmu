package portal.ranting.model;

import lombok.Getter;
import lombok.Setter;
import portal.core.model.BaseModel;

import javax.persistence.*;

@Entity
@Table(name = "ranting")
@Setter
@Getter
public class RantingModel extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "KETUA_RANTING", nullable = false)
    private String ketuaRanting;
}
