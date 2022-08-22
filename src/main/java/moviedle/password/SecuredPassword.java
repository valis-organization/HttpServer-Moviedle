package moviedle.password;

public class SecuredPassword {
    private final String password;
    private final String salt;

    public SecuredPassword(String password, String salt){
        this.password = password;
        this.salt = salt;
    }

    public String getSalt() {
        return salt;
    }

    public String getPassword() {
        return password;
    }
}
