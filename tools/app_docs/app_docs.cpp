#include<fstream>
#include<iostream>
#include<vector>
#include<cstring>
#include<string>

#pragma warning(disable:4996)
#pragma warning(disable:4244)
#pragma comment(lib, "ws2_32.lib")
#pragma comment(lib, "winmm.lib")
#pragma GCC diagnostic ignored "-Wwrite-strings"

#define MAX_BUFFER_SIZE 1024
#define MAX_FILE_SIZE 1024
#define speed ios::sync_with_stdio(false);     \
              cin.tie(0);                      \
              cout.tie(0)                      \

using namespace std;

const string def_path = "./MusicPlayer/";
const string doc_file = "../MusicPlayer/Properties_doc.txt";
const string properties_doc = "> Properties Documentation <\n\nProperty Name | Description\ngui.defaultTheme | Changes the LAF of the MusicPlayer\nAvaliable Confs: regulardark, materia, onedark, arcdark, dracula, nord, gruvbox, vuesion, regularlight, solarized\n\nexplorer.defaultDir | Default Spawn Directory for the File Explorer:\n\".\", \"~\", \"/\"\n\nrunner.disableCache | Disable Caching\ntrue, false";

int main(int argc, char **argv) {
  speed;
  try {
    ofstream fout(doc_file);
    fout << properties_doc;
    fout.close();
  }
  catch (exception e) {
    cout << e.what();
    return 1;
  }

  cout << "0" << endl;
  return 0;
}