package pfe.jwt_spring.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;

public enum BussnissErrorCodes {
    NO_CODE(0,HttpStatus.NOT_IMPLEMENTED,"No code "),
    NEW_PASSWORD_DOES_NOT_MATCH(301,BAD_REQUEST,"The new password does not match"),
    ACCOUNT_LOCKED(302,FORBIDDEN,"User account is locked"),
    ACCOUNT_DISABLED(303,FORBIDDEN,"User account is disabled"),
BAD_CREDENTIALS(304,FORBIDDEN,"Login and / or password is incorrect ")
    ;
    @Getter
    private final int code;
@Getter
private final String description;
    @Getter
private  final  HttpStatus httpStatus;

    BussnissErrorCodes(int code,  HttpStatus httpStatus,String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}
