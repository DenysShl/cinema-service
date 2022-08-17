package cinema.lib;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cinema.dto.request.UserRequestDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class FieldsValueMatchValidatorTest {
    private static FieldsValueMatchValidator passwordValidator;
    private static FieldsValueMatch constraintAnnotation;
    private static UserRequestDto userRequestDto;
    private static String email = "denys@gmail.com";
    private static String password = "Qaz2@34dff";
    private String passwordFail = "120";

    @BeforeAll
    static void beforeAll() {
        passwordValidator = new FieldsValueMatchValidator();
        userRequestDto = getRegistrationDto(email, password, password);
        constraintAnnotation = Mockito.mock(FieldsValueMatch.class);
    }

    @Test
    void isValid_Password_Ok() {
        Mockito.when(constraintAnnotation.field()).thenReturn("password");
        Mockito.when(constraintAnnotation.fieldMatch()).thenReturn("repeatPassword");
        passwordValidator.initialize(constraintAnnotation);
        assertTrue(passwordValidator.isValid(userRequestDto, null));
    }

    @Test
    void isValid_notPresentPassword_notOk() {
        Mockito.when(constraintAnnotation.field()).thenReturn("password");
        Mockito.when(constraintAnnotation.fieldMatch()).thenReturn("repeatPassword");
        passwordValidator.initialize(constraintAnnotation);
        userRequestDto.setRepeatPassword(passwordFail);
        assertFalse(passwordValidator.isValid(userRequestDto, null));
    }

    private static UserRequestDto getRegistrationDto(String email,
                                                          String password,
                                                          String repeatpassword) {
        UserRequestDto registrationDto = new UserRequestDto();
        registrationDto.setEmail(email);
        registrationDto.setPassword(password);
        registrationDto.setRepeatPassword(password);
        return registrationDto;
    }
}
