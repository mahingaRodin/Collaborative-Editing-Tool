package com.cet.collaborative_editing_tool.service.auth;

import com.cet.collaborative_editing_tool.dto.RegisterRequest;
import com.cet.collaborative_editing_tool.dto.UserDto;

public interface AuthService {
    UserDto createUser(RegisterRequest request);
}
