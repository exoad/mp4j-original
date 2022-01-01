#include <unistd.h>

#include <algorithm>
#include <fstream>
#include <iostream>
#include <string>
#include <vector>

#pragma comment(lib, "cryptlib.lib")
#pragma comment(lib, "libeay32.lib")

#define speed ios::sync_with_stdio(false)
#define endl "\n"

using namespace std;

const string def_path = "/resource/";

int main() {
  speed;
  string temp = "";
  ifstream list("./files.txt");
  while (getline(list, temp)) {
    ifstream ifile;
    ifile.open(temp);
    if (ifile) {
      temp = "";
      continue;
    } else {
      cout << temp << endl;
      /**
       * @param  {} undefined : 
       */
      cout << "1" << endl;
      return 1;
    }
  }
  cout << "0" << endl;
  return 0;
}