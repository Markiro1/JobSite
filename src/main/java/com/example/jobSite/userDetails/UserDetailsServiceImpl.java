package com.example.jobSite.userDetails;

import com.example.jobSite.entity.Recruiter;
import com.example.jobSite.entity.User;
import com.example.jobSite.repository.RecruiterRepo;
import com.example.jobSite.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepository;
    private final RecruiterRepo recruiterRepo;

    @Autowired
    public UserDetailsServiceImpl(UserRepo userRepository, RecruiterRepo recruiterRepo) {
        this.userRepository = userRepository;
        this.recruiterRepo = recruiterRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Objects.requireNonNull(email, "Email must not be null");

        Optional<User> user = userRepository.findByEmail(email);
        Optional<Recruiter> recruiter = recruiterRepo.findByEmail(email);

        if (user.isPresent()) {
            return new UserDetailsImpl(user.get());
        }
        if (recruiter.isPresent()) {
            return new UserDetailsImpl(recruiter.get());
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
