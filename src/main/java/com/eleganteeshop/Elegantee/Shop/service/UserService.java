package com.eleganteeshop.Elegantee.Shop.service;

import com.eleganteeshop.Elegantee.Shop.Dto.AccountDetailDTO;
import com.eleganteeshop.Elegantee.Shop.Dto.RegistrationBaseDTO;
import com.eleganteeshop.Elegantee.Shop.entities.AccountDetails;
import com.eleganteeshop.Elegantee.Shop.entities.UserEntity;
import com.eleganteeshop.Elegantee.Shop.exceptions.UserException.RuntimeError.UserAlreadyExist;
import com.eleganteeshop.Elegantee.Shop.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> byUsername = userRepository.findByUsername(username);

        if(byUsername.isPresent()){
            var userObj = byUsername.get();
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(getUserRoles(userObj.getRoles()))
                    .build();
        }else
            throw new UsernameNotFoundException(username+" Not Found!");
    }

    private String[] getUserRoles(String roles){
        return roles.split(",");
    }

    public UserEntity createNewUser(RegistrationBaseDTO user){
        String username = user.getUser().getUsername();

        if(userRepository.findByUsername(username).isEmpty()){
            String roles = "USERS";
            AccountDetailDTO accountDetailDTO = user.getAccountDetails();
            AccountDetails accountDetails = new AccountDetails();
            accountDetails.setUsername(username);
            accountDetails.setFirstName(accountDetailDTO.getFirstname());
            accountDetails.setLastName(accountDetailDTO.getFirstname());
            accountDetails.setEmailAddress(accountDetailDTO.getEmailAddress());
            accountDetails.setBirthDate(accountDetailDTO.getBirthDate());
            UserEntity newUser = new UserEntity(username,user.getUser().getPassword(),roles,accountDetails);

            return userRepository.save(newUser);
        }else
            throw new UserAlreadyExist(username+" Already Exist");
    }

    public Optional<UserEntity> findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public List<UserEntity> allUsers(){
        return userRepository.findAll();
    }
}
