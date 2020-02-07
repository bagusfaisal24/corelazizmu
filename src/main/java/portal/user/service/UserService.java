package portal.user.service;

import portal.user.model.UserModel;
import portal.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("unknown user");
        }

        return user;
    }

    public UserModel getUser(Long id) {
        UserModel user = userRepository.findOne(id);

        if (user == null) {
            throw new UsernameNotFoundException("unknown user");
        }

        return user;
    }

    public String generatePassword(String password) {
        return this.bCryptPasswordEncoder.encode(password);
    }

    public UserDetails save(UserModel user) {
        return userRepository.save(user);
    }

    public void delete(String username) {
        UserModel user = (UserModel) loadUserByUsername(username);
        userRepository.hardDelete(user.getId());
    }

    public List<UserModel> findUserByRole(String roleInvestment) {
        return userRepository.findAllByUserRole(roleInvestment);
    }
}
