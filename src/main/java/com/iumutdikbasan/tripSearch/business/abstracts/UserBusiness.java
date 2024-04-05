package com.iumutdikbasan.tripSearch.business.abstracts;

import com.iumutdikbasan.tripSearch.common.results.DataResult;
import com.iumutdikbasan.tripSearch.common.results.Result;
import com.iumutdikbasan.tripSearch.dto.request.user.UserDeleteRequestDTO;
import com.iumutdikbasan.tripSearch.dto.request.user.UserSaveRequestDTO;
import com.iumutdikbasan.tripSearch.dto.response.UsersResponseDTO;

import java.util.List;

public interface UserBusiness {
    DataResult<List<UsersResponseDTO>> getAll();
    Result add(UserSaveRequestDTO userSaveRequestDTO);
    Result delete(UserDeleteRequestDTO userDeleteRequestDTO);
}
