#include <iostream>
#include <thread>
#include <omp.h>

// compile like: g++ vecAdd.cc -std=c++11 -pthread -o vecAdd
int isPrime(int num);
void myRun(int start, int stop, int index, int* count);

int main() {
    int low = 1000;
    int high = 1000000;
    int range = high - low;

    int numThreads = 5;
    //std::thread* ths[numThreads];

    
    int* count = (int*)malloc(numThreads * sizeof(int));

    omp_set_num_threads(numThreads);

    #pragma omp parallel
    {	
	int index = 0;
	int total = 0;
	double wtime = omp_get_wtime();

	#pragma omp for schedule(static, 5)
	for(int i = low; i < high; i++){
            total += isPrime(i);
	}
	wtime = omp_get_wtime() - wtime;
	std::cout << omp_get_thread_num() << " : " << wtime << " : " << total << "\n";
	index++;
    }

    
    free(count);
}

int isPrime(int num) {
    for (int i = 2; i < num; i++) {
        if (num % i == 0)
            return 0;
    }
    return 1;
}
