/**
 * @brief API Wrapper and Caller
 * @author Jack Meng
 * @date 2021/12/31
 *
 * This program is pretty simple, just
 * calls the API and writes it to a file
 *
 * Never shall this file be run by a standalone user
 * and not without a separate program calling this
 * file.
 *
 * Licensed under the EPL-2.0 License, see LICENSE
 */

#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>

#include <cstring>
#include <fstream>
#include <iostream>
#include <string>

using namespace std;

void maker_run(string cmd) {
  system(cmd.c_str());
  
}
int main(int argc, char **argv) {
  std::string url = "https://exoad.github.io/MusicPlayer/";
  struct stat st = {0};
  if (stat("mp_cache", &st) == -1) {
    mkdir("mp_cache", 0777);
  }
  std::string file = "./MusicPlayer/cache/api_wrapper.json";
  std::string cmd = "curl " + url + " > " + file;

  if (cmd.c_str() == NULL) {
    cout << 1;
  } else {
    maker_run(cmd.c_str());
    /**
     * @param  {file} undefined : 
     * @return {std::ifstream}  : 
     */
    std::ifstream ifs(file);
    std::string content((std::istreambuf_iterator<char>(ifs)),
                        (std::istreambuf_iterator<char>()));
    ifs.close();
    cout << content;


    // make a copy of file but with the current unix time
    std::string new_file = "./MusicPlayer/cache/api_wrapper_" +
                           std::to_string(time(NULL)) + ".json";
    std::ofstream ofs(new_file);
    ofs << content;
    ofs.close();
    

  }
}
