package poly.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.util.PapagoUtil;
import poly.util.UrlUtil;


@Controller
public class TestController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping(value = "test")
	public String test() {
		return "/test";
	}
	
	@RequestMapping(value = "test2")
	@ResponseBody
	public String test(HttpServletRequest request) throws Exception{
		
		String search = request.getParameter("search");
		
		return search;
	}
	
	   @RequestMapping(value="result", produces = "application/json; charset=utf8")
	   public @ResponseBody String result(HttpServletRequest request, ModelMap model) throws Exception {
	      String search = request.getParameter("search");
	      log.info(search);

	      UrlUtil uu = new UrlUtil();
	      
	      String url = "http://127.0.0.1:8000";
	      String api = "/search";
	      String Name = "?Text=";
	      String Text = search;
	      
	      String res = uu.urlReadforString(url+api+Name+URLEncoder.encode(Text, "UTF-8"));
	      PapagoUtil.converter("res");
	      System.out.println("res : " + res );
	      
	      uu = null;
	      
	      return res;
	   }
	}
