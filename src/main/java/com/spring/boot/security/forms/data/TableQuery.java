package com.spring.boot.security.forms.data;

import com.spring.boot.security.constant.TableConstant;

public class TableQuery {
	
	
	public static String getActiveChallansQuery()
	{
		return "select a.*,b.totDistributed from (select cBook.*,sum(lotBook.total_qty) totQty from challan_book cBook\r\n" + 
				"inner join lot_book lotBook on cBook.challan_id=lotBook.challan_id group by 1 ) a inner join\r\n" + 
				"( select lotBook.challan_id,sum(subLotBook.total_qty) as totDistributed from lot_book lotBook\r\n" + 
				"Left join sub_lot_book subLotBook on lotBook.lot_id=subLotBook.lot_id group by 1) b on a.challan_id=b.challan_id\r\n" + 
				" where a.totQty!=b.totDistributed or b.totDistributed is null\r\n" ;
				
	}
   public static String getUpdateDistributionQuery(int challanId)
	{
		return "select \r\n" + 
				"challanBook.challan_id,challanBook.challan_date,challanBook.truck_no,sourceDetails.source_id,sourceDetails.source_name,"
				+ "destinationDetails.destination,traderDetails.trader_name,trader_mark,item.item_id,item.item_name,boxDetails.box_id,boxDetails.box_name,"
				+ "boxDetails.total_wt,lotBook.total_qty,lotBook.lot_id,subQuery.tot_bal\r\n" + 
				"from challan_book challanBook\r\n" + 
				"inner join source_details sourceDetails\r\n" + 
				"on sourceDetails.source_id=challanBook.source_id\r\n" + 
				"inner join\r\n" + 
				"destination_details destinationDetails\r\n" + 
				"on destinationDetails.destination_id=challanBook.destination_id\r\n" + 
				"inner join\r\n" + 
				"lot_book lotBook\r\n" + 
				"on lotBook.challan_id=challanBook.challan_id\r\n" + 
				"inner join trader_details traderDetails\r\n" + 
				"on traderDetails.trader_id=lotBook.trader_id\r\n" + 
				"inner join item_details item\r\n" + 
				"on item.item_id=lotBook.item_id\r\n" + 
				"inner join box_details boxDetails\r\n" + 
				"on boxDetails.box_id=lotBook.box_id"+
				" inner join "+
				" (SELECT lotBook.lot_id,case when totDistributed is null then lotBook.total_qty-0 else lotBook.total_qty-sub_lot.totDistributed end as tot_bal FROM lot_book lotBook left join("+
				" SELECT lot_id,sum(total_qty) as totDistributed FROM sub_lot_book group by 1)"+
				" sub_lot on sub_lot.lot_id=lotBook.lot_id ) subQuery on subQuery.lot_id=lotBook.lot_id where subQuery.tot_bal !=0 And challanBook.challan_id="+challanId	
				;
	}
	public static String getUpdateModalQuery()
	{
		return "SELECT lotBook.challan_id,lotBook.lot_id,lotBook.total_qty,case when totDistributed is null then lotBook.total_qty-0 else lotBook.total_qty-sub_lot.totDistributed end as qtyAvl,lotBook.receiver FROM lot_book lotBook left join  \r\n" + 
				"(\r\n" + 
				"SELECT lot_id,sum(total_qty) as totDistributed FROM sub_lot_book group by 1\r\n" + 
				") sub_lot on sub_lot.lot_id=lotBook.lot_id where lotBook.lot_id=?";
	}
	
	public static String getReceivingDtQuery(int lotId)
	{
		return "SELECT  cBook.challan_id, receiving_date,count(*)  FROM challan_book cBook inner join lot_book lotBook ON cBook.challan_id=lotBook.challan_id " + 
				"inner join sub_lot_book subLotId on subLotId.lot_id=lotBook.lot_id  where lotBook.lot_id ="+lotId+" group by 1,2 order by 1";
	}
	
