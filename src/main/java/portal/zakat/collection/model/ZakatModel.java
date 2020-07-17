package portal.zakat.collection.model;

import lombok.Getter;
import lombok.Setter;
import portal.core.model.BaseModel;
import portal.master.model.ProductTypeModel;
import portal.membership.model.MemberModel;
import portal.ranting.model.RantingModel;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ZAKAT_COLLECTION")
@Setter
@Getter
public class ZakatModel extends BaseModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private MemberModel member;
	
	@ManyToOne
	private ProductTypeModel productType;
	
	@Column(name = "AMOUNT", nullable = false)
	private Double amount;
	
	@Column(name = "SUBMIT_DATE", nullable = false)
	private Timestamp submitDate;
}
