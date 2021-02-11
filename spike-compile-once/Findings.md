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

/c/code/spring-petclinic (main)
$ mvn -v
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: C:\maven
Java version: 15.0.1, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk-15.0.1
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"

```
dir *.* tmp >> [agent_jdk1_8_ran_jdk_11.txt](results/agent_jdk1_8_ran_jdk_11.txt)

* Ran with JVM 15 *OK*
``` bash
/c/code/spring-petclinic (main)
$ java -version
java version "15.0.1" 2020-10-20
Java(TM) SE Runtime Environment (build 15.0.1+9-18)
Java HotSpot(TM) 64-Bit Server VM (build 15.0.1+9-18, mixed mode, sharing)
``` 
dir *.* tmp >> [agent_jdk1_8_ran_jdk_15.txt](results/agent_jdk1_8_ran_jdk_15.txt)



### Agent compiled with JVM 11

* Ran with JVM 1.8 *OK*


* Ran with JVM 11 *OK*
``` bash
/c/code/spring-petclinic (main)
$ java --version
openjdk 11.0.10 2021-01-19 LTS
OpenJDK Runtime Environment Corretto-11.0.10.9.1 (build 11.0.10+9-LTS)
OpenJDK 64-Bit Server VM Corretto-11.0.10.9.1 (build 11.0.10+9-LTS, mixed mode)

/c/code/spring-petclinic (main)
$ mvn -version
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: C:\maven
Java version: 11.0.10, vendor: Amazon.com Inc., runtime: C:\Program Files\Amazon Corretto\jdk11.0.10_9
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"

``` 

dir *.* tmp >> [agent_jdk11_ran_jdk_11.txt](results/agent_jdk11_ran_jdk_11.txt)


* Ran with JVM 15 *OK*

``` bash
 /c/code/spring-petclinic (main)
$ java -version
java version "15.0.1" 2020-10-20
Java(TM) SE Runtime Environment (build 15.0.1+9-18)
Java HotSpot(TM) 64-Bit Server VM (build 15.0.1+9-18, mixed mode, sharing)

/c/code/spring-petclinic (main)
$ mvn -version
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: C:\maven
Java version: 15.0.1, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk-15.0.1
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```

dir *.* tmp >> [agent_jdk11_ran_jdk_15.txt](results/agent_jdk11_ran_jdk_15.txt)

## What do we need to build? What is involved?




## Notes

