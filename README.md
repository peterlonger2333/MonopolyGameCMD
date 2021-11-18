# The Monopoly Game

A command line implementation of the Monopoly Game for COMP3211 - Software Engineering at The Hong Kong Polytechnic University.

Clone this repository and give it a try: `git clone https://githubcom/McXD/MonopolyGameCMD`.

The master branch hosts the latest stable version. The minimum system requirement is you have **JRE 1.8+** installed to run the game. If you want to build, you must have the corresponding JDK as well.

## Build From Source

To build, simply run `./gradlew build`. The build process will generate the following artifacts of interest:

- A fat-jar that includes all required libraries to run the application
- A source jar that contains the application source code
- Jacoco test report

They can be found under `./build` folder.

## Run The Game

We recommend three ways to run this project if you have this repository on you machine.

### Run via IntelliJ

You can open the project in IntelliJ IDEA. The Gradle plugin should recognize this is a gradle project and prepare it for you. After it is finished, navigate to `Client.java` under `src` and hit run. Alternatively, run in debug mode if you want to inspect the system's runtime information.

### Run via Gradle

Gradle makes running this app via a console/terminal a one liner. Just navigate to the project directory and run `./gradlew -q --console plain run`

### Run via Jar

You can also use the fat-jar produced during the build process by running `java -jar ${jar_name}`. Simply replace `$jar_name` with the file generated. The jar file is typically named `MonopolyGameCMD-${version}`.