package cinema.spring.dto.request;

import cinema.spring.lib.FieldsValueMatch;
import cinema.spring.lib.ValidEmail;
import javax.validation.constraints.Size;

@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)
public class UserRequestDto {
    @ValidEmail
    private String email;
    @Size(min = 8, max = 40)
    private String password;
    private String repeatPassword;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
