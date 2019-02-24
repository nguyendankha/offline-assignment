package zalora.utilities;

public class TestExcelFile {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(getLoginCredential());
	}
	
	public static Object[][] getLoginCredential() throws Exception {
		ExcelDataReader excelDataReader = new ExcelDataReader();
		Object[][] testObjArray = excelDataReader.getTableArray("D:\\SeleniumOfflineAssignment\\ZaloraOfflineAssignment\\src\\test\\resources\\excelData\\TestData.xlsx", "brands");
		return (testObjArray);
	}

}