	public static String  getFareCalculationQuery()
	{
		return "Select subLotBook.sub_lot_id,subLotBook.receiving_date,cBook.truck_no,source.source_name,adest.agent_destination_name" + 
				",agent.agent_name,agent.agent_mark,item.item_name,box.box_name,box.total_wt,subLotBook.total_qty" + 
				",fareRule.fare FROM challan_book cBook "
				+ "inner join lot_book lotBook on cBook.challan_id=lotBook.challan_id "
				+ "inner join sub_lot_book subLotBook on lotBook.lot_id=subLotBook.lot_id "
				+ "inner join agent_destination adest on subLotBook.agent_destination_id=adest.agent_destination_id "
				+ "inner join box_details box on box.box_id=lotBook.box_id "
				+ "inner join item_details item on item.item_id=lotBook.item_id " 
				+ "inner join source_details source on source.source_id=cBook.source_id " 
				+ "inner join agent_details agent on agent.agent_id=subLotBook.agent_id " 
				+ "left join fare_rule fareRule on cBook.source_id=fareRule.source_id and  fareRule.agent_destination_id=subLotBook.agent_destination_id and fareRule.box_id=lotBook.box_id and fareRule.item_id=lotBook.item_id "
				+ "left join fare_book fareBook on fareBook.sub_lot_id =subLotBook.sub_lot_id where fareBook.sub_lot_id is null";
	}
	
	public static String getCollectionDataQuery()
	{
		return "select * from (SELECT fareBook.fare_id,fareBook.sub_lot_id,cBook.truck_no,subLotBook.receiving_date,item.item_name,box.box_name,box.total_wt,subLotBook.total_qty,agent.agent_name,agent.agent_mark,agentDest.agent_destination_name,fareBook.total_fare,fareBal.totPymt,fareBal.totDebit,(fareBook.total_fare-(case when fareBal.totPymt is null then 0 else fareBal.totPymt end) -( case when fareBal.totDebit is null then 0 else fareBal.totDebit end)) as totalBalAmt\r\n" + 
				"FROM fare_book fareBook inner join  sub_lot_book subLotBook on fareBook.sub_lot_id=subLotBook.sub_lot_id inner join lot_book lotBook on lotBook.lot_id=subLotBook.lot_id inner join challan_book  cBook on cBook.challan_id=lotBook.challan_id inner join item_details item on item.item_id=lotBook.item_id inner join box_details box on box.box_id=lotBook.box_id inner join agent_details agent on agent.agent_id=subLotBook.agent_id inner join agent_destination  agentDest on agentDest.agent_destination_id=subLotBook.agent_destination_id \r\n" + 
				"left join\r\n" + 
				"(select fareCollection.sub_lot_id,sum(fareCollection.tot_payment) as totPymt,sum(fareCollection.debit_amt) as totDebit from fare_collection fareCollection   group by 1 order by 1) fareBal\r\n" + 
				"on fareBal.sub_lot_id=fareBook.sub_lot_id) collections\r\n" + 
				"where collections.totalBalAmt !=0";
	}
	
	public static String getCollectionDataQuery(int subLotId)
	{
		return getCollectionDataQuery()+" and collections.sub_lot_id="+subLotId;
	}
	
