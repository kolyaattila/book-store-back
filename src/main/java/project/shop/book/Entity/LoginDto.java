package project.shop.book.Entity;

import javax.validation.constraints.NotNull;

public class LoginDto {

    @NotNull
    private String email;

    @NotNull
    private String password;


    public LoginDto(@NotNull String email, @NotNull String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
