package com.api.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ClientInfo {
	public final static String CLIENT_INFO_HEADER_NAME = "X-ClientInfo";
	private String siteInfo;
	public static ClientInfo defaultValue(){
		ClientInfo info = new ClientInfo();
		info.setSiteInfo("localhost");
		return info;
	}
}