	public static String getAllChallansQuery()
	{
		return "select cBook.challan_id,cBook.challan_date,cBook.truck_no,source.source_id,source.source_name,destination.destination_id,destination.destination,cBook.driver_name,cBook.driver_mobile from challan_book cBook inner join destination_details destination on cBook.destination_id=destination.destination_id\r\n" + 
				"inner join source_details source on  source.source_id=cBook.source_id order by cBook.challan_id";
	
	}
	public static String getAllActiveChallansQuery(boolean active)
	{
		String allChallanQuery=getAllChallansQuery();
		if(active) {
		
		return "select cBook.challan_id,cBook.challan_date,cBook.truck_no,source.source_id,source.source_name,destination.destination_id,destination.destination,cBook.driver_name,cBook.driver_mobile from (\r\n" + 
				"select cBook.challan_id,sum(lotBook.total_qty) totQty from challan_book cBook\r\n" + 
				"inner join lot_book lotBook on cBook.challan_id=lotBook.challan_id group by 1\r\n" + 
				") cntl1\r\n" + 
				"inner join\r\n" + 
				"( select lotBook.challan_id,sum(subLotBook.total_qty) as totDistributed from lot_book lotBook\r\n" + 
				"Left join sub_lot_book subLotBook on lotBook.lot_id=subLotBook.lot_id group by 1\r\n" + 
				") cntl2\r\n" + 
				"on cntl1.challan_id=cntl2.challan_id\r\n" + 
				"inner join challan_book cBook on cBook.challan_id=cntl1.challan_id\r\n" + 
				"inner join destination_details destination on cBook.destination_id=destination.destination_id\r\n" + 
				"inner join source_details source on  source.source_id=cBook.source_id\r\n" + 
				"where cntl1.totQty!=cntl2.totDistributed or cntl2.totDistributed is null\r\n" + 
				"order by cBook.challan_id\r\n"; 
			
		}
		return allChallanQuery;
	
	}
	public static String getLotBooksByChallanIdQuery(int challanId)
	{
	return "SELECT lotBook.lot_id,trader.trader_id,trader.trader_name,trader.trader_mark,item.item_id,item.item_name,box.box_id,box.box_name,box.total_wt,lotBook.total_qty,lotBook.total_wt,lotBook.receiver FROM lot_book lotBook inner join trader_details trader on trader.trader_id=lotBook.trader_id" +
			" inner join item_details item on item.item_id=lotBook.item_id inner join box_details box on box.box_id=lotBook.box_id where lotBook.challan_id="+challanId;
	}	
	
	public static String getAllTraderListQuery()
	{
	return "SELECT  trader_id as value,concat(trader_mark, case when trader_name<>\"\" then concat(\"(\",trader_name,\")\") else \"\" end) as text from trader_details";
	}
	
	public static String getAllSourceListQuery()
	{
	return "SELECT source_id as value,source_name as text from source_details";
	}
	public static String getAllDestinationListQuery()
	{
	return "SELECT destination_id as value,destination as text from destination_details";
	}
	
	public static String getAllItemsListQuery()
	{
	return "SELECT item_id as value,item_name as text from item_details";
	}
	public static String getAllBoxListQuery()
	{
	return "SELECT box_id as value,concat(box_name,\"-\",total_wt,\"Kg\") as text from box_details";
	}
	
	public static String getAllAgentListQuery()
	{
	return "SELECT agent_id as value,concat(agent_mark,case when agent_name <>\"\" then concat(\"-\",agent_name) else \"\" end) as text from agent_details";
	}
	
	public static String getAllAgentDestListQuery()
	{
	return "SELECT agent_destination_id as value,agent_destination_name as text from agent_destination";
	}
	
	public static String getAllDistributionDataQuery()
	{
		return "select cBook.challan_id,cBook.challan_date,cBook.truck_no,lotBook.lot_id,source.source_name,destination.destination,trader.trader_name,concat(\r\n" + 
				"item.item_name,\"-\",box.box_name,\"-\",box.total_wt) as item_code,agent.agent_id,agent.agent_name,agent.agent_mark,subLotBook.sub_lot_id,aDest.agent_destination_id,aDest.agent_destination_name,subLotBook.total_qty,subLotBook.receiving_date\r\n" + 
				"from challan_book cBook \r\n" + 
				"inner join lot_book lotBook on lotBook.challan_id=cBook.challan_id\r\n" + 
				"inner join sub_lot_book subLotBook on subLotBook.lot_id=lotBook.lot_id\r\n" + 
				"inner join trader_details trader on trader.trader_id=lotBook.trader_id\r\n" + 
				"inner join destination_details destination on cBook.destination_id=destination.destination_id\r\n" + 
				"inner join source_details source on  source.source_id=cBook.source_id\r\n" + 
				"inner join item_details item on item.item_id=lotBook.item_id\r\n" + 
				"inner join box_details box on box.box_id=lotBook.box_id\r\n" + 
				"inner join agent_details agent on agent.agent_id=subLotBook.agent_id\r\n" + 
				"inner join agent_destination aDest on aDest.agent_destination_id=subLotBook.agent_destination_id";
	}
	
