package Problemset;

class DoesntExistsException extends UnsupportedOperationException {
    public DoesntExistsException() {}

    public DoesntExistsException(String m) {
        super(m);
    }
}
