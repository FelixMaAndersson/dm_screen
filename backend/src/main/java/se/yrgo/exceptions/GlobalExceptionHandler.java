package se.yrgo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.yrgo.dto.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import se.yrgo.exceptions.campaign.CampaignNotFoundException;
import se.yrgo.exceptions.playerCharacter.CharacterNotFoundException;
import se.yrgo.exceptions.user.UserAlreadyExistsException;
import se.yrgo.exceptions.user.UserNotFoundException;
import se.yrgo.exceptions.monster.MonsterAlreadyExistsException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFound(UserNotFoundException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(CampaignNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCampaignNotFound(CampaignNotFoundException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(CharacterNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCharacterNotFound(CharacterNotFoundException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExists(
            UserAlreadyExistsException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(MonsterAlreadyExistsException.class)
    public ResponseEntity<String> handleMonsterAlreadyExists(
            MonsterAlreadyExistsException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }
}
