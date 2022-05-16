package validador.extras;

public enum OperacionTesteo {
    LOGIN,
    REGISTRO,
    EXIT;

    public static OperacionTesteo of(int operacion) {
        switch (operacion) {
            case 1:
                return LOGIN;
            case 2:
                return REGISTRO;
            case 3:
                return EXIT;
            default:
                throw new IllegalArgumentException();
        }
    }
}
