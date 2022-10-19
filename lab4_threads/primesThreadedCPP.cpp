#include <iostream>
#include <thread>

int isPrime(int num);
int numPrimes(int low, int high);

int main()
{

}

int isPrime(int num)
{
    for (int i = 2; i < num; i++)
    {
        if (num % i == 0)
            return 0;
    }
    return 1;
}

int numPrimes(int low, int high)
{
    int count = 0;
    for (int i = low; i < high; i++)
    {
        count += isPrime(i);
    }
    return count;
}