// g++ heromain.cc Superhero.cc -std=c++11 -o hero
// The c++11 is to switch to the 2011 defns so we can use "auto"

#include <iostream>
#include <vector>
#include <string>
#include "Superhero.h"

int main() {

  std::cout << "\n---- Make two new heros on the heap ----\n";  
  Superhero* s1 = new Superhero(new std::string("Johnny"), new std::string("Storm"), new std::string("HumanTorch"));
  Superhero* s2 = new Superhero(new std::string("Peter"), new std::string("Parker"), new std::string("Spiderman"));
  std::cout << *s1 << " is " << s1->getHeroName() << "\n";  // Johnny Storm
  std::cout << *s2 << " is " << s2->getHeroName() << "\n";  // Peter Parker

 

  std::cout << "\n---- Make a copy of s1 ----\n";
  Superhero* s3 = new Superhero(*s1);
  std::cout << *s1 << " is " << s1->getHeroName() << "\n";  // Johnny Storm
  std::cout << *s2 << " is " << s2->getHeroName() << "\n";  // Peter Parker
  std::cout << *s3 << " is " << s3->getHeroName() << "\n";  // Johnny Storm

  

  std::cout << "\n---- Reassign s3 to s4 ----\n";
  Superhero* s4 = new Superhero(new std::string("Susan"), new std::string("Storm"), new std::string("InvisibleWoman"));
  *s3 = *s4;
  std::cout << *s1 << " is " << s1->getHeroName() << "\n";  // Johnny Storm
  std::cout << *s2 << " is " << s2->getHeroName() << "\n";  // Peter Parker
  std::cout << *s3 << " is " << s3->getHeroName() << "\n";  // Susan Storm

  std::cout << *s4 << " is " << s4->getHeroName() << "\n";  // Susan Storm

 

  std::cout << "\n---- Check for equality ----\n";
  if (*s1 == *s3) {
    std::cout << "1 equal 3\n";    
  } else {
    std::cout << "1 NOT equal 3\n";   // NOT equal
  }  

  Superhero* s5 = new Superhero(new std::string("Peter"), new std::string("Parker"), new std::string("Spiderman"));
   if (*s2 == *s5) {
     std::cout << "2 equal 5\n";  // equal
  } else {
     std::cout << "2 NOT equal 5\n";
  }

  

  std::cout << "\n---- Make a list of Superheros ----\n";
  Superhero* s6 = new Superhero(new std::string("Tony"), new std::string("Stark"), new std::string("Ironman"));
  std::vector<Superhero> theSuperheros;
  theSuperheros.push_back(*s1);  // Johnny Storm
  theSuperheros.push_back(*s2);  // Peter Parker
  theSuperheros.push_back(*s3);  // Susan Storm
  theSuperheros.push_back(*s4);  // Susan Storm
  theSuperheros.push_back(*s5);  // Peter Parker
  theSuperheros.push_back(*s6);  // Tony Stark

  // Normal for loop example
  for (int i=0; i<theSuperheros.size(); i++) {
    std::cout << theSuperheros[i] << "\n";
  }

  // Soring example
  std::cout << "\n---- Sort the list of Superheros ----\n";
  std::sort(theSuperheros.begin(), theSuperheros.end());

  // Peter Parker
  // Peter Parker
  // Tony Stark
  // Johnny Storm
  // Susan Storm
  // Susan Storm

  // Iterators rather than for loop example
  for (auto i=theSuperheros.begin(); i!=theSuperheros.end(); i++) {

    std::cout << *i << "\n";
  }

 

  std::cout << "\n---- Deleting the Superheros ----\n";
  delete(s1);
  delete(s2);
  delete(s3);
  delete(s4);
  delete(s5);
  delete(s6);
}