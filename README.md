# Aequilibrium Projects 
Submitted on Tuesday 19 July - 10:50 am

# Project Title: The CastleCompany
The CastleCompany

## How to Run
1- main method: src->aequilibrium->Main.java
2- Use the test units

## Works Done
1- Design and Implementation of the project. 
2- Adding some test units: testcases package
3- Adding Java docs: doc folder: start here: /AEQWork/TheCastleCompany/doc/index.html

## Algorithm
1- Algorithms is described in CastlesBuilderFunction class
2- The complexity order is O(n)

## How to Improve
1- Adding more test cases
2- Improving the documentations


## Design Patterns
1- Functional Programming (Functional interfaces)

## Assumptions
In addition to the assumptions described in the project description, I have 
made the following assumptions:

1- I have made this assumption that, it is possible to build a castle at the end of the land too.
for instance for this input [1,2,1], 3 castles are possible
and for this input [1,2,2], 2 castles are possible


# Project Title: The TransformationCompany
The TransformationCompany

# How to Run
1- main method: src->aequilibrium->Main.java
2- Use the test units

## Works Done
1- Design and Implementation of the project
2- Adding some test units: testcases package
3- Adding Java docs: doc folder: start here: /AEQWork/TheTransformationCompany/doc/index.html

## How to improve
1- Adding more test cases
2- Improving the documentations

## Design patterns
1- Functional Programming
2- Iterator/Iterable
3- Observer/Observable
4- Builder

## Assumptions
In addition to the assumptions described in the project description, I have 
made the following assumptions:

1- Based on this statement "Each Transformer must either be an Autobot or a Deception"

in the project description, I have made this assumption that, there should be no other type of Transformers

except Autobot and Deception. In the proposed design, there is a class called Transformer which is the parent

of Autobot and Deception. Since my assumption is that there should be no other type of Transformers than Autobot and Deception,

The class Transformer is not inheritable anymore from outside class TransformerFactory.

In other words, although the class Transformer is the parent of Autobot and Deception, but it is not inheritable

from outside class TransformerFactory. To this end, the Transformer class is defined as a private inner class inside the class

TransformerFactory. The class Autobot and Deception also have to be defined as public inner classes inside TransformerFactory.

This is because, the classes Autobot and Deceptions both have the class Transformer as their parent and the class Transformer is only

visible to inner classes inside the TransformerFactory class.


Such a design makes sure the user cannot define any other type of transformers except Autobot and Deception.

Such a design comes with its own limitations. For instance since the parent class (the Transformer) is not visible

outside the class TransformerFactory, I could not fully leverage the polymorphism, and therefore some similar methods are defined

both in Autobot and Deception class. 
