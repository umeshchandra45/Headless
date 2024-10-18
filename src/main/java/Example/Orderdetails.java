package Example;

public class Orderdetails {
	public Integer Order_Number;
	public String Order_Header_Status;
	public Integer Order_Revision;
	public String Line_Number;
	public String Item_Number;
	public String Line_Status_Code;
	public String Current_Requested_Ship_Date;
	public String New_Requested_Ship_Date;

	public Integer getOrder_Number() {
		return Order_Number;
	}

	public void setOrder_Number(Integer order_Number) {
		Order_Number = order_Number;
	}

	public String getOrder_Header_Status() {
		return Order_Header_Status;
	}

	public void setOrder_Header_Status(String order_Header_Status) {
		Order_Header_Status = order_Header_Status;
	}

	public Integer getOrder_Revision() {
		return Order_Revision;
	}

	public void setOrder_Revision(Integer order_Revision) {
		Order_Revision = order_Revision;
	}

	public String getLine_Number() {
		return Line_Number;
	}

	public void setLine_Number(String line_Number) {
		Line_Number = line_Number;
	}

	public String getItem_Number() {
		return Item_Number;
	}

	public void setItem_Number(String item_Number) {
		Item_Number = item_Number;
	}

	public String getLine_Status_Code() {
		return Line_Status_Code;
	}

	public void setLine_Status_Code(String line_Status_Code) {
		Line_Status_Code = line_Status_Code;
	}

	public String getCurrent_Requested_Ship_Date() {
		return Current_Requested_Ship_Date;
	}

	public void setCurrent_Requested_Ship_Date(String current_Requested_Ship_Date) {
		Current_Requested_Ship_Date = current_Requested_Ship_Date;
	}

	public String getNew_Requested_Ship_Date() {
		return New_Requested_Ship_Date;
	}

	public void setNew_Requested_Ship_Date(String new_Requested_Ship_Date) {
		New_Requested_Ship_Date = new_Requested_Ship_Date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Current_Requested_Ship_Date == null) ? 0 : Current_Requested_Ship_Date.hashCode());
		result = prime * result + ((Item_Number == null) ? 0 : Item_Number.hashCode());
		result = prime * result + ((Line_Number == null) ? 0 : Line_Number.hashCode());
		result = prime * result + ((Line_Status_Code == null) ? 0 : Line_Status_Code.hashCode());
		result = prime * result + ((New_Requested_Ship_Date == null) ? 0 : New_Requested_Ship_Date.hashCode());
		result = prime * result + ((Order_Header_Status == null) ? 0 : Order_Header_Status.hashCode());
		result = prime * result + ((Order_Number == null) ? 0 : Order_Number.hashCode());
		result = prime * result + ((Order_Revision == null) ? 0 : Order_Revision.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orderdetails other = (Orderdetails) obj;
		if (Current_Requested_Ship_Date == null) {
			if (other.Current_Requested_Ship_Date != null)
				return false;
		} else if (!Current_Requested_Ship_Date.equals(other.Current_Requested_Ship_Date))
			return false;
		if (Item_Number == null) {
			if (other.Item_Number != null)
				return false;
		} else if (!Item_Number.equals(other.Item_Number))
			return false;
		if (Line_Number == null) {
			if (other.Line_Number != null)
				return false;
		} else if (!Line_Number.equals(other.Line_Number))
			return false;
		if (Line_Status_Code == null) {
			if (other.Line_Status_Code != null)
				return false;
		} else if (!Line_Status_Code.equals(other.Line_Status_Code))
			return false;
		if (New_Requested_Ship_Date == null) {
			if (other.New_Requested_Ship_Date != null)
				return false;
		} else if (!New_Requested_Ship_Date.equals(other.New_Requested_Ship_Date))
			return false;
		if (Order_Header_Status == null) {
			if (other.Order_Header_Status != null)
				return false;
		} else if (!Order_Header_Status.equals(other.Order_Header_Status))
			return false;
		if (Order_Number == null) {
			if (other.Order_Number != null)
				return false;
		} else if (!Order_Number.equals(other.Order_Number))
			return false;
		if (Order_Revision == null) {
			if (other.Order_Revision != null)
				return false;
		} else if (!Order_Revision.equals(other.Order_Revision))
			return false;
		return true;
	}

}
