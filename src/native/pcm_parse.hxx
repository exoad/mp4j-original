#ifndef PCM_PARSE_HXX
#define PCM_PARSE_HXX

    #ifndef _GLIBCXX_IOSTREAM
    #include <iostream>

    #define debug(n) std::cout << "[DEBUG] : "<< #n << " @ " << &n << " = " << &(*n) << std::endl;
    #endif

#define BITRATE_INDEX_START 0x32
#define BITRATE_INDEX_END 0x320
#define BITRATE_INDEX_BAD 256
#define BITRATE_NATIVE_BUFFER_SIZE 4096
#define NATIVE_DEBUGGER_SERIAL tpe1

#define STD_POS_RATE 0x41400

#define HEX_STD 43
#define HEX_STD_M 4F

#endif