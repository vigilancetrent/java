* the value of the investment after one year are
* printed to standard output.
*/
public class Interest {
public static void main(String[] args) {
/* Declare the variables. */
double principal;
double rate;
double interest;

principal = 17000;
rate = 0.07;
interest = principal * rate;
// Compute the interest.
principal = principal + interest;

System.out.print("The interest earned is $");
System.out.println(interest);
System.out.print("The value of the investment after one year is $");
System.out.println(principal);
} // end of main()
} // end of class Interest
