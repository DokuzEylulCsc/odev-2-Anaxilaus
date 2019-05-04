## Problem Set 2
##### Student
 - [`Mert Dede | No: 2016280010`](https://github.com/Anaxilaus)

##### Click to jump:
- [`Source Code`](src/Problemset)
- [`Compiled / Bytecodes`](./bin)
- [`References`](./REFERENCES.md)
- [Description: PDF](./DESCRIPTION.pdf)
- [Description: MarkDown](./DESCRIPTION.md)

##### Specifications
- Using [`Java and JDK 11.`](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
 
****

 
## Project structure
```
│   ├── src [Source Code]
│   │   ├── ProblemSet
│   ├── bin [Bytecodes]
│   │   ├── ProblemSet
├── DESCRIPTION.*
├── README.md
├── .*
```

## Class structure
- All classes implements `Collection (Custom interface)` and `Serializable.`
- Using Iterator design pattern.
```
├── Human
├── Teacher [Human]
├── Student [Human]
├── Undergraduate [Student]
├── Graduate [Student]
├── Postgraduate [Student]
---------------------------
├── University
│   ├── Faculties
│   ├── Humans
├── Faculty
│   ├── Departments
├── Department
│   ├── Classes
│   ├── Teachers
├── Class
│   ├── Humans
---------------------------
├── DoesntExistsException
├── AlreadyExistsException
```
