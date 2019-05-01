package gui;

import java.util.ArrayList;

public class bundledTuple 
{
	int listIndex;
	ArrayList<Object> dataValues = new ArrayList<Object>();
	ArrayList<String> columnName = new ArrayList<String>();
	
	public bundledTuple(int listIndex)
	{
		this.listIndex = listIndex;
	}
	
	public void addValue(Object value, String columnName)
	{
		dataValues.add(value);
		this.columnName.add(columnName);
	}
	
	public String toString()
	{
		String concatString = "";
		int index = 0;
		for(Object obj : dataValues)
		{
			concatString += columnName.get(index) + ": " + obj + "  ";
			index++;
		}
		return concatString;
	}
	
	public String getTypes()
	{
		String temp = "";
		for(Object obj : dataValues)
		{
			temp += obj.getClass();
		}
		return temp;
	}
}
