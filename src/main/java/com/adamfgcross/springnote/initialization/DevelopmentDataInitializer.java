package com.adamfgcross.springnote.initialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.adamfgcross.springnote.domain.ERole;
import com.adamfgcross.springnote.domain.Role;
import com.adamfgcross.springnote.domain.User;
import com.adamfgcross.springnote.repository.RoleRepository;
import com.adamfgcross.springnote.repository.UserRepository;

@Component
@Profile("dev")
public class DevelopmentDataInitializer implements CommandLineRunner {

	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public DevelopmentDataInitializer(UserRepository userRepository, 
    		PasswordEncoder passwordEncoder,
    		RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

	
	@Override
	public void run(String... args) throws Exception {
		
		Role user_role = new Role();
		user_role.setName(ERole.ROLE_USER);
		roleRepository.save(user_role);
		
		User devuser = new User();
		devuser.setUsername("adam");
		devuser.setEnabled(true);
		devuser.setPassword(passwordEncoder.encode("guest"));
		devuser.getRoles().add(user_role);
		userRepository.save(devuser);
	}

}
