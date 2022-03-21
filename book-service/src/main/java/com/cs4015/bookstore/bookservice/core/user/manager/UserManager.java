package com.cs4015.bookstore.bookservice.core.user.manager;

import com.cs4015.bookstore.api.core.user.models.User;
import java.util.List;
import java.util.Optional;

public interface UserManager {

    Optional<User> getUserById(Long userId);
    Optional<User> saveUser(User user);

    void deleteUser(long userId);

    //Optional<List<User>> getAllUserWithPagination(int page, int offset);

}
