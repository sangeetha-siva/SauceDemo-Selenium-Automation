Run all tests

   mvn test
   
Run specific test class
 
mvn test -Dtest=LoginTest

Run smoke tests only
Edit testng.xml to run only the Smoke Tests section.

Test Results

After running, TestNG generates a report at---target/surefire-reports/index.html
