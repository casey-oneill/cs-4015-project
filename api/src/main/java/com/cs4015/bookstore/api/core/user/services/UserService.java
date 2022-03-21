package com.cs4015.bookstore.api.core.user.services;
import com.cs4015.bookstore.api.core.user.models.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
	
@Tag(name = "UserService", description = "REST API for book information.")
public interface UserService {

    /**
    * Get a user by the given userId.
    *
    * @param userId Id of the user.
    * @return the user, if found, else null.
    */
    @Operation(
        summary = "${api.user-service.get-user.description}",
        description = "${api.user-service.get-user.notes}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
        @ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
        @ApiResponse(responseCode = "404", description = "${api.responseCodes.notFound.description}"),
        @ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
    })
    @GetMapping(
        value = "/users/{userId}",
        produces = "application/json")
    User getUser(@PathVariable long userId);

    /**
    * Update a user with its userId.
    * @param user A JSON representation of the User to be updated.
    * @param userId Id of the user.
    * @return the book, if updated, else null.
    */
    @PutMapping(
        value = "/users/{userId}",
        consumes = "application/json",
        produces = "application/json"
    )
    User updateUser(@RequestBody User user, @PathVariable long userId);

    /**
    * Add a new User.
    * @param book A JSON representation of the new Book to be added.
    * @return the book, if added, else null.
    */
    @PostMapping(
        value = "/users",
        consumes = "application/json",
        produces = "application/json"
    )
    User addUser(@RequestBody User user);

    /**
    * Delete a user by the given userId.
    * @param userId id of the deleted user.
    */
    @DeleteMapping("/users/{userId}")
    void deleteUser(@PathVariable long userId);

    /**
    * @param page the page number
    * @param offset the number of the rows in one page
    * @return a list of Users.
    *
    @GetMapping(
        value = "/users",
        produces = "application/json"
    )
    List<Book> getAllUsers(@RequestParam(value="page", required = false, defaultValue = "0") int page, @RequestParam(value ="offset", required = false, defaultValue = "10") int offset);
        
    }
    **/
}
