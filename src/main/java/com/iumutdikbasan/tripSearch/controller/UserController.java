package com.iumutdikbasan.tripSearch.controller;

import com.iumutdikbasan.tripSearch.business.abstracts.UserBusiness;
import com.iumutdikbasan.tripSearch.common.results.Result;
import com.iumutdikbasan.tripSearch.dto.request.user.UserDeleteRequestDTO;
import com.iumutdikbasan.tripSearch.dto.request.user.UserSaveRequestDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@NoArgsConstructor
public class UserController {
    @Autowired
    UserBusiness userBusiness;

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    private Result add(@RequestBody UserSaveRequestDTO userSaveRequestDTO) {
        return this.userBusiness.add(userSaveRequestDTO);
    }
    @PutMapping("/delete")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    private Result delete(@Valid @RequestBody UserDeleteRequestDTO deleteUserRequestDTO){
        return this.userBusiness.delete(deleteUserRequestDTO);
    }
    @GetMapping("/getall")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    private Result getAll(){
        return this.userBusiness.getAll();
    }
}
