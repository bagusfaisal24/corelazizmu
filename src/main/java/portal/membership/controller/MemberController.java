package portal.membership.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import portal.core.exception.ValidationErrorException;
import portal.membership.form.MemberForm;
import portal.membership.model.MemberModel;
import portal.membership.service.MemberSvc;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/membership/member")
public class MemberController {
	
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	private final MemberSvc memberSvc;
	
	@Autowired
	public MemberController(MemberSvc memberSvc) {
		this.memberSvc = memberSvc;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<MemberModel> getAll() {
		return (List<MemberModel>) memberSvc.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public MemberModel createNew(@Valid @RequestBody MemberForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) throw new ValidationErrorException(bindingResult.getAllErrors());
		return memberSvc.createNew(form);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public MemberModel getDetail(@PathVariable Long id) {
		return memberSvc.getDetail(id);
	}
	
	@RequestMapping(value = "{id}/update", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public MemberModel update(@Valid @RequestBody MemberForm form,
							  @PathVariable Long id, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) throw new ValidationErrorException(bindingResult.getAllErrors());
		return memberSvc.update(id, form);
	}
}
