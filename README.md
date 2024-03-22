# MowitNow Kata
[![Github build](https://github.com/rbaoueb/mowitnow-sgcib/actions/workflows/ci.yml/badge.svg?branch=main)](https://github.com/rbaoueb/mowitnow-sgcib/actions/workflows/ci.yml)
[![AppVeyor Status](https://img.shields.io/appveyor/ci/rbaoueb/mowitnow-sgcib/main.svg?label=AppVeyor%20build)](https://ci.appveyor.com/project/rbaoueb/mowitnow-sgcib/history)
[![Sonarqube Status](https://sonarcloud.io/api/project_badges/measure?project=rbaoueb_mowitnow-sgcib&metric=alert_status)](https://sonarcloud.io/dashboard?id=rbaoueb_mowitnow-sgcib)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/a3a17032bd8f4205b737a43406127f94)](https://app.codacy.com/gh/rbaoueb/mowitnow-sgcib/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)

[![codecov](https://codecov.io/gh/rbaoueb/mowitnow-sgcib/branch/main/graph/badge.svg)](https://codecov.io/gh/rbaoueb/mowitnow-sgcib)
[![CodeFactor](https://www.codefactor.io/repository/github/rbaoueb/mowitnow-sgcib/badge)](https://www.codefactor.io/repository/github/rbaoueb/mowitnow-sgcib)


Kata implementation for simple Mower behavior

## Tools
JDK 17
BDD Cucumber/Gherking
Junit5
Maven
Jacoco
Github CI

## Report
The javadoc report of the project can be reachable through this [link](https://rbaoueb.github.io/mowitnow-sgcib/)


## Installation
you can import the source project on your IDE or build the jar and push it to your maven repository :

```bash
git clone https://github.com/rbaoueb/mowitnow-sgcib.git
cd mowitnow-sgcib
mvn clean install
```

then run the generated dependency by the command line:
```bash
java -jar mowitnow-sgcib-1.0.0-SNAPSHOT.jar /path/to/input/file
```
