package com.spring.boot.security.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.boot.security.dto.ChallanBookData;
import com.spring.boot.security.entity.ChallanBook;
import com.spring.boot.security.entity.LotBook;
import com.spring.boot.security.entity.SubLotBook;
import com.spring.boot.security.exception.AppHttpException;
import com.spring.boot.security.exception.DataBaseException;
import com.spring.boot.security.helper.FormUtils;
import com.spring.boot.security.repository.BoxDetailsRepository;
import com.spring.boot.security.repository.LotBookRepository;
import com.spring.boot.security.repository.SubLotBookRepository;

@Controller
public class ChallanEntryController {

	private static final Logger LOGGER=LoggerFactory.getLogger(ChallanEntryController.class);
	
	@Autowired
	ChallanBookData challanBookData;
	
	@Autowired
	BoxDetailsRepository boxDetailsRepository;
	
	@Autowired
	LotBookRepository lotBookRepository;
	
	
	@RequestMapping(value="/management/saveChallan" ,method=RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = DataBaseException.class)
	public String saveChallanBookData(@RequestParam Date date,@RequestParam Integer sourceId, @RequestParam Integer destinationId,@RequestParam String truckNo, @RequestParam(required = false) String driverName,
			@RequestParam(required = false) String driverMobile,@RequestParam Integer[] traderId,@RequestParam Integer[] itemId,
			@RequestParam Integer[] boxId,@RequestParam Integer[] totalQty,@RequestParam(required = false) String[] receiver,RedirectAttributes redirectAttributes,HttpServletRequest request) throws DataBaseException, AppHttpException 
	{
		
		
		HttpSession session=request.getSession(false);
		if(session == null) {
			throw new AppHttpException("Invalid Session !! Please Login Again");
		}
		
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate=sdf.format(dt);
		
		String sessionId=session.getId();
		LOGGER.info("session Id:"+session.getId());
		LOGGER.info("User Id:"+session.getAttribute("username"));
		LOGGER.info("Starting Saving Challan Data.....");
		
		ChallanBook challanBook=new ChallanBook();
		challanBook.setDate(date);
		challanBook.setDestinationId(destinationId);
		challanBook.setDriverName(driverName);
		challanBook.setTruckNo(truckNo);
		challanBook.setDriverMobile(driverMobile);
		challanBook.setSessionId(sessionId);
		challanBook.setCreateTimeStamp(currentDate);
		challanBook.setSourceId(sourceId);
		ChallanBook savedChallanBook=challanBookData.saveChallanData(challanBook);
		LOGGER.info("Saving Challan Data.....");
		
		if(savedChallanBook==null) {
			
				throw new DataBaseException("Exception found while saving data into Challan Book ","management");
			
		}
		for(int i=0;i<traderId.length;i++)
		{
			LotBook lotBook=new LotBook();
			lotBook.setChallanId(challanBook.getChallanId());
			lotBook.setTraderId(traderId[i]);
			lotBook.setItemId(itemId[i]);
			lotBook.setTotalQty(totalQty[i]);
			int wtPerBox=boxDetailsRepository.findById(boxId[i]).get().getTotal_wt();
			lotBook.setTotalWt(wtPerBox*totalQty[i]);
			lotBook.setBoxId(boxId[i]);
			lotBook.setSessionId(sessionId);
			lotBook.setCreateTimeStamp(currentDate);
			lotBook.setIsDistributed("N");
			if(receiver.length!=0)
			lotBook.setReceiver(FormUtils.nullToEmpty(receiver[i]));
			LotBook savedLotBook=lotBookRepository.save(lotBook);
			LOGGER.info("Saving Lot Data.....");
			if(savedLotBook==null) {
				
					throw new DataBaseException("Exception found while saving data into Lot Book ","management");
				
			}
			
		}
		redirectAttributes.addFlashAttribute("message", "Success");
		return "redirect:/management";
		
		
	}
	
	
	
	@ExceptionHandler(value= {DataBaseException.class})
	public ModelAndView handleDataBaseException(DataBaseException ex)
	{
		
		LOGGER.error(ex.getMessage(), ex);
		ModelAndView model=new ModelAndView();
		model.addObject("message", "Failed");
		model.addObject("exception", ex.getMessage());
		model.setViewName(ex.getPage());
		return model;
		
		
	}
	
	@ExceptionHandler(value= {AppHttpException.class})
	public ModelAndView handleAppHttpException(AppHttpException ex)
	{
		
		LOGGER.error(ex.getMessage(), ex);
		ModelAndView model=new ModelAndView();
		model.addObject("message", "Failed");
		model.addObject("exception", ex.getMessage());
		model.setViewName("showerror");
		return model;
		
		
	}
	
}