	public static String getAllFareBookQuery()
	{
		return "select cBook.challan_id,cBook.truck_no,lotBook.lot_id,concat(source.source_name,\"-\",destination.destination)\r\n" + 
				"as FromeToWhere,concat(item.item_name,\"-\",box.box_name,\"-\",box.total_wt) as item_code,\r\n" + 
				"subLotBook.sub_lot_id,agent.agent_id,concat(agent.agent_mark, case when agent.agent_name <>\"\" then concat(\"(\",agent.agent_name,\")\") else \"\" end) as agent\r\n" + 
				",aDest.agent_destination_id,aDest.agent_destination_name,subLotBook.receiving_date,subLotBook.total_qty,fareBook.fare_per_box as farePerBox,\r\n" + 
				"fareBook.total_fare\r\n" + 
				"from challan_book cBook\r\n" + 
				"inner join lot_book lotBook on lotBook.challan_id=cBook.challan_id\r\n" + 
				"inner join sub_lot_book subLotBook on subLotBook.lot_id=lotBook.lot_id\r\n" + 
				"inner join trader_details trader on trader.trader_id=lotBook.trader_id\r\n" + 
				"inner join destination_details destination on cBook.destination_id=destination.destination_id\r\n" + 
				"inner join source_details source on  source.source_id=cBook.source_id\r\n" + 
				"inner join item_details item on item.item_id=lotBook.item_id\r\n" + 
				"inner join box_details box on box.box_id=lotBook.box_id\r\n" + 
				"inner join agent_details agent on agent.agent_id=subLotBook.agent_id\r\n" + 
				"inner join agent_destination aDest on aDest.agent_destination_id=subLotBook.agent_destination_id\r\n" + 
				"inner join fare_book fareBook on fareBook.sub_lot_id=subLotBook.sub_lot_id\r\n" + 
				"order by cBook.challan_id,subLotBook.lot_id desc";
	}
	
	public static String getCollectionBookDataQuery()
	{
		return "select * from (SELECT fareBook.fare_id,fareBook.sub_lot_id,cBook.truck_no,concat(item.item_name,\"-\",box.box_name,\"-\",box.total_wt) as item_code,concat(agent.agent_mark, case when agent.agent_name <>\"\" then concat(\"(\",agent.agent_name,\")\") else \"\" end) as agent_name,agentDest.agent_destination_name as adest_name,"+
				"subLotBook.receiving_date,subLotBook.total_qty,fareBook.fare_per_box,fareBook.total_fare,(fareBook.total_fare) as tot_fare,fareBal.totPymt,"+
				"fareBal.totDebit,(fareBook.total_fare-fareBal.totPymt-fareBal.totDebit ) as totalBalAmt," + 
				"fareBal.latestPymtDt FROM fare_book fareBook inner join  sub_lot_book subLotBook on fareBook.sub_lot_id=subLotBook.sub_lot_id inner join lot_book lotBook on lotBook.lot_id=subLotBook.lot_id inner join challan_book  cBook on cBook.challan_id=lotBook.challan_id inner join item_details item on item.item_id=lotBook.item_id "+
				"inner join box_details box on box.box_id=lotBook.box_id inner join agent_details agent on agent.agent_id=subLotBook.agent_id inner join agent_destination  agentDest on agentDest.agent_destination_id=subLotBook.agent_destination_id\r\n" + 
				"inner join\r\n" + 
				"(select fareCollection.sub_lot_id,sum(fareCollection.tot_payment) as totPymt,sum(fareCollection.debit_amt) as totDebit,max(pymt_dt) as latestPymtDt from fare_collection fareCollection   group by 1 order by 1) fareBal\r\n" + 
				"on fareBal.sub_lot_id=fareBook.sub_lot_id and fareBal.totPymt>0) collections" 
				;
	}
	
