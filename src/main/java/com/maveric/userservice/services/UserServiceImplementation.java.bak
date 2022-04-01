package com.maveric.userservice.services;

import com.maveric.userservice.dto.Userdto;
import com.maveric.userservice.dto.UserResponse;
import com.maveric.userservice.enums.Gender;
import com.maveric.userservice.exception.GenderNotValid;
import com.maveric.userservice.exception.UserDetailsNotPresent;
import com.maveric.userservice.exception.UserEmailAlreadyExist;
import com.maveric.userservice.exception.UserNotExistsException;
import com.maveric.userservice.model.User;
import com.maveric.userservice.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    private static Logger log = LoggerFactory.getLogger(UserServiceImplementation.class);

    @Autowired
    UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
@LoadBalanced

    @Override
    public UserResponse updateUser(String userId, Userdto updateUser) {

        if(userRepo.findByuserId(userId)==null){
            log.info("Can't be updated ");
            throw new UserNotExistsException("User cannot be updated, user not Present");
        }
        log.info("user updating in repo");
        return setUserResponse(userRepo.save(new User(userId,updateUser.getFirstName(),updateUser.getMiddleName(),updateUser.getLastName(),updateUser.getPhoneNumber(),
                updateUser.getDateOfBirth(),updateUser.getGender(),updateUser.getAddress(),updateUser.getEmployeeNumber(),
                updateUser.getBloodGroup(),updateUser.getEmail(),bCryptPasswordEncoder.encode(updateUser.getPassword()))));

    }
	
	@Override
    public String deleteUser(String userId) {
        if(userRepo.findByuserId(userId)==null){
            log.info("user not found ,can't be deleted");
            throw new UserNotExistsException("User cannot be deleted, user not Present");
        }
         userRepo.deleteById(userId);
        log.info("user deleted successfully");
         return "User successfully deleted";
    }


    

    

    public UserResponse setUserResponse(User user){
        return new UserResponse(user.getUserId(),user.getFirstName(),user.getMiddleName(),
                user.getLastName(),user.getPhoneNumber(),user.getDateOfBirth(),user.getGender(),
                user.getAddress(),user.getEmployeeNumber(),user.getBloodGroup(),user.getEmail());
    }

    public User setUser(Userdto userdto){
    return new User(userdto.getFirstName(),userdto.getMiddleName(),userdto.getLastName(),userdto.getPhoneNumber(),
            userdto.getDateOfBirth(),userdto.getGender(),userdto.getAddress(),userdto.getEmployeeNumber(),
            userdto.getBloodGroup(),userdto.getEmail(),bCryptPasswordEncoder.encode(userdto.getPassword()));
    }

}
