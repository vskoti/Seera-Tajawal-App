# Seera-Tajawal-App
Appium test automation for TAJAWAL App from the very Seera Group!

# Concepts Included in the framework: 
Page Object pattern,
Singleton Object Creation,
Common Mobile elements interaction methods,
Common api interaction methods,
Mavenised parameters for testng test run,
Externalised test configuration,
Parameterization from testng for parallel testing with different system ports,
Commonly used test utility classes/methods,
WebDriver scope limitation for performance enhancement

# Tools:
Java,
Maven,
Selenium Webdriver,
Appium,
TestNG,
Log4j,
Extent Reports

# Requirements:
In order to utilise this project you need to have the following installed locally:
For Android : 
Maven, 
Java 1.8,
Node,
Appium,
Adroid Studio, 
Android SDK
Note : Other required system/environment variables.

# Configuration: 
Config.properties is placed at the base project path. 
Desired capabilties for AppiumDriver is being read from Config properties. 
Note : Appium driver start & stop is handled in the script. 
Emulator needs to be created from android studio and the name has to be updated in testng.xml with systemport before the script run. 

# Usage/Run command:
Following are the mandatory entries before run : 
Below parameters(desired capabilities) are updated in testng xml: 
Emulator/Device name, System port and platform name
Update Config.properties with other desired capabiliies.

Command:
mvn clean install -Dtestnames= ${testng 'test' name} 
The tests are segragated as below : 
smoke ; which includes test groups {smoke},

# Parallel Testing 
Currently configured to run each testng <test> parallely, differing with below parameters ; Emulator/Device name, System port and platform name. 

Each test can then be parallely run. Driver is singleton and each class is enabled to have its own copy of driver for running.  
Usage:
mvn clean install -Dtestnames=smoke 
Note :  testnames wil run all groups internally mapped as above within the test -> groups -> run tag  

# Reporting:
Default Testng Report : HTML Reports for each module are written into their respective /target directories after a successful run. 
Custom Extent Report : 'Test-Reports' folder is created and the custom generated HTML report will be available in the directory after a successful run. 
The folder name and path is being taken from config properties and can be suitably configured. 

# Logging: 
Log4j : logs folder is created at the project path and generated print.log file for all log level.   

# CICD: 
The above mentioned RUN command, need to be given in the Jenkins maven build configuration. The reporting will still remain same and can be configured
for emailable report after run. 
  
