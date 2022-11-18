#ifndef SUPERHERO_H
#define SUPERHERO_H

#include <iostream>
#include <stdbool.h>
#include <string>
#include <algorithm>

class Superhero {
private:
    std::string* firstName;
    std::string* lastName;
    std::string* heroName;

public:

    Superhero(); // def constructor
    Superhero(std::string* firstName, std::string* lastName, std::string* heroString); // constructor
    Superhero(const Superhero &r); // cpy constructor
    ~Superhero(); // destructor

    std::string getHeroName();

    // Operator Overloading
    Superhero &operator=(const Superhero &r);
    bool operator==(const Superhero &r) const;
    bool operator<(const Superhero &r) const;
    friend std::ostream &operator<<(std::ostream &os, const Superhero &r);
};
#endif