echo off
javac -encoding UTF-8 *.java
set inputstr= 
set /P inputstr="class name? "
java %inputstr%
pause