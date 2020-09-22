package com.cictec.web.auth.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ClientQuery {
	
	private int start;
	private int limit;
	private int page;
	
	private String sort;
	private List<ClientSortProperty> sortProperties = new ArrayList<ClientSortProperty>();
	
	private String filter;
	private Map<String,String> params = new HashMap<String,String>();
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
		if(this.sort != null){
			ObjectMapper mapper = new ObjectMapper(); 
			try {
				List<ClientSortProperty> sps = mapper.readValue(sort, new TypeReference<List<ClientSortProperty>>() {});
				sortProperties.addAll(sps);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<ClientSortProperty> getSortProperties() {
		return sortProperties;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
		if(this.filter != null){
			ObjectMapper mapper = new ObjectMapper(); 
			try {
				List<ClientFilterProperty> cfps = mapper.readValue(filter, new TypeReference<List<ClientFilterProperty>>() {});
				for(ClientFilterProperty cfp : cfps){
					params.put(cfp.getProperty(), cfp.getValue());
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}

	public Map<String, String> getParams() {
		return params;
	}
	
	public static void main(String[] args){
		Pattern PATTERN =  Pattern.compile("\"lng\":([\\d|.]+),\"lat\":([\\d|.]+)",Pattern.CASE_INSENSITIVE);
		String msg = "{\"location\":{\"lng\":116.645917,\"lat\":39.678383},\"formatted_address\":\"北京市大兴区周神路\",\"business\":\"\",\"addressComponent\":{\"city\":\"北京市\",\"direction\":\"\",\"distance\":\"\",\"district\":\"大兴区\",\"province\":\"北京市\",\"street\":\"周神路\",\"street_number\":\"\"},\"cityCode\":131}";
		Matcher matcher = PATTERN.matcher(msg);
		if(matcher.find()){
			System.out.println("Lng:"+matcher.group(1));
			System.out.println("Lat:"+matcher.group(2));
		}
	}
	
}