	public static String getCollectionsById(int subLotId)
	{
		return "select collection_id,tot_payment,debit_amt,pymt_dt from " +TableConstant.COLLECTION_BOOK_TABLE+ " where sub_lot_id="+subLotId;
				
	}
	/*public static String getAllFareBookQuery()
	{
		return "select cBook.challan_id,cBook.truck_no,lotBook.lot_id,concat(source.source_name,\"-\",destination.destination)\r\n" + 
				"as FromeToWhere,concat(item.item_name,\"-\",box.box_name,\"-\",box.total_wt) as item_code,\r\n" + 
				"subLotBook.sub_lot_id,agent.agent_id,concat(agent.agent_name,\"(\",agent.agent_mark,\")\") as agent\r\n" + 
				",aDest.agent_destination_id,aDest.agent_destination_name,subLotBook.receiving_date,subLotBook.total_qty,fareBook.fare_per_box as farePerBox,\r\n" + 
				"fareBook.total_fare,sum(fareCollection.tot_payment) as tot_payment,sum(fareCollection.debit_amt) as debit_amt,\r\n" + 
				"GROUP_CONCAT(fareCollection.pymt_dt) as pymt_dt\r\n" + 
				"from challan_book cBook\r\n" + 
				"inner join lot_book lotBook on lotBook.challan_id=cBook.challan_id\r\n" + 
				"inner join sub_lot_book subLotBook on subLotBook.lot_id=lotBook.lot_id\r\n" + 
				"inner join trader_details trader on trader.trader_id=lotBook.trader_id\r\n" + 
				"inner join destination_details destination on cBook.destination_id=destination.destination_id\r\n" + 
				"inner join source_details source on  source.source_id=cBook.source_id\r\n" + 
				"inner join item_details item on item.item_id=lotBook.item_id\r\n" + 
				"inner join box_details box on box.box_id=lotBook.box_id\r\n" + 
				"inner join agent_details agent on agent.agent_id=subLotBook.agent_id\r\n" + 
				"inner join agent_destination aDest on aDest.agent_destination_id=subLotBook.agent_destination_id\r\n" + 
				
				"inner join fare_book fareBook on fareBook.sub_lot_id=subLotBook.sub_lot_id\r\n" + 
				"Left Join fare_collection fareCollection on fareCollection.sub_lot_id=subLotBook.sub_lot_id\r\n" + 
				"Group by 1,2,3,4,5,6,7,8,9,10,11,12,13,14 order by cBook.challan_id,subLotBook.lot_id desc";
	}*/
	
	public static String getTruckLedgerQuery()
	{
		return " Select * from (select truck_no,truck_start_dt,fromToWhere,truck_end_dt,adv_fare,tot_fare,prize_amt,"+
				" totPymt,(tot_fare+prize_amt-totPymt-adv_fare) as tot_bal from (SELECT tledger.truck_no,truck_start_dt,"+
				" concat(source_name,\"-\",destination) as fromToWhere,truck_end_dt,adv_fare,tot_fare,prize_amt,\r\n" + 
				" sum(case when tPymt.pymt_amt is null then 0 else tPymt.pymt_amt end ) as totPymt "+ 
				" FROM truck_ledger tledger \r\n"+
				" left join truck_pymt tPymt on  tledger.truck_no=tPymt.truck_no\r\n" + 
				" and tledger.truck_start_dt=tPymt.t_start_dt inner join source_details source on source.source_id=tledger.source_id\r\n" + 
				" inner join destination_details destination on destination.destination_id=tledger.destination_id\r\n" + 
				" group by 1,2,3,4,5,6,7) cntl ) truckLedger where tot_bal !=0 or truck_end_dt is null order by truckLedger.truck_start_dt desc" 
				;
	}
	
