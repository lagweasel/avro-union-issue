# avro-union-issue
Includes unit test that highlights issue with union of null/java.time.Instant
Intended to be run against scatrin:avro repo which includes improvements to better support unions.

# Instructions to reproduce issue:
Prerequisite: Before running, set up your local maven repo to use scatrin's latest snapshot (e.g. as follows, from empty folder):
1. git clone https://github.com/scatrin/avro.git
2. cd avro
3. mvn clean install
4. note the avro snapshot version (e.g. "1.9.0-SNAPSHOT")

To test the behaviour/highlight the issue, clone this repo and run the tests as follows (from empty folder):
1. git clone https://github.com/lagweasel/avro-union-issue.git
2. cd avro-union-issue.git
3. grep 'avro.version' pom.xml
   check that the 'avro.version' property value matches the avro version from the prerequisite above
   if it doesn't match, update the avro.version property accordingly in this file
4. mvn clean test

# Expected result:
Results :

Tests in error:
  testWithInstantValue(unionissue.AvroSecondaryDispatchDateTimeValueTest): java.lang.Long cannot be cast to java.time.Instant

Tests run: 2, Failures: 0, Errors: 1, Skipped: 0

# Desired result:
Both tests (testWithInstantValue and testWithNullValue) should pass.

