package com.iumutdikbasan.tripSearch.business.rules;

import com.iumutdikbasan.tripSearch.common.exceptions.BusinessException;
import com.iumutdikbasan.tripSearch.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AppUserManagerRules {
    @Autowired
    private UserRepository userRepository;
    public void checkIfRoleDecent(String role) {
        Boolean isDecent = false;
        String[] roles = new String[]{"ADMIN", "USER"};
        for (String r : roles) {
            if(r.equals(role)) {
                isDecent = true;
                break;
            }
        }
        if(isDecent == false) {
            throw new BusinessException("Invalid role for user creation !");
        }
    }
    public void checkIfUsernameUnique(String username){
        if(this.userRepository.existsUserByUsername(username)){
            throw new BusinessException("This username is already taken !");
        }
    }
}
