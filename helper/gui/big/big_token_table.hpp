#pragma once

#ifndef TOKEN_TABLE_HXX 
#define TOKEN_TABLE_HXX

/// where each char in the buffer is being shifted to the right with an unsigned bitwise operator (>>>)
const uint64_t*** DEFAULT_CHAREABLE_ARRAY = {
    {{0x20, 0x01, 0x04, 0x06, 0x05, 0x77}, 25565},
    {{0x01, 0x04, 0x06, 0x05, 0x77, 0x16}, 25565},

};

#endif