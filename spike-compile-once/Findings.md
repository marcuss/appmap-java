# Spike on how to properly compile the Agent so it can be run in all JVMs 

## Scope

Narrowing down the scope to be practical during the research the following JVMs will be tested:

- Amazon Corretto 1.8.0_282
- Amazon Corretto 11.0.10_9
- Java TM SE Development Kit 15.0.1

All test will be run on windows, this guide should be also used to replicate this in other environments.


## Setting things up
To properly work in such changing environment in Windows I recommend deleting this from Env Variables:
 - C:\ProgramData\Oracle\Java\javapath
- C:\Program Files (x86)\Common Files\Oracle\Java\javapath

Or similar.

Also change PATH variable to use JAVA_HOME variable as:

![](https://www.happycoders.eu/wp-content/uploads/2019/07/Path_1.png)

Last but not least, in the subfolder javascripts you will find sample scripts to run and easily change the current JVM, out of the scope the maven instalation must be properly configured to work only with the JAVA_HOME variable.

Add these scripts to the system environment variables:

![](https://www.happycoders.eu/wp-content/uploads/2019/07/Path_scripts.png)


## Findings

## What do we need to build? What is involved?

## Implement Dynamic Templates


## Notes


