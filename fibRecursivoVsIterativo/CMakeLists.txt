cmake_minimum_required(VERSION 3.12)

project(fibRecursivoVsIterativo
   VERSION 0.1.0
   DESCRIPTION "C++ project by Javier Aspiroz"
   LANGUAGES CXX
)

include_directories("D://MisDocumentos//UEM//Proyectos//ProgramacionEstructurasLineales//extra-files")

set(SOURCES main.cpp)
add_executable(${PROJECT_NAME} ${SOURCES})

set_target_properties(${PROJECT_NAME} PROPERTIES
   CXX_STANDARD 17
   CXX_STANDARD_REQUIRED ON
   CXX_EXTENSIONS OFF
   RUNTIME_OUTPUT_DIRECTORY_DEBUG ${CMAKE_BINARY_DIR}/debug
   RUNTIME_OUTPUT_DIRECTORY_RELEASE ${CMAKE_BINARY_DIR}/release
)

#-------------------------------------------------------------------
# compile options:

if (CMAKE_CXX_COMPILER_ID MATCHES "MSVC")
   target_compile_options(${PROJECT_NAME} PRIVATE /W3 /WX)
elseif (CMAKE_CXX_COMPILER_ID MATCHES "Clang|GNU")
   target_compile_options(${PROJECT_NAME} PRIVATE -Wall -Wextra -pedantic -Werror)
endif()

#-------------------------------------------------------------------
# third-party libraries integration:

find_package(fmt CONFIG REQUIRED)
find_package(nlohmann_json CONFIG REQUIRED)
find_package(range-v3 CONFIG REQUIRED)

target_link_libraries(${PROJECT_NAME} PRIVATE
   fmt::fmt
   nlohmann_json::nlohmann_json
   range-v3
)