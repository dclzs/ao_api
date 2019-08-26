package exception;

import entity.ResultEnum;

public class AoException extends RuntimeException {

    private ResultEnum resultEnum;

    public AoException() {
    }

    public ResultEnum getResultEnum() {
        return resultEnum;
    }

    public AoException(ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
    }

    public AoException(ResultEnum resultEnum, Throwable cause) {
        super(cause.getMessage());
        this.setStackTrace(cause.getStackTrace());
        this.resultEnum = resultEnum;
    }

    public AoException(String message) {
        super(message);
    }

    public AoException(String message, Throwable cause) {
        super(message, cause);
    }

    public AoException(Throwable cause) {
        super(cause);
    }

    public AoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
