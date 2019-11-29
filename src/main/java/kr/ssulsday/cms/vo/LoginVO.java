package kr.ssulsday.cms.vo;

import kr.cubex.comm.vo.BaseDataVO;

public class LoginVO extends BaseDataVO {
	
	private String user_id;
	private String user_pwd;
	private boolean isCookie;
	
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public boolean getisUseCookie() {
		return isCookie;
	}
	public void setisUseCookie(boolean isCookie) {
		this.isCookie = isCookie;
	}
}
