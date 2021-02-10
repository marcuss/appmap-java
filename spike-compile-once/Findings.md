# Spike on how to properly compile the Agent so it can be run in all JVMs 

## Scope

Narrowing down the scope to be practical during the research the following JVMs will be tested:

- Amazon Corretto 1.8.0_282
- Amazon Corretto 11.0.10_9
- Java TM SE Development Kit 15.0.1

All test will be run on windows, this guide should be also used to replicate this in other environments and update accordingly.


## Setting things up
To properly work in such changing environment in Windows I recommend deleting this from Env Variables:
 - C:\ProgramData\Oracle\Java\javapath
- C:\Program Files (x86)\Common Files\Oracle\Java\javapath

Or similar.

Also change PATH variable to use JAVA_HOME variable as:

![](https://www.happycoders.eu/wp-content/uploads/2019/07/Path_1.png)

Last but not least, in the subfolder javascripts you will find sample scripts to run and easily change the current JVM.


Add these scripts to the system environment variables:

![](https://www.happycoders.eu/wp-content/uploads/2019/07/Path_scripts.png)

Out of the scope of the guide you should also check that the maven installation is properly configured to work only with the JAVA_HOME variable.

The project used to test will be petclinic sample from spring-boot samples:

https://github.com/spring-projects/spring-petclinic

## Findings

### Agent compiled with JVM 1.8
* Ran with JVM 1.8 *OK*
``` bash
/c/code/spring-petclinic (main)
$ java -version
openjdk version "1.8.0_282"
OpenJDK Runtime Environment Corretto-8.282.08.1 (build 1.8.0_282-b08)
OpenJDK 64-Bit Server VM Corretto-8.282.08.1 (build 25.282-b08, mixed mode)
```
 dir *.* tmp >> [agent_jdk1_8_ran_jdk_1_8.txt](results/agent_jdk1_8_ran_jdk_1_8.txt)


* Ran with JVM 11  *OK*
``` bash
/c/code/spring-petclinic (main)
$ java --version
openjdk 11.0.10 2021-01-19 LTS
OpenJDK Runtime Environment Corretto-11.0.10.9.1 (build 11.0.10+9-LTS)
OpenJDK 64-Bit Server VM Corretto-11.0.10.9.1 (build 11.0.10+9-LTS, mixed mode)
```
dir *.* tmp >> [agent_jdk1_8_ran_jdk_11.txt](results/agent_jdk1_8_ran_jdk_11.txt)

## What do we need to build? What is involved?

## Implement Dynamic Templates


## Notes

