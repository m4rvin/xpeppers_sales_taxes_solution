# XPeppers Sales-Taxes solution
## Requisite
* Java JDK 1.7
* Maven
## Build
From the folder containing the pom.xml file type `mvn package` (unit tests will get automatically executed before packaging).
## Clean
From the folder containing the pom.xml file type `mvn clean`.
## Execute
java -jar `path_to_jar_with_dependencies` `categories_filepath` `input_filepath`(e.g. `java -jar target/XPeppersSalesTaxes-0.0.1-SNAPSHOT-jar-with-dependencies.jar categories.json input1.txt`)
