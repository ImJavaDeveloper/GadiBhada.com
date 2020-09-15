package com.spring.boot.security.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.boot.security.dto.ChallanBookData;
import com.spring.boot.security.entity.ChallanBook;
import com.spring.boot.security.entity.LotBook;
import com.spring.boot.security.entity.TruckLedger;
import com.spring.boot.security.exception.AppHttpException;
import com.spring.boot.security.exception.DataBaseException;
import com.spring.boot.security.helper.DataValidator;
import com.spring.boot.security.helper.FormUtils;
import com.spring.boot.security.repository.BoxDetailsRepository;
import com.spring.boot.security.repository.LotBookRepository;
import com.spring.boot.security.repository.TruckLedgerRespository;

@Controller
@RequestMapping("/gadibhada/dataentry")
public class ChallanEntryController {

	private static final Logger LOGGER=LoggerFactory.getLogger(ChallanEntryController.class);
	
	@Autowired
	ChallanBookData challanBookData;
	
	@Autowired
	BoxDetailsRepository boxDetailsRepository;
	
	@Autowired
	LotBookRepository lotBookRepository;
	
	@Autowired
	TruckLedgerRespository truckLedgerRepository;
	
	
	@RequestMapping(value="/savechallanentry" ,method=RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = DataBaseException.class)
	public String saveChallanBookData(@RequestParam Date date,@RequestParam Integer sourceId, @RequestParam Integer destinationId,@RequestParam String truckNo, @RequestParam(required = false) String driverName,
			@RequestParam String advancePymt,@RequestParam(required = false) String driverMobile,@RequestParam Integer[] traderId,@RequestParam Integer[] itemId,
			@RequestParam Integer[] boxId,@RequestParam Integer[] totalQty,@RequestParam(required = false) String[] receiver,RedirectAttributes redirectAttributes,HttpServletRequest request) throws DataBaseException, AppHttpException 
	{
		
		if(advancePymt!=null && !DataValidator.isNumber(advancePymt)) {
			LOGGER.error("Error: Advance payement Is Not Correct format");
			redirectAttributes.addFlashAttribute("message", "Failed");
			redirectAttributes.addFlashAttribute("exception", "Please Enter Correct Advance Payment");
			return "redirect:/gadibhada/dataentry";
		}
		/*if(challanBookData.findIfChallanBookExist(date, truckNo))
		{
			LOGGER.error("Error: Can't save data as Challan Book already exist with same date and truck no");
			redirectAttributes.addFlashAttribute("message","Failed");
			redirectAttributes.addFlashAttribute("exception", "Challan Book already exist with same date-"+date+" and truck no-"+truckNo);
			return "redirect:/gadibhada/dataentry";
			//return to form
			
		}*/
		HttpSession session=request.getSession(false);
		if(session == null) {
			LOGGER.error("Error: Invalid Session !! Please Login Again");
			throw new AppHttpException("Invalid Session !! Please Login Again");
		}
		
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate=sdf.format(dt);
		
		String sessionId=session.getId();
		//LOGGER.info("session Id:"+session.getId());
		//LOGGER.info("User Id:"+session.getAttribute("username"));
		LOGGER.info("Info: Recording Challan Book Data For Truck:"+truckNo+" And Date:"+date);
		
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
		
		if(savedChallanBook==null) {
			    LOGGER.error("Error: Exception found while saving data into Challan Book for Truck:"+truckNo+"And Date:"+date);
				throw new DataBaseException("Exception found while saving data into Challan Book ","management");
			
		}
		LOGGER.info("Info: Saving Lot Book Data for Truck:"+truckNo+" And Date:"+date);
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
			
			if(savedLotBook==null) {
				    LOGGER.error("Error: Exception found while saving data into Lot Book for Truck:"+truckNo+"And Date:"+date);
					throw new DataBaseException("Exception found while saving data into Lot Book ","management");
				
			}
			
			LOGGER.info("Info: Saving Truck Ledger Data for Truck:"+truckNo+" And Date:"+date);
			TruckLedger truckData=new TruckLedger();
			truckData.setTruckNo(truckNo);
			truckData.setTruckStratDt(date);
			truckData.setSource_id(sourceId);
			truckData.setDestinationId(destinationId);
			truckData.setAdvFare(FormUtils.getDouble(advancePymt));
			
			TruckLedger truckLedger=truckLedgerRepository.save(truckData);

			if(truckLedger==null) {
				    LOGGER.error("Error: Exception found while saving data into Truck Ledger for Truck:"+truckNo+"And Date:"+date);
					throw new DataBaseException("Exception found while saving data into Truck Ledger ","management");
				
			}
			
		}
		redirectAttributes.addFlashAttribute("message", "Success");
		return "redirect:/gadibhada/dataentry";
		
		
	}
	
	@RequestMapping(value="/findIfChallanBookExist" ,method=RequestMethod.POST)
    @ResponseBody
	public String findIfChallanBookExist(@RequestParam Date date,@RequestParam String truckNo) throws DataBaseException, AppHttpException 
	{
		LOGGER.info("Checking If Challan Data Is Already Present");
	if(challanBookData.findIfChallanBookExist(date, truckNo))
	{
		LOGGER.error("Error: Can't save data as Challan Book already exist with same date and truck no");
		
		return "True";
		//return to form
		
	}
	else
		return "False";
	}
	
}
