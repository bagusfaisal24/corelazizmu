package portal.membership.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portal.core.exception.DataNotFoundException;
import portal.membership.form.MemberForm;
import portal.membership.model.MemberModel;
import portal.membership.repository.MemberRepositories;
import portal.ranting.model.RantingModel;
import portal.ranting.service.RantingSvc;
import portal.util.TransactionDate;


@Service
public class MemberSvcImpl implements MemberSvc {
	
	private MemberRepositories memberRepositories;
	private RantingSvc rantingSvc;
	private TransactionDate transactionDate;
	
	@Autowired
	public MemberSvcImpl(MemberRepositories memberRepositories, RantingSvc rantingSvc,
						 TransactionDate transactionDate) {
		this.memberRepositories = memberRepositories;
		this.rantingSvc = rantingSvc;
		this.transactionDate = transactionDate;
	}
	
	@Override
	public Iterable<MemberModel> findAll() {
		return memberRepositories.getAll();
	}
	
	@Override
	public MemberModel createNew(MemberForm form) {
		RantingModel ranting = rantingSvc.findOne(form.getRantingId());
		if (ranting == null) throw new DataNotFoundException("data ranting tidak ada");
		
		MemberModel member = new MemberModel();
		return setMember(form, ranting, member);
	}
	
	private MemberModel setMember(MemberForm form, RantingModel ranting, MemberModel member) {
		member.setName(form.getName());
		member.setBirthDate(transactionDate.getStartOfDay(form.getBirthDate()));
		member.setDobPlace(form.getDobPlace());
		member.setJob(form.getJob());
		member.setNbm(form.getNbm());
		member.setRanting(ranting);
		return memberRepositories.save(member);
	}
	
	@Override
	public MemberModel getDetail(Long id) {
		return memberRepositories.findOne(id);
	}
	
	@Override
	public MemberModel update(Long id, MemberForm form) {
		RantingModel ranting = rantingSvc.findOne(form.getRantingId());
		if (ranting == null) throw new DataNotFoundException("data ranting tidak ada");
		MemberModel member = getDetail(id);
		return setMember(form, ranting, member);
	}
	
	@Override
	public void delete(Long id) {
		MemberModel member = getDetail(id);
		if (member == null) throw new DataNotFoundException("member tidak ditemukan");
		member.setDeletedAt(transactionDate.now());
		memberRepositories.save(member);
	}
}
