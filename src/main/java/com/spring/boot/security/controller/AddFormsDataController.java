package com.spring.boot.security.controller;


import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.boot.security.entity.LotBook;
import com.spring.boot.security.exception.AppHttpException;
import com.spring.boot.security.exception.DataBaseException;
import com.spring.boot.security.helper.FormUtils;
import com.spring.boot.security.repository.BoxDetailsRepository;
import com.spring.boot.security.repository.LotBookRepository;

@Controller
@RequestMapping("/gadibhada")
public class AddFormsDataController {
	
	//private static final Logger LOGGER=LoggerFactory.getLogger(UpdateFormsDataController.class);
	
	
	@Autowired
	LotBookRepository lotBookRepository;
	@Autowired
	BoxDetailsRepository boxDetailsRepository;
	
	@RequestMapping(value="/managedata/addLotInChallan",method=RequestMethod.POST)
	@ResponseBody
	public String addLotInChallan(@RequestParam int trader,@RequestParam int item,@RequestParam int boxType,
			@RequestParam int totQty,@RequestParam(required = false) String receiver,@RequestParam int challanId,
			HttpServletRequest request) throws Exception
	{
		HttpSession session=request.getSession(false);
		if(session == null) {
			throw new AppHttpException("Invalid Session !! Please Login Again");
		}
		String sessionId=session.getId();
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate=sdf.format(dt);
		LotBook lotBook=new LotBook();
		lotBook.setChallanId(challanId);
		lotBook.setTraderId(trader);
		lotBook.setItemId(item);
		lotBook.setTotalQty(totQty);
		int wtPerBox=boxDetailsRepository.findById(boxType).get().getTotal_wt();
		lotBook.setTotalWt(wtPerBox*totQty);
		lotBook.setBoxId(boxType);
		lotBook.setSessionId(sessionId);
		lotBook.setCreateTimeStamp(currentDate);
		lotBook.setIsDistributed("N");
		if(receiver.length()!=0)
		lotBook.setReceiver(FormUtils.nullToEmpty(receiver));
		
		//LOGGER.info("Saving Lot Data.....");
		LotBook savedLotBook=lotBookRepository.save(lotBook);
		
		if(savedLotBook==null) {
			
				return "Exception found while saving data into Lot Book "; 
			
		}
		
			return "Success";
		
	}
	

}
