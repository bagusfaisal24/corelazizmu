package portal.membership.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portal.membership.form.MemberForm;
import portal.membership.model.MemberModel;
import portal.membership.repository.MemberRepositories;
import portal.util.TransactionDate;

import java.time.LocalDate;

@Service
public class MemberSvcImpl implements MemberSvc {

    private MemberRepositories memberRepositories;
    private TransactionDate transactionDate;

    @Autowired
    public MemberSvcImpl(MemberRepositories memberRepositories, TransactionDate transactionDate) {
        this.memberRepositories = memberRepositories;
        this.transactionDate = transactionDate;
    }

    @Override
    public Iterable<MemberModel> findAll() {
        return memberRepositories.findAll();
    }

    @Override
    public MemberModel createNew(MemberForm form) {
        MemberModel member = new MemberModel();
        member.setName(form.getName());
        member.setBirthDate(transactionDate.getStartOfDay(form.getBirthDate()));
        member.setDobPlace(form.getDobPlace());
        member.setJob(form.getJob());
        return memberRepositories.save(member);
    }

    @Override
    public MemberModel getDetail(Long id) {
        return memberRepositories.findOne(id);
    }

    @Override
    public MemberModel update(Long id, MemberForm form) {
        MemberModel member = getDetail(id);
        member.setName(form.getName());
        member.setBirthDate(transactionDate.getStartOfDay(form.getBirthDate()));
        member.setDobPlace(form.getDobPlace());
        member.setJob(form.getJob());
        return memberRepositories.save(member);
    }
}
