package com.picpay.transactions.service;

import java.net.URI;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpay.transactions.domain.dto.UserDTO;

@Service
public class UserApiAccess {
	
	@Value("${api.user.url}")
	private String endPointUsers;
	
	@Value("${api.user.agent}")
	private String userAgent;
	
	private HttpHeaders headers;
	private JSONObject body;
	
	public UserApiAccess() {
		this.headers = new HttpHeaders();
		this.headers.setContentType(MediaType.APPLICATION_JSON);
		this.headers.setCacheControl(CacheControl.noCache());
		this.headers.set("User-Agent", this.userAgent);
		
		this.body = new JSONObject();
	}
	
	public void addParameter(String key, Object value) {
		this.body.put(key, value);
	}
	
	public UserDTO getUser(Integer id) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<String> request = new HttpEntity<>(this.body.toString(), this.headers);
			ResponseEntity<UserDTO> response = restTemplate.exchange(new URI(this.endPointUsers + "/" + id), 
					HttpMethod.GET, request, UserDTO.class);
			
			if(response.getStatusCode() == HttpStatus.OK) {
				return response.getBody();
			}
			
			return null;
		}catch (Exception e) {
			return null;
		}

	}

}
