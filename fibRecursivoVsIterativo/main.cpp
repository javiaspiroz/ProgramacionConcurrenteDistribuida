#include<iostream>
#include <chrono>
using namespace std;
using namespace std::chrono;

// ITERATIVE
int fibI(int n){
	int fib[] = {0,1,1};
	for(int i=2; i<=n; i++){
		fib[i%3] = fib[(i-1)%3] + fib[(i-2)%3];
		cout << "fib(" << i << ") = " << fib[i%3] << endl;
	}
	return fib[n%3];
}
// RECURSIVE
int fibR(int n){
	if(n<=0) return 0;
	else if(n==1) return 1;
	else return fibR(n-1)+fibR(n-2);
}

int main(){
	int a = 10;
	auto start = high_resolution_clock::now();
	cout << endl << "From recursive function" << endl;
	for(int i=1; i<=a; ++i)
		cout << "fib(" << i << ") = " << fibR(i) << endl;
	auto stop = high_resolution_clock::now();
	auto duration = duration_cast<microseconds>(stop - start);
	cout << "Tiempo "<< duration.count()<< " microseconds" << endl;

	auto start1 = high_resolution_clock::now();
	cout << "From iterative function" << endl; fibI(a);
	cout << endl;
	auto stop1 = high_resolution_clock::now();
	auto duration1 = duration_cast<microseconds>(stop1 - start1);
	cout << "Tiempo "<< duration1.count()<< " microseconds" << endl;

	cout<< "ITERATIVO es "<< duration.count()/duration1.count() << " VECES mas RAPIDO que el RECURSIVO";
	return 0;
}