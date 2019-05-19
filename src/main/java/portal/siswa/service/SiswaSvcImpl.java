package portal.siswa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portal.siswa.model.SiswaModel;
import portal.siswa.repository.SiswaRepositories;

@Service
public class SiswaSvcImpl implements SiswaSvc {

    private SiswaRepositories siswaRepo;

    @Autowired
    public void setSiswaRepo(SiswaRepositories siswaRepo) {
        this.siswaRepo = siswaRepo;
    }

    @Override
    public Iterable<SiswaModel> findAll() {
        return siswaRepo.findAll();
    }
}
