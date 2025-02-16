package Utils;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	
	//DataProvider 1
	
		@DataProvider(name="Data")
		public String [][] getAllData() throws IOException
		{
			String path=".\\TestData\\UserData.xlsx";//taking xl file from testData
			
			ExcelUtils xlutil=new ExcelUtils(path);//creating an object for XLUtility
			
			int totalrows=xlutil.getRowCount("Sheet1");	
			int totalcols=xlutil.getCellCount("Sheet1",1);
					
			String apiData[][]=new String[totalrows][totalcols];//created for two dimension array which can store the data user and password
			
			for(int i=1;i<=totalrows;i++)  //1   //read the data from xl storing in two deminsional array
			{		
				for(int j=0;j<totalcols;j++)  //0    i is rows j is col
				{
					apiData[i-1][j]= xlutil.getCellData("Sheet1",i, j);  //1,0
				}
			}
		return apiData;//returning two dimension array
					
		}
		
		@DataProvider(name="UserNames")
		public String[] getUserNames() throws IOException
		{
			String path=".\\TestData\\UserData.xlsx";
			
			ExcelUtils xlutil=new ExcelUtils(path);
			
			int totalrows=xlutil.getRowCount("Sheet1");	
					
			String[] apiData=new String[totalrows];
			
			for(int i=1;i<=totalrows;i++)
			{		
				for(int j=0;j<totalrows;j++)
				{
					apiData[i-1]= xlutil.getCellData("Sheet1",i, 1);
				}
			}
		return apiData;
					
		}
		
}
