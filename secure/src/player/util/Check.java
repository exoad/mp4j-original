package player.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Util Checker Class</h1>
 *
 * <p>
 *     This is a precursor checker class.
 *     It prioritizes the checking of the contents
 *     of a file in order to finalize it's contents.
 *
 *     For the current iteration, it checks for the status
 *     of a file, for example if it is empty, etc.
 * </p>
 *
 * @see java.io.File
 * @see java.io.FileReader
 */

public final class Check {
    private Check(){
    }

    public static boolean isEmpty(File f) {
        try(BufferedReader br = new BufferedReader(new FileReader(f))) {
            if(!br.readLine().isEmpty()) return false;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @org.jetbrains.annotations.NotNull
    public static List<String> getContent(File f) {
        ArrayList<String> s = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while((line = br.readLine()) != null) {
                s.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}
