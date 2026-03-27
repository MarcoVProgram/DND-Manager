package manager.exceptions;

public class IncorrectInputException extends Exception {

    private static final long serialVersionUID = 471174238787598357L;
    private String inputMistake;

    public IncorrectInputException(String inputMistake) {
        super("Your input (" +  inputMistake + ") is incorrect.");
        this.inputMistake = inputMistake;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IncorrectInputException:\n");
        sb.append("Input Mistake: ").append(inputMistake);
        return sb.toString();
    }
}
