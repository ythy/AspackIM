package com.imc.aspackim.service;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

public class XmppUtil {
	
	private static XMPPConnection connection;
	
	public static XMPPConnection getConnection() {
		if(connection == null)
		{
			ConnectionConfiguration config = new ConnectionConfiguration("109.14.6.245", 5222);
			/** 是否启用安全验证 */
			config.setSASLAuthenticationEnabled(false);
			/** 是否启用调试 */
			// config.setDebuggerEnabled(true);
			/** 创建connection链接 */
			try {
				connection = new XMPPConnection(config);
				/** 建立连接 */
				connection.connect();
			} catch (XMPPException e) {
				e.printStackTrace();
			}
		}
		
		return connection;
	}
	
}
