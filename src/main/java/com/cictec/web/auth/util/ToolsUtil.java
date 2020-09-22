package com.cictec.web.auth.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ToolsUtil {
	
	public static void writeJSON(HttpServletResponse response, String json) throws IOException {
		
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(json);
	}

	public static void writeJSON(HttpServletResponse response, Object obj) throws IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		JsonFactory factory = mapper.getFactory();
		response.setContentType("text/html;charset=utf-8");
		JsonGenerator responseJsonGenerator = factory.createGenerator(response.getOutputStream(), JsonEncoding.UTF8);
		responseJsonGenerator.writeObject(obj);
	}
	
}
