package portal.siswa.model;

import portal.core.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "SISWA")
public class SiswaModel extends BaseModel {

    @Id
    @GeneratedValue
    @Setter
    @Getter
    private Long id;

    @Column(name = "NAMA", nullable = false)
    @Setter
    @Getter
    private String nama;

    @Column(name = "KELAS", nullable = false)
    @Setter
    @Getter
    private String kelas;

}
