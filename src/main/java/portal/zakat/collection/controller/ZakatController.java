package portal.zakat.collection.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import portal.core.exception.ValidationErrorException;
import portal.zakat.collection.form.ZakatForm;
import portal.zakat.collection.model.ZakatModel;
import portal.zakat.collection.service.ZakatSvc;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/zakat")
public class ZakatController {
	
	private static final Logger log = LoggerFactory.getLogger(ZakatController.class);
	
	@Autowired
	private ZakatSvc zakatSvc;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ZakatModel> getAll() {
		return (List<ZakatModel>) zakatSvc.getAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public ZakatModel createNew(@Valid @RequestBody ZakatForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) throw new ValidationErrorException(bindingResult.getAllErrors());
		return zakatSvc.createNew(form);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public ZakatModel getDetail(@PathVariable Long id) {
		return zakatSvc.getDetail(id);
	}
}
