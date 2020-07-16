package portal.zakat.collection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portal.core.exception.DataNotFoundException;
import portal.master.model.ProductTypeModel;
import portal.master.service.ProductTypeSvc;
import portal.membership.model.MemberModel;
import portal.membership.service.MemberSvc;
import portal.util.TransactionDate;
import portal.zakat.collection.form.ZakatForm;
import portal.zakat.collection.model.ZakatModel;
import portal.zakat.collection.repository.ZakatRepositories;

@Service
public class ZakatSvcImp implements ZakatSvc {
	
	private final MemberSvc memberSvc;
	private final ProductTypeSvc productTypeSvc;
	private final TransactionDate transactionDate;
	private final ZakatRepositories zakatRepositories;
	
	@Autowired
	public ZakatSvcImp(MemberSvc memberSvc, ProductTypeSvc productTypeSvc, TransactionDate transactionDate, ZakatRepositories zakatRepositories) {
		this.memberSvc = memberSvc;
		this.productTypeSvc = productTypeSvc;
		this.transactionDate = transactionDate;
		this.zakatRepositories = zakatRepositories;
	}
	
	@Override
	public Iterable<ZakatModel> getAll() {
		return zakatRepositories.findAll();
	}
	
	@Override
	public ZakatModel createNew(ZakatForm form) {
		MemberModel member = memberSvc.getDetail(form.getMemberId());
		if (member == null) throw new DataNotFoundException("member tidak ditemukan");
		ProductTypeModel productType = productTypeSvc.findOne(form.getProductTypeId());
		if (productType == null) throw new DataNotFoundException("product zakat tidak ditemukan");
		ZakatModel zakat = new ZakatModel();
		zakat.setMember(member);
		zakat.setProductType(productType);
		zakat.setSubmitDate(transactionDate.getStartOfDay(form.getSubmitDate()));
		zakat.setAmount(form.getAmount());
		zakat.setKeterangan(form.getKeterangan());
		return save(zakat);
	}
	
	@Override
	public ZakatModel getDetail(Long id) {
		return zakatRepositories.findOne(id);
	}
	
	public ZakatModel save(ZakatModel zakat) {
		return zakatRepositories.save(zakat);
	}
}
