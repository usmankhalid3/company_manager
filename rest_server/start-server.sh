echo 'Stopping the server..'
ps -ef | grep "java" | grep -v grep | awk '{print $2}' | xargs kill -9
echo 'Purging class files..'
rm -rf bin/
mkdir bin
echo 'Compiling code..'
javac -d bin -sourcepath src -cp lib/*.jar src/com/rest/server/core/Server.java
echo 'Starting server..'
nohup java -cp bin:lib/*.jar com.rest.server.core.Server > daemon.out 2>&1 &