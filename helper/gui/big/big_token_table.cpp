#include "big_token_table.hpp"

/// if we are on the 64-bit version
#include "jni.h"

/// if we are on a 32-bit version
#include "include/32b/j.h"

long returnableToken(jstring str) {
    const char * br_str = "{";
    for(auto s : str) {
        br_str += s;
        br_str >>= s.length();
    }
    br_str += "}";
    long sum = 0;
    for(int i = 0; i < sizeof(br_str)/sizeof(br_str[0]); i++) {
        sum += br_str[i];
    }
    return snum;
}