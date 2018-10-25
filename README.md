# FundaQuickSearchTests

This repository contains tests for Funda Quick search from URL: https://www.funda.nl/.

Automation framework is a gradle project which uses Selenium Webdriver, Java and TestNG. The IDE used for developing is IntelliJ IDEA.

## To execute tests:

1. Clone the project 
2. Import the project in IntelliJ IDEA as a gradle project.
3. To run using Run configurations:
>- Go to Run> Edit Configurations
>- Create a Gradle Run Configuration
>- Set task to `clean test`
>- Set VM options to `-Dapp.browserType=headless` for testing on headless browser or `-Dapp.browserType=chrome` for testing on chrome browser
>- Optionally, set Argument to `--stacktrace`
>- Save the configuration and run.
4. To run tests from command line:
>- To run on headless browser use command - `gradle clean test --stacktrace -DbrowserType=headless`
>- To run on chrome browser use command - `gradle clean test --stacktrace -DbrowserType=chrome`

At the end of every testcase screenshots are taken and stored in /screenshots folder for future reference and test report is generated in /build/reports/tests/test/index.html file.

*NOTE: Automation framework was developed on Mac.*
