#include <iostream>
#include <fstream>
#include <cmath>
#include "Q3EC1.h"


using namespace std;

ComplexNumber::ComplexNumber(double i, double j){
		real=i;
		imag=j;
		//cout << "ComplexNumber constructed.." << endl;
	}
inline void ComplexNumber::prt(){
	 	double count=real+imag;
	 	cout << count <<endl;
	 }
inline double ComplexNumber::abs(){
	 	double b=sqrt(real*real+imag*imag);
	 	return b;
	 }

inline ComplexNumber ComplexNumber::add(ComplexNumber c2){
	 	ComplexNumber c;
	 	c.real=c2.real+real;
	 	c.imag=c2.imag+imag;
		return c;
	}
inline ComplexNumber ComplexNumber::squared(){
	 	ComplexNumber c;
	 	double tmp=real;
	 	c.real=(pow(real,2)-pow(imag,2));
	 	c.imag=(2*tmp*imag);
	 	return c;
	 }
 array2D::array2D(int xResolution, int yResolution){
		xRes=xResolution;
		yRes=yResolution;
		xtable=new float*[yRes];
		for(int i=0;i<yRes;i++){
			xtable[i]=new float[xRes];
		}
		//cout << "array2D constructed.." << endl;
	}
inline void array2D::getSize(int &xResolution, int &yResolution){
		xResolution=xRes;
		yResolution=yRes;
		cout << "xRes size is " << xResolution << endl;
		cout << "yRes size is " << yResolution << endl;
	}

inline int array2D::getValue(int x, int y){
		return xtable[x][y];
	}

inline void array2D::setValue(int x, int y, int val){

		xtable[x][y]=val;
	}

PGMImage::PGMImage(int xResolution, int yResolution, string imageFilename):array2D(xResolution,yResolution){
		filename=imageFilename;
		cout << "PGMImage constructed.." << endl;
	}

inline	void PGMImage::getResolution(int &xResolution, int &yResolution){
		getSize( xResolution,  yResolution);
	}
inline	void PGMImage::setPixel(int x,int y,int val){
		setValue( x, y, val);
	}
inline	int PGMImage::getPixel(int x,int y){

		return getValue(x, y);
	}
inline void PGMImage::writeFile(){
		ofstream PGMImage(filename);

		PGMImage << "P2" << endl;
		PGMImage << xRes << " " << yRes << endl;
		PGMImage << 255 << endl;
		for(int j=0;j<yRes;j++)
		{
			for(int i=0;i<xRes;i++)
			{
				PGMImage<<xtable[j][i]<<" ";
			}
			PGMImage<<endl;
		}

		PGMImage.close();
	}


	
int main(){
	PGMImage *a = new PGMImage(640,480,"fractal.pgm");
	int xRes, yRes;
	a->getResolution(xRes,yRes);

		float cxmin=-0.75 , cymin=0.1;
		float cxmax=cxmin+0.001, cymax=cymin+0.001;
		for(int y=0;y<yRes;y++){
			for(int x=0;x<xRes;x++){
				double i=cxmin+(x/(xRes-1.0))*(cxmax-cxmin);
				double j=cymin+(y/(yRes-1.0))*(cymax-cymin);
				
				//cout<<"i= "<< i <<"   "<< "j=  "<< j<< endl;
				ComplexNumber c(i,j);
				ComplexNumber z(0,0);
				int iter=0;
				int maxIter=255;

				while(iter<maxIter && z.abs()<2){
					// ComplexNumber s=z.squared();
					z=(z.squared()).add(c);
					iter++;
					//cout<<z.abs()<<" ";
					//cout << "iter" << iter << endl;
				}
				if(z.abs()>=2){
					a->setPixel(y,x,iter);
					//cout << xtable[x][y]<<" ";
					//cout << "abs"<< endl;
				}
				else {
					a->setPixel(y,x,0);
					//cout << xtable[x][y]<<" ";
				}
			}
			//PGMImage << endl;
			//cout<<endl;
		}
	a->writeFile();
	//ComplexNumber c1(3,4), c2(-2,1);
	//ComplexNumber c3=c1.squared(), c4=c1.add(c2);
	//cout << " c4 " <<c4.real<< c4.imag << endl;
	//double b=c1.abs();
	//c1.prt();
	delete a;
	return 0;
}//main


