package dictionaryModule;

public class UserAccount {

    private static final int MAX_FAILED_ATTEMPTS = 3;

    private final String username;
    private final String password;
    private int failedAttempts = 0;
    private boolean blocked = false;

    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }

    public boolean isBlocked() { return blocked; }

    // Devuelve true si la contraseña es correcta y la cuenta no está bloqueada.
    // Incrementa el contador de intentos fallidos y bloquea al llegar al límite.
    public boolean login(String attemptedPassword) {
        if (blocked) return false;

        if (password.equals(attemptedPassword)) {
            failedAttempts = 0;
            return true;
        }

        failedAttempts++;
        if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
            blocked = true;
        }
        return false;
    }

    public int getRemainingAttempts() {
        return Math.max(0, MAX_FAILED_ATTEMPTS - failedAttempts);
    }
}