	public static String getLocalFareEntryQuery(String ledgerDt)
	{
		return "select GROUP_CONCAT(distinct cntl.truck_no) trucks,\r\n" + 
				"GROUP_CONCAT(distinct convert(cntl.truck_end_dt,CHAR)) truck_arrival_dt,\r\n" + 
				"cntl.local_driver,\r\n" + 
				"cntl.destination,\r\n" + 
				"GROUP_CONCAT(cntl.agent_destination_name) destName,\r\n" + 
				"sum(cntl.TotWt) totalWt ,\r\n" + 
				"case when localFare.tot_local_fare is null then 0 else localFare.tot_local_fare end totalFare  from \r\n" + 
				"(\r\n" + 
				"SELECT cBook.truck_no,tledger.truck_end_dt,subLotBook.local_driver,dest.destination,aDest.agent_destination_name,subLotBook.receiving_date,\r\n" + 
				"sum(subLotBook.total_qty*box.total_wt) as TotWt FROM challan_book cBook inner join lot_book lotBook on cBook.challan_id=lotBook.challan_id inner join\r\n" + 
				"sub_lot_book subLotBook on subLotBook.lot_id=lotBook.lot_id inner join local_fare_track lfaretrack\r\n" + 
				"on subLotBook.sub_lot_id=lfaretrack.sub_lot_id And\r\n" + 
				"subLotBook.local_driver !=\"\" and lfaretrack.indicator='N'\r\n" + 
				"inner join destination_details dest on dest.destination_id=cBook.destination_id\r\n" + 
				"inner join truck_ledger tledger on tledger.truck_no=cBook.truck_no and cBook.challan_date =tledger.truck_start_dt\r\n" + 
				"inner join agent_destination aDest on aDest.agent_destination_id=subLotBook.agent_destination_id\r\n" + 
				"inner join box_details box on box.box_id=lotBook.box_id\r\n" + 
				"Where subLotBook.receiving_date='"+ledgerDt+"'"+
				" group by 1,2,3,4,5\r\n" + 
				
				") cntl\r\n" + 
				"Left join local_fare localFare on localFare.local_driver=cntl.local_driver and localFare.ledger_dt='"+ledgerDt+"' \r\n" + 
				" group by 3,4,7"
				;
	}
	
	public static String getLocalFareDetailQuery(String truckNo,String arrivalDt,String localDriver,String ledgerDt)
	{
		return "SELECT subLotBook.sub_lot_id,item.item_name,concat(box.box_name,\"-\",box.total_wt,\"Kg\") as BoxType,subLotBook.total_qty,box.total_wt*subLotBook.total_qty as totWt,\r\n" + 
				"agent.agent_mark,agent.agent_name,aDest.agent_destination_name\r\n" + 
				"FROM challan_book cBook\r\n" + 
				"inner join lot_book lotBook on cBook.challan_id=lotBook.challan_id \r\n" + 
				"inner join sub_lot_book subLotBook on subLotBook.lot_id=lotBook.lot_id \r\n" + 
				"inner join local_fare_track lfaretrack on subLotBook.sub_lot_id=lfaretrack.sub_lot_id and lfaretrack.indicator='N'\r\n" + 
				"inner join destination_details dest on dest.destination_id=cBook.destination_id\r\n" + 
				"inner join truck_ledger tledger on tledger.truck_no=cBook.truck_no and cBook.challan_date =tledger.truck_start_dt\r\n" + 
				"inner join agent_destination aDest on aDest.agent_destination_id=subLotBook.agent_destination_id\r\n" + 
				"inner join box_details box on box.box_id=lotBook.box_id\r\n" + 
				"inner join item_details item on item.item_id=lotBook.item_id\r\n" + 
				"inner join agent_details agent on agent.agent_id=subLotBook.agent_id"+
				" where cBook.truck_no in("+truckNo+") and tledger.truck_end_dt in("+arrivalDt+") and subLotBook.local_driver='"+localDriver+"' and subLotBook.receiving_date='"+ledgerDt+"'"
				;
	}
	
