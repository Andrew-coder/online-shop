package online.shop.dao.exception;

/**
 * Created by andri on 1/14/2017.
 */
public class DaoException extends RuntimeException {
    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
