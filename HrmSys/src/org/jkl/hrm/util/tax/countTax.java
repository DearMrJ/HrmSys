package org.jkl.hrm.util.tax;

/**
 *
 *@author Alan
 */
public class countTax {
	
	public static double count(double basic_salary , double bonus ,double fine ) {
		double real_wage = basic_salary+bonus-fine;
		double tax = 0;
		double needTax = basic_salary + bonus - fine - 5000;
		System.out.println("应纳税所得额为："+needTax+" 元");
		if (needTax<=0) {
//			return real_wage;
			return tax;
		}else if (needTax>0 && needTax <=5000) {
//			return real_wage;
			return tax;
		}else if(needTax <= 8000){
			tax = round((needTax - 5000)*0.03);
			real_wage -= tax;
			
		}else if (needTax <= 17000) {
			tax = round((needTax - 5000)*0.1-210);
			real_wage -= tax;
			
		}else if (needTax <= 30000) {
			tax = round((needTax - 5000)*0.2-1410);
			real_wage -= tax;
			
		}else if (needTax <= 40000) {
			tax = round((needTax - 5000)*0.25-2660);
			real_wage -= tax;
			
		}else if (needTax <= 60000) {
			tax = round((needTax - 5000)*0.3-4410);
			real_wage -= tax;
			
		}else if (needTax <= 85000) {
			tax = round((needTax - 5000)*0.4-7160);
			real_wage -= tax;
			
		}else {
			tax = round((needTax - 5000)*0.3-15160);
			real_wage -= tax;
			
		}
		System.out.println("纳税金额为："+tax+" 元");
//		return real_wage;
		return tax;
	}
	
	
	public static double round(double d) {
//		return (double)Math.round(d*100)/100;
		return Math.round(d*100)/100;
	}
	
	
	/*简单，但返回值是string
	public String round(double d) {
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(d);
	}
	*/
	
}

