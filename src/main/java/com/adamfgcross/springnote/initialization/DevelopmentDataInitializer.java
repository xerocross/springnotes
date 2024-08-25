package com.adamfgcross.springnote.initialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.adamfgcross.springnote.data.UserRepository;
import com.adamfgcross.springnote.entities.User;

@Component
@Profile("dev")
public class DevelopmentDataInitializer implements CommandLineRunner {

	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DevelopmentDataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

	
	@Override
	public void run(String... args) throws Exception {
		User devuser = new User();
		devuser.setUsername("adam");
		devuser.setEnabled(true);
		devuser.setPassword(passwordEncoder.encode("guest"));
		userRepository.save(devuser);
	}

}
