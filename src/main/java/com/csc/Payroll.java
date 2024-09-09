package com.csc;

import java.lang.*;
import java.util.*;

public class Payroll {
  private double hours_worked;
  private double rate;
  private int dependents;
  
  private double gross_pay;
  private double deductions;
  private double net_pay;
  
  public Payroll() {
    Scanner scanner = new Scanner(System.in);
    
    System.out.println("Welcome to the payroll calculator!");
    
    hours_worked = nextDoubleMin(0.0, "How many hours did you work this week?", "Please input a number greater than or equal to 0.0");
    rate = 16.78;
    dependents = nextIntMin(0, "How many children do you have?", "Please input a number greater than or equal to 0");
    
    printStub();
  }
  
  // constructor used for testing
  public Payroll(double hours, int dependents) {
    hours_worked = hours;
    rate = 16.78;
    this.dependents = dependents;
    
    printStub();
  }
  
  public void printStub() {
    printPayrollStubHeader();
    printHoursAndRate();
    
    computeAndPrintGrossPay();
    
    System.out.println("");
    
    computeAndPrintDeductions();

    System.out.println("");
    
    computeAndPrintNetPay();
    
    System.out.println("Thank you for using the payroll calculator.");
  }
  
  private void printPayrollStubHeader() {
    System.out.println("Payroll stub:\n");
  }
  
  private void printHoursAndRate() {
    printStubLine("Hours", String.format("%.1f", hours_worked));
    printStubLine("Rate", String.format("%s $/hr", formatMonetaryDouble(this.rate)));
  }
  
  // computes how much money is made (before taxes or anything else) after a certain number of hours with a certain hourly rate.
  // returns the gross pay.
  public double computeAndPrintGrossPay() {
    double payment = 0.0;
    
    // compute the payment for the first 40 hours of work
    double hours = hours_worked;
    if (hours > 40.0) {
      payment += rate * 40.0;
      hours -= 40.0;
      
      payment += hours * rate * 1.5;
    } else {
      payment += rate * hours;
    }
    
    gross_pay = payment;
    
    printMoneyStubLine("Gross", gross_pay);
    
    return gross_pay;
  }
  
  // returns the amount deducted.
  public double computeAndPrintDeductions() {
    // how much to deduct from gross pay.
    double socsec = gross_pay * 0.06;
    double fedinc = gross_pay * 0.14;
    double stateinc = gross_pay * 0.05;
    double union = 10.0;
    double insurance = 0.0;
    if (dependents >= 3) {
      insurance = 35.0;
    } else {
      insurance = 15.0;
    }
    
    printMoneyStubLine("SocSec", socsec);
    printMoneyStubLine("FedTax", fedinc);
    printMoneyStubLine("StTax", stateinc);
    printMoneyStubLine("Union", union);
    printMoneyStubLine("Ins", insurance);
    
    deductions = socsec + fedinc + stateinc + union + insurance;
    
    return deductions;
  }
  
  public double computeAndPrintNetPay() {
    net_pay = gross_pay - deductions;
    printMoneyStubLine("Net", net_pay);
    
    return net_pay;
  }
  
  // reads the next double from System.in that has a value greater than or equal to the min_value
  private static double nextDoubleMin(double min_value, String prompt_message, String error_message) {
    Scanner scanner = new Scanner(System.in);
    
    double value = 0.0;
    
    while (true) {
      System.out.println(prompt_message);
      value = scanner.nextDouble();
      
      if (value < min_value) {
        System.out.println(error_message);
      } else {
        break;
      }
    }
    
    return value;
  }
  
  // reads the next integer from System.in that has a value greater than or equal to the min_value
  private static int nextIntMin(int min_value, String prompt_message, String error_message) {
    Scanner scanner = new Scanner(System.in);
    
    int value = 0;
    
    while (true) {
      System.out.println(prompt_message);
      value = scanner.nextInt();
      
      if (value < min_value) {
        System.out.println(error_message);
      } else {
        break;
      }
    }
    
    return value;
  }
  
  public static String formatMonetaryDouble(double value) {
    return String.format("%.2f", value);
  }
  
  private static void printStubLine(String header, String value) {
    System.out.println(String.format("%9s:\t%s", header, value));
  }
  
  private static void printMoneyStubLine(String value_header, double amount) {
    printStubLine(value_header, "$ " + formatMonetaryDouble(amount));
  }
  
  public static void main(String[] args) {
    Payroll p = new Payroll();
  }
}
