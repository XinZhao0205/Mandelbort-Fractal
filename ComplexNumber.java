import java.lang.Math;

public class ComplexNumber{
 double real=0;
 double imag=0;

 ComplexNumber(double i, double j){
  real = i;
  imag = j;
 }

 public double abs(){
  double b = Math.sqrt(real*real+imag*imag);
  return b;
 }
 public ComplexNumber add(ComplexNumber c2){
  real=c2.real+real;
  imag=c2.imag+imag;
  ComplexNumber c = new ComplexNumber(real,imag);
  return c;
 }
 public ComplexNumber squared(){
  double temp = real;
  real = real*real-imag*imag;
  imag = (2*temp*imag);
  ComplexNumber c = new ComplexNumber(real,imag);
  return c;
 }
}