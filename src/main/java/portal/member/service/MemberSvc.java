package portal.member.service;

import portal.member.form.MemberForm;
import portal.member.model.MemberModel;

public interface MemberSvc {

    Iterable<MemberModel> findAll();

//    MemberModel createNew(MemberForm form);
}
