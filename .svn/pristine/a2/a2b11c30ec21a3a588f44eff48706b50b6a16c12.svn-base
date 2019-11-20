package kr.cubex.data;

import java.util.Locale;

import org.springframework.context.MessageSource;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.cubex.utils.ComStr;

public class BaseResult {
	public static final int 			RET_OK						= 200;
	public static final int 			ERR_INVALID_PARAMETERS		= 601;
	public static final int 			ERR_RESULT_FAIL				= 602;
	public static final int 			ERR_EXIST_DATA				= 603;
	public static final int 			ERR_NO_DATA					= 604;
	public static final int 			ERR_INVALID_API_KEY			= 605;
	public static final int				ERR_DB_DUPLICATE_KEY		= 606;

	public static final int 			ERR_LOGIN_FAILED			= 611;
	//
	public static final int				ERR_ID_NOT_FOUND			= 611;
	public static final int				ERR_PWD_NOT_MATCH			= 612;
	public static final int				ERR_USER_NOT_EXIST			= 613;

	public static final int 			ERR_DECRYPT_FILE_FAIL		= 621;
	//
	public static final String			MSG_OK						= "OK";
	public static final String			MSG_ERR_INVALID_PARAMETERS	= "Invalid parameters";
	public static final String			MSG_ERR_RESULT_FAIL			= "Result fail";
	public static final String			MSG_ERR_LOGIN_FAILED		= "Login failed";
	public static final String			MSG_ERR_EXIST_DATA			= "Exist data";
	public static final String			MSG_ERR_NO_DATA				= "No data";
	public static final String			MSG_ERR_INVALID_API_KEY		= "Invalid api key";
	

	private int							retCode;
	private String						retMsg;
	
	public int getRetCode() {
		return retCode;
	}
	public void setRetCode(int res_code) {
		this.retCode = res_code;
	}
	public void setRetCode(int retCode, MessageSource messageSource) {
		this.retCode = retCode;
		
		if (this.retCode == RET_OK) {
			retMsg = "OK";
		}
		else {
			if (messageSource == null) {
				retMsg = "에러가 발생하였습니다 - 에러코드:" + this.retCode;
			}
			else {
				try {
					String	sMsgCode = "errmsg.rcode-" + retCode;
					retMsg = messageSource.getMessage(sMsgCode, null, Locale.getDefault());
				} catch(Exception e) {
					retMsg = "에러가 발생하였습니다 - 에러코드:" + this.retCode;
				}
			}
		}
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String res_msg) {
		this.retMsg = res_msg;
	}
	public void setResultCode(int retCode) {
		this.retCode = retCode;
		switch(retCode) {
		case RET_OK:
			retMsg = MSG_OK;
			break;

		case ERR_INVALID_PARAMETERS:
			retMsg = MSG_ERR_INVALID_PARAMETERS;
			break;
		case ERR_RESULT_FAIL:
			retMsg = MSG_ERR_RESULT_FAIL;
			break;
		case ERR_EXIST_DATA:
			retMsg = MSG_ERR_EXIST_DATA;
			break;

		case ERR_LOGIN_FAILED:
			retMsg = MSG_ERR_LOGIN_FAILED;
			break;

		default:
			retMsg = "Unknown result code: " + retCode;
			break;
		}
	}

	@JsonIgnore
	public boolean isRetOK() {
		return retCode == RET_OK;
	}

	@Override
	public String toString() {
		return ComStr.toJson(this);
	}
	
	//
	public static BaseResult create(int retCode) {
		BaseResult	result = new BaseResult();
		result.setResultCode(retCode);
		return result;
	}
}
