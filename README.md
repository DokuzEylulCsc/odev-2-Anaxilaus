## Problem Set 2
##### Student
 - [`Mert Dede | No: 2016280010`](https://github.com/Anaxilaus)

##### Specifications
- Using [`Java and JDK 11.`](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)

##### Click to jump
- [Sample XML output](./BIL2002.xml)
- [`Source Code`](src/Problemset)
- [`Compiled / Bytecodes`](./bin)
- [Description: PDF](./DESCRIPTION.pdf)
- [Description: MarkDown](./DESCRIPTION.md)
- [`References`](./REFERENCES.md)

## Project structure
```
│   ├── src [Source Code]
│   │   ├── ProblemSet
│   ├── bin [Bytecodes]
│   │   ├── ProblemSet
├── BIL2002.xml [Sample XML output]
├── BILG1001.xml [Sample XML output]
├── University.ser [Optional serialization]
├── DESCRIPTION.*
├── README.md
├── .*
```

## Class structure

- Using Iterator Design Pattern.

```
├── Human
│   ├── Classes [iterator]
├── Teacher [Human]
├── Student [Human]
├── Undergraduate [Student]
├── Graduate [Student]
├── Postgraduate [Student]

---------------------------

├── University
│   ├── Faculties [iterator]
│   ├── Humans [createHumanIterator]
├── Faculty
│   ├── Departments [iterator]
├── Department
│   ├── Classes [iterator]
│   ├── Teachers [iterator]
├── Class
│   ├── Humans [iterator]

---------------------------

├── DoesntExistsException
├── AlreadyExistsException

---------------------------

├── Iterable
```
