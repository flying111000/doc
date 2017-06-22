
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MiniException extends RuntimeException { 
    private static final long serialVersionUID = 1L;  
	private String errorId;
	private String errorMessage;

	public String getErrorId() {
		return errorId;
	}

	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public MiniException() {
		super();
	}

	public MiniException(String errorId, String errorMessage) {
		super(errorMessage);
		this.errorId = errorId;
		this.errorMessage = errorMessage;
	}
}
