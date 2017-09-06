package com.imc.aspackim.vo;

import org.jivesoftware.smack.Roster;

public class ReturnData {

	 private boolean error = true;
	 private String message;
	 private Roster roster;
	 
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Roster getRoster() {
		return roster;
	}
	public void setRoster(Roster roster) {
		this.roster = roster;
	}

}
