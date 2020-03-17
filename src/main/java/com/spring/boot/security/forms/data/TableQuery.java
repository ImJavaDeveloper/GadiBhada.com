package com.spring.boot.security.forms.data;

public class TableQuery {
	
	public static String getUpdateDistributionQuery()
	{
		return "select \r\n" + 
				"challanBook.challan_id,challanBook.challan_date,challanBook.truck_no,sourceDetails.source_name,"
				+ "destinationDetails.destination,traderDetails.trader_name,trader_mark,item.item_name,boxDetails.box_name,"
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
				" sub_lot on sub_lot.lot_id=lotBook.lot_id ) subQuery on subQuery.lot_id=lotBook.lot_id where subQuery.tot_bal !=0 "	
				;
	}
	public static String getUpdateModalQuery()
	{
		return "SELECT lotBook.challan_id,lotBook.lot_id,lotBook.total_qty,case when totDistributed is null then lotBook.total_qty-0 else lotBook.total_qty-sub_lot.totDistributed end as qtyAvl,lotBook.receiver FROM lot_book lotBook left join  \r\n" + 
				"(\r\n" + 
				"SELECT lot_id,sum(total_qty) as totDistributed FROM sub_lot_book group by 1\r\n" + 
				") sub_lot on sub_lot.lot_id=lotBook.lot_id where lotBook.lot_id=?";
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
				+ "left join fare_rule fareRule on cBook.source_id=fareRule.source_id and  fareRule.agent_destination_id=subLotBook.agent_destination_id and fareRule.box_id=lotBook.box_id and fareRule.item_id=lotBook.item_id"
				+ " left join fare_book fareBook on fareBook.sub_lot_id =subLotBook.sub_lot_id where fareBook.sub_lot_id is null";
	}
	
	public static String getCollectionDataQuery()
	{
		return "select * from (SELECT fareBook.fare_id,fareBook.sub_lot_id,cBook.truck_no,subLotBook.receiving_date,item.item_name,box.box_name,box.total_wt,subLotBook.total_qty,agent.agent_name,agent.agent_mark,agentDest.agent_destination_name,fareBook.total_fare ,(fareBook.total_fare+(case when fareBal.totextra is null then 0 else fareBal.totextra end)-(case when fareBal.totPymt is null then 0 else fareBal.totPymt end) -( case when fareBal.totDebit is null then 0 else fareBal.totDebit end)) as totalBalAmt\r\n" + 
				"FROM fare_book fareBook inner join  sub_lot_book subLotBook on fareBook.sub_lot_id=subLotBook.sub_lot_id inner join lot_book lotBook on lotBook.lot_id=subLotBook.lot_id inner join challan_book  cBook on cBook.challan_id=lotBook.challan_id inner join item_details item on item.item_id=lotBook.item_id inner join box_details box on box.box_id=lotBook.box_id inner join agent_details agent on agent.agent_id=subLotBook.agent_id inner join agent_destination  agentDest on agentDest.agent_destination_id=subLotBook.agent_destination_id \r\n" + 
				"left join\r\n" + 
				"(select fareCollection.sub_lot_id,sum(fareCollection.tot_payment) as totPymt,sum(fareCollection.debit_amt) as totDebit,sum(fareCollection.extra_fare) as totextra from fare_collection fareCollection   group by 1 order by 1) fareBal\r\n" + 
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
	public static String getLotBooksByChallanIdQuery(int challanId)
	{
	return "SELECT lotBook.lot_id,trader.trader_id,trader.trader_name,trader.trader_mark,item.item_id,item.item_name,box.box_id,box.box_name,box.total_wt,lotBook.total_qty,lotBook.total_wt,lotBook.receiver FROM lot_book lotBook inner join trader_details trader on trader.trader_id=lotBook.trader_id" +
			" inner join item_details item on item.item_id=lotBook.item_id inner join box_details box on box.box_id=lotBook.box_id where lotBook.challan_id="+challanId;
	}	
	
	public static String getAllTraderListQuery()
	{
	return "SELECT trader_id as value,concat(trader_name,\"(\",trader_mark,\")\") as text from trader_details";
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
}
