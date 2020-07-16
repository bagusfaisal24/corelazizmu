package portal.zakat.collection.service;

import portal.zakat.collection.form.ZakatForm;
import portal.zakat.collection.model.ZakatModel;

public interface ZakatSvc {
	
	Iterable<ZakatModel> getAll();
	
	ZakatModel createNew(ZakatForm form);
	
	ZakatModel getDetail(Long id);
}
