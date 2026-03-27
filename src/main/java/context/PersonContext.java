package context;

public class PersonContext {
    private final ThreadLocal<String> randomFirstname = ThreadLocal.withInitial(() -> null);
    private final ThreadLocal<String> randomLastname = ThreadLocal.withInitial(() -> null);
    private final ThreadLocal<String> randomEmail = ThreadLocal.withInitial(() -> null);

    public String getRandomFirstname() {
        return randomFirstname.get();
    }

    public String getRandomLastname() {
        return randomLastname.get();
    }

    public String getRandomEmail() {
        return randomEmail.get();
    }

    public void setRandomFirstname(String firstname) {
        randomFirstname.set(firstname);
    }

    public void setRandomLastname(String lastname) {
        randomLastname.set(lastname);
    }

    public void setRandomEmail(String email) {
        randomEmail.set(email);
    }

}