	public static String getLocalFareSubLotIdslQuery(String truckNo,String arrivalDt,String localDriver,String ledgerDt)
	{
		return "SELECT subLotBook.sub_lot_id "+ 
				"FROM challan_book cBook\r\n" + 
				"inner join lot_book lotBook on cBook.challan_id=lotBook.challan_id \r\n" + 
				"inner join sub_lot_book subLotBook on subLotBook.lot_id=lotBook.lot_id \r\n" + 
				"inner join local_fare_track lfaretrack on subLotBook.sub_lot_id=lfaretrack.sub_lot_id and lfaretrack.indicator='N'\r\n" + 
				"inner join destination_details dest on dest.destination_id=cBook.destination_id\r\n" + 
				"inner join truck_ledger tledger on tledger.truck_no=cBook.truck_no and cBook.challan_date =tledger.truck_start_dt\r\n" + 
				"inner join agent_destination aDest on aDest.agent_destination_id=subLotBook.agent_destination_id\r\n" + 
				"inner join box_details box on box.box_id=lotBook.box_id\r\n" + 
				"inner join item_details item on item.item_id=lotBook.item_id\r\n" + 
				"inner join agent_details agent on agent.agent_id=subLotBook.agent_id"+
				" where cBook.truck_no in("+truckNo+") and tledger.truck_end_dt in("+arrivalDt+") and subLotBook.local_driver='"+localDriver+"' and subLotBook.receiving_date='"+ledgerDt+"'"
				;
	}
	
	public static String fareAndcollDataQuery()
	{
		return "select AGENT_DESTINATION,AGENT_NAME,DELIVERY_DATE,ITEM_CODE,TOTAL_QTY,TOTAL_FARE,TOTAL_PAYMENT,TOTAL_DEBIT,TOTAL_BALANCE from (SELECT agentDest.agent_destination_name as AGENT_DESTINATION,concat(agent.agent_mark, case when agent.agent_name <>\"\" then concat(\"(\",agent.agent_name,\")\") else \"\" end) as AGENT_NAME,subLotBook.receiving_date as DELIVERY_DATE,concat(item.item_name,\"-\",box.box_name,\"-\",box.total_wt) as ITEM_CODE,subLotBook.receiving_date,cBook.truck_no,"+
				"subLotBook.total_qty as TOTAL_QTY,fareBook.FARE_PER_BOX,fareBook.total_fare as TOTAL_FARE,"+
				"case when fareBal.totPymt is null then 0 else fareBal.totPymt end  as TOTAL_PAYMENT,"+
				"case when fareBal.totDebit is null then 0 else fareBal.totDebit end  as TOTAL_DEBIT,"+
			     "(fareBook.total_fare-(case when fareBal.totPymt is null then 0 else fareBal.totPymt end)-(case when fareBal.totDebit is null then 0 else fareBal.totDebit end) ) as TOTAL_BALANCE," + 
				"fareBal.latestPymtDt FROM fare_book fareBook inner join  sub_lot_book subLotBook on fareBook.sub_lot_id=subLotBook.sub_lot_id inner join lot_book lotBook on lotBook.lot_id=subLotBook.lot_id inner join challan_book  cBook on cBook.challan_id=lotBook.challan_id inner join item_details item on item.item_id=lotBook.item_id "+
				"inner join box_details box on box.box_id=lotBook.box_id inner join agent_details agent on agent.agent_id=subLotBook.agent_id inner join agent_destination  agentDest on agentDest.agent_destination_id=subLotBook.agent_destination_id\r\n" + 
				"Left join\r\n" + 
				"(select fareCollection.sub_lot_id,sum(fareCollection.tot_payment) as totPymt,sum(fareCollection.debit_amt) as totDebit,max(pymt_dt) as latestPymtDt from fare_collection fareCollection   group by 1 order by 1) fareBal\r\n" + 
				"on fareBal.sub_lot_id=fareBook.sub_lot_id ) collections " 
				;
		
	}
	
}
