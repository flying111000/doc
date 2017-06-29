
public enum ErrorCodeEnum {
	ER1420("."),
	ER1499("general error.")//book end
	;

	private String msg;

	private ErrorCodeEnum(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
}
