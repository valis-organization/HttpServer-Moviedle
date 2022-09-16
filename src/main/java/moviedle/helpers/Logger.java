package moviedle.helpers;

public final class Logger {

    public static void registeredNewUser(String nickname){println("Registered new user: " + nickname);}

    public static void registrationFailed(){println("Failed registering new user.");}

    public static void receivedRandomMovieRequest(){println("Log: received random movie request");}

    public static void receivedAllMoviesRequest(){println("Log: received all movies request");}

    private static void println(String string){System.out.println(string);}
}
