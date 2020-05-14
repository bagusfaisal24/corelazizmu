package portal.master.model;

import lombok.Getter;
import lombok.Setter;
import portal.core.model.BaseModel;

import javax.persistence.*;

@Entity
@Table(name = "product_type")
@Setter
@Getter
public class ProductTypeModel extends BaseModel {

    public static final String ZAKAT_UANG = "uang";
    public static final String ZAKAT_PERTANIAN = "pertanian";
    public static final String ZAKAT_PENDAPATAN = "pendapatan";
    public static final String ZAKAT_PERNIAGAAN = "perniagaan";
    public static final String ZAKAT_EMAS = "emas";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    private String code;
}
