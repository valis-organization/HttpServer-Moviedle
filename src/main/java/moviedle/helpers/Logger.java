package moviedle.helpers;

public final class Logger {

    public static void registeredNewUser(String nickname){println("Registered new user: " + nickname);}

    public static void registrationFailed(){println("Failed registering new user.");}

    private static void println(String string){System.out.println(string);}
}
