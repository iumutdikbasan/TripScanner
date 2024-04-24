package com.iumutdikbasan.tripSearch.business.concretes;

import com.iumutdikbasan.tripSearch.business.abstracts.UserBusiness;
import com.iumutdikbasan.tripSearch.business.rules.AppUserManagerRules;
import com.iumutdikbasan.tripSearch.common.exceptions.BusinessException;
import com.iumutdikbasan.tripSearch.common.results.*;
import com.iumutdikbasan.tripSearch.dto.request.user.UserDeleteRequestDTO;
import com.iumutdikbasan.tripSearch.dto.request.user.UserSaveRequestDTO;
import com.iumutdikbasan.tripSearch.dto.response.UsersResponseDTO;
import com.iumutdikbasan.tripSearch.mapper.ModelMapperBusiness;
import com.iumutdikbasan.tripSearch.model.concretes.User;
import com.iumutdikbasan.tripSearch.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AppUserManager implements UserBusiness {
    @Autowired
    private AppUserManagerRules appUserManagerRules;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapperBusiness modelMapperBusiness;

    @Override
    public DataResult<List<UsersResponseDTO>> getAll() {
        List<User> appUsers = this.userRepository.findAll();
        List<UsersResponseDTO> getAllUsersResponses = new ArrayList<>();
        for(User u : appUsers) {
            getAllUsersResponses.add(this.modelMapperBusiness.forResponse().map(u, UsersResponseDTO.class));
        };
        return new SuccessDataResult(getAllUsersResponses, ResultMessage.SUCCESS.toString());
    }

    @Override
    public Result add(UserSaveRequestDTO userSaveRequestDTO) {
        this.appUserManagerRules.checkIfUsernameUnique(userSaveRequestDTO.getUsername());
        this.appUserManagerRules.checkIfRoleDecent(userSaveRequestDTO.getRole().toUpperCase());
        userSaveRequestDTO.setPassword(this.passwordEncoder.encode(userSaveRequestDTO.getPassword()));
        String incomingRole = userSaveRequestDTO.getRole().toUpperCase();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ROLE_"+incomingRole);
        userSaveRequestDTO.setRole(stringBuilder.toString());

        User user = this.modelMapperBusiness.forRequest().map(userSaveRequestDTO, User.class);
        user.setId(UUID.randomUUID().toString());
        user.setActive(true);
        this.userRepository.save(user);
        return new SuccessResult();
    }

    @Override
    public Result delete(UserDeleteRequestDTO userDeleteRequestDTO) {
        User user  = this.userRepository.findById(userDeleteRequestDTO.getId()).orElseThrow(() ->
                new BusinessException("The user does not exist."));
        user.setActive(false);
        this.userRepository.save(user);
        return new SuccessResult();
    }
}
