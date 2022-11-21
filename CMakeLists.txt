cmake_minimum_required(VERSION 3.22.3)
project(iCE_Climbers C)

set(CMAKE_C_STANDARD 11)

add_executable(iCE_Climbers
        Servidor.c)

if(MINGW)
    target_link_libraries(iCE_Climbers ws2_32)
endif()
