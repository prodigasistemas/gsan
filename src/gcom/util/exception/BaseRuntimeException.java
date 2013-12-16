package gcom.util.exception;

public class BaseRuntimeException extends RuntimeException {
	public BaseRuntimeException(String msg, Throwable e){
		super(msg, e);
	}
}
