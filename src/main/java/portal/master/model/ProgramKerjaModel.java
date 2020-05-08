package portal.master.model;

import lombok.Getter;
import lombok.Setter;
import portal.core.model.BaseModel;

import javax.persistence.*;

@Entity
@Table(name = "program_kerja")
@Setter
@Getter
public class ProgramKerjaModel extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
