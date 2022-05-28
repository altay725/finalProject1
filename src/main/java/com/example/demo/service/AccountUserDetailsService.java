package com.example.demo.service;



import com.example.demo.model.Account;

import com.example.game_proj.model.AccountUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.demo.security.Role.*;



@Service
@AllArgsConstructor
public class AccountUserDetailsService implements UserDetailsService {

    private final MainService mainService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getAccountUserDetails(username);
    }

    private AccountUserDetails getAccountUserDetails(String username) {
        Account theAccount = mainService.getAccountByUsername(username);

        AccountUserDetails accountUserDetails = new AccountUserDetails(
                theAccount.getUsername(),
                passwordEncoder.encode(theAccount.getPassword()),
                true,
                true,
                true,
                true,
                GUEST.getSimpleGrantedAuthority()
        );
        return accountUserDetails;
    }

}