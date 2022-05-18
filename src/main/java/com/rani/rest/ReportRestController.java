package com.rani.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rani.reports.ExcelGenerator;
import com.rani.reports.PdfGenerator;
import com.rani.request.SearchRequest;
import com.rani.response.SearchResponse;
import com.rani.service.ReportService;

@RestController
public class ReportRestController {

	@Autowired
	private ReportService service;
	
	@GetMapping("/plan-name")
	public List<String> getPlanName(){
		return service.getPlanName();
		
	}
	
	@GetMapping("/plan-status")
	public List<String> getPlanStatuses(){
		return service.getPlanStatus();
	}
	
	@GetMapping("/search")
	public List<SearchResponse> searchPlans(SearchRequest request){
		return service.searchPlans(request);
	}
	
	@GetMapping("/excel")
	  public void generateExcel(HttpServletResponse response) throws Exception {

	    response.setContentType("application/octet-stream");
	    String headerKey = "Content-Disposition";
	    String headerValue = "attachment; filename=Plans.xls";
	    response.setHeader(headerKey, headerValue);

	    List<SearchResponse> records = service.searchPlans(null);
	    ExcelGenerator excel = new ExcelGenerator();
	    excel.generateExcel(records, response);
	  }
	
	@GetMapping("/pdf")
	  public void generatePdf(HttpServletResponse httpResponse) throws Exception {

	    httpResponse.setContentType("application/pdf");
	    String headerKey = "Content-Disposition";
	    String headerValue = "attachment; filename=Plans.pdf";
	    httpResponse.setHeader(headerKey, headerValue);

	    List<SearchResponse> records = service.searchPlans(null);
	    PdfGenerator pdfGen = new PdfGenerator();
	    pdfGen.generatePdf(records, httpResponse);
	  }
	
	
}
