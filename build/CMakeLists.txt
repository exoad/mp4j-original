cmake_minimum_required(VERSION ";%s" )

find_package(Java REQUIRED)

include(UseJava)

project(MusicPlayer4J NONE)

set(CMAKE_JAVA_COMPILE_FLAGS "-source 1.7 -target 1.7")

# Incomplete for Java