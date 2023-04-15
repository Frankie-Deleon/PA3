package DeleonOlsenON1PA3;
import java.util.Scanner;
import java.util.Calendar;
import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

public class SalesSystem
{
  private SalesReport[] reports;
  private Scanner input = new Scanner(System.in);
  private Calendar dateTime;
  private ArrayList<String> salesReports;
  private String fileName;
  private char count;


  public void SalesStystem()
  {
  }
  public void start()
  {
    System.out.printf("%nPriming read to enter the sales system.%n");
    System.out.printf("%nTANDEM ENTERPRISES%n");
    System.out.printf("%nBegin the processing of a sales report? Enter 'Y' or 'N': ");

    if(count == 'Y')
    {
      processSalesReport();
      writeSalesRepData();
      checkInputFile();
    }
    else {
      exitMessage();
    }
  }
  public void processSalesReport()
  {
    int size = 0;
    int qtrCounter = 0;
    int monthCounter = 0;
    String aSalesReport = "\n\nTANDEM ENTERPRISES";
    SalesReport anotherObj = new SalesReport();

    aSalesReport += anotherObj.getCompanyTargetMsg();
    aSalesReport += anotherObj.getSalesRepRevReport();
    anotherObj.setProjectedSales();
    anotherObj.setNoSalesReps();

    size = anotherObj.getNoSalesReps();
    reports = new SalesReport[size];

    for(int i = 0; i < size; i++)
    {
      qtrCounter = 1;
      reports[i] = new SalesReport();
      reports[i].setSalesRep(i + 1);
      reports[i].setNoQtrs();
      while(qtrCounter <= reports[i].getNoQtrs())
      {
        monthCounter = 1;
        reports[i].chooseQtr(qtrCounter);
        while(monthCounter <= reports[i].getNoMonths())
        {
          reports[i].determineMonthNo(monthCounter);
          reports[i].setSalesRevenue();
          reports[i].calculateQtrlySales();
          ++monthCounter;
        }
        ++qtrCounter;
      }
      reports[i].calculateAnnualSales();
      aSalesReport += reports[i].getSalesRepRevReport();
      aSalesReport += reports[i].getRepTargetMsg();
      salesReport.add(aSalesReport);
      aSalesReport = "%n%n TANDEM ENTERPRISES";
    }
    for(String eachReport : salesReports)
    {
      System.out.printf("%n%s", eachReport);
    }
    anotherObj.getCompanyTargetMsg();
  }

  public void writeSalesRepData()
  {
    String record = "";
    PrintWriter outputFile = null;
    boolean fileError = false;

    try {
      System.out.printf("%nEnter the file name for sales report (WARNING: This will erase a pre-existing file!): ");
      fileName = input.next();
      outputFile = new PrintWriter(fileName);
      for(int i = 0; i < salesReports.length; i++)
      {
        String record = String.format("%s, %d, %s, %.2f%n",
                                      salesReports[i].getDate(),
                                      salesReports[i].getNoQtrs(),
                                      salesReports[i].getSalesRep(),
                                      salesReports[i].getQuarterlySales());
        outputFile.printf(record);
      }
    } catch(IOException e)
    {
      System.err.printf("%nFile cannot be created.%n");
      fileError = true;
    }
    if(!fileError)
    {
      outputFile.close();
      System.out.printf("Data written to the %s file.%n", fileName);
    }
  }

  public void checkInputFile()
  {
    File file;
    String fileRecord;
    boolean fileNotFound;

    try{
      System.out.printf("%nEnter the name for the sales report file: ");
      String fileName = "SalesReports.txt";
      file = new File(fileName);
      while(inputFile.hasNext())
      {
        fileRecord = inputFile.nextLine();
        System.out.printf("%n%s", fileRecord);
      }
      System.out.println();
    }catch(IOExceptions e) {
      System.err.printf("File not found!%n");
      fileNotFound = true;
    }catch(NullPointerException e) {
      System.err.printf("Record couldn't be accessed or read.%n");
      fileNotFound = true;
    }
    if(!fileNotFound)
    {
      inputFile.close();
    }
  }
  public void exitMessage()
  {
    System.out.printf("%nExiting Sales System.%n");
  }
}
public void chooseQtr(int qtrCounter)
{
    switch (qtrCounter)
    {
        case 1:
            System.out.println("Enter the sales revenue for the first quarter: ");
            break;
        case 2:
            System.out.println("Enter the sales revenue for the second quarter: ");
            break;
        case 3:
            System.out.println("Enter the sales revenue for the third quarter: ");
            break;
        case 4:
            System.out.println("Enter the sales revenue for the fourth quarter: ");
            break;
    }
}

public static void main(String[] args)
{
    SalesSystem salesSystem = new SalesSystem();
    salesSystem.start();
}

}

