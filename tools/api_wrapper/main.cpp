#include <sys/stat.h>
#include <sys/types.h>

#include <fstream>
#include <iostream>
#include <string>

using namespace std;

int main(int argc, char** argv) {
  std::string url = "https://exoad.github.io/MusicPlayer/";
  struct stat st = {0};
  if (stat("api_cache", &st) == -1) {
    mkdir("api_cache", 0777);
  }
  std::string file = "./api_cache/api_wrapper.json";
  std::string cmd = "curl " + url + " > " + file;

  if (cmd.c_str() == NULL) {
    cout << 1;
  } else {
    system(cmd.c_str());
  }
}