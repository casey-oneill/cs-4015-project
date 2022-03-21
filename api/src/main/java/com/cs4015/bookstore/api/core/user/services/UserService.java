package com.cs4015.bookstore.api.core.user.services;
import com.cs4015.bookstore.api.core.book.models.Book;
import com.cs4015.bookstore.api.core.book.models.UserBooks;
import com.cs4015.bookstore.api.core.user.models.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
	
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
    @Operation(
            summary = "Update the user profile by the given user id.",
            description = "Return a updated user profile if the user is Updated. Otherwise an error will return.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
            @ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
            @ApiResponse(responseCode = "404", description = "${api.responseCodes.notFound.description}"),
            @ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
    })
    @PutMapping(
        value = "/users/{userId}",
        consumes = "application/json",
        produces = "application/json"
    )
    User updateUser(@RequestBody User user, @PathVariable long userId);

    /**
    * Add a new User.
    * @param user A JSON representation of the new user to be added.
    * @return the user, if added, else null.
    */

    @Operation(
            summary = "Create a user profile and save into database.",
            description = "Create a user profile based on the user inforamtion that provide in the request body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
            @ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
            @ApiResponse(responseCode = "404", description = "${api.responseCodes.notFound.description}"),
            @ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
    })
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
    @Operation(
            summary = "Delete a user profile by the given user id.",
            description = "Delete a user from the database by the given user id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
            @ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
            @ApiResponse(responseCode = "404", description = "${api.responseCodes.notFound.description}"),
            @ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
    })
    void deleteUser(@PathVariable long userId);

    /**
     * Get a List of books that users list for sell.
     * @param user_id the user id.
     * @return List user-book list that contains all the books that the user for sell.
     */
    @Operation(
            summary = "Get all the books that the given user is on sell.",
            description = "Return a user-book list that contains all the books that the user are owned or sell.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
            @ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
            @ApiResponse(responseCode = "404", description = "${api.responseCodes.notFound.description}"),
            @ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
    })
    @GetMapping(value="/users/{user_id}/books", produces="application/json")
    public UserBooks getUserBooks(@PathVariable ("user_id") long user_id);

    /**
     * Add a book into a user.
     * @param user_id the id of the user.
     * @param book A book that user is for sell.
     * @return A list of book that the give user is for sell.
     */
    @Operation(
            summary = "Add a book to the given user id.",
            description = "Add a book to the giver user id. If the book is successfully add, a list of user books will be returned.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
            @ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
            @ApiResponse(responseCode = "404", description = "${api.responseCodes.notFound.description}"),
            @ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
    })
    @PostMapping(value="/users/{user_id}/books", produces="application/json")
    public UserBooks addBookToUser(@PathVariable ("user_id") long user_id, @RequestBody Book book);

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
