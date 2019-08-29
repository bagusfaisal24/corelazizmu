package portal.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portal.member.form.MemberForm;
import portal.member.model.MemberModel;
import portal.member.repository.MemberRepositories;
import portal.util.TransactionDate;

@Service
public class MemberSvcImpl implements MemberSvc {

    private MemberRepositories memberRepositories;
    private TransactionDate transactionDate;

    @Autowired
    public void setSiswaRepo(MemberRepositories memberRepositories, TransactionDate transactionDate) {
        this.memberRepositories = memberRepositories;
        this.transactionDate = transactionDate;
    }

    @Override
    public Iterable<MemberModel> findAll() {
        return memberRepositories.findAll();
    }

//    @Override
//    public MemberModel createNew(MemberForm form) {
//        MemberModel member = new MemberModel();
//        member.setName(form.getName());
//        member.setBirthDate(transactionDate.getStartOfDay(form.getBirthDate()));
//        member.setDobPlace(form.getDobPlace());
//        member.setJob(form.getJob());
//        return memberRepositories.save(member);
//    }
}
