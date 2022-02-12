package backend.cxx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import backend.Tool;
import backend.ToolInterface;

import app.global.SystemType;

/**
 * Exposed Class for interface
 */

public class Maker extends ToolInterface implements Tool {

 ArrayList<String> arr = new ArrayList<>();
 Commands type;
 final Runtime rt = Runtime.getRuntime();

    public Maker(Commands e, String... files) {
        for (String er : files) {
            assert er != null;
            arr.add(er);
        }
        type = e;

    }

    private boolean checkIfAvaliable(Notions e) {
        try {
            rt.exec(e.getCommand());
        } catch (IOException e1) {
            return false;
        }
        return true;
    }

    private boolean checkIfAvaliable(NotionsMake e) {
        try {
            rt.exec(e.getCommand() + " --version");
        } catch (IOException e1) {
            return false;
        }
        return true;
    }

    public String compileNRun(NotionsMake x) throws IOException {
        Process proc = Runtime.getRuntime().exec(x.getCommand() + " " + arr.get(0) + " -o " + SystemType.removeFileExtension(arr.get(0)) + SystemType.osCXXExec());
        proc = rt.exec(SystemType.removeFileExtension(arr.get(0)) + SystemType.osCXXExec());
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

        String s = null;
        while ((s = stdInput.readLine()) != null) {
            return s;
        }
        return null;
    }

    public void main(String[] args) {

        if (type == Commands.G_PLUS_PLUS) {
            if (checkIfAvaliable(Notions.G_PLUS_PLUS)) {
                System.out.println("G++ is avaliable");
                for (String er : arr) {
                    System.out.println(er);
                }
            } else {
                System.out.println("G++ is not avaliable");
            }
        } else if (type == Commands.GCC) {
            if (checkIfAvaliable(Notions.GCC)) {
                System.out.println("GCC is avaliable");
                for (String er : arr) {
                    System.out.println(er);
                }
            } else {
                System.out.println("GCC is not avaliable");
            }
        } else if (type == Commands.CLANG_PLUS_PLUS) {
            if (checkIfAvaliable(Notions.CLANG_PLUS_PLUS)) {
                System.out.println("CLANG++ is avaliable");
                for (String er : arr) {
                    System.out.println(er);
                }
            } else {
                System.out.println("CLANG++ is not avaliable");
            }
        }
    }

    @Override
    public void callCell(String[] args) {
        // DO NOTHING
    }
}