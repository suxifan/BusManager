package com.cictec.web.auth.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.cictec.web.auth.pojo.BusLine;
import com.cictec.web.auth.pojo.ClientQuery;
import com.cictec.web.auth.pojo.ClientQueryByParam;
import com.cictec.web.auth.pojo.PagingResult;
import com.cictec.web.auth.pojo.Roles;
import com.cictec.web.auth.pojo.UserAuthData;
import com.cictec.web.auth.service.BusLineService;
import com.cictec.web.fuel.util.DBLogger;

@Controller
@RequestMapping(value="/busLine")
public class BusLineController extends BaseController {

	
	private static final Logger LOG = LoggerFactory.getLogger(BusLineController.class);

	@Resource(name="servBusLineService")
    public BusLineService busLineService;
	
	@RequestMapping(value = "/getAllBusLines", method= {RequestMethod.POST,RequestMethod.GET})  
	public @ResponseBody  PagingResult<BusLine> getAllBusLines(@RequestBody ClientQuery cq){
		PagingResult<BusLine> pr = new PagingResult<BusLine>();
		List<BusLine> liLine = null;
		liLine  = busLineService.getAllLines();
		
        LOG.warn("getAllBusLines.....:"+cq.getStart()+":"+cq.getLimit()+":"+cq.getPage());
        if(liLine!=null && liLine.size() > 0){
            pr.setTotalCount(liLine.size());
            pr.setData(liLine);
        }
		
		return pr;
	}


    @RequestMapping(value = "/getAllBusLinesWithPage", method= {RequestMethod.POST,RequestMethod.GET})
    public @ResponseBody  PagingResult<BusLine> getAllBusLinesWithPage(@RequestBody ClientQuery cq){
        PagingResult<BusLine> pr = new PagingResult<BusLine>();
        List<BusLine> liLine = null;
        liLine  = busLineService.getAllLines();

        LOG.warn("getAllBusLines.....:"+cq.getStart()+":"+cq.getLimit()+":"+cq.getPage());
        if(liLine!=null && liLine.size() > 0){
            pr.setTotalCount(liLine.size());



            int page = cq.getPage();
            int limit = cq.getLimit();
            int start = cq.getStart();
            int retStart = 0, retFin = 0;
            retFin = page*limit > liLine.size() ? liLine.size():page*limit;
            retStart = (page-1)*limit > liLine.size()-1 ? liLine.size()-1:(page-1)*limit;
            List<BusLine> n_list = liLine.subList(retStart, retFin);
            pr.setData(n_list);
            //pr.setData(liLine);
        }

        return pr;
    }




    @ResponseBody
    @RequestMapping(value="/saveBusLine")
    public Map<String, Object>   saveBusLine(@ModelAttribute BusLine busLine, BindingResult result){ 
    	boolean flag =false;
    	
        try{
        	if(busLine.getLineId()==null || "".equals(busLine.getLineId())){
        		flag = busLineService.saveBusLine(busLine);
        		DBLogger.log("增加线路:"+busLine.getLineId()+":"+busLine.getLineName());
        	}else{
        		flag = busLineService.updateBusLine(busLine);
        		DBLogger.log("修改线路:"+busLine.getLineId()+":"+busLine.getLineName());
        	}
       	 
        } catch (Exception e){
            e.printStackTrace();
        }
    	
        DBLogger.log("增加公交线路成功");
    	Map<String,Object> map = new HashMap<String, Object>();
        map.put("success", flag);
        return map;//"success"; 
	}
	
	
    @ResponseBody  
    @RequestMapping(value="/deleteBusLine")
	public Map<String, Object>deleteBusLine(String[] lineIds){
    	boolean flag =false;
    	for(String lineId:lineIds){
	    	LOG.debug("controller deleteBusLine"+lineId);
	    	DBLogger.log("删除线路ID:"+lineId);
		    try {
				flag = busLineService.deleteBusLine(lineId);
			}catch (Exception e) {
				e.printStackTrace();
			}
    	}
	    Map<String,Object> map = new HashMap<String, Object>();
	    map.put("success", flag);
        return map;
	}
	
	
    
    @RequestMapping(value="/queryBusLineByKeywords")
    public @ResponseBody  List<BusLine> queryBusLineByKeywords(@RequestBody ClientQueryByParam cq) throws UnsupportedEncodingException{
		List<BusLine> liLine = null;
		String keyword = cq.getLineName();
		String keyUtf8 = null;
		if( keyword!=null ){
			keyUtf8 = URLDecoder.decode(keyword, "UTF-8");
		}
		liLine  = busLineService.queryBusLineByKeywords(keyUtf8);
		return liLine;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
