package com.csc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPayroll {

  Payroll payroll;

  @BeforeEach
  void setUp() {
    payroll = new Payroll(45, 3);
  }
  
  @Test
  public void checkGrossPay() {
    double gross_pay = payroll.computeAndPrintGrossPay();
    
    assertEquals(40 * 16.78 + 5 * 16.78 * 1.5, gross_pay);
  }
  
  @Test
  public void checkDeductions() {
    payroll.computeAndPrintGrossPay();
    double deductions = payroll.computeAndPrintDeductions();
    
    double valid_gross_pay = 40 * 16.78 + 5 * 16.78 * 1.5;
    double valid_deduction = valid_gross_pay * (0.06 + 0.14 + 0.05) + 10 + 35;
    double diff = valid_deduction - deductions;
    
    // check if the deductions are accurate to 1 cent.
    // floating point inaccuracy may change results slightly.
    assertEquals((diff < 0.01) & (diff > -0.01), true);
  }
  
  @Test
  public void checkNetPay() {
    payroll.computeAndPrintGrossPay();
    payroll.computeAndPrintDeductions();
    double net_pay = payroll.computeAndPrintNetPay();
    
    double valid_gross_pay = 40 * 16.78 + 5 * 16.78 * 1.5;
    double valid_deduction = valid_gross_pay * (0.06 + 0.14 + 0.05) + 10 + 35;
    double valid_net = valid_gross_pay - valid_deduction;
    double diff = valid_net - net_pay;
    
    assertEquals((diff < 0.01) & (diff > -0.01), true);
  }
}
