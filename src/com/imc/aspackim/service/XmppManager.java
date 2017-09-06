package com.imc.aspackim.service;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

import com.imc.aspackim.vo.ReturnData;

public class XmppManager {
	
	 
	private static XMPPConnection connection = XmppUtil.getConnection();
	
	public static ReturnData login(String user, String password) {
		ReturnData result = new ReturnData();
		try {
			if (connection == null)
			{
				result.setMessage("connection is null");
				return result;
			}
			/** 登录 */			
			connection.login(user, password);
			result.setMessage("login success");
			result.setError(false);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	public static ReturnData register(String user, String password) {
		ReturnData result = new ReturnData();
		try {
			if (connection == null)
			{
				result.setMessage("connection is null");
				return result;
			}
			connection.getAccountManager().createAccount(user, password);
			result.setMessage("register success");
			result.setError(false);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	public static ReturnData getRoster(String user)
	{
		ReturnData result = new ReturnData();
		try {
			if (connection == null)
			{
				result.setMessage("connection is null");
				return result;
			}
			Roster roster = connection.getRoster();
			result.setMessage("getRoster success");
			result.setRoster(roster);
			result.setError(false);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	public static ReturnData addFriend(String user, String nickname)
	{
		ReturnData result = new ReturnData();
		try {
			if (connection == null)
			{
				result.setMessage("connection is null");
				return result;
			}
			
			connection.getRoster().createEntry(user, nickname, new String[]{"Firends"});
			result.setMessage("addFriend success");
			result.setError(false);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	public static void sendTalkMsg(String to, String msg) {
		Chat chat = connection.getChatManager()
				.createChat(to, null);
		try {
			chat.sendMessage(msg);
		} catch (XMPPException e) {
			e.printStackTrace();
		}
	}

}
