logparser
--------------------------------------------------------------------------------

logparser parses an apache httpd log and saves it in json format. If you do not
give a file name as argument it will use the file "sample.log". The output file
name will be the same as the log file name with .json prepended to it. If the
json file already exists it will read this. Delete the json file to force
logparser to reparse the log file.

If given a port number as argument logparser will run as a http server. You can
access log statistics at: http://localhost:<port>/stats


Build status
--------------------------------------------------------------------------------
master:

[![Build Status](https://semaphoreci.com/api/v1/nilshb/logparser/branches/master/badge.svg)](https://semaphoreci.com/nilshb/logparser)


Build deployment package
------------------------
mvn clean package


Run tests
------------------------
mvn test


Run logparser
------------------------
java -jar target\logparser-<version>-jar-with-dependencies.jar [file=<file name>] [port=<server port>]

Example:
java -jar target/logparser-1.0-SNAPSHOT-jar-with-dependencies.jar port=4242
