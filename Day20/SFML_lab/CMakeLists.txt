cmake_minimum_required(VERSION 3.10)

project(testSFML)

set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_STANDARD_REQUIRED True)

set(SRCDIR ../src)

include_directories(/opt/homebrew/include)

find_package(SFML 2.5 REQUIRED COMPONENTS graphics system window)

add_executable(game ${SRCDIR}/main.cpp)

target_link_libraries(game sfml-graphics sfml-system sfml-window)


