package exception;

/**
 * @author tong.luo
 * @description StringConvertObjectException
 * @date 2021/1/13 11:13
 */
public class StringConvertObjectException extends RuntimeException {
    private static final long serialVersionUID = -4599980519309805786L;
    private String msg;

    public StringConvertObjectException(String msg) {
        super(msg);
    }

    public StringConvertObjectException(Throwable cause, String msg) {
        super(cause);
        this.msg = msg;
    }

    public StringConvertObjectException(Throwable cause) {
        super(cause);
        this.msg = msg;
    }

}
