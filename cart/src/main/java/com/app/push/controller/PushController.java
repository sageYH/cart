package com.app.push.controller;

import java.io.FileInputStream;
import java.util.Arrays;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

@Controller
public class PushController {
	Logger log = LoggerFactory.getLogger(this.getClass());

//	//http post format sample
//	POST https://fcm.googleapis.com/v1/projects/__my project name/message:send HTTP/1.1
//
//	Content-Type: applocation/json
//	Authorization: Bearer __private key(admin key) on Firebase site
//	{
//	  "message":{
//	    "token": "a token created from a device"	//save as db(changed frequently)
//	    "notification": {
//	      "body": "This is an FCM notification message!",
//	      "title": "FCM Message",
//	    }
//	  }
//	}
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/fcmTest", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	public void fcmTest() throws Exception {
		try {	
			
			String path = "C:/** .. **/webapp/resources/google/{fcm-test-*******************************.json}";		   
			String MESSAGING_SCOPE = "https://www.googleapis.com/auth/firebase.messaging";
			String[] SCOPES = { MESSAGING_SCOPE };
			
			GoogleCredential googleCredential = GoogleCredential
								.fromStream(new FileInputStream(path))
								.createScoped(Arrays.asList(SCOPES));
			googleCredential.refreshToken();
								
			HttpHeaders headers = new HttpHeaders();
			headers.add("content-type" , MediaType.APPLICATION_JSON_VALUE);
			headers.add("Authorization", "Bearer " + googleCredential.getAccessToken());
			
			JSONObject notification = new JSONObject();
			notification.put("body", "TEST");
			notification.put("title", "TEST");
			
			JSONObject message = new JSONObject();
			message.put("token", "fa_qIyte8d4:APA91bHOGnZulT059PyK3z_sb1dIkDXTiZUIuRksmS7TdK6XgXAS5kopeGIwUfyhad3X3iXMNknCUOZaF6_mgoj1ohG10CanRyJ_EW1d3xN2E-1DPiLdbMK4pdOgdhB1ztZClqB-25rC");
			message.put("notification", notification);
			
			JSONObject jsonParams = new JSONObject();
			jsonParams.put("message", message);
			
			HttpEntity<JSONObject> httpEntity = new HttpEntity<JSONObject>(jsonParams, headers);
			RestTemplate rt = new RestTemplate();			
			
			ResponseEntity<String> res = rt.exchange("https://fcm.googleapis.com/v1/projects/{프로젝트명}/messages:send"
					, HttpMethod.POST
					, httpEntity
					, String.class);
		
			if (res.getStatusCode() != HttpStatus.OK) {
				log.debug("FCM-Exception");
				log.debug(res.getStatusCode().toString());
				log.debug(res.getHeaders().toString());
				log.debug(res.getBody().toString());
				
			} else {
				log.debug(res.getStatusCode().toString());
				log.debug(res.getHeaders().toString());
				log.debug(res.getBody().toLowerCase());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
