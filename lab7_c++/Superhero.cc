#include <iostream>
#include "Superhero.h"

//
// Constructors
//

Superhero::Superhero() {
}

Superhero::Superhero(std::string *firstName, std::string *lastName, std::string *heroString) {
    this->firstName = firstName;
    this->lastName = lastName;
    this->heroName = heroName;
}

// Copy constr
Superhero::Superhero(const Superhero &r) {
    this->firstName = r.firstName;
    this->lastName = r.lastName;
    this->heroName = r.heroName;
}

//
// Methods
//

std::string Superhero::getHeroName() {
    std::string* name = new std::string();
    *name = *(this->firstName) + " " + *(this->lastName);
    return *name;   
}

//
// Operator Overloading
//

Superhero &Superhero::operator=(const Superhero &r) {
    this->firstName = r.firstName;
    this->lastName = r.lastName;
    this->heroName = r.heroName;
    
    return *this;
}

Superhero::~Superhero() {
    //std::cout << "Dest called\n";
}

bool Superhero::operator==(const Superhero &r) const {
    std::string* fn = this->firstName;
    std::string* rfn = r.firstName;

    std::string* ln = this->firstName;
    std::string* rln = r.firstName;

    return (*fn).compare((*rfn)) && (*ln).compare((*rln));
}

bool Superhero::operator<(const Superhero &r) const {
    std::string* fn = this->firstName;
    std::string* rfn = r.firstName;

    std::string* ln = this->firstName;
    std::string* rln = r.firstName;

    return ((*fn).compare((*rfn)) < 0);
}

std::ostream &operator<<(std::ostream &os, const Superhero &r)
{
    os << r.firstName << " " << r.lastName;
    return os;
}