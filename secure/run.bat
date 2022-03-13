@rem Runs the current batch load of Java Source Files and compiles them to a local *.class & *.jar file; JVM
gradlew ./build
rm -f src-test/MediaPlayer.class
java src-test/MediaPlayerTester.java