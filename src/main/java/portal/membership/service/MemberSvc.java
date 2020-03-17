package portal.membership.service;

import portal.membership.form.MemberForm;
import portal.membership.model.MemberModel;


public interface MemberSvc {

    Iterable<MemberModel> findAll();

    MemberModel createNew(MemberForm form);

    MemberModel getDetail(Long id);

    MemberModel update(Long id, MemberForm form);
}
